package com.example.repositorieschallenge.domain

import com.example.repositorieschallenge.data.RepositoriesRepository
import com.example.repositorieschallenge.data.model.RepositoriesResponseModel
import com.example.repositorieschallenge.domain.model.RepositoriesModel
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class RepositoriesInteractorTest {
    private val repository: RepositoriesRepository = mockk()
    private val mapper: RepositoriesMapper = mockk()

    private val repositoriesInteractor = RepositoriesInteractorImpl(repository, mapper)

    @Test
    fun `get repositories return success`() {
        val response = mockk<RepositoriesResponseModel>()
        val mappedResponse = ArrayList<RepositoriesModel>()

        every {
            runBlocking {
                repository.getRepositories("1")
            }
        } returns response

        every {
            runBlocking {
                repository.getRepositories("1")
            }
        } returns response

        every {
            runBlocking {
                mapper.toRepositories(
                    any()
                )
            }
        } returns mappedResponse

        runBlocking {
            val result = repositoriesInteractor.getRepositories("1")
            Assert.assertTrue(result.isEmpty())
        }
    }
}