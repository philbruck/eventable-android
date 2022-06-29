package com.eventable

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_invitation.*

class InvitationFragment : Fragment(R.layout.fragment_invitation){


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var auth = FirebaseAuth.getInstance()
        var user = auth.currentUser
        var uid = user?.uid
        val db = FirebaseFirestore.getInstance()

        var voteQuestion = 0

        joinEventBtn.setOnClickListener{
            //Augustin: Prüfen, ob Eingabe 6-stellig ist
            if(eventIdEdTe.length() != 6) {
                Toast.makeText(activity, "Du musst eine 6-stellige Zahl eingeben", Toast.LENGTH_LONG).show()
            }
            else {
                db.collection("events")
                    .whereEqualTo("event_id", eventIdEdTe.text.toString())
                    .get()
                    .addOnCompleteListener{ documents ->
                        //Augustin: Überprüfen, ob ein Event in der DB gefunden wurde
                        if(documents.result.size() == 0) {
                            Toast.makeText(activity, "Es wurde keine Veranstaltung gefunden", Toast.LENGTH_LONG).show()
                        }
                        else {
                            //Überpfüfen, ob schon jemand abgestimmt hat
                            db.collection("answers")
                                .whereEqualTo("uid", uid)
                                .whereEqualTo("event_id", eventIdEdTe.text.toString())
                                .get()
                                .addOnCompleteListener { documents ->
                                    if(documents.result.size() == 0) {
                                        //Nicht zweimal abgestimmt
                                        //Sprug zu den Fragen des eingegebenen Events
                                        val invitationCode = eventIdEdTe.text.toString()
                                        val action_InvitationToData4 =
                                            InvitationFragmentDirections.actionInvitationFragmentToData2Fragment(invitationCode, voteQuestion)
                                        findNavController().navigate(action_InvitationToData4)
                                    }
                                    else {
                                        // Mehrfach abgestimmt
                                        Toast.makeText(activity, "Du hast bereits abgestimmt", Toast.LENGTH_LONG).show()
                                    }
                                }
                                .addOnFailureListener{exeption ->
                                    Log.e(TAG, "Fehler bei Überprüfung, ob User schon bereits abgestimmt hat", exeption)
                                }
                        }
                    }
                    .addOnFailureListener{exeption ->
                        Log.e(TAG, "Error by join Event", exeption)
                    }

                    }
            }

        }
    }

