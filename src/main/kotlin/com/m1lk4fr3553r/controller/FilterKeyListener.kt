package com.m1lk4fr3553r.controller

import java.awt.event.KeyEvent
import java.awt.event.KeyListener

class FilterKeyListener(val filterFunction: () -> Unit,val  resetFunction: () -> Unit): KeyListener {
    override fun keyTyped(p0: KeyEvent?) {
    }

    override fun keyPressed(p0: KeyEvent?) {
    }

    override fun keyReleased(p0: KeyEvent?) {
        when(p0?.keyCode) {
            10 -> filterFunction()
            27 -> resetFunction()
        }
    }
}