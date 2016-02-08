package tamburrelli.gennaro.rec_fuels_v01;

import android.content.Context;

/**
 * Created by Gennaro on 02/02/2016.
 */
public class manageDB {
    private connDB database;

    public manageDB(Context ctx){
        database=new connDB(ctx);
    }

    public void create(String name){}
}
