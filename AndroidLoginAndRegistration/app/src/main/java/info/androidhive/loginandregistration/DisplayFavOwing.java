package info.androidhive.loginandregistration;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import info.androidhive.loginandregistration.app.AppConfig;
import info.androidhive.loginandregistration.app.AppController;
import info.androidhive.loginandregistration.helper.SessionManager;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;


public class DisplayFavOwing extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_fav_owing);
        ListView myListView = (ListView)findViewById(R.id.listView);
        final ArrayList<String> items = new ArrayList<String>();
        // Create the array adapter to bind the array to the listview
        final ArrayAdapter<String> listAdapter;
        listAdapter = new ArrayAdapter<String>(this,
                R.layout.activity_disp_ind_fav,
                items);

        Intent intent=getIntent();
        final String email=intent.getStringExtra("email");

        final ArrayList<JSONObject> jobjs=new ArrayList<JSONObject>();
        String tag_string_req = "req_owing";
        StringRequest strReq = new StringRequest(Method.POST,
                AppConfig.URL_REGISTER, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                //Toast.makeText(getApplicationContext(),response, Toast.LENGTH_LONG).show();
                try {

                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    // Check for error node in json

                    if(error) {
                        // Error in login. Get the error message
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
                    }
                    else
                    {


                        String fid=jObj.getString("fid");
                        System.out.println(jObj);
                        //listAdapter.add(fid);
                        JSONArray favour=jObj.getJSONArray("rs");
                        System.out.println(favour.getJSONObject(0));
                        System.out.println(favour.getJSONObject(1));
                        JSONObject jtmp=favour.getJSONObject(0);
                        for(int i=0;i<favour.length();i++) {
                            jobjs.add(favour.getJSONObject(i));
                            listAdapter.add(favour.getJSONObject(i).getJSONObject("record").getString("uname2") + "  |  " + favour.getJSONObject(i).getJSONObject("record").getString("description"));
                        }
                        //Toast.makeText(getApplicationContext(),
                        //fid, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                //Log.e( "Login Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }){

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("tag", "owing");
                params.put("email", email);

                return params;
            }

        };

        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
        //listAdapter.add("1");
        //listAdapter.add("2");
        //listAdapter.add("3");
        myListView.setAdapter(listAdapter);
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);
                JSONObject jchoice=jobjs.get(position);
                System.out.println(jchoice);
                try {
                    final String status = jchoice.getJSONObject("record").getString("status");
                    final String description=jchoice.getJSONObject("record").getString("description");
                    final String dest=jchoice.getJSONObject("record").getString("dest");
                    final String amount=jchoice.getJSONObject("record").getString("amount");
                    final String weight=jchoice.getJSONObject("record").getString("weight");
                    final String dist=jchoice.getJSONObject("record").getString("dist");
                    final String deadline=jchoice.getJSONObject("record").getString("deadline");
                    final String uname2=jchoice.getJSONObject("record").getString("uname2");
                    final String email2=jchoice.getJSONObject("record").getString("email2");
                    final String fid=jchoice.getJSONObject("record").getString("fid");
                    System.out.println(status);
                    view.animate().setDuration(100).alpha(0)
                            .withEndAction(new Runnable() {
                                @Override
                                public void run() {
                                    //if (item == "3") {
                                    if(status.equalsIgnoreCase("Pending")){
                                        Intent intent = new Intent(DisplayFavOwing.this,
                                                DispIndFav2.class);
                                        String favour="Description : "+description+"\n"+
                                                "Destination       : "+dest+"\n"+
                                                "Amount            : Rs. "+amount+"\n"+
                                                "Weight            : "+weight+"kg\n"+
                                                "Distance          : "+dist+"km\n"+
                                                "Deadline          :"+deadline+"\n"+
                                                "Name              :"+uname2+"\n"+
                                                "Email             :"+email2+"\n"+
                                                "Status            :"+status+"\n";
                                        intent.putExtra("favour", favour);
                                        intent.putExtra("status",status);
                                        intent.putExtra("fid",fid);
                                        startActivity(intent);
                                        //finish();
                                        //listAdapter.notifyDataSetChanged();
                                        view.setAlpha(1);
                                    }
                                    else {
                                        Intent intent = new Intent(DisplayFavOwing.this,
                                                DispIndFav.class);
                                        String favour="Description : "+description+"\n"+
                                                "Destination       : "+dest+"\n"+
                                                "Amount            : Rs. "+amount+"\n"+
                                                "Weight            : "+weight+"kg\n"+
                                                "Distance          : "+dist+"km\n"+
                                                "Deadline          :"+deadline+"\n"+
                                                "Name              :"+uname2+"\n"+
                                                "Email             :"+email2+"\n"+
                                                "Status            :"+status+"\n";
                                        intent.putExtra("favour", favour);
                                        startActivity(intent);
                                        //finish();
                                        //listAdapter.notifyDataSetChanged();
                                        view.setAlpha(1);
                                    }

                                }
                            });
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display_fav, menu);
        return true;
    }

}