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

    int page = 0;
    LinearLayout root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showHome();
    }

    void base() {
        root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(42, 70, 42, 38);
        root.setGravity(Gravity.CENTER_HORIZONTAL);
        root.setBackgroundColor(Color.rgb(6, 26, 58));
        setContentView(root);
    }

    TextView text(String s, int size, int color) {
        TextView t = new TextView(this);
        t.setText(s);
        t.setTextSize(size);
        t.setTextColor(color);
        t.setGravity(Gravity.CENTER);
        t.setPadding(0, 12, 0, 12);
        return t;
    }

    Button btn(String s) {
        Button b = new Button(this);
        b.setText(s);
        b.setTextSize(18);
        b.setAllCaps(false);
        return b;
    }

    void nav() {
        LinearLayout bar = new LinearLayout(this);
        bar.setOrientation(LinearLayout.HORIZONTAL);
        bar.setGravity(Gravity.CENTER);
        bar.setPadding(0, 45, 0, 0);

        Button home = btn("首页");
        Button shop = btn("商城");
        Button mine = btn("我的");

        home.setOnClickListener(v -> showHome());
        shop.setOnClickListener(v -> showShop());
        mine.setOnClickListener(v -> showMine());

        bar.addView(home, new LinearLayout.LayoutParams(0, 110, 1));
        bar.addView(shop, new LinearLayout.LayoutParams(0, 110, 1));
        bar.addView(mine, new LinearLayout.LayoutParams(0, 110, 1));
        root.addView(bar);
    }

    public void showHome() {
        base();

        root.addView(text("🍁 枫叶 VPN", 34, Color.WHITE));
        root.addView(text("● 已连接 · 智能加速中", 17, Color.rgb(20, 210, 230)));

        Button power = btn("⏻");
        power.setTextSize(56);
        power.setTextColor(Color.rgb(20, 210, 230));

        GradientDrawable bg = new GradientDrawable();
        bg.setShape(GradientDrawable.OVAL);
        bg.setColor(Color.rgb(8, 36, 48));
        bg.setStroke(7, Color.rgb(20, 180, 210));
        power.setBackground(bg);

        root.addView(power, new LinearLayout.LayoutParams(320, 320));

        power.setOnClickListener(v ->
                Toast.makeText(this, "已连接日本01 · VIP专线", Toast.LENGTH_LONG).show()
        );

        root.addView(text("\n当前节点", 16, Color.rgb(150, 170, 190)));
        root.addView(text("🇯🇵 日本01   VIP专线   45ms", 22, Color.WHITE));
        root.addView(text("🇺🇸 芝加哥01   AI专线   158ms", 20, Color.WHITE));

        root.addView(text("\n快捷入口", 18, Color.rgb(120, 183, 255)));
        root.addView(text("ChatGPT   YouTube   Netflix   Telegram", 18, Color.WHITE));

        nav();
    }

    public void showShop() {
        base();

        root.addView(text("🍁 枫叶 VPN", 32, Color.WHITE));
        root.addView(text("套餐中心", 28, Color.WHITE));
        root.addView(text("高级套餐【每月300G】", 22, Color.WHITE));
        root.addView(text("已用 2.49GB        剩余 297.51GB", 17, Color.rgb(170, 185, 200)));
        root.addView(text("到期时间：2026年5月23日", 17, Color.rgb(170, 185, 200)));

        root.addView(text("\n月卡套餐   ¥19.9   /   300G", 21, Color.WHITE));
        root.addView(text("季卡套餐   ¥49.9   /   900G", 21, Color.WHITE));
        root.addView(text("年卡套餐   ¥169    /   3600G", 21, Color.WHITE));

        Button buy = btn("立即购买 / 续费");
        buy.setOnClickListener(v ->
                Toast.makeText(this, "联系客服购买：fengyevpn", Toast.LENGTH_LONG).show()
        );
        root.addView(buy, new LinearLayout.LayoutParams(-1, 120));

        nav();
    }

    public void showMine() {
        base();

        root.addView(text("我的账户", 30, Color.WHITE));
        root.addView(text("套餐：高级套餐 300G/月", 21, Color.WHITE));
        root.addView(text("状态：有效", 20, Color.rgb(20, 210, 230)));
        root.addView(text("余额：¥0", 20, Color.WHITE));

        root.addView(text("\n设置", 24, Color.WHITE));
        root.addView(text("切换主题  ›\n订单记录  ›\n流量明细  ›\n邀请返利  ›\n通知与设置  ›", 21, Color.WHITE));

        Button service = btn("联系客服");
        service.setOnClickListener(v ->
                Toast.makeText(this, "客服微信：fengyevpn", Toast.LENGTH_LONG).show()
        );
        root.addView(service, new LinearLayout.LayoutParams(-1, 120));

        nav();
    }
}