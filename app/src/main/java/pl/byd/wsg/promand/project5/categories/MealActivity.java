package pl.byd.wsg.promand.project5.categories;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import pl.byd.wsg.promand.project5.R;

import pl.byd.wsg.promand.project5.dashboards.DashboardGraphActivity;
import pl.byd.wsg.promand.project5.dashboards.DashboardListViewActivity;


public class MealActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meal_subcategory_activity);
        ActionBar actionBar = getActionBar();
        actionBar.setHomeButtonEnabled(true); //this required API level 14  MIGUEL
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId())
        {
            case android.R.id.home:
                Intent intent = new Intent(this, DashboardGraphActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
/*    public void openAdd_screen(View v){
        startActivity(new Intent(this, AddScreenActivity.class));
    }*/

    public void inputBreakfast(View v) {
        Button buttonBreakfast = (Button) findViewById(R.id.button_breakfast);
        String str = buttonBreakfast.getText().toString();

        Intent returnIntent = new Intent();
        returnIntent.putExtra("result",str);
        setResult(RESULT_OK, returnIntent);
        this.finish();
    }
    public void inputLunch(View v) {
        Button buttonLunch = (Button) findViewById(R.id.button_lunch);
        String str = buttonLunch.getText().toString();

        Intent returnIntent = new Intent();
        returnIntent.putExtra("result",str);
        setResult(RESULT_OK, returnIntent);
        this.finish();
    }
    public void inputDinner(View v) {
        Button buttonDinner = (Button) findViewById(R.id.button_dinner);
        String str = buttonDinner.getText().toString();

        Intent returnIntent = new Intent();
        returnIntent.putExtra("result",str);
        setResult(RESULT_OK, returnIntent);
        this.finish();
    }
    public void inputHB(View v) {
        Button buttonHB = (Button) findViewById(R.id.button_hb);
        String str = buttonHB.getText().toString();

        Intent returnIntent = new Intent();
        returnIntent.putExtra("result",str);
        setResult(RESULT_OK, returnIntent);
        this.finish();
    }
    public void inputFull(View v) {
        Button buttonFull = (Button) findViewById(R.id.button_all);
        String str = buttonFull.getText().toString();

        Intent returnIntent = new Intent();
        returnIntent.putExtra("result",str);
        setResult(RESULT_OK, returnIntent);
        this.finish();
    }
 }