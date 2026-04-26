package com.fengye.vpn;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(60, 120, 60, 60);
        layout.setBackgroundColor(Color.rgb(6, 26, 58));

        TextView title = new TextView(this);
        title.setText("🍁 枫叶 VPN");
        title.setTextSize(32);
        title.setTextColor(Color.WHITE);

        TextView sub = new TextView(this);
        sub.setText("安全放心");
        sub.setTextSize(22);
        sub.setTextColor(Color.rgb(120, 183, 255));

        layout.addView(title);
        layout.addView(sub);

        setContentView(layout);
    }
}