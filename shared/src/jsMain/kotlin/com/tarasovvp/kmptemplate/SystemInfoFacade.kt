package com.tarasovvp.kmptemplate


private val core = SystemInfo()

@OptIn(ExperimentalJsExport::class)
@JsExport
fun getInfo(): String = core.getInfo()