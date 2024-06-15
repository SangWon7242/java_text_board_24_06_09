package com.sbs.java.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

  static void makeTestData(List<Article> articles) {
    articles.add(new Article(1, "제목1", "내용1"));
    articles.add(new Article(2, "제목2", "내용2"));
    articles.add(new Article(3, "제목3", "내용3"));
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int articleLastId = 0;
    Article lastArticle = null;

    List<Article> articles = new ArrayList<>();

    makeTestData(articles);

    if(!articles.isEmpty()) {
      articleLastId = articles.get(articles.size() - 1).id;
    }

    System.out.println("== 자바 텍스트 게시판 ==");

    while (true) {
      System.out.print("명령) ");
      String cmd = sc.nextLine();

      if (cmd.equals("/usr/article/write")) {
        System.out.print("제목 : ");
        String title = sc.nextLine();

        if(title.trim().isEmpty()) {
          System.out.println("제목을 입력해주세요.");
          continue;
        }

        System.out.print("내용 : ");
        String content = sc.nextLine();

        if(content.trim().isEmpty()) {
          System.out.println("내용을 입력해주세요.");
          continue;
        }

        int id = ++articleLastId;

        Article article = new Article(id, title, content);
        lastArticle = article;

        articles.add(article);

        System.out.printf("%d번 게시물이 등록되었습니다.\n", article.id);
      } else if (cmd.equals("/usr/article/detail")) {
        if (lastArticle == null) {
          System.out.println("게시물이 존재하지 않습니다.");
          continue;
        }

        Article article = lastArticle;

        System.out.println("== 게시물 상세보기 ==");
        System.out.printf("번호 : %d\n", article.id);
        System.out.printf("제목 : %s\n", article.title);
        System.out.printf("내용 : %s\n", article.content);
      } else if (cmd.equals("/usr/article/list")) {
        System.out.println("== 게시물 리스트 ==");

        System.out.println("번호 | 제목");

       for(int i = articles.size() - 1; i >= 0; i--) {
         Article article = articles.get(i);
         System.out.printf("%d | %s\n", article.id, article.title);
       }

      } else if (cmd.equals("exit")) {
        System.out.println("== 자바 텍스트 게시판 종료 ==");
        break;
      } else {
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