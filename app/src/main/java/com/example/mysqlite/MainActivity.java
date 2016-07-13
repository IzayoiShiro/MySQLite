package com.example.mysqlite;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    private MyOpenHelper helper;
    private SQLiteDatabase db;
    private Button insert_b,resolver_b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setOnClickListener();
    }

    private void setOnClickListener() {
        insert_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = helper.getWritableDatabase();
                for (int i = 0 ;i < 10 ; i++){
                    ContentValues values = new ContentValues();
                    values.put("name","robot"+i);
                    values.put("number","100"+i);
                    db.insert("person",null,values);
                }
                db.close();
            }
        });
        resolver_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String path = "content://com.example.mysqlite.personDB/query";
                Uri uri = Uri.parse(path);
                ContentResolver resolver = getContentResolver();
                Cursor cursor = resolver.query(uri,null,null,null,null,null);
                while (cursor.moveToNext()){
                    int id = cursor.getInt(cursor.getColumnIndex("id"));
                    String name = cursor.getString(1);
                    String number = cursor.getString(2);
                    System.out.println(id+"---"+name+"---"+number);
                }
                cursor.close();
            }
        });
    }

    private void init() {
        insert_b = (Button) findViewById(R.id.insert);
        resolver_b = (Button) findViewById(R.id.resolver);
        helper = new MyOpenHelper(this);
    }
}
