package com.eventable

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment(R.layout.fragment_home) {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        refreshHome()

        recyclerView.layoutManager =
            LinearLayoutManager(activity) //philbruck: hier wird die activity übergeben weil man ja eine Context übergebn muss und Fragmnet leiter nicht von Context ab!

        recyclerView.adapter = RecyclerAdapter()

    }

    private fun refreshHome() {

        refresherHome.setOnRefreshListener { //philbruck: dem <androidx.swiperefreshlayout> Layout mit der id "refreher" eine Refrsehlsitener geben!

            Toast.makeText(activity, "Page has been refreshed", Toast.LENGTH_SHORT).show()
            refresherHome.isRefreshing = false

            val action_HomeToSelf = HomeFragmentDirections.actionHomeFragmentSelf()
            findNavController().navigate(action_HomeToSelf)

        }


    }


}