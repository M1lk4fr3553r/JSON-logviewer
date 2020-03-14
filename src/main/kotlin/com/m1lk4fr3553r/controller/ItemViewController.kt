package com.m1lk4fr3553r.controller

import com.m1lk4fr3553r.view.ItemView
import java.awt.KeyboardFocusManager
import java.awt.event.KeyEvent
import javax.swing.DefaultFocusManager
import javax.swing.JFileChooser

class ItemViewController(view: ItemView): DefaultFocusManager() {
    private val itemView: ItemView
    init {
        itemView = view
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(this)
    }

    override fun dispatchKeyEvent(e: KeyEvent?): Boolean {
        if (e?.id == KeyEvent.KEY_RELEASED && itemView.isActive) {
            when (e.keyCode) {
                27 -> {
                    itemView.dispose()
                    KeyboardFocusManager.getCurrentKeyboardFocusManager().removeKeyEventDispatcher(this)
                }
                else -> return false
            }
        }
        return false
    }
}