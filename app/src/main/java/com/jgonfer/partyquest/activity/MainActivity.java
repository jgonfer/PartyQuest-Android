package com.jgonfer.partyquest.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.jgonfer.partyquest.R;
import com.jgonfer.partyquest.fragment.GridFragment;
import com.jgonfer.partyquest.fragment.HelloFragment;
import com.jgonfer.partyquest.fragment.LoginFragment;
import com.jgonfer.partyquest.fragment.MainActivityFragment;

public class MainActivity extends AppCompatActivity implements LoginFragment.LoginFragmentListener, MainActivityFragment.MainFragmentListener {

    private String name ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        displayView(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
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

    private void displayView(int position) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);

        switch (position) {
            case 0:
                MainActivityFragment maf = MainActivityFragment.newInstance();
                maf.setListener(this);
                fragment = maf;
                break;
            case 1:
                LoginFragment lf = LoginFragment.newInstance();
                lf.setListener(this);
                fragment = lf;
                break;
            case 2:
                HelloFragment hf = HelloFragment.newInstance();
                hf.name = name;
                fragment = hf;
                break;
            case 3:
                GridFragment gf = GridFragment.newInstance();
                fragment = gf;
            default:
                break;
        }

        //currentFragment = fragment;
        if (fragment != null) {
            FragmentManager fm = getSupportFragmentManager();
            for(int i = 1; i < fm.getBackStackEntryCount(); ++i) {
                fm.popBackStack();
            }
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.replace(R.id.fragment, fragment, title);
            if (position > 0) {
                fragmentTransaction.addToBackStack(title);
            }
            fragmentTransaction.commit();

            if (getSupportActionBar() != null) getSupportActionBar().setTitle(title);
        }

    }

    @Override
    public void submitTry(boolean success) {

    }

    @Override
    public void submitName(String name) {
        this.name = name;
        displayView(2);
    }

    @Override
    public void assignmentSelected(int position) {
        displayView(position);
    }
}
