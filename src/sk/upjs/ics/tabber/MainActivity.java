package sk.upjs.ics.tabber;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBar.TabListener;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;

public class MainActivity extends ActionBarActivity implements TabListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Notice that setContentView() is not used, because we use the root
		// android.R.id.content as the container for each fragment

		// setup action bar for tabs
		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayShowTitleEnabled(false);

		Tab tab = actionBar
				.newTab()
				.setText("Random quote")
				.setTabListener(this)
				.setTag(QuoteFragment.class.getName());
		actionBar.addTab(tab);

		tab = actionBar
				.newTab()
				.setText("Quotes")
				.setTabListener(this)
				.setTag(QuoteListFragment.class.getName());
		actionBar.addTab(tab);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		String tag = (String) tab.getTag();
		String fragmentClassName = tag;
    	/*
			try to load an existing fragment that may have been preserved
			automatically due to configuration change
    	 */			
		Fragment selectedFragment = getSupportFragmentManager().findFragmentByTag(tag);
	    if (selectedFragment == null) {
	    	selectedFragment = Fragment.instantiate(this, fragmentClassName);
	        ft.add(android.R.id.content, selectedFragment, tag);
	    } else {
	        ft.attach(selectedFragment);
	    }		
	}
	

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		String tag = (String) tab.getTag();
		Fragment selectedFragment = getSupportFragmentManager().findFragmentByTag(tag);
		if (selectedFragment != null) {
            ft.detach(selectedFragment);
        }
	}
	
	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
        // User selected the already selected tab. Usually do nothing.
	}

}
