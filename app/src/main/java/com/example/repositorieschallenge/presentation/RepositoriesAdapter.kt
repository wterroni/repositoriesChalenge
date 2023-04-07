package com.example.repositorieschallenge.presentation

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.repositorieschallenge.R
import com.example.repositorieschallenge.domain.model.RepositoriesModel
import com.squareup.picasso.Picasso


class RepositoriesAdapter(
) : RecyclerView.Adapter<RepositoriesAdapter.ViewHolder>(), Filterable {

    var itemsFiltered: MutableList<RepositoriesModel> = mutableListOf()
    private var layoutManager: StaggeredGridLayoutManager? = null

    enum class ViewType {
        SMALL,
        DETAILED
    }

    var items: ArrayList<RepositoriesModel> = arrayListOf()
        set(value) {
            val oldSize = field.size
            val newSize = value.size
            field.addAll(value)

            notifyItemRangeInserted(oldSize, newSize)
        }

    @SuppressLint("NotifyDataSetChanged")
    fun addData(list: List<RepositoriesModel>) {
        items = list as ArrayList<RepositoriesModel>
        itemsFiltered = items
        notifyDataSetChanged()
    }

    fun setLayoutManager(layoutManager: StaggeredGridLayoutManager) {
        this.layoutManager = layoutManager
    }

    override fun getItemCount(): Int = itemsFiltered.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            ViewType.DETAILED.ordinal -> ViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.repositories_list_grid, parent, false)
            )
            else -> ViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.repositories_list_line, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemsFiltered[position])
    }

    override fun getItemViewType(position: Int): Int {
        return if (layoutManager?.spanCount == 1) ViewType.SMALL.ordinal
        else ViewType.DETAILED.ordinal
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val textName: TextView = view.findViewById(R.id.text_name)
        private val textStars: TextView = view.findViewById(R.id.text_stars)
        private val textForks: TextView = view.findViewById(R.id.text_forks)
        private val textAuthor: TextView = view.findViewById(R.id.text_author)

        private val imageView: ImageView = view.findViewById(R.id.image_repository)

        @SuppressLint("NotifyDataSetChanged")
        fun bind(item: RepositoriesModel) {
            Picasso.with(itemView.context).load(item.imageUrl).into(imageView)
            textName.text = item.repoName
            textStars.text = item.starsCount.toString()
            textForks.text = item.forkCount.toString()
            textAuthor.text = item.authorName
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint?.toString() ?: ""
                if (charString.isEmpty()) itemsFiltered = items else {
                    val filteredList = ArrayList<RepositoriesModel>()
                    items
                        .filter {
                            (it.repoName.uppercase().contains(constraint!!))

                        }
                        .forEach { filteredList.add(it) }
                    itemsFiltered = filteredList

                    Log.e("performFiltering: t1: ", filteredList.size.toString())

                }
                return FilterResults().apply { values = itemsFiltered }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {

                itemsFiltered = if (results?.values == null)
                    ArrayList()
                else
                    results.values as ArrayList<RepositoriesModel>
                notifyDataSetChanged()

                Log.e("performFiltering: t2 ", "called" + itemsFiltered.size)

            }
        }
    }
}