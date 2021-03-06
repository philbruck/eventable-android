package com.eventable

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.util.*
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.reflect.Array.set

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.i("TAG", "Die MainActivity hat gestartet!")

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment,
                R.id.invitationFragment,
                R.id.confirmedFragment,
                R.id.createFragment
            )
        ) //philbruck: alle Fragment der BottomNav als "top level" Fragments definieren

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController =
            navHostFragment.findNavController() //philbruck: eigene navController definieren
        //val navController = navHostFragment.navController


        setSupportActionBar(toolbar) //philbruck: eigene Toolbar anzeigen lassen

        setupActionBarWithNavController(
            navController,
            appBarConfiguration
        ) //philbruck: toolbar mit den nacController koppeln //philbruck: der ActionBar wird die appBarConfiguration mitgeteilt

        toolbar.setupWithNavController(navController) //philbruck: toolbar mit dem navController koppeln
        bottom_nav.setupWithNavController(navController) //philbruck: bootton_nav mit dem navController koppeln

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean { //philbruch: menu inflaten
        menuInflater.inflate(R.menu.options_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean { //philbruck: wenn ein menupunkt geklickt wird, ausf??hren was der NavController sagt
        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }

}