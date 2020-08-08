package com.m1lk4fr3553r.ui.controller

import com.m1lk4fr3553r.model.SettingsTableModel
import com.m1lk4fr3553r.ui.view.DisplayableSettings
import com.m1lk4fr3553r.ui.view.SettingsPanel
import com.m1lk4fr3553r.ui.view.SettingsWindow
import com.m1lk4fr3553r.util.Util

class SettingsWindowController(private val view: SettingsWindow) {
    val properties = Util.getProperties()
    private val keyPanel = SettingsPanel(properties.key)
    private val colorPanel = SettingsPanel(properties.color)
    private val miscPanel = SettingsPanel(properties.misc)
    init {
        view.jTabbedPane.addTab("Keys", keyPanel)
        view.jTabbedPane.addTab("Colors", colorPanel)
        view.jTabbedPane.addTab("MISC", miscPanel)
    }

    fun saveSettings() {
        modelToProperties(keyPanel.table.model as SettingsTableModel, properties.key)
        modelToProperties(colorPanel.table.model as SettingsTableModel, properties.color)
        modelToProperties(miscPanel.table.model as SettingsTableModel, properties.misc)
        properties.store()
        view.dispose()
    }

    fun modelToProperties(model: SettingsTableModel, properties: DisplayableSettings) {
        var values: Array<String> = emptyArray()
        for(i in 0 until model.rowCount) {
            values = values.plus(model.getValueAt(i, 1) as String)
        }
        properties.setValues(values)
    }
}