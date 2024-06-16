import java.util.*;

public class Main {
  public static void main(String[] args) {
    // 압축 된 데이터
    String queryString = "id=33&hitCount=1&age=14&studentNo=65&name=Bob";
    
    // 압축 해제 시작
    String[] queryStringBits = queryString.split("&");

    Map<String, String> params = new HashMap<>(); // 순서를 보장x
    // Map<String, String> params = new LinkedHashMap<>(); // 순서를 보장o

    for(String bit : queryStringBits) {
      String[] bitBits = bit.split("=");

      params.put(bitBits[0], bitBits[1]);
    }
    // 압축 해제 끝

    System.out.println("== 원하는 것을 하나하나 가져와서 사용 ==");
    System.out.printf("id : %d\n", Integer.parseInt(params.get("id")));
    System.out.printf("hitCount : %d\n", Integer.parseInt(params.get("hitCount")));
    System.out.printf("age : %d\n", Integer.parseInt(params.get("age")));
    System.out.printf("studentNo : %d\n", Integer.parseInt(params.get("studentNo")));
    System.out.printf("name : %s\n", params.get("name"));

    System.out.println("== 반복문을 이용한 전체 출력 ==");
    for(String paramName : params.keySet()) {
      String paramValue = params.get(paramName);
      System.out.printf("%s : %s\n", paramName, paramValue);
    }
  }
}
