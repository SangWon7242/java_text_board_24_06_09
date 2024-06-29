package com.sbs.java.board;

public class Member {
  public int id;
  public String username;
  public String password;
  public String name;

  public Member(int id, String username, String password, String name) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.name = name;
  }

  @Override
  public String toString() {
    return "Member{" +
        "id=" + id +
        ", username='" + username + '\'' +
        ", password='" + password + '\'' +
        ", name='" + name + '\'' +
        '}';
  }
}
