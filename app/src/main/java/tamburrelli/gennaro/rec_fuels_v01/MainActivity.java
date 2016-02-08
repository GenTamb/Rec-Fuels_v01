package tamburrelli.gennaro.rec_fuels_v01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connDB FuelRecDB = new connDB(getApplicationContext());

        userCRUDdb user = new userCRUDdb(getApplicationContext(), "", "");


        final ArrayAdapter<String> adapter= new ArrayAdapter<>(this, R.layout.row, user.queryUsersTable());
        ListView list = (ListView) findViewById(R.id.listView);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> av, View v, int pos, long id) {
                TextView c = (TextView) v.findViewById(R.id.elementRow);
                String content = c.getText().toString();
                Toast.makeText(getApplicationContext(), content, Toast.LENGTH_SHORT).show();
                String[] all=content.split("-");
                Intent accountMenu;
                accountMenu = new Intent(getApplicationContext(), accountMenu_activity.class);
                accountMenu.putExtra("userName",all[0]);
                accountMenu.putExtra("userCar",all[1]);
                startActivity(accountMenu);
            }
        });


        Button newAccount=(Button) findViewById(R.id.newAccount);
        newAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Insert new user", Toast.LENGTH_SHORT).show();
                Intent insertNewAccount;
                insertNewAccount = new Intent(getApplicationContext(), newAccount.class);
                startActivity(insertNewAccount);

            }
        });

    }



    }











