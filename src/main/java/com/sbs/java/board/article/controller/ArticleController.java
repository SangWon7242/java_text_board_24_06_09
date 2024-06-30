package com.sbs.java.board.article.controller;

import com.sbs.java.board.article.entity.Article;
import com.sbs.java.board.Rq;
import com.sbs.java.board.Util;
import com.sbs.java.board.article.service.ArticleService;
import com.sbs.java.board.container.Container;

import java.util.ArrayList;
import java.util.List;

public class ArticleController {
  private ArticleService articleService;

  public ArticleController() {
    articleService = Container.articleService;
  }

  public void actionWrite() {
    System.out.print("제목 : ");
    String title = Container.sc.nextLine();

    if (title.trim().isEmpty()) {
      System.out.println("제목을 입력해주세요.");
      return;
    }

    System.out.print("내용 : ");
    String content = Container.sc.nextLine();

    if (content.trim().isEmpty()) {
      System.out.println("내용을 입력해주세요.");
      return;
    }

    articleService.write(title, content);

    System.out.println("게시물이 등록되었습니다.");
  }

  public void showDetail(Rq rq) {
    int id = rq.getIntParam("id", 0);

    if(id == 0) {
      System.out.println("id를 올바르게 입력해주세요.");
      return;
    }

    List<Article> articles = articleService.getArticles();

    if (articles.isEmpty()) {
      System.out.println("게시물이 존재하지 않습니다.");
      return;
    }

    Article article = articleService.findById(id);

    if (article == null) {
      System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
      return;
    }

    System.out.println("== 게시물 상세보기 ==");
    System.out.printf("번호 : %d\n", article.getId());
    System.out.printf("제목 : %s\n", article.getTitle());
    System.out.printf("내용 : %s\n", article.getContent());
  }

  public void showList(Rq rq) {
    String searchKeyword = rq.getParam("searchKeyword", "");
    String orderBy = rq.getParam("orderBy", "idDesc");
    boolean orderByIdDesc = orderBy.equals("idDesc"); // 역순 정렬에 대한 코드

    System.out.println("== 게시물 리스트 ==");
    System.out.println("번호 | 제목");

    List<Article> filteredArticles = articleService.getArticles();
    
    // 검색 시작
    if (!searchKeyword.isEmpty()) {
      filteredArticles = new ArrayList<>();

      for (Article article : articleService.getArticles()) {
        if (article.getTitle().contains(searchKeyword) || article.getTitle().contains(searchKeyword)) {
          filteredArticles.add(article);
        }
      }
    }
    // 검색 끝

    // 정렬 시작
    List<Article> sortedArticles = filteredArticles;

    if (orderByIdDesc) {
      sortedArticles = Util.reverseList(sortedArticles);
    }
    // 정렬 끝

    // 게시물 리스트 출력
    for (Article article : sortedArticles) {
      System.out.printf("%d | %s\n", article.getId(), article.getTitle());
    }
  }

  public void actionModify(Rq rq) {
    int id = rq.getIntParam("id", 0);

    if(id == 0) {
      System.out.println("id를 올바르게 입력해주세요.");
      return;
    }

    List<Article> articles = articleService.getArticles();

    if (articles.isEmpty()) {
      System.out.println("게시물이 존재하지 않습니다.");
      return;
    }

    Article article = articleService.findById(id);

    if (article == null) {
      System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
      return;
    }

    System.out.print("새 내용 : ");
    article.setTitle(Container.sc.nextLine());
    System.out.print("새 제목 : ");
    article.setContent(Container.sc.nextLine());

    System.out.printf("%d번 게시물을 수정하였습니다.\n", id);
  }

  public void actionDelete(Rq rq) {
    int id = rq.getIntParam("id", 0);

    if(id == 0) {
      System.out.println("id를 올바르게 입력해주세요.");
      return;
    }

    List<Article> articles = articleService.getArticles();

    if (articles.isEmpty()) {
      System.out.println("게시물이 존재하지 않습니다.");
      return;
    }

    Article article = articleService.findById(id);

    if (article == null) {
      System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
      return;
    }

    articleService.remove(article);

    System.out.printf("%d번 게시물이 삭제되었습니다.\n", id);
  }
}
