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


public class OfferFav extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_fav);
        final EditText eta = (EditText) findViewById(R.id.eta);
        final EditText etd = (EditText) findViewById(R.id.etd);
        final EditText eda = (EditText) findViewById(R.id.eda);
        final EditText edd = (EditText) findViewById(R.id.edd);
        final Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                final Intent intent = getIntent();
                final String email= intent.getStringExtra("email");
                final String uname=intent.getStringExtra("uname");
                final String seta = eta.getText().toString();
                final String setd = etd.getText().toString();
                final String seda = eda.getText().toString();
                final String sedd = edd.getText().toString();
                if (seta.length() > 0 && setd.length() > 0 && seda.length() > 0 && sedd.length() > 0) {

                    String tag_string_req = "req_offerfav";
                    StringRequest strReq = new StringRequest(Request.Method.POST,
                            AppConfig.URL_REGISTER, new Response.Listener<String>() {

                        @Override
                        public void onResponse(String response) {
                            //Toast.makeText(getApplicationContext(),response, Toast.LENGTH_LONG).show();
                            try {
                                JSONObject jObj = new JSONObject(response);
                                boolean error = jObj.getBoolean("error");

                                // Check for error node in json

                                if (error) {
                                    // Error in login. Get the error message
                                    String errorMsg = jObj.getString("error_msg");
                                    Toast.makeText(getApplicationContext(),
                                            errorMsg, Toast.LENGTH_LONG).show();
                                } else {
                                    /*Toast.makeText(getApplicationContext(),
                                            "Status available! Return to main menu for updates.", Toast.LENGTH_LONG).show();*/

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
                    }) {

                        @Override
                        protected Map<String, String> getParams() {
                            // Posting parameters to login url
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("tag", "offerfav");
                            params.put("email",email);
                            params.put("uname",uname);
                            params.put("eta",seta+" "+seda);
                            params.put("etd",setd+" "+sedd);
                            return params;
                        }

                    };

                    AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
                    Toast.makeText(getApplicationContext(),
                                            "Status available! Return to main menu for updates. Login again to change status back to busy.", Toast.LENGTH_LONG).show();

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
        getMenuInflater().inflate(R.menu.menu_offer_fav, menu);
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
