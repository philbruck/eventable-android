package com.eventable

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_confirmed.*


class ConfirmedFragment : Fragment(R.layout.fragment_confirmed) {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecyclerAdapterConfirmed.ViewHolder>? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        refreshConfirmed()

        recyclerViewConfirmed.layoutManager =
            LinearLayoutManager(activity) //philbruck: hier wird die activity übergeben weil man ja eine Context übergebn muss und Fragmnet leiter nicht von Context ab!

        recyclerViewConfirmed.adapter = RecyclerAdapterConfirmed()
    }

    private fun refreshConfirmed() {

        refresherConfirmed.setOnRefreshListener { //philbruck: dem <androidx.swiperefreshlayout> Layout mit der id "refreher" eine Refrsehlsitener geben!

            Toast.makeText(activity, "Page has been refreshed", Toast.LENGTH_SHORT).show()
            refresherConfirmed.isRefreshing = false

            val action_ConfirmToSelf = ConfirmedFragmentDirections.actionConfirmedFragmentSelf()
            findNavController().navigate(action_ConfirmToSelf)

        }


    }


}