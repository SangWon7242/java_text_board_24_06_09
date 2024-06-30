package com.sbs.java.board;

import com.sbs.java.board.container.Container;
import com.sbs.java.board.session.Session;

import java.util.Map;

public class Rq {
  private String url;
  private Map<String, String> params;
  private String urlPath;
  private Session session;

  Rq(String url) {
    this.url = url;
    params = Util.getParamsFromUrl(this.url);
    urlPath = Util.getUrlPathFromUrl(this.url);

    session = Container.session;
  }

  public Map<String, String> getParams() {
    return params;
  }

  public String getUrlPath() {
    return urlPath;
  }

  public int getIntParam(String paramName, int defaultValue) {
    if(!params.containsKey(paramName)) return defaultValue;

    try {
      return Integer.parseInt(params.get(paramName));
    }
    catch (NumberFormatException e) {
      return defaultValue;
    }
  }

  public String getParam(String paramName, String defaultValue) {
    if(!params.containsKey(paramName)) return defaultValue;

    return params.get(paramName);
  }

  public void setSessionAttr(String attrName, Object value) {
    session.setAttribute(attrName, value);
  }

  public Object getSessionAttr(String attrName) {
    return session.getAttribute(attrName);
  }

  public boolean hasSessionAttr(String attrName) {
    return session.hasAttribute(attrName);
  }

  public void removeSessionAttr(String attrName) {
    session.removeAttribute(attrName);
  }


  // 로그인이 된 경우 : true
  // 로그인이 안 된 경우 : false
  public boolean isLogined() {
    return hasSessionAttr("loginedMember");
  }

  public boolean isNotLogined() {
    return !isLogined();
  }
  
  // 로그인 : 로그인 정보를 세션에 저장
  public void login(String attrName, Object value) {
    session.setAttribute(attrName, value);
  }

  // 로그아웃 : 로그인 정보를 세션에서 삭제
  public void logout() {
    session.removeAttribute("loginedMember");
  }
}