package lianliankan;

import android.graphics.Bitmap;

public class PieceImage {
	private int ImageID;
	private Bitmap bm;
	public PieceImage(int ImageID,Bitmap bm){
		this.ImageID=ImageID;
		this.bm=bm;
		
	}
	public void setImageID(int ImageID){
		this.ImageID=ImageID;
	}
	public void setBitmap(Bitmap bm){
		this.bm=bm;
	}
	public int getImageID(){
		return this.ImageID;
	}
	public Bitmap getBitmap(){
		return this.bm;
	}

}
