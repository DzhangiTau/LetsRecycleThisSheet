package com.example.letsrecyclethissheet

class Utils {
    companion object {
        const val DB_NAME = "CONTACTS_DB"
        const val DB_VERSION = 1
        const val DB_TABLE_CONTACTS = "CONTACTS_TABLE"

        private val list = mutableListOf<Pair<String, String>>()

        fun generateMenuItems(itemsCount: Int): List<Pair<String, String>> {
            for (i in 1..itemsCount) {
                list.add(Pair("Menu item", i.toString()))
            }
            return list
        }
    }
}