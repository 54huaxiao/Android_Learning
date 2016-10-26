package com.example.vincent.lab4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Vincent on 2016/10/18.
 */

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle onsavedInstance) {
        super.onCreate(onsavedInstance);
        setContentView(R.layout.main_layout);
        Button s_btn = (Button) findViewById(R.id.s_broadcast);
        Button d_btn = (Button) findViewById(R.id.d_broadcast);
        s_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StaticActivity.class);
                startActivity(intent);
            }
        });
        d_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DynamicActivity.class);
                startActivity(intent);
            }
        });
    }
}
