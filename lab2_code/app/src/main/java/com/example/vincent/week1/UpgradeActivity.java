package com.example.vincent.week1;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by Vincent on 2016/9/28.
 */

public class UpgradeActivity extends Activity {
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upgrade_layout);
        Button login = (Button) findViewById(R.id.login);
        final Button register = (Button) findViewById(R.id.register);
        final EditText user_edit = (EditText) findViewById(R.id.user_edit);
        final EditText pwd_edit = (EditText) findViewById(R.id.pwd_edit);
        final RadioButton stu = (RadioButton) findViewById(R.id.radioButton);
        final RadioButton teacher = (RadioButton) findViewById(R.id.radioButton1);
        final RadioButton group = (RadioButton) findViewById(R.id.radioButton2);
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        final android.support.design.widget.TextInputLayout user_input = (TextInputLayout) findViewById(R.id.user_input);
        final android.support.design.widget.TextInputLayout pwd_input = (TextInputLayout) findViewById(R.id.pwd_input);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user_edit.getText().toString().equals("")) {
                    user_input.setErrorEnabled(true);
                    user_input.setError("用户名不能为空");
                } else if (pwd_edit.getText().toString().equals("")) {
                    pwd_input.setErrorEnabled(true);
                    pwd_input.setError("密码不能为空");
                } else if (user_edit.getText().toString().equals("Android") && pwd_edit.getText().toString().equals("123456")) {
                    user_input.setErrorEnabled(false);
                    pwd_input.setErrorEnabled(false);
                    Snackbar.make(register,"登录成功",Snackbar.LENGTH_SHORT).setAction("按钮", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(UpgradeActivity.this, "SnackBar的按钮被点击了", Toast.LENGTH_SHORT).show();
                        }
                    }).setActionTextColor(getResources().getColor(R.color.colorPrimary)).show();
                } else {
                    user_input.setErrorEnabled(false);
                    pwd_input.setErrorEnabled(false);
                    Snackbar.make(register,"登录失败",Snackbar.LENGTH_SHORT).setAction("按钮", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(UpgradeActivity.this, "SnackBar的按钮被点击了", Toast.LENGTH_SHORT).show();
                        }
                    }).setActionTextColor(getResources().getColor(R.color.colorPrimary)).show();
                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (stu.isChecked()) {
                    Snackbar.make(register,"学生身份注册功能尚未开启",Snackbar.LENGTH_SHORT).setAction("按钮", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(UpgradeActivity.this, "SnackBar的按钮被点击了", Toast.LENGTH_SHORT).show();
                        }
                    }).setActionTextColor(getResources().getColor(R.color.colorPrimary)).show();
                } else if (teacher.isChecked()) {
                    Snackbar.make(register,"教师身份注册功能尚未开启",Snackbar.LENGTH_SHORT).setAction("按钮", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(UpgradeActivity.this, "SnackBar的按钮被点击了", Toast.LENGTH_SHORT).show();
                        }
                    }).setActionTextColor(getResources().getColor(R.color.colorPrimary)).show();
                } else if (group.isChecked()) {
                    Snackbar.make(register,"社团身份注册功能尚未开启",Snackbar.LENGTH_SHORT).setAction("按钮", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(UpgradeActivity.this, "SnackBar的按钮被点击了", Toast.LENGTH_SHORT).show();
                        }
                    }).setActionTextColor(getResources().getColor(R.color.colorPrimary)).show();
                } else {
                    Snackbar.make(register,"管理者身份注册功能尚未开启",Snackbar.LENGTH_SHORT).setAction("按钮", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(UpgradeActivity.this, "SnackBar的按钮被点击了", Toast.LENGTH_SHORT).show();
                        }
                    }).setActionTextColor(getResources().getColor(R.color.colorPrimary)).show();
                }
            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = (RadioButton) findViewById(group.getCheckedRadioButtonId());
                Snackbar.make(register,radioButton.getText().toString() + "身份被选中",Snackbar.LENGTH_SHORT).setAction("按钮", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(UpgradeActivity.this, "SnackBar的按钮被点击了", Toast.LENGTH_SHORT).show();
                    }
                }).setActionTextColor(getResources().getColor(R.color.colorPrimary)).show();
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Upgrade Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
