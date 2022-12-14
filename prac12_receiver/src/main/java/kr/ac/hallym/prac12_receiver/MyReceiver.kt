package kr.ac.hallym.prac12_receiver

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import java.nio.file.attribute.AclEntry.Builder

class MyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        Log.d("kkang","My Receiver...")
        val manager = context.getSystemService(AppCompatActivity.NOTIFICATION_SERVICE)as NotificationManager
        val builder: NotificationCompat.Builder

        //알림설정
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            //번전 26이상
            val channelId = "one-channel"
            val channelName = "My Channel One"
            val channel = NotificationChannel(channelId,channelName,
            NotificationManager.IMPORTANCE_DEFAULT).apply {
                //채널에 다양한 정보 설정
                description = "My Channel One Description"
                setShowBadge(true)
                val uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
                val audioAttributes = AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_ALARM)
                    .build()
                setSound(uri, audioAttributes)
                enableVibration(true)
            }

            //채널을 NotificationManager에 등록
            manager.createNotificationChannel(channel)

            //채널을 이용하여 builder 생성
            builder = NotificationCompat.Builder(context,channelId)
        }
        else{
            //버전 26 미만
            builder = NotificationCompat.Builder(context)
        }

        builder.run{
            //알림의 기본 정보
            setSmallIcon(android.R.drawable.ic_notification_overlay)
            setWhen(System.currentTimeMillis())
            setContentTitle("배터리 정보 앱")
            setContentText("배터리 상태를 알려드립니다.")
        }

        manager.notify(11,builder.build())

    }
}