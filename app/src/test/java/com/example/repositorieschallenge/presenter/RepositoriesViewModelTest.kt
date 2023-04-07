package com.example.repositorieschallenge.presenter

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.repositorieschallenge.domain.RepositoriesInteractor
import com.example.repositorieschallenge.domain.model.RepositoriesModel
import com.example.repositorieschallenge.mockRepositories
import com.example.repositorieschallenge.mockRepository
import com.example.repositorieschallenge.presentation.RepositoriesUiModel
import com.example.repositorieschallenge.presentation.RepositoriesViewModel
import io.mockk.Runs
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class RepositoriesViewModelTest {
    private val interactor: RepositoriesInteractor = mockk()
    private val repositoriesUiModel: RepositoriesUiModel = mockk()

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        setupViewModel()
    }

    @get:Rule
    val coroutineRule = InstantTaskExecutorRule()

    private fun setupViewModel() {
        val response = ArrayList<RepositoriesModel>()
        response.add(mockRepository)
        every {
            runBlocking {
                interactor.getRepositories("1")
            }
        } returns response

        every { repositoriesUiModel.getLocalRepositories() } returns mockRepositories
    }

    @Test
    fun `get repositories return success`() = runBlocking {
        setupViewModel()
        val viewModel = RepositoriesViewModel(interactor, repositoriesUiModel)

        Assert.assertNotNull(viewModel.repositoriesOb.value)
        Assert.assertNotNull(viewModel.loadingOB.value)

    }
}