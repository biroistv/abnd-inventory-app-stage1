package com.example.biro.inventory_app.BookStoreData;

import android.provider.BaseColumns;

public class BookStoreContract {

    public static abstract class BookStoreEntry implements BaseColumns{

        public static final String TABLE_NAME = "books";

        public static final String COLUMN_BOOK_ID = BaseColumns._ID;
        public static final String COLUMN_BOOK_NAME = "name";
        public static final String COLUMN_BOOK_PRICE = "price";
        public static final String COLUMN_BOOK_QUANTITY = "quantity";
        public static final String COLUMN_BOOK_SUPPLIER_NAME = "supplier_name";
        public static final String COLUMN_BOOK_SUPPLIER_PHONE_NUMBER = "supplier_phone_number";



    }
}
