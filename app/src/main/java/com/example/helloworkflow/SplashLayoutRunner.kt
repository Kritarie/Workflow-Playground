package com.example.helloworkflow

import android.view.View
import android.widget.TextView
import com.squareup.workflow.ui.ContainerHints
import com.squareup.workflow.ui.LayoutRunner
import com.squareup.workflow.ui.ViewBinding

class SplashLayoutRunner(view: View) : LayoutRunner<SplashWorkflow.Rendering> {

    private val label = view.findViewById<TextView>(R.id.label)

    override fun showRendering(
        rendering: SplashWorkflow.Rendering,
        containerHints: ContainerHints
    ) {
        label.text = rendering.videoUrl
    }

    companion object : ViewBinding<SplashWorkflow.Rendering> by LayoutRunner.bind(
        R.layout.splash_layout, ::SplashLayoutRunner
    )
}