package org.ray.nyarioskeun.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import org.ray.nyarioskeun.R
import org.ray.nyarioskeun.data.model.Menus
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
            Menus(R.drawable.lecture, "DOSEN"),
            Menus(R.drawable.student, "MAHASISWA"),
            Menus(R.drawable.employee, "KARYAWAN"),
            Menus(R.drawable.canteen, "PETUGAS KANTIN"),
            Menus(R.drawable.tmart, "PETUGAS TMART"),
            Menus(R.drawable.parent, "ORANG TUA")
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