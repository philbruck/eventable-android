package com.eventable

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LogInActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        val registrationActivityBtn: Button = findViewById(R.id.registrationActivityBtn)
        val emailLogInEdTe: EditText = findViewById(R.id.emailLogInEdTe)
        val passwordLogInEdTe: EditText = findViewById(R.id.passwordLogInEdTe)
        val logInBtn: Button = findViewById(R.id.logInBtn)
        auth = FirebaseAuth.getInstance()



        registrationActivityBtn.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }

        logInBtn.setOnClickListener {
            if (emailLogInEdTe.text.trim().toString().isNotEmpty() || passwordLogInEdTe.text.trim()
                    .toString().isNotEmpty()
            ) {
                signInUser(
                    emailLogInEdTe.text.trim().toString(),
                    passwordLogInEdTe.text.trim().toString()
                )
            } else {
                Toast.makeText(this, "Input requured", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun signInUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    //Firebase registerd user
                    val firebaseUser: FirebaseUser = task.result!!.user!!


                    val intent = Intent(
                        this@LogInActivity,
                        DashboardActivity::class.java
                    )
                    intent.flags =
                        Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    intent.putExtra("user_id", firebaseUser.uid)
                    intent.putExtra("email_id", email)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(
                        this@LogInActivity,
                        task.exception!!.message.toString(),
                        Toast.LENGTH_SHORT
                    ).show()

                }
            }
    }
}




