package com.fengye.vpn;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        webView = new WebView(this);
        setContentView(webView);

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);

        webView.setWebViewClient(new WebViewClient());

        webView.loadDataWithBaseURL(
                null,
                getHtml(),
                "text/html",
                "UTF-8",
                null
        );
    }

    private String getHtml() {
        return """
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>枫叶 VPN</title>

<style>
* {
    box-sizing: border-box;
    -webkit-tap-highlight-color: transparent;
}

body {
    margin: 0;
    padding: 0;
    font-family: Arial, "Microsoft YaHei", sans-serif;
    background: radial-gradient(circle at top, #103a73 0%, #06172f 45%, #020812 100%);
    color: white;
    overflow: hidden;
}

.app {
    width: 100vw;
    height: 100vh;
    position: relative;
    overflow: hidden;
}

.page {
    width: 100vw;
    height: 100vh;
    padding: 36px 22px 100px;
    display: none;
    overflow-y: auto;
}

.page.active {
    display: block;
}

.header {
    text-align: center;
    margin-top: 12px;
}

.logo-title {
    font-size: 30px;
    font-weight: 800;
    letter-spacing: 2px;
}

.subtitle {
    color: #77dfff;
    font-size: 15px;
    margin-top: 8px;
}

.status {
    text-align: center;
    margin-top: 20px;
    font-size: 16px;
    color: #28e7ff;
}

.center-area {
    display: flex;
    justify-content: center;
    align-items: center;
    margin-top: 36px;
}

.outer-ring {
    width: 245px;
    height: 245px;
    border-radius: 50%;
    border: 3px solid rgba(38, 220, 255, 0.85);
    box-shadow:
        0 0 25px rgba(38, 220, 255, 0.7),
        inset 0 0 25px rgba(38, 220, 255, 0.25);
    display: flex;
    justify-content: center;
    align-items: center;
}

.inner-ring {
    width: 195px;
    height: 195px;
    border-radius: 50%;
    border: 2px solid rgba(255, 139, 42, 0.85);
    box-shadow:
        0 0 28px rgba(255, 116, 30, 0.75),
        inset 0 0 20px rgba(255, 116, 30, 0.2);
    display: flex;
    justify-content: center;
    align-items: center;
}

.leaf {
    font-size: 92px;
    filter: drop-shadow(0 0 18px rgba(255, 90, 20, 0.9));
    transition: 0.25s;
}

.disconnected .inner-ring {
    border-color: #777;
    box-shadow: 0 0 15px rgba(120,120,120,0.45), inset 0 0 20px rgba(120,120,120,0.2);
}

.disconnected .leaf {
    filter: grayscale(1);
    opacity: 0.65;
}

.node-card {
    margin-top: 34px;
    padding: 18px;
    border-radius: 22px;
    background: rgba(9, 39, 78, 0.92);
    border: 1px solid rgba(54, 178, 255, 0.35);
    box-shadow: 0 10px 30px rgba(0,0,0,0.35);
}

.card-title {
    font-size: 17px;
    color: #8bdfff;
    margin-bottom: 12px;
}

.node-line {
    display: flex;
    justify-content: space-between;
    font-size: 16px;
    padding: 9px 0;
    border-bottom: 1px solid rgba(255,255,255,0.08);
}

.node-line:last-child {
    border-bottom: none;
}

.quick-grid {
    margin-top: 22px;
    display: grid;
    grid-template-columns: repeat(2, 1fr);
    gap: 14px;
}

.quick-btn {
    background: linear-gradient(135deg, #0a4d7a, #092445);
    border: 1px solid rgba(84, 206, 255, 0.45);
    border-radius: 18px;
    padding: 16px;
    text-align: center;
    color: white;
    font-weight: 700;
    box-shadow: 0 6px 18px rgba(0,0,0,0.35);
}

.section-title {
    font-size: 30px;
    font-weight: 800;
    text-align: center;
    margin-top: 12px;
    margin-bottom: 18px;
}

.vip-card {
    padding: 20px;
    border-radius: 24px;
    background: linear-gradient(135deg, rgba(22,92,155,0.95), rgba(7,34,75,0.95));
    border: 1px solid rgba(100, 213, 255, 0.42);
    box-shadow: 0 10px 35px rgba(0,0,0,0.45);
    margin-bottom: 18px;
}

.vip-name {
    font-size: 21px;
    font-weight: 800;
}

.vip-info {
    color: #c5def7;
    font-size: 14px;
    margin-top: 10px;
    line-height: 1.8;
}

.progress {
    width: 100%;
    height: 10px;
    background: rgba(255,255,255,0.15);
    border-radius: 20px;
    overflow: hidden;
    margin-top: 14px;
}

.progress-bar {
    width: 18%;
    height: 100%;
    background: linear-gradient(90deg, #22e0ff, #ff8c28);
}

.plan {
    padding: 18px;
    border-radius: 22px;
    margin-bottom: 14px;
    background: rgba(8, 35, 75, 0.95);
    border: 1px solid rgba(72, 188, 255, 0.35);
    box-shadow: 0 8px 26px rgba(0,0,0,0.35);
}

.plan.hot {
    background: linear-gradient(135deg, rgba(13, 78, 122, 0.98), rgba(11, 40, 83, 0.98));
    border: 1px solid rgba(255, 150, 45, 0.7);
}

.plan-top {
    display: flex;
    justify-content: space-between;
    align-items: center;
}

.plan-name {
    font-size: 20px;
    font-weight: 800;
}

.price {
    font-size: 22px;
    color: #ff9b38;
    font-weight: 900;
}

.plan-desc {
    margin-top: 10px;
    color: #c9def7;
    font-size: 14px;
    line-height: 1.6;
}

.buy-btn {
    margin-top: 14px;
    width: 100%;
    padding: 13px;
    border-radius: 18px;
    background: linear-gradient(90deg, #1bdcff, #1677ff);
    text-align: center;
    font-weight: 800;
    color: white;
}

.profile-card {
    padding: 20px;
    border-radius: 24px;
    background: linear-gradient(135deg, rgba(8,44,92,0.96), rgba(8,28,58,0.96));
    border: 1px solid rgba(75, 201, 255, 0.35);
    margin-bottom: 16px;
    box-shadow: 0 8px 26px rgba(0,0,0,0.35);
}

.profile-title {
    font-size: 22px;
    font-weight: 800;
}

.profile-row {
    display: flex;
    justify-content: space-between;
    padding: 13px 0;
    border-bottom: 1px solid rgba(255,255,255,0.08);
    color: #dcecff;
}

.profile-row:last-child {
    border-bottom: none;
}

.service {
    margin-top: 12px;
    padding: 16px;
    border-radius: 18px;
    background: rgba(18, 84, 130, 0.75);
    text-align: center;
    color: #ffffff;
    font-weight: 800;
}

.bottom-nav {
    position: fixed;
    left: 0;
    bottom: 0;
    width: 100vw;
    height: 78px;
    background: rgba(3, 17, 38, 0.96);
    display: flex;
    border-top: 1px solid rgba(61, 202, 255, 0.25);
    box-shadow: 0 -8px 26px rgba(0,0,0,0.45);
}

.nav-item {
    flex: 1;
    text-align: center;
    padding-top: 10px;
    color: #7ea8c9;
    font-size: 13px;
}

.nav-item .icon {
    font-size: 22px;
    margin-bottom: 4px;
}

.nav-item.active {
    color: #25dfff;
    font-weight: 800;
}

.tip {
    color: #8baecb;
    text-align: center;
    font-size: 13px;
    margin-top: 18px;
}
</style>
</head>

<body>
<div class="app">

    <div id="home" class="page active">
        <div class="header">
            <div class="logo-title">枫叶 VPN</div>
            <div class="subtitle">安全放心 · 全球高速专线</div>
        </div>

        <div id="status" class="status">● 已断开</div>

        <div class="center-area">
            <div id="ringBox" class="outer-ring disconnected" onclick="toggleConnect()">
                <div class="inner-ring">
                    <div class="leaf">🍁</div>
                </div>
            </div>
        </div>

        <div class="tip">点击枫叶连接 / 断开</div>

        <div class="node-card">
            <div class="card-title">当前线路</div>
            <div class="node-line"><span>🇯🇵 日本01 · VIP专线</span><span>45ms</span></div>
            <div class="node-line"><span>🇺🇸 芝加哥01 · AI专线</span><span>158ms</span></div>
        </div>

        <div class="quick-grid">
            <div class="quick-btn" onclick="openSite('https://chat.openai.com')">ChatGPT</div>
            <div class="quick-btn" onclick="openSite('https://youtube.com')">YouTube</div>
            <div class="quick-btn" onclick="openSite('https://netflix.com')">Netflix</div>
            <div class="quick-btn" onclick="openSite('https://telegram.org')">Telegram</div>
        </div>
    </div>

    <div id="shop" class="page">
        <div class="section-title">套餐中心</div>

        <div class="vip-card">
            <div class="vip-name">高级套餐【每月300G】</div>
            <div class="vip-info">
                已用 2.49GB　剩余 297.51GB<br>
                到期时间：2026年5月23日
            </div>
            <div class="progress"><div class="progress-bar"></div></div>
        </div>

        <div class="plan">
            <div class="plan-top"><div class="plan-name">月卡套餐</div><div class="price">¥19.9</div></div>
            <div class="plan-desc">300G 高速流量，适合短期体验。</div>
            <div class="buy-btn" onclick="contact()">立即购买</div>
        </div>

        <div class="plan hot">
            <div class="plan-top"><div class="plan-name">季卡套餐 · 主推</div><div class="price">¥49.9</div></div>
            <div class="plan-desc">900G 高速流量，VIP专线优先，性价比最高。</div>
            <div class="buy-btn" onclick="contact()">立即购买</div>
        </div>

        <div class="plan">
            <div class="plan-top"><div class="plan-name">半年卡</div><div class="price">¥89.9</div></div>
            <div class="plan-desc">1800G 高速流量，适合稳定长期用户。</div>
            <div class="buy-btn" onclick="contact()">立即购买</div>
        </div>

        <div class="plan">
            <div class="plan-top"><div class="plan-name">年卡套餐</div><div class="price">¥169</div></div>
            <div class="plan-desc">3600G 高速流量，全年稳定使用。</div>
            <div class="buy-btn" onclick="contact()">立即购买</div>
        </div>

        <div class="plan hot">
            <div class="plan-top"><div class="plan-name">AI 专线</div><div class="price">¥39.9/月</div></div>
            <div class="plan-desc">ChatGPT / Claude / Gemini 专线优化。</div>
            <div class="buy-btn" onclick="contact()">立即购买</div>
        </div>

        <div class="plan">
            <div class="plan-top"><div class="plan-name">家庭共享年卡</div><div class="price">¥299</div></div>
            <div class="plan-desc">5000G 多设备共享，适合家庭和小团队。</div>
            <div class="buy-btn" onclick="contact()">立即购买</div>
        </div>
    </div>

    <div id="mine" class="page">
        <div class="section-title">我的账户</div>

        <div class="profile-card">
            <div class="profile-title">👑 VIP 高级会员</div>
            <div class="vip-info">
                用户ID：10086<br>
                套餐：高级套餐 300G/月<br>
                状态：有效
            </div>
        </div>

        <div class="profile-card">
            <div class="profile-row"><span>余额</span><span>¥0</span></div>
            <div class="profile-row"><span>已用流量</span><span>2.49GB</span></div>
            <div class="profile-row"><span>剩余流量</span><span>297.51GB</span></div>
            <div class="profile-row"><span>到期时间</span><span>2026-05-23</span></div>
        </div>

        <div class="profile-card">
            <div class="profile-row"><span>订单记录</span><span>›</span></div>
            <div class="profile-row"><span>流量明细</span><span>›</span></div>
            <div class="profile-row"><span>邀请返利</span><span>›</span></div>
            <div class="profile-row"><span>代理制度</span><span>›</span></div>
            <div class="profile-row"><span>我的团队</span><span>›</span></div>
            <div class="profile-row"><span>通知与设置</span><span>›</span></div>
            <div class="profile-row"><span>关于我们</span><span>V1.0.0</span></div>
        </div>

        <div class="service" onclick="contact()">联系客服 QQ：270294959</div>
    </div>

    <div class="bottom-nav">
        <div id="navHome" class="nav-item active" onclick="showPage('home')">
            <div class="icon">🏠</div>首页
        </div>
        <div id="navShop" class="nav-item" onclick="showPage('shop')">
            <div class="icon">🛒</div>商城
        </div>
        <div id="navMine" class="nav-item" onclick="showPage('mine')">
            <div class="icon">👤</div>我的
        </div>
    </div>

</div>

<script>
let connected = false;

function showPage(page) {
    document.getElementById('home').classList.remove('active');
    document.getElementById('shop').classList.remove('active');
    document.getElementById('mine').classList.remove('active');

    document.getElementById(page).classList.add('active');

    document.getElementById('navHome').classList.remove('active');
    document.getElementById('navShop').classList.remove('active');
    document.getElementById('navMine').classList.remove('active');

    if (page === 'home') document.getElementById('navHome').classList.add('active');
    if (page === 'shop') document.getElementById('navShop').classList.add('active');
    if (page === 'mine') document.getElementById('navMine').classList.add('active');
}

function toggleConnect() {
    connected = !connected;

    const ring = document.getElementById('ringBox');
    const status = document.getElementById('status');

    if (connected) {
        ring.classList.remove('disconnected');
        status.innerText = '● 已连接 · 智能加速中';
        status.style.color = '#28e7ff';
    } else {
        ring.classList.add('disconnected');
        status.innerText = '● 已断开';
        status.style.color = '#999999';
    }
}

function contact() {
    alert('客服QQ：270294959');
}

function openSite(url) {
    window.location.href = url;
}
</script>

</body>
</html>
""";
    }
}