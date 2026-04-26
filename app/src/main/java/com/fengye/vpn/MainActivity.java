package com.fengye.vpn;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.content.Intent;
import android.net.Uri;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ScrollView;

public class MainActivity extends Activity {

    boolean connected = true;
    LinearLayout root;
    LinearLayout content;
    TextView statusText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showHome();
    }

    void base() {
        root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setBackgroundColor(Color.rgb(6, 26, 58));

        ScrollView scroll = new ScrollView(this);
        content = new LinearLayout(this);
        content.setOrientation(LinearLayout.VERTICAL);
        content.setPadding(36, 55, 36, 20);
        content.setGravity(Gravity.CENTER_HORIZONTAL);

        scroll.addView(content);
        root.addView(scroll, new LinearLayout.LayoutParams(-1, 0, 1));

        nav();
        setContentView(root);
    }

    TextView text(String s, int size, int color) {
        TextView t = new TextView(this);
        t.setText(s);
        t.setTextSize(size);
        t.setTextColor(color);
        t.setGravity(Gravity.CENTER);
        t.setPadding(0, 8, 0, 8);
        return t;
    }

    Button btn(String s) {
        Button b = new Button(this);
        b.setText(s);
        b.setTextSize(15);
        b.setAllCaps(false);
        return b;
    }

    void nav() {
        LinearLayout bar = new LinearLayout(this);
        bar.setOrientation(LinearLayout.HORIZONTAL);
        bar.setPadding(20, 10, 20, 22);
        bar.setGravity(Gravity.CENTER);
        bar.setBackgroundColor(Color.rgb(10, 36, 70));

        Button home = btn("首页");
        Button shop = btn("商城");
        Button mine = btn("我的");

        home.setOnClickListener(v -> showHome());
        shop.setOnClickListener(v -> showShop());
        mine.setOnClickListener(v -> showMine());

        bar.addView(home, new LinearLayout.LayoutParams(0, 86, 1));
        bar.addView(shop, new LinearLayout.LayoutParams(0, 86, 1));
        bar.addView(mine, new LinearLayout.LayoutParams(0, 86, 1));

        root.addView(bar, new LinearLayout.LayoutParams(-1, -2));
    }

    void openUrl(String url) {
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        } catch (Exception e) {
            Toast.makeText(this, "无法打开，请检查是否安装应用", Toast.LENGTH_SHORT).show();
        }
    }

    public void showHome() {
        base();

        content.addView(text("🍁 枫叶 VPN", 32, Color.WHITE));

        statusText = text(
                connected ? "● 已连接 · 智能加速中" : "● 未连接",
                17,
                connected ? Color.rgb(20, 210, 230) : Color.rgb(255, 120, 120)
        );
        content.addView(statusText);

        Button power = btn("⏻");
        power.setTextSize(54);
        power.setTextColor(Color.rgb(20, 210, 230));

        GradientDrawable bg = new GradientDrawable();
        bg.setShape(GradientDrawable.OVAL);
        bg.setColor(Color.rgb(8, 36, 48));
        bg.setStroke(7, Color.rgb(20, 180, 210));
        power.setBackground(bg);

        content.addView(power, new LinearLayout.LayoutParams(285, 285));

        power.setOnClickListener(v -> {
            connected = !connected;
            statusText.setText(connected ? "● 已连接 · 智能加速中" : "● 未连接");
            statusText.setTextColor(connected ? Color.rgb(20, 210, 230) : Color.rgb(255, 120, 120));
            Toast.makeText(this, connected ? "已连接日本01 · VIP专线" : "已断开连接", Toast.LENGTH_SHORT).show();
        });

        content.addView(text("\n当前节点", 15, Color.rgb(150, 170, 190)));
        content.addView(text("🇯🇵 日本01   VIP专线   45ms", 20, Color.WHITE));
        content.addView(text("🇺🇸 芝加哥01   AI专线   158ms", 18, Color.WHITE));

        content.addView(text("\n快捷入口", 18, Color.rgb(120, 183, 255)));

        Button chatgpt = btn("ChatGPT");
        Button youtube = btn("YouTube");
        Button netflix = btn("Netflix");
        Button tg = btn("Telegram");

        chatgpt.setOnClickListener(v -> openUrl("https://chat.openai.com"));
        youtube.setOnClickListener(v -> openUrl("https://youtube.com"));
        netflix.setOnClickListener(v -> openUrl("https://netflix.com"));
        tg.setOnClickListener(v -> openUrl("https://telegram.org"));

        content.addView(chatgpt, new LinearLayout.LayoutParams(-1, 80));
        content.addView(youtube, new LinearLayout.LayoutParams(-1, 80));
        content.addView(netflix, new LinearLayout.LayoutParams(-1, 80));
        content.addView(tg, new LinearLayout.LayoutParams(-1, 80));
    }

    public void showShop() {
        base();

        content.addView(text("🍁 枫叶 VPN", 31, Color.WHITE));
        content.addView(text("套餐中心", 27, Color.WHITE));
        content.addView(text("高级套餐【每月300G】", 21, Color.WHITE));
        content.addView(text("已用 2.49GB    剩余 297.51GB", 16, Color.rgb(170, 185, 200)));
        content.addView(text("到期时间：2026年5月23日", 16, Color.rgb(170, 185, 200)));

        content.addView(text("\n月卡  ¥19.9 / 月  300G", 20, Color.WHITE));
        content.addView(text("季卡  ¥49.9 / 季  900G  主推", 20, Color.rgb(20, 210, 230)));
        content.addView(text("年卡  ¥169 / 年  3600G", 20, Color.WHITE));
        content.addView(text("AI专线  ¥39.9 / 月", 20, Color.WHITE));
        content.addView(text("邀请1人返¥10，邀请5人送月卡", 16, Color.rgb(120, 183, 255)));

        Button buy = btn("立即购买 / 续费");
        buy.setOnClickListener(v -> Toast.makeText(this, "联系客服QQ：270294959", Toast.LENGTH_LONG).show());
        content.addView(buy, new LinearLayout.LayoutParams(-1, 95));
    }

    public void showMine() {
        base();

        content.addView(text("我的账户", 30, Color.WHITE));
        content.addView(text("套餐：高级套餐 300G/月", 20, Color.WHITE));
        content.addView(text("状态：有效", 20, Color.rgb(20, 210, 230)));
        content.addView(text("余额：¥0", 20, Color.WHITE));

        content.addView(text("\n功能中心", 24, Color.WHITE));
        content.addView(text("订单记录  ›", 20, Color.WHITE));
        content.addView(text("流量明细  ›", 20, Color.WHITE));
        content.addView(text("邀请返利  ›", 20, Color.WHITE));
        content.addView(text("代理制度  ›", 20, Color.WHITE));
        content.addView(text("通知与设置  ›", 20, Color.WHITE));

        Button service = btn("联系客服");
        service.setOnClickListener(v -> Toast.makeText(this, "客服QQ：270294959", Toast.LENGTH_LONG).show());
        content.addView(service, new LinearLayout.LayoutParams(-1, 95));
    }
}