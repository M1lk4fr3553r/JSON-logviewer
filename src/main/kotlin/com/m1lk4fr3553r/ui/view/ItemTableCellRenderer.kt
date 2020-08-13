package com.m1lk4fr3553r.ui.view

import com.m1lk4fr3553r.model.ItemTableModel
import com.m1lk4fr3553r.util.Util
import com.m1lk4fr3553r.model.JSONListItem
import com.m1lk4fr3553r.model.properties.Properties
import java.awt.Color
import java.awt.Component
import javax.swing.JTable
import javax.swing.table.DefaultTableCellRenderer

class ItemTableCellRenderer(private val mainWindow: MainWindow) : DefaultTableCellRenderer() {
    override fun getTableCellRendererComponent(table: JTable?, value: Any?, isSelected: Boolean, hasFocus: Boolean, row: Int, column: Int): Component {
        val props = Util.getProperties()

        val item = (table?.model as ItemTableModel).getItem(row)

        val component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column)

        colorBackground(column, props, item, component)
        colorForeground(column, props, item, component)
        colorSearch(isSelected, props, item, component)

        return component
    }

    private fun colorBackground(column: Int, props: Properties, item: JSONListItem?, component: Component) {
        if (column == 1 || props.color.colorEntireRow) {
            when (item?.level) {
                "INFO" -> component.background = Color.decode(props.color.infoBackground)
                "WARN" -> component.background = Color.decode(props.color.warnBackground)
                "ERROR" -> component.background = Color.decode(props.color.errorBackground)
                "DEBUG" -> component.background = Color.decode(props.color.debugBackground)
            }
        } else {
            component.background = Color.WHITE
        }
    }

    private fun colorForeground(column: Int, props: Properties, item: JSONListItem?, component: Component) {
        if (column == 1 || props.color.colorEntireRow) {
            when (item?.level) {
                "INFO" -> component.foreground = Color.decode(props.color.info)
                "WARN" -> component.foreground = Color.decode(props.color.warn)
                "ERROR" -> component.foreground = Color.decode(props.color.error)
                "DEBUG" -> component.foreground = Color.decode(props.color.debug)
            }
        } else {
            component.foreground = Color.BLACK
        }
    }

    private fun colorSearch(isSelected: Boolean, props: Properties, item: JSONListItem?, component: Component) {
        if (!isSelected && mainWindow.searchField.text != "" && item!!.raw.contains(mainWindow.searchField.text.toLowerCase())) {
            component.background = Color.decode(props.color.search)
        }
    }
}
