package com.eventable

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.VelocityTracker
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.eventable.model.Event
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObjects
import kotlinx.android.synthetic.main.fragment_data1.*

class Data1Fragment : Fragment(R.layout.fragment_data1){

    private val args : Data1FragmentArgs by navArgs() //philbruck: autogenerire Klasse wegen dem safeArgs-Plug-In
    private lateinit var firestoneDb: FirebaseFirestore
    private lateinit var events: MutableList<Event>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var auth = FirebaseAuth.getInstance()
        var user = auth.currentUser
        var uid = user?.uid


        firestoneDb = FirebaseFirestore.getInstance()
        events = mutableListOf()

        val eventsReference = firestoneDb.collection("events")
        eventsReference
            .whereEqualTo("event_id", args.eventId)
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

                    DataHomeLocation.text = event.location
                    DataHomeName.text = event.name
                    DataHomeEventId.text = event.eventId
                    DataHomeData.text = event.date
                    DataHomeStartTime.text = event.starttime
                    DataHomeDescription.text = event.description


                    var coundquestions = event.questions?.size?.toInt()
                    when(coundquestions) {
                        0 -> Log.e(TAG, "Keine Fragen vorhanden")
                        1 -> DataHomeQuestion1.text = event.questions?.get(0)
                        2 -> {DataHomeQuestion1.text = event.questions?.get(0)
                                DataHomeQuestion2.text = event.questions?.get(1)}
                        3 -> {DataHomeQuestion1.text = event.questions?.get(0)
                            DataHomeQuestion2.text = event.questions?.get(1)
                           DataHomeQuestion3.text = event.questions?.get(2) }
                        4 -> Log.e(TAG, "Kommt bald")
                        5 -> Log.e(TAG, "Kommt bald")
                    }
                }
            }



        val answersReference = firestoneDb.collection("answers")
        eventsReference
            .whereEqualTo("event_id", args.eventId)
            .whereEqualTo("question", 1)
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















        //DataHomeEventId.text = args.eventId.toString()
       // DataHomeLocation.text = args.location.toString()
       // DataHomeName.text = args.name.toString()









        //data1_user_id_TV.text = args.userId.toString()
        //data1_event_id_TV.text = args.eventId.toString()
        //data1_user_id_TV.text = args.name.toString()
    }

}