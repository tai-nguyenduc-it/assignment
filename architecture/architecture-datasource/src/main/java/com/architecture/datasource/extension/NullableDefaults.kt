package com.architecture.datasource.extension

fun Boolean?.orFalse() = this == true

fun Boolean?.orTrue() = this ?: true

fun Int?.orZero() = this ?: 0

fun Long?.orZero() = this ?: 0

fun Float?.orZero() = this ?: 0F

fun Double?.orZero() = this ?: 0

fun Char?.orEmpty() = this ?: ""
