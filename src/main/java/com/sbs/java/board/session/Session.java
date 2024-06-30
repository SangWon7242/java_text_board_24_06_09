package com.sbs.java.board.session;

import java.util.HashMap;
import java.util.Map;

public class Session {
  private Map<String, Object> sessionStore;

  public Session() {
    sessionStore = new HashMap<>();
  }

  public void setAttribute(String attrName, Object value) {
    sessionStore.put(attrName, value);
  }

  public Object getAttribute(String attrName) {
   return sessionStore.get(attrName);
  }

  public boolean hasAttribute(String attrName) {
    return sessionStore.containsKey(attrName);
  }

  public void removeAttribute(String attrName) {
    sessionStore.remove(attrName);
  }
}
