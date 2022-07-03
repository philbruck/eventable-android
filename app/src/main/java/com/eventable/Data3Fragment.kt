package com.eventable

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.eventable.model.Answer
import com.eventable.model.Event
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_data3.*

class Data3Fragment : Fragment(R.layout.fragment_data3) {

    private val args: Data3FragmentArgs by navArgs() //philbruck: autogenerire Klasse wegen dem safeArgs-Plug-In
    private lateinit var firestoneDb: FirebaseFirestore
    private lateinit var events: MutableList<Event>
    private lateinit var answers: MutableList<Answer>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var auth = FirebaseAuth.getInstance()
        var user = auth.currentUser
        var uid = user?.uid
        firestoneDb = FirebaseFirestore.getInstance()
        var coundQuestions = 0

        events = mutableListOf()
        answers = mutableListOf()

        //Augustin: Abfrage der EventInfos
        val eventsReference = firestoneDb.collection("events")
        eventsReference
            .whereEqualTo("event_id", args.eventId)
            .addSnapshotListener { snapshot, exception ->
                if (exception != null || snapshot == null) {
                    Log.e(ContentValues.TAG, "Exception when querying events", exception)
                    return@addSnapshotListener
                }
                //Speichert die Objekte in eine Liste
                var eventList = snapshot.toObjects(Event::class.java)
                events.clear()
                events.addAll(eventList)
                //adapter.notifyDataSetChanged()
                Log.i("EventList: ", eventList.toString())

                for (event in eventList) {
                    Log.i(ContentValues.TAG, "Event ${event}")

                    //EventInfos werden abgefragt
                    NameData3HolderTV.text = event.name
                    locationData3HolderTV.text = event.location
                    dateData3HolderTV.text = event.date
                    starttimeData3HolderTV.text = "${event.starttime} Uhr"
                    descriptionData3HolderTV.text = event.description


                    //Abfrage je nachdem wie viele Fragen gestellt worden sind
                    coundQuestions = event.questions?.size!!
                    when (coundQuestions) {
                        0 -> Log.e(ContentValues.TAG, "Keine Fragen vorhanden")
                        1 -> {
                            question1Data3HolderTV.text = event.questions?.get(0)
                            card_layout_question2Data3.visibility = View.INVISIBLE
                            card_layout_question3Data3.visibility = View.INVISIBLE
                            card_layout_question4Data3.visibility = View.INVISIBLE
                            card_layout_question5Data3.visibility = View.INVISIBLE
                        }

                        2 -> {
                            question1Data3HolderTV.text = event.questions?.get(0)
                            question2Data3HolderTV.text = event.questions?.get(1)

                            card_layout_question3Data3.visibility = View.INVISIBLE
                            card_layout_question4Data3.visibility = View.INVISIBLE
                            card_layout_question5Data3.visibility = View.INVISIBLE
                        }
                        3 -> {
                            question1Data3HolderTV.text = event.questions?.get(0)
                            question2Data3HolderTV.text = event.questions?.get(1)
                            question3Data3HolderTV.text = event.questions?.get(2)

                            card_layout_question4Data3.visibility = View.INVISIBLE
                            card_layout_question5Data3.visibility = View.INVISIBLE
                        }
                        4 -> {
                            question1Data3HolderTV.text = event.questions?.get(0)
                            question2Data3HolderTV.text = event.questions?.get(1)
                            question3Data3HolderTV.text = event.questions?.get(2)
                            question4Data3HolderTV.text = event.questions?.get(3)

                            card_layout_question5Data3.visibility = View.INVISIBLE
                        }
                        5 -> {
                            question1Data3HolderTV.text = event.questions?.get(0)
                            question2Data3HolderTV.text = event.questions?.get(1)
                            question3Data3HolderTV.text = event.questions?.get(2)
                            question4Data3HolderTV.text = event.questions?.get(3)
                            question5Data3HolderTV.text = event.questions?.get(4)
                        }
                    }
                }
            }

        // Ruft die Antworten für Fragen ab
        val answersReference = firestoneDb.collection("answers")
        answersReference
            .whereEqualTo("event_id", args.eventId)
            .whereEqualTo("uid", uid)
            .addSnapshotListener { snapshot, exception ->
                if (exception != null || snapshot == null) {
                    Log.e(ContentValues.TAG, "Exception when querying answers", exception)
                    return@addSnapshotListener
                }
                //Speichert die Objekte in eine Liste
                var answerList = snapshot.toObjects(Answer::class.java)
                answers.clear()
                answers.addAll(answerList)

                for (answer in answerList) {
                    Log.i("Anworten:", "Answers ${answer}")

                    //Augustin: Ordnet die abgegebenen Antworten den Fragen zu
                    when (answer.questionIndex) {
                        0 -> {
                            question1AnswerData3HolderTV.text = answer.answer
                        }
                        1 -> {
                            question2AnswerData3HolderTV.text = answer.answer
                        }
                        2 -> {
                            question3AnswerData3HolderTV.text = answer.answer
                        }
                        3 -> {
                            question4AnswerData3HolderTV.text = answer.answer
                        }
                        4 -> {
                            question5AnswerData3HolderTV.text = answer.answer
                        }
                    }
                }
            }
    }
}
