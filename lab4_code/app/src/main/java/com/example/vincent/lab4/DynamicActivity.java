package com.example.vincent.lab4;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Vincent on 2016/10/19.
 */

public class DynamicActivity extends Activity {
    public static final String DYNAMICACTION = "com.example.vincent.lab4.MydBroadcast";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.d_broadcastlayout);
        final EditText textView = (EditText) findViewById(R.id.editText);
        final Button r_btn = (Button) findViewById(R.id.broadcast_btn);
        final DynamicBroadcast dynamicBroadcast = new DynamicBroadcast();
        r_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(r_btn.getText().equals("Register Broadcast")) {
                    if (textView.getText().toString().isEmpty()) {
                        Toast.makeText(DynamicActivity.this, "The input shouldn't be empty.", Toast.LENGTH_SHORT).show();
                    } else {
                        r_btn.setText("Unregister Broadcast");
                        IntentFilter dynamic_filter = new IntentFilter();
                        dynamic_filter.addAction(DYNAMICACTION);
                        registerReceiver(dynamicBroadcast, dynamic_filter);
                    }
                } else {
                    r_btn.setText("Register Broadcast");
                    unregisterReceiver(dynamicBroadcast);
                }
            }
        });
        Button send = (Button) findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText textView = (EditText) findViewById(R.id.editText);
                Intent intent = new Intent(DYNAMICACTION);
                Bundle bundle = new Bundle();
                bundle.putString("text", textView.getText().toString());
                intent.putExtras(bundle);
                sendBroadcast(intent);
            }
        });
    }
}
