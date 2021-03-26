package org.ray.nyarioskeun.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import org.ray.nyarioskeun.R
import org.ray.nyarioskeun.data.model.Menu
import org.ray.nyarioskeun.databinding.FragmentHomeBinding
import org.ray.nyarioskeun.utils.MenuAdapter

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var menuAdapter: MenuAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        getMenu()
        return binding.root
    }

    private fun getMenu() {
        val arrListMenu = arrayListOf(
            Menu(R.drawable.lecture, "DOSEN"),
            Menu(R.drawable.student, "MAHASISWA"),
            Menu(R.drawable.employee, "KARYAWAN"),
            Menu(R.drawable.canteen, "PETUGAS KANTIN"),
            Menu(R.drawable.tmart, "PETUGAS TMART"),
            Menu(R.drawable.parent, "ORANG TUA")
        )

        menuAdapter = MenuAdapter(
            arrListMenu
        ) { _, item ->
            Snackbar.make(requireView(), "Clicked ${item.name}", Snackbar.LENGTH_SHORT).show()
        }

        with(binding.rvMain) {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = menuAdapter
            setHasFixedSize(true)
        }
    }
}