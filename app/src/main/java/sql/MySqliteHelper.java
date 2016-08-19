package sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by Mario on 13/08/2016.
 */
public class MySqliteHelper  extends SQLiteOpenHelper
{
    private final static String DATABASE_NAME = "appAPOD";
    private final static int DATABASE_VERSION=1;
    public static final String TABLE_NAME = "item_favorite";
    public static final String COLUMN_ID = BaseColumns._ID;
    public static final String COLUMN_ITEM_IMGPHOTO = "imgphoto";
    public static final String COLUMN_ITEM_CAMFULL = "camarafull";
    public static final String COLUMN_ITEM_LANDDATE = "landdate";
    public static final String COLUMN_ITEM_CAMNAME = "caneraname";
    public static final String COLUMN_ITEM_ROVNAME = "rovername";

    private static final String CREATE_TABLE="create table "+TABLE_NAME+
            "("+COLUMN_ID+" integer primary key autoincrement,"+
            COLUMN_ITEM_IMGPHOTO+" text not null,"+
            COLUMN_ITEM_CAMFULL+" text not null,"+
            COLUMN_ITEM_LANDDATE+" text not null,"+
            COLUMN_ITEM_CAMNAME+" text not null,"+
            COLUMN_ITEM_ROVNAME+" text not null)";

    private static final String DELETE_TABLE=" FROM "+TABLE_NAME;

    public MySqliteHelper(Context context) {
        super(context, DATABASE_NAME,null,DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETE_TABLE);
        db.execSQL(CREATE_TABLE);
    }
}
