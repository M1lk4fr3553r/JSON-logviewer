package com.m1lk4fr3553r.ui.view

import com.m1lk4fr3553r.ui.controller.MainWindowController
import com.m1lk4fr3553r.util.Globals
import javax.swing.JMenu
import javax.swing.JMenuBar
import javax.swing.JMenuItem
import javax.swing.JOptionPane
import kotlin.system.exitProcess

class MainWindowMenuBar(private val controller: MainWindowController) : JMenuBar() {
  init {
    initFileMenu()
    initEditMenu()
    initAboutMenu()
  }

  private fun initFileMenu() {
    val fileMenu = JMenu("File")

    val openItem = JMenuItem("Open")
    openItem.addActionListener { controller.loadFileWithOpenDialog() }
    fileMenu.add(openItem)

    val settingsIcon = JMenuItem("Settings")
    settingsIcon.addActionListener { controller.openSettings() }
    fileMenu.add(settingsIcon)

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

  private fun initAboutMenu() {
    val aboutMenu = JMenu("About")

    val versionItem = JMenuItem("Version")
    versionItem.addActionListener {
      JOptionPane.showMessageDialog(this,
          "Version: ${Globals.VERSION}\n" +
              "Build: ${Globals.BUILD}\n" +
              "Build date: ${Globals.BUILD_DATE}")
    }
    aboutMenu.add(versionItem)

    add(aboutMenu)
  }
}
