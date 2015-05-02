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


public class DisplayGivers extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_givers);
        ListView myListView = (ListView)findViewById(R.id.listViewGivers);
        final ArrayList<String> items = new ArrayList<String>();
        // Create the array adapter to bind the array to the listview
        final ArrayAdapter<String> listAdapter;
        listAdapter = new ArrayAdapter<String>(this,
                R.layout.activity_disp_ind_fav,
                items);

        Intent intent=getIntent();
        final String email1=intent.getStringExtra("email");
        final String uname1=intent.getStringExtra("uname");
        final String status=intent.getStringExtra("status");
        System.out.println(uname1);
        final ArrayList<JSONObject> jobs=new ArrayList<JSONObject>();
        String tag_string_req = "req_givers";
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
                        JSONArray givers=jObj.getJSONArray("rs");
                        //JSONObject jtmp=givers.getJSONObject(0);
                        for(int i=0;i<givers.length();i++) {
                            System.out.println(givers.getJSONObject(i));
                            jobs.add(givers.getJSONObject(i));
                            listAdapter.add(givers.getJSONObject(i).getJSONObject("record").getString("uname") +
                                    "  |  " + givers.getJSONObject(i).getJSONObject("record").getString("etd")+
                            "  |  "+givers.getJSONObject(i).getJSONObject("record").getString("eta"));
                        }

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
                params.put("tag", "givers");
                params.put("email", email1);
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
                JSONObject jchoice=jobs.get(position);
                try {
                    final String email2=jchoice.getJSONObject("record").getString("email");
                    final String uname2=jchoice.getJSONObject("record").getString("uname");
                    view.animate().setDuration(100).alpha(0)
                            .withEndAction(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent = new Intent(DisplayGivers.this,
                                            AskForFav.class);
                                    intent.putExtra("email1",email2);
                                    intent.putExtra("uname1",uname2);
                                    intent.putExtra("email2",email1);
                                    intent.putExtra("uname2",uname1);
                                    intent.putExtra("status",status);
                                    startActivity(intent);
                                    //finish();
                                    listAdapter.notifyDataSetChanged();
                                    view.setAlpha(1);
                                    //    }
                                }
                            });
                }catch(Exception e){e.printStackTrace();}
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