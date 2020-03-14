package com.m1lk4fr3553r.controller

import com.m1lk4fr3553r.view.ItemView
import com.m1lk4fr3553r.view.MainWindow
import java.awt.KeyboardFocusManager
import java.awt.event.KeyEvent
import javax.swing.DefaultFocusManager
import javax.swing.JFileChooser

class MainWindowController: DefaultFocusManager() {
    private val frame: MainWindow
    init {
        frame = MainWindow("JSON logviewer")
        frame.isVisible = true
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(this)
    }

    override fun dispatchKeyEvent(e: KeyEvent?): Boolean {
        if (e?.id == KeyEvent.KEY_RELEASED && frame.isActive) {
            println(e.keyCode)
            when (e.keyCode) {
                // l
                76 -> {
                    val fileChooser = JFileChooser()
                    fileChooser.showOpenDialog(frame)
                    frame.list.setListData(JSONParser.parse(fileChooser.selectedFile))
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
