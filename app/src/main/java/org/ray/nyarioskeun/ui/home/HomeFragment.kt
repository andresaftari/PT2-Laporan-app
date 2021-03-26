package org.ray.nyarioskeun.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
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
        ) { position, item ->
            val bundle: Bundle

            when (position) {
                0 -> {
                    bundle = bundleOf("status" to "Dosen")
                    findNavController().navigate(R.id.action_homeFragment_to_reportFragment, bundle)
                }
                1 -> {
                    bundle = bundleOf("status" to "Mahasiswa")
                    findNavController().navigate(R.id.action_homeFragment_to_reportFragment, bundle)
                }
                2 -> {
                    bundle = bundleOf("status" to "Karyawan")
                    findNavController().navigate(R.id.action_homeFragment_to_reportFragment, bundle)
                }
                3 -> {
                    bundle = bundleOf("status" to "Petugas Kantin")
                    findNavController().navigate(R.id.action_homeFragment_to_reportFragment, bundle)
                }
                4 -> {
                    bundle = bundleOf("status" to "Petugas TMart")
                    findNavController().navigate(R.id.action_homeFragment_to_reportFragment, bundle)
                }
                5 -> {
                    bundle = bundleOf("status" to "Orang Tua")
                    findNavController().navigate(R.id.action_homeFragment_to_reportFragment, bundle)
                }
            }
        }

        with(binding.rvMain) {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = menuAdapter
            setHasFixedSize(true)
        }
    }
}