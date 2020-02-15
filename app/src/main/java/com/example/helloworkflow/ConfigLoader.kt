package com.example.helloworkflow

import io.reactivex.Single
import java.util.concurrent.TimeUnit

object ConfigLoader {

    fun loadConfig(): Single<Config> = Single.just(createConfig()).delay(5, TimeUnit.SECONDS)

    private fun createConfig(): Config {
        return Config(
            splashVideoUrl = "www.google.com",
            homeTabs = listOf(
                Tab("Home"),
                Tab("Shows"),
                Tab("Sports")
            )
        )
    }

}