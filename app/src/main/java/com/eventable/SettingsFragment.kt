package com.eventable

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : Fragment(R.layout.fragment_settings) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        LogOutSettingsBtn.setOnClickListener {
            //Augustin: Ausloggen des Benutzers
            Log.e(TAG, "Benutzer wurde erfoglreich ausgeloggt")
            startActivity(Intent(activity, LogInActivity::class.java))
            FirebaseAuth.getInstance().signOut()
        }
    }
}