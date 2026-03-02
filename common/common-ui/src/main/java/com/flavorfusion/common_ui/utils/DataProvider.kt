package com.flavorfusion.common_ui.utils

interface DataProvider<out T> {
    fun provideData(): T
}