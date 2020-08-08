package com.m1lk4fr3553r.ui.controller

import com.m1lk4fr3553r.ui.view.SettingsPanel
import com.m1lk4fr3553r.ui.view.SettingsWindow
import com.m1lk4fr3553r.util.Util
import javax.swing.JPanel

class SettingsWindowController(private val view: SettingsWindow) {
    val properties = Util.getProperties()
    init {
        view.jTabbedPane.addTab("Keys", SettingsPanel(properties.key))
        view.jTabbedPane.addTab("Colors", SettingsPanel(properties.color))
        view.jTabbedPane.addTab("MISC", SettingsPanel(properties.misc))
    }
}