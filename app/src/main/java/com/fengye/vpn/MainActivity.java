package com.fengye.vpn;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    boolean connected = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(40, 60, 40, 40);
        root.setGravity(Gravity.CENTER_HORIZONTAL);
        root.setBackgroundColor(Color.rgb(6, 26, 58));

        TextView title = new TextView(this);
        title.setText("枫叶 VPN");
        title.setTextSize(34);
        title.setTextColor(Color.WHITE);
        title.setGravity(Gravity.CENTER);

        TextView status = new TextView(this);
        status.setText("● 已连接");
        status.setTextSize(16);
        status.setTextColor(Color.rgb(20, 210, 230));
        status.setGravity(Gravity.CENTER);
        status.setPadding(0, 20, 0, 70);

        Button power = new Button(this);
        power.setText("⏻");
        power.setTextSize(54);
        power.setTextColor(Color.rgb(20, 210, 230));

        GradientDrawable powerBg = new GradientDrawable();
        powerBg.setShape(GradientDrawable.OVAL);
        powerBg.setColor(Color.rgb(8, 36, 48));
        powerBg.setStroke(6, Color.rgb(20, 180, 210));
        power.setBackground(powerBg);

        LinearLayout.LayoutParams powerParams = new LinearLayout.LayoutParams(300, 300);
        power.setLayoutParams(powerParams);

        power.setOnClickListener(v -> {
            connected = !connected;
            if (connected) {
                status.setText("● 已连接");
                Toast.makeText(this, "枫叶 VPN 已连接", Toast.LENGTH_SHORT).show();
            } else {
                status.setText("● 未连接");
                Toast.makeText(this, "已断开连接", Toast.LENGTH_SHORT).show();
            }
        });

        TextView node = new TextView(this);
        node.setText("\n当前节点\n🇯🇵 日本 01  ›");
        node.setTextSize(22);
        node.setTextColor(Color.WHITE);
        node.setGravity(Gravity.CENTER);
        node.setPadding(0, 80, 0, 80);

        TextView bottom = new TextView(this);
        bottom.setText("首页          商城          我的");
        bottom.setTextSize(20);
        bottom.setTextColor(Color.rgb(20, 210, 230));
        bottom.setGravity(Gravity.CENTER);
        bottom.setPadding(0, 80, 0, 0);

        root.addView(title);
        root.addView(status);
        root.addView(power);
        root.addView(node);
        root.addView(bottom);

        setContentView(root);
    }
}