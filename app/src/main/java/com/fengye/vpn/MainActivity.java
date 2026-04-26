package com.fengye.vpn;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private boolean connected = true;
    private TextView statusText;
    private Button powerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 使用 XML 布局
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