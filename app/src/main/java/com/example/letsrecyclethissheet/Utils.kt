package com.example.letsrecyclethissheet

class Utils {
    companion object {
        private val list = mutableListOf<Pair<String, String>>()

        fun generateMenuItems(itemsCount: Int): List<Pair<String, String>> {
            for (i in 1..itemsCount) {
                list.add(Pair("Menu item", i.toString()))
            }
            return list
        }
    }
}