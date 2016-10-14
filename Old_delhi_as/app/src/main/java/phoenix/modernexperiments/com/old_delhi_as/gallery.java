package phoenix.modernexperiments.com.old_delhi_as;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class gallery extends Fragment{
	
	View v1;
	ImageView wrapper;
	Button prev, next;
	TextView  desc;
	LinearLayout l1;
	Boolean reverse;
	TranslateAnimation go_out, enter, go_out_to_left, enter_from_right;
	
	int width = 450 ; //find a better way
	int imageResources[] = { R.drawable.chandnichowk1, R.drawable.redfort, R.drawable.qutub };
	int number_images = 3, iter_images = 0;
	String imageDescription[] = {"Busy Streets of Chandni Chowk", "The Red Fort", "Qutub Minar"};
	
	public View onCreateView(LayoutInflater inflator, ViewGroup container, Bundle savedInstanceState){
		
		v1 = inflator.inflate(R.layout.gallery, container, false);
		
		wrapper = (ImageView)v1.findViewById(R.id.gallery1);
		l1 = (LinearLayout)v1.findViewById(R.id.gallery_container);
		
		prev =(Button)v1.findViewById(R.id.previous);
		prev.setOnClickListener(clickListener);
		next =(Button)v1.findViewById(R.id.next);
		next.setOnClickListener(clickListener);
		desc = (TextView)v1.findViewById(R.id.textView1);
		
		go_out = new TranslateAnimation(0, width, 0, 0);
		go_out.setDuration(400);
		
		enter = new TranslateAnimation(-(width), 0, 0, 0);
		enter.setDuration(400);
		
		go_out_to_left = new TranslateAnimation(0, -(width), 0, 0);
		go_out_to_left.setDuration(400);
		
		enter_from_right = new TranslateAnimation(width, 0, 0, 0);
		enter_from_right.setDuration(400);
		
		go_out.setAnimationListener(new AnimationListener(){

			@Override
			public void onAnimationEnd(Animation animation) {
				
				wrapper.setImageResource(imageResources[iter_images]);
				wrapper.startAnimation(enter);
				desc.setText(imageDescription[iter_images]);
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationStart(Animation animation) {
			}
			
		});

		go_out_to_left.setAnimationListener(new AnimationListener(){

			@Override
			public void onAnimationEnd(Animation animation) {
				wrapper.setImageResource(imageResources[iter_images]);
				wrapper.startAnimation(enter_from_right);
				desc.setText(imageDescription[iter_images]);
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onAnimationStart(Animation animation) {
				
			}
			
		});
		
		return v1;
	}
	
	View.OnClickListener clickListener = new View.OnClickListener(){
		@Override
		public void onClick(View v){
			if (v == next){
				iter_images = (iter_images+1)%number_images; 
				wrapper.startAnimation(go_out);

			}
			else if( v == prev){
				iter_images = (iter_images+number_images-1)%number_images; 
				wrapper.startAnimation(go_out_to_left);
			}
			}
	};
}

