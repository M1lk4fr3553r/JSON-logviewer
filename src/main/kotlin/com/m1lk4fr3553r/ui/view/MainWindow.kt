package com.m1lk4fr3553r.ui.view

import com.m1lk4fr3553r.model.ItemTableModel
import com.m1lk4fr3553r.model.JSONListItem
import com.m1lk4fr3553r.ui.controller.ItemTableKeyListener
import java.awt.BorderLayout
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import javax.swing.*
import javax.swing.JTable.*;

class MainWindow(title: String) : JFrame() {
    val table = JTable(ItemTableModel())
    val filterField = JTextField()
    val searchField = JTextField()
    private val scrollPane: JScrollPane
    private val statusBar = JToolBar()

    init {
        initWindow(title)
        table.setDefaultRenderer(JSONListItem::class.java, ItemTableCellRenderer(this))
        table.autoResizeMode = AUTO_RESIZE_LAST_COLUMN
        table.addKeyListener(ItemTableKeyListener())
        scrollPane = JScrollPane(table)
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
