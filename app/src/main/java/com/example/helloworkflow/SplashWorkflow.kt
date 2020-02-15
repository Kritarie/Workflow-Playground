package com.example.helloworkflow

import com.example.helloworkflow.SplashWorkflow.Action.*
import com.example.helloworkflow.SplashWorkflow.State.*
import com.squareup.workflow.*
import com.squareup.workflow.rx2.asWorker
import io.reactivex.Single
import java.util.concurrent.TimeUnit

object SplashWorkflow : StatefulWorkflow<Unit, SplashWorkflow.State, Config, SplashWorkflow.Rendering>() {

    sealed class State {
        object ConfigLoading : State()
        data class ConfigLoaded(val config: Config) : State()
    }

    data class Rendering(
        val videoUrl: String? = null
    )

    private sealed class Action : WorkflowAction<State, Config> {

        class HandleConfigLoaded(val config: Config) : Action()

        class SplashCompleted(val config: Config) : Action()

        override fun WorkflowAction.Updater<State, Config>.apply() {
            when (this@Action) {
                is HandleConfigLoaded -> {
                    nextState = ConfigLoaded(config)
                }

                is SplashCompleted -> {
                    setOutput(config)
                }
            }
        }
    }

    override fun initialState(props: Unit, snapshot: Snapshot?): State = ConfigLoading

    override fun render(
        props: Unit,
        state: State,
        context: RenderContext<State, Config>
    ): Rendering {
        return when (state) {
            is ConfigLoading -> {
                context.runningWorker(ConfigLoader.loadConfig().asWorker()) {
                    HandleConfigLoaded(it)
                }

                Rendering()
            }
            is ConfigLoaded -> {
                context.runningWorker(Single.timer(2, TimeUnit.SECONDS).asWorker()) {
                    SplashCompleted(state.config)
                }

                Rendering(
                    videoUrl = state.config.splashVideoUrl
                )
            }
        }
    }

    override fun snapshotState(state: State): Snapshot = Snapshot.EMPTY

}