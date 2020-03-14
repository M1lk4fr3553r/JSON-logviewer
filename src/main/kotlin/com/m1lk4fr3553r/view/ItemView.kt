package com.m1lk4fr3553r.view

import com.m1lk4fr3553r.controller.ItemViewController
import com.m1lk4fr3553r.model.JSONListItem
import javax.swing.JFrame

class ItemView(item: JSONListItem, parent: JFrame) : JFrame() {
    private val controller: ItemViewController

    init {
        controller = ItemViewController(this)
        title = item.toString()
        defaultCloseOperation = DISPOSE_ON_CLOSE
        setSize(1920 / 3, 1080 / 3)
        setLocationRelativeTo(parent)
        isVisible = true
    }
}