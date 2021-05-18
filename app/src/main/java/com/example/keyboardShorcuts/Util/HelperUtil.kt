
package com.example.keyboardShorcuts.Util


fun convertListToString(dataSet: List<String>): String {
    var cmd = ""
    dataSet.apply {
        this.forEachIndexed { index, s ->
            if (index == this.size - 1) {
                cmd += s
            } else {
                cmd += "$s + "
            }
        }
    }
    return "$cmd"
}