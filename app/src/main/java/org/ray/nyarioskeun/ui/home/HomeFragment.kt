package org.ray.nyarioskeun.ui.home

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import org.ray.core.domain.model.Account
import org.ray.core.domain.model.Menus
import org.ray.nyarioskeun.MainActivity
import org.ray.nyarioskeun.R
import org.ray.nyarioskeun.databinding.FragmentHomeBinding
import org.ray.nyarioskeun.ui.WelcomeActivity
import org.ray.nyarioskeun.ui.history.HistoryActivity
import org.ray.nyarioskeun.utils.ARGUMENTS_CHECK
import org.ray.nyarioskeun.utils.MenuAdapter
import org.ray.nyarioskeun.utils.PASSED_DATA_CHECK

@SuppressLint("LogNotTimber")
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var menuAdapter: MenuAdapter

    private var account = Account()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)

        // Set fullname on actionbar title (From login page)
        if (activity?.intent!!.hasExtra("EXTRA_USERNAME") &&
            activity?.intent!!.hasExtra("EXTRA_FULLNAME")
        ) {
            val loginUsername = activity?.intent?.getStringExtra("EXTRA_USERNAME")
            val fullname = activity?.intent?.getStringExtra("EXTRA_FULLNAME")

            Log.d("$PASSED_DATA_CHECK.username", "$loginUsername")
            Log.d("$PASSED_DATA_CHECK.fullname", "$fullname")

            (activity as MainActivity).supportActionBar?.title = fullname
            account = Account(fullname = fullname, username = loginUsername)
        }

        // Set fullname on actionbar title (From report page)
        if (activity?.intent!!.hasExtra("EXTRA_ACCOUNT_RETURN")) {
            val fullname = activity?.intent?.getStringExtra("EXTRA_ACCOUNT_RETURN")

            Log.d("$PASSED_DATA_CHECK.fullname", "$fullname")

            (activity as MainActivity).supportActionBar?.title = fullname
            account = Account(fullname = fullname)
        }

        getMenu()
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_history, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_history -> {
                startActivity(
                    Intent(
                        requireContext(),
                        HistoryActivity::class.java
                    ).putExtra("USERNAME_HISTORY", account.username)
                )
                return true
            }
            R.id.action_logout -> {
                startActivity(
                    Intent(
                        requireContext(),
                        WelcomeActivity::class.java
                    ).putExtra("LOGOUT_MESSAGE", "Anda berhasil Logout!")
                )
                return true
            }
        }

        return false
    }

    // Load main menu
    private fun getMenu() {
        val arrListMenu = arrayListOf(
            Menus(R.drawable.lecture, "Dosen"),
            Menus(R.drawable.student, "Mahasiswa"),
            Menus(R.drawable.employee, "Karyawan"),
            Menus(R.drawable.canteen, "Kantin"),
            Menus(R.drawable.tmart, "Pegawai TMart"),
            Menus(R.drawable.parent, "Orang Tua")
        )

        menuAdapter = MenuAdapter(arrListMenu) { position, item ->
            // Passing user data
            val bundleStatus = Bundle()

            with(bundleStatus) {
                putString("status", item.name)
                putParcelable("account", account)
            }
            Log.d("$ARGUMENTS_CHECK.account", account.toString())

            when (position) {
                0 -> {
                    Log.d("$ARGUMENTS_CHECK.dosen", "${bundleStatus.get("status")}")
                    findNavController().navigate(
                        R.id.action_homeFragment_to_reportFragment, bundleStatus
                    )
                }
                1 -> {
                    Log.d("$ARGUMENTS_CHECK.mahasiswa", "${bundleStatus.get("status")}")
                    findNavController().navigate(
                        R.id.action_homeFragment_to_reportFragment, bundleStatus
                    )
                }
                2 -> {
                    Log.d("$ARGUMENTS_CHECK.karyawan", "${bundleStatus.get("status")}")
                    findNavController().navigate(
                        R.id.action_homeFragment_to_reportFragment, bundleStatus
                    )
                }
                3 -> {
                    Log.d("$ARGUMENTS_CHECK.kantin", "${bundleStatus.get("status")}")
                    findNavController().navigate(
                        R.id.action_homeFragment_to_reportFragment, bundleStatus
                    )
                }
                4 -> {
                    Log.d("$ARGUMENTS_CHECK.tmart", "${bundleStatus.get("status")}")
                    findNavController().navigate(
                        R.id.action_homeFragment_to_reportFragment, bundleStatus
                    )
                }
                5 -> {
                    Log.d("$ARGUMENTS_CHECK.ot", "${bundleStatus.get("status")}")
                    findNavController().navigate(
                        R.id.action_homeFragment_to_reportFragment, bundleStatus
                    )
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