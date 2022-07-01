package com.eventable

import android.content.ContentValues.TAG
import android.os.Bundle
import android.provider.CalendarContract
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eventable.model.Event
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.fragment_confirmed.*
import kotlinx.android.synthetic.main.fragment_home.*


class ConfirmedFragment : Fragment(R.layout.fragment_confirmed) {

    private lateinit var firestoreDb: FirebaseFirestore
    private lateinit var events : MutableList<Event>

    private var layoutManager: RecyclerView.LayoutManager? = null
    //private var adapter: RecyclerView.Adapter<RecyclerAdapterConfirmed.ViewHolder>? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var auth = FirebaseAuth.getInstance()
        var user = auth.currentUser
        var uid = user?.uid

        firestoreDb = FirebaseFirestore.getInstance()
        events = mutableListOf()

        recyclerViewConfirmed.adapter = RecyclerAdapterConfirmed(events)
        recyclerViewConfirmed.layoutManager = LinearLayoutManager(activity)

        val eventsReference = firestoreDb.collection("events")
        eventsReference
            .whereArrayContains("votes_user", uid.toString())
            .addSnapshotListener{ snapshot, excecption ->
                if (excecption != null || snapshot == null) {
                    Log.e(TAG, "Exception when querying confirmed events")
                    return@addSnapshotListener
                }
                var eventList = snapshot.toObjects(Event::class.java)
                events.clear()
                events.addAll(eventList)

                for(event in eventList) {
                    Log.i(TAG, "Confirmed ${event}")
                }
            }

        refreshConfirmed()

        //recyclerViewConfirmed.layoutManager =
          //  LinearLayoutManager(activity) //philbruck: hier wird die activity übergeben weil man ja eine Context übergebn muss und Fragmnet leiter nicht von Context ab!

        //recyclerViewConfirmed.adapter = RecyclerAdapterConfirmed()
    }

    override fun onResume() {
        recyclerViewConfirmed.adapter = RecyclerAdapterConfirmed(events)
        super.onResume()
    }

    private fun refreshConfirmed() {

        refresherConfirmed.setOnRefreshListener { //philbruck: dem <androidx.swiperefreshlayout> Layout mit der id "refreher" eine Refrsehlsitener geben!

            recyclerViewConfirmed.adapter = RecyclerAdapterConfirmed(events)
            Toast.makeText(activity, "Seite wurde neu geladen", Toast.LENGTH_SHORT).show()
            refresherConfirmed.isRefreshing = false
        }


    }


}