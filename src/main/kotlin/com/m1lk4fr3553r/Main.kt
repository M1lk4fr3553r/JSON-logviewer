package com.m1lk4fr3553r

import com.m1lk4fr3553r.controller.MainWindowController

fun main(args: Array<String>) {
    val controller = MainWindowController()

    if (args.isNotEmpty()) {
        controller.loadFile(args[0])
    }
}