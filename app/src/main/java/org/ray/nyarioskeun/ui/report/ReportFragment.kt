package org.ray.nyarioskeun.ui.report

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.esafirm.imagepicker.features.ImagePicker
import com.esafirm.imagepicker.model.Image
import com.google.android.material.snackbar.Snackbar
import org.ray.nyarioskeun.R
import org.ray.nyarioskeun.data.local.model.Account
import org.ray.nyarioskeun.databinding.FragmentReportBinding
import org.ray.nyarioskeun.ui.SuccessActivity
import org.ray.nyarioskeun.utils.DATE_CHECK
import org.ray.nyarioskeun.utils.IMAGE_PICKER_CHECK
import org.ray.nyarioskeun.utils.ImageCompressor
import org.ray.nyarioskeun.utils.PASSED_DATA_CHECK
import java.io.File
import java.io.IOException
import java.io.InvalidObjectException
import java.text.SimpleDateFormat
import java.util.*

class ReportFragment : Fragment() {
    private var userAccount: Account? = null
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
    private var getPhoto: File? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentReportBinding.inflate(layoutInflater, container, false)

        setUserData()
        loadDropDownLocation()
        setTodayDate()

        with(binding) {
            ivEvidence.setOnClickListener { editEvidencePhoto() }
            tlEvidence.setOnClickListener { editEvidencePhoto() }
            btnSubmit.setOnClickListener { checkReportData() }
        }

        return binding.root
    }

    // Set user evidence photo
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (ImagePicker.shouldHandle(requestCode, resultCode, data)) {
            val pickedImage: Image = ImagePicker.getFirstImageOrNull(data)

            try {
                val parcelFileDescriptor = requireActivity()
                    .contentResolver
                    .openFileDescriptor(pickedImage.uri, "r")!!

                val fileDescriptor = parcelFileDescriptor.fileDescriptor
                val image = BitmapFactory.decodeFileDescriptor(fileDescriptor)
                val filePhoto = ImageCompressor.addTempFile(requireContext(), image)
                getPhoto = filePhoto

                // Upload ke API menggunakan Multipart
                // --------------------------------- //
                // Upload ke API menggunakan Multipart

                with(binding) {
                    Glide.with(requireContext())
                        .load(image)
                        .into(ivEvidence)

                    edtEvidence.setText(filePhoto.name)
                }
            } catch (ex: InvalidObjectException) {
                Log.d("$IMAGE_PICKER_CHECK.evidence", "${ex.printStackTrace()}")
            } catch (ex: IOException) {
                Log.d("$IMAGE_PICKER_CHECK.evidence", ex.message.toString())
            }
        }
    }

    // Mengambil user evidence photo
    private fun editEvidencePhoto() = ImagePicker.create(this).single().start()

    // Set report date ke tanggal lapor (tanggal sekarang)
    private fun setTodayDate() {
        val df = SimpleDateFormat("EEE, d MMM yyyy (HH:mm)", Locale("EN"))
        val date = Date()
        val today = df.format(date)

        Log.d(DATE_CHECK, today)
        binding.edtReportDate.setText(today)
    }

    // Submit report data
    private fun setReportData() {
        binding.pbLoading.visibility = View.VISIBLE

        Snackbar.make(
            binding.root,
            "Megirim laporan...",
            Snackbar.LENGTH_SHORT
        ).show()

        val background = object : Thread() {
            override fun run() {
                sleep(2000)

                startActivity(
                    Intent(
                        requireContext(),
                        SuccessActivity::class.java
                    ).putExtra("EXTRA_ACCOUNT", userAccount!!.fullname.toString())
                )
                activity?.finish()
            }
        }
        background.start()
    }

    // Form sanity check
    private fun checkReportData() {
        val reportProblem = binding.edtProblem.text.toString()
        val reportDescription = binding.edtDescription.text.toString()
        val reportLocation = binding.dropdownLocation.text.toString()

        with(binding) {
            when {
                TextUtils.isEmpty(reportProblem) -> {
                    edtProblem.error = "Silakan isi keluhan fasilitas terlebih dahulu"
                    edtProblem.requestFocus()
                }
                TextUtils.isEmpty(reportLocation) -> {
                    Snackbar.make(
                        requireView(),
                        "Silakan pilih lokasi fasilitas terlebih dahulu",
                        Snackbar.LENGTH_SHORT
                    ).show()
                    dropdownLocation.requestFocus()
                }
                getPhoto == null -> {
                    Snackbar.make(
                        requireView(),
                        "Silakan isi bukti keluhan fasilitas terlebih dahulu",
                        Snackbar.LENGTH_SHORT
                    ).show()
                    edtEvidence.requestFocus()
                }
                TextUtils.isEmpty(reportDescription) -> {
                    edtDescription.error = "Deskripsikan keluhan terlebih dahulu"
                    edtDescription.requestFocus()
                }
                else -> setReportData()
            }
        }
    }

    // Get user fullname and status
    private fun setUserData() {
        val userStatus = arguments?.get("status")
        userAccount = arguments?.getParcelable("account")

        Log.d("$PASSED_DATA_CHECK.account", userAccount!!.fullname.toString())
        Log.d("$PASSED_DATA_CHECK.status", userStatus.toString())

        with(binding) {
            tvStatus.text = userStatus.toString()
            tvName.text = userAccount!!.fullname.toString()
        }
    }

    // Load dropdown untuk pilihan location
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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    companion object {
        private const val USERNAME_STATE = "USERNAME"
        private const val STATUS_STATE = "STATUS"
    }
}