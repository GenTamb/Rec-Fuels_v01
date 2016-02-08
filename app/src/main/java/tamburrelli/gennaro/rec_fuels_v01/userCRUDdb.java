package tamburrelli.gennaro.rec_fuels_v01;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Gennaro on 02/02/2016.
 */
public class userCRUDdb {
    private connDB db = null;
    private user user;

    public userCRUDdb(Context context, String userName, String userCar) {
        this.db = new connDB(context);
        this.user = new user(userName, userCar);
    }

    public void createUserTable() {
        SQLiteDatabase dataBase = this.db.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("userName", this.user.getUserName());
        cv.put("userCar", this.user.getUserCar());
        try {
            dataBase.insert("users", null, cv);
        } catch (SQLiteException sqle) {
            //error
        }
        String usersTable = "CREATE TABLE IF NOT EXISTS " + this.user.getUserName() + this.user.getUserCar() +
                "( ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                " kmDone REAL NOT NULL," +
                " fuelType TEXT NOT NULL," +
                " fuelAmount REAL NOT NULL," +
                " cost REAL NOT NULL," +
                " date TEXT NOT NULL," +
                " position TEXT," +
                " note TEXT)";
        dataBase.execSQL(usersTable);
    }


    public void insert(Float kmDone, String fuelType, Float fuelAmount, Float cost, String date, String position, String gasStationName) throws SQLiteException {
        SQLiteDatabase database = this.db.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("kmDone", kmDone);
        cv.put("fuelType", fuelType);
        cv.put("fuelAmount", fuelAmount);
        cv.put("cost", cost);
        cv.put("date", date);
        cv.put("position", position);
        cv.put("note", gasStationName);
        database.insert(this.user.getUserName() + this.user.getUserCar(), null, cv);


    }

    public ArrayList queryUsersTable() {
        ArrayList res = new ArrayList();
        Cursor crs = null;
        try {
            SQLiteDatabase dbu = this.db.getReadableDatabase();
            crs = dbu.rawQuery("SELECT * FROM users", null);
            if (crs != null) {
                if (crs.moveToFirst()) {
                    do {
                        String userName = crs.getString(crs.getColumnIndex("userName"));
                        String userCar = crs.getString(crs.getColumnIndex("userCar"));
                        res.add(userName + "-" + userCar);
                    } while (crs.moveToNext());
                }
            }

        } catch (SQLiteException sqle) {
            return null;
        }
        crs.close();
        return res;
    }

    public ArrayList getHistory() {
        ArrayList res = new ArrayList();
        Cursor crs = null;
        try {
            SQLiteDatabase dbu = this.db.getReadableDatabase();
            String table=this.user.getUserName()+this.user.getUserCar();
            crs = dbu.rawQuery("SELECT * FROM " + table, null);
                if (crs.moveToFirst()) {
                    do {
                        String id = crs.getString(crs.getColumnIndex("ID"));
                        String km = crs.getString(crs.getColumnIndex("kmDone"));
                        String ftype = crs.getString(crs.getColumnIndex("fuelType"));
                        String famount = crs.getString(crs.getColumnIndex("fuelAmount"));
                        String fcost = crs.getString(crs.getColumnIndex("cost"));
                        String date = crs.getString(crs.getColumnIndex("date"));
                        String position = crs.getString(crs.getColumnIndex("position"));
                        String note = crs.getString(crs.getColumnIndex("note"));
                        res.add("NUM: "+id + "- KM: " + km + "- FTYPE: " + ftype + "- AMOUNT: " + famount + "- COST: " + fcost + "- DATE: " + date + "- POSITION: " + position + "- NOTE: " + note);
                    } while (crs.moveToNext());
                }
        } catch (SQLiteException sqle) {
            return null;
        }
        crs.close();
        return res;

    }

}

