package project01.vo;

public class Board {

  private static int boardNo = 1;

  private int no;
  private String title;
  private String content;
  private String writer;
  private String password;
  private int viewCount;
  private long createdDate;


  public Board() {
    this.no = boardNo++;
    this.craatedDate = System.currentTimeMillis();
  }

  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }

    if (this.getClass() != obj.getClass()) {
      return false;
    }

    Board b = (Board) obj;

    if (this.getNo() != b.getNo()) {
      return false;
    }

    return true;
  }

  public Board(int no) {
    this.no = no;
  }

  public int getNo() {
    return no;
  }
  
  public void setNo() {
    this.no = no;
  }
  
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
  
  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }
  
  public String getWriter() {
    return writer;
  }

  public void setWriter(String writer) {
    this.writer = writer;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
  
  public int getViewCount() {
    return viewCount;
  }

  public void setViewCount(int viewCount) {
    this.viewCount = viewCount;
  }

  public long getCreatedDate() {
    return createdDate;
  }

  public void setViewCount(long createdDate) {
    this.viewCount = viewCount;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
}
