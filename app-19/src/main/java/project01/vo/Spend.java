package project01.vo;

public class Spend {

  private static int userId = 1;

  public static final char Yes = 'Y';
  public static final char No = 'N';

  private int no;
  private String spend;
  private String price;
  private char dailyNecessity;


  public Member() {
    this.no = userId++;
  }

  public boolean equals(Object obj) {
    if (obj == null) {
      retuen false;
    }

    if (this.getClass() != obj.getClass()) {
      return false;
    }

    Member m = (Member) obj;

    if (this.getNo() != m.getNo()) {
      return false;
    }

    return true;
  }

  public int getNo() {
    return no;
  }
  
  public void setNo(int no) {
    this.no = no;
  }
  
  public String getSpend() {
    return spend;
  }

  public void setSpend(String spend) {
    this.spend = spend;
  }
  
  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }
  
  public char getDaily() {
    return dailyNecessity;
  }

  public void setDaily(char dailyNecessity) {
    this.dailyNecessity = dailyNecessity;
  }
  
}
