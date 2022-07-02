package com.eventable

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.eventable.model.Answer
import com.eventable.model.Event
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_data1.*

class Data1Fragment : Fragment(R.layout.fragment_data1) {

    private val args: Data1FragmentArgs by navArgs() //philbruck: autogenerire Klasse wegen dem safeArgs-Plug-In
    private lateinit var firestoneDb: FirebaseFirestore
    private lateinit var events: MutableList<Event>
    private lateinit var answers: MutableList<Answer>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var auth = FirebaseAuth.getInstance()
        var user = auth.currentUser
        var uid = user?.uid

        firestoneDb = FirebaseFirestore.getInstance()
        events = mutableListOf()
        answers = mutableListOf()

        val eventsReference = firestoneDb.collection("events")
        eventsReference
            .whereEqualTo("event_id", args.countEventId)
            .addSnapshotListener { snapshot, exception ->
                if (exception != null || snapshot == null) {
                    Log.e(TAG, "Exception when querying events", exception)
                    return@addSnapshotListener
                }
                //Speichert die Objekte in eine Liste
                var eventList = snapshot.toObjects(Event::class.java)
                events.clear()
                events.addAll(eventList)

                for (event in eventList) {
                    Log.i(TAG, "Event ${event}")

                    nameData1HolderTV.text = event.name
                    locationData1HolderTV.text = event.location
                    dateData1HolderTV.text = event.date
                    starttimeData1HolderTV.text = "${event.starttime} Uhr"
                    eventIDData1HolderTV.text = event.eventId
                    descriptionData1HolderTV.text = event.description

                    //Augusin: Fügt die Fragen den entsprechenden Holdern zu und blendet nicht gebrauchte Cards aus
                    var coundquestions = event.questions?.size?.toInt()
                    when (coundquestions) {
                        0 -> Log.e(TAG, "Keine Fragen vorhanden")
                        1 -> {
                            question1Data1HolderTV.text = event.questions?.get(0)
                            card_layout_question2.visibility = View.INVISIBLE
                            card_layout_question3.visibility = View.INVISIBLE
                            card_layout_question4.visibility = View.INVISIBLE
                            card_layout_question5.visibility = View.INVISIBLE
                        }
                        2 -> {
                            question1Data1HolderTV.text = event.questions?.get(0)
                            question2Data1HolderTV.text = event.questions?.get(1)

                            card_layout_question3.visibility = View.INVISIBLE
                            card_layout_question4.visibility = View.INVISIBLE
                            card_layout_question5.visibility = View.INVISIBLE
                        }
                        3 -> {
                            question1Data1HolderTV.text = event.questions?.get(0)
                            question2Data1HolderTV.text = event.questions?.get(1)
                            question3Data1HolderTV.text = event.questions?.get(2)

                            card_layout_question4.visibility = View.INVISIBLE
                            card_layout_question5.visibility = View.INVISIBLE
                        }
                        4 -> {
                            question1Data1HolderTV.text = event.questions?.get(0)
                            question2Data1HolderTV.text = event.questions?.get(1)
                            question3Data1HolderTV.text = event.questions?.get(2)
                            question4Data1HolderTV.text = event.questions?.get(3)

                            card_layout_question5.visibility = View.INVISIBLE
                        }
                        5 -> {
                            question1Data1HolderTV.text = event.questions?.get(0)
                            question2Data1HolderTV.text = event.questions?.get(1)
                            question3Data1HolderTV.text = event.questions?.get(2)
                            question4Data1HolderTV.text = event.questions?.get(3)
                            question5Data1HolderTV.text = event.questions?.get(4)
                        }
                    }
                }

                // Ruft die Antworten für Fragen ab
                val answersReference = firestoneDb.collection("answers")
                answersReference
                    .whereEqualTo("event_id", args.countEventId)
                    .addSnapshotListener { snapshot, exception ->
                        if (exception != null || snapshot == null) {
                            Log.e(TAG, "Exception when querying answers", exception)
                            return@addSnapshotListener
                        }
                        //Speichert die Objekte in eine Liste
                        var answerList = snapshot.toObjects(Answer::class.java)
                        answers.clear()
                        answers.addAll(answerList)

                        var countAnswer1 = Array(2) { 0 }
                        var countAnswer2 = Array(2) { 0 }
                        var countAnswer3 = Array(2) { 0 }
                        var countAnswer4 = Array(2) { 0 }
                        var countAnswer5 = Array(2) { 0 }
                        var countsum1 = 0
                        var countsum2 = 0
                        var countsum3 = 0
                        var countsum4 = 0
                        var countsum5 = 0

                        //countAnswer[0] ist ja und countAnswer[1] ist nein
                        for (answer in answerList) {
                            Log.i(TAG, "Answers ${answer}")

                            when (answer.questionIndex.toInt()) {
                                0 -> {
                                    if (answer.answer == "ja") {
                                        countAnswer1[0]++
                                    } else if (answer.answer == "nein") {
                                        countAnswer1[1]++
                                    }
                                }
                                1 -> {
                                    if (answer.answer == "ja") {
                                        countAnswer2[0]++
                                    } else if (answer.answer == "nein") {
                                        countAnswer2[1]++
                                    }
                                }
                                2 -> {
                                    if (answer.answer == "ja") {
                                        countAnswer3[0]++
                                    } else if (answer.answer == "nein") {
                                        countAnswer3[1]++
                                    }
                                }
                                3 -> {
                                    if (answer.answer == "ja") {
                                        countAnswer4[0]++
                                    } else if (answer.answer == "nein") {
                                        countAnswer4[1]++
                                    }
                                }
                                4 -> {
                                    if (answer.answer == "ja") {
                                        countAnswer5[0]++
                                    } else if (answer.answer == "nein") {
                                        countAnswer5[1]++
                                    }
                                }
                            }
                            question1SumData1HolderTV.text = countsum1.toString()
                            question2SumData1HolderTV.text = countsum2.toString()
                            question3SumData1HolderTV.text = countsum3.toString()
                            question4SumData1HolderTV.text = countsum4.toString()
                            question5SumData1HolderTV.text = countsum5.toString()
                        }
                        //Ordnet den Counterzählern die richtige Anzahl zu
                        question1YesData1HolderTV.text = "${countAnswer1[0]}"
                        question1NoData1HolderTV.text = "${countAnswer1[1]}"

                        question2YesData1HolderTV.text = "${countAnswer2[0]}"
                        question2NoData1HolderTV.text = "${countAnswer2[1]}"

                        question3YesData1HolderTV.text = "${countAnswer3[0]}"
                        question3NoData1HolderTV.text = "${countAnswer3[1]}"

                        question4YesData1HolderTV.text = "${countAnswer4[0]}"
                        question4NoData1HolderTV.text = "${countAnswer4[1]}"

                        question5YesData1HolderTV.text = "${countAnswer5[0]}"
                        question5NoData1HolderTV.text = "${countAnswer5[1]}"


                        //Berechnet die Summe aller Antworten pro Frage
                        countsum1 = countAnswer1[0] + countAnswer1[1]
                        question1SumData1HolderTV.text = "${countsum1}"

                        countsum2 = countAnswer2[0] + countAnswer2[1]
                        question2SumData1HolderTV.text = "${countsum2}"

                        countsum3 = countAnswer3[0] + countAnswer3[1]
                        question3SumData1HolderTV.text = "${countsum3}"

                        countsum4 = countAnswer4[0] + countAnswer4[1]
                        question4SumData1HolderTV.text = "${countsum4}"

                        countsum5 = countAnswer5[0] + countAnswer5[1]
                        question5SumData1HolderTV.text = "${countsum5}"
                    }
            }
    }
}

