package com.eventable

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class LogIn : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        val loginBtn : Button = findViewById(R.id.LogInBtn)
        val emailTextField : EditText = findViewById(R.id.EmailTextField)
        val passwordTextField : EditText = findViewById(R.id.PasswordTextField)
        val email2TextView : TextView = findViewById(R.id.email2TextView)
        val password2TextView : TextView = findViewById(R.id.password2TextView)
        val emailregistrationTextField : TextView = findViewById(R.id.emailregistrationtextField)
        val passwordregistrationTextField : TextView = findViewById(R.id.passwordregistrationTextField)
        val anzeigenbtn : Button = findViewById(R.id.AnzeigenBtn)

        val db = Firebase.firestore



        anzeigenbtn.setOnClickListener{
            when{
                // Wenn Email Field leer, dann Meldung
                TextUtils.isEmpty(emailregistrationTextField.text.toString().trim{ it <= ' ' }) -> {
                    Toast.makeText(
                        this@LogIn,
                        "Please enter email",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                // Wenn Password Field leer, dann Meldung
                TextUtils.isEmpty(passwordregistrationTextField.text.toString().trim{ it <= ' '}) -> {
                    Toast.makeText(
                        this@LogIn,
                        "Please enter password",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {

                    val email: String = emailregistrationTextField.text.toString().trim{it <= ' '}
                    val password: String = passwordregistrationTextField.text.toString().trim { it <= ' '}

                // Create an istance and create a register a user with email and password
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(
                        { task ->
                            //If the registration is successfully done
                            if (task.isSuccessful) {
                                //Firebase registerd user
                                val firebaseUser: FirebaseUser = task.result!!.user!!

                                Toast.makeText(
                                    this@LogIn,
                                    "You are registered successfully",
                                    Toast.LENGTH_SHORT
                                ).show()

                                val intent = Intent(this@LogIn, Hauptseite::class.java)
                                intent.flags =
                                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                intent.putExtra("user_id", firebaseUser.uid)
                                intent.putExtra("email_id", email)
                                startActivity(intent)
                                finish()
                            }else{
                                Toast.makeText(
                                    this@LogIn,
                                    task.exception!!.message.toString(),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }


                        }
                    )

                }














            }

        }







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