package com.example.biro.inventory_app;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.biro.inventory_app.BookStoreData.BookStoreContract;
import com.example.biro.inventory_app.BookStoreData.BookStoreDbHelper;

public class MainActivity extends AppCompatActivity {


    private BookStoreDbHelper bookStoreDbHelper = new BookStoreDbHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        insertData();
        queryData();

    }

    private void insertData() {
        SQLiteDatabase db = bookStoreDbHelper.getWritableDatabase();

        ContentValues content_values = new ContentValues();
        content_values.put(BookStoreContract.BookStoreEntry.COLUMN_BOOK_NAME, "Test");
        content_values.put(BookStoreContract.BookStoreEntry.COLUMN_BOOK_PRICE, 49);
        content_values.put(BookStoreContract.BookStoreEntry.COLUMN_BOOK_QUANTITY, 4);
        content_values.put(BookStoreContract.BookStoreEntry.COLUMN_BOOK_SUPPLIER_NAME, "The supplier");
        content_values.put(BookStoreContract.BookStoreEntry.COLUMN_BOOK_SUPPLIER_PHONE_NUMBER, "1234566756");


        if (db.insert(BookStoreContract.BookStoreEntry.TABLE_NAME, null, content_values) == -1)
            Log.d("database - insert", "Error while inserting new data into the database");
        else
            Log.d("database - insert", "Successfully added a new row to the database");
    }

    private void queryData() {
        StringBuilder LOG_MSG = new StringBuilder();
        LOG_MSG.append("Table:\n"+
                BookStoreContract.BookStoreEntry.COLUMN_BOOK_ID + "\t" +
                        BookStoreContract.BookStoreEntry.COLUMN_BOOK_NAME + "\t" +
                        BookStoreContract.BookStoreEntry.COLUMN_BOOK_PRICE + "\t" +
                        BookStoreContract.BookStoreEntry.COLUMN_BOOK_QUANTITY + "\t" +
                        BookStoreContract.BookStoreEntry.COLUMN_BOOK_SUPPLIER_NAME + "\t" +
                        BookStoreContract.BookStoreEntry.COLUMN_BOOK_SUPPLIER_PHONE_NUMBER + "\n"
        );

        SQLiteDatabase db = bookStoreDbHelper.getReadableDatabase();

        Cursor cursor = null;

        try {
            cursor = db.query(
                    BookStoreContract.BookStoreEntry.TABLE_NAME,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
            );

            int id_column_index = cursor.getColumnIndex(
                    BookStoreContract.BookStoreEntry._ID);
            int name_column_index = cursor.getColumnIndex(
                    BookStoreContract.BookStoreEntry.COLUMN_BOOK_NAME);
            int price_column_index = cursor.getColumnIndex(
                    BookStoreContract.BookStoreEntry.COLUMN_BOOK_PRICE);
            int quantity_column_index = cursor.getColumnIndex(
                    BookStoreContract.BookStoreEntry.COLUMN_BOOK_QUANTITY);
            int supplier_name_column_index = cursor.getColumnIndex(
                    BookStoreContract.BookStoreEntry.COLUMN_BOOK_SUPPLIER_NAME);
            int supplier_phone_number_column_index = cursor.getColumnIndex(
                    BookStoreContract.BookStoreEntry.COLUMN_BOOK_SUPPLIER_PHONE_NUMBER);

            while (cursor.moveToNext()) {
                int id_value = cursor.getInt(id_column_index);
                String name_value = cursor.getString(name_column_index);
                int price_value = cursor.getInt(price_column_index);
                int quantity_value = cursor.getInt(quantity_column_index);
                String supplier_name_value = cursor.getString(supplier_name_column_index);
                String supplier_phone_number_value = cursor.getString(supplier_phone_number_column_index);

                LOG_MSG.append(id_value).append("\t")
                        .append(name_value).append("\t")
                        .append(price_value).append("\t")
                        .append(quantity_value).append("\t")
                        .append(supplier_name_value).append("\t")
                        .append(supplier_phone_number_value).append("\n");
            }

            Log.d("database - query",LOG_MSG.toString());

        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

    }
}
