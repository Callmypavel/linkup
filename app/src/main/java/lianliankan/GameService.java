package lianliankan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.graphics.Point;




public class GameService {
	private GameConf config ;
	private Piece[][] pieces;
	private LinkInfo LI;
	public GameConf getConfig(){
		return this.config;
	}
	public GameService(){
	}
	public GameService(GameConf config){
		this.config=config;
	}
	public void start(){
		GameBoard board = new GameBoard();
		pieces = board.create(this.config);
	}
	public Piece[][] getPieces(){
		return this.pieces;
	}
	public void setPieces(Piece[][] pieces){
		this.pieces=pieces;
	}
	public LinkInfo Judge(Piece p1,Piece p2){
	Point turns1 = new Point();
	Point turns2 = new Point();
	HashMap<Point,Point> twoPointmap = new HashMap<Point,Point>();
	Point onepoint = null;
	LI=null;
	if(p1.isSameImage(p2)){
		if(p1!=p2){
			Point point1 = p1.getCentral();
			Point point2 = p2.getCentral();
		//同列
		if(point1.x==point2.x){
			if(isBlockY(point1,point2)){
				twoPointmap = twoPoints(point1,point2);
				if(twoPointmap!=null){
				for(Point point3 : twoPointmap.keySet()){
					turns1 = point3;
					turns2 = twoPointmap.get(point3);
				}if(turns1.x==point1.x||turns1.y==point1.y){
				LI = new LinkInfo(point1,turns1,turns2,point2);
				}
				 if(turns1.x==point2.x||turns1.y==point2.y){
				LI = new LinkInfo(point1,turns2,turns1,point2);
				}
				
				}if(twoPointmap==null)
					LI = null;
				 return LI;
			}
			else if(!isBlockY(point1,point2))
				LI = new LinkInfo(p1.getCentral(),p2.getCentral());
			}
		//同行
		if(point1.y==point2.y){
			if(isBlockX(point1,point2)){
				twoPointmap = twoPoints(point1,point2);
				if(twoPointmap!=null){
				for(Point point3 : twoPointmap.keySet()){
					turns1 = point3;
					turns2 = twoPointmap.get(point3);
				}if(turns1.x==point1.x||turns1.y==point1.y){
				LI = new LinkInfo(point1,turns1,turns2,point2);
				}
				 if(turns1.x==point2.x||turns1.y==point2.y){
				LI = new LinkInfo(point1,turns2,turns1,point2);
				}
				}if(twoPointmap==null)
					LI = null;
				return LI;
			}
			else if(!isBlockX(point1,point2)){
				LI = new LinkInfo(p1.getCentral(),p2.getCentral());
			}
		}
		if((point1.x!=point2.x)&&(point1.y!=point2.y)){
			if(point1.x>point2.x){
				onepoint=onepoint(point2,point1);
			}
			if(point1.x<point2.x){
				onepoint=onepoint(point1,point2);
			}
			if(onepoint==null){
			if(point1.x<point2.x){
				twoPointmap = twoPoints(point1,point2);
			}
			if(point1.x>point2.x){
				twoPointmap = twoPoints(point2,point1);
			}
			if(twoPointmap!=null){
			for(Point point3 : twoPointmap.keySet()){
				turns1 = point3;
				turns2 = twoPointmap.get(point3);
			if(turns1.x==point1.x||turns1.y==point1.y){
				LI = new LinkInfo(point1,turns1,turns2,point2);
				}
			if(turns1.x==point2.x||turns1.y==point2.y){
				LI = new LinkInfo(point1,turns2,turns1,point2);
				}
			return LI;
			}
		}if(twoPointmap==null){
			LI = null;
			return LI;
		}
		}if(onepoint!=null){
			LI = new LinkInfo(point1,onepoint,point2);
			return LI;
		}
	}
	}else if(p1==p2){
			return LI;
		}
		
	}return LI;
}

	public List<Point> getUpChannel(Point p,int min){
		List<Point> points = new ArrayList<Point>();
		for(int i = p.y-2*GameConf.PIECE_HEIGHT;i >= min;i -=2*GameConf.PIECE_HEIGHT){
			if(hasPiece(p.x,i)){
				return points;
			}
			points.add(new Point(p.x,i));
		}
		return points;
	}
		
	public List<Point> getDownChannel(Point p,int max){
		List<Point> points = new ArrayList<Point>();
		for(int i = p.y+2*GameConf.PIECE_HEIGHT;i <= max;i +=2*GameConf.PIECE_HEIGHT){
			if(hasPiece(p.x,i)){
				return points;
			}
			points.add(new Point(p.x,i));
		}
		return points;
	}
	public List<Point> getLeftChannel(Point p,int min){
		List<Point> points = new ArrayList<Point>();
		for(int i = p.x-2*GameConf.PIECE_WIDTH;i >= min;i -=2*GameConf.PIECE_WIDTH){
			if(hasPiece(i,p.y)){
				return points;
			}
			points.add(new Point(i,p.y));
		}
		return points;
	}	
	public List<Point> getRightChannel(Point p,int max){
		List<Point> points = new ArrayList<Point>();
		for(int i = p.x+2*GameConf.PIECE_WIDTH;i <= max;i +=2*GameConf.PIECE_WIDTH){
			if(hasPiece(i,p.y)){
				return points;
			}
			points.add(new Point(i,p.y));
		}
		return points;
	}
	public boolean isBlockX(Point p1,Point p2){
		if (p2.x < p1.x)
		{	
			return isBlockX(p2, p1);
		}
		for(int i =p1.x+2*GameConf.PIECE_WIDTH;i < p2.x;i+=2*GameConf.PIECE_WIDTH){
			if(hasPiece(i,p1.y)){
				return true;
			}
		}
		return false;
	}
	public boolean isBlockY(Point p1,Point p2){
		if (p2.y < p1.y)
		{
			return isBlockY(p2, p1);
		}
		for(int i =p1.y+2*GameConf.PIECE_HEIGHT;i < p2.y;i+=2*GameConf.PIECE_HEIGHT){
			if(hasPiece(p1.x,i)){
				return true;
			}
		}return false;
	}
	private boolean hasPiece(int x,int y)
	{
		if (findPiece(x, y) == null){
			return false;
		}
			
		return true;
	}
	public HashMap<Point,Point> twoPoints(Point point1,Point point2){
		HashMap<Point,Point> map = new HashMap<Point,Point>();
		HashMap<Point,Point> map1 = new HashMap<Point,Point>();
		HashMap<Point,Point> map2 = new HashMap<Point,Point>();
		HashMap<Point,Point> returnmap = new HashMap<Point,Point>();
		int widthmax = (config.getXsize()+1)*2*GameConf.PIECE_WIDTH+config.getBeginX();
		int heightmax = (config.getYsize()+1)*2*GameConf.PIECE_HEIGHT+config.getBeginY();
		List<Point> p1Lefts = getLeftChannel(point1,0);
		List<Point> p2Lefts = getLeftChannel(point2,0);
		List<Point> p1Rights = getRightChannel(point1,widthmax);
		List<Point> p2Rights = getRightChannel(point2,heightmax);
		List<Point> p1Ups =getUpChannel(point1,0);
		List<Point> p2Ups = getUpChannel(point2,0);
		List<Point> p1Downs = getDownChannel(point1,heightmax);
		List<Point> p2Downs = getDownChannel(point2,heightmax);
		//同一列的情况
		if(point1.x==point2.x){
			if(p1Lefts.size()!=0){
			//向左遍历
			for(Point p1Left : p1Lefts){
				for(Point p2Left : p2Lefts){
					if(p1Left.x==p2Left.x){
						if(!isBlockY(p1Left,p2Left)){
							map.put(p1Left, p2Left);	
						}
					}
				}
			}if(map.size()!=0){
			map1 = getindexXShortestLine(point1,map);
				}
			if(p1Lefts.size()==0&&p1Rights.size()==0){
				return null;
			}
		}
			if(p1Rights.size()!=0){
			//向右遍历
			for(Point p1Right : p1Rights){
				for(Point p2Right : p2Rights){
					if(p1Right.x==p2Right.x){
						if(!isBlockY(p1Right,p2Right)){
							map.put(p1Right, p2Right);	
						}
					}
				}
			}
			if(map.size()!=0){
				map2 = getindexXShortestLine(point1,map);
			}
		}	
			if(map1.size()!=0&&map2.size()!=0){
				if(point1.x<=((config.getXsize()-1)/2)*2*GameConf.PIECE_WIDTH+config.getBeginX())
					returnmap = map1;
				if(point1.x>((config.getXsize()-1)/2)*2*GameConf.PIECE_WIDTH+config.getBeginX())
					returnmap = map2;		
				return returnmap;
			}
			if(map1.size()==0){
				returnmap = map2;		
				return returnmap;
			}
			if(map2.size()==0){
				returnmap = map1;		
				return returnmap;
			}
		}
		//同一行
		if(point1.y==point2.y){
			if(p1Ups.size()!=0){
				//向上遍历
			for(Point p1Up : p1Ups){
				for(Point p2Up : p2Ups){
					if(p1Up.y==p2Up.y){
						if(!isBlockY(p1Up,p2Up)){
							map.put(p1Up, p2Up);	
						}
					}
				}
			}if(map.size()!=0){
					map1 = getindexYShortestLine(point1,map);
			}
		}
			if(p1Ups.size()==0&&p1Downs.size()==0){
				return null;
			}
			if(p1Downs.size()!=0){
				//向下遍历
			for(Point p1Down : p1Downs){
				for(Point p2Down : p2Downs){
					if(p1Down.y==p2Down.y){
						if(!isBlockX(p1Down,p2Down)){
							map.put(p1Down, p2Down);	
						}
					}
				}
			}if(map.size()!=0){
				map2 = getindexYShortestLine(point1,map);
		}
	}if(map1.size()!=0&&map2.size()!=0){
		if(point1.y<=((config.getXsize()-1)/2)*2*GameConf.PIECE_WIDTH+config.getBeginY())
			returnmap = map1;
		if(point1.y>((config.getXsize()-1)/2)*2*GameConf.PIECE_WIDTH+config.getBeginY())
			returnmap = map2;
		return returnmap;
	}
		if(map1.size()==0){
			returnmap = map2;		
			return returnmap;
		}
		if(map2.size()==0){
			returnmap = map1;		
			return returnmap;
		}
}
	if(p1Ups.size()!=0||p1Downs.size()!=0||p1Lefts.size()!=0||p1Rights.size()!=0||
			p2Ups.size()!=0||p2Downs.size()!=0||p2Lefts.size()!=0||p2Rights.size()!=0){
		//右上角
		if(isRightUp(point1,point2)){
			//向右遍历
			for(Point p1Right : p1Rights)
				for(Point p2Right : p2Rights)
					if(p1Right.x==p2Right.x&&!isBlockY(p1Right,p2Right)){
						map.put(p1Right, p2Right);
					}
			//向左遍历
			for(Point p1Left : p1Lefts)
				for(Point p2Left : p2Lefts)
					if(p1Left.x==p2Left.x&&!isBlockY(p1Left,p2Left)){
						map.put(p1Left, p2Left);
					}
			//向上遍历
			for(Point p1Up : p1Ups)
				for(Point p2Up : p2Ups)
					if(p1Up.y==p2Up.y&&!isBlockX(p1Up,p2Up)){
						map.put(p1Up, p2Up);
					}
			//向下遍历
			for(Point p1Down : p1Downs)
				for(Point p2Down : p2Downs)
					if(p1Down.y==p2Down.y&&!isBlockX(p1Down,p2Down)){
						map.put(p1Down, p2Down);
					}
			//向右向左遍历
			for(Point p1Right : p1Rights)
				for(Point p2Left : p2Lefts)
					if(p1Right.x==p2Left.x&&!isBlockY(p1Right,p2Left)){
						map.put(p1Right, p2Left);
					}
			//向上向下遍历
			for(Point p1Up : p1Ups)
				for(Point p2Down : p2Downs)
					if(p1Up.y==p2Down.y&&!isBlockX(p1Up,p2Down)){
						map.put(p1Up,p2Down);
					}
			if(map.size()!=0){
				returnmap = getShortestLine(point1,point2,map);
			}
		}
		//右下角
		if(isRightDown(point1,point2)){
			//向右遍历
			for(Point p1Right : p1Rights)
				for(Point p2Right : p2Rights)
					if(p1Right.x==p2Right.x&&!isBlockY(p1Right,p2Right)){
						map.put(p1Right, p2Right);
					}
			//向左遍历
			for(Point p1Left : p1Lefts)
				for(Point p2Left : p2Lefts)
					if(p1Left.x==p2Left.x&&!isBlockY(p1Left,p2Left)){
						map.put(p1Left, p2Left);
					}
			//向上遍历
			for(Point p1Up : p1Ups)
				for(Point p2Up : p2Ups)
					if(p1Up.y==p2Up.y&&!isBlockX(p1Up,p2Up)){
						map.put(p1Up, p2Up);
					}
			//向下遍历
			for(Point p1Down : p1Downs)
				for(Point p2Down : p2Downs)
					if(p1Down.y==p2Down.y&&!isBlockX(p1Down,p2Down)){
						map.put(p1Down, p2Down);
					}
			//向右向左遍历
			for(Point p1Right : p1Rights)
				for(Point p2Left : p2Lefts)
					if(p1Right.x==p2Left.x&&!isBlockY(p1Right,p2Left)){
						map.put(p1Right, p2Left);
					}
			//向下向上遍历
			for(Point p1Down : p1Downs)
				for(Point p2Up : p2Ups)
					if(p1Down.y==p2Up.y&&!isBlockX(p1Down,p2Up)){
						map.put(p1Down,p2Up);
					}
				if(map.size()!=0){
				returnmap = getShortestLine(point1,point2,map);
			}
		}
	}else if(p1Ups.size()==0&&p1Downs.size()==0&&p1Lefts.size()!=0&&p1Rights.size()==0&&
			p2Ups.size()==0&&p2Downs.size()==0&&p2Lefts.size()!=0&&p2Rights.size()==0) {
			return null;		
		}
		return returnmap;
	}
	
	
	public Point onepoint(Point p1,Point p2){
		Point returnPoint = null;
		//在右上角，向右向下遍历,向上向左遍历
		if(isRightUp(p1,p2)){
			for(Point point : getRightChannel(p1,p2.x)){
				for(Point point1 : getDownChannel(p2,p1.y)){
					if(point1.equals(point)){
						returnPoint = point1;
					}
				}
			}
			for(Point point : getUpChannel(p1,p2.y)){
				for(Point point1 : getLeftChannel(p2,p1.x)){
					if(point1.equals(point)){
						returnPoint = point1;
					}
				}
			}
		}
		//在右下角，向右向上遍历,向下向左遍历
		if(isRightDown(p1,p2)){
			for(Point point : getRightChannel(p1,p2.x)){
				for(Point point1 : getUpChannel(p2,p1.y)){
					if(point1.equals(point)){
						returnPoint = point1;
					}
				}
			}
			for(Point point : getDownChannel(p1,p2.y)){
				for(Point point1 : getLeftChannel(p2,p1.x)){
					if(point1.equals(point)){
						returnPoint = point1;
					}
				}
			}
		} 
	
		return returnPoint;
	}
	

	public boolean isRightUp(Point p1,Point p2){
		if(p1.x<p2.x&&p1.y>p2.y){
			return true;
		}
		else return false;
	}
	public boolean isRightDown(Point p1,Point p2){
		if(p1.x<p2.x&&p1.y<p2.y){
			return true;
		}
			
		else return false;
	}
	public HashMap<Point,Point> getShortestLine(Point p1,Point p2,HashMap<Point,Point> map){
		int length;
		HashMap<Point,Point> map1 = new HashMap<Point,Point>();
		List<Point> points1 = new ArrayList<Point>();
		List<Point> points2 = new ArrayList<Point>();
		ArrayList<Integer> lengths = new ArrayList<Integer>();
		if(map==null){
			return map;
		}
		if(map!=null){
		for(Point point : map.keySet()){
			if(point.x==map.get(point).x){		
				length=Math.abs(point.y-map.get(point).y)+Math.abs(p1.x-p2.x);
				lengths.add(length);
			}
			if(point.y==map.get(point).y){	
				length=Math.abs(point.x-map.get(point).x)+Math.abs(p1.y-p2.y);
				lengths.add(length);
			}		
			points1.add(point);
			points2.add(map.get(point));
		}	
		map1.put(points1.get(getShortestLength(lengths)), points2.get(getShortestLength(lengths)));
		}
		return map1;
	}
	public HashMap<Point,Point> getindexXShortestLine(Point p1,HashMap<Point,Point> map){
		List<Point> points1 = new ArrayList<Point>();
		List<Point> points2 = new ArrayList<Point>();
		int length ;
		ArrayList<Integer> lengths = new ArrayList<Integer>();
		for(Point point : map.keySet()){
			length = Math.abs(point.x-p1.x);
			lengths.add(length);
			points1.add(point);
			points2.add(map.get(point));
		}HashMap<Point,Point> map1 = new HashMap<Point,Point>();
		map1.put(points1.get(getShortestLength(lengths)), points2.get(getShortestLength(lengths)));
		return map1;
	}
	public HashMap<Point,Point> getindexYShortestLine(Point p1,HashMap<Point,Point> map){
		List<Point> points1 = new ArrayList<Point>();
		List<Point> points2 = new ArrayList<Point>();
		int length;
		ArrayList<Integer> lengths = new ArrayList<Integer>();
		for(Point point : map.keySet()){
			length = Math.abs(point.y-p1.y);
			lengths.add(length);
			points1.add(point);
			points2.add(map.get(point));
		}HashMap<Point,Point> map1 = new HashMap<Point,Point>();
		map1.put(points1.get(getShortestLength(lengths)), points2.get(getShortestLength(lengths)));
		return map1;
		
	}
	
	public int getShortestLength(ArrayList<Integer> lengths){
		int shortlength = lengths.get(0);
		int shortlengthnumber = 0;
		for(int i=0;i<lengths.size();i++){
			if(lengths.get(i)<shortlength){
				shortlengthnumber = i;
		}
	}
			return shortlengthnumber;
}

	public boolean hasPieces(){
		for(int i=0;i<pieces.length;i++)
			for(int j=0;j<pieces[i].length;j++){
				if(pieces[i][j]!=null)
					return true;
			}
		return false;
	}
		
	public Piece findPiece(float touchX, float touchY)
	{	

		int indexX = (int) touchX - this.config.getBeginX();
		int indexY = (int) touchY - this.config.getBeginY();
		
		if (indexX < 0 || indexY < 0)
		{
			return null;
		}
		indexX = getIndex(indexX, 128);
	    indexY = getIndex(indexY, 128);
		if (indexX < 0 || indexY < 0)
		{	
			return null;
		}
		if (indexX >= this.config.getXsize()||indexY>= this.config.getYsize())
		{	
			return null;
		}
		return this.pieces[indexX][indexY];
	}

	private int getIndex(int index, int size)
	{
		if (index % size == 0)
		{
			index = index / size - 1;
		}
		else
		{
			index = index / size;
		}
		return index;
	}
}

