package com.sbs.java.board;

import com.sbs.java.board.article.controller.ArticleController;
import com.sbs.java.board.container.Container;
import com.sbs.java.board.member.controller.MemberController;

public class App {
  MemberController memberController;
  ArticleController articleController;

  public App() {
    memberController = Container.memberController;
    articleController = Container.articleController;
  }

  public void run() {
    System.out.println("== 자바 텍스트 게시판 ==");

    while (true) {
      System.out.print("명령) ");
      String cmd = Container.sc.nextLine();

      Rq rq = new Rq(cmd);

      if (rq.getUrlPath().equals("/usr/article/write")) {
        articleController.actionWrite();
      } else if (rq.getUrlPath().equals("/usr/article/detail")) {
        articleController.showDetail(rq);
      } else if (rq.getUrlPath().equals("/usr/article/list")) {
        articleController.showList(rq);
      } else if (rq.getUrlPath().equals("/usr/article/modify")) {
        articleController.actionModify(rq);
      } else if (rq.getUrlPath().equals("/usr/article/delete")) {
        articleController.actionDelete(rq);
      } else if (rq.getUrlPath().equals("/usr/member/join")) {
        memberController.actionJoin();
      } else if (rq.getUrlPath().equals("/usr/member/login")) {
        memberController.actionLogin();
      } else if (cmd.equals("exit")) {
        System.out.println("== 자바 텍스트 게시판 종료 ==");
        break;
      } else {
        System.out.println("올바른 명령어가 아닙니다.");
      }
    }

    Container.sc.close();
  }
}
