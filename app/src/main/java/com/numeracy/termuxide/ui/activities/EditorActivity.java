package com.numeracy.termuxide.ui.activities;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.numeracy.termuxide.R;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class EditorActivity extends AppCompatActivity {
    private EditText editor;
    @Override protected void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_editor);
        editor = findViewById(R.id.editor_text);
        findViewById(R.id.save_file).setOnClickListener(v -> saveFile());
    }
    private void saveFile() {
        String path = getIntent().getStringExtra("path");
        if (path == null) { Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show(); return; }
        try {
            Files.write(new File(path).toPath(), editor.getText().toString().getBytes(StandardCharsets.UTF_8));
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
        } catch (Exception e) { Toast.makeText(this, "Save failed: " + e.getMessage(), Toast.LENGTH_LONG).show(); }
    }
}
