package com.example.mysqlite;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by 杨天宇 on 2016/7/13.
 */
public class MyContentProvider extends ContentProvider {

    private static UriMatcher matcher = new UriMatcher(-1);
    static {
        matcher.addURI("com.example.mysqlite.personDB","query",1);
    }

    private MyOpenHelper helper;

    @Override
    public boolean onCreate() {
        helper = new MyOpenHelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {
        SQLiteDatabase db = helper.getReadableDatabase();
        int result = matcher.match(uri);
        if (result == 1){
            Cursor cursor = db.query("person",strings,s,strings1,null,null,s1);
            return cursor;
        } else {
            throw new RuntimeException("Uri解析失败");
        }
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }
}
