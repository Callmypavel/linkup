package lianliankan;

import com.example.lianliankan.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class Building extends Activity {
	private TextView TV;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		int flag=WindowManager.LayoutParams.FLAG_FULLSCREEN;
		Window window=Building.this.getWindow();
	    window.setFlags(flag, flag);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.building);
		TV = (TextView)findViewById(R.id.tv1);
		TV.setText("�ù�����δ���ţ��Ⱥȱ��������ɣ�");
		TV.setTextSize(30);
	}
}
