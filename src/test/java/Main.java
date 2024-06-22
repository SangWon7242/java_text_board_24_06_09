import java.util.*;

public class Main {
  public static void main(String[] args) {
    Rq rq = new Rq("/usr/article/list?page=2&searchKeyword=안녕? 나는");

    Map<String, String> params = rq.getParams();
    System.out.println(params);

    params = rq.getParams();
    System.out.println(params);

    String urlPath = rq.getUrlPath();
    System.out.println(urlPath);
  }
}

class Rq {
  String url;

  Rq(String url) {
    this.url = url;
  }

  public Map<String, String> getParams() {
    return Util.getParamsFromUrl(url);
  }

  public String getUrlPath() {
    return Util.getUrlPathFromUrl(url);
  }
}

class Util {
  static Map<String, String> getParamsFromUrl(String url) {
    System.out.println("getParamsFromUrl 실행");
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
    System.out.println("getUrlPathFromUrl 실행");
    return url.split("\\?", 2)[0];
  }
}