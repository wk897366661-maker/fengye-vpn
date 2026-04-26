package com.fengye.vpn;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.MotionEvent;
import android.widget.*;
import android.content.Intent;
import android.net.Uri;

public class MainActivity extends Activity {

    int page = 0;
    boolean connected = false;
    float downX;
    LinearLayout root, content;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        splash();
    }

    void splash() {
        LinearLayout s = new LinearLayout(this);
        s.setOrientation(LinearLayout.VERTICAL);
        s.setGravity(Gravity.CENTER);
        s.setBackgroundColor(Color.rgb(3, 15, 36));

        s.addView(txt("🍁", 90, Color.WHITE));
        s.addView(txt("枫叶 VPN", 36, Color.WHITE));
        s.addView(txt("安全放心 · 智能加速", 18, Color.rgb(30, 210, 230)));

        setContentView(s);
        new Handler().postDelayed(() -> showPage(0), 1800);
    }

    TextView txt(String t, int size, int color) {
        TextView v = new TextView(this);
        v.setText(t);
        v.setTextSize(size);
        v.setTextColor(color);
        v.setGravity(Gravity.CENTER);
        v.setPadding(8, 8, 8, 8);
        return v;
    }

    Button btn(String t) {
        Button b = new Button(this);
        b.setText(t);
        b.setTextSize(15);
        b.setAllCaps(false);
        return b;
    }

    GradientDrawable bg(int color, int stroke, int strokeColor, int radius) {
        GradientDrawable g = new GradientDrawable();
        g.setColor(color);
        g.setCornerRadius(radius);
        if (stroke > 0) g.setStroke(stroke, strokeColor);
        return g;
    }

    void base() {
        root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setBackgroundColor(Color.rgb(3, 15, 36));

        ScrollView scroll = new ScrollView(this);
        content = new LinearLayout(this);
        content.setOrientation(LinearLayout.VERTICAL);
        content.setPadding(28, 38, 28, 20);
        content.setGravity(Gravity.CENTER_HORIZONTAL);

        scroll.addView(content);
        root.addView(scroll, new LinearLayout.LayoutParams(-1, 0, 1));

        nav();

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
        if (p == 0) home();
        if (p == 1) shop();
        if (p == 2) mine();
    }

    void nav() {
        LinearLayout n = new LinearLayout(this);
        n.setOrientation(LinearLayout.HORIZONTAL);
        n.setPadding(20, 10, 20, 16);
        n.setGravity(Gravity.CENTER);
        n.setBackgroundColor(Color.rgb(7, 28, 58));

        Button h = btn("首页");
        Button s = btn("商城");
        Button m = btn("我的");

        h.setOnClickListener(v -> showPage(0));
        s.setOnClickListener(v -> showPage(1));
        m.setOnClickListener(v -> showPage(2));

        n.addView(h, new LinearLayout.LayoutParams(0, 82, 1));
        n.addView(s, new LinearLayout.LayoutParams(0, 82, 1));
        n.addView(m, new LinearLayout.LayoutParams(0, 82, 1));

        root.addView(n);
    }

    void dots() {
        content.addView(txt(
                page == 0 ? "●  ○  ○" : page == 1 ? "○  ●  ○" : "○  ○  ●",
                24,
                Color.WHITE
        ));
    }

    void card(String title, String sub) {
        TextView c = txt(title + "\n" + sub, 18, Color.WHITE);
        c.setGravity(Gravity.LEFT);
        c.setPadding(30, 24, 30, 24);
        c.setBackground(bg(Color.rgb(8, 34, 68), 2, Color.rgb(25, 90, 130), 26));
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(-1, -2);
        p.setMargins(0, 12, 0, 12);
        content.addView(c, p);
    }

    void home() {
        base();

        content.addView(txt("枫叶 VPN", 34, Color.WHITE));
        content.addView(txt(connected ? "● 已连接 · 智能加速中" : "● 已断开", 18,
                connected ? Color.rgb(20, 210, 230) : Color.rgb(160, 160, 160)));

        TextView leaf = txt("🍁", 96, connected ? Color.rgb(255, 120, 20) : Color.rgb(130, 130, 130));
        leaf.setBackground(bg(Color.rgb(4, 22, 35), 6, Color.rgb(20, 190, 230), 220));
        leaf.setPadding(80, 80, 80, 80);
        leaf.setOnClickListener(v -> {
            connected = !connected;
            Toast.makeText(this, connected ? "已连接日本01 · VIP专线" : "已断开连接", Toast.LENGTH_SHORT).show();
            home();
        });

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(330, 330);
        lp.setMargins(0, 35, 0, 35);
        content.addView(leaf, lp);

        content.addView(txt("点击枫叶连接 / 断开", 16, Color.rgb(160, 180, 200)));

        card("当前节点", "🇯🇵 日本01  VIP专线        45ms\n🇺🇸 芝加哥01  AI专线      158ms");
        card("网络模式", "智能模式 · 自动选择最优线路");
        card("加速状态", connected ? "网络稳定 · 延迟 45ms" : "当前未连接");
        card("邀请好友得奖励", "每邀请1人返¥10，邀请5人送月卡");

        dots();
    }

    void shop() {
        base();

        content.addView(txt("套餐中心", 32, Color.WHITE));

        card("高级套餐【每月300G】", "已用 2.49GB    剩余 297.51GB\n到期时间：2026年5月23日");

        card("🔥 月卡", "¥19.9 / 月\n300G 高速流量");
        card("👑 季卡【主推】", "¥49.9 / 季\n900G 高速流量 · VIP专线优先");
        card("💎 半年卡", "¥89.9 / 半年\n1800G 高速流量");
        card("🏆 年卡", "¥169 / 年\n3600G 高速流量");
        card("🤖 AI 专线月卡", "¥39.9 / 月\nChatGPT / Claude / Gemini 专线");
        card("👨‍👩‍👧 家庭共享年卡", "¥299 / 年\n5000G 多设备共享");
        card("🎁 邀请返利", "每邀请1人返¥10，邀请5人送月卡");

        Button buy = btn("立即购买 / 续费");
        buy.setOnClickListener(v -> Toast.makeText(this, "联系客服QQ：270294959", Toast.LENGTH_LONG).show());
        content.addView(buy, new LinearLayout.LayoutParams(-1, 90));

        Button service = btn("联系客服 QQ：270294959");
        service.setOnClickListener(v -> Toast.makeText(this, "客服QQ：270294959", Toast.LENGTH_LONG).show());
        content.addView(service, new LinearLayout.LayoutParams(-1, 90));

        dots();
    }

    void mine() {
        base();

        content.addView(txt("我的账户", 32, Color.WHITE));

        card("👑 VIP 高级会员", "枫叶用户  ID:10086\n到期时间：2026年5月23日");
        card("账户数据", "余额：¥0\n已用流量：2.49GB\n剩余流量：297.51GB");
        card("代理中心", "成为代理，享高额返利");
        card("功能中心", "订单记录  ›\n流量明细  ›\n邀请返利  ›\n代理制度  ›\n我的团队  ›\n优惠券  ›\n通知与设置  ›\n常见问题  ›\n关于我们  V1.0.0");
        card("联系客服", "QQ：270294959");

        Button copy = btn("联系客服 QQ：270294959");
        copy.setOnClickListener(v -> Toast.makeText(this, "客服QQ：270294959", Toast.LENGTH_LONG).show());
        content.addView(copy, new LinearLayout.LayoutParams(-1, 90));

        Button logout = btn("退出登录");
        content.addView(logout, new LinearLayout.LayoutParams(-1, 90));

        dots();
    }
}