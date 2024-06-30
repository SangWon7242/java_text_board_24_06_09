package com.sbs.java.board.member.controller;

import com.sbs.java.board.Member;
import com.sbs.java.board.Rq;
import com.sbs.java.board.container.Container;

import java.util.ArrayList;
import java.util.List;

public class MemberController {
  int memberLastId;
  List<Member> members;

  public MemberController() {
    memberLastId = 0;
    members = new ArrayList<>();

    makeTestData();

    if (!members.isEmpty()) {
      memberLastId = members.get(members.size() - 1).getId();
    }
  }

  void makeTestData() {
    members.add(new Member(1, "user1", "1234", "장발장"));
    members.add(new Member(2, "user2", "1234", "신짱구"));
    members.add(new Member(3, "user3", "1234", "김철수"));
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

    System.out.printf("\"%s\"님 회원 가입 되었습니다.\n", member.getName());
  }

  public void actionLogin(Rq rq) {
    boolean isLogined = rq.isLogined();

    if(isLogined) {
      System.out.println("이미 로그인 상태입니다.");
      return;
    }

    String username;
    String password;
    Member member;

    System.out.println("== 로그인 ==");

    // username 입력 시작
    while (true) {
      System.out.print("로그인 아이디 : ");
      username = Container.sc.nextLine();

      if(username.trim().isEmpty()) {
        System.out.println("username(을)를 입력해주세요.");
        continue;
      }

      member = memberFindByUserName(username);

      if(member == null) {
        System.out.printf("\"%s\"(은)는 없는 username 입니다.\n", username);
        continue;
      }

      break;
    }
    // username 입력 끝

    int tryPasswordCount = 0;
    int tryPasswordMaxCount = 3;

    // password 입력 시작
    while (true) {
      if(tryPasswordCount >= tryPasswordMaxCount) {
        System.out.println("비밀번호를 다시 확인 후 입력해주세요.");
        return;
      }

      System.out.print("비밀번호 : ");
      password = Container.sc.nextLine();

      if(password.trim().isEmpty()) {
        System.out.println("password(을)를 입력해주세요.");
        continue;
      }

      if(!member.getPassword().equals(password)) {
        tryPasswordCount++;

        System.out.printf("비밀번호가 일치하지 않습니다.(틀린 횟수 : %d/%d)\n", tryPasswordCount, tryPasswordMaxCount);
        continue;
      }

      break;
    }
    // password 입력 끝

    rq.login("loginedMember", member);

    System.out.printf("\"%s\"님 로그인 되었습니다.\n", member.getUsername());
  }

  private Member memberFindByUserName(String username) {
    Member member = members
        .stream()
        .filter(m -> m.getUsername().equals(username)) // 해당 녀석이 참인 것만 필터링
        .findFirst() // 필터링 결과가 하나만 남는데, 그 하나 남을 가져온다.
        .orElse(null);

    return member;
  }

  public void actionLogout(Rq rq) {
    if(rq.isNotLogined()) {
      System.out.println("이미 로그아웃 상태입니다.");
      return;
    }

    rq.logout(); // 로그아웃 처리

    System.out.println("로그아웃 되었습니다.");
  }
}
