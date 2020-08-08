package com.m1lk4fr3553r.ui.controller

import com.m1lk4fr3553r.model.ItemTableModel
import com.m1lk4fr3553r.model.JSONListItem
import com.m1lk4fr3553r.ui.view.ItemView
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.swing.JFrame
import javax.swing.JList
import javax.swing.JTable

class MainWindowMouseAdapter(private val parent: JFrame): MouseAdapter() {

    override fun mouseClicked(e: MouseEvent?) {
        val table = e?.source as JTable
        if (e.clickCount == 2) {
            ItemView((table.model as ItemTableModel).getItem(table.selectedRow)!!, parent)
        }
    }
}