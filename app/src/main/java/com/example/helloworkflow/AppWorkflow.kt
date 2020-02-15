package com.example.helloworkflow

import com.example.helloworkflow.AppWorkflow.State
import com.squareup.workflow.*

object AppWorkflow : StatefulWorkflow<Unit, State, Nothing, Any>() {

    data class State(
        val config: Config?
    )

    private val splashWorkflow = SplashWorkflow
    private val homeWorkflow = HomeWorkflow

    private class ConfigLoaded(private val config: Config) : WorkflowAction<State, Nothing> {

        override fun WorkflowAction.Updater<State, Nothing>.apply() {
            nextState = State(config)
        }
    }

    override fun render(props: Unit, state: State, context: RenderContext<State, Nothing>): Any {
        val config = state.config
        return if (config == null) {
            context.renderChild(splashWorkflow) {
                ConfigLoaded(it)
            }
        } else {
            context.renderChild(homeWorkflow, config)
        }
    }

    override fun initialState(props: Unit, snapshot: Snapshot?): State = State(null)

    override fun snapshotState(state: State): Snapshot = Snapshot.EMPTY
}