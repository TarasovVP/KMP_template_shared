package com.tarasovvp.kmptemplate

@OptIn(ExperimentalJsExport::class)
@JsExport
fun getInfo(): String = SystemInfo().getInfo()

@OptIn(ExperimentalJsExport::class)
@JsExport
fun getDeviceModel(): String = SystemInfo().getDeviceModel()

@OptIn(ExperimentalJsExport::class)
@JsExport
fun getOsVersion(): String = SystemInfo().getOsVersion()