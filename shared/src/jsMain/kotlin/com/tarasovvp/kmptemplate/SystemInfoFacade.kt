package com.tarasovvp.kmptemplate

@OptIn(ExperimentalJsExport::class)
@JsExport
fun getInfo(): String = SystemInfo().getInfo()