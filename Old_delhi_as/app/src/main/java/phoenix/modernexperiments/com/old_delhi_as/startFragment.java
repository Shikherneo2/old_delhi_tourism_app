package phoenix.modernexperiments.com.old_delhi_as;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class startFragment extends Fragment{

	TextView bt1, bt2, bt3, bt4, btMap, galleryButton;
	View previous;
	
	OnSelectedListener lselect;
	
	public interface OnSelectedListener{
		void onSelection(int buttonNumber);
	}
	
	@Override
	public void onAttach(Activity activity){
		super.onAttach(activity);
		
		try{
			lselect = (OnSelectedListener)activity;
		}catch(ClassCastException e){
			throw new ClassCastException(activity.toString() + " Must implement OnSelectedListener");
		}
		
	}
	
	public View onCreateView(LayoutInflater inflator, ViewGroup container, Bundle savedInstanceState){
		View v1 = inflator.inflate(R.layout.start_fragment, container, false);
		
		previous = null;
		bt1 = (TextView)v1.findViewById(R.id.textView1);
		bt2 = (TextView)v1.findViewById(R.id.textView2);
		bt3 = (TextView)v1.findViewById(R.id.textView3);
		bt4 = (TextView)v1.findViewById(R.id.textView4);
		btMap = (TextView)v1.findViewById(R.id.textView8);
		galleryButton = (TextView)v1.findViewById(R.id.textView9); 

		bt1.setOnClickListener(clickListener);
		bt2.setOnClickListener(clickListener);
		bt3.setOnClickListener(clickListener);
		bt4.setOnClickListener(clickListener);
		galleryButton.setOnClickListener(clickListener);
		btMap.setOnClickListener(clickListener);

		return v1;
	}
	
	View.OnClickListener clickListener = new View.OnClickListener(){
		@Override
		public void onClick(View v){
			
			if(previous != v && previous != null)
				previous.setBackgroundResource(R.drawable.box_border);
				
			v.setBackgroundResource(R.drawable.box_border2);
			previous = v;
			
			if(v == bt1)
				lselect.onSelection(1);

			else if(v == bt3)
				lselect.onSelection(3);

			else if( v == bt2 )
				lselect.onSelection(2);

			else if( v == bt4)
				lselect.onSelection(4);
			
			else if( v == btMap)
				lselect.onSelection(8);
			
			else if(v == galleryButton)
				lselect.onSelection(9);
		}
	};
		
}