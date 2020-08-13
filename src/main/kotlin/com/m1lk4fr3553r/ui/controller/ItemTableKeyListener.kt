package com.m1lk4fr3553r.ui.controller

import java.awt.event.KeyEvent
import java.awt.event.KeyListener

class ItemTableKeyListener : KeyListener {

  override fun keyTyped(e: KeyEvent?) {
    //Not needed
  }

  override fun keyPressed(e: KeyEvent?) {
    if (e?.keyCode == KeyEvent.VK_ENTER) {
      e.consume()
    }
  }

  override fun keyReleased(e: KeyEvent?) {
    // Not needed
  }
}