package com.example.clarotest.domain.use_cases

import com.example.clarotest.common.Resource
import com.example.clarotest.data.repository.FakeEntriesRepository
import com.example.clarotest.domain.models.EntryDetails
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetEntriesUseCaseTest {
    private lateinit var getEntriesUseCase: GetEntriesUseCase
    private lateinit var fakeEntriesRepository: FakeEntriesRepository

    @Before
    fun setUp() {
        fakeEntriesRepository = FakeEntriesRepository()
        getEntriesUseCase = GetEntriesUseCase(fakeEntriesRepository)
    }

    @Test
    fun `Get all Entries`() = runBlocking{
        // Arrange
        val entries = listOf(
            EntryDetails("AdoptAPet", "Resource to help get pets adopted", "apiKey", true, "yes", "https://www.adoptapet.com/public/apis/pet_list.html", "Animals"),
            EntryDetails("Axolotl", "Collection of axolotl pictures and facts", "", true, "no", "https://theaxolotlapi.netlify.app/", "Animals")
        )

        val actual = getEntriesUseCase().first()

        val expected = Resource.Success(entries)

        assertEquals(expected.data, actual.data)
    }

    @Test
    fun `Check the first element are equal`() = runBlocking{
        // Arrange
        val entries = listOf(
            EntryDetails("AdoptAPet", "Resource to help get pets adopted", "apiKey", true, "yes", "https://www.adoptapet.com/public/apis/pet_list.html", "Animals"),
            EntryDetails("Axolotl", "Collection of axolotl pictures and facts", "", true, "no", "https://theaxolotlapi.netlify.app/", "Animals")
        )

        val actual = getEntriesUseCase().first()

        val expected = Resource.Success(entries)

        assertEquals(expected.data?.get(0)?.API ?: null, actual.data?.get(0)?.API ?: null)
    }

    @Test
    fun `Check is data are equal`() = runBlocking{
        // Arrange
        val entries = listOf(
            EntryDetails("AdoptAPet", "Resource to help get pets adopted", "apiKey", true, "yes", "https://www.adoptapet.com/public/apis/pet_list.html", "Animals"),
            EntryDetails("Axolotl", "Collection of axolotl pictures and facts", "", true, "no", "https://theaxolotlapi.netlify.app/", "Animals")
        )

        val expected = Resource.Success(entries)
        val actual = getEntriesUseCase().first()
        val equal = expected.data == actual.data

        assertEquals(true, equal)
    }
}