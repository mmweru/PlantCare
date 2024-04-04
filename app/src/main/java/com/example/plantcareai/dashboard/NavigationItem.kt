package com.example.plantcareai.dashboard

sealed class NavigationItem {
    object Home : NavigationItem()
    object Logout : NavigationItem()
    object About : NavigationItem()
    object History : NavigationItem()
    object Camera : NavigationItem()
}