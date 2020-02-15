package com.example.helloworkflow

import com.example.helloworkflow.BrowseWorkflow.Rendering
import com.example.helloworkflow.BrowseWorkflow.State
import com.squareup.workflow.RenderContext
import com.squareup.workflow.Snapshot
import com.squareup.workflow.StatefulWorkflow

typealias Title = String

object BrowseWorkflow : StatefulWorkflow<Title, State, Nothing, Rendering>() {

    class State {

    }

    data class Rendering(
        val title: Title
    )

    override fun render(
        props: Title,
        state: State,
        context: RenderContext<State, Nothing>
    ): Rendering {
        return Rendering(
            title = props
        )
    }

    override fun initialState(props: Title, snapshot: Snapshot?): State {
        return State()
    }

    override fun snapshotState(state: State): Snapshot {
        return Snapshot.EMPTY
    }
}