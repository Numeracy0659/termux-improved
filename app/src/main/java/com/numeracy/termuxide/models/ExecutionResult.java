package com.numeracy.termuxide.models;

public class ExecutionResult {
    private String output;
    private String error;
    private int exitCode;
    private long executionTime;
    private boolean success;

    public ExecutionResult(String output, String error, int exitCode, long executionTime) {
        this.output = output;
        this.error = error;
        this.exitCode = exitCode;
        this.executionTime = executionTime;
        this.success = exitCode == 0;
    }

    // Getters
    public String getOutput() { return output; }
    public String getError() { return error; }
    public int getExitCode() { return exitCode; }
    public long getExecutionTime() { return executionTime; }
    public boolean isSuccess() { return success; }

    @Override
    public String toString() {
        return "ExecutionResult{" +
                "output='" + output + '\'' +
                ", error='" + error + '\'' +
                ", exitCode=" + exitCode +
                ", executionTime=" + executionTime +
                ", success=" + success +
                '}';
    }
}
