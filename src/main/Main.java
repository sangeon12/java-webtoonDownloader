package main;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String url = "http://comic.naver.com/webtoon/detail.nhn?";
		System.out.println("원하는 웹툰의 id를 입력하세요");
		String id = in.next();
		url += "titleId=" + id + "&no=";
		
		System.out.println("원하는 웹툰의 첫번째 회차를 입력하세요");
		int firstNo = in.nextInt();
		
		System.out.println("원하는 웹툰의 마지막 회차를 입력하세요");
		int endNo = in.nextInt();
		
		System.out.println("저장될 경로를 입력하세요");
		String path = in.next();
		
		Downloader d = new Downloader();
		d.download(url,firstNo, endNo, path);
	}
}
