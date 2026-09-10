package com.numeracy.termuxide.languages;

import android.content.Context;
import com.numeracy.termuxide.models.ExecutionResult;
import com.numeracy.termuxide.utils.ProcessExecutor;

public class PythonLanguage extends Language {
    private Context context;
    private ProcessExecutor executor;

    public PythonLanguage(Context context) {
        this.context = context;
        this.executor = new ProcessExecutor(context);
        this.name = "Python";
        this.fileExtensions = new String[]{".py"};
        this.isCompiled = false;
    }

    @Override
    public String getName() {
        return "Python";
    }

    @Override
    public String getVersion() {
        return "3.x";
    }

    @Override
    public String[] getFileExtensions() {
        return new String[]{".py", ".pyw"};
    }

    @Override
    public boolean isCompiled() {
        return false;
    }

    @Override
    public ExecutionResult execute(String sourceFile) {
        long startTime = System.currentTimeMillis();
        try {
            String command = "python3 " + sourceFile;
            ExecutionResult result = executor.execute(command);
            long executionTime = System.currentTimeMillis() - startTime;
            return new ExecutionResult(result.getOutput(), result.getError(), result.getExitCode(), executionTime);
        } catch (Exception e) {
            return new ExecutionResult("", "Error: " + e.getMessage(), 1, System.currentTimeMillis() - startTime);
        }
    }

    @Override
    public ExecutionResult compile(String sourceFile) {
        // Python doesn't need compilation
        return new ExecutionResult("", "Python is interpreted", 0, 0);
    }

    @Override
    public boolean isInstalled() {
        try {
            ExecutionResult result = executor.execute("python3 --version");
            return result.isSuccess();
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String getSyntaxHighlightingRules() {
        return "python";
    }
}
