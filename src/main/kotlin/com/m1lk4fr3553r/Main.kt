package com.m1lk4fr3553r

import com.m1lk4fr3553r.controller.MainWindowController
import com.m1lk4fr3553r.updater.github.GitHubUpdater
import com.m1lk4fr3553r.updater.github.GitHubUpdaterCallback
import com.m1lk4fr3553r.util.Util
import java.io.FileOutputStream
import javax.swing.JOptionPane

fun main(args: Array<String>) {
    GitHubUpdater.createInstance("https://github.com/M1lk4fr3553r/JSON-logviewer", UpdaterCallback())
            .checkForUpdate("v1.4")

    val stream = Util::class.java.classLoader.getResourceAsStream("_JSONlogviewer.properties")
    val outfile = Util.getPropertiesFile()
    if (!outfile.exists() && stream != null) {
        FileOutputStream(outfile).write(stream.readAllBytes())
    }
    val controller = MainWindowController()

    if (args.isNotEmpty()) {
        controller.loadFile(args[0])
    }
}

class UpdaterCallback : GitHubUpdaterCallback {
    override fun checkUpdateCallback(updateFound: Boolean) {
        if (updateFound) {
            println("Update found, starting download...")
            GitHubUpdater.getInstance().download()
        }
    }

    override fun downloadCallback(successfulDownload: Boolean) {
        if (successfulDownload) {
            println("Download finished, starting update process...")
            JOptionPane.showMessageDialog(null, "Update downloaded.\nUpdate will be applied on program exit.")
            GitHubUpdater.getInstance().update("json-logviewer.jar", true)
        }
    }
}