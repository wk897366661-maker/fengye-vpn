package com.fengye.vpn

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val text = TextView(this)
        text.text = "🍁 枫叶 VPN\n安全放心"
        text.textSize = 28f

        setContentView(text)
    }
}