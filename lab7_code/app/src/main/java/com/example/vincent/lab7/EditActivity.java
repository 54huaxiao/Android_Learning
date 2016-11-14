package com.example.vincent.lab7;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by Vincent on 2016/11/10.
 */

public class EditActivity extends Activity {
    private Button save_btn;
    private Button load_btn;
    private Button clear_btn;
    private EditText editText;
    @Override
    protected void onCreate(Bundle onSavedInstanceState) {
        super.onCreate(onSavedInstanceState);
        setContentView(R.layout.edit_layout);
        save_btn = (Button) findViewById(R.id.save_btn);
        load_btn = (Button) findViewById(R.id.load_btn);
        clear_btn = (Button) findViewById(R.id.clear_btn_2);
        editText = (EditText) findViewById(R.id.edit_text);
        clear_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
            }
        });
        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = editText.getText().toString();
                FileOutputStream out = null;
                BufferedWriter writer = null;
                try {
                    out = openFileOutput("data", Context.MODE_PRIVATE);
                    writer = new BufferedWriter(new OutputStreamWriter(out));
                    writer.write(data);
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (writer != null) {
                            writer.close();
                            Toast.makeText(EditActivity.this, "Save successfully.", Toast.LENGTH_SHORT).show();
                        }
                    }catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        load_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileInputStream in = null;
                BufferedReader reader = null;
                StringBuilder builder = new StringBuilder();
                try {
                    in = openFileInput("data");
                    reader = new BufferedReader(new InputStreamReader(in));
                    String line = "";
                    while ((line = reader.readLine()) != null) {
                        builder.append(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (reader != null) {
                            reader.close();
                            Toast.makeText(EditActivity.this, "Load successfully.", Toast.LENGTH_SHORT).show();
                        }
                    }catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(EditActivity.this, "Fail to load file.", Toast.LENGTH_SHORT).show();
                    }
                }
                editText.setText(builder.toString());
            }
        });
    }
}
