package com.example.helloworkflow

data class Config(
    val splashVideoUrl: String?,
    val homeTabs: List<Tab>
)

data class Tab(
    val title: String
)