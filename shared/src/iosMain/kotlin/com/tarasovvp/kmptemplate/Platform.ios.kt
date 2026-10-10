package com.tarasovvp.kmptemplate

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.alloc
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.ptr
import kotlinx.cinterop.toKString
import platform.Foundation.NSProcessInfo
import platform.UIKit.UIDevice
import platform.posix.uname
import platform.posix.utsname

class IOSPlatformHelper : PlatformHelper {
    override val platformIdentifier: String =
        "${UIDevice.currentDevice.systemName()} " +
                UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): PlatformHelper = IOSPlatformHelper()

/**
 * Hardware model identifier (e.g. "iPhone15,2"). On the simulator uname reports the host
 * arch, so the simulated model from SIMULATOR_MODEL_IDENTIFIER is used instead.
 * Falls back to UIDevice.model ("iPhone"/"iPad").
 */
@OptIn(ExperimentalForeignApi::class)
internal actual fun platformDeviceModel(): String {
    val simulatorModel = NSProcessInfo.processInfo.environment["SIMULATOR_MODEL_IDENTIFIER"] as? String
    if (!simulatorModel.isNullOrBlank()) return simulatorModel
    val machine = memScoped {
        val info = alloc<utsname>()
        if (uname(info.ptr) == 0) info.machine.toKString() else ""
    }
    return machine.ifBlank { UIDevice.currentDevice.model }
}
