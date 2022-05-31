package com.eventable

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView

class Hauptseite : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hauptseite)

        val userIDTextField : TextView = findViewById(R.id.UserTextField)

        val userid = intent.getStringExtra("user_id")
        val emailId = intent.getStringExtra("email_id")

        userIDTextField.text = "User ID:  :: $userid"









    }
}