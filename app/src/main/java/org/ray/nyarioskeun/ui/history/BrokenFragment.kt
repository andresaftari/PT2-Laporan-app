package org.ray.nyarioskeun.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import org.koin.android.viewmodel.ext.android.viewModel
import org.ray.core.data.remote.api.response.ResponseHistory
import org.ray.core.utils.Status
import org.ray.nyarioskeun.databinding.FragmentBrokenBinding
import org.ray.nyarioskeun.utils.HistoryAdapter

class BrokenFragment : Fragment() {
    private lateinit var binding: FragmentBrokenBinding
    private lateinit var historyAdapter: HistoryAdapter

    private val viewModel: HistoryViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBrokenBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewModel) {
            getHistory().observe(viewLifecycleOwner, {
                when (it.status) {
                    Status.SUCCESS -> {
                        val arrOfData = arrayListOf<ResponseHistory>()

                        it.data?.forEach { item ->
                            val status = item.status
                            if (status == "broken") arrOfData.add(item)
                        }

                        if (arrOfData.isNotEmpty()) {
                            binding.tvNoHistory.visibility = View.GONE
                        }

                        historyAdapter = HistoryAdapter(arrOfData)

                        with(binding.rvHistory) {
                            layoutManager = LinearLayoutManager(requireContext())
                            adapter = historyAdapter
                        }
                    }
                    Status.LOADING -> Snackbar.make(
                        binding.root,
                        "Mohon menunggu",
                        Snackbar.LENGTH_SHORT
                    ).show()
                    Status.ERROR -> Snackbar.make(
                        binding.root,
                        "Gagal menampilkan riwayat! ${it.message}",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            })
        }
    }
}