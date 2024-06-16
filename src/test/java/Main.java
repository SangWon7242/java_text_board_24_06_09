import java.util.ArrayList;
import java.util.List;

public class Main {
  public static void main(String[] args) {
    // 압축 된 데이터
    String queryString = "count=1&id=33&age=14&studentNo=65";
    
    // 압축 해제 시작
    String[] queryStringBits = queryString.split("&");

    List<String> paramNames = new ArrayList<>();
    List<Integer> paramValues = new ArrayList<>();

    for(String bit : queryStringBits) {
      String[] bitBits = bit.split("=");

      paramNames.add(bitBits[0]);
      paramValues.add(Integer.parseInt(bitBits[1]));
    }
    // 압축 해제 끝

    System.out.println(paramNames);
    System.out.println(paramValues);

    int findIndex = paramNames.indexOf("id");
    System.out.printf("id : %d\n", paramValues.get(findIndex));
  }
}
