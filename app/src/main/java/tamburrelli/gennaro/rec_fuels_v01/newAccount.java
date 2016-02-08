package tamburrelli.gennaro.rec_fuels_v01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Gennaro on 05/02/2016.
 */
public class newAccount extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_account);



        Button newAccount=(Button) findViewById(R.id.addUserToDB);
        newAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView userName=(TextView)findViewById(R.id.editUserName);
                TextView userCar=(TextView)findViewById(R.id.editUserCar);
                String userNameContent=userName.getText().toString();
                String userCarContent=userCar.getText().toString();
                if(userNameContent.equals("") || userCarContent.equals("")) {
                    Toast.makeText(getApplicationContext(),"One or both fields are empty, fill them!",Toast.LENGTH_LONG).show();
                }
                else{
                    userNameContent=userNameContent.replace(" ","");
                    userCarContent=userCarContent.replace(" ","");
                    userCRUDdb user = new userCRUDdb(getApplicationContext(),userNameContent,userCarContent);
                    user.createUserTable();
                    Toast.makeText(getApplicationContext(), "User Added", Toast.LENGTH_LONG).show();
                    Intent getBack;
                    getBack = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(getBack);
                }

            }
        });

    }



}
