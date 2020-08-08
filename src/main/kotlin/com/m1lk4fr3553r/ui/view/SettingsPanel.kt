package com.m1lk4fr3553r.ui.view

import javax.swing.JScrollPane
import javax.swing.JTable
import javax.swing.table.DefaultTableModel

class SettingsPanel(displayableSettings: DisplayableSettings, table: JTable = JTable()) : JScrollPane(table) {
    init {
        table.dragEnabled = false
        table.model = DefaultTableModel(arrayOf("Setting", "Value"), 0)
        val keys = displayableSettings.getKeys()
        val values = displayableSettings.getValues()
        for (i in 0..keys.size - 1) {
            (table.model as DefaultTableModel).addRow(arrayOf(keys[i], values[i]))
        }
    }
}