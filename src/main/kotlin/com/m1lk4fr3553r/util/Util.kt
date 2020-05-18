package com.m1lk4fr3553r.util

import com.m1lk4fr3553r.controller.MainWindowController
import com.m1lk4fr3553r.model.SortedProperties
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch
import java.awt.Dimension
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.*

class Util {
    companion object {
        private var properties: Properties? = null

        fun getProperties(): Properties {
            if (properties == null) {
                loadProperties()
            }
            return properties as Properties
        }

        fun watchProperties() {
            val file = getPropertiesFile()
            val watchChannel = file.asWatchChannel()
            GlobalScope.launch {
                watchChannel.consumeEach {
                    if (it.file == file && file.exists()) {
                        loadProperties()
                    }
                }
            }
        }

        private fun loadProperties() {
            val stream = FileInputStream(getPropertiesFile())
            val prop = Properties()
            prop.load(stream)
            stream.close()
            properties = prop
        }

        fun getPropertiesFile(): File {
            return File(File(Util::class.java.protectionDomain.codeSource.location.toURI()).parent + File.separator + "JSONlogviewer.properties")
        }

        fun saveProperties(properties: Properties) {
            val stream = FileOutputStream(getPropertiesFile())
            val sortedProperties = SortedProperties()
            sortedProperties.putAll(properties)
            sortedProperties.store(stream, null)
            stream.close()
        }

        fun watchForChanges(file: File, controller: MainWindowController) {
            val watchChannel = file.asWatchChannel()

            GlobalScope.launch {
                watchChannel.consumeEach {
                    if (it.file == file) {
                        controller.loadFile(file, false)
                        watchChannel.close()
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
