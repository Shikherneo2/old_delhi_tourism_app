package phoenix.modernexperiments.com.old_delhi_as;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import phoenix.modernexperiments.com.old_delhi_as.startFragment.OnSelectedListener;

public class MainActivity extends FragmentActivity implements OnSelectedListener {
	
	int DEVICE_CLASS = 1; // 0 for small, 1 for normal, 2 for large and 3 for extra large
	
	Boolean welcome_removed = false;
	TextView welcome_text_view1, welcome_text_view2;
	Fragment next;
	FragmentManager fm;
	FragmentTransaction ft;
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		MenuInflater inflater = getMenuInflater(); 
		inflater.inflate(R.menu.main, menu);
		
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch(item.getItemId()){
			case R.id.rate_app:
				Toast.makeText(getApplicationContext(), "5 stars", Toast.LENGTH_SHORT).show();
				return true;
			case R.id.about_us:
				Toast.makeText(getApplicationContext(), "We are good", Toast.LENGTH_SHORT).show();
				return true;
			case R.id.exit:
				System.exit(0);
			default:
				return super.onOptionsItemSelected(item);
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		if( (getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE){
			DEVICE_CLASS = 2;
		}
		else if( (getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_XLARGE ){
			DEVICE_CLASS = 3;
		}
		
		if (DEVICE_CLASS == 2 || DEVICE_CLASS ==3){
			welcome_text_view1  = (TextView)findViewById(R.id.welcome_text1);
			welcome_text_view2  = (TextView)findViewById(R.id.welcome_text2);
		}
		
		fm = getFragmentManager();
		ft = fm.beginTransaction();
		startFragment st = new startFragment();
		ft.add(R.id.list, st);
		ft.commit();
	}

	    @Override
	    protected void onResume() {
	        super.onResume();
	    }

		@Override
		public void onSelection(int buttonNumber) {
			switch(buttonNumber){
				case 1: next = new History();
						break;
				case 2: next = new getting_there();
						break;		
				case 3: next = new places_to_go();
						break;
				case 4: next = new foods();
						break;		
				case 8: next = new Map();
						break;
				case 9: next = new gallery();
						break;		
			}
			
			if(!welcome_removed && (DEVICE_CLASS == 2 || DEVICE_CLASS == 3)){
				welcome_removed = true;
				welcome_text_view1.setVisibility(View.GONE);
				welcome_text_view2.setVisibility(View.GONE);
			}	
			
			fm = getFragmentManager();
			ft = fm.beginTransaction();
			
			if(DEVICE_CLASS == 2 || DEVICE_CLASS == 3){
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				ft.replace(R.id.section_right, next);
			}
			else{
				ft.replace(R.id.list, next);
				ft.addToBackStack(null);
			}
			ft.commit();
		}
}
