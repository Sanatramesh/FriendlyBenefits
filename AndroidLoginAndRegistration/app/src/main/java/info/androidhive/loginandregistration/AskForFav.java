package info.androidhive.loginandregistration;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import info.androidhive.loginandregistration.app.AppConfig;
import info.androidhive.loginandregistration.app.AppController;


public class AskForFav extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_for_fav);

        Intent intent=getIntent();
        final String semail1=intent.getStringExtra("email1");
        final String semail2=intent.getStringExtra("email2");
        final String suname1=intent.getStringExtra("uname1");
        final String suname2=intent.getStringExtra("uname2");
        final String status=intent.getStringExtra("status");
        final EditText desc=(EditText) findViewById(R.id.editText);
        final EditText dest=(EditText) findViewById(R.id.editText2);
        final EditText amount=(EditText) findViewById(R.id.editText3);
        final EditText weight=(EditText) findViewById(R.id.editText4);
        final EditText dist=(EditText) findViewById(R.id.editText6);
        final EditText deadline=(EditText) findViewById(R.id.editText5);
        final EditText deadline_date=(EditText) findViewById(R.id.editText7);
        final Button createFav=(Button) findViewById(R.id.button);

        createFav.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                final String sdesc = desc.getText().toString();
                final String sdest = dest.getText().toString();
                final String samount = amount.getText().toString();
                final String sweight = weight.getText().toString();
                final String sdist = dist.getText().toString();
                final String sdeadline = deadline.getText().toString();
                final String sdeadline_date = deadline_date.getText().toString();
                if (sdesc.length() > 0 && sdest.length() > 0 && samount.length() > 0 && sweight.length() > 0 && sdist.length() > 0
                        && sdeadline.length()>0 && sdeadline_date.length()>0) {

                    String tag_string_req = "req_addfav";
                    StringRequest strReq = new StringRequest(Request.Method.POST,
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
                                    /*Toast.makeText(getApplicationContext(),
                                            "Favour request sent! Go to main menu for updates.", Toast.LENGTH_LONG).show();*/

                                    //String fid=jObj.getString("fid");
                                    //System.out.println(jObj);
                                    //listAdapter.add(fid);

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
                            params.put("tag", "addfav");
                            params.put("desc", sdesc);
                            params.put("dest", sdest);
                            params.put("amount", samount);
                            params.put("weight", sweight);
                            params.put("dist", sdist);
                            params.put("deadline", sdeadline+" "+sdeadline_date);
                            params.put("email1", semail1);
                            params.put("email2", semail2);
                            params.put("uname1", suname1);
                            params.put("uname2", suname2);
                            //params.put()
                            return params;
                        }

                    };

                    AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
                    Toast.makeText(getApplicationContext(),
                            "Favour request sent! Go to main menu for updates.", Toast.LENGTH_LONG).show();

                } else {
                    // Prompt user to enter credentials
                    Toast.makeText(getApplicationContext(),
                            "Please enter the credentials!", Toast.LENGTH_LONG)
                            .show();
                }

            }
        });
    }


            @Override
            public boolean onCreateOptionsMenu(Menu menu) {
                // Inflate the menu; this adds items to the action bar if it is present.
                getMenuInflater().inflate(R.menu.menu_ask_for_fav, menu);
                return true;
            }
        }
