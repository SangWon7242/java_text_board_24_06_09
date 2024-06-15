package com.sbs.java.board;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int articleLastId = 0;
    Article lastArticle = null;

    System.out.println("== 자바 텍스트 게시판 ==");

    while (true) {
      System.out.print("명령) ");
      String cmd = sc.nextLine();

      if(cmd.equals("/usr/article/write")) {
        System.out.print("제목 : ");
        String title = sc.nextLine();

        System.out.print("내용 : ");
        String content = sc.nextLine();

        int id = ++articleLastId;

        Article article = new Article(id, title, content);
        lastArticle = article;

        System.out.println("생성된 게시물 객체 : " + article);
        System.out.printf("%d번 게시물이 등록되었습니다.\n", article.id);
      }
      else if(cmd.equals("/usr/article/detail")) {
        if(lastArticle == null) {
          System.out.println("게시물이 존재하지 않습니다.");
          continue;
        }

        Article article = lastArticle;

        System.out.println("== 게시물 상세보기 ==");
        System.out.printf("번호 : %d\n", article.id);
        System.out.printf("제목 : %s\n", article.title);
        System.out.printf("내용 : %s\n", article.content);
      }
      else if(cmd.equals("exit")) {
        System.out.println("== 자바 텍스트 게시판 종료 ==");
        break;
      }
      else {
        System.out.println("올바른 명령어가 아닙니다.");
      }
    }

    sc.close();
  }
}

class Article {
  int id;
  String title;
  String content;

  Article(int id, String title, String content) {
    this.id = id;
    this.title = title;
    this.content = content;
  }

  @Override
  public String toString() {
    return "{id: %d, title: \"%s\", body: \"%s\"}".formatted(id, title, content);
  }
}