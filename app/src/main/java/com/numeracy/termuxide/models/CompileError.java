package com.numeracy.termuxide.models;

public class CompileError {
    private String file;
    private int line;
    private int column;
    private String message;
    private ErrorType type;

    public enum ErrorType {
        ERROR, WARNING, INFO
    }

    public CompileError(String file, int line, int column, String message, ErrorType type) {
        this.file = file;
        this.line = line;
        this.column = column;
        this.message = message;
        this.type = type;
    }

    // Getters
    public String getFile() { return file; }
    public int getLine() { return line; }
    public int getColumn() { return column; }
    public String getMessage() { return message; }
    public ErrorType getType() { return type; }
}
