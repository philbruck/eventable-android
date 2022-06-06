package com.eventable

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RegistrationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)


        val emailregEdTe: EditText = findViewById(R.id.emailregEdTe)
        val passwordregEdTe: EditText = findViewById(R.id.passwordregEdTe)
        val registrationBtn: Button = findViewById(R.id.registrationBtn)
        val logInActivityBtn: Button = findViewById(R.id.logInActivityBtn)

        val db = Firebase.firestore


        logInActivityBtn.setOnClickListener {
            // Zurück auf die LogInActivity
            val intent = Intent(this, LogInActivity::class.java)
            startActivity(intent)
        }

        registrationBtn.setOnClickListener {
            when {
                // Wenn Email Field leer, dann Meldung
                TextUtils.isEmpty(emailregEdTe.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this@RegistrationActivity,
                        "Please enter email",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                // Wenn Password Field leer, dann Meldung
                TextUtils.isEmpty(passwordregEdTe.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this@RegistrationActivity,
                        "Please enter password",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {

                    val email: String = emailregEdTe.text.toString().trim { it <= ' ' }
                    val password: String = passwordregEdTe.text.toString().trim { it <= ' ' }

                    // Create an istance and create a register a user with email and password
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(
                            { task ->
                                //If the registration is successfully done
                                if (task.isSuccessful) {
                                    //Firebase registerd user
                                    val firebaseUser: FirebaseUser = task.result!!.user!!

                                    Toast.makeText(
                                        this@RegistrationActivity,
                                        "You are registered successfully",
                                        Toast.LENGTH_SHORT
                                    ).show()


                                    val intent = Intent(
                                        this@RegistrationActivity,
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
                                        this@RegistrationActivity,
                                        task.exception!!.message.toString(),
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        )
                }
            }
        }












    }
}
