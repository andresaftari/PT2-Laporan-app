package org.ray.nyarioskeun.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ReusableAdapter<T>(
    private var layout: Int,
    private var items: ArrayList<T>,
    private var view: (View, T) -> Unit,
    var handler: (Int, T) -> Unit
) : RecyclerView.Adapter<ReusableAdapter.ViewHolder<T>>() {
    fun setData(list: List<T>?) {
        if (list.isNullOrEmpty()) return
        with(items) {
            clear()
            addAll(list)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder<T>(
        LayoutInflater
            .from(parent.context)
            .inflate(this.layout, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder<T>, position: Int) {
        val item = items[position]
        with(holder) {
            bind(item, view)
            itemView.setOnClickListener { handler(position, item) }
        }
    }

    override fun getItemCount() = items.size

    class ViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: T, view: (View, T) -> Unit) = view(itemView, item)
    }
}