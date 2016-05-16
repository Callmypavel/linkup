package lianliankan;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Point;

public class LinkInfo {
	private List<Point> points = new ArrayList<Point>();
	
	public LinkInfo(){
		points=null;
	}
	public LinkInfo(Point p0,Point p1){
		points.add(p0);
		points.add(p1);	
	}
	public LinkInfo(Point p0,Point p1,Point p2){
		points.add(p0);
		points.add(p1);
		points.add(p2);
	}
	public LinkInfo(Point p0,Point p1,Point p2,Point p3){
		points.add(p0);
		points.add(p1);
		points.add(p2);
		points.add(p3);
	}
	public List<Point> getLinkPoints(){
		return points;
	}
	public boolean isconnevtive(){
		if(points!=null){
			return true;
		}else return false;
	}
}
