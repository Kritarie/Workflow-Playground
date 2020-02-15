package com.example.helloworkflow

import com.example.helloworkflow.HomeWorkflow.State
import com.squareup.workflow.*

typealias HomeTabs = List<BottomTab>

object HomeWorkflow : StatefulWorkflow<Config, State, Nothing, BottomTabScreen>() {

    data class State(
        val tabs: HomeTabs,
        val selected: Int
    )

    override fun render(
        props: Config,
        state: State,
        context: RenderContext<State, Nothing>
    ): BottomTabScreen {
        val selectedTab = state.tabs[state.selected]
        val screen = context.renderChild(BrowseWorkflow, selectedTab.title)
        return BottomTabScreen(
            screen = screen,
            tabs = state.tabs,
            onTabSelected = { context.actionSink.send(selectTabAction(it)) }
        )
    }

    private fun selectTabAction(position: Int) = action {
        nextState = nextState.copy(
            tabs = nextState.tabs.mapIndexed { index, bottomTab ->
                bottomTab.copy(selected = index == position)
            },
            selected = position
        )
    }

    override fun initialState(props: Config, snapshot: Snapshot?): State {
        return State(
            tabs = props.homeTabs.mapIndexed { index, tab ->
                BottomTab(
                    title = tab.title,
                    selected = index == 0
                )
            },
            selected = 0
        )
    }

    override fun snapshotState(state: State) = Snapshot.EMPTY

}