package lianliankan;
import com.example.lianliankan.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class Welcome extends Activity {
	private int record;
	private Button start,range,set,out;
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
	    int flag=WindowManager.LayoutParams.FLAG_FULLSCREEN;
	    Window window=Welcome.this.getWindow();
	    window.setFlags(flag, flag);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.welcome);
		start = (Button)findViewById(R.id.startbutton);	
		 range = (Button)findViewById(R.id.rangebutton);
		 set = (Button)findViewById(R.id.setbutton);
		 out = (Button)findViewById(R.id.outbutton);
		 start.setOnClickListener(new View.OnClickListener() {
			 	public void onClick(View v) {
					Intent i = new Intent();				
					i.setClass(Welcome.this, Link.class);
					startActivityForResult(i, 0);	
				}
			});
		 range.setOnClickListener(new View.OnClickListener() {
			 	public void onClick(View v) {
					Intent i = new Intent();
					Bundle bundle = new Bundle();
					bundle.putInt("GameTime", record);
					i.putExtras(bundle);
					setResult(RESULT_OK);
					i.setClass(Welcome.this, Building.class);
					startActivity(i);
				}
			});
		 set.setOnClickListener(new View.OnClickListener() {
			 	public void onClick(View v) {
					Intent i = new Intent();
					i.setClass(Welcome.this, Building.class);
					startActivity(i);
				}
			});
		 
		 out.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					
					Welcome.this.finish();	
				}
			});
	}
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
			// TODO Auto-generated method stub
			super.onActivityResult(requestCode, resultCode, data);
			System.out.println("欢迎接受了");
			if(resultCode==RESULT_CANCELED)
				setTitle("cancel");
			else if(resultCode==RESULT_OK){
				Bundle bundle = data.getExtras();
				if(bundle!=null){
					record = bundle.getInt("GameTime");
				}
			}
			System.out.println("结果record为"+record);
		}
	

}
