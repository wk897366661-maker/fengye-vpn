package com.fengye.vpn;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    private boolean connected = true;
    private TextView statusText;
    private Button powerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showSplash();
    }

    private void showSplash() {
        LinearLayout splash = new LinearLayout(this);
        splash.setOrientation(LinearLayout.VERTICAL);
        splash.setGravity(Gravity.CENTER);
        splash.setBackgroundColor(Color.rgb(6, 26, 58));

        TextView leaf = new TextView(this);
        leaf.setText("🍁");
        leaf.setTextSize(90);
        leaf.setGravity(Gravity.CENTER);

        TextView title = new TextView(this);
        title.setText("枫叶 VPN");
        title.setTextSize(34);
        title.setTextColor(Color.WHITE);
        title.setGravity(Gravity.CENTER);

        TextView sub = new TextView(this);
        sub.setText("安全放心");
        sub.setTextSize(20);
        sub.setTextColor(Color.rgb(120, 183, 255));
        sub.setGravity(Gravity.CENTER);

        splash.addView(leaf);
        splash.addView(title);
        splash.addView(sub);

        setContentView(splash);

        new Handler().postDelayed(() -> showMainPage(), 2000);
    }

    private void showMainPage() {
        setContentView(R.layout.activity_main);

        statusText = findViewById(R.id.statusText);
        powerButton = findViewById(R.id.powerButton);

        powerButton.setOnClickListener(v -> {
            connected = !connected;

            if (connected) {
                statusText.setText("● 已连接 · 智能加速中");
                Toast.makeText(this, "已连接日本01 · VIP专线", Toast.LENGTH_SHORT).show();
            } else {
                statusText.setText("● 已断开连接");
                Toast.makeText(this, "连接已断开", Toast.LENGTH_SHORT).show();
            }
        });
    }
}