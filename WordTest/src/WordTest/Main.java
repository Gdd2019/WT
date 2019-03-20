package WordTest;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import javax.swing.JFrame;
 
public class Main extends JFrame{
	private static final long serialVersionUID = 1L;
	public static String[] WordCount;
	public static Map<String, Integer> WordsCount;
	public static void main(String[] args) throws IOException{
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		//读入《哈利波特》
		String line = "src/word.txt";
		File file = new File(line);
		InputStreamReader is = new InputStreamReader(new FileInputStream(file), "utf-8");
		BufferedReader buff = new BufferedReader(is);
		List<String> list = new ArrayList<String>();//list中只存放纯单词文本
		String readLine = null;//单词列表初始为空
		while((readLine = buff.readLine())!=null){
			String[] WordsArr = readLine.split("[^a-zA-z]");
			for(String word:WordsArr){
				if(word.length()!=0){
					list.add(word);//将截取后的纯单词放入list
				}
			}
		}
		buff.close();
		WordsCount = new TreeMap<String,Integer>();//键值对存储单词信息，String为单词，Integer为单词出现次数
		//词频统计(查询单次出现次数，并循环进行累加统计)
		for(String lists:list){
			if(WordsCount.get(lists)!=null){
				WordsCount.put(lists, WordsCount.get(lists)+1);//单次出现频次累加
			}else{
				WordsCount.put(lists, 1);
			}
		}
		System.out.println("********词频统计功能********");
		System.out.println("请选择功能(输入1~5之间的整数)：");
		System.out.println("1.统计词频个数");
		System.out.println("2.查询词频统计柱状图");
		System.out.println("3.查询高频词");
		System.out.println("4.按字典顺序输出");
		System.out.println("5.退出系统");
		int i = in.nextInt();
		switch(i){
		case 1:	HighCount hwc = new HighCount();
				System.out.println("请输入要查询单词的个数k(正整数)：");
				int n = in.nextInt();
				hwc.SortMap(WordsCount, n);
				break;
		case 2: 	
				break;
}
   
}  
	//绘制柱形图
	public Main(){
		super();
		setTitle("词汇统计柱状图");
		setBounds(WordCount.length, 200, 400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	@Override
	public void paint(Graphics g){
		int Width = getWidth();
		int Height = getHeight();
		int leftMargin = 40;//柱形图左边界
		int topMargin = 40;//柱形图上边界
		Graphics2D g2 = (Graphics2D) g;
		int ruler = Height-topMargin;
		int rulerStep = ruler/20;//将当前的高度评分为20个单位
		g2.setColor(Color.WHITE);//绘制白色背景
		g2.fillRect(0, 0, Width, Height);//绘制矩形图
		g2.setColor(Color.LIGHT_GRAY);
		for(int i=0;i<rulerStep;i++){
			g2.drawString((400-20*i)+"个", 8, topMargin+rulerStep*i);//绘制Y轴上的数据
		}
		g2.setColor(Color.BLUE);
		int m=0;
		for(int i = 0;i<WordCount.length;i++){
			int value = WordsCount.get(WordCount[i]);
			int step = (m+1)*40;//设置每隔柱形图的水平间隔为40
			g2.fillRoundRect(leftMargin+step*2,Height-value, 40, value, 40, 10);//绘制每个柱状条
			g2.drawString(WordCount[i], leftMargin+step*2, Height-value-5);	//标识每个柱状条		
			m++;
		}
	}
	}
