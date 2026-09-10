package com.numeracy.termuxide.ui.activities;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.numeracy.termuxide.R;
import com.numeracy.termuxide.models.ExecutionResult;
import com.numeracy.termuxide.utils.ProcessExecutor;
import java.util.concurrent.Executors;

public class TerminalActivity extends AppCompatActivity {
    private TextView output;
    private EditText command;
    @Override protected void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_terminal);
        output = findViewById(R.id.terminal_output);
        command = findViewById(R.id.terminal_command);
        findViewById(R.id.run_command).setOnClickListener(v -> runCommand());
    }
    private void runCommand() {
        String value = command.getText().toString().trim();
        if (value.isEmpty()) return;
        output.append("$ " + value + "\n");
        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                ExecutionResult result = new ProcessExecutor(this).execute(value);
                runOnUiThread(() -> output.append(result.getOutput() + result.getError()));
            } catch (Exception e) { runOnUiThread(() -> output.append(e.getMessage() + "\n")); }
        });
    }
}
