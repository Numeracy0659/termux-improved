package com.numeracy.termuxide.utils;

import android.content.Context;
import com.numeracy.termuxide.models.ExecutionResult;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

/** Executes Termux commands and captures both output streams without leaking processes. */
public class ProcessExecutor {
    private static final long DEFAULT_TIMEOUT_SECONDS = 60;
    private final Context context;

    public ProcessExecutor(Context context) {
        this.context = context.getApplicationContext();
    }

    public ExecutionResult execute(String command) throws IOException, InterruptedException {
        return execute(command, DEFAULT_TIMEOUT_SECONDS);
    }

    public ExecutionResult execute(String command, long timeoutSeconds)
            throws IOException, InterruptedException {
        long started = System.currentTimeMillis();
        Process process = new ProcessBuilder("/system/bin/sh", "-c", command)
                .redirectErrorStream(false)
                .start();
        StringBuilder output = new StringBuilder();
        StringBuilder error = new StringBuilder();
        Thread stdout = stream(process.getInputStream(), output);
        Thread stderr = stream(process.getErrorStream(), error);
        boolean finished = process.waitFor(timeoutSeconds, TimeUnit.SECONDS);
        if (!finished) {
            process.destroyForcibly();
            stdout.join(1000);
            stderr.join(1000);
            return new ExecutionResult(output.toString(), "Process timed out after " + timeoutSeconds + " seconds", 124,
                    System.currentTimeMillis() - started);
        }
        stdout.join(1000);
        stderr.join(1000);
        return new ExecutionResult(output.toString(), error.toString(), process.exitValue(),
                System.currentTimeMillis() - started);
    }

    private Thread stream(java.io.InputStream input, StringBuilder target) {
        Thread thread = new Thread(() -> {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
                String line;
                while ((line = reader.readLine()) != null) target.append(line).append('\n');
            } catch (IOException ignored) { }
        });
        thread.start();
        return thread;
    }
}
