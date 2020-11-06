package com.example.boradcast_receiver_demo3

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    var tvvalue : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        LocalBroadcastManager.getInstance(this).registerReceiver(messageeceiver, IntentFilter("custom-action-local-broadcast"))
        tvvalue = findViewById(R.id.tvtext)
        tvtext.setOnClickListener {
            var intent = Intent("custom-action-local-broadcast")
            intent.putExtra("name","Local Broadcast Demo")
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
        }
    }

    private val messageeceiver: BroadcastReceiver = object : BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent) {
            val message = intent.getStringExtra("name")
            tvtext.text = message
        }
    }
}