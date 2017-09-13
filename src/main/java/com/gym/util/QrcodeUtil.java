package com.gym.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;

/**
 * 二维码生成工具类，，，扫码认证登录
 * 
 * @author Administrator
 *
 */
public class QrcodeUtil {
	public static Map<String, String> user = new HashMap<String,String>();
	public static BufferedImage getQrcode(String content) throws UnsupportedEncodingException {
		// qrcode.jar
		Qrcode qrcode = new Qrcode();
		qrcode.setQrcodeEncodeMode('B');
		// 纠错能力：L(7%) M(15%) Q(25%) H(30%),纠错能力越高，存储数据越少
		qrcode.setQrcodeErrorCorrect('M');
		// 设置版本号,版本越高存储数据越多，图片建议越大
		qrcode.setQrcodeVersion(15);
		int width = 235, height = 235;
		// 在缓存中创建一个图片对象
		// R red G green B blue 三原色
		BufferedImage bImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		// 通过内存中的图片拿到绘图工具类
		Graphics2D grap = bImage.createGraphics();
		// 设置画笔颜色
		grap.setColor(Color.WHITE);
		// 从 0 0 开始 情况图片的内容
		grap.fillRect(0, 0, width, height);
		boolean[][] bytes = qrcode.calQrcode(content.getBytes("utf-8"));
		// 设置画笔颜色为黑色
		grap.setColor(Color.BLACK);
		// 二进制 boolean true:1 false:0
		for (int i = 0; i < bytes.length; i++) {
			for (int j = 0; j < bytes.length; j++) {
				if (bytes[j][i]) {// 描述黑色方块
					grap.fillRect(j * 3 + 2, i * 3 + 2, 3, 3);
				}
			}
		}
		return bImage;
	}
	public static void main(String[] args) throws UnsupportedEncodingException {
		BufferedImage bufferedImage = getQrcode("hllo word");
		try {
			ImageIO.write(bufferedImage, "JPEG",new File("d:\\a.jpeg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
