package com.example.srt_qa.permissiontest;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btn_request;
    private static final int MY_REQUEST_READ_CONTACT_PERMISSION = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_request = findViewById(R.id.btn_request);
        btn_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CONTACTS) !=
                        PackageManager.PERMISSION_GRANTED){
//                    if(ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,Manifest.permission.READ_CONTACTS)){
                          ////第一次拒绝之后，第二次会直接返回true，关于返回结果，参照android官方文档
//                        Toast.makeText(MainActivity.this,"请打开通讯录读取权限",Toast.LENGTH_SHORT).show();
//                    }else {
//                        ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.READ_CONTACTS},MY_REQUEST_READ_CONTACT_PERMISSION);
//                    }
                    ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.READ_CONTACTS},MY_REQUEST_READ_CONTACT_PERMISSION);
                }else {
                    Toast.makeText(MainActivity.this,"已有通讯录读取权限",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case MY_REQUEST_READ_CONTACT_PERMISSION:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this,"请求读取通讯录权限成功",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this,"请求读取通讯录权限失败",Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }
}
