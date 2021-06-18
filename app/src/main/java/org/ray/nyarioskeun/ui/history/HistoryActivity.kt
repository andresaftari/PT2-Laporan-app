package org.ray.nyarioskeun.ui.history

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import org.ray.core.domain.model.Account
import org.ray.nyarioskeun.databinding.ActivityHistoryBinding
import org.ray.nyarioskeun.utils.ARGUMENTS_CHECK
import org.ray.nyarioskeun.utils.SectionsPagerAdapter

@SuppressLint("LogNotTimber")
class HistoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        supportActionBar.let { title = "History" }
        setContentView(binding.root)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager = binding.viewPager
        val tabs = binding.tabs

        viewPager.adapter = sectionsPagerAdapter
        tabs.setupWithViewPager(viewPager)

        if (intent.hasExtra("USERNAME_HISTORY")) {
            val data = intent.getStringExtra("USERNAME_HISTORY")
            Log.d("$ARGUMENTS_CHECK.username", "$data")

            Account(username = "$data")
        }
    }
}