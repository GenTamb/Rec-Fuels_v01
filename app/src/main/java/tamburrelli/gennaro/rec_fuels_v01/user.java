package tamburrelli.gennaro.rec_fuels_v01;

/**
 * Created by Gennaro on 02/02/2016.
 */
public class user {
    private String userName,userCar;

    public user(){
        this.userCar="";
        this.userName="";
    }
    public user(String uname,String ucar){
        this.userName=uname;
        this.userCar=ucar;
    }
    public void setUserName(String uname){
        this.userName=uname;
    }
    public void setUserCar(String ucar){
        this.userCar=ucar;
    }
    public String getUserName(){
        return this.userName;
    }
    public String getUserCar(){
        return this.userCar;
    }
}
