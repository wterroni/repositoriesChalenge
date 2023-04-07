package com.example.repositorieschallenge.presentation

import androidx.lifecycle.MutableLiveData
import com.example.repositorieschallenge.common.BaseViewModel
import com.example.repositorieschallenge.domain.RepositoriesInteractor
import com.example.repositorieschallenge.domain.model.RepositoriesModel
import kotlinx.coroutines.launch

class RepositoriesViewModel(private val interactor: RepositoriesInteractor): BaseViewModel() {

    val repositoriesOb = MutableLiveData<List<RepositoriesModel>>()
    val loadingOB = MutableLiveData<Boolean>()
    val repositoriesObExceptionOb = MutableLiveData<Exception>()

    init {
        getRepositories()
    }

    private fun getRepositories() {
        launch {
            try {
                val repositories = interactor.getRepositories()
                repositoriesOb.value = repositories
                loadingOB.value = false
            } catch (ex: Exception) {
                repositoriesObExceptionOb.value = ex
                loadingOB.value = false
            }
        }
    }

    fun retry() {
        loadingOB.value = true
        getRepositories()
    }
}