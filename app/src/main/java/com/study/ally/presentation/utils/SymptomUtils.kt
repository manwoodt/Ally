package com.study.ally.presentation.utils

fun Float.toIntensityText(): String {
    return when (this.toInt()) {
        1 -> "Слабая"
        2 -> "Средняя"
        3 -> "Сильная"
        else -> "Неизвестно"
    }
}