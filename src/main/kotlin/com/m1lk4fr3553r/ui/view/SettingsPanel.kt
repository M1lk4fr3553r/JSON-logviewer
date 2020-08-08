package com.m1lk4fr3553r.ui.view

import com.m1lk4fr3553r.model.SettingsListItem
import javax.swing.JList
import javax.swing.JScrollPane

class SettingsPanel(displayableSettings: DisplayableSettings, list: JList<SettingsListItem> = JList<SettingsListItem>()) : JScrollPane(list) {
    init {
        list.setListData(displayableSettings.getSettings())
    }
}