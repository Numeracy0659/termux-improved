package com.numeracy.termuxide.termux;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import com.numeracy.termuxide.utils.ProcessExecutor;

public class TermuxIntegrationService {
    private static final String TAG = "TermuxIntegration";
    private Context context;
    private ProcessExecutor executor;

    public TermuxIntegrationService(Context context) {
        this.context = context;
        this.executor = new ProcessExecutor(context);
    }

    public boolean isTermuxInstalled() {
        try {
            // Check if we're running in Termux environment
            return System.getenv("TERMUX_APP_PID") != null;
        } catch (Exception e) {
            Log.e(TAG, "Error checking Termux installation", e);
            return false;
        }
    }

    public String getTermuxVersion() {
        try {
            return System.getenv("TERMUX_VERSION");
        } catch (Exception e) {
            return "unknown";
        }
    }

    public void fixTermuxPermissions() {
        try {
            executor.execute("termux-fix-permissions");
        } catch (Exception e) {
            Log.e(TAG, "Error fixing Termux permissions", e);
        }
    }

    public void installPackage(String packageName) {
        try {
            executor.execute("apt-get update && apt-get install -y " + packageName);
        } catch (Exception e) {
            Log.e(TAG, "Error installing package: " + packageName, e);
        }
    }
}
