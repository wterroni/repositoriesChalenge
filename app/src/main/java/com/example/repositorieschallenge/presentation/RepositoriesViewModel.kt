package com.example.repositorieschallenge.presentation

import androidx.lifecycle.MutableLiveData
import com.example.repositorieschallenge.common.BaseViewModel
import com.example.repositorieschallenge.domain.RepositoriesInteractor
import com.example.repositorieschallenge.domain.model.RepositoriesModel
import kotlinx.coroutines.launch

class RepositoriesViewModel(
    private val interactor: RepositoriesInteractor,
    private val uiModel: RepositoriesUiModel
) : BaseViewModel() {

    val repositoriesOb = MutableLiveData<List<RepositoriesModel>>()
    val loadingOB = MutableLiveData<Boolean>()
    val repositoriesObExceptionOb = MutableLiveData<Exception>()
    private var isMoreCategories = false
    private var pageCount = 1

    init {
        getRepositories()
    }

    private fun getRepositories() {
        launch {
            try {
                val repositories = interactor.getRepositories(pageCount.toString())
                repositoriesOb.value = repositories
                uiModel.removeLocalRepositories(repositories)
                uiModel.saveLocalRepositories(repositories)
                loadingOB.value = false
            } catch (ex: Exception) {
                loadingOB.value = false
                getLocalRepositories(ex)
            }
        }
    }
    private fun getLocalRepositories(ex: Exception) {
        val repositories = uiModel.getLocalRepositories()
        if (repositories.isNotEmpty()) {
            repositoriesOb.value = repositories
        } else {
            repositoriesObExceptionOb.value = ex
        }
    }

    fun loadMoreCategories() {
        pageCount++
        if(!isMoreCategories) {
            getRepositories()
        }
        isMoreCategories = true
    }


    fun retry() {
        loadingOB.value = true
        getRepositories()
    }
}