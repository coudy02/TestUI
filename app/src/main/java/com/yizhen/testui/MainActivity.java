package com.yizhen.testui;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.yizhen.testui.recyclerView.TRecycleViewActivity;
import com.yizhen.testui.sqlite.SQLiteActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 2018-03-22
 *  【读取assets下的文件，并安装】
 *  问题1：android.os.FileUriExposedException: file:///storage/emulated/0/check.apk exposed beyond app through Intent.getData()
 *  解决：Android N对访问文件权限收回，按照Android N的要求，若要在应用间共享文件，您应发送一项 content://URI，并授予 URI 临时访问权限。
 而进行此授权的最简单方式是使用 FileProvider类。
 *  问题2：java.io.FileNotFoundException: /storage/emulated/0/Video/ekwing_main_paren.apk（你的文件路径）: open failed: EACCES (Permission denied)
 *  解决： 动态获取权限，Android 6.0 新特性，一些保护权限，除了要在AndroidManifest中声明权限，还要使用如下代码动态获取
 *
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_cycle;
    private Button btn_open_apk;
    private Button btn_sqlite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_cycle = (Button) findViewById(R.id.btn_cycle);
        btn_cycle.setOnClickListener(this);
        btn_open_apk = (Button) findViewById(R.id.btn_open_apk);
        btn_open_apk.setOnClickListener(this);
        btn_sqlite = (Button) findViewById(R.id.btn_sqlite);
        btn_sqlite.setOnClickListener(this);

        /**
         * 动态获取权限，Android 6.0 新特性，一些保护权限，除了要在AndroidManifest中声明权限，还要使用如下代码动态获取
         */
        if (Build.VERSION.SDK_INT >= 23) {
            int REQUEST_CODE_CONTACT = 101;
            String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
            //验证是否许可权限
            for (String str : permissions) {
                if (this.checkSelfPermission(str) != PackageManager.PERMISSION_GRANTED) {
                    //申请权限
                    this.requestPermissions(permissions, REQUEST_CODE_CONTACT);
                    return;
                }
            }
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_cycle:{
                Intent intent = new Intent(this, TRecycleViewActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.btn_sqlite:{
                Intent intent = new Intent(this, SQLiteActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.btn_open_apk:{
                boolean openApk = true;
                if(openApk){
                    getApkAndInstall();
                }
                break;
            }
        }
    }

    /**
     * 1.先打开文件，
     * 2.打开成功，安装apk
     */
    private void getApkAndInstall() {
        AssetManager am = null;
        InputStream in = null;
        OutputStream out = null;
        try {
            am = getAssets();
            in = am.open("check_37_20180305_test_211.apk");
            Log.e("zhen", "is=" + in);
            String outFileName = Environment.getExternalStorageDirectory().getAbsolutePath();//保存到外部存储,大部分设备是sd卡根目录
            String copyName = "check.apk"; //copy后具体名称 System.currentTimeMillis() +
            File outFile = new File(outFileName, copyName);
            out = new FileOutputStream(outFile);
            copyFile(in, out);
            in.close();
            out.flush();
            out.close();
            //copy 完毕,直接安装
            File file = new File(outFileName, copyName);
            installApkFile(this, file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void installApkFile(Context context, String filePath) {

        File file = this.getFilesDir();
        file = Environment.getExternalStorageDirectory(); //  /storage/emulated/0
        Log.e("zhen", "file="+file);  //   /data/user/0/com.yizhen.testui/files
        String[] filestr = file.list();
        Log.e("zhen", "filestr="+filestr.length);
        File[] files = file.listFiles();
        Log.e("zhen", "files="+files.length);
        for(File f: files){
            Log.e("zhen", "f="+f);
        }

        Intent intent = new Intent(Intent.ACTION_VIEW);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

            Uri contentUri = FileProvider.getUriForFile(context, "com.yizhen.testui.fileprovider", new File(filePath));


            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
            Log.e("zhen", "版本6.0以上");
        } else {
            intent.setDataAndType(Uri.fromFile(new File(filePath)), "application/vnd.android.package-archive");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Log.e("zhen", "以下");
        }
        context.startActivity(intent);
    }

    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
    }
}
