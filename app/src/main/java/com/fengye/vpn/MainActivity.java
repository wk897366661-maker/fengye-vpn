package com.fengye.vpn;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(50, 80, 50, 50);
        root.setBackgroundColor(Color.rgb(6, 26, 58));

        TextView title = new TextView(this);
        title.setText("🍁 枫叶 VPN");
        title.setTextSize(32);
        title.setTextColor(Color.WHITE);
        title.setGravity(Gravity.CENTER);

        TextView sub = new TextView(this);
        sub.setText("VIP 专线 · 安全放心");
        sub.setTextSize(18);
        sub.setTextColor(Color.rgb(120, 183, 255));
        sub.setPadding(0, 20, 0, 50);

        TextView packageTitle = new TextView(this);
        packageTitle.setText("套餐中心");
        packageTitle.setTextSize(24);
        packageTitle.setTextColor(Color.WHITE);

        TextView month = new TextView(this);
        month.setText("月卡套餐   ¥19.9   /   300G");
        month.setTextSize(20);
        month.setTextColor(Color.WHITE);
        month.setPadding(0, 40, 0, 20);

        TextView season = new TextView(this);
        season.setText("季卡套餐   ¥49.9   /   900G");
        season.setTextSize(20);
        season.setTextColor(Color.WHITE);
        season.setPadding(0, 20, 0, 20);

        TextView year = new TextView(this);
        year.setText("年卡套餐   ¥169   /   3600G");
        year.setTextSize(20);
        year.setTextColor(Color.WHITE);
        year.setPadding(0, 20, 0, 50);

        Button buyBtn = new Button(this);
        buyBtn.setText("立即购买");
        buyBtn.setTextSize(20);
        buyBtn.setOnClickListener(v ->
                Toast.makeText(this, "联系客服购买套餐", Toast.LENGTH_LONG).show()
        );

        Button serviceBtn = new Button(this);
        serviceBtn.setText("联系客服");
        serviceBtn.setTextSize(20);
        serviceBtn.setOnClickListener(v ->
                Toast.makeText(this, "客服微信：fengyevpn", Toast.LENGTH_LONG).show()
        );

        TextView bottom = new TextView(this);
        bottom.setText("\n首页        商城        我的");
        bottom.setTextSize(18);
        bottom.setTextColor(Color.rgb(20, 210, 230));
        bottom.setGravity(Gravity.CENTER);

        root.addView(title);
        root.addView(sub);
        root.addView(packageTitle);
        root.addView(month);
        root.addView(season);
        root.addView(year);
        root.addView(buyBtn);
        root.addView(serviceBtn);
        root.addView(bottom);

        setContentView(root);
    }
}