package com.gym.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;

/**
 * 后端身份识别二维码生成
 * @author Administrator
 *
 */
public class CarCodeUtil {
	public static void getCar(OutputStream outputStream) throws IOException{
		int width=235,height=235;
		//在缓存中创建一个图片对象
		//R red G green B blue 三原色
		BufferedImage bImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		//通过内存中的图片拿到绘图工具类
		Graphics2D grap = bImage.createGraphics();
		//设置画笔颜色
		grap.setColor(Color.WHITE);
		//填充一个矩形
		grap.fillRect(0, 0, width, height);
		/**
		 * 21*21个模块，177*177个模块，每提高一个版本会增加四个模块
		 * 数据类型与容量
		 * 数字：7089个字符
		 * 字母：4296个字符
		 * 8位字节的数据：2953
		 * 汉字：1817
		 * 
		 * 数据表示方法：深色模块便是二进制1，浅色模块表示二进制0
		 * 纠错能力：L(7%) M(15%) Q(25%) H(30%),纠错能力越高，存储数据越少
		 * 
		 */
		//qrcode.jar
		Qrcode qrcode = new Qrcode();
		qrcode.setQrcodeEncodeMode('B');
		//设置纠错率
		qrcode.setQrcodeErrorCorrect('M');
		//设置版本号
		qrcode.setQrcodeVersion(15);
		//需要加密生成二维码的内容,vCard电子名片
		//String content ="-------http://www.jijijia.com/";
		String content ="BEGIN:VCARD VERSION:3.0\nFN:郭兰萍\nTITLE:在校大学生\nORG:长治学院南校区\nEMAIL:641274615@qq.com\nTEL;CELL;VOICE:18535598236\nADR:山西省阳泉市 END:VCARD";
		boolean[][] bytes = qrcode.calQrcode(content.getBytes("utf-8"));
		//设置画笔颜色为黑色
		grap.setColor(Color.BLACK);
		//二进制 boolean true:1 false:0
		for(int i =0;i<bytes.length;i++){
			for(int j=0;j<bytes.length;j++){
				if(bytes[j][i]){//描述黑色方块
					grap.fillRect(j*3+2, i*3+2, 3, 3);
				}
			}
		}
		//中间的log图标
		//BufferedImage logo = ImageIO.read(new File("D://log2.jpg"));
		//grap.drawImage(logo, 92, 92,50,50,null);
		//保存图形到磁盘中
		//ImageIO.write(bImage, "JPEG", new File("d://huang.jpeg"));
		try {
			ImageIO.write(bImage, "JPEG", outputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
