package com.fengye.vpn;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.*;
import android.view.MotionEvent;

public class MainActivity extends Activity {

    int page = 0;
    boolean connected = false;
    float downX;
    LinearLayout root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showSplash();
    }

    void showSplash() {
        LinearLayout s = new LinearLayout(this);
        s.setOrientation(LinearLayout.VERTICAL);
        s.setGravity(Gravity.CENTER);
        s.setBackgroundColor(Color.rgb(6, 26, 58));

        TextView leaf = text("🍁", 96, Color.WHITE);
        TextView title = text("枫叶 VPN", 34, Color.WHITE);
        TextView sub = text("安全放心", 20, Color.rgb(120,183,255));

        s.addView(leaf);
        s.addView(title);
        s.addView(sub);
        setContentView(s);

        new Handler().postDelayed(() -> showPage(0), 2000);
    }

    TextView text(String txt, int size, int color) {
        TextView v = new TextView(this);
        v.setText(txt);
        v.setTextSize(size);
        v.setTextColor(color);
        v.setGravity(Gravity.CENTER);
        v.setPadding(0, 10, 0, 10);
        return v;
    }

    Button btn(String txt) {
        Button b = new Button(this);
        b.setText(txt);
        b.setTextSize(18);
        b.setAllCaps(false);
        return b;
    }

    void base() {
        root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.CENTER_HORIZONTAL);
        root.setPadding(40, 70, 40, 40);
        root.setBackgroundColor(Color.rgb(6, 26, 58));
        setContentView(root);

        root.setOnTouchListener((v, e) -> {
            if (e.getAction() == MotionEvent.ACTION_DOWN) {
                downX = e.getX();
                return true;
            }
            if (e.getAction() == MotionEvent.ACTION_UP) {
                float upX = e.getX();
                if (downX - upX > 120 && page < 2) showPage(page + 1);
                if (upX - downX > 120 && page > 0) showPage(page - 1);
                return true;
            }
            return true;
        });
    }

    void showPage(int p) {
        page = p;
        if (page == 0) showHome();
        if (page == 1) showShop();
        if (page == 2) showMine();
    }

    void footer() {
        root.addView(text("\n首页     商城     我的", 18, Color.rgb(120,183,255)));
        root.addView(text(page == 0 ? "●  ○  ○" : page == 1 ? "○  ●  ○" : "○  ○  ●", 24, Color.WHITE));
    }

    void showHome() {
        base();

        root.addView(text("枫叶 VPN", 34, Color.WHITE));
        root.addView(text(connected ? "● 已连接 · 智能加速中" : "● 已断开", 18,
                connected ? Color.rgb(20,210,230) : Color.rgb(160,160,160)));

        TextView circle = text(connected ? "🍁" : "🍁", 92,
                connected ? Color.rgb(255,120,30) : Color.rgb(130,130,130));
        circle.setBackgroundColor(connected ? Color.rgb(10,48,60) : Color.rgb(60,60,60));
        circle.setPadding(60,60,60,60);

        circle.setOnClickListener(v -> {
            connected = !connected;
            Toast.makeText(this, connected ? "已连接日本01" : "已断开连接", Toast.LENGTH_SHORT).show();
            showHome();
        });

        root.addView(circle);

        root.addView(text("\n当前节点", 16, Color.rgb(150,170,190)));
        root.addView(text("🇯🇵 日本01  VIP专线  45ms", 21, Color.WHITE));
        root.addView(text("🇺🇸 芝加哥01  AI专线  158ms", 19, Color.WHITE));
        root.addView(text("\n左右滑动切换页面", 16, Color.rgb(120,183,255)));

        footer();
    }

    void showShop() {
        base();

        root.addView(text("套餐中心", 32, Color.WHITE));
        root.addView(text("高级套餐【每月300G】", 22, Color.WHITE));
        root.addView(text("已用 2.49GB   剩余 297.51GB", 16, Color.rgb(170,185,200)));
        root.addView(text("到期时间：2026年5月23日", 16, Color.rgb(170,185,200)));

        root.addView(text("\n月卡  ¥19.9 / 月  300G", 21, Color.WHITE));
        root.addView(text("季卡  ¥49.9 / 季  900G  主推", 21, Color.rgb(20,210,230)));
        root.addView(text("年卡  ¥169 / 年  3600G", 21, Color.WHITE));
        root.addView(text("AI专线  ¥39.9 / 月", 21, Color.WHITE));
        root.addView(text("邀请1人返¥10，邀请5人送月卡", 16, Color.rgb(120,183,255)));

        Button buy = btn("立即购买 / 续费");
        buy.setOnClickListener(v -> Toast.makeText(this, "联系客服QQ：270294959", Toast.LENGTH_LONG).show());
        root.addView(buy);

        footer();
    }

    void showMine() {
        base();

        root.addView(text("我的账户", 32, Color.WHITE));
        root.addView(text("套餐：高级套餐 300G/月", 21, Color.WHITE));
        root.addView(text("状态：有效", 21, Color.rgb(20,210,230)));
        root.addView(text("余额：¥0", 21, Color.WHITE));

        root.addView(text("\n功能中心", 24, Color.WHITE));
        root.addView(text("订单记录  ›\n流量明细  ›\n邀请返利  ›\n代理制度  ›\n通知与设置  ›", 21, Color.WHITE));

        Button service = btn("联系客服");
        service.setOnClickListener(v -> Toast.makeText(this, "客服QQ：270294959", Toast.LENGTH_LONG).show());
        root.addView(service);

        footer();
    }
}