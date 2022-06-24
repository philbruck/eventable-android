package com.eventable

import android.app.ActionBar
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.eventable.model.Answer
import com.eventable.model.Event
import com.google.api.Distribution
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_data1.*

class Data1Fragment : Fragment(R.layout.fragment_data1){

    private val args : Data1FragmentArgs by navArgs() //philbruck: autogenerire Klasse wegen dem safeArgs-Plug-In
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
                //adapter.notifyDataSetChanged()

                for (event in eventList) {
                    Log.i(TAG, "Event ${event}")

                    dataHomeLocation.text = event.location
                    dataHomeName.text = event.name
                    dataHomeEventId.text = event.eventId
                    dataHomeData.text = event.date
                    dataHomeStartTime.text = event.starttime
                    dataHomeDescription.text = event.description



                    // So kann man TextViews dynamisch erzeugen -> Wie kann ich zum Beispiel die Höhe einstellen?
/*                    val tv_dynamic = TextView(activity)
                    tv_dynamic.textSize = 20f
                    tv_dynamic.text = "Automatisch generiert"
                    //Augustin: Größe setzen
                    tv_dynamic.layoutParams = RelativeLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT)
                    questionRL.addView(tv_dynamic)*/



                    //Augusin: Soll man das so machen oder kann man das irgendwie eleganter?
                    var coundquestions = event.questions?.size?.toInt()
                    when (coundquestions) {
                        0 -> Log.e(TAG, "Keine Fragen vorhanden")
                        1 -> dataHomeQuestion1.text = event.questions?.get(0)
                        2 -> {
                            dataHomeQuestion1.text = event.questions?.get(0)
                            dataHomeQuestion2.text = event.questions?.get(1)
                        }
                        3 -> {
                            dataHomeQuestion1.text = event.questions?.get(0)
                            dataHomeQuestion2.text = event.questions?.get(1)
                            dataHomeQuestion3.text = event.questions?.get(2)
                        }
                        4 -> {
                            dataHomeQuestion1.text = event.questions?.get(0)
                            dataHomeQuestion2.text = event.questions?.get(1)
                            dataHomeQuestion3.text = event.questions?.get(2)
                            dataHomeQuestion4.text = event.questions?.get(3)

                        }
                        5 -> {
                            dataHomeQuestion1.text = event.questions?.get(0)
                            dataHomeQuestion2.text = event.questions?.get(1)
                            dataHomeQuestion3.text = event.questions?.get(2)
                            dataHomeQuestion4.text = event.questions?.get(3)
                            dataHomeQuestion5.text = event.questions?.get(4)
                        }
                    }
                }
            }


        // Ruft die Antworten für Frage 1 ab
        val answersReference = firestoneDb.collection("answers")
        answersReference
            .whereEqualTo("event_id", args.countEventId)
            //.whereEqualTo("question_index", "0")
            .addSnapshotListener { snapshot, exception ->
                if (exception != null || snapshot == null) {
                    Log.e(TAG, "Exception when querying answers", exception)
                    return@addSnapshotListener
                }
                //Speichert die Objekte in eine Liste
                var answerList = snapshot.toObjects(Answer::class.java)
                answers.clear()
                answers.addAll(answerList)

                //var countAnswerYes1 = 0
                //var countAnswerNo1 = 0
                var countAnswer1 = Array(2){0}
                var countAnswer2= Array(2){0}
                var countAnswer3 = Array(2){0}
                var countAnswer4 = Array(2){0}
                var countAnswer5 = Array(2){0}
                var countsum1 = 0
                var countsum2 = 0
                var countsum3 = 0
                var countsum4 = 0
                var countsum5 = 0

                //countAnswer[0] ist ja und countAnswer[1] ist nein

                for (answer in answerList) {
                    Log.i(TAG, "Answers ${answer}")

                    when(answer.questionIndex.toInt()) {
                        0 -> {  if(answer.answer == "ja") {
                                    countAnswer1[0]++
                                } else if(answer.answer == "nein") {
                                    countAnswer1[1]++
                                 }
                        }
                        1 -> {  if(answer.answer == "ja") {
                            countAnswer2[0]++
                            } else if(answer.answer == "nein") {
                            countAnswer2[1]++
                            }
                        }
                        2 -> {if(answer.answer == "ja") {
                            countAnswer3[0]++
                        } else if(answer.answer == "nein") {
                            countAnswer3[1]++
                            }
                        }
                        3 -> {}
                        4 -> {}
                    }
                    //DataHomeQueston1Sum.text = countsum.toString()
                }
                dataHomeQuestion1Yes.text = "Ja: ${countAnswer1[0]}"
                dataHomeQuestion1No.text = "Nein: ${countAnswer1[1]}"

                dataHomeQuestion2Yes.text = "Ja: ${countAnswer2[0]}"
                dataHomeQuestion2No.text = "Nein: ${countAnswer2[1]}"

                dataHomeQuestion3Yes.text = "Ja: ${countAnswer3[0]}"
                dataHomeQuestion3No.text = "Nein: ${countAnswer3[1]}"

                countsum1 = countAnswer1[0] + countAnswer1[1]
                dataHomeQueston1Sum.text = "Gesamt: ${countsum1}"

                countsum2 = countAnswer2[0] + countAnswer2[1]
                dataHomeQueston2Sum.text = "Gesamt: ${countsum2}"

                countsum3 = countAnswer3[0] + countAnswer3[1]
                dataHomeQueston3Sum.text = "Gesamt: ${countsum3}"


            }



                //data1_user_id_TV.text = args.userId.toString()
                //data1_event_id_TV.text = args.eventId.toString()
                //data1_user_id_TV.text = args.name.toString()
            }

    }
