package com.tarasovvp.kmptemplate

interface PlatformHelper {
    val platformIdentifier: String
}

expect fun getPlatform(): PlatformHelper