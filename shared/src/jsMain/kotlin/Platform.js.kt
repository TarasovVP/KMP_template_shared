class JsPlatform : Platform {

    override val name: String = "JS ${js("navigator.userAgent") as? String ?: "Node"}"
}

actual fun getPlatform(): Platform = JsPlatform()