package com.tarasovvp.kmptemplate

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform