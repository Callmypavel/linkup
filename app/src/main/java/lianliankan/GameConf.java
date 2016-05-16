package lianliankan;

import android.content.Context;

public class GameConf {
		private int GameTime;
		private int Xsize;
		private int Ysize;
		private Context context;
		private int BeginX;
		private int BeginY;
		private int imageWidth;
		private int imageHeight;
		public static final int PIECE_WIDTH = 64;
		public static final int PIECE_HEIGHT = 64;
		public static int DEFAULT_TIME = 100;	
		public GameConf(int xSize, int ySize, int beginImageX,
				int beginImageY, long gameTime, Context context)
			{
				this.Xsize = xSize;
				this.Ysize = ySize;
				this.BeginX = beginImageX;
				this.BeginY = beginImageY;
				this.GameTime = (int) gameTime;
				this.context = context;
			}
		public GameConf(int GameTime,int Xsize,int Ysize){
			this.Xsize=Xsize;
			this.Ysize=Ysize;
			this.GameTime=GameTime;
		}
		public int getimageWidth(){
			return this.imageWidth;
		}
		public int getimageHeight(){
			return this.imageHeight;
		}
		public int getXsize(){
			return this.Xsize;
		}
		public int getYsize(){
			return this.Ysize;
		}
		public int getBeginX(){
			return this.BeginX;
		}
		public int getBeginY(){
			return this.BeginY;
		}
		public Context getContext(){
			return context;
		}
		public int getPiecesNumber(){
			return (this.Xsize-1)*(this.Ysize-1);
		}
		public int getGameTime(){
			return this.GameTime;
		}
		public void setXsize(int Xsize){
			this.Xsize=Xsize;
		}
		public void setYsize(int Ysize){
			this.Ysize=Ysize;
		}
		public void setGameTime(int GameTime){
			this.GameTime=GameTime;
		}
}
