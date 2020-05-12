package com.m1lk4fr3553r

import java.awt.Dimension
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.*

class Util {
    companion object {
        fun getProperties(): Properties {
            val stream = FileInputStream(File(File(Util::class.java.getProtectionDomain().getCodeSource().getLocation().toURI()).parent + File.separator + "JSONlogviewer.properties"))
            val prop = Properties()
            prop.load(stream)
            stream.close()
            return prop
        }

        fun getPropertiesFile(): File {
            return File(File(Util::class.java.getProtectionDomain().getCodeSource().getLocation().toURI()).parent + File.separator + "JSONlogviewer.properties")
        }

        fun saveProperties(properties: Properties) {
            val stream = FileOutputStream(File(File(Util::class.java.getProtectionDomain().getCodeSource().getLocation().toURI()).parent + File.separator + "JSONlogviewer.properties"))
            val sortedProperties = SortedProperties()
            sortedProperties.putAll(properties)
            sortedProperties.store(stream, null)
            stream.close()
        }
    }
}

fun Dimension.plus(other:Int): Dimension {
    this.width += other
    this.height += other
    return this
}
