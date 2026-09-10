# Contributing to Termux Improved

Thank you for your interest in contributing to Termux Improved! This document provides guidelines and instructions for contributing.

## Getting Started

1. **Fork the repository** on GitHub
2. **Clone your fork** locally
3. **Create a new branch** for your feature or bugfix
4. **Make your changes**
5. **Test thoroughly**
6. **Submit a pull request**

## Development Setup

### Prerequisites
- Git
- Android SDK (for building)
- Java Development Kit (JDK)
- Maven or Gradle

### Setting Up Your Environment

```bash
git clone https://github.com/your-username/termux-improved.git
cd termux-improved
# Follow build instructions in BUILD.md
```

## Making Changes

### Code Style

- Follow Android/Java coding conventions
- Use meaningful variable and function names
- Add comments for complex logic
- Keep functions focused and modular

### Commit Messages

Write clear, descriptive commit messages:
```
[CATEGORY] Brief description

More detailed explanation if needed.
- List specific changes
- Reference related issues (#123)
```

Categories:
- `[FEATURE]` - New functionality
- `[FIX]` - Bug fixes
- `[PERF]` - Performance improvements
- `[DOCS]` - Documentation updates
- `[REFACTOR]` - Code refactoring

### Testing

- Test your changes thoroughly
- Include unit tests for new features
- Test on multiple Android versions when possible
- Verify no regressions in existing functionality

## Submitting Pull Requests

1. **Update** your local repository with the latest changes from `main`
2. **Push** your branch to your fork
3. **Create a Pull Request** with:
   - Clear title describing the change
   - Detailed description of what and why
   - Reference to related issues (if any)
   - Screenshots/videos for UI changes

4. **Respond to feedback** from reviewers
5. **Keep commits clean** - rebase if needed

## Pull Request Guidelines

- One feature or fix per PR when possible
- Keep PRs focused and manageable
- Include tests and documentation updates
- Ensure CI/CD checks pass
- Be respectful and professional in discussions

## Reporting Issues

When reporting bugs, please include:
- Device and Android version
- Termux version
- Steps to reproduce
- Expected vs actual behavior
- Error logs/stack traces if available

## Code of Conduct

- Be respectful and inclusive
- Provide constructive feedback
- Help others learn and grow
- Report inappropriate behavior

## Questions?

- Check existing issues and discussions
- Review the documentation
- Ask in PR comments or open an issue

Thank you for contributing! 🎉
