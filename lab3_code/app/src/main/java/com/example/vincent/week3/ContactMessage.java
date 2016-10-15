package com.example.vincent.week3;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;


/**
 * Created by Vincent on 2016/10/11.
 */

public class ContactMessage extends Activity {
    private Boolean flag = false;
    private String[] data = {"编辑联系人", "分享联系人", "加入黑名单", "删除联系人"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_message);
        ImageView back_btn = (ImageView) findViewById(R.id.back_btn);
        final ImageView star_btn = (ImageView) findViewById(R.id.empty_star);
        TextView name = (TextView) findViewById(R.id.name);
        RelativeLayout top_layout = (RelativeLayout) findViewById(R.id.top_layout);
        top_layout.setBackgroundColor(Color.parseColor("#"+getIntent().getStringExtra("bg_color")));
        TextView phone = (TextView) findViewById(R.id.phone);
        TextView phone_add = (TextView) findViewById(R.id.phone_add);
        phone.setText(getIntent().getStringExtra("phone"));
        phone_add.setText("手机 "+getIntent().getStringExtra("phone_add"));
        name.setText(getIntent().getStringExtra("name"));
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactMessage.this, MainActivity.class);
                startActivity(intent);
            }
        });
        star_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (star_btn == v && flag == false) {
                    star_btn.setImageDrawable(getResources().getDrawable(R.mipmap.full_star));
                    flag = !flag;
                } else {
                    star_btn.setImageDrawable(getResources().getDrawable(R.mipmap.empty_star));
                    flag = !flag;
                }
            }
        });
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ContactMessage.this, R.layout.msg_item, data);
        ListView listView = (ListView) findViewById(R.id.list_mess);
        listView.setAdapter(adapter);
    }
}
