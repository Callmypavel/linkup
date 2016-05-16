package lianliankan;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.example.lianliankan.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ImageUtil {
	static List<Integer> ImageValues= getImageValues();
	public static List<Integer> getImageValues(){
		try{
			Field[] Fields=R.drawable.class.getFields();
			List<Integer> values = new ArrayList<Integer>();
			for(Field field : Fields){
				if(field.getName().indexOf('o')!=-1){
					values.add(field.getInt(R.drawable.class));
				}
			}
			return values;
		}catch(Exception e){
			return null;
		}
	}
	public static List<Integer> getRandomValues(List<Integer> values,int number){
		Random random = new Random();
		List<Integer> RandomValues = new ArrayList<Integer>();
		try{
			for(int i=0;i<number;i++){
			int index = random.nextInt(values.size());
			RandomValues.add(values.get(index));
			}return RandomValues;
		}catch(Exception e){
			return RandomValues;
		}	
	}
	public static List<Integer> getPlayValues(int PieceNumber){
		if(PieceNumber%2!=0){
			PieceNumber+=1;
		}
		List<Integer> PlayValues = getRandomValues(ImageValues,PieceNumber/2);
		PlayValues.addAll(PlayValues);
		return PlayValues;
	}
	public static List<PieceImage> getPlayImages(int PieceNumber,Context context){
		List<Integer> PlayValues= getPlayValues(PieceNumber);
		List<PieceImage> result = new ArrayList<PieceImage>();
		for(Integer value : PlayValues){
			Bitmap bm = BitmapFactory.decodeResource(context.getResources(), value);
			PieceImage PI = new PieceImage(value,bm);
			result.add(PI);
		}return result;
	}

	public static Bitmap getSelectedImage(Context context) {
		Bitmap bm = BitmapFactory.decodeResource(context.getResources(), R.drawable.selected);
		return bm;
	}
	
	

}
