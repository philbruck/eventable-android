package com.eventable

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth

class DashboardActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        
        val UserIDTeVi : TextView = findViewById(R.id.UserIDTeVi)
        val logOutButton : Button = findViewById(R.id.logOutBtn)

        val userid = intent.getStringExtra("user_id")
        val emailId = intent.getStringExtra("email_id")

        UserIDTeVi.text = "User ID:  :: $userid"

        logOutButton.setOnClickListener{
            //Logout from App
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this@DashboardActivity, LogInActivity::class.java))
            finish()
        }







    }
}