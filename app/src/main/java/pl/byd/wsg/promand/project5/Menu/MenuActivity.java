package pl.byd.wsg.promand.project5.Menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import pl.byd.wsg.promand.project5.AddScreen.AddScreenActivity;
import pl.byd.wsg.promand.project5.Dashboard.DashboardGraphActivity;
import pl.byd.wsg.promand.project5.R;

/**
 * Created by Miguel on 14-03-2014.
 */
public class MenuActivity extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_screen);

    }

    public void goDashboard(View v){
        Intent intent = new Intent(this, DashboardGraphActivity.class);
        startActivity(intent);
    }

    public void goAddScreen(View v){
        Intent intent = new Intent(this, AddScreenActivity.class);
        startActivity(intent);
    }
}