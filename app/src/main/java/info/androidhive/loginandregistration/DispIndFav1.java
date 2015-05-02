package info.androidhive.loginandregistration;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import info.androidhive.loginandregistration.app.AppConfig;
import info.androidhive.loginandregistration.app.AppController;


public class DispIndFav1 extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disp_ind_fav1);
        Intent intent=getIntent();
        final String favour=intent.getStringExtra("favour");
        //final String status=intent.getStringExtra("status");
        final String fid=intent.getStringExtra("fid");
        TextView tv = (TextView) findViewById(R.id.textView);
        tv.setText(favour);
        Button complete=(Button) findViewById(R.id.completedButton);
        Button incomplete=(Button) findViewById(R.id.incompleteButton);
        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tag = "req_endfav";
                //System.out.println("Hello");
                StringRequest strReq = new StringRequest(Request.Method.POST,
                        AppConfig.URL_REGISTER, new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(getApplicationContext(),response, Toast.LENGTH_LONG).show();
                        try {

                            JSONObject jObj = new JSONObject(response);
                            boolean error = jObj.getBoolean("error");
                            System.out.println(response);


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
                        params.put("tag", "endfav");
                        params.put("fid", fid);
                        params.put("status","Completed");
                        return params;
                    }

                };
                AppController.getInstance().addToRequestQueue(strReq, tag);
                Toast.makeText(getApplicationContext(),
                        "Task completed! Return to main menu for updates.", Toast.LENGTH_LONG).show();
            }
            }

            );
            incomplete.setOnClickListener(new View.OnClickListener()
            {
                                              @Override
                  public void onClick(View v) {
                      String tag = "req_endfav";
                      StringRequest strReq = new StringRequest(Request.Method.POST,
                              AppConfig.URL_REGISTER, new Response.Listener<String>() {

                          @Override
                          public void onResponse(String response) {
                              //Toast.makeText(getApplicationContext(),response, Toast.LENGTH_LONG).show();
                              try {

                                  JSONObject jObj = new JSONObject(response);
                                  boolean error = jObj.getBoolean("error");


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
                              params.put("tag", "endfav");
                              params.put("fid", fid);
                              params.put("status","Incomplete");
                              return params;
                          }

                      };
                      AppController.getInstance().addToRequestQueue(strReq, tag);
                      Toast.makeText(getApplicationContext(),"Task not finished! Go to main menu for updates.", Toast.LENGTH_LONG).show();
                  }

              }
             );

        }


        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_disp_ind_fav1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
