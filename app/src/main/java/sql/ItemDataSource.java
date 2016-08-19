package sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import model.ModelItemFavorites;

/**
 * Created by Mario on 13/08/2016.
 */
public class ItemDataSource
{

    private final SQLiteDatabase db;

    public ItemDataSource(Context context)
    {
        MySqliteHelper helper = new MySqliteHelper(context);
        db=helper.getWritableDatabase();
    }
    public void saveItemFavorites(ModelItemFavorites modelItemFavorites)
    {
        ContentValues contentValuesAPP = new ContentValues();
        contentValuesAPP.put(MySqliteHelper.COLUMN_ITEM_IMGPHOTO,modelItemFavorites.imgphoto);
        contentValuesAPP.put(MySqliteHelper.COLUMN_ITEM_CAMFULL,modelItemFavorites.camfull);
        contentValuesAPP.put(MySqliteHelper.COLUMN_ITEM_LANDDATE,modelItemFavorites.landdate);
        contentValuesAPP.put(MySqliteHelper.COLUMN_ITEM_CAMNAME,modelItemFavorites.camname);
        contentValuesAPP.put(MySqliteHelper.COLUMN_ITEM_ROVNAME,modelItemFavorites.rovname);
        db.insert(MySqliteHelper.TABLE_NAME,null,contentValuesAPP);
    }
    public void updateItemFavorites(ModelItemFavorites modelItemFavorites)
    {
        ContentValues contentValuesAPP = new ContentValues();
        contentValuesAPP.put(MySqliteHelper.COLUMN_ITEM_IMGPHOTO,modelItemFavorites.imgphoto);
        contentValuesAPP.put(MySqliteHelper.COLUMN_ITEM_CAMFULL,modelItemFavorites.camfull);
        contentValuesAPP.put(MySqliteHelper.COLUMN_ITEM_LANDDATE,modelItemFavorites.landdate);
        contentValuesAPP.put(MySqliteHelper.COLUMN_ITEM_CAMNAME,modelItemFavorites.camname);
        contentValuesAPP.put(MySqliteHelper.COLUMN_ITEM_ROVNAME,modelItemFavorites.rovname);
        db.update(MySqliteHelper.TABLE_NAME,contentValuesAPP,MySqliteHelper.COLUMN_ID+"='"+modelItemFavorites.id+"'",null);
    }
    public void deleteItem(ModelItemFavorites modelItemFavorites)
    {
        db.delete(MySqliteHelper.TABLE_NAME,MySqliteHelper.COLUMN_ID+"=?",
                new String[]{String.valueOf(modelItemFavorites.id)});
    }
    public List<ModelItemFavorites> getAllItems()
    {
        List<ModelItemFavorites>modelItemList = new ArrayList<>();
        Cursor cursor = db.query(MySqliteHelper.TABLE_NAME,null,null,null,null,null,null);
        while (cursor.moveToNext())
        {
            int id_table = cursor.getInt(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_ID));
            String imgphoto=cursor.getString(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_ITEM_IMGPHOTO));
            String camfull=cursor.getString(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_ITEM_CAMFULL));
            String  landdate=cursor.getString(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_ITEM_LANDDATE));
            String camname=cursor.getString(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_ITEM_CAMNAME));
            String rovname=cursor.getString(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_ITEM_ROVNAME));
            /*Log.d("camfull",camfull);
            Log.d("camfull",camname);
            Log.d("landate",landdate);
            Log.d("imgphoto",imgphoto);
            Log.d("rovname",rovname);*/
            ModelItemFavorites modelitem = new ModelItemFavorites();
            modelitem.id=id_table;
            modelitem.imgphoto=imgphoto;
            modelitem.camfull=camfull;
            modelitem.landdate=landdate;
            modelitem.camname=camname;
            modelitem.rovname=rovname;
            modelItemList.add(modelitem);
        }
        return modelItemList;
    }
}
