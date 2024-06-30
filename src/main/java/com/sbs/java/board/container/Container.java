package com.sbs.java.board.container;

import com.sbs.java.board.article.controller.ArticleController;
import com.sbs.java.board.article.repository.ArticleRepository;
import com.sbs.java.board.article.service.ArticleService;
import com.sbs.java.board.member.controller.MemberController;
import com.sbs.java.board.member.repository.MemberRepository;
import com.sbs.java.board.member.service.MemberService;
import com.sbs.java.board.session.Session;
import lombok.Getter;

import java.util.Scanner;

// 전체적으로 공유되는 녀석
public class Container {
  public static Scanner sc;

  @Getter
  public static Session session;

  public static MemberRepository memberRepository;
  public static ArticleRepository articleRepository;

  public static MemberService memberService;
  public static ArticleService articleService;

  public static MemberController memberController;
  public static ArticleController articleController;

  static {
    sc = new Scanner(System.in);
    session = new Session();

    memberRepository = new MemberRepository();
    articleRepository = new ArticleRepository();

    memberService = new MemberService();
    articleService = new ArticleService();

    memberController = new MemberController();
    articleController = new ArticleController();
  }
}
