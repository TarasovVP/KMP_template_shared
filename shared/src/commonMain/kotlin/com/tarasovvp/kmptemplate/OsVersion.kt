package com.tarasovvp.kmptemplate

/**
 * Platform-specific operating system name and version. Internal on purpose: the public entry point is
 * [SystemInfo.getOsVersion], so the public surface (Swift/Java/JS) stays a single method.
 * Implementations must never throw and must always return a non-empty string.
 */
internal expect fun platformOsVersion(): String
