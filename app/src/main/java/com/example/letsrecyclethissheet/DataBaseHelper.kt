package com.example.letsrecyclethissheet

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.letsrecyclethissheet.Utils.Companion.DB_NAME
import com.example.letsrecyclethissheet.Utils.Companion.DB_TABLE_CONTACTS
import com.example.letsrecyclethissheet.Utils.Companion.DB_VERSION
import com.example.letsrecyclethissheet.model.ContactModel

class DataBaseHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    companion object {
        private const val KEY_ID = "id"
        private const val KEY_NAME = "name"
        private const val KEY_PHONE = "phone"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(
            "CREATE TABLE $DB_TABLE_CONTACTS($KEY_ID INTEGER PRIMARY KEY,$KEY_NAME TEXT, $KEY_PHONE TEXT)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(
            "DROP TABLE IF EXISTS $DB_TABLE_CONTACTS"
        )
        onCreate(db)
    }

    fun addContact(contact: ContactModel): Long {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(KEY_NAME, contact.name)
        contentValues.put(KEY_PHONE, contact.phone)

        val success = db.insert(DB_TABLE_CONTACTS, null, contentValues)

        db.close()
        return success
    }

    fun getContacts(): ArrayList<ContactModel> {
        val db = this.readableDatabase
        val cursor: Cursor?
        val selectQuery = "SELECT * FROM $DB_TABLE_CONTACTS"
        val contacts = ArrayList<ContactModel>()

        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var id: Int
        var name: String
        var phone: String

        if (cursor.moveToFirst()) {
            do {
                id = cursor.getInt(cursor.getColumnIndex(KEY_ID))
                name = cursor.getString(cursor.getColumnIndex(KEY_NAME))
                phone = cursor.getString(cursor.getColumnIndex(KEY_PHONE))

                val contact = ContactModel(id = id, name = name, phone = phone)
                contacts.add(contact)
            } while (cursor.moveToNext())
        }
        return contacts
    }

    fun updateContact(contact: ContactModel): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_NAME, contact.name)
        contentValues.put(KEY_PHONE, contact.phone)

        val success = db.update(
            DB_TABLE_CONTACTS,
            contentValues,
            "$KEY_ID = ${contact.id}",
            null
        )

        db.close()
        return success
    }

    fun deleteContact(contact: ContactModel): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_ID, contact.id)

        val success = db.delete(DB_TABLE_CONTACTS, "$KEY_ID = ${contact.id}", null)

        db.close()
        return success
    }
}