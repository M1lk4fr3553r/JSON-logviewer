package com.m1lk4fr3553r.controller

import com.m1lk4fr3553r.model.JSONListItem
import com.m1lk4fr3553r.view.ItemView
import com.m1lk4fr3553r.view.MainWindow
import java.awt.KeyboardFocusManager
import java.awt.event.KeyEvent
import java.io.File
import javax.swing.DefaultFocusManager
import javax.swing.JFileChooser

class MainWindowController : DefaultFocusManager() {
    private val frame = MainWindow("JSON logviewer")
    private var loadedData = emptyArray<JSONListItem>()

    init {
        frame.isVisible = true
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(this)
        frame.filterField.addKeyListener(FilterKeyListener(::filter, ::resetFilter))
        frame.searchField.addKeyListener(FilterKeyListener(::search, ::resetSearch))
        frame.list.addMouseListener(MainWindowMouseAdapter(frame))
    }

    private fun loadFileWithOpenDialog() {
        val fileChooser = JFileChooser()
        fileChooser.showOpenDialog(frame)
        if (fileChooser.selectedFile.isFile && fileChooser.selectedFile != null) {
            loadedData = JSONParser.parse(fileChooser.selectedFile)
            frame.list.setListData(loadedData)
        }
    }

    fun loadFile(path: String) {
        val file = File(path)
        if (file.isFile) {
            loadedData = JSONParser.parse(file)
            frame.list.setListData(loadedData)
        }
    }

    private fun filter() {
        val filtered: List<JSONListItem> = loadedData.filter { it.raw.contains(frame.filterField.text.toLowerCase()) }
        frame.list.setListData(filtered.toTypedArray())
        frame.list.requestFocus()
    }

    private fun resetFilter() {
        frame.filterField.text = ""
        frame.list.setListData(loadedData)
        frame.list.requestFocus()
    }

    private fun search() {
        val results = loadedData.filter { it.raw.contains(frame.searchField.text.toLowerCase()) }.map { loadedData.indexOf(it) }
        for (i in results) {
            if (i > frame.list.selectedIndex) {
                frame.list.setSelectedValue(frame.list.model.getElementAt(i), true)
                break
            }
        }
    }

    private fun resetSearch() {
        frame.searchField.text = ""
        frame.list.requestFocus()
    }

    override fun dispatchKeyEvent(e: KeyEvent?): Boolean {
        if (frame.isActive && e?.id == KeyEvent.KEY_RELEASED && !frame.filterField.hasFocus() && !frame.searchField.hasFocus()) {
//            println(e.keyCode)
            when (e.keyCode) {
                // ENTER
                10 -> {
                    ItemView(frame.list.selectedValue, frame)
                    return true
                }
                // F
                70 -> {
                    frame.filterField.requestFocus()
                    return true
                }
                // L
                76 -> {
                    loadFileWithOpenDialog()
                    return true
                }
                // S
                83 -> {
                    frame.searchField.requestFocus()
                    return true
                }
                else -> return false
            }
        }
        return false
    }
}
