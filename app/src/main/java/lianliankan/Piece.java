package lianliankan;

import android.graphics.Point;

public class Piece {
	private PieceImage PI;
	private int indexX;
	private int indexY;
	private	int Beginx;
	private int Beginy;
	public boolean isSameImage(Piece p){
		if (PI == null)
		{
			if (p.PI != null)
				return false;
		}
		return PI.getImageID() == p.PI.getImageID();
	}
	
	public Piece(int indexX,int indexY){
		this.indexX=indexX;
		this.indexY=indexY;
	}
	
	public void setindexX(int indexX){
		this.indexX=indexX;
	}
	public int getindexX(){
		return this.indexX;
	}
	public void setindexY(int indexY){
		this.indexY=indexY;
	}
	public int getindexY(){
		return this.indexY;
	}
	public PieceImage getImage(){
		return this.PI;
	}
	public void setBeginx(int x){
		this.Beginx=x;
	}
	public int getBeginx(){
		return this.Beginx;
	}
	public void setBeginy(int y){
		this.Beginy=y;
	}
	public int getBeginy(){
		return this.Beginy;
	}
	public void setPieceImage(PieceImage PI){
		this.PI=PI;
	}
	public Point getCentral(){
		Point point = new Point(this.getCentralX(),this.getCentralY());
		return point;
	}
	public int getCentralX(){
		return getBeginx()+getImage().getBitmap().getWidth()/2;
	}
	public int getCentralY(){
		return getBeginy()+getImage().getBitmap().getHeight()/2;
	}


	
	
}
