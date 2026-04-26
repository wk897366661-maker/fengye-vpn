package com.fengye.vpn;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    private final String nodeLink = "vmess://ewogICJ2IjogIjIiLAogICJwcyI6ICIiLAogICJhZGQiOiAiNDUuNzYuMjIwLjE1NSIsCiAgInBvcnQiOiA0OTc2OSwKICAiaWQiOiAiNmMxNzRiOWUtNTNiNi00OGI3LTg0OTktMGYwYzE0MDg3MjY2IiwKICAiYWlkIjogMCwKICAibmV0IjogInRjcCIsCiAgInR5cGUiOiAibm9uZSIsCiAgImhvc3QiOiAiIiwKICAicGF0aCI6ICIiLAogICJ0bHMiOiAibm9uZSIKfQ==";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(60, 100, 60, 60);
        layout.setBackgroundColor(Color.rgb(6, 26, 58));

        TextView title = new TextView(this);
        title.setText("🍁 枫叶 VPN");
        title.setTextSize(34);
        title.setTextColor(Color.WHITE);

        TextView sub = new TextView(this);
        sub.setText("安全放心");
        sub.setTextSize(22);
        sub.setTextColor(Color.rgb(120, 183, 255));

        Button copyBtn = new Button(this);
        copyBtn.setText("一键复制节点");
        copyBtn.setTextSize(18);
        copyBtn.setOnClickListener(v -> {
            ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            cm.setPrimaryClip(ClipData.newPlainText("枫叶节点", nodeLink));
            Toast.makeText(this, "节点已复制，请到 V2Box 从剪贴板导入", Toast.LENGTH_LONG).show();
        });

        Button openBtn = new Button(this);
        openBtn.setText("打开 V2Box");
        openBtn.setTextSize(18);
        openBtn.setOnClickListener(v -> {
            try {
                Intent intent = getPackageManager().getLaunchIntentForPackage("com.v2ray.ang");
                if (intent != null) {
                    startActivity(intent);
                } else {
                    Intent market = new Intent(Intent.ACTION_VIEW, Uri.parse("market://search?q=V2Box"));
                    startActivity(market);
                }
            } catch (Exception e) {
                Toast.makeText(this, "请手动打开 V2Box", Toast.LENGTH_LONG).show();
            }
        });

        TextView guide = new TextView(this);
        guide.setText("\n使用教程：\n1. 点击“一键复制节点”\n2. 打开 V2Box\n3. 选择“从剪贴板导入”\n4. 点击连接");
        guide.setTextSize(18);
        guide.setTextColor(Color.WHITE);

        layout.addView(title);
        layout.addView(sub);
        layout.addView(copyBtn);
        layout.addView(openBtn);
        layout.addView(guide);

        setContentView(layout);
    }
}