package com.sd.bforbanktest.core.network

import com.sd.bforbank.core.environment.EnvironmentGateway
import io.mockk.every
import io.mockk.mockk

fun mockEnvironment() = mockk<EnvironmentGateway> {
    every { isDebug } returns false
    every { isMock } returns false
    every { androidVersion } returns 27
}
