package com.sd.bforbank.core.environment

import android.os.Build
import com.sd.bforbanktest.core.environment.implementation.BuildConfig
import javax.inject.Inject

internal class Environment @Inject constructor() : EnvironmentGateway {

    override val isDebug: Boolean
        get() = BuildConfig.DEBUG

    override val isRelease: Boolean
        get() = BuildConfig.BUILD_TYPE.contains("release")

    override val isMock: Boolean
        get() = BuildConfig.FLAVOR == "mock"

    override val isProd: Boolean
        get() = BuildConfig.FLAVOR == "prod"

    override val androidVersion: Int
        get() = Build.VERSION.SDK_INT

    override val flavorName: String
        get() = BuildConfig.FLAVOR
}
