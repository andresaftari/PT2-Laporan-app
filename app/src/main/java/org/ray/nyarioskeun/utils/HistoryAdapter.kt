package org.ray.nyarioskeun.utils

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.ray.core.data.remote.api.response.ResponseHistory
import org.ray.core.utils.API_BASE_URL
import org.ray.nyarioskeun.databinding.ItemHistoryBinding

class HistoryAdapter(
    private var items: ArrayList<ResponseHistory>
) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {
    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        with(holder) { bind(item) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    @SuppressLint("SetTextI18n")
    class ViewHolder(val binding: ItemHistoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(history: ResponseHistory) = with(binding) {
            Glide.with(this.root)
                .load("$API_BASE_URL/${history.username}/${history.bukti_kerusakan}")
                .into(ivThumbnail)

            tvUsername.text = "Reported by ${history.username}"
            tvHistoryDate.text = history.tanggal_laporan
            tvHistoryLoc.text = history.lokasi
            tvHistoryTitle.text = history.nama_kerusakan
        }
    }
}