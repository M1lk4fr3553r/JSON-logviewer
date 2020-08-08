package com.m1lk4fr3553r.ui.view

import com.m1lk4fr3553r.model.JSONListItem
import com.m1lk4fr3553r.ui.controller.SettingsWindowController
import com.m1lk4fr3553r.util.plus
import org.apache.commons.text.StringEscapeUtils
import java.awt.BorderLayout
import javax.swing.*

class SettingsWindow(parent: JFrame) : JFrame() {
    val jTabbedPane = JTabbedPane()
    val buttonPanel = JPanel()
    val saveButton = JButton("Save")
    val cancelButton = JButton("Cancel")

    private val controller = SettingsWindowController(this)

    init {
        title = "Settings"
        size = parent.size
        defaultCloseOperation = DISPOSE_ON_CLOSE
        setLocationRelativeTo(parent)
        layout = BorderLayout()

        add(jTabbedPane)

        buttonPanel.add(saveButton)
        buttonPanel.add(cancelButton)
        add(buttonPanel, BorderLayout.PAGE_END)

        isVisible = true
    }
}
