package com.m1lk4fr3553r.ui.view

import com.m1lk4fr3553r.model.JSONListItem
import com.m1lk4fr3553r.ui.controller.SettingsWindowController
import com.m1lk4fr3553r.util.plus
import org.apache.commons.text.StringEscapeUtils
import javax.swing.JFrame
import javax.swing.JScrollPane
import javax.swing.JTabbedPane
import javax.swing.JTextArea

class SettingsWindow(parent: JFrame) : JFrame() {
    val jTabbedPane = JTabbedPane()

    private val controller = SettingsWindowController(this)

    init {
        title = "Settings"
        size = parent.size
        defaultCloseOperation = DISPOSE_ON_CLOSE
        setLocationRelativeTo(parent)
        add(jTabbedPane)
        isVisible = true
    }
}
