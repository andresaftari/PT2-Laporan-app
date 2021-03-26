package org.ray.nyarioskeun.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import org.ray.nyarioskeun.MainActivity
import org.ray.nyarioskeun.R
import org.ray.nyarioskeun.data.model.Menus
import org.ray.nyarioskeun.databinding.FragmentHomeBinding
import org.ray.nyarioskeun.utils.ARGUMENTS_CHECK
import org.ray.nyarioskeun.utils.MenuAdapter

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var menuAdapter: MenuAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        if (activity?.intent!!.hasExtra("EXTRA_USERNAME")) {
            val loginUsername = activity?.intent?.getStringExtra("EXTRA_USERNAME")
            Log.d("Testing_dulu", "$loginUsername")

            (activity as MainActivity).supportActionBar?.title = loginUsername
        }

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
            val bundle = bundleOf("status" to item.name)

            when (position) {
                0 -> {
                    Log.d("$ARGUMENTS_CHECK.dosen", "${bundle.get("status")}")
                    findNavController().navigate(R.id.action_homeFragment_to_reportFragment, bundle)
                }
                1 -> {
                    Log.d("$ARGUMENTS_CHECK.mahasiswa", "${bundle.get("status")}")
                    findNavController().navigate(R.id.action_homeFragment_to_reportFragment, bundle)
                }
                2 -> {
                    Log.d("$ARGUMENTS_CHECK.karyawan", "${bundle.get("status")}")
                    findNavController().navigate(R.id.action_homeFragment_to_reportFragment, bundle)
                }
                3 -> {
                    Log.d("$ARGUMENTS_CHECK.kantin", "${bundle.get("status")}")
                    findNavController().navigate(R.id.action_homeFragment_to_reportFragment, bundle)
                }
                4 -> {
                    Log.d("$ARGUMENTS_CHECK.tmart", "${bundle.get("status")}")
                    findNavController().navigate(R.id.action_homeFragment_to_reportFragment, bundle)
                }
                5 -> {
                    Log.d("$ARGUMENTS_CHECK.ot", "${bundle.get("status")}")
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