package com.example.AlcoCalculator;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;

public class MainActivity extends SherlockFragmentActivity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


        ActionBar bar = getSupportActionBar();
        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setHomeButtonEnabled(true);
        bar.setDisplayShowTitleEnabled(true);
        bar.setTitle("ABS");
        //bar.setLogo(R.drawable.surflogo);

        ActionBar.Tab tab1 = bar.newTab();
        ActionBar.Tab tab2 = bar.newTab();
        tab1.setText("Drinks List");
        tab2.setText("Statistics");
        tab1.setTabListener(new MyTabListener());
        tab2.setTabListener(new MyTabListener());
        bar.addTab(tab1);
        bar.addTab(tab2);


    }

    private class MyTabListener implements ActionBar.TabListener {
        public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
            if (tab.getPosition() == 0) {
                // FragmentA frag = new FragmentA();
                // ft.replace(android.R.id.content, frag);
                Toast.makeText(getBaseContext(),
                        String.valueOf("Drinks List tab selected"), Toast.LENGTH_LONG).show();
            } else {
                // FragmentB frag = new FragmentB();
                // ft.replace(android.R.id.content, frag);
                Toast.makeText(getBaseContext(),
                        String.valueOf("Statistics tab selected"), Toast.LENGTH_LONG).show();
            }
        }

        public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
// TODO Auto-generated method stub
        }

        public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
// TODO Auto-generated method stub
        }
    }
}
