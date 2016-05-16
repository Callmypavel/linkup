package lianliankan;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.example.lianliankan.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class Link extends Activity{
private GameConf config;
private TextView TV;
private Button pause,refresh;
private int GameTime;
private Timer timer = new Timer(true);
private Piece[][] pieces;
private GameView view;
private GameService gameservice;
private Piece isSelected;
private AlertDialog.Builder builder;
private Handler handler = new Handler(){
	public void handleMessage(Message msg) {
		switch(msg.what){
		case 0x123:
			GameTime--;
			TV.setText("ʣ��ʱ�� "+GameTime);
			if(GameTime==0){
				timer.cancel();
				timer = null;
				gameFail();
				return;
			}
			break;
		}
	};
};
	public void onCreate(Bundle savedInstanceState){
		
		super.onCreate(savedInstanceState);
	    int flag=WindowManager.LayoutParams.FLAG_FULLSCREEN;
	    Window window=Link.this.getWindow();
	    window.setFlags(flag, flag);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.link);	
		init();
	}
	public void onBackPressed() { 
//        super.onBackPressed();   
		System.out.println("��������");
		//if(timer!=null){
		//timer.cancel();
		//timer=null;
		//}
		builder= new Builder(this);
		builder.setCancelable(false);
		builder.setIcon(R.drawable.o23);
		builder.setTitle("退出！");
		builder.setMessage("确定要退出吗？");
		builder.setPositiveButton("嗯，退出", new DialogInterface.OnClickListener() {
		
			@Override
			public void onClick(DialogInterface dialog, int which) {
				if(timer!=null){
					timer.cancel();
					timer=null;
					}
				dialog.dismiss();
				finish();
			}
		});
		builder.setNegativeButton("别急我再玩会儿", new DialogInterface.OnClickListener() {
			
		@Override
		public void onClick(DialogInterface arg0, int arg1) {
			arg0.dismiss();
			//timer=new Timer();
			//timer.schedule(new TimerTask(){ public void run(){
			//	handler.sendEmptyMessage(0x123);
		   //	}
		  //},0,1000);
		}		
	});
		builder.create().show();
    } 
	private void init()
	{
		config = new GameConf(8, 9, 90, 100 , 100000, this);
		pause = (Button)findViewById(R.id.pausebutton); 
		refresh = (Button)findViewById(R.id.refreshbutton);
		view = (GameView)findViewById(R.id.gameView);
		view.postInvalidate();
		view.invalidate();
		TV = (TextView)findViewById(R.id.tv);
		GameTime=GameConf.DEFAULT_TIME;
		TV.setText("剩余时间"+GameTime);
		gameservice = new GameService(this.config);
		view.setGameService(gameservice);
		startGame(GameConf.DEFAULT_TIME);
			this.view.setOnTouchListener(new View.OnTouchListener()
			{
				public boolean onTouch(View view, MotionEvent e)
				{
					if (e.getAction() == MotionEvent.ACTION_DOWN)
					{
						TouchDown(e);
					}
					if (e.getAction() == MotionEvent.ACTION_UP)
					{
						TouchUp(e);
					}
					return true;
				}
				});
			pause.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					gamePause();
				}
			});
			refresh.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					gameRefresh();
				}
			});
	} 

	public Piece[][] getPieces(){
		return this.pieces;
	}

	public void startGame(int gametime){
		this.GameTime=gametime;
		if(timer!=null){
			timer.cancel();
			this.timer=new Timer(true);
		}	
		if(GameTime == GameConf.DEFAULT_TIME){
				view.startGame();
		}
			timer = new Timer(true);
			this.timer.schedule(new TimerTask(){ public void run(){
			handler.sendEmptyMessage(0x123);
	    	}
	  },0,1000);	
			view.setSelectedPiece(null);
	
	}
	

	public void TouchDown(MotionEvent event){
		float x = event.getX();
		float y = event.getY();
		pieces = this.gameservice.getPieces();
		Piece piece = this.gameservice.findPiece(x, y);
		if(piece != null){
		view.setSelectedPiece(piece);
		if(isSelected==null){
			isSelected=piece;
			view.postInvalidate();
			return;
		}
		if(isSelected!=null){
			LinkInfo LI = this.gameservice.Judge(isSelected, piece);
			if(LI!=null){
				view.setLinkInfo(LI);
				view.setSelectedPiece(null);
				pieces[piece.getindexX()][piece.getindexY()]=null;
				pieces[isSelected.getindexX()][isSelected.getindexY()]=null;
				isSelected=null;
				piece=null;
				view.postInvalidate();
				if(!this.gameservice.hasPieces()){
					gameSuccess();
				}
			}else if(LI==null){
				isSelected = piece;
				view.postInvalidate();
			}
		}
	}
}
	public void TouchUp(MotionEvent e){
		view.postInvalidate();
	}
	
	public void gameFail(){
		if(timer!=null){
			timer.cancel();
			timer=null;
			}
		builder= new Builder(this);
		builder.setCancelable(false);
		builder.setIcon(R.drawable.o25);
		builder.setTitle("ʧ�������������");
		builder.setMessage("���¿�ʼ��");
		builder.setPositiveButton("�ð�", new DialogInterface.OnClickListener() {
		
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				init();
			}
		});
		builder.setNegativeButton("����", new DialogInterface.OnClickListener() {
				
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				arg0.dismiss();
				finish();
			}
		
				
		});
		builder.create().show();
		}
	
	public void gameSuccess(){
		if(timer!=null){
			timer.cancel();
			timer=null;
			}
		builder= new Builder(this);
		builder.setCancelable(false);
		builder.setIcon(R.drawable.o28);
		builder.setTitle("��Ϸʤ����");
		builder.setMessage("���¿�ʼ��");
		builder.setPositiveButton("�϶��ۣ�", new DialogInterface.OnClickListener() {
		
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				startGame(GameConf.DEFAULT_TIME);
			}
		});
		builder.setNegativeButton("����", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				Intent intent = getIntent();
				Bundle bundle = new Bundle();
				bundle.putInt("GameTime",GameTime);
				intent.putExtras(bundle);
				setResult(RESULT_OK,intent);
				System.out.println("result");
				arg0.dismiss();
				finish();
			}
		
				
		});
			builder.create().show();
		}
	
	private void gamePause(){
		if(timer!=null){
		timer.cancel();
		timer=null;
		}
		builder= new Builder(this);
		builder.setCancelable(false);
		builder.setIcon(R.drawable.tea);
		builder.setTitle("��ͣ��");
		builder.setMessage("�Ⱥ�һ����������");
		builder.setPositiveButton("������", new DialogInterface.OnClickListener() {
		
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				timer=new Timer(true);
				timer.schedule(new TimerTask(){ public void run(){
					handler.sendEmptyMessage(0x123);
			    	}
			  },0,1000);
			}
		});
			builder.create().show();
		}
	
	public void gameRefresh(){
		List<Piece> notNullPieces = new ArrayList<Piece>();
		pieces =this.gameservice.getPieces();
		for(int i=0;i<pieces.length;i++)
			for(int j=0;j<pieces[i].length;j++)
				if(pieces[i][j]!=null){
					Piece piece = new Piece(i,j);
					piece.setPieceImage(pieces[i][j].getImage());
					notNullPieces.add(piece);			
				}
		
		Collections.shuffle(notNullPieces);
		int k =0;
			for(int i=0;i<pieces.length;i++){
				for(int j=0;j<pieces[0].length;j++)
					if(pieces[i][j]!=null){	
						k+=1;
						pieces[i][j].setPieceImage(notNullPieces.get(k-1).getImage());			
					}
		view.postInvalidate();
			}
	}

	
	

	public void onClick(View v) {
		switch(v.getId()){
		case R.id.pausebutton:
			gamePause();
			break;
		case R.id.refreshbutton:
			gameRefresh();
			break;
		}
	}
		};

	