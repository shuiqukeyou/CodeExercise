package com.example.lastation.databasetest;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class DatabaseProvider extends ContentProvider {
    public static final int BOOK_DIR = 0;
    public static final int BOOK_ITME = 1;
    public static final int CATEGORY_DIR = 2;
    public static final int CATEGORY_IERM = 3;
    public static final String AUTORITY = "com.example.lastation.databasetest.provider";
    private static UriMatcher uriMatcher;
    private MyDatabaseHelper dbHelper;

    static {
        uriMatcher = new UriMatcher(uriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTORITY, "book", BOOK_DIR);
        uriMatcher.addURI(AUTORITY, "book/#", BOOK_ITME);
        uriMatcher.addURI(AUTORITY, "category", CATEGORY_DIR);
        uriMatcher.addURI(AUTORITY, "category/#" ,CATEGORY_IERM);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int deletedRows = 0;
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
                deletedRows = db.delete("BOOK", selection, selectionArgs);
                break;
            case BOOK_ITME :
                String bookId = uri.getPathSegments().get(1);
                deletedRows = db.delete("BOOK", "id = ?", new String[] {bookId});
                break;
            case CATEGORY_DIR:
                deletedRows = db.delete("Category", selection, selectionArgs);
                break;
            case CATEGORY_IERM:
                String categeryId = uri.getPathSegments().get(1);
                deletedRows = db.delete("Category", "id = ?", new String[]{categeryId});
                break;
            default:
                break;
        }
        return deletedRows;
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.databasetest.provider.book";
            case BOOK_ITME :
                return "vnd.android.cursor.item/vnd.com.example.databasetest.provider.book";
            case CATEGORY_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.databasetest.provider.categoryt";
            case CATEGORY_IERM:
                return "vnd.android.cursor.item/vnd.com.example.databasetest.provider.categoryt";
        }
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Uri uriReturn = null;
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
            case BOOK_ITME :
                long newBookId = db.insert("Book", null, values);
                uriReturn = Uri.parse("comtent://" + AUTORITY + "/book/" + newBookId);
                break;
            case CATEGORY_DIR:
                break;
            case CATEGORY_IERM:
                long newCategoryId = db.insert("Category", null, values);
                uriReturn = Uri.parse("content://" + AUTORITY + "/category/" + newCategoryId);
                break;
            default:
                break;
        }
        return uriReturn;
    }

    @Override
    public boolean onCreate() {
        dbHelper = new MyDatabaseHelper(getContext(), "BookStore.db", null, 2);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
                cursor = db.query("Book", projection , selection, selectionArgs, null, null, sortOrder);
                break;
            case BOOK_ITME :
                String bookId = uri.getPathSegments().get(1);
                cursor = db.query("Book", projection, "id = ?", new String[]{bookId}, null, null, sortOrder);
                break;
            case CATEGORY_DIR:
                cursor = db.query("Category", projection , selection, selectionArgs, null, null, sortOrder);
                break;
            case CATEGORY_IERM:
                String categeryId = uri.getPathSegments().get(1);
                cursor = db.query("Category", projection, "id = ?", new String[]{categeryId}, null, null, sortOrder);
                break;
            default:
                break;
        }
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int updateRows = 0;
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
                updateRows = db.update("BOOK",values , selection, selectionArgs);
                break;
            case BOOK_ITME :
                String bookId = uri.getPathSegments().get(1);
                updateRows = db.update("BOOK", values, "id = ?", new String[] {bookId});
                break;
            case CATEGORY_DIR:
                updateRows = db.update("Category", values, selection, selectionArgs);
                break;
            case CATEGORY_IERM:
                String categeryId = uri.getPathSegments().get(1);
                updateRows = db.update("Category", values, "id = ?", new String[]{categeryId});
                break;
            default:
                break;
        }
        return updateRows;
    }
}
