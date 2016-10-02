package com.example.vincent.week1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Vincent on 2016/9/28.
 */

public class GuidePage extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guide_layout);
        Button base_btn = (Button) findViewById(R.id.base_btn);
        Button upgrade_btn = (Button) findViewById(R.id.upgrade_btn);
        base_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GuidePage.this, MainActivity.class);
                startActivity(intent);
            }
        });
        upgrade_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GuidePage.this, UpgradeActivity.class);
                startActivity(intent);
            }
        });
    }
}
