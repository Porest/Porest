package com.planb.porest.support.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by dsm2016 on 2017-07-05.
 */

public class DBHelper extends SQLiteOpenHelper {
    private volatile static DBHelper helper;

    private DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public static DBHelper getInstance(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        if(helper == null) {
            // DCL, 인스턴스가 null일 때 동기화를 얻음으로서 synchronize의 오버헤드 감소
            synchronized (DBHelper.class) {
                if(helper == null) {
                    helper = new DBHelper(context, name, factory, version);
                }
            }
        }

        return helper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE `checker`(id TEXT)");
        // 싱글 튜플 DB, cookie 유무를 판단하여 자동 로그인 처리

        db.execSQL("INSERT INTO `checker` VALUES(null)");
        // 초기값은 null
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }
}
