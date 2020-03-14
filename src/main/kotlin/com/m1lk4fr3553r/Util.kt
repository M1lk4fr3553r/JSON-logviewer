package com.m1lk4fr3553r

import java.util.*

class Util {
    companion object {
        fun getProperties(): Properties {
            val stream = Util::class.java.classLoader.getResourceAsStream("JSONlogviewer.properties")
            val prop = Properties()
            prop.load(stream)
            return prop
        }
    }
}