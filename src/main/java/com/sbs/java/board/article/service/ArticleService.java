package com.sbs.java.board.article.service;

import com.sbs.java.board.article.entity.Article;
import com.sbs.java.board.article.repository.ArticleRepository;
import com.sbs.java.board.container.Container;

import java.util.List;

public class ArticleService {
  private ArticleRepository articleRepository;

  public ArticleService() {
    articleRepository = Container.articleRepository;
  }

  public List<Article> getArticles() {
    return articleRepository.getArticles();
  }

  public void write(String title, String content) {
    articleRepository.write(title, content);
  }

  public Article findById(int id) {
    return articleRepository.findById(id);
  }

  public void remove(Article article) {
    articleRepository.remove(article);
  }
}
