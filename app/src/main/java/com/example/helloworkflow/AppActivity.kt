package com.example.helloworkflow

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.squareup.workflow.ui.ViewRegistry
import com.squareup.workflow.ui.WorkflowRunner
import com.squareup.workflow.ui.setContentWorkflow

class AppActivity : AppCompatActivity() {

    private val viewRegistry = ViewRegistry(SplashLayoutRunner, BottomTabLayoutRunner, BrowseLayoutRunner)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentWorkflow(viewRegistry) {
            WorkflowRunner.Config(AppWorkflow)
        }
    }
}