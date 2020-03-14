package com.m1lk4fr3553r.view

import com.m1lk4fr3553r.Util
import com.m1lk4fr3553r.model.JSONListItem
import java.awt.Color
import java.awt.Component
import javax.swing.DefaultListCellRenderer
import javax.swing.JList

class ItemListCellRenderer : DefaultListCellRenderer() {
    val infoColor: Color
    val warnColor: Color
    val errorColor: Color
    val debugColor: Color
    init {
        val props = Util.getProperties()
        infoColor = Color.decode(props.getProperty("color.info", "#000000"))
        warnColor = Color.decode(props.getProperty("color.warn", "#0000FF"))
        errorColor = Color.decode(props.getProperty("color.error", "#FF0000"))
        debugColor = Color.decode(props.getProperty("color.debug", "#00FF00"))
    }
    override fun getListCellRendererComponent(list: JList<*>?, value: Any?, index: Int, isSelected: Boolean, cellHasFocus: Boolean): Component {
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