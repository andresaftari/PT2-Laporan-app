package org.ray.nyarioskeun

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import org.ray.core.data.local.entity.AccountEntity
import org.ray.core.utils.ARGUMENTS_CHECK

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (intent.hasExtra("EXTRA_USERNAME")) {
            val data = intent.getStringExtra("EXTRA_USERNAME")
            Log.d("$ARGUMENTS_CHECK.username", "$data")

            AccountEntity(username = "$data")
        }

        navController = findNavController(R.id.myNavHostFragment)
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onSupportNavigateUp() = navController.navigateUp()
}