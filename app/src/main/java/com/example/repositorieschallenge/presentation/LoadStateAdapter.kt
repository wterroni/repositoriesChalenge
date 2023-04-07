package com.example.repositorieschallenge.presentation

import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.repositorieschallenge.R
import com.example.repositorieschallenge.common.inflate
import com.example.repositorieschallenge.common.visible

class LoadStateAdapter: RecyclerView.Adapter<LoadStateAdapter.LoadStateViewHolder>() {

    var loadState: LoadState = LoadState.Done
        set(value) {
            when (field) {
                value -> {
                    notifyItemChanged(0)
                }
                is LoadState.Loading -> {
                    itemsCount = 0
                    notifyItemRemoved(0)
                }
                is LoadState.Done -> {
                    itemsCount = 1
                    notifyItemInserted(1)
                }
            }

            field = value
        }

    private var itemsCount: Int = 0

    inner class LoadStateViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(parent.inflate(VIEW_ID)) {
        private val progressBar: ProgressBar = parent.findViewById(R.id.progress_bar)
        fun bind(loadState: LoadState) = with(itemView) {
            progressBar.visible(
                visible = LoadState.Loading == loadState
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = LoadStateViewHolder(parent)
    override fun getItemViewType(position: Int): Int = VIEW_ID
    override fun getItemCount(): Int = itemsCount
    override fun onBindViewHolder(holder: LoadStateViewHolder, position: Int) {
        holder.bind(loadState)
    }

    companion object {
        private const val VIEW_ID =
            R.layout.item_load_state
    }
}