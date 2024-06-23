package com.sbs.java.board;

import java.util.*;
import java.util.stream.IntStream;

public class Main {

  static void makeTestData(List<Article> articles) {
    IntStream.rangeClosed(1, 100)
        .forEach(i -> articles.add(new Article(i, "제목" + i, "내용" + i)));
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int articleLastId = 0;

    List<Article> articles = new ArrayList<>();

    makeTestData(articles);

    if(!articles.isEmpty()) {
      articleLastId = articles.get(articles.size() - 1).id;
    }

    System.out.println("== 자바 텍스트 게시판 ==");

    while (true) {
      System.out.print("명령) ");
      String cmd = sc.nextLine();

      Rq rq = new Rq(cmd);

      if (rq.getUrlPath().equals("/usr/article/write")) {
        actionUsrArticleWrite(sc, articleLastId, articles);
        articleLastId++;
      } else if (rq.getUrlPath().equals("/usr/article/detail")) {
        actionUsrArticleDetail(rq, articles);
      } else if (rq.getUrlPath().equals("/usr/article/list")) {
        actionUsrArticleList(rq, articles);
      } else if (cmd.equals("exit")) {
        System.out.println("== 자바 텍스트 게시판 종료 ==");
        break;
      } else {
        System.out.println("올바른 명령어가 아닙니다.");
      }
    }

    sc.close();
  }

  private static void actionUsrArticleWrite(Scanner sc, int articleLastId, List<Article> articles) {
    System.out.print("제목 : ");
    String title = sc.nextLine();

    if(title.trim().isEmpty()) {
      System.out.println("제목을 입력해주세요.");
      return;
    }

    System.out.print("내용 : ");
    String content = sc.nextLine();

    if(content.trim().isEmpty()) {
      System.out.println("내용을 입력해주세요.");
      return;
    }

    int id = ++articleLastId;

    Article article = new Article(id, title, content);

    articles.add(article);

    System.out.printf("%d번 게시물이 등록되었습니다.\n", article.id);
  }

  private static void actionUsrArticleDetail(Rq rq, List<Article> articles) {
    Map<String, String> params = rq.getParams();

    if(!params.containsKey("id")) {
      System.out.println("id를 입력해주세요.");
      return;
    }

    int id = 0;

    try {
      id = Integer.parseInt(params.get("id"));
    } catch (NumberFormatException e) {
      System.out.println("id를 정수 형태로 입력해주세요.");
      return;
    }

    if (articles.isEmpty()) {
      System.out.println("게시물이 존재하지 않습니다.");
      return;
    }

    if(id > articles.size()) {
      System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
      return;
    }

    Article article = articles.get(id - 1);

    System.out.println("== 게시물 상세보기 ==");
    System.out.printf("번호 : %d\n", article.id);
    System.out.printf("제목 : %s\n", article.title);
    System.out.printf("내용 : %s\n", article.content);
  }

  private static void actionUsrArticleList(Rq rq, List<Article> articles) {
    Map<String, String> params = rq.getParams();

    System.out.println("== 게시물 리스트 ==");
    System.out.println("번호 | 제목");

    boolean orderByIdDesc = true; // 역순 정렬에 대한 코드

    if(params.containsKey("orderBy") && params.get("orderBy").equals("idAsc")) {
      orderByIdDesc = false;
    }

    // 검색 시작
    // articles : 정렬 되지 않은 1 ~ 100개의 게시물
    List<Article> filteredArticles = articles;

    if(params.containsKey("searchKeyword")) {
      String searchKeyword = params.get("searchKeyword");

      filteredArticles = new ArrayList<>();

      for(Article article : articles) {
        if(article.title.contains(searchKeyword) || article.content.contains(searchKeyword)) {
          filteredArticles.add(article);
        }
      }
    }
    // 검색 끝

    // 정렬 시작
    List<Article> sortedArticles = filteredArticles;

    if(orderByIdDesc) {
      sortedArticles = Util.reverseList(sortedArticles);
    }
    // 정렬 끝

    // 게시물 리스트 출력
    for(Article article : sortedArticles) {
      System.out.printf("%d | %s\n", article.id, article.title);
    }

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

class Rq {
  String url;
  Map<String, String> params;
  String urlPath;

  Rq(String url) {
    this.url = url;
    params = Util.getParamsFromUrl(this.url);
    urlPath = Util.getUrlPathFromUrl(this.url);
  }

  public Map<String, String> getParams() {
    return params;
  }

  public String getUrlPath() {
    return urlPath;
  }
}

class Util {
  static Map<String, String> getParamsFromUrl(String url) {
    Map<String, String> params = new HashMap<>();
    String[] urlBits = url.split("\\?", 2);

    if(urlBits.length == 1) {
      return params;
    }

    String queryStr= urlBits[1];

    for(String bit : queryStr.split("&")) {
      String[] bits = bit.split("=", 2);

      if(bits.length == 1) {
        continue;
      }

      params.put(bits[0], bits[1]);
    }

    return params;
  }

  static String getUrlPathFromUrl(String url) {
    return url.split("\\?", 2)[0];
  }

  // 이 함수는 원본리스트를 훼손하지 않고, 새 리스트를 만듭니다. 즉 정렬이 반대인 복사본리스트를 만들어서 반환합니다.
  public static<T> List<T> reverseList(List<T> list) {
    List<T> reverse = new ArrayList<>(list.size());

    for ( int i = list.size() - 1; i >= 0; i-- ) {
      reverse.add(list.get(i));
    }
    return reverse;
  }
}