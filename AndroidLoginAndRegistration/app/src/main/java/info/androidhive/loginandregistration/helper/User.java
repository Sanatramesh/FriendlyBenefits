package info.androidhive.loginandregistration.helper;

/**
 * Created by Admin on 10-04-2015.
 */
import java.util.ArrayList;

public class User {
    String Username;
    String EmailId;
    ArrayList<Favours> owed;
    ArrayList<Favours> owing;

    public User()
    {
        Username="";
        EmailId="";
        owed=new ArrayList<Favours>();
        owing=new ArrayList<Favours>();
    }
    public String get_Username()
    {
        return Username;
    }
    public String get_EmailId()
    {
        return EmailId;
    }
    public ArrayList<Favours> get_owed()
    {
        return owed;
    }
    public ArrayList<Favours> get_owing()
    {
        return owing;
    }
    public void setUsername(String user)
    {
        Username=user;
    }
    public void setEmailId(String email)
    {
        EmailId=email;
    }
    public void requestFavour(Favours fav)
    {
        owed.add(fav);
    }
    public void offerFavour(Favours fav)
    {
        owing.add(fav);
    }
    public void removeOwed(Favours fav)
    {
        owed.remove(fav);
    }
    public void removeOwing(Favours fav)
    {
        owing.remove(fav);
    }
}