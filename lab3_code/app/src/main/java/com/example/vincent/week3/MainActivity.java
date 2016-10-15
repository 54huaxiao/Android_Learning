package com.example.vincent.week3;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vincent on 2016/10/11.
 */

public class MainActivity extends Activity {
    private List<Contact> contactList = new ArrayList<Contact>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_list);
        initContact();
        final ContactAdapter adapter = new ContactAdapter(MainActivity.this, contactList);
        final ListView listview = (ListView) findViewById(R.id.contact_list);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contact contact = contactList.get(position);
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, ContactMessage.class);
                intent.putExtra("name", contact.getName());
                intent.putExtra("phone", contact.getPhone());
                intent.putExtra("phone_add", contact.getPhone_add());
                intent.putExtra("type", contact.getType());
                intent.putExtra("bg_color", contact.getBg_color());
                startActivity(intent);
            }
        });
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final Contact contact = contactList.get(position);
                final AlertDialog.Builder logon_dialog = new AlertDialog.Builder(MainActivity.this);
                logon_dialog.setTitle("删除联系人").setMessage("确定删除联系人" + contact.getName()).setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        contactList.remove(contact);
                        adapter.notifyDataSetChanged();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).setCancelable(false).create().show();
                return true;
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
    }

    private void initContact() {
    Contact Aaron = new Contact("Aaron", "17715523654", "江苏苏州电信", "手机", "bb4c3b");
    contactList.add(Aaron);
    Contact Elvis = new Contact("Elvis", "18825653224", "广东揭阳移动", "手机", "c48d30");
    contactList.add(Elvis);
    Contact David = new Contact("David", "15052116654", "江苏无锡移动", "手机", "4469b0");
    contactList.add(David);
    Contact Edwin = new Contact("Edwin", "18854211875", "山东青岛移动", "手机", "20a17b");
    contactList.add(Edwin);
    Contact Frank = new Contact("Frank", "13955188541", "安徽合肥移动", "手机", "bb4c3b");
    contactList.add(Frank);
    Contact Joshua = new Contact("Joshua", "13621574410", "江苏苏州移动", "手机", "c48d30");
    contactList.add(Joshua);
    Contact Ivan = new Contact("Ivan", "15684122771", "山东烟台联通", "手机", "4469b0");
    contactList.add(Ivan);
    Contact Mark= new Contact("Mark", "17765213579", "广东珠海电信", "手机", "20a17b");
    contactList.add(Mark);
    Contact Joseph = new Contact("Joseph", "13955188541", "河北石家庄电信", "手机", "bb4c3b");
    contactList.add(Joseph);
    Contact Phoebe = new Contact("Phoebe", "17895466428", "山东东营移动", "手机", "c48d30");
    contactList.add(Phoebe);
    }
}
