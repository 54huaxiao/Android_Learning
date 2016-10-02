package com.example.vincent.week1;

/**
 * Created by Vincent on 2016/9/10.
 */
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        Button login = (Button) findViewById(R.id.login);
        Button register = (Button) findViewById(R.id.register);
        final EditText user_edit = (EditText) findViewById(R.id.user_edit);
        final EditText pwd_edit = (EditText) findViewById(R.id.pwd_edit);
        final RadioButton stu = (RadioButton) findViewById(R.id.radioButton);
        final RadioButton teacher = (RadioButton) findViewById(R.id.radioButton1);
        final RadioButton group = (RadioButton) findViewById(R.id.radioButton2);
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        final AlertDialog.Builder logon_dialog = new AlertDialog.Builder(MainActivity.this);
        logon_dialog.setTitle("提示").setMessage("登录成功").setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "对话框\"确定\"按钮被点击", Toast.LENGTH_SHORT).show();
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "对话框\"取消\"按钮被点击", Toast.LENGTH_SHORT).show();
            }
        }).setCancelable(false).create();
        final AlertDialog.Builder fail_dialog = new AlertDialog.Builder(MainActivity.this);
        fail_dialog.setTitle("提示").setMessage("登录失败").setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "对话框\"确定\"按钮被点击", Toast.LENGTH_SHORT).show();
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "对话框\"取消\"按钮被点击", Toast.LENGTH_SHORT).show();
            }
        }).setCancelable(false).create();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user_edit.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
                } else if (pwd_edit.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                } else if (user_edit.getText().toString().equals("Android") && pwd_edit.getText().toString().equals("123456")) {
                    logon_dialog.show();
                } else {
                    fail_dialog.show();
                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (stu.isChecked()) {
                    Toast.makeText(MainActivity.this, "学生身份注册功能尚未开启", Toast.LENGTH_SHORT).show();
                } else if (teacher.isChecked()) {
                    Toast.makeText(MainActivity.this, "教师身份注册功能尚未开启", Toast.LENGTH_SHORT).show();
                } else if (group.isChecked()) {
                    Toast.makeText(MainActivity.this, "社团身份注册功能尚未开启", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "管理者身份注册功能尚未开启", Toast.LENGTH_SHORT).show();
                }
            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = (RadioButton)findViewById(group.getCheckedRadioButtonId());
                Toast.makeText(MainActivity.this, radioButton.getText().toString()+"身份被选中",Toast.LENGTH_SHORT).show();
            }
        });
    }

}
