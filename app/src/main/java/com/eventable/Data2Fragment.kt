package com.eventable

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.eventable.model.Answer
import com.eventable.model.Event
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_data2.*


class Data2Fragment : Fragment(R.layout.fragment_data2){

    private lateinit var firestoneDb: FirebaseFirestore

    private val args : Data2FragmentArgs by navArgs() //philbruck: autogenerire Klasse wegen dem safeArgs-Plug-In
    private lateinit var events: MutableList<Event>
    private lateinit var answers: Answer


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var countQuestion = 0
        var voteQuestion = args.voteQuestion
        var invitationCode = 0
        val db = FirebaseFirestore.getInstance()
        var auth = FirebaseAuth.getInstance()
        var user = auth.currentUser
        var uid = user?.uid


        firestoneDb = FirebaseFirestore.getInstance()
        //Datenquelle wird erstellt
        events = mutableListOf()


        //To-do:
        //EventInfos sollen angezeigt werden ->Erledigt
        //Fragen sollen angezeigt und ausgefüllt werden können -> ERledigt
        //Counter wie viel Fragen noch kommen -> ERledigt
        //Unter Events soll bei Votes die uid agespeichert werden
        //Antworten sollen unter Antworten gespeichert werden

        //Augustin: Abfrage von EventInfos
        val eventsReference = firestoneDb.collection("events")
        eventsReference
            .whereEqualTo("event_id", args.invitationCode)
            .addSnapshotListener { snapshot, exception ->
                if (exception != null || snapshot == null) {
                    Log.e(ContentValues.TAG, "Exception when querying events", exception)
                    return@addSnapshotListener
                }

                //Speichert die Objekte in eine Liste
                var eventList = snapshot.toObjects(Event::class.java)
                events.clear()
                events.addAll(eventList)

                for (event in eventList) {
                    nameData2HolderTV.text = event.name
                    locationData3HolderTV.text = event.location
                    dateData3HolderTV.text = event.date
                    starttimeData3HolderTV.text = "${event.starttime} Uhr"
                    countQuestionData2HolderTV.text = "${voteQuestion+1} /  ${event.questions?.size.toString()}"
                    descriptionData3HolderTV.text = event.description
                    questionData2EdTe.text = event.questions?.get(voteQuestion)

                    voteQuestion++
                    countQuestion = event.questions?.size!!
                }
            }

        answerBtn.setOnClickListener {
                // Es gibt noch offene Fragen
                //RadioButtons auswerten
                if (yesRB.isChecked) {
                    Log.i("Votes3", voteQuestion.toString())
                    answers = Answer(uid.toString(), "ja", args.invitationCode, voteQuestion-1)
                } else if (noRB.isChecked) {
                    answers = Answer(uid.toString(), "nein", args.invitationCode, voteQuestion-1)
                } else {
                    Toast.makeText(
                        activity,
                        "Bitte wähle eine der Antworten aus",
                        Toast.LENGTH_LONG
                    ).show()
                }

                if (yesRB.isChecked || noRB.isChecked) {

                    db.collection("answers").document().set(answers)
                        .addOnSuccessListener {
                            Log.d(
                                ContentValues.TAG,
                                "Antwort wurde abgegeben"
                            )
                        }
                        .addOnFailureListener {
                            Log.d(ContentValues.TAG, "Fehler beim Antworten auf eine Frage")
                        }
                }

            val action_Data2ToSelf =
                Data2FragmentDirections.actionData2FragmentSelf(args.invitationCode.toString(), voteQuestion)
            findNavController().navigate(action_Data2ToSelf)

            if(countQuestion == voteQuestion) {
                //Augustin: Alle Fragen wurden beantwortet
                Toast.makeText(
                    activity,
                    "Du hast alle Fragen erfolgreich beantwortet",
                    Toast.LENGTH_LONG
                ).show()

                Log.i("Code", "${args.invitationCode}")

                //Augustin: Hier funktioniert das nicht, wirft mir immer einen Fehler aus
                val data1 = db.collection("events").document(args.invitationCode)
/*                data1
                    .update("votes",  FieldValue.arrayUnion("$uid"))
                    .addOnSuccessListener { Log.i(TAG, "Antwort wurde hinzugefügt") }
                    .addOnFailureListener { Log.i(TAG, "Antwort konnte nicht hinzugefügt werden")}*/

                val action_Data2ToHomeFragment =
                    Data2FragmentDirections.actionData2FragmentToHomeFragment()
                findNavController().navigate(action_Data2ToHomeFragment)
            }


        }


    }

}







