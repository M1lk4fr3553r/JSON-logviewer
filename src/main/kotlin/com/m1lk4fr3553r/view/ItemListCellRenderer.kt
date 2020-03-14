package com.m1lk4fr3553r.view

import com.m1lk4fr3553r.model.JSONListItem
import java.awt.Color
import java.awt.Component
import javax.swing.DefaultListCellRenderer
import javax.swing.JList

class ItemListCellRenderer : DefaultListCellRenderer() {
    override fun getListCellRendererComponent(list: JList<*>?, value: Any?, index: Int, isSelected: Boolean, cellHasFocus: Boolean): Component {
        val item = (value as JSONListItem)
        val component = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus)
        when (item.items.get(item.levelKey)) {
            "WARN" -> component.foreground = Color.ORANGE
            "DEBUG" -> component.foreground = Color.GREEN
            "ERROR" -> component.foreground = Color.RED
        }
        return component
    }
}