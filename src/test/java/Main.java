import java.util.*;

public class Main {
  public static void main(String[] args) {
    Map<String, String> params = Util.getParams("id=33&hitCount=1&name=Bob&calc=[1=2]");
    System.out.println(params);
    System.out.println(params.get("id")); // 33
    System.out.println(params.get("name")); // Bob
    System.out.println(params.get("calc"));
  }
}

class Util {
  static Map<String, String> getParams(String queryStr) {
    Map<String, String> params = new HashMap<>();

    for(String bit : queryStr.split("&")) {
      String[] bits = bit.split("=", 2);

      if(bits.length == 1) {
        continue;
      }

      params.put(bits[0], bits[1]);
    }

    return params;
  }
}