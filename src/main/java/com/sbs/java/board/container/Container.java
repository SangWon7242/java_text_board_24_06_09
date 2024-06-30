package com.sbs.java.board.container;

import com.sbs.java.board.article.controller.ArticleController;
import com.sbs.java.board.member.controller.MemberController;
import com.sbs.java.board.session.Session;

import java.util.Scanner;

// 전체적으로 공유되는 녀석
public class Container {
  public static Scanner sc;
  public static Session session;

  public static MemberController memberController;
  public static ArticleController articleController;

  static {
    sc = new Scanner(System.in);
    session = new Session();

    memberController = new MemberController();
    articleController = new ArticleController();
  }

  public static Session getSession() {
    return session;
  }
}
