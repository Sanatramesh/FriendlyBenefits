package info.androidhive.loginandregistration;

import info.androidhive.loginandregistration.helper.SQLiteHandler;
import info.androidhive.loginandregistration.helper.SessionManager;

import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    private TextView txtName;
    private TextView txtEmail;
    private Button btnLogout, offer,owed,owing,givers, proceed;

    private SQLiteHandler db;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtName = (TextView) findViewById(R.id.name);
        txtEmail = (TextView) findViewById(R.id.email);
        btnLogout = (Button) findViewById(R.id.btnLogout);
        //proceed = (Button) findViewById(R.id.proceed);
        offer=(Button) findViewById(R.id.offer);
        owing=(Button) findViewById(R.id.owing);
        owed=(Button) findViewById(R.id.owed);
        givers=(Button) findViewById(R.id.givers);
        // SqLite database handler
        db = new SQLiteHandler(getApplicationContext());

        // session manager
        session = new SessionManager(getApplicationContext());

        if (!session.isLoggedIn()) {
            logoutUser();
        }

        final Intent intent = getIntent();
        final String email= intent.getStringExtra("email");
        final String uname=intent.getStringExtra("username");
        final String status=intent.getStringExtra("status");
        // Fetching user details from sqlite
        HashMap<String, String> user = db.getUserDetails();

        String name = user.get("name");
        //String email = user.get("email");

        // Displaying the user details on the screen
        txtName.setText(uname);
        txtEmail.setText(email);

        // Logout button click event
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });
        owed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,
                        DisplayFav.class);
                //intent.putExtra("email",email);
                intent.putExtra("email", email);
                startActivity(intent);
                //finish();
            }
        });
        owing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,DisplayFavOwing.class);
                intent.putExtra("email",email);
                startActivity(intent);
            }
        });
        offer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(status.equalsIgnoreCase("Busy")) {
                    Intent intent = new Intent(MainActivity.this, OfferFav.class);
                    intent.putExtra("email", email);
                    intent.putExtra("uname", uname);
                    startActivity(intent);
                }
                else
                {
                    Intent intent = new Intent(MainActivity.this, StopFav.class);
                    intent.putExtra("email", email);
                    startActivity(intent);
                }
            }
        });
        givers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,DisplayGivers.class);
                intent.putExtra("email",email);
                intent.putExtra("uname",uname);
                intent.putExtra("status",status);
                startActivity(intent);
            }
        });
    }

    /**
     * Logging out the user. Will set isLoggedIn flag to false in shared
     * preferences Clears the user data from sqlite users table
     * */
    private void logoutUser() {
        session.setLogin(false);

        db.deleteUsers();

        // Launching the login activity
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
