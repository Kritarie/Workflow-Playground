package com.example.helloworkflow

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.children

typealias TabSelectedHandler = (position: Int) -> Unit

private typealias Tabs = List<BottomTab>

class BottomTabBar
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var tabSelectedHandler: TabSelectedHandler = { }

    init {
        orientation = HORIZONTAL
    }

    fun update(tabs: Tabs) {
        for (n in tabs.indices) {
            var tabView = getChildAt(n) as? TabView
            if (tabView == null) {
                tabView = TabView(context).apply {
                    setOnClickListener { tabSelectedHandler(n) }
                }

                addView(
                    tabView,
                    LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1.0f))
            }

            tabView.visibility = View.VISIBLE
            tabView.update(tabs[n])
        }

        children.drop(tabs.size).forEach { it.visibility = View.GONE }
    }

    fun onTabSelected(tabSelectedHandler: TabSelectedHandler) {
        this.tabSelectedHandler = tabSelectedHandler
    }

}

private class TabView(context: Context) : FrameLayout(context) {

    init {
        background = ColorDrawable(Color.TRANSPARENT)
    }

    private val title: TextView = TextView(context).also {
        addView(it, LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, Gravity.CENTER))
    }

    fun update(tab: BottomTab) {
        title.text = tab.title
        (background as ColorDrawable).color = if (tab.selected) Color.RED else Color.TRANSPARENT
    }

}