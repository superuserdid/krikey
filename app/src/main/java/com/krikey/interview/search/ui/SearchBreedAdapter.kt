package com.krikey.interview.search.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.krikey.interview.R
import com.krikey.interview.arch.ImageLoader
import com.krikey.interview.feed.model.CatFeedPresentationModel
import com.krikey.interview.feed.ui.CatFeedImageAdapter
import com.krikey.interview.search.model.SearchBreedPresentationModel
import kotlinx.android.synthetic.main.cat_feed_item.view.*
import kotlinx.android.synthetic.main.search_breed_item.view.*

class SearchBreedAdapter(breeds: List<SearchBreedPresentationModel.BreedPresentationModel>) : RecyclerView.Adapter<SearchBreedAdapter.SearchBreedViewHolder>() {

    private var dataSet = arrayListOf<SearchBreedPresentationModel.BreedPresentationModel>().apply { addAll(breeds) }

    var itemClickListener: ((SearchBreedPresentationModel.BreedPresentationModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        SearchBreedViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.search_breed_item, parent, false))

    override fun onBindViewHolder(holder: SearchBreedViewHolder, position: Int) {
        val breed = dataSet[position]
        holder.apply {
            parent.setOnClickListener {
                itemClickListener?.invoke(breed)
            }
            name.text = breed.name
        }
    }

    fun clear() {
        dataSet.clear()
        notifyDataSetChanged()
    }

    override fun getItemCount() = dataSet.count()

    class SearchBreedViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val parent: View = view.itemParent

        val name: TextView = view.name

    }

}
