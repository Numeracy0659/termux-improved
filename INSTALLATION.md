# Termux Improved IDE - Installation & Setup Guide

## System Requirements

### Minimum Requirements
- **Android Version**: Android 7.0 (API 24) or higher
- **Device RAM**: 2GB minimum (4GB+ recommended)
- **Storage**: 2GB free space minimum
- **Processor**: ARM, ARM64, x86, or x86_64

### Recommended Specifications
- **Android Version**: Android 10+ (API 29+)
- **Device RAM**: 4GB or more
- **Storage**: 5GB+ free space
- **Processor**: ARM64 or x86_64
- **Screen Size**: 5.5" or larger (for comfortable editing)

## Development Environment Setup

### Prerequisites
Before building Termux Improved IDE from source, ensure you have:

1. **Git** - For cloning the repository
2. **Android SDK** - API level 33+ recommended
3. **Android NDK** - For native component building
4. **Java Development Kit (JDK)** - Version 11 or higher
5. **Gradle** - Build system (included in Android Studio)

### Desktop Setup (For Development)

#### Step 1: Install Android Studio
```bash
# Download from https://developer.android.com/studio

# On Linux
chmod +x android-studio-*.bin
./android-studio-*.bin

# On macOS
# Use installer or: brew install android-studio

# On Windows
# Run installer executable
```

#### Step 2: Configure Android SDK
```bash
# In Android Studio: Tools > SDK Manager
# Install:
# - Android API 33 or higher
# - Android NDK (side by side)
# - CMake
# - Emulator

export ANDROID_HOME=$HOME/Android/Sdk
export PATH=$PATH:$ANDROID_HOME/cmdline-tools/latest/bin
export PATH=$PATH:$ANDROID_HOME/ndk/latest/bin
```

#### Step 3: Install JDK
```bash
# Ubuntu/Debian
sudo apt update
sudo apt install openjdk-11-jdk

# macOS
brew install openjdk@11

# Verify installation
java -version
javac -version
```

#### Step 4: Clone Repository
```bash
git clone https://github.com/Numeracy0659/termux-improved.git
cd termux-improved

# Create local.properties
cat > local.properties << EOF
sdk.dir=$ANDROID_HOME
ndk.dir=$ANDROID_HOME/ndk/25.0.8775105
cmake.dir=$ANDROID_HOME/cmake/3.22.1
EOF
```

### Step 5: Build the Project
```bash
# Using Gradle wrapper (recommended)
./gradlew assembleDebug

# Or with Gradle directly
gradle assembleDebug
```

## On-Device Setup (For End Users)

### Installation via APK
1. **Download the APK**
   - Download from GitHub Releases page
   - Or build from source (see above)

2. **Enable Unknown Sources**
   - Settings > Security > Unknown Sources > Enable
   - OR Settings > Apps & notifications > Special app access > Install unknown apps

3. **Install APK**
   ```bash
   # Via command line
   adb install termux-improved-debug.apk
   
   # Or manually: Tap the APK file and select "Install"
   ```

4. **Grant Permissions**
   - Storage access
   - Terminal access (if using Termux integration)
   - File system permissions

### First Launch Setup

#### Initialize Environment
```bash
# On first launch, the IDE will:
# 1. Create project directories
# 2. Initialize Termux integration
# 3. Install necessary tools
# 4. Create sample project

# This may take 1-2 minutes
```

#### Install Language Runtimes
```bash
# Python
pkg install python3
pip install --upgrade pip

# C/C++ Compilers
pkg install gcc g++ gdb make cmake

# Java
pkg install openjdk-17

# Node.js
pkg install nodejs npm

# Git
pkg install git
```

#### Set Up First Project
1. **Launch IDE**
   - Open "Termux Improved IDE"

2. **Create New Project**
   - File > New Project
   - Select Language (Python, C++, Java, etc.)
   - Choose Project Template
   - Enter Project Name
   - Select Project Location

3. **Project Structure Created**
   ```
   MyProject/
   ├── src/
   │   └── main.py (or main.cpp, Main.java, etc.)
   ├── build/
   ├── .gitignore
   ├── README.md
   └── build.gradle (or Makefile)
   ```

## Installation Troubleshooting

### Issue: "Installation failed: Insufficient storage"
**Solution:**
- Free up at least 2GB of storage
- Clear app cache: Settings > Apps > Termux Improved > Storage > Clear Cache
- Move to external storage if available

### Issue: "Android SDK not found"
**Solution:**
```bash
# Set ANDROID_HOME correctly
export ANDROID_HOME=/path/to/android/sdk

# Verify SDK exists
ls $ANDROID_HOME/platforms
```

### Issue: "Gradle sync failed"
**Solution:**
```bash
# Clear Gradle cache
./gradlew clean

# Update dependencies
./gradlew --refresh-dependencies

# Rebuild
./gradlew assembleDebug
```

### Issue: "NDK not found"
**Solution:**
```bash
# Install NDK via Android Studio
# Tools > SDK Manager > SDK Tools > NDK (check and install)

# Or manually set in local.properties
ndk.dir=/path/to/ndk/25.0.8775105
```

### Issue: "JDK 11 not found"
**Solution:**
```bash
# Install Java 11
# Ubuntu/Debian: sudo apt install openjdk-11-jdk
# macOS: brew install openjdk@11

# Set JAVA_HOME
export JAVA_HOME=/path/to/jdk11
```

### Issue: "App crashes on startup"
**Solution:**
1. Check logcat output:
   ```bash
   adb logcat | grep "Termux"
   ```
2. Clear app data:
   - Settings > Apps > Termux Improved > Storage > Clear Data
3. Reinstall application

## Post-Installation Configuration

### Configure Editor Preferences
1. **Open Settings**
   - Menu > Preferences or Settings

2. **Editor Settings**
   - Font: Select monospace font
   - Font Size: 12-14px (adjust for screen size)
   - Tab Size: 4 spaces (or your preference)
   - Auto-indentation: Enable
   - Line wrapping: Enable/Disable per preference

3. **Theme Selection**
   - Dark Mode (default)
   - Light Mode
   - Custom themes

### Configure Build Settings
1. **Compiler Settings**
   - Select compiler (GCC, Clang)
   - Set optimization flags (-O2, -O3)
   - Enable debug symbols (-g)

2. **Build Options**
   - Output directory: `./build`
   - Parallel builds: Enable (for faster compilation)
   - Build timeout: 5 minutes (default)

### Configure Language Runtimes
1. **Python**
   ```bash
   # Set Python path
   which python3
   
   # Configure virtual environments
   python3 -m venv ~/venv
   ```

2. **C/C++**
   ```bash
   # Verify compiler
   gcc --version
   g++ --version
   gdb --version
   ```

3. **Java**
   ```bash
   # Set Java path
   which java
   java -version
   ```

## Verification Steps

### Verify Installation
```bash
# Check if all components installed correctly
termux-fix-permissions

# Verify Python
python3 -c "print('Python installed')"

# Verify C compiler
gcc --version

# Verify Java
java -version

# Verify Node.js
node --version
npm --version
```

### Create Test Project
1. **Create Simple Python Project**
   ```python
   # test.py
   print("Hello from Termux Improved IDE!")
   ```

2. **Run Program**
   - Open file in IDE
   - Click "Run"
   - Verify output in terminal

## Performance Optimization

### Memory Management
```bash
# Configure Gradle heap size (in gradle.properties)
org.gradle.jvmargs=-Xmx2048m -XX:MaxPermSize=512m
```

### Build Optimization
```bash
# Enable parallel builds
org.gradle.parallel=true
org.gradle.workers.max=4

# Enable build cache
org.gradle.build.cache=true
```

### IDE Optimization
1. **Disable unused plugins** in Settings
2. **Increase IDE heap memory** if sluggish
3. **Use lightweight theme** on low-end devices
4. **Close unused editor tabs**

## Uninstallation

### Remove from Device
```bash
# Via command line
adb uninstall com.termux.ide

# Or manually
Settings > Apps > Termux Improved > Uninstall
```

### Clean Development Environment
```bash
# Remove build artifacts
./gradlew clean

# Remove Gradle cache
rm -rf ~/.gradle

# Remove SDK (if not needed for other projects)
rm -rf ~/Android/Sdk
```

## Getting Help

### Documentation
- GitHub Wiki: https://github.com/Numeracy0659/termux-improved/wiki
- API Documentation: See docs/ directory
- Build Guide: See BUILD.md

### Community Support
- GitHub Issues: https://github.com/Numeracy0659/termux-improved/issues
- Discussions: https://github.com/Numeracy0659/termux-improved/discussions
- Stack Overflow: Tag with `termux-improved`

### Development Support
- Contributing Guide: See CONTRIBUTING.md
- Architecture Details: See docs/ARCHITECTURE.md
- Language Support: See docs/LANGUAGES.md

## Next Steps

1. **Create Your First Project**
   - Choose your preferred programming language
   - Create a new project in the IDE
   - Start coding!

2. **Explore IDE Features**
   - Try syntax highlighting
   - Use code completion
   - Test debugging capabilities
   - Run and compile code

3. **Join the Community**
   - Star the repository
   - Report issues and suggestions
   - Contribute improvements
   - Share your projects

---

**Last Updated**: 2026-09-10
**Version**: Installation Guide v1.0
**Status**: ✅ Complete
