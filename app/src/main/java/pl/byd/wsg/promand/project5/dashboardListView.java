package pl.byd.wsg.promand.project5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import pl.byd.wsg.promand.project5.categories.CategoriesActivity;

/**
 * Created by Miguel on 14-03-2014.
 */
public class dashboardListView extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_listview);
    }

    public void goCategories(View v){
        Intent intent = new Intent(this, CategoriesActivity.class);
        startActivity(intent);
    }

    public void goProjects(View v){
        Intent intent = new Intent(this, projectActivity.class);
        startActivity(intent);
    }

    public void goDashboard(View v){
        Intent intent = new Intent(this, dashboardGraphActivity.class);
        startActivity(intent);
    }
}