package com.krikey.interview.search.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.krikey.interview.R
import com.krikey.interview.arch.ImageLoader
import com.krikey.interview.arch.impl.android.AndroidModule
import com.krikey.interview.search.SearchBreedContract
import com.krikey.interview.search.di.DaggerSearchBreedComponent
import com.krikey.interview.search.di.SearchBreedComponent
import com.krikey.interview.feed.model.CatFeedPresentationModel
import com.krikey.interview.search.model.SearchBreedPresentationModel
import com.krikey.interview.util.maybeOpen
import kotlinx.android.synthetic.main.cat_feed_fragment.*
import kotlinx.android.synthetic.main.fragment_search_breed.*
import kotlinx.android.synthetic.main.fragment_search_breed.recyclerView
import kotlinx.android.synthetic.main.toolbar.*

class SearchBreedFragment : Fragment(), SearchBreedContract.View {

    companion object {

        fun newInstance() = SearchBreedFragment()

    }

    private val component: SearchBreedComponent by lazy {
        DaggerSearchBreedComponent
            .builder()
            .androidModule(AndroidModule(context!!))
            .build()
    }

    private val presenter: SearchBreedContract.Presenter by lazy { component.presenter }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search_breed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onViewAttached(this)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(context!!, LinearLayoutManager.VERTICAL))
        }
        searchBar.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                search(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                search(newText)
                return true
            }
        })
    }

    private fun search(query: String?) {
        presenter.search(query.orEmpty())
    }

    override fun openWebsite(url: String) {
        Intent(Intent.ACTION_VIEW, Uri.parse(url))
            .maybeOpen(context!!, getString(R.string.could_not_open_website))
    }

    override fun onSearchResultsRetrieved(results: SearchBreedPresentationModel) {
        recyclerView.adapter = SearchBreedAdapter(results.breeds).apply {
            itemClickListener = {
                presenter.onBreedClick(it)
            }
        }
    }

    override fun clear() {
        (recyclerView.adapter as SearchBreedAdapter).clear()
    }

    override fun onError(error: SearchBreedContract.Error) {
        Toast.makeText(activity, error.name, Toast.LENGTH_SHORT).show()
    }

    override fun showNoWebsiteToOpen() {
        Toast.makeText(activity, R.string.could_not_open_website, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onViewDetached()
    }

}