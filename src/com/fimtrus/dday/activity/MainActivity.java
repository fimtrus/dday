package com.fimtrus.dday.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.fimtrus.dday.R;
import com.fimtrus.dday.fragment.LeftSlidingFragment;
import com.fimtrus.dday.fragment.RightSlidingFragment;
import com.fimtrus.dday.fragment.TimerFragment;
import com.jhlibrary.slidingmenu.activity.SlidingBaseActivity;
import com.jhlibrary.util.Util;
import com.slidingmenu.lib.SlidingMenu;

public class MainActivity extends SlidingBaseActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		addLeftRightSlidingMenu(savedInstanceState, R.layout.fragment_left_sliding, 
				R.layout.fragment_right_sliding, R.id.fragment_left_sliding,
				R.id.fragment_right_sliding,
				new LeftSlidingFragment(), new RightSlidingFragment());
		getSupportFragmentManager()
		.beginTransaction()
		.replace(R.id.content_frame, ((Fragment) new TimerFragment()))
		.commit();
		getSupportActionBar().setTitle("");
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
       MenuInflater inflater = getSupportMenuInflater();
       inflater.inflate(R.menu.main, menu);
       return super.onCreateOptionsMenu(menu);
    }

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
//		switch (item.getItemId()) {
//		case R.id.actionbar_rightbutton :
//			getSlidingMenu().showSecondaryMenu();
//			break;
//		}
		return super.onOptionsItemSelected(item);
	}

}
