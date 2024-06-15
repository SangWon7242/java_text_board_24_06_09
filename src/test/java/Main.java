public class Main {
  public static void main(String[] args) {
    Article article = new Article(1, "제목", "내용");
    System.out.println(article);

    Article article2 = new Article();
  }
}

class Article {
  int id;
  String title;
  String content;

  Article() {

  }

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
