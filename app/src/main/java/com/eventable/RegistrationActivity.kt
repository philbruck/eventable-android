package com.eventable

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText

class RegistrationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        val registrationBtn : Button = findViewById(R.id.RegistrationBtn)
        val emailRegistrationTeEd : EditText = findViewById(R.id.emailRegistrationEdTe)
        val passwordRegistrationTeEd : EditText = findViewById(R.id.PasswordRegistrationEdTe)




        registrationBtn.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                val EmailRegistrationText : String = emailRegistrationTeEd.text.toString()
                val PasswordRegistrationText : String = passwordRegistrationTeEd.text.toString()

                Log.e("TAG", "$EmailRegistrationText")
                Log.e("TAG", "$PasswordRegistrationText")
            }
        })



    }
}