package com.example.vincent.lab7;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private EditText new_psw;
    private EditText confirm_psw;
    private Button ok_btn;
    private Button clear_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        new_psw = (EditText) findViewById(R.id.new_psw);
        confirm_psw = (EditText) findViewById(R.id.confirm_psw);
        ok_btn = (Button) findViewById(R.id.ok_btn);
        clear_btn = (Button) findViewById(R.id.clear_btn);
        Boolean isRemember = preferences.getBoolean("Remember", false);
        if (isRemember) {
            new_psw.setVisibility(View.GONE);
            confirm_psw.setHint("Password");
            ok_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String new_psw1 = preferences.getString("new_pwd", "");
                    String confirm_psw_1 = confirm_psw.getText().toString();
                    if (confirm_psw_1.isEmpty()) {
                        Toast.makeText(MainActivity.this, "Password can not be empty.", Toast.LENGTH_SHORT).show();
                    } else if (!confirm_psw_1.equals(new_psw1)) {
                        Toast.makeText(MainActivity.this, "Invalid Password.", Toast.LENGTH_SHORT).show();
                    } else {
                        Intent intent = new Intent(MainActivity.this, EditActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            });
            clear_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new_psw.setText("");
                    confirm_psw.setText("");
                }
            });
        }
        else {
            ok_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String new_psw1 = new_psw.getText().toString();
                    String confirm_psw_1 = confirm_psw.getText().toString();
                    if (new_psw1.isEmpty() || confirm_psw_1.isEmpty()) {
                        Toast.makeText(MainActivity.this, "Password can not be empty.", Toast.LENGTH_SHORT).show();
                    } else if (!new_psw1.equals(confirm_psw_1)) {
                        Toast.makeText(MainActivity.this, "Password Mismatch.", Toast.LENGTH_SHORT).show();
                    } else {
                        editor = preferences.edit();
                        editor.putString("new_pwd", new_psw1);
                        editor.putString("confirm_pws", confirm_psw_1);
                        editor.putBoolean("Remember", true);
                        editor.commit();
                        Intent intent = new Intent(MainActivity.this, EditActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            });
            clear_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new_psw.setText("");
                    confirm_psw.setText("");
                }
            });
        }
    }
}
