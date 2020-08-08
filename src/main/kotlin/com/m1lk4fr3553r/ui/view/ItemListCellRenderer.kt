package com.m1lk4fr3553r.ui.view

import com.m1lk4fr3553r.util.Util
import com.m1lk4fr3553r.model.JSONListItem
import java.awt.Color
import java.awt.Component
import javax.swing.DefaultListCellRenderer
import javax.swing.JList

class ItemListCellRenderer(private val mainWindow: MainWindow) : DefaultListCellRenderer() {
    override fun getListCellRendererComponent(list: JList<*>?, value: Any?, index: Int, isSelected: Boolean, cellHasFocus: Boolean): Component {
        val props = Util.getProperties()
        val infoColor = Color.decode(props.color.info)
        val warnColor = Color.decode(props.color.warn)
        val errorColor = Color.decode(props.color.error)
        val debugColor = Color.decode(props.color.debug)
        val searchColor = Color.decode(props.color.search)

        val item = (value as JSONListItem)

        val component = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus)

        when (item.items.get(item.levelKey)) {
            "INFO" -> component.foreground = infoColor
            "WARN" -> component.foreground = warnColor
            "ERROR" -> component.foreground = errorColor
            "DEBUG" -> component.foreground = debugColor
        }

        if (!isSelected && mainWindow.searchField.text != "" && item.raw.contains(mainWindow.searchField.text.toLowerCase())) {
            component.background = searchColor
        }
        return component
    }
}
