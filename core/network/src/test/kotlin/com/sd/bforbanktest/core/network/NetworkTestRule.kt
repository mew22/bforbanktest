package com.sd.bforbanktest.core.network

import io.mockk.every
import io.mockk.mockk
import okhttp3.mockwebserver.MockWebServer
import org.junit.rules.TestWatcher
import org.junit.runner.Description

internal class NetworkTestRule : TestWatcher() {

    val server = MockWebServer()
    val environmentGateway = mockEnvironment()
    private val api = mockk<Api>()
    val component = DaggerTestComponent.factory().create(
        environmentGateway,
        api,
    )

    override fun starting(description: Description) {
        server.start()
        every { environmentGateway.isProd } returns false
        every { api.invoke() } returns server.url("/v1/test/").toString()
    }

    override fun finished(description: Description) {
        server.shutdown()
    }
}
