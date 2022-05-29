package com.eventable

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.e("TAG", "test msg")



        val button : Button = findViewById(R.id.button) //Macht den Button mit der Klasse bekannt

        val intent = Intent(this, LogInActivity::class.java) //Man benötigt Intent für Wechsel auf andere Activity
        startActivity(intent) //Start der Activity


    }
}