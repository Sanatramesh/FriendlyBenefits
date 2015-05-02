package info.androidhive.loginandregistration;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class DispIndFav extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disp_ind_fav);
        Intent intent=getIntent();
        String favour=intent.getStringExtra("favour");
        TextView tv = (TextView) findViewById(R.id.rowTextView);
        tv.setText(favour);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_disp_ind_fav, menu);
        return true;
    }

}
