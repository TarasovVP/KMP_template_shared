package com.vnteam.kmptemplate

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform