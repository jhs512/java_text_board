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
	private ArticleController articleController;
	public static Scanner sc;

	public App() {
		articleController = new ArticleController();

		articleController.makeTestData();
	}

	public void run() {
		sc = new Scanner(System.in);
		while (true) {
			System.out.printf("명령어) ");
			String command = sc.nextLine().trim();

			if (command.length() == 0)
				continue;
			if (command.equals("article add")) {
				articleController.doAdd();
			} else if (command.startsWith("article list ")) {
				articleController.showList(command);
			} else if (command.equals("system exit")) {
				System.out.println("프로그램을 종료합니다.");
				break;
			}
		}

		sc.close();
	}
}

class ArticleController {
	private static final int ITEMS_COUNT_IN_A_PAGE = 5;
	private List<Article> articles;
	private int articlesLastId;

	public ArticleController() {
		articles = new ArrayList<>();
		articlesLastId = 0;
	}

	private int addArticle(String title, String body) {
		int id = articlesLastId + 1;
		String regDate = Util.getNowDateStr();
		Article article = new Article(id, regDate, title, body);
		articles.add(article);
		articlesLastId++;

		return articlesLastId;
	}

	public void makeTestData() {
		int testDataCount = 40;

		for (int i = 0; i < testDataCount; i++) {
			int id = i + 1;
			String title = (id % 2 == 0 ? "잘가_" : "안녕_") + id;
			String body = (id % 2 == 0 ? "조심히 가렴_" : "반가워_") + id;

			addArticle(title, body);
		}
	}

	public void showList(String command) {
		String[] commandBits = command.split(" ");
		int pageParamIndex = commandBits.length - 1;

		int page = Integer.parseInt(commandBits[pageParamIndex]);
		int totalPage = (int) Math.ceil(articles.size() / (float) ITEMS_COUNT_IN_A_PAGE);

		if (page < 1) {
			page = 1;
		} else if (page > totalPage) {
			page = totalPage;
		}

		System.out.printf("= 게시물 리스트(%dp/%dp) - 시작 =\n", page, totalPage);

		System.out.println("번호 / 날짜 / 제목");

		int startIndex = articles.size() - 1;

		startIndex -= ITEMS_COUNT_IN_A_PAGE * (page - 1);

		int endIndex = startIndex - ITEMS_COUNT_IN_A_PAGE + 1;

		if (endIndex < 0) {
			endIndex = 0;
		}

		for (int i = startIndex; i >= endIndex; i--) {
			Article article = articles.get(i);

			System.out.printf("%d / %s / %s\n", article.getId(), article.getRegDate(), article.getTitle());
		}

		System.out.printf("= 게시물 리스트(%dp/%dp) - 끝 =\n", page, totalPage);
	}

	public void doAdd() {
		System.out.println("= 게시물 작성 - 시작 =");
		System.out.printf("제목 : ");
		String title = App.sc.nextLine().trim();
		System.out.printf("내용 : ");
		String body = App.sc.nextLine().trim();

		int id = addArticle(title, body);

		System.out.printf("%d번 글이 생성되었습니다.\n", id);
		System.out.println("= 게시물 작성 - 끝 =");
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