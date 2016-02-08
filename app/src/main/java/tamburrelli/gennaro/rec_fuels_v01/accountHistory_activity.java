package tamburrelli.gennaro.rec_fuels_v01;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Gennaro on 06/02/2016.
 */
public class accountHistory_activity extends Activity {
    private CursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accounthistory_activity);
        Bundle extras = getIntent().getExtras();
        final String userName = extras.getString("userName");
        final String userCar = extras.getString("userCar");

        userCRUDdb user = new userCRUDdb(getApplicationContext(), userName, userCar);
        ListView listview = (ListView) findViewById(R.id.listView);
        try {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.row, user.getHistory());
            ListView list = (ListView) findViewById(R.id.listView);
            list.setAdapter(adapter);
        }
        catch(NullPointerException e){
            Toast.makeText(getApplicationContext(),"No entries for this account",Toast.LENGTH_LONG).show();
        }
        /*
        ListView listview = (ListView) findViewById(R.id.listView);
        ArrayList result = new ArrayList();
        Cursor crs = user.getHistoryCursor();
            if (crs.moveToFirst()) {
                do {
                    String id = crs.getString(crs.getColumnIndex("ID"));
                    String kmdone = crs.getString(crs.getColumnIndex("kmDone"));
                    String fuelType = crs.getString(crs.getColumnIndex("fuelType"));
                    String fuelAmount = crs.getString(crs.getColumnIndex("fuelAmount"));
                    String cost = crs.getString(crs.getColumnIndex("cost"));
                    String date = crs.getString(crs.getColumnIndex("date"));
                    String position = crs.getString(crs.getColumnIndex("position"));
                    String note = crs.getString(crs.getColumnIndex("note"));
                    result.add(id);
                    result.add(kmdone);
                    result.add(fuelType);
                    result.add(fuelAmount);
                    result.add(cost);
                    result.add(date);
                    result.add(position);
                    result.add(note);
                } while (crs.moveToNext());
            }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.row, result);
        listview.setAdapter(adapter);
        */

    }
}


