package com.eventable

import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eventable.model.Event
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_data1.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_invitation.*
import kotlinx.android.synthetic.main.view_cardlayout.*


class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var firestoneDb: FirebaseFirestore
    private lateinit var events: MutableList<Event>
    private lateinit var adapter: RecyclerAdapter


    private var layoutManager: RecyclerView.LayoutManager? = null
    //private var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var auth = FirebaseAuth.getInstance()
        var user = auth.currentUser
        var uid = user?.uid


        firestoneDb = FirebaseFirestore.getInstance()
        //Datenquelle wird erstellt
        events = mutableListOf()
        //adapter = RecyclerAdapter(this, events )

        recyclerView.adapter = RecyclerAdapter(events)
        //philbruck: hier wird die activity übergeben weil man ja eine Context übergebn muss und Fragmnet leiter nicht von Context ab!
        recyclerView.layoutManager = LinearLayoutManager(activity)

        refreshHome()


        val eventsReference = firestoneDb.collection("events")
        eventsReference
            .whereEqualTo("creator", uid)
            //.orderBy("creationTimeMs", Query.Direction.DESCENDING)
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
                }
            }


    }

    override fun onResume() {
        recyclerView.adapter = RecyclerAdapter(events)
        super.onResume()
    }


    private fun refreshHome() {
        refresherHome.setOnRefreshListener { //philbruck: dem <androidx.swiperefreshlayout> Layout mit der id "refreher" eine Refrsehlsitener geben!

            recyclerView.adapter = RecyclerAdapter(events)
            Toast.makeText(activity, "Seite wurde neu geladen", Toast.LENGTH_SHORT).show()
            refresherHome.isRefreshing = false

        }
    }
}






