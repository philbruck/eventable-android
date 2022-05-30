package com.eventable

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class LogIn : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        val loginBtn : Button = findViewById(R.id.LogInBtn)
        val emailTextField : EditText = findViewById(R.id.EmailTextField)
        val passwordTextField : EditText = findViewById(R.id.PasswordTextField)

        val db = Firebase.firestore


        loginBtn.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                val EmailRegistrationText : String = emailTextField.text.toString()
                val PasswordRegistrationText : String = passwordTextField.text.toString()

                Log.e("TAG", "$EmailRegistrationText")
                Log.e("TAG", "$PasswordRegistrationText")

                val user = hashMapOf(
                    "first" to "$EmailRegistrationText",
                    "last" to "$PasswordRegistrationText",
                )

                db.collection("users")
                    .add(user)
                    .addOnSuccessListener { documentReference ->
                        Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                    }
                    .addOnFailureListener { e ->
                        Log.w(TAG, "Error adding document", e)
                    }







            }
        })




    }
}