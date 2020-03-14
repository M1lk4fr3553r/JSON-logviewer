package com.m1lk4fr3553r.controller

import com.m1lk4fr3553r.view.ItemView
import com.m1lk4fr3553r.view.MainWindow
import java.awt.KeyboardFocusManager
import java.awt.event.KeyEvent
import javax.swing.DefaultFocusManager
import javax.swing.JFileChooser

class MainWindowController : DefaultFocusManager() {
    private val frame = MainWindow("JSON logviewer")

    init {
        frame.isVisible = true
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(this)
    }

    private fun loadFile() {
        val fileChooser = JFileChooser()
        fileChooser.showOpenDialog(frame)
        if (fileChooser.selectedFile.isFile && fileChooser.selectedFile != null) {
            frame.list.setListData(JSONParser.parse(fileChooser.selectedFile))
        }
    }

    override fun dispatchKeyEvent(e: KeyEvent?): Boolean {
        if (frame.isActive && e?.id == KeyEvent.KEY_RELEASED && !frame.filterField.hasFocus()) {
            println(e.keyCode)
            when (e.keyCode) {
                // l
                76 -> {
                    loadFile()
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
