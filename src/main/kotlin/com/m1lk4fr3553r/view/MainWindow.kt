package com.m1lk4fr3553r.view

import com.m1lk4fr3553r.model.JSONListItem
import javax.swing.*

class MainWindow(title: String): JFrame() {
    val list = JList<JSONListItem>()
    val scrollPane: JScrollPane
    init {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName())
        setTitle(title)
        defaultCloseOperation = EXIT_ON_CLOSE
        setSize(1920/2, 1080/2)
        setLocationRelativeTo(null)

        val listModel = DefaultListModel<JSONListItem>()
        for (i in 0..20) {
            listModel.addElement(JSONListItem())
        }
        list.model = listModel
        scrollPane = JScrollPane(list)
        add(scrollPane)
    }
}