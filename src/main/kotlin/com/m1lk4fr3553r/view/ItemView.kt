package com.m1lk4fr3553r.view

import com.m1lk4fr3553r.controller.ItemViewController
import com.m1lk4fr3553r.model.JSONListItem
import com.m1lk4fr3553r.util.plus
import org.apache.commons.text.StringEscapeUtils
import javax.swing.JFrame
import javax.swing.JScrollPane
import javax.swing.JTextArea

class ItemView(item: JSONListItem, parent: JFrame) : JFrame() {
    private val controller = ItemViewController(this)
    private val textArea: JTextArea

    init {
        title = item.toString()
        defaultCloseOperation = DISPOSE_ON_CLOSE

        textArea = JTextArea(StringEscapeUtils.unescapeJava(item.json.toString(2)))
        textArea.isEditable = false
        add(JScrollPane(textArea))
        size = textArea.preferredSize.plus(50)
        setLocationRelativeTo(parent)
        isVisible = true
    }
}
