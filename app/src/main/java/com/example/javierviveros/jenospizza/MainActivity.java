package com.example.javierviveros.jenospizza;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {

    static private DataBaseManager manager;

    static public DataBaseManager getManager(){    return manager;    }

    public static void setManager(DataBaseManager manager) {
        MainActivity.manager = manager;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        DbHelper helper = new DbHelper(this);
        manager = new DataBaseManager(this);

        manager.insertar("Bello","6.345429", "-75.525130");
        manager.insertar("44","6.277009", "-75.565018");
        manager.insertar("Local 4212", "6.201249", "-75.571729");

        SQLiteDatabase db = helper.getWritableDatabase();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_inicio) {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }

        if (id == R.id.action_control) {
            Control fragment = new Control();
            fragmentTransaction.replace(android.R.id.content, fragment).commit();
        }

        if (id == R.id.action_mapa) {
            Intent i = new Intent(this, MapsActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }
}