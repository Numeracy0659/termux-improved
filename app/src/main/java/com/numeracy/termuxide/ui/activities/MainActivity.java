package com.numeracy.termuxide.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.numeracy.termuxide.R;

public class MainActivity extends AppCompatActivity {
    @Override protected void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_main);
        Button editor = findViewById(R.id.open_editor);
        Button terminal = findViewById(R.id.open_terminal);
        Button explorer = findViewById(R.id.open_explorer);
        editor.setOnClickListener(v -> startActivity(new Intent(this, EditorActivity.class)));
        terminal.setOnClickListener(v -> startActivity(new Intent(this, TerminalActivity.class)));
        explorer.setOnClickListener(v -> startActivity(new Intent(this, ExplorerActivity.class)));
    }
}
