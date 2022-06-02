package com.eventable

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth

class Hauptseite : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hauptseite)

        val userIDTextField : TextView = findViewById(R.id.UserTextField)
        val ausloggenBtn : Button = findViewById(R.id.logOutBtn)

        val userid = intent.getStringExtra("user_id")
        val emailId = intent.getStringExtra("email_id")

        userIDTextField.text = "User ID:  :: $userid"


        ausloggenBtn.setOnClickListener {
            //Logout from App
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this@Hauptseite, LogIn::class.java))
            finish()
        }










    }
}