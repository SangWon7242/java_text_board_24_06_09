import java.util.*;

public class Main {
  public static void main(String[] args) {
    Map<String, String> params = Util.getParamsFromUrl("/usr/article/list?page=2&searchKeyword=안녕? 나는");
    System.out.println(params);
    System.out.println(params.get("page")); // 2
    System.out.println(params.get("searchKeyword")); // 안녕

    String urlPath = Util.getUrlPathFromUrl("/usr/article/list?page=2&searchKeyword=안녕? 나는");
    System.out.println(urlPath);

    urlPath = Util.getUrlPathFromUrl("/usr/article/list");
    System.out.println(urlPath);
  }
}

class Util {
  static Map<String, String> getParamsFromUrl(String url) {
    Map<String, String> params = new HashMap<>();
    String[] urlBits = url.split("\\?", 2);

    if(urlBits.length == 1) {
      return params;
    }

    String queryStr= urlBits[1];

    for(String bit : queryStr.split("&")) {
      String[] bits = bit.split("=", 2);

      if(bits.length == 1) {
        continue;
      }

      params.put(bits[0], bits[1]);
    }

    return params;
  }

  static String getUrlPathFromUrl(String url) {
    return url.split("\\?", 2)[0];
  }
}