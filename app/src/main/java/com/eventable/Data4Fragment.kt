package com.eventable

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.eventable.model.Answer
import com.eventable.model.Event
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_data4.*

class Data4Fragment : Fragment(R.layout.fragment_data4) {

    private lateinit var eventInfos: Array<String>


    private val args: Data4FragmentArgs by navArgs() //philbruck: autogenerire Klasse wegen dem safeArgs-Plug-In

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var auth = FirebaseAuth.getInstance()
        var user = auth.currentUser
        var uid = user?.uid
        val db = FirebaseFirestore.getInstance()
        var alreadyCreated = args.alreadyCreated
        var coundQuestions = args.coundQuestions

        eventInfos = Array(4) { "init" }

        continueBtn.setOnClickListener {
            if (questionEdTe.length() == 0) {
                Toast.makeText(
                    activity,
                    "Es dürfen keine leeren Fragen gestellt werden",
                    Toast.LENGTH_LONG
                ).show()
            } else {

                //Augustin: Überprüft, ob Event schon erstellt worden ist
                if (alreadyCreated == false) {
                    val events = Event(
                        args.eventInfos[4],
                        args.eventInfos[3],
                        uid.toString(),
                        args.eventId.toString(),
                        args.eventInfos[1],
                        args.eventInfos[0],
                        listOf("${questionEdTe.text}"),
                        args.eventInfos[2],
                        System.currentTimeMillis(),
                        listOf()
                    )
                    db.collection("events").document(args.eventId.toString()).set(events)
                        .addOnSuccessListener { Log.d(TAG, "Event wurde erstellt") }
                        .addOnFailureListener { Log.d(TAG, "Fehler beim Erstellen eines Events") }

                    Toast.makeText(
                        activity,
                        "Frage wurde erfolgreich hinzugefügt",
                        Toast.LENGTH_SHORT
                    ).show()

                    alreadyCreated = true;
                } else {
                    //Augustin: Wenn Event schon erstellt wurde, werden nur Fragen hinzugefügt
                    val data = db.collection("events").document(args.eventId.toString())
                    Toast.makeText(
                        activity,
                        "Frage wurde erfolgreich hinzugefügt",
                        Toast.LENGTH_SHORT
                    ).show()

                    data
                        .update("questions", FieldValue.arrayUnion("${questionEdTe.text}"))
                        .addOnSuccessListener { Log.d(TAG, "Frage wurde hinzugefügt") }
                        .addOnFailureListener { Log.d(TAG, "Fehler beim hinzufügen von Fragen") }
                }
                coundQuestions++

                val action_Data4ToSelf =
                    Data4FragmentDirections.actionData4FragmentSelf(
                        args.eventInfos,
                        args.eventId,
                        alreadyCreated,
                        coundQuestions
                    )
                findNavController().navigate(action_Data4ToSelf)
            }
        }

        // Um zu überprüfen, dass nicht mehr als 5 Fragen gestellt wurden
        if (coundQuestions >= 4) {
            continueBtn.visibility = View.INVISIBLE
            Toast.makeText(
                activity,
                "Es dürfen maximal 5 Fragen gestellt werden, du hast akutell 4",
                Toast.LENGTH_SHORT
            ).show()
        }

        //Um zu überprüfen, dass User mindestens eine Frage gestellt hat
        if (alreadyCreated == false) {
            finishBtn.visibility = View.INVISIBLE
        } else {
            finishBtn.visibility = View.VISIBLE
        }

        Log.e("Fragen", coundQuestions.toString())

        finishBtn.setOnClickListener {
            //Wenn keine Frage eingegeben wurde, wird nichts in Datenbank gespeichert
            if (questionEdTe.length() == 0) {
                val action_Data4ToHome =
                    Data4FragmentDirections.actionData4FragmentToHomeFragment()
                findNavController().navigate(action_Data4ToHome)
                Toast.makeText(
                    activity,
                    "Event wurde erfolgreich erstellt, \n Event-ID: ${args.eventId}",
                    Toast.LENGTH_LONG
                ).show()

            } else {
                // Wenn eine Frage eingegeben wurde, wird die Frage in Datenbank gespeichert
                val data = db.collection("events").document(args.eventId.toString())
                Toast.makeText(activity, "Frage wurde erfolgreich hinzugefügt", Toast.LENGTH_SHORT)
                    .show()

                data
                    .update("questions", FieldValue.arrayUnion("${questionEdTe.text}"))
                    .addOnSuccessListener { Log.d(TAG, "Event wurde erfolgreich erstellt") }
                    .addOnFailureListener { Log.d(TAG, "Fehler beim hinzufügen von Fragen") }

                val action_Data4ToHomeFragment =
                    Data4FragmentDirections.actionData4FragmentToHomeFragment()
                findNavController().navigate(action_Data4ToHomeFragment)
            }
        }
    }
}