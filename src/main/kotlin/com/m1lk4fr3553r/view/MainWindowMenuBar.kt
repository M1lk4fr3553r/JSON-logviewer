package com.m1lk4fr3553r.view

import com.m1lk4fr3553r.controller.MainWindowController
import javax.swing.JMenu
import javax.swing.JMenuBar
import javax.swing.JMenuItem
import kotlin.system.exitProcess

class MainWindowMenuBar(controller: MainWindowController): JMenuBar() {
    val controller: MainWindowController
    init {
        this.controller = controller
        initFileMenu()
        initEditMenu()
    }

    private fun initFileMenu() {
        val fileMenu = JMenu("File")

        val openItem = JMenuItem("Open")
        openItem.addActionListener { controller.loadFileWithOpenDialog() }
        fileMenu.add(openItem)

        val exitItem = JMenuItem("Exit")
        exitItem.addActionListener { exitProcess(0) }
        fileMenu.add(exitItem)

        add(fileMenu)
    }

    private fun initEditMenu() {
        val editMenu = JMenu("Edit")

        val filterItem = JMenuItem("Filter")
        filterItem.addActionListener { controller.requestFilter() }
        editMenu.add(filterItem)

        val searchItem = JMenuItem("Search")
        searchItem.addActionListener { controller.requestSearch() }
        editMenu.add(searchItem)

        add(editMenu)
    }
}
