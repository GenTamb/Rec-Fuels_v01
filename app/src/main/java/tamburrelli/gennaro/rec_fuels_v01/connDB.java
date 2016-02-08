package tamburrelli.gennaro.rec_fuels_v01;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Gennaro on 02/02/2016.
 */
public class connDB extends SQLiteOpenHelper {
    public static final String DBNAME="RecordFuels";

    public connDB(Context context){
        super(context,DBNAME,null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String usersTable="CREATE TABLE IF NOT EXISTS users"+
                "( ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                " userName TEXT," +
                " userCar Text)";
        db.execSQL(usersTable);
        /*String fuelType="CREATE TABLE IF NOT EXISTS fuelType"+
                "(type TEXT PRIMARY KEY,"+
                " seq INTEGER)";
        db.execSQL(fuelType);
        ContentValues cv=new ContentValues();
        cv.put("type","Diesel");
        cv.put("seq", "1");
        try{
            db.insert("fuelType",null,cv);
        }
        catch(SQLiteException sqle){
            //error
        }
        ContentValues cv1=new ContentValues();
        cv1.put("type","Petrol");
        cv1.put("seq", "2");
        try{
            db.insert("fuelType",null,cv1);
        }
        catch(SQLiteException sqle){
            //error
        }
        ContentValues cv2=new ContentValues();
        cv2.put("type","LPG");
        cv2.put("seq", "3");
        try{
            db.insert("fuelType",null,cv2);
        }
        catch(SQLiteException sqle){
            //error
        }*/

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

   /* public ArrayList queryUsersTable()
    {
        ArrayList res=new ArrayList();
        Cursor crs=null;
        try {
            SQLiteDatabase db = getReadableDatabase();
            crs = db.rawQuery("SELECT * FROM users",null);
            if (crs != null) {
                if (crs.moveToFirst()) {
                    do {
                        String userName = crs.getString(crs.getColumnIndex("userName"));
                        String userCar = crs.getString(crs.getColumnIndex("userCar"));
                        res.add(userName);
                        res.add(userCar);
                    } while (crs.moveToNext());
                }
            }
            else{
                res.add("Welcome,add a new user by clicking on the button!");
            }

        }
        catch(SQLiteException sqle)
        {
            return null;
        }
        crs.close();
        return res;
    }*/
}
