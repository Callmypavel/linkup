package lianliankan;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.example.lianliankan.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

	public class Range extends Activity {
			private int GameTime;
			private int record[] = new int[10];
			private ListView listview;		
 			protected void onCreate(Bundle savedInstanceState) {
	 			// TODO Auto-generated method stub
	 			super.onCreate(savedInstanceState);
	 			int flag=WindowManager.LayoutParams.FLAG_FULLSCREEN;
	 			Window window=Range.this.getWindow();
	 			window.setFlags(flag, flag);
	 			requestWindowFeature(Window.FEATURE_NO_TITLE);
	 		    GameTime = this.getIntent().getExtras().getInt("GameTime");
				int newrecord = 100-GameTime;
				System.out.println("����Ϊ"+newrecord);
				try {
					System.out.println("��һ��");
					ArrayList<String> list  = readRange();
					System.out.println("ת�����"+Integer.parseInt(list.get(0)));
					System.out.println("���"+list);
					System.out.println("ת�����"+Integer.parseInt(list.get(0)));
					for(int i=0;i<10;i++){
						record[i]=Integer.parseInt(list.get(i));
						System.out.println("�ɹ���ȡ");
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("��ȡʧ��");
					System.out.println(e.getCause());
					e.printStackTrace();
				}
				
				if(newrecord<record[0]){						
					for(int i=0;i<9;i++){
					record[i] = record[i+1];
					}
					record[0] = newrecord; 			
				}
				if(record[0]!=0){
					for(int i=0;i<10;i++){
						if(newrecord>record[i]){
							record[i+1]=newrecord;
							break;
						}
					}
				}
				if(record[0]==0){
					System.out.println("��һ��Ϊ0");
					record[0]=newrecord;
					System.out.println("��һ����������Ϊ"+record[0]);
					saveRange();
					System.out.println("�ɹ�����"+saveRange());
				}
	 			listview = new ListView(this);
	 			listview.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, getData()));
	 			System.out.println("����������");
	 			listview.setBackgroundResource(R.drawable.assassin);
	 			setContentView(listview);		
	 			 
 }
 		
 			private List<String> getData(){
 				List<String> list = new ArrayList<String>();
 				for(int i=0;i<10;i++){
 					list.add(i+1+"�� "+record[i]);
 					System.out.println("ԭʼ����"+record[i]);
 				}
 				return list;
 			}
 			
 			public boolean saveRange(){
 				String path = Environment.getExternalStorageDirectory()+"/range.txt";
 				File file = new File(path);
 				try{
 					System.out.println("1");
 					FileOutputStream fos = new FileOutputStream(file);
 					System.out.println("2");
 					for(int i =0;i<10;i++){
 					System.out.println(i+" "+record[i]+" "+record[i+1]);
 					fos.write((record[i]+"##").getBytes());		
 					}
 					fos.close();
 				}catch(Exception e){
 					e.getCause();
 					System.out.println("���� ����ʧ��"+e.getCause());
 					return false;
 				}
 				return true;
 			}
 			public ArrayList<String> readRange() {
 				ArrayList<String> list = new ArrayList<String>();
 				File file = new File(Environment.getExternalStorageDirectory()+"/range.txt");
 				FileInputStream fis;
				try {
					fis = new FileInputStream(file);
					BufferedReader br = new BufferedReader(new InputStreamReader(fis));
	 				String str = null;
	 				try{
	 					str = br.readLine();
	 					System.out.println("���������ַ���"+str);
	 				}catch(IOException e){
	 					System.out.println("io�쳣");
	 				}
	 				String[]ranges = str.split("##");
	 				System.out.println("��������ranges"+ranges);
	 				System.out.println("��������ranges[0]"+ranges[0]);
	 				for(int i=0;i<10;i++){
	 				System.out.println("list��ʼ����������"+ranges[i]+list);
	 				list.add(ranges[i]);
	 				}
	 				for(int i=0;i<10;i++){
	 				System.out.println("��������list.get(i)"+list.get(0));
	 				}
	 				System.out.println("��������list"+list);
	 				return list;
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					System.out.println("������ �����Ҳ�����");
					e1.printStackTrace();
				}
				System.out.println("�ߵ���һ��"+list);
				return list;
 				
 			}
}
