package kr.ac.hallym.prac12

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kr.ac.hallym.prac12.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button1.setOnClickListener {
            val intent = Intent(this, MyReceiver::class.java)
            sendBroadcast(intent)
        }

        val receiver = object : BroadcastReceiver(){
            override fun onReceive(p0: Context?, p1: Intent?) {
                Log.d("kkang", "registerReceiver's onReceive()...")
            }
        }

        val filter = IntentFilter("ACTION_RECEIVER")
        registerReceiver(receiver,filter)

        binding.button2.setOnClickListener {
            val intent = Intent("ACTION_RECEIVER")
            sendBroadcast(intent)
        }
    }
}