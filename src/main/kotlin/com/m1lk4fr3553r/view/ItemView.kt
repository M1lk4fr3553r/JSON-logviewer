package com.m1lk4fr3553r.view

import com.m1lk4fr3553r.controller.ItemViewController
import com.m1lk4fr3553r.model.JSONListItem
import org.apache.commons.text.StringEscapeUtils
import javax.swing.JFrame
import javax.swing.JScrollPane
import javax.swing.JTextArea

class ItemView(item: JSONListItem, parent: JFrame) : JFrame() {
    private val controller: ItemViewController
    private val textArea: JTextArea

    init {
        controller = ItemViewController(this)
        title = item.toString()
        defaultCloseOperation = DISPOSE_ON_CLOSE
        setSize(1920 / 3, 1080 / 3)
        setLocationRelativeTo(parent)
        isVisible = true

        textArea = JTextArea(StringEscapeUtils.unescapeJava(item.json.toString(2) ))
        textArea.isEditable = false
        add(JScrollPane(textArea))
    }
}