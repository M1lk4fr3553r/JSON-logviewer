package com.m1lk4fr3553r.controller

import com.m1lk4fr3553r.Util
import com.m1lk4fr3553r.model.JSONListItem
import com.m1lk4fr3553r.view.ItemView
import com.m1lk4fr3553r.view.MainWindow
import com.m1lk4fr3553r.view.MainWindowMenuBar
import org.json.JSONException
import java.awt.Desktop
import java.awt.KeyboardFocusManager
import java.awt.event.KeyEvent
import java.io.File
import javax.swing.DefaultFocusManager
import javax.swing.JFileChooser

class MainWindowController : DefaultFocusManager() {
    private val frame = MainWindow("JSON logviewer")
    private var loadedData = emptyArray<JSONListItem>()
    private var displayedData = loadedData

    init {
        frame.jMenuBar = MainWindowMenuBar(this)
        frame.isVisible = true
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(this)
        frame.filterField.addKeyListener(FilterKeyListener(::filter, ::resetFilter))
        frame.searchField.addKeyListener(FilterKeyListener(::search, ::resetSearch))
        frame.list.addMouseListener(MainWindowMouseAdapter(frame))
        loadFile(Util.getProperties().getProperty("path.last", ""))
    }

    fun loadFileWithOpenDialog() {
        //Load last opened file
        val properties = Util.getProperties()
        val lastPath = properties.getProperty("path.last", "%userhome%")

        val fileChooser = JFileChooser(lastPath)
        fileChooser.showOpenDialog(frame)
        if (fileChooser.selectedFile != null) {
            loadFile(fileChooser.selectedFile)
        }
    }

    fun loadFile(path: String) {
        loadFile(File(path))
    }

    fun loadFile(file: File) {
        if (file.isFile) {
            try {
                loadedData = JSONParser.parse(file)
                frame.list.setListData(loadedData)
                displayedData = loadedData

                // Store selected file to Properties
                val properties = Util.getProperties()
                properties.setProperty("path.last", file.absolutePath)
                Util.saveProperties(properties)
            } catch (exception: JSONException) {
                System.err.println("Not a valid JSON-Log")
                exception.printStackTrace()
            }
        }
    }

    fun openSettings() {
        val desktop = Desktop.getDesktop()
        desktop.open(Util.getPropertiesFile())
    }

    fun requestFilter() {
        frame.filterField.requestFocus()
    }

    private fun filter() {
        val filtered: List<JSONListItem> = loadedData.filter { it.raw.contains(frame.filterField.text.toLowerCase()) }
        frame.list.setListData(filtered.toTypedArray())
        displayedData = filtered.toTypedArray()
        frame.list.requestFocus()
    }

    private fun resetFilter() {
        frame.filterField.text = ""
        frame.list.setListData(loadedData)
        displayedData = loadedData
        frame.list.requestFocus()
    }

    fun requestSearch() {
        frame.searchField.requestFocus()
    }

    private fun search() {
        val results = displayedData.filter { it.raw.contains(frame.searchField.text.toLowerCase()) }.map { displayedData.indexOf(it) }
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
            println(e.keyCode)
            if (e.isControlDown) {
                when (e.keyCode) {
                    // ENTER
                    10 -> {
                        ItemView(frame.list.selectedValue, frame)
                        return true
                    }
                    // CTRL + F
                    70 -> {
                        requestFilter()
                        return true
                    }
                    // CTRL + O
                    79 -> {
                        loadFileWithOpenDialog()
                        return true
                    }
                    // CTRL + S
                    83 -> {
                        requestSearch()
                        return true
                    }
                    else -> return false
                }
            } else {
                when (e.keyCode) {
                    // ENTER
                    10 -> {
                        if (frame.list.selectedValue != null) {
                            ItemView(frame.list.selectedValue, frame)
                            return true
                        } else {
                            return false
                        }
                    }
                    else -> return false
                }
            }
        }
        return false
    }
}
