package com.m1lk4fr3553r.view

import com.m1lk4fr3553r.util.Util
import com.m1lk4fr3553r.model.JSONListItem
import java.awt.Color
import java.awt.Component
import javax.swing.DefaultListCellRenderer
import javax.swing.JList

class ItemListCellRenderer : DefaultListCellRenderer() {
    override fun getListCellRendererComponent(list: JList<*>?, value: Any?, index: Int, isSelected: Boolean, cellHasFocus: Boolean): Component {
        val props = Util.getProperties()
        val infoColor = Color.decode(props.getProperty("color.info", "#000000"))
        val warnColor = Color.decode(props.getProperty("color.warn", "#ada401"))
        val errorColor = Color.decode(props.getProperty("color.error", "#aa0000"))
        val debugColor = Color.decode(props.getProperty("color.debug", "#0037dd"))
        val item = (value as JSONListItem)
        val component = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus)
        when (item.items.get(item.levelKey)) {
            "INFO" -> component.foreground = infoColor
            "WARN" -> component.foreground = warnColor
            "ERROR" -> component.foreground = errorColor
            "DEBUG" -> component.foreground = debugColor
        }
        return component
    }
}
