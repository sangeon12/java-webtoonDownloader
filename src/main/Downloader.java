package main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Downloader {
	public void download(String addr,int firstNo, int endNo, String target) {
		try {
			
			for(int i = firstNo; i <= endNo; i++) {
				Document doc = Jsoup.connect(addr+i).get();
				Elements imgs = doc.select("img[src~=image-comic]");
				File saveFile = new File(target + File.separator +i);
				saveFile.mkdir();
				
				for(int j = 0; j < imgs.size(); j++) {
					String src = imgs.get(j).attr("src");
					
					imgDown(addr, target, src, j+1, i);
					
					System.out.println(i+"화 총 " + imgs.size() + "개 중 " + (j+1) + "개 다운로드 완료");
				}
			}
		} catch (Exception e) {
			System.out.println("다운로드중 오류발생 url 확인필요");
		}
	}
	
	private void imgDown(String addr, String target, String src, int page, int no) {
		
		try {
			int idx = src.lastIndexOf("."); 
			String ext = src.substring(idx); 
			
			File saveFile = new File(target + File.separator +no+ File.separator + page + ext);
			FileOutputStream fos = new FileOutputStream(saveFile);
			
			HttpURLConnection conn = (HttpURLConnection)new URL(src).openConnection();
			conn.setConnectTimeout(40000); 
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Referer", addr);
			conn.setRequestProperty("User-Agent", "Mozilla/5.0");
			InputStream in = conn.getInputStream();
			
			byte[] buffer = new byte[1024*1024];
			int len = 0; 
			while(true) {
				len = in.read(buffer);
				if(len <= 0) {
					break;
				}
				fos.write(buffer, 0, len);
			}
			fos.close();
			in.close();
			
		} catch (Exception e) {
			System.out.println(page + "번 이미지 다운중 오류 발생");
		}
	}
}
