# Termux Improved IDE - Architecture Guide

## System Architecture Overview

```
┌──────────────────────────────────────────────────────┐
│         Termux Improved IDE (Android App)            │
├──────────────────────────────────────────────────────┤
│                                                      │
│  ┌────────────────────────────────────────────────┐ │
│  │         User Interface Layer (UI)              │ │
│  │  ┌──────────┐ ┌──────────┐ ┌──────────┐       │ │
│  │  │  Editor  │ │ Terminal │ │ Explorer │       │ │
│  │  └──────────┘ └──────────┘ └──────────┘       │ │
│  └────────────────────────────────────────────────┘ │
│                        │                            │
│  ┌────────────────────▼─────────────────────────┐  │
│  │      Business Logic & Services Layer         │  │
│  │  ┌─────────────┐ ┌──────────┐ ┌───────────┐ │  │
│  │  │ Build Mgmt  │ │Compiler  │ │Interpreter│ │  │
│  │  └─────────────┘ └──────────┘ └───────────┘ │  │
│  │  ┌─────────────┐ ┌──────────┐ ┌───────────┐ │  │
│  │  │Project Mgmt │ │  Debug   │ │ FileOps   │ │  │
│  │  └─────────────┘ └──────────┘ └───────────┘ │  │
│  └────────────────────────────────────────────────┘ │
│                        │                            │
│  ┌────────────────────▼─────────────────────────┐  │
│  │    Core Services & Utilities                 │  │
│  │  ┌──────────────┐ ┌─────────────────┐       │  │
│  │  │Language Mgmt │ │Syntax Highlight │       │  │
│  │  └──────────────┘ └─────────────────┘       │  │
│  └────────────────────────────────────────────────┘ │
│                        │                            │
│  ┌────────────────────▼─────────────────────────┐  │
│  │    Termux Integration Layer                  │  │
│  │  ┌──────────┐ ┌──────────┐ ┌──────────┐     │  │
│  │  │Terminal  │ │PackageMgr│ │Binaries  │     │  │
│  │  │Emulator  │ │(apt/pkg) │ │(GCC, etc)│     │  │
│  │  └──────────┘ └──────────┘ └──────────┘     │  │
│  └────────────────────────────────────────────────┘ │
│                        │                            │
└────────────────────────┼────────────────────────────┘
                         │
        ┌────────────────┼────────────────┐
        │                │                │
┌───────▼────────┐ ┌────▼────────┐ ┌────▼────────┐
│  Python 3.x    │ │   GCC/Clang  │ │  Node.js    │
│  Interpreter   │ │  Compilers   │ │ JavaScript  │
└────────────────┘ └──────────────┘ └─────────────┘
```

## Component Breakdown

### 1. UI Layer

#### Editor UI Component
```
EditorActivity
├── SyntaxHighlighter
├── CodeCompletionEngine
├── LineNumberView
├── BracketMatcher
└── SearchPanel
```

**Responsibilities:**
- Display source code with syntax highlighting
- Handle user input (typing, selection, copy/paste)
- Provide code completion suggestions
- Manage undo/redo operations
- Display line numbers and errors

#### Terminal UI Component
```
TerminalActivity
├── TerminalView
├── InputHandler
├── OutputBuffer
└── ShellCommandExecutor
```

**Responsibilities:**
- Display terminal output
- Handle user input commands
- Manage terminal sessions
- Execute shell commands

#### Project Explorer Component
```
ExplorerActivity
├── FileTree
├── FileContextMenu
├── NewFileDialog
└── FileOperations
```

**Responsibilities:**
- Display project file structure
- Handle file operations (create, delete, rename)
- Manage project hierarchy
- Quick navigation

### 2. Business Logic Layer

#### Compiler Service
```
CompilerService
├── LanguageDetector
├── CompilationConfig
├── ErrorParser
└── OutputHandler
```

**Responsibilities:**
- Detect programming language from file extension
- Configure compilation flags
- Execute compiler (GCC, Clang)
- Parse and display compilation errors
- Generate executable/object files

#### Interpreter Service
```
InterpreterService
├── PythonInterpreter
├── JavaScriptInterpreter
├── RubyInterpreter
└── RuntimeEnvironment
```

**Responsibilities:**
- Execute interpreted languages
- Manage runtime environment
- Handle stdin/stdout
- Manage package dependencies

#### Build System Service
```
BuildService
├── BuildConfiguration
├── BuildRules
├── DependencyResolver
└── BuildExecutor
```

**Responsibilities:**
- Manage build configuration (Makefile, CMakeLists.txt)
- Resolve dependencies
- Execute build process
- Track build artifacts

#### Debug Service
```
DebugService
├── Breakpoint Manager
├── VariableInspector
├── CallStackTracer
└── GDBInterface
```

**Responsibilities:**
- Set/manage breakpoints
- Step through code execution
- Inspect variables
- Display call stack
- Interface with GDB debugger

### 3. Language Support Architecture

```
LanguageInterface (Abstract Base)
├── getPythonLanguage()
│   ├── Interpreter: Python 3.x
│   ├── PackageManager: pip
│   └── Features: Virtual envs, REPL
├── getCppLanguage()
│   ├── Compiler: G++
│   ├── BuildTool: Make/CMake
│   └── Debugger: GDB
├── getCLanguage()
│   ├── Compiler: GCC
│   ├── BuildTool: Make
│   └── Debugger: GDB
├── getWebLanguages()
│   ├── HTML/CSS/JS: Built-in
│   ├── Runtime: Node.js
│   └── Server: HTTP server
└── getJavaLanguage()
    ├── Compiler: javac
    ├── Runtime: JVM
    └── BuildTool: Gradle
```

## Data Flow Diagrams

### Code Compilation Flow
```
User writes code in Editor
        │
        ▼
Save file to disk
        │
        ▼
User clicks "Build"
        │
        ▼
Language Detector → Identifies file type
        │
        ▼
Build Service → Loads language config
        │
        ▼
Compiler Service → Executes compiler
        │
        ▼
Error Parser → Parses compiler output
        │
        ▼
Display errors/warnings in IDE
        │
        ▼
On success: Generate executable
```

### Code Execution Flow
```
User clicks "Run"
        │
        ▼
Check if compiled (for compiled langs)
        │
        ▼
Interpreter/Executor Service
        │
        ├─→ (Compiled) Execute binary
        │
        └─→ (Interpreted) Run interpreter
        │
        ▼
Capture stdout/stderr
        │
        ▼
Display in Terminal/Output Panel
```

### Project Creation Flow
```
User selects "New Project"
        │
        ▼
Language Selection Dialog
        │
        ▼
Project Template Selection
        │
        ▼
Project Configuration
        │
        ▼
Create directory structure
        │
        ▼
Generate template files
        │
        ▼
Project ready for editing
```

## Database Schema

### Projects Table
```sql
CREATE TABLE projects (
    id INTEGER PRIMARY KEY,
    name TEXT NOT NULL,
    language TEXT NOT NULL,
    path TEXT UNIQUE NOT NULL,
    created_at TIMESTAMP,
    last_modified TIMESTAMP,
    description TEXT
);
```

### Files Table
```sql
CREATE TABLE files (
    id INTEGER PRIMARY KEY,
    project_id INTEGER,
    filename TEXT NOT NULL,
    path TEXT NOT NULL,
    content TEXT,
    last_modified TIMESTAMP,
    FOREIGN KEY(project_id) REFERENCES projects(id)
);
```

### Build Configuration Table
```sql
CREATE TABLE build_configs (
    id INTEGER PRIMARY KEY,
    project_id INTEGER,
    language TEXT NOT NULL,
    compiler TEXT,
    compiler_flags TEXT,
    linker_flags TEXT,
    FOREIGN KEY(project_id) REFERENCES projects(id)
);
```

## File Structure

```
app/
├── src/main/java/com/termux/ide/
│   ├── ui/
│   │   ├── activities/
│   │   │   ├── EditorActivity.java
│   │   │   ├── TerminalActivity.java
│   │   │   ├── ExplorerActivity.java
│   │   │   └── MainActivity.java
│   │   ├── views/
│   │   │   ├── SyntaxHighlightedEditText.java
│   │   │   ├── TerminalView.java
│   │   │   └── FileTreeView.java
│   │   └── fragments/
│   │       ├── EditorFragment.java
│   │       ├── TerminalFragment.java
│   │       └── OutputFragment.java
│   ├── services/
│   │   ├── CompilerService.java
│   │   ├── InterpreterService.java
│   │   ├── BuildService.java
│   │   ├── DebugService.java
│   │   └── ProjectService.java
│   ├── languages/
│   │   ├── LanguageInterface.java
│   │   ├── PythonLanguage.java
│   │   ├── CppLanguage.java
│   │   ├── CLanguage.java
│   │   ├── WebLanguages.java
│   │   └── JavaLanguage.java
│   ├── utils/
│   │   ├── FileUtils.java
│   │   ├── ProcessExecutor.java
│   │   ├── SyntaxHighlighter.java
│   │   └── LanguageDetector.java
│   ├── models/
│   │   ├── Project.java
│   │   ├── CompileError.java
│   │   ├── Language.java
│   │   └── BuildConfig.java
│   └── termux/
│       ├── TermuxSession.java
│       ├── TermuxShell.java
│       └── PackageManager.java
├── src/main/res/
│   ├── layout/
│   ├── values/
│   ├── drawable/
│   └── menu/
└── build.gradle
```

## Integration Points

### Termux Integration
- Shell execution through Termux terminal session
- Package management (apt, pkg)
- Access to system binaries (GCC, Clang, Python, etc.)
- File system access

### External Tools
- **GCC/G++** - C/C++ compilation
- **Clang** - Alternative compiler
- **GDB** - Debugging
- **Python 3.x** - Python interpreter
- **Node.js** - JavaScript runtime
- **Git** - Version control

## Communication Protocols

### Inter-Component Communication
- **Event Bus**: For loose coupling between components
- **Callbacks**: For asynchronous operations
- **Shared Preferences**: For configuration storage
- **SQLite Database**: For persistent data

### External Communication
- **Process Execution**: Spawn compiler/interpreter processes
- **Pipe Communication**: Capture stdout/stderr
- **Signal Handling**: Monitor process completion

---

**Last Updated**: 2026-09-10
**Status**: 🚧 Design Phase
