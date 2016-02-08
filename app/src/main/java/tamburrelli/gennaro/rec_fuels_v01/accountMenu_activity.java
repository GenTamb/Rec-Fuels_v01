package tamburrelli.gennaro.rec_fuels_v01;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Gennaro on 05/02/2016.
 */
public class accountMenu_activity extends Activity {

    public String FuelType;
    public String KmDone;
    public String FuelAmount;
    public String ReFuelCost;
    public String Note;
    public String Position;
    public String Date;
    public userCRUDdb record;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accountmenu_activity);
        Bundle extras = getIntent().getExtras();
        final String userName = extras.getString("userName");
        final String userCar = extras.getString("userCar");
        TextView userNameText=(TextView)findViewById(R.id.editUserName);
        TextView userCarText=(TextView)findViewById(R.id.editUserCar);
        userNameText.setText(userName);
        userCarText.setText(userCar);
        //button add record
        Button add=(Button)findViewById(R.id.buttonADDrecord);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get radio selection
                RadioGroup radio=(RadioGroup)findViewById(R.id.radioGroup);
                int selection=radio.getCheckedRadioButtonId();
                try {
                    RadioButton radioSelected = (RadioButton) findViewById(selection);
                    FuelType=radioSelected.getText().toString(); //store
                }
                catch (NullPointerException e){
                    Toast.makeText(getApplicationContext(),"No fuel Type selected!",Toast.LENGTH_SHORT).show();
                }

                //get kmdone value
                EditText kmDone=(EditText)findViewById(R.id.editKmDone);
                KmDone=kmDone.getText().toString();    //store


                //get fuel amount
                EditText amount=(EditText)findViewById(R.id.editFuelAmount);
                FuelAmount=amount.getText().toString(); //store


                //get refuel cost
                EditText cost=(EditText)findViewById(R.id.editReFuelCost);
                ReFuelCost=cost.getText().toString(); //store


                //get gasstationname
                EditText fnote=(EditText)findViewById(R.id.editNote);
                Note=fnote.getText().toString(); //store

                //get position
                Position="TODO";

                //get date
                DatePicker date=(DatePicker)findViewById(R.id.datePicker);
                int day=date.getDayOfMonth();
                int month=date.getMonth()+1;
                int year=date.getYear();
                Date=Integer.toString(day)+"-"+Integer.toString(month)+"-"+Integer.toString(year); //store
                if(KmDone.isEmpty() || FuelAmount.isEmpty() || ReFuelCost.isEmpty())
                    Toast.makeText(getApplicationContext(),"Check fields for errors",Toast.LENGTH_SHORT).show();
                else {
                    Float KmDoneFloat=Float.parseFloat(KmDone);
                    Float FuelAmountFloat=Float.parseFloat(FuelAmount);
                    Float ReFuelCostFloat=Float.parseFloat(ReFuelCost);
                    //insert into db
                    record = new userCRUDdb(getApplicationContext(), userName, userCar);
                    try {
                        record.insert(KmDoneFloat, FuelType, FuelAmountFloat, ReFuelCostFloat, Date, Position, Note);
                        Toast.makeText(getApplicationContext(), "Record Added", Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(),KmDoneFloat+FuelType+FuelAmountFloat+ReFuelCostFloat+Date+Position+Note,Toast.LENGTH_LONG).show();
                        //clear inputs
                        radio.clearCheck();
                        kmDone.setText("");
                        amount.setText("");
                        cost.setText("");
                        fnote.setText("");
                        //position to do

                    } catch (SQLiteException sqle) {
                        Toast.makeText(getApplicationContext(), "error:" + sqle, Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        //button go to history
        Button goToHistory=(Button)findViewById(R.id.buttonShowHistory);
        goToHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showHistory=new Intent(getApplicationContext(),accountHistory_activity.class);
                showHistory.putExtra("userName",userName);
                showHistory.putExtra("userCar",userCar);
                startActivity(showHistory);
            }
        });
    }

}
