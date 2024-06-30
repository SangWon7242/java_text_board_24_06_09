package com.sbs.java.board.article.repository;

import com.sbs.java.board.article.entity.Article;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ArticleRepository {
  private int lastId;

  @Getter
  private List<Article> articles;

  public ArticleRepository() {
    lastId = 0;
    articles = new ArrayList<>();

    makeTestData();

    if (!articles.isEmpty()) {
      lastId = articles.get(articles.size() - 1).getId();
    }
  }

  void makeTestData() {
    IntStream.rangeClosed(1, 100)
        .forEach(i -> articles.add(new Article(i, "제목" + i, "내용" + i)));
  }

  public void write(String title, String content) {
    articles.add(new Article(lastId, title, content));
  }

  public Article findById(int id) {
    Article article = articles
        .stream()
        .filter(a -> a.getId() == id) // 해당 녀석이 참인 것만 필터링
        .findFirst() // 필터링 결과가 하나만 남는데, 그 하나 남을 가져온다.
        .orElse(null);

    return article;
  }

  public void remove(Article article) {
    articles.remove(article);
  }
}
