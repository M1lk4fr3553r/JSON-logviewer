package com.m1lk4fr3553r.model

import javax.swing.table.DefaultTableModel

class SettingsTableModel(headers: Array<Any>, rows: Int) : DefaultTableModel(headers, rows) {
    override fun isCellEditable(row: Int, column: Int): Boolean {
        return column != 0
    }
}