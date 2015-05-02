package info.androidhive.loginandregistration.helper;
import java.util.ArrayList;
/**
 * Created by Admin on 10-04-2015.
 */
public class Favours {
    String Desc;
    String dest;
    float amt_limit;
    float weight;
    float dist_limit;
    String deadline;
    String item_list;
    String uname;
    String email;

    public Favours(String des, String dst, float amt, float wt, float distlmt, String time, String items,String user,String emailID){
        Desc = des;
        dest = dst;
        amt_limit = amt;
        weight = wt;
        dist_limit = distlmt;
        deadline = time;
        item_list = items;
        uname=user;
        email=emailID;
    }
    public String getFavourDescription(){
        return Desc;
    }
    public float getDistanceLimit(){
        return dist_limit;
    }
    public String getDestination(){
        return dest;
    }
    public String getItemList(){
        return item_list;
    }
    public float getAmountLimit(){
        return amt_limit;
    }
    public float getWeightLimit(){
        return weight;
    }
    public String getDeadLine(){
        return deadline;
    }
    public String getUsername(){
        return uname;
    }
    public String getEmailID(){
        return email;
    }
    public void setFavourDescription(String desc){
        Desc = desc;
    }
    public void setDistanceLimit(float dist){
        dist_limit = dist;
    }
    public void setDestination(String dst){
        dest = dst;
    }
    public void setItemList(String items){
        item_list = items;
    }
    public void setAmountLimit(float amt){
        amt_limit = amt;
    }
    public void setWeightLimit(float wt){
        weight = wt;
    }
    public void getDeadLine(String time){
        deadline = time;
    }
    public void setUsername(String user){
        uname = user;
    }
    public void setEmailID(String emailID){
        email=emailID;
    }
}
