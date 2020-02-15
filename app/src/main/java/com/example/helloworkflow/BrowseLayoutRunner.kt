package com.example.helloworkflow

import android.view.View
import android.widget.TextView
import com.squareup.workflow.ui.ContainerHints
import com.squareup.workflow.ui.LayoutRunner
import com.squareup.workflow.ui.ViewBinding

class BrowseLayoutRunner(view: View) : LayoutRunner<BrowseWorkflow.Rendering> {

    private val label = view.findViewById<TextView>(R.id.title)

    override fun showRendering(
        rendering: BrowseWorkflow.Rendering,
        containerHints: ContainerHints
    ) {
        label.text = rendering.title
    }

    companion object : ViewBinding<BrowseWorkflow.Rendering> by LayoutRunner.bind(
        R.layout.browse_layout, ::BrowseLayoutRunner
    )
}