package com.example.vincent.lab8;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Vincent on 2016/11/16.
 */

public class AddActivity extends Activity{
    private MyDB dbHelper;
    private Button add;
    private EditText name_edit;
    private EditText birthday_edit;
    private EditText gift_edit;
    private boolean flag = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_info);
        dbHelper = new MyDB(this, "myDatabase.db", null, 1);
        add = (Button) findViewById(R.id.add);
        name_edit = (EditText) findViewById(R.id.name_edit);
        birthday_edit = (EditText) findViewById(R.id.birthday_edit);
        gift_edit = (EditText) findViewById(R.id.gift_edit);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name_edit.getText().toString().isEmpty()) {
                    Toast.makeText(AddActivity.this, "名字不能为空", Toast.LENGTH_SHORT).show();
                } else if (birthday_edit.getText().toString().isEmpty()) {
                    Toast.makeText(AddActivity.this, "生日不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    Cursor cursor = db.query("Item", null, null, null, null, null, null);
                    if (cursor.moveToFirst()) {
                        do {
                            String name = cursor.getString(cursor.getColumnIndex("name"));
                            if (name.equals(name_edit.getText().toString())) {
                                flag = false;
                            }
                        } while (cursor.moveToNext());
                    }
                    cursor.close();
                    if (flag) {
                        values.put("name", name_edit.getText().toString());
                        values.put("birthday", birthday_edit.getText().toString());
                        values.put("gift", gift_edit.getText().toString());
                        db.insert("Item", null, values);
                    } else {
                        Toast.makeText(AddActivity.this, "名字重复了，请核查", Toast.LENGTH_SHORT).show();
                    }
                    Intent intent = new Intent(AddActivity.this, MainActivity.class);
                    startActivity(intent);
                    //values.clear();
                }
            }
        });
    }
}
