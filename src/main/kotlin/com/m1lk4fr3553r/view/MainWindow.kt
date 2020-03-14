package com.m1lk4fr3553r.view

import com.m1lk4fr3553r.controller.FilterKeyListener
import com.m1lk4fr3553r.model.JSONListItem
import java.awt.BorderLayout
import javax.swing.*

class MainWindow(title: String) : JFrame() {
    val list = JList<JSONListItem>()
    val scrollPane: JScrollPane
    val statusBar = JToolBar()
    val filterField = JTextField()
    val searchField = JTextField()

    init {
        initWindow(title)
        list.cellRenderer = ItemListCellRenderer()
        scrollPane = JScrollPane(list)
        add(scrollPane)
        statusBar.add(JLabel("Filter:"))
        statusBar.add(filterField)
        statusBar.add(JLabel("Search:"))
        statusBar.add(searchField)
        add(statusBar, BorderLayout.SOUTH)
    }

    private fun initWindow(title: String) {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName())
        setTitle(title)
        defaultCloseOperation = EXIT_ON_CLOSE
        setSize(1920 / 2, 1080 / 2)
        setLocationRelativeTo(null)
    }
}