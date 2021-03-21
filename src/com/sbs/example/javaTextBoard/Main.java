package com.sbs.example.javaTextBoard;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("== 프로그램 시작 ==");

		new App().run();

		System.out.println("== 프로그램 끝 ==");
	}
}

class App {
	private List<Article> articles;
	private int articlesLastId;

	public App() {
		articles = new ArrayList<>();
		articlesLastId = 0;
	}

	public void run() {
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.printf("명령어) ");
			String command = sc.nextLine().trim();

			if (command.length() == 0)
				continue;

			if (command.equals("article add")) {
				System.out.println("= 게시물 작성 - 시작 =");
				System.out.printf("제목 : ");
				String title = sc.nextLine().trim();
				System.out.printf("내용 : ");
				String body = sc.nextLine().trim();
				String regDate = Util.getNowDateStr();
				int id = articlesLastId + 1;

				Article article = new Article(id, regDate, title, body);
				articlesLastId++;

				articles.add(article);

				System.out.printf("%d번 글이 생성되었습니다.\n", id);
				System.out.println("= 게시물 작성 - 끝 =");
			} else if (command.equals("article list")) {
				System.out.println("= 게시물 리스트 - 시작 =");

				System.out.println("번호 / 날짜 / 제목");

				for (int i = articles.size() - 1; i >= 0; i--) {
					Article article = articles.get(i);

					System.out.printf("%d / %s / %s\n", article.getId(), article.getRegDate(), article.getTitle());
				}

				/*
				 * for (Article article : articles) { }
				 */

				System.out.println("= 게시물 리스트 - 끝 =");
			} else if (command.equals("system exit")) {
				System.out.println("프로그램을 종료합니다.");
				break;
			}
		}

		sc.close();
	}
}

class Article {
	private int id;
	private String regDate;
	private String title;
	private String body;

	public Article(int id, String regDate, String title, String body) {
		super();
		this.id = id;
		this.regDate = regDate;
		this.title = title;
		this.body = body;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", regDate=" + regDate + ", title=" + title + ", body=" + body + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

}

class Util {
	public static String getNowDateStr() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}
}