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
    }

    private fun resetFilter() {
        frame.filterField.text = ""
        frame.list.setListData(loadedData)
    }

    override fun dispatchKeyEvent(e: KeyEvent?): Boolean {
        if (frame.isActive && e?.id == KeyEvent.KEY_RELEASED && !frame.filterField.hasFocus()) {
            println(e.keyCode)
            when (e.keyCode) {
                // l
                76 -> {
                    loadFileWithOpenDialog()
                    return true
                }
                70 -> {
                    return true
                }
                // ENTER
                10 -> {
                    ItemView(frame.list.selectedValue, frame)
                    return true
                }
                else -> return false
            }
        }
        return false
    }
}
