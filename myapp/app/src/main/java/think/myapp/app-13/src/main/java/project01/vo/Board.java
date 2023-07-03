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

  public int getNo() {
    return no;
  }
  
  public void setNo() {
    this.no = no;
  }
  
  public String getTitle() {
    return title;
  }

  public void setTitle() {
    this.title = title;
  }
  
  public String getContent() {
    return content;
  }

  public void setContent() {
    this.content = content;
  }
  
  public String getWriter() {
    return writer;
  }

  public void setWriter() {
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

  public void setViewCount() {
    this.viewCount = viewCount;
  }

  public long getCreatedDate() {
    return createdDate;
  }

  public void setViewCount(long createdDate) {
    this.viewCount = viewCount;
  }
}
