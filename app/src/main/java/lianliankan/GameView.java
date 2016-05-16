package lianliankan;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;
public class GameView extends View {
	private Paint paint;
	private GameService gs;
	private Bitmap bm;
	private LinkInfo li; 
	private Piece selectedPiece;
	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.paint=new Paint();
		this.paint.setColor(Color.RED);
		this.paint.setStrokeWidth(10);
		this.bm=ImageUtil.getSelectedImage(context);
	}
	public GameService getGameService(){
		return this.gs;
	}

	public void setGameService(GameService GS){
		this.gs=GS;
	}
	
	public void setSelectedPiece(Piece selectedPiece){
		this.selectedPiece=selectedPiece;
	}
	
	public void setLinkInfo(LinkInfo li){
		this.li=li;
	}
	public void startGame(){
		this.gs.start();
		this.postInvalidate();
	}
		@Override
	 protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
			// TODO Auto-generated method stub
			super.onMeasure(widthMeasureSpec, heightMeasureSpec);
			 int width = 1080;  
			 int height= 1200;  
			  setMeasuredDimension(width, height);  
		}
	
	 protected void onDraw(Canvas canvas){
		 super.onDraw(canvas);
		 if(gs==null)
			 return;
		 if(gs!=null){
			 Piece[][] pieces=gs.getPieces();
			 for(int i=0;i<pieces.length;i++){
				 for(int j=0;j<pieces[i].length;j++){
					 if(pieces[i][j]!=null)
						 canvas.drawBitmap(pieces[i][j].getImage().getBitmap(), pieces[i][j].getBeginx(),pieces[i][j].getBeginy(), null);				 
				 }
			 }
		if(li!=null){
	 		drawLine(this.li,canvas);
			this.li=null;
		}	 
		if(selectedPiece!=null){
			canvas.drawBitmap(this.bm, this.selectedPiece.getBeginx(), this.selectedPiece.getBeginy(), null);
		}
	 }
		 
 }
	 private void drawLine(LinkInfo li,Canvas canvas){
		 List<Point> points=li.getLinkPoints();
		 for(int i=0;i<points.size()-1;i++){
			 Point current=points.get(i);
			 Point next=points.get(i+1);
			 canvas.drawLine(current.x,current.y, next.x, next.y, this.paint);
		 }
		 
	 }

}
