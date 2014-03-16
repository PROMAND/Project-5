package pl.byd.wsg.promand.project5.add;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import pl.byd.wsg.promand.project5.R;
import pl.byd.wsg.promand.project5.categories.CategoriesActivity;
import pl.byd.wsg.promand.project5.menus.MenuActivity;
import pl.byd.wsg.promand.project5.projects.ProjectActivity;

/**
 * Created by Sergey on 3/14/14.
 */

public class AddScreenActivityTest extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_screen_test);
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
                Intent intent = new Intent(this, MenuActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void openCategoryChooser(View v){
        Intent intent = new Intent(this, CategoriesActivity.class);
        startActivity(intent);
    }

    public void goProjects(View v){
        Intent intent = new Intent(this, ProjectActivity.class);
        startActivity(intent);
    }

    public void takePhoto(View v){
      /*  Intent intent = new Intent(this, Camera.class);
        startActivity(intent);  */
    }

    public void submitExpense(View v){
     /*   Intent intent = new Intent(this, AddExpense.class);
        startActivity(intent);    */
    }

}