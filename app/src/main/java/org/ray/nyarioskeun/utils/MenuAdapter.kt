package org.ray.nyarioskeun.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.ray.nyarioskeun.data.local.model.Menus
import org.ray.nyarioskeun.databinding.ItemMenuBinding

class MenuAdapter(private var items: ArrayList<Menus>, var handler: (Int, Menus) -> Unit) :
    RecyclerView.Adapter<MenuAdapter.ViewHolder>() {

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        with(holder) {
            bind(item)
            binding.root.setOnClickListener { handler(position, item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    class ViewHolder(val binding: ItemMenuBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(menu: Menus) = with(binding) {
            Glide.with(this.root)
                .load(menu.thumbnail)
                .into(ivMenu)

            tvMenuName.text = menu.name
        }
    }
}