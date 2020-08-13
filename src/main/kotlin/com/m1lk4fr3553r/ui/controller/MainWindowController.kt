package com.m1lk4fr3553r.ui.controller

import com.m1lk4fr3553r.model.ItemTableModel
import com.m1lk4fr3553r.util.Util
import com.m1lk4fr3553r.model.JSONListItem
import com.m1lk4fr3553r.ui.view.ItemView
import com.m1lk4fr3553r.ui.view.MainWindow
import com.m1lk4fr3553r.ui.view.MainWindowMenuBar
import com.m1lk4fr3553r.ui.view.SettingsWindow
import org.json.JSONException
import java.awt.KeyboardFocusManager
import java.awt.datatransfer.DataFlavor
import java.awt.dnd.*
import java.awt.event.KeyEvent
import java.io.File
import javax.swing.DefaultFocusManager
import javax.swing.JFileChooser

class MainWindowController : DefaultFocusManager(), DropTargetListener {
    private val frame = MainWindow("JSON logviewer")
    var loadedData = emptyArray<JSONListItem>()
    var displayedData = loadedData
    set(data) {
        field = data
        (frame.table.model as ItemTableModel).setData(loadedData)
    }

    init {
        frame.jMenuBar = MainWindowMenuBar(this)
        frame.isVisible = true
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(this)
        frame.filterField.addKeyListener(FilterKeyListener(::filter, ::resetFilter))
        frame.searchField.addKeyListener(FilterKeyListener(::search, ::resetSearch))
        frame.table.addMouseListener(MainWindowMouseAdapter(frame))
        Util.watchProperties()
        loadFile(Util.getProperties().misc.lastFile)
        DropTarget(frame, DnDConstants.ACTION_COPY_OR_MOVE, this, true)
    }

    fun loadFileWithOpenDialog() {
        //Load last opened file
        val properties = Util.getProperties()
        var lastPath = properties.misc.lastFile
        if (lastPath.equals("")) {
            lastPath = "%USERHOME%"
        }

        val fileChooser = JFileChooser(lastPath)
        fileChooser.showOpenDialog(frame)
        if (fileChooser.selectedFile != null) {
            loadFile(fileChooser.selectedFile, true)
        }
    }

    fun loadFile(path: String) {
        loadFile(File(path), false)
    }

    fun loadFile(file: File, updateLocation: Boolean) {
        if (file.isFile) {
            try {
                loadedData = JSONParser.parse(file).reversedArray()
                displayedData = loadedData
                if (updateLocation) {
                    // Store selected file to Properties
                    val properties = Util.getProperties()
                    properties.misc.lastFile = file.absolutePath
                    Util.saveProperties(properties)
                }
                Util.watchForChanges(file, this)
            } catch (exception: JSONException) {
                System.err.println("Not a valid JSON-Log")
                exception.printStackTrace()
            }
        }
    }

    fun openSettings() {
        SettingsWindow(this.frame)
    }

    fun requestFilter() {
        frame.filterField.requestFocus()
    }

    private fun filter() {
        val filtered: List<JSONListItem> = loadedData.filter { it.raw.contains(frame.filterField.text.toLowerCase()) }
        displayedData = filtered.toTypedArray()
        frame.table.requestFocus()
    }

    private fun resetFilter() {
        frame.filterField.text = ""
        displayedData = loadedData
        frame.table.requestFocus()
    }

    fun requestSearch() {
        frame.searchField.requestFocus()
    }

    private fun search() {
        val results = displayedData.filter { it.raw.contains(frame.searchField.text.toLowerCase()) }.map { displayedData.indexOf(it) }
        for (i in results) {
            if (i > frame.table.selectedRow) {
                frame.table.setRowSelectionInterval(i, i)
                break
            }
        }
        frame.table.repaint()
    }

    private fun resetSearch() {
        frame.searchField.text = ""
        frame.table.requestFocus()
        frame.table.repaint()
    }

    override fun dispatchKeyEvent(e: KeyEvent?): Boolean {
        if (frame.isActive && e?.id == KeyEvent.KEY_RELEASED && !frame.filterField.hasFocus() && !frame.searchField.hasFocus()) {
            println(e.keyCode)
            if (e.isControlDown) {
                when (e.keyCode) {
                    // ENTER
                    10 -> {
                        ItemView((frame.table.model as ItemTableModel).getItem(frame.table.selectedRow)!!, frame)
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
                        if ((frame.table.model as ItemTableModel).getItem(frame.table.selectedRow) != null) {
                            ItemView((frame.table.model as ItemTableModel).getItem(frame.table.selectedRow)!!, frame)
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

    override fun dropActionChanged(dtde: DropTargetDragEvent?) {
        // Currently no logic needed
    }

    override fun drop(dtde: DropTargetDropEvent?) {
        dtde?.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE)
        val data = dtde?.transferable?.getTransferData(DataFlavor.javaFileListFlavor)
        if (data is List<*>) {
            loadFile(data[0] as File, true)
        }
    }

    override fun dragOver(dtde: DropTargetDragEvent?) {
        // Currently no logic needed
    }

    override fun dragExit(dte: DropTargetEvent?) {
        // Currently no logic needed
    }

    override fun dragEnter(dtde: DropTargetDragEvent?) {
        // Currently no logic needed
    }
}
