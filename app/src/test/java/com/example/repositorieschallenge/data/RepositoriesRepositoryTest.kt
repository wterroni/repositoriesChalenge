package com.example.repositorieschallenge.data

import com.example.repositorieschallenge.data.model.RepositoriesResponseModel
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class RepositoriesRepositoryTest {
    private val service: RepositoriesService = mockk()

    private val repository = RepositoriesRepositoryImpl(service)

    @Test
    fun `get repositories return success`() {
        val response = mockk<RepositoriesResponseModel>()
        every {
            runBlocking {
                service.getRepositories("1")
            }
        } returns response

        runBlocking {
            val result = repository.getRepositories("1")
            Assert.assertEquals(result, response)
        }
    }
}