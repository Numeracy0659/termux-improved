package com.numeracy.termuxide.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.numeracy.termuxide.R;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.io.File;
import java.util.Arrays;

public class ExplorerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explorer);
        ListView files = findViewById(R.id.file_list);
        File root = getExternalFilesDir(null) != null ? getExternalFilesDir(null) : getFilesDir();
        File[] entries = root.listFiles();
        String[] names = entries == null ? new String[]{"(empty)"} : Arrays.stream(entries).map(File::getName).toArray(String[]::new);
        files.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, names));
    }
}
