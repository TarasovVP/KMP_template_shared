package com.tarasovvp.kmptemplate

import platform.UIKit.UIDevice

class IOSPlatformHelper : PlatformHelper {
    override val platformIdentifier: String =
        "${UIDevice.currentDevice.systemName()} " +
                UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): PlatformHelper = IOSPlatformHelper()