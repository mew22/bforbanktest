package com.sd.bforbanktest.core.network

import io.mockk.every
import io.mockk.isMockKMock
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertTrue

internal class NetworkTest {

    @get:Rule
    val testRule = NetworkTestRule()

    @Test
    fun `Service method is not suspend`() {
        // When
        val service = createTestService()

        // Then
        assertFailsWith<IllegalArgumentException> {
            service.getTodosSync()
        }
    }

    @Test
    fun `Service method does not return Result type`() = runTest {
        // When
        val service = createTestService()

        // then
        assertFailsWith<IllegalArgumentException> {
            service.getTodosWithoutResult()
        }
    }

    @Test
    fun `Create real service`() {
        // When
        val service = createTestService()

        // Then
        assertIs<TestService>(service)
        assertFalse(isMockKMock(service))
    }

    @Test
    fun `Create mock service`() {
        // Given
        every { testRule.environmentGateway.isMock } returns true

        // When
        val service = createTestService()

        // Then
        assertIs<TestService>(service)
        assertTrue(isMockKMock(service))
    }

    @Test
    fun `Return successful response from server`() = runTest {
        // Given
        val mockResponse = MockResponse().setBody(sampleResponseBody)
        testRule.server.enqueue(mockResponse)

        // When
        val service = createTestService()
        val result = service.getTodos()

        // Then
        assertTrue(result.isSuccess)
        assertContentEquals(sampleResponse, result.getOrNull())
    }

    @Test
    fun `Return error response from server`() = runTest {
        // Given
        val mockResponse = MockResponse().setResponseCode(500)
        testRule.server.enqueue(mockResponse)

        // When
        val service = createTestService()
        val result = service.getTodos()

        // Then
        assertTrue(result.isFailure)
        val exception = result.exceptionOrNull()
        assertIs<NetworkException>(exception)
        assertEquals(500, exception.httpCode)
    }

    private fun createTestService(): TestService =
        testRule.component.networkBuilder.build().create { mockk() }
}
