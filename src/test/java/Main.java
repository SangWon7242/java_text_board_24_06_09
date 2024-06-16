public class Main {
  public static void main(String[] args) {
    String queryString = "a=100&b=20&c=30&d=40&e=50";
    String[] queryStringBits = queryString.split("&");

    // 문제점 : 로직 자체 유연하지 못한다.
    // 왜? 파라미터가 추가되면 그거에 맞는 로직을 다시 수정해줘야 하기 때문에

    int a = 0;
    int b = 0;
    int c = 0;
    int d = 0;

    for(String bit : queryStringBits) {
      String[] bitBits = bit.split("=");

      String paramName = bitBits[0];
      String paramValue = bitBits[1];

      if(paramName.equals("a")) {
        a = Integer.parseInt(paramValue);
      } else if (paramName.equals("b")) {
        b = Integer.parseInt(paramValue);
      } else if (paramName.equals("c")) {
        c = Integer.parseInt(paramValue);
      } else if (paramName.equals("d")) {
        d = Integer.parseInt(paramValue);
      }
    }

    System.out.printf("a : %d\n", a);
    System.out.printf("b : %d\n", b);
    System.out.printf("c : %d\n", c);
    System.out.printf("d : %d\n", d);
  }
}
