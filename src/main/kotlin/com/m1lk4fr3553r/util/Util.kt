package com.m1lk4fr3553r.util

import com.m1lk4fr3553r.ui.controller.MainWindowController
import com.m1lk4fr3553r.model.properties.Properties
import com.m1lk4fr3553r.ui.controller.JSONParser
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch
import java.awt.Dimension
import java.io.File

class Util {
    companion object {
        private var loadedFileChannel: KWatchChannel? = null
        private var properties: Properties? = null

        fun getProperties(): Properties {
            if (properties == null) {
                properties = Properties.load()
            }
            return properties as Properties
        }

        fun watchProperties() {
            val file = getPropertiesFile()
            val watchChannel = file.asWatchChannel()
            GlobalScope.launch {
                watchChannel.consumeEach {
                    if (it.file == file && file.exists()) {
                        properties = Properties.load()
                    }
                }
            }
        }

        fun getPropertiesFile(): File {
            return File(File(Util::class.java.protectionDomain.codeSource.location.toURI()).parent + File.separator + "JSONlogviewer.json")
        }

        fun saveProperties(properties: Properties) {
            properties.store()
        }

        fun watchForChanges(file: File, controller: MainWindowController) {
            loadedFileChannel?.close()
            loadedFileChannel = file.asWatchChannel()

            GlobalScope.launch {
                loadedFileChannel!!.consumeEach {
                    if (it.file == file) {
                        controller.loadedData = JSONParser.parse(file).reversedArray()
                        controller.displayedData = controller.loadedData
                    }
                }
            }
        }
    }
}

fun Dimension.plus(other: Int): Dimension {
    this.width += other
    this.height += other
    return this
}
