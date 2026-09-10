package com.numeracy.termuxide.termux;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.numeracy.termuxide.utils.ProcessExecutor;

public class TermuxIntegrationService extends Service {
    private static final String TAG = "TermuxIntegration";
    private Context context;
    private ProcessExecutor executor;

    @Override
    public void onCreate() {
        super.onCreate();
        this.context = getApplicationContext();
        this.executor = new ProcessExecutor(this);
    }

    @Override public IBinder onBind(Intent intent) { return null; }

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
        if (packageName == null || !packageName.matches("[a-zA-Z0-9+._-]+")) {
            Log.e(TAG, "Invalid package name");
            return;
        }
        try {
            executor.execute("apt-get update && apt-get install -y " + packageName);
        } catch (Exception e) {
            Log.e(TAG, "Error installing package: " + packageName, e);
        }
    }
}
