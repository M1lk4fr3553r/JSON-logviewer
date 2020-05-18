package com.m1lk4fr3553r.controller

import com.m1lk4fr3553r.model.JSONListItem
import com.m1lk4fr3553r.view.ItemView
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.swing.JFrame
import javax.swing.JList

class MainWindowMouseAdapter(private val parent: JFrame): MouseAdapter() {

    override fun mouseClicked(e: MouseEvent?) {
        val list = e?.source as JList<*>
        if (e.clickCount == 2) {
            val index = list.locationToIndex(e.point)
            ItemView(list.model.getElementAt(index) as JSONListItem, parent)
        }
    }
}