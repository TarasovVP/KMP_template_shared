package com.tarasovvp.kmptemplate

class JsPlatform : Platform {

    override val name: String =
        buildString {
            append("JS ")
            val agent: String? = js(
                "typeof navigator !== 'undefined' ? navigator.userAgent : null"
            ) as String?
            append(agent ?: "Node")
        }
}

actual fun getPlatform(): Platform = JsPlatform()