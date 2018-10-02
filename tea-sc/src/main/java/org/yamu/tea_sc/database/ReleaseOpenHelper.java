package org.yamu.tea_sc.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.greenrobot.greendao.database.Database;
import org.yamu.tea_sc.database.DaoMaster.OpenHelper;

/**
 * Created by mjt89 on 2018/10/2 0002
 */
public class ReleaseOpenHelper extends OpenHelper {


    public ReleaseOpenHelper(Context context, String name) {
        super(context, name);
    }

    public ReleaseOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onCreate(Database db) {
        super.onCreate(db);
    }
}
