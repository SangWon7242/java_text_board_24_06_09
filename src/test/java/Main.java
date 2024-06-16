public class Main {
  public static void main(String[] args) {
    String queryString = "a=1&b=2&c=3";
    String[] queryStringBits = queryString.split("&");

    for(String bit : queryStringBits) {
      System.out.println(bit);
    }
  }
}
