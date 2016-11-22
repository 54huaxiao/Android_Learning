package com.example.vincent.lab8;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Button add_item;
    private ListView list_view;
    private MyDB dbHelper;
    private TextView name_dialog;
    private EditText birthday_dialog;
    private EditText gift_dialog;
    private TextView phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final List<Map<String, Object>> data = new ArrayList<>();
        add_item = (Button) findViewById(R.id.add_item);
        list_view = (ListView) findViewById(R.id.list_view);
        dbHelper = new MyDB(this, "myDatabase.db", null, 1);
        add_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        final Cursor cursor = db.query("Item", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String birthday = cursor.getString(cursor.getColumnIndex("birthday"));
                String gift = cursor.getString(cursor.getColumnIndex("gift"));
                Map<String, Object> temp = new LinkedHashMap<>();
                temp.put("name", name);
                temp.put("birthday", birthday);
                temp.put("gift", gift);
                data.add(temp);
            } while (cursor.moveToNext());
        }
        cursor.close();
        final SimpleAdapter simpleAdapter = new SimpleAdapter(this, data, R.layout.item,
                new String[] {"name", "birthday", "gift"}, new int[] {R.id.name_item, R.id.birthday_item, R.id.gift_item});
        list_view.setAdapter(simpleAdapter);
        list_view.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final Map<String, Object> item = data.get(position);
                final AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setMessage("是否删除").setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        data.remove(item);
                        simpleAdapter.notifyDataSetChanged();
                        db.delete("Item", "name = ?", new String[] {item.get("name").toString()});
                    }
                }).setNegativeButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setCancelable(false).create().show();
                return true;
            }
        });
        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int flag = 0;
                final Map<String, Object> item = data.get(position);
                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.dialoglayout, (ViewGroup) findViewById(R.id.dialog));
                final AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                name_dialog = (TextView) layout.findViewById(R.id.name_dialog);
                birthday_dialog = (EditText) layout.findViewById(R.id.birthday_dialog);
                gift_dialog = (EditText) layout.findViewById(R.id.gift_dialog);
                name_dialog.setText(item.get("name").toString());
                birthday_dialog.setText(item.get("birthday").toString());
                gift_dialog.setText(item.get("gift").toString());
                phone = (TextView) layout.findViewById(R.id.phone);
                String phone_num = "";
                Cursor contact = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
                if (contact.moveToFirst()) {
                    int idColumn = contact.getColumnIndex(ContactsContract.Contacts._ID);
                    int displayNameColumn = contact.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
                    do {
                        String contactId = contact.getString(idColumn);
                        String displayName = contact.getString(displayNameColumn);
                        if (displayName.equals(item.get("name").toString())) {
                            flag = 1;
                            int phoneCount = contact.getInt(contact.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
                            if (phoneCount > 0) {
                                // 获得联系人的电话号码列表
                                Cursor phoneCursor = getContentResolver().query(
                                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                        null,
                                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID
                                                + "=" + contactId, null, null);
                                if (phoneCursor.moveToFirst()) {
                                    do {
                                        //遍历所有的联系人下面所有的电话号码
                                        String phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                                        //使用Toast技术显示获得的号码
                                        phone_num += phoneNumber + " ";
                                    } while (phoneCursor.moveToNext());
                                }
                                phone.setText(phone_num);
                            } else {
                                phone.setText("无");
                            }
                        }
                    } while (contact.moveToNext());
                    if (flag == 0) phone.setText("无");
                }
                dialog.setTitle("O(∩_∩)O~~").setView(layout).setPositiveButton("保存修改", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ContentValues values = new ContentValues();
                        values.put("birthday", birthday_dialog.getText().toString());
                        values.put("gift", gift_dialog.getText().toString());
                        db.update("Item", values, "name = ?", new String[] {item.get("name").toString()});
                        Map<String, Object> temp = new LinkedHashMap<>();
                        temp.put("name", item.get("name").toString());
                        temp.put("birthday", birthday_dialog.getText().toString());
                        temp.put("gift", gift_dialog.getText().toString());
                        data.remove(item);
                        data.add(temp);
                        simpleAdapter.notifyDataSetChanged();
                    }
                }).setNegativeButton("放弃修改", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).setCancelable(false).show();
            }
        });
    }
}
