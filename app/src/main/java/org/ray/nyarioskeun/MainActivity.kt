package org.ray.nyarioskeun

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import org.ray.core.domain.domainModel.Account
import org.ray.nyarioskeun.utils.ARGUMENTS_CHECK

@SuppressLint("LogNotTimber")
class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (intent.hasExtra("EXTRA_USERNAME")) {
            val data = intent.getStringExtra("EXTRA_USERNAME")
            Log.d("$ARGUMENTS_CHECK.username", "$data")

            Account(username = "$data")
        }

        navController = findNavController(R.id.myNavHostFragment)
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onSupportNavigateUp() = navController.navigateUp()
}