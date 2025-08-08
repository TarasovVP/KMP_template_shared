package com.tarasovvp.kmptemplate

interface PlatformHelper {
    val name: String
}

expect fun getPlatform(): PlatformHelper