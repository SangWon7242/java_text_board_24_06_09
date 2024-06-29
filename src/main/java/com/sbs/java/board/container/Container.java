package com.sbs.java.board.container;

import com.sbs.java.board.article.controller.ArticleController;

import java.util.Scanner;

// 전체적으로 공유되는 녀석
public class Container {
  public static Scanner sc;

  public static ArticleController articleController;

  static {
    sc = new Scanner(System.in);

    articleController = new ArticleController();
  }
}
