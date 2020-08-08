package com.m1lk4fr3553r.ui.view

import com.m1lk4fr3553r.model.SettingsTableModel
import javax.swing.JScrollPane
import javax.swing.JTable

class SettingsPanel(displayableSettings: DisplayableSettings, val table: JTable = JTable()) : JScrollPane(table) {
    init {
        table.dragEnabled = false
        table.model = SettingsTableModel(arrayOf("Setting", "Value"), 0)
        val keys = displayableSettings.getKeys()
        val values = displayableSettings.getValues()
        for (i in keys.indices) {
            (table.model as SettingsTableModel).addRow(arrayOf(keys[i], values[i]))
        }
    }
}