import java.util.*;

public class Main {
  public static void main(String[] args) {
    String queryString1 = "id=33&hitCount=1&age=14&studentNo=65&name=Bob";
    Map<String, String> params1 = Util.getParams(queryString1);
    System.out.println(params1);

    String queryString2 = "id=15&hitCount=100";
    Map<String, String> params2 = Util.getParams(queryString2);
    System.out.println(params2);
  }
}

class Util {
  static Map<String, String> getParams(String queryStr) {
    Map<String, String> params = new HashMap<>();

    String[] queryStringBits1 = queryStr.split("&");

    for(String bit : queryStringBits1) {
      String[] bitBits = bit.split("=");

      params.put(bitBits[0], bitBits[1]);
    }

    return params;
  }
}