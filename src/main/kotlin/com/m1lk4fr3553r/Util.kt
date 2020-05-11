package com.m1lk4fr3553r

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
            return prop
        }

        fun saveProperties(properties: Properties) {
            val stream = FileOutputStream(File(File(Util::class.java.getProtectionDomain().getCodeSource().getLocation().toURI()).parent + File.separator + "JSONlogviewer.properties"))
            properties.store(stream, "")
        }
    }
}
