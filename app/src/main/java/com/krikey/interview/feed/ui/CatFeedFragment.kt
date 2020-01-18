package com.krikey.interview.feed.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.krikey.interview.R
import com.krikey.interview.arch.impl.android.AndroidModule
import com.krikey.interview.feed.CatFeedContract
import com.krikey.interview.feed.di.CatFeedComponent
import com.krikey.interview.feed.di.DaggerCatFeedComponent
import com.krikey.interview.feed.model.CatFeedPresentationModel
import com.krikey.interview.search.ui.SearchBreedFragment
import kotlinx.android.synthetic.main.cat_feed_fragment.*
import kotlinx.android.synthetic.main.toolbar.*

class CatFeedFragment : Fragment(), CatFeedContract.View {

    private val component: CatFeedComponent by lazy {
        DaggerCatFeedComponent
            .builder()
            .androidModule(AndroidModule(context!!))
            .build()
    }

    private val presenter: CatFeedContract.Presenter by lazy { component.presenter }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
        = inflater.inflate(R.layout.cat_feed_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.title = getString(com.krikey.interview.R.string.app_name)
        (activity as? AppCompatActivity)?.setSupportActionBar(toolbar)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(context!!, LinearLayoutManager.VERTICAL))
        }
        presenter.apply {
            onViewAttached(this@CatFeedFragment)
            fetchCats()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.cat_feed, menu)
        menu.findItem(R.id.search).apply {
            setIcon(android.R.drawable.ic_menu_search)
            setOnMenuItemClickListener {
                presenter.onSearchClicked()
                true
            }
        }
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun goToSearch() {
        activity?.supportFragmentManager?.beginTransaction()?.addToBackStack(SearchBreedFragment::class.java.simpleName)?.replace(fragmentContainer.id,
            SearchBreedFragment.newInstance())?.commit()
    }

    override fun onCatsRetrieved(catFeed: CatFeedPresentationModel) {
        recyclerView.adapter = CatFeedImageAdapter(catFeed.catImages, component.imageLoader)
    }

    @Suppress("REDUNDANT_ELSE_IN_WHEN")
    override fun onError(error: CatFeedContract.Error) {
        Toast.makeText(context, when (error) {
            CatFeedContract.Error.NETWORK_UNAVAILABLE -> getString(com.krikey.interview.R.string.network_unavailable)
            else -> error.name
        }, Toast.LENGTH_LONG).show()
    }

    override fun onResume() {
        super.onResume()
        activity?.title = getString(com.krikey.interview.R.string.app_name)
    }

    override fun onDestroy() {
        super.onDestroy()
        component.presenter.onViewDetached()
    }

}