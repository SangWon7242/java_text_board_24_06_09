public class Main {
  public static void main(String[] args) {
    String queryString = "a=1&b=2&c=3";
    String[] queryStringBits = queryString.split("&");

    for(String bit : queryStringBits) {
      String[] bitBits = bit.split("=");

      String paramName = bitBits[0];
      String paramValue = bitBits[1];

      System.out.printf("%s : %s\n", paramName, paramValue);
    }
  }
}
