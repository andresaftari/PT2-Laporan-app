package org.ray.nyarioskeun.ui.history

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.ray.nyarioskeun.databinding.ActivityHistoryBinding
import org.ray.nyarioskeun.utils.SectionsPagerAdapter

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
    }
}