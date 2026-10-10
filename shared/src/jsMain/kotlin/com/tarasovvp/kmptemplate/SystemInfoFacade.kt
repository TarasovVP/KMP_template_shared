package com.tarasovvp.kmptemplate

@OptIn(ExperimentalJsExport::class)
@JsExport
fun getInfo(): String = SystemInfo().getInfo()

@OptIn(ExperimentalJsExport::class)
@JsExport
fun getDeviceModel(): String = SystemInfo().getDeviceModel()