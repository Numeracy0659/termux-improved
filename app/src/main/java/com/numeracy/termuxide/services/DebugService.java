package com.numeracy.termuxide.services;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
public class DebugService extends Service {
    @Override public IBinder onBind(Intent intent) { return null; }
}
