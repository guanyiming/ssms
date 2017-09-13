package com.gym.util;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;

public class ImageUtil {
	private static final String CODE="qwertyuioplkjhgfdsazxcvbnmQWERTYUIOPLKJHGFDSAZXCVBNM0123456789";
	/*public static void main(String[] args) {
		int width = 250;
		int height = 520;
		//内存缓存图片,默认颜色的黑色的
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D grad = image.createGraphics();
		try {
			//修改背景为白色
			grad.setColor(Color.WHITE);
			//背景覆盖为白色
			grad.fillRect(0, 0, width, height);
			//设置画笔为黑色
			grad.setColor(Color.black);
			grad.drawString("萍萍丑旦", 50, 50);
			grad.setColor(Color.RED);
			//扰乱线
			grad.drawLine(20, 20, 120, 120);
			randLines(grad,width,height);
			ImageIO.write(image, "jpeg",new File("D:\\abc.jpeg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i = 0;i<10;i++){
			randColor(150,200);
		}
	}*/
	public static String  getRandImg(OutputStream outputStream,int width,int height){
		//内存缓存图片,默认颜色的黑色的
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D grad = image.createGraphics();
		//修改背景
		grad.setColor(randColor(100, 150));
		//背景覆盖
		grad.fillRect(0, 0, width, height);
		//先花线，在画验证码，这样验证码在线的上边
		randLines(grad,width,height);
		//起始的坐标位置
		int x = 5;
		String code = getRandomCode(4);
		grad.setFont(new Font("宋体", Font.BOLD, 30));
		for(int i = 0 ;i<code.length();i++){
			grad.setColor(randColor(200, 255));
			//验证码旋转的角度
			int degree = new Random().nextInt()%30;
			//旋转角度
			grad.rotate(degree*Math.PI/180, x, 30);
			grad.drawString(code.charAt(i)+"", x, 30);
			grad.rotate(-degree*Math.PI/180, x, 30);
			x+=30;
		}
		try {
			ImageIO.write(image, "jpeg", outputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return code;
	}
	private static String getRandomCode(int nums){
		Random random = new Random();
		StringBuilder strb = new StringBuilder();
		for(int i =0 ; i <nums ; i++ ){
			strb.append(CODE.charAt(random.nextInt(CODE.length())));
		}
		return strb.toString();
	}
	/**
	 * 1.产生随机颜色，（字体颜色是随机的）
	 * 2、产生随机的干扰线
	 * 3、字体进行角度旋转
	 */
	public static Color randColor(int start,int end){
		Random random = new Random();
		//0-255
		int red = start +(random.nextInt(end-start));
		int green=start +(random.nextInt(end-start));
		int blue=start +(random.nextInt(end-start));
		return new Color(red, green, blue);
	}
	/**
	 * 随机干扰线
	 */
	public static void randLines(Graphics2D grap,int widht,int height){
		for(int i = 0;i <40;i++){
			grap.setColor(randColor(0, 200));
			int x1 = new Random().nextInt(widht);
			int y1 = new Random().nextInt(height);
			int x2 = new Random().nextInt(widht);
			int y2 = new Random().nextInt(height);
			grap.drawLine(x1, y1, x2, y2);
		}
	}
}
