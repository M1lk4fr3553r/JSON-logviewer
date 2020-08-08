package com.m1lk4fr3553r.ui.view

import com.m1lk4fr3553r.model.ItemTableModel
import com.m1lk4fr3553r.util.Util
import com.m1lk4fr3553r.model.JSONListItem
import java.awt.Color
import java.awt.Component
import javax.swing.DefaultListCellRenderer
import javax.swing.JList
import javax.swing.JTable
import javax.swing.table.DefaultTableCellRenderer

class ItemTableCellRenderer(private val mainWindow: MainWindow) : DefaultTableCellRenderer() {
    override fun getTableCellRendererComponent(table: JTable?, value: Any?, isSelected: Boolean, hasFocus: Boolean, row: Int, column: Int): Component {
        val props = Util.getProperties()
        val infoColor = Color.decode(props.color.info)
        val warnColor = Color.decode(props.color.warn)
        val errorColor = Color.decode(props.color.error)
        val debugColor = Color.decode(props.color.debug)
        val searchColor = Color.decode(props.color.search)

        val item = (table?.model as ItemTableModel).getItem(row)

        val component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column)
        if (column == 1 || props.color.colorEntireRow) {
            when (item?.level) {
                "INFO" -> component.foreground = infoColor
                "WARN" -> component.foreground = warnColor
                "ERROR" -> component.foreground = errorColor
                "DEBUG" -> component.foreground = debugColor
            }
        } else {
            component.foreground = Color.BLACK
        }

        if (!isSelected && mainWindow.searchField.text != "" && item!!.raw.contains(mainWindow.searchField.text.toLowerCase())) {
            component.background = searchColor
        } else if (!isSelected) {
            component.background = Color.WHITE
        }
        return component
    }


}
