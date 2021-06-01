package com.example.keyboardShorcuts.util

import com.example.keyboardShorcuts.model.Keys


fun convertListToString(data: Keys): String {

    var dataSet = if (ToogleSwitch.state) data.mac_keys else data.keys
    val cmd: String
    if (dataSet.isNullOrEmpty() && ToogleSwitch.state) {
        dataSet = data.keys
        cmd = dataSet.joinToString(" + ") {
            " $it"
        }.replace("Ctrl", "Cmd")
    } else {
        cmd = dataSet.joinToString(" + ") {
            it
        }
    }
    return cmd
}

