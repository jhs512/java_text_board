package com.sbs.example.javaTextBoard;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("== 프로그램 시작 ==");

		new App().run();

		System.out.println("== 프로그램 끝 ==");
	}
}

class App {
	public void run() {
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.printf("명령어) ");
			String command = sc.nextLine().trim();

			if (command.length() == 0)
				continue;

			if (command.equals("system exit")) {
				System.out.println("프로그램을 종료합니다.");
				break;
			}
		}

		sc.close();
	}
}
