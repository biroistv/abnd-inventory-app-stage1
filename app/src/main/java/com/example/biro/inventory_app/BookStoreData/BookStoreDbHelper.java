package com.example.biro.inventory_app.BookStoreData;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class BookStoreDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "bookstore.db";
    private static final int DATABASE_VERSION = 1;

    public BookStoreDbHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE books (" +
                        BookStoreContract.BookStoreEntry.COLUMN_BOOK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        BookStoreContract.BookStoreEntry.COLUMN_BOOK_NAME + " TEXT NOT NULL," +
                        BookStoreContract.BookStoreEntry.COLUMN_BOOK_PRICE + " INTEGER DEFAULT 99999," +
                        BookStoreContract.BookStoreEntry.COLUMN_BOOK_QUANTITY + " INTEGER DEFAULT 0," +
                        BookStoreContract.BookStoreEntry.COLUMN_BOOK_SUPPLIER_NAME + " TEXT NOT NULL," +
                        BookStoreContract.BookStoreEntry.COLUMN_BOOK_SUPPLIER_PHONE_NUMBER + " TEXT NOT NULL);"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + BookStoreContract.BookStoreEntry.TABLE_NAME + ";");
        onCreate(db);
    }
}
