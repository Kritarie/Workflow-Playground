package com.example.helloworkflow

import android.view.View
import com.squareup.workflow.ui.ContainerHints
import com.squareup.workflow.ui.LayoutRunner
import com.squareup.workflow.ui.ViewBinding
import com.squareup.workflow.ui.WorkflowViewStub

class BottomTabLayoutRunner(view: View) : LayoutRunner<BottomTabScreen> {

    private val screenStub = view.findViewById<WorkflowViewStub>(R.id.bottom_tab_stub)
    private val bottomTabBar = view.findViewById<BottomTabBar>(R.id.bottom_tab_bar)

    override fun showRendering(
        rendering: BottomTabScreen,
        containerHints: ContainerHints
    ) {
        screenStub.update(rendering.screen, containerHints)
        bottomTabBar.update(rendering.tabs)
        bottomTabBar.onTabSelected(rendering.onTabSelected)
    }

    companion object : ViewBinding<BottomTabScreen> by LayoutRunner.bind(
        R.layout.bottom_tab_layout, ::BottomTabLayoutRunner
    )
}