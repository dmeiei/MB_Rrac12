package kr.ac.hallym.prac12

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kr.ac.hallym.prac12.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    lateinit var binding: ActivityMain2Binding
    lateinit var receiver: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main2)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        receiver = object :BroadcastReceiver(){
            override fun onReceive(p0: Context?, p1: Intent?) {
                when(p1?.action){
                    Intent.ACTION_SCREEN_ON -> Log.d("kkang","screen on")
                    Intent.ACTION_SCREEN_OFF -> Log.d("kkang","screen off")
                    Intent.ACTION_BATTERY_OKAY -> Log.d("kkang","ACTION_BATTERY_OKAY...")
                    Intent.ACTION_BATTERY_LOW -> Log.d("kkang","ACTION_BATTERY_LOW...")
                    Intent.ACTION_BATTERY_CHANGED -> Log.d("kkang","ACTION_BATTERY_CHANGED...")
                    Intent.ACTION_POWER_CONNECTED -> Log.d("kkang","ACTION_POWER_CONNECTED...")
                    Intent.ACTION_POWER_DISCONNECTED -> Log.d("kkang","ACTION_POWER_DISCONNECTED...")
                }
            }
        }

        val filter = IntentFilter().apply {
            addAction(Intent.ACTION_SCREEN_ON)
            addAction(Intent.ACTION_SCREEN_OFF)
            addAction(Intent.ACTION_BATTERY_OKAY)
            addAction(Intent.ACTION_BATTERY_LOW)
            addAction(Intent.ACTION_BATTERY_CHANGED)
            addAction(Intent.ACTION_POWER_CONNECTED)
            addAction(Intent.ACTION_POWER_DISCONNECTED)
        }
        registerReceiver(receiver,filter)

    }
}