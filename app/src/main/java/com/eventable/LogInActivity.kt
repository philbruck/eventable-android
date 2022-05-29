package com.eventable

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button

class LogInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        val LogInBtn : Button = findViewById(R.id.LogInBtn) //Macht den Button mit der Klasse bekannt
        val RegistrationBtn : Button = findViewById(R.id.RegistrationLogInBtn)

        val goToRegistration = Intent(this, RegistrationActivity::class.java) //Man benötigt Intent für Wechsel auf andere Activity


        RegistrationBtn.setOnClickListener(object : View.OnClickListener {
            override fun onClick (v: View?) {
                startActivity(goToRegistration)
            }
        })







    }
}