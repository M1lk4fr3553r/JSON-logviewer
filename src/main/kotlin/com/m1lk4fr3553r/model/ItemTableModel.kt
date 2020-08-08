package com.m1lk4fr3553r.model

import org.json.JSONObject
import javax.swing.table.AbstractTableModel

class ItemTableModel(private var items: List<JSONListItem> = listOf(JSONListItem(JSONObject()))): AbstractTableModel() {
    val columns: List<String> = listOf("Timestamp", "Level", "Message")
    override fun getRowCount(): Int {
        return items.size
    }

    override fun getColumnCount(): Int {
        return columns.size
    }

    override fun getValueAt(rowIndex: Int, columnIndex: Int): Any {
        val item = items[rowIndex]
        return when (columnIndex) {
            0 -> item.timestamp ?: "null"
            1 -> item.level ?: "null"
            2 -> item.message ?: "null"
            else -> ""
        }
    }

    override fun getColumnName(column: Int): String {
        return columns[column]
    }

    override fun getColumnClass(columnIndex: Int): Class<*> {
        return JSONListItem::class.java
    }

    fun setData(data: Array<JSONListItem>) {
        items = data.toList()
        fireTableDataChanged()
    }

    fun getItem(row: Int): JSONListItem? {
        return items[row]
    }
}