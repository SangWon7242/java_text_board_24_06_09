package com.sbs.java.board.member.controller;

import com.sbs.java.board.Article;
import com.sbs.java.board.Member;
import com.sbs.java.board.container.Container;

import java.util.ArrayList;
import java.util.List;

public class MemberController {
  int memberLastId;
  List<Member> members;

  public MemberController() {
    memberLastId = 0;
    members = new ArrayList<>();
  }

  public void actionJoin() {
    String username;
    String password;
    String passwordConfirm;
    String name;

    System.out.println("== 회원 가입 ==");

    // username 입력 시작
    while (true) {
      System.out.print("로그인 아이디 : ");
      username = Container.sc.nextLine();

      if(username.trim().isEmpty()) {
        System.out.println("username(을)를 입력해주세요.");
        continue;
      }

      break;
    }
    // username 입력 끝

    // password 입력 시작
    while (true) {
      System.out.print("비밀번호 : ");
      password = Container.sc.nextLine();

      if(password.trim().isEmpty()) {
        System.out.println("password(을)를 입력해주세요.");
        continue;
      }

      while (true) {
        System.out.print("비밀번호 확인 : ");
        passwordConfirm = Container.sc.nextLine();

        if(passwordConfirm.trim().isEmpty()) {
          System.out.println("passwordConfirm(을)를 입력해주세요.");
          continue;
        }

        if(!passwordConfirm.equals(password)) {
          System.out.println("비밀번호가 일치하지 않습니다.");
          continue;
        }

        break;
      }

      break;
    }
    // password 입력 끝

    // name 입력 시작
    while (true) {
      System.out.print("이름 : ");
      name = Container.sc.nextLine();

      if(name.trim().isEmpty()) {
        System.out.println("name(을)를 입력해주세요.");
        continue;
      }

      break;
    }
    // name 입력 끝

    int id = ++memberLastId;

    Member member = new Member(id, username, password, name);

    members.add(member);

    System.out.printf("\"%s\"님 회원 가입 되었습니다.\n", member.name);
  }
}
