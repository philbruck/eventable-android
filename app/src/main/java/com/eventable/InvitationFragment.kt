package com.eventable

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_invitation.*

class InvitationFragment : Fragment(R.layout.fragment_invitation){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        joinEventBtn.setOnClickListener{
            //Augustin: Prüfen, ob Eingabe 6-stellig ist
            if(eventIdEdTe.length() != 6) {
                Toast.makeText(activity, "Du musst eine 6-stellige Zahl eingeben", Toast.LENGTH_LONG).show()
            }
            else {
                val db = FirebaseFirestore.getInstance()
                db.collection("events")
                    .whereEqualTo("event_id", eventIdEdTe.text.toString())
                    .get()
                    .addOnCompleteListener{ documents ->
                        //Augustin: Überprüfen, ob ein Event in der DB gefunden wurde
                        if(documents.result.size() == 0) {
                            Toast.makeText(activity, "Es wurde keine Veranstaltung gefunden", Toast.LENGTH_LONG).show()
                        }
                        else {
                            //Augustin: Hier sollte zu den Fragen eines Events gesprungen werden
                            Toast.makeText(activity, "Jetzt weiterspringen zu den Fragen des Events", Toast.LENGTH_LONG).show()
                        }
                    }
                    .addOnFailureListener{exeption ->
                        Log.e(TAG, "Error by join Event", exeption)
                    }




                    }



            }

        }


    }

