package lianliankan;

import java.util.Timer;
import java.util.TimerTask;

import com.example.lianliankan.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Loading extends Activity {
	
	      protected void onCreate(Bundle savedInstanceState) {  
	          super.onCreate(savedInstanceState);  
	          setContentView(R.layout.loading);    
	          new Timer().schedule(new TimerTask() {  
	              public void run() {  
	                  startActivity(new Intent(Loading.this,Welcome.class));  
	                  finish();  
	                  System.out.println("����");
	                    
	              }  
	          }, 3000);//����ͣ��ʱ��Ϊ1000=1s��  
	      } 

	

}
