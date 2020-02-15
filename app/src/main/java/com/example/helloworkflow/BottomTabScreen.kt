package com.example.helloworkflow

class BottomTabScreen(
    val screen: Any,
    val tabs: List<BottomTab>,
    val onTabSelected: (position: Int) -> Unit
)

data class BottomTab(
    val title: String,
    val selected: Boolean
)