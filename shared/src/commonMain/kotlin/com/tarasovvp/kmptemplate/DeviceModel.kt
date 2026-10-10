package com.tarasovvp.kmptemplate

/**
 * Platform-specific device model string. Internal on purpose: the public entry point is
 * [SystemInfo.getDeviceModel], so the public surface (Swift/Java/JS) stays a single method.
 */
internal expect fun platformDeviceModel(): String
