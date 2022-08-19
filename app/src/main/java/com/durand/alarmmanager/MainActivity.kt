package com.durand.alarmmanager

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.durand.alarmmanager.databinding.ActivityMainBinding
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private lateinit var picker: MaterialTimePicker
    private lateinit var calendar: Calendar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createNotificationChannel()

        binding.selectTimeBtn.setOnClickListener {
            showTimePicker()
        }

        binding.setAlarmBtn.setOnClickListener {
            setAlarm()
        }

        binding.cancelAlarmBtn.setOnClickListener {

        }

    }

    private fun setAlarm() {

    }

    private fun showTimePicker() {
        picker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_12H)
            .setHour(12)
            .setMinute(0)
            .setTitleText("Select Alarm Time")
            .build()

        picker.show(supportFragmentManager, "josue")
        picker.addOnPositiveButtonClickListener {
            if (picker.hour > 12) {
                binding.selectedTime.text =
                    String.format("%02d", picker.hour - 12) + " : " + String.format(
                        "%02d",
                        picker.minute
                    ) + "PM"
            } else {
                binding.selectedTime.text =
                    String.format("%02d", picker.hour) + " : " + String.format(
                        "%02d",
                        picker.minute
                    ) + "AM"
            }
            calendar = Calendar.getInstance()
            calendar[Calendar.HOUR_OF_DAY] = picker.hour
            calendar[Calendar.MINUTE] = picker.minute
            calendar[Calendar.SECOND] = 0
            calendar[Calendar.MILLISECOND] = 0

        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name: CharSequence = "josue durand"
            val description = "Community Android"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel("josue", name, importance)
            channel.description = description
            val notificationManager = getSystemService(NotificationManager::class.java)

            notificationManager.createNotificationChannel(channel)
        }
    }
}