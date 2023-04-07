package com.example.repositorieschallenge.presentation

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.repositorieschallenge.R
import com.example.repositorieschallenge.component.InfoViewState
import com.example.repositorieschallenge.databinding.RepositoriesActivityBinding
import com.example.repositorieschallenge.domain.model.RepositoriesModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class RepositoriesActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
    private val binding by lazy {
        RepositoriesActivityBinding.inflate(layoutInflater)
    }

    private val repositoriesAdapter: RepositoriesAdapter by lazy { RepositoriesAdapter() }
    private val loadStateAdapter: LoadStateAdapter by lazy { LoadStateAdapter() }

    private lateinit var lManager: StaggeredGridLayoutManager
    private val repositoriesViewModel: RepositoriesViewModel by viewModel()

    private lateinit var repositoriesList: ArrayList<RepositoriesModel>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

        lManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        setContentView(binding.root)
        setupView()
    }

    private fun setupView() {
        setupRecyclewView()
        setListeners()
        setupObservers()
        setupSearchView()
    }

    private fun setupSearchView() {
        binding.searchLayout.searchView.setOnQueryTextListener(this)
    }

    private fun setupObservers() {
        repositoriesViewModel.repositoriesOb.observe(
            this,
            Observer(::handleRepository)
        )
        repositoriesViewModel.loadingOB.observe(
            this,
            Observer(::handleLoading)
        )
        repositoriesViewModel.repositoriesObExceptionOb.observe(
            this,
            Observer(::handleError)
        )
    }

    private fun handleLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.shimmerViewContainer.visibility = View.VISIBLE
            binding.shimmerViewContainer.startShimmer()
            binding.repositoriesRecyclerView.visibility = View.GONE
            binding.infoView.visibility = View.GONE
        } else {
            binding.shimmerViewContainer.visibility = View.GONE
            binding.shimmerViewContainer.stopShimmer()
        }
    }

    private fun handleRepository(repositories: List<RepositoriesModel>) {
        repositoriesList = repositories as ArrayList<RepositoriesModel>
        binding.shimmerViewContainer.stopShimmer()
        binding.shimmerViewContainer.visibility = View.GONE
        binding.repositoriesRecyclerView.visibility = View.VISIBLE
        repositoriesAdapter.addData(repositories)
    }

    private fun handleError(exception: Exception?) {
        binding.infoView.visibility = View.VISIBLE
        binding.infoView.setState(
            InfoViewState(
                iconRes = R.drawable.reload,
                title = getString(R.string.title_error),
                description = getString(R.string.description_error),
                iconAction = repositoriesViewModel::retry
            )
        )
    }

    private fun setupRecyclewView() = with(binding.repositoriesRecyclerView) {
        repositoriesAdapter.apply {
            setLayoutManager(lManager)
        }

        layoutManager = lManager
        adapter = repositoriesAdapter
    }

    private fun setListeners() {
        binding.changeListBtn.setOnClickListener {
            if (lManager.spanCount == 1) {
                lManager.spanCount = 2
                binding.changeListBtn.setImageResource(R.drawable.ic_list_grid)
            } else {
                lManager.spanCount = 1
                binding.changeListBtn.setImageResource(R.drawable.ic_grid_list)
            }
            repositoriesAdapter.notifyItemRangeChanged(0, repositoriesAdapter.itemCount ?: 0)
        }
        binding.repositoriesRecyclerView.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (!recyclerView.canScrollVertically(1) && lManager.childCount > 0) {
                    repositoriesViewModel.loadMoreCharacters()
                }
            }
        })
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        repositoriesAdapter.filter.filter(query)
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        repositoriesAdapter.filter.filter(newText?.uppercase())
        return false
    }
}