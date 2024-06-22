import java.util.ArrayList;
import java.util.List;

public class Main {
  public static void main(String[] args) {
    List<Article> articles = new ArrayList<>();
    articles.add(new Article(1, "제목1", "내용1"));
    articles.add(new Article(2, "안녕하세요.", "반가워요"));
    articles.add(new Article(3, "자바는 할만한가요?", "자바는 어렵습니다."));

    String searchKeyword = "1";

    List<Article> filteredArticles = new ArrayList<>();

    for(Article article : articles) {
      if(article.title.contains(searchKeyword) || article.content.contains(searchKeyword)) {
        filteredArticles.add(article);
      }
    }

    System.out.println(filteredArticles);

  }
}

class Article {
  int id;
  String title;
  String content;

  Article(int id, String title, String content) {
    this.id = id;
    this.title = title;
    this.content = content;
  }

  @Override
  public String toString() {
    return "{id: %d, title: \"%s\", body: \"%s\"}".formatted(id, title, content);
  }
}