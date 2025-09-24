package com.fahimdev.shared

class DesktopPlatform : Platform {
    override val name: String = "Desktop ${System.getProperty("os.name")} ${System.getProperty("os.version")}"
}

actual fun getPlatform(): Platform = DesktopPlatform()