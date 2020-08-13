package com.m1lk4fr3553r

import com.m1lk4fr3553r.ui.controller.MainWindowController
import com.m1lk4fr3553r.model.properties.Properties
import com.m1lk4fr3553r.updater.github.GitHubUpdater
import com.m1lk4fr3553r.updater.github.GitHubUpdaterCallback
import com.m1lk4fr3553r.util.Util
import javax.swing.JOptionPane

fun main(args: Array<String>) {
    val outfile = Util.getPropertiesFile()
    if (!outfile.exists()) {
        Properties().store()
    }

    if (!Util.getProperties().misc.disableUpdate) {
        GitHubUpdater.createInstance("https://github.com/M1lk4fr3553r/JSON-logviewer", UpdaterCallback())
                .checkForUpdate("v1.5")
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
            val optionPane = JOptionPane("Update downloaded.\nUpdate will be applied on program exit.", JOptionPane.INFORMATION_MESSAGE, JOptionPane.OK_CANCEL_OPTION)
            optionPane.createDialog(null, "Update ready").isVisible = true

            while (optionPane.value == JOptionPane.UNINITIALIZED_VALUE) {
                Thread.sleep(2000)
            }

            if (optionPane.value == JOptionPane.OK_OPTION) {
                GitHubUpdater.getInstance().update("json-logviewer.jar", true)
                println("Update planned")
            } else {
                println("Update canceled by user")
            }
        }
    }
}