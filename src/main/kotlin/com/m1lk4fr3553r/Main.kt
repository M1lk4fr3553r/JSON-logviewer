package com.m1lk4fr3553r

import com.m1lk4fr3553r.controller.MainWindowController
import java.io.File
import java.io.FileOutputStream

fun main(args: Array<String>) {
    val stream = Util::class.java.classLoader.getResourceAsStream("_JSONlogviewer.properties")
    val outfile = File(File(Util::class.java.getProtectionDomain().getCodeSource().getLocation().toURI()).parent + File.separator + "JSONlogviewer.properties")
    if (!outfile.exists() && stream != null) {
        FileOutputStream(outfile).write(stream.readAllBytes())
    }
    val controller = MainWindowController()

    if (args.isNotEmpty()) {
        controller.loadFile(args[0])
    }
}