package org.ray.nyarioskeun.ui.report

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import org.ray.nyarioskeun.R
import org.ray.nyarioskeun.data.model.Account
import org.ray.nyarioskeun.databinding.FragmentReportBinding
import org.ray.nyarioskeun.utils.PASSED_DATA_CHECK

class ReportFragment : Fragment() {
    private lateinit var binding: FragmentReportBinding
    private var listLocation = listOf(
        "Kantin Asrama Putra",
        "Kantin Asrama Putri",
        "Ruang-Riung",
        "TMart Asrama Putra",
        "TMart Ruang Riung",
        "Laboratorium FIT",
        "Kelas FIT",
        "Gedung FIT",
    )

    private var getLocation = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentReportBinding.inflate(layoutInflater, container, false)

        setUserData()
        loadDropDownLocation()

        return binding.root
    }

    // Getting user status and fullname
    private fun setUserData() {
        val userStatus = arguments?.get("status")
        val userAccount = arguments?.getParcelable<Account>("account")

        Log.d("$PASSED_DATA_CHECK.account", userAccount!!.fullname.toString())
        Log.d("$PASSED_DATA_CHECK.status", userStatus.toString())

        with(binding) {
            tvStatus.text = userStatus.toString()
            tvName.text = userAccount.fullname.toString()
        }
    }

    private fun loadDropDownLocation() {
        val arrAdapter = ArrayAdapter(
            requireContext(),
            R.layout.item_list_dropdown_text,
            listLocation
        )

        with(binding.dropdownLocation) {
            setAdapter(arrAdapter)
            addTextChangedListener(object : TextWatcher {
                override fun onTextChanged(
                    text: CharSequence?,
                    start: Int,
                    before: Int,
                    count: Int
                ) {
                    for (position in listLocation.indices) {
                        if (binding.dropdownLocation.text.toString() == listLocation[position]) {
                            getLocation = listLocation[position]
                        }
                    }
                }

                override fun beforeTextChanged(
                    text: CharSequence?,
                    start: Int,
                    before: Int,
                    count: Int
                ) {
                }

                override fun afterTextChanged(text: Editable?) {}
            })
        }
    }
}