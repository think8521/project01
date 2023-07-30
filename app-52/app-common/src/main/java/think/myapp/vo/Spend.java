package think.myapp.vo;

import java.io.Serializable;
import java.sql.Timestamp;

public class Spend implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final char Yes = 'Y';
  public static final char No = 'N';

  private int no;
  private String spend;
  private int price;
  private char daily;
  private Member who;
  private Timestamp createdDate;
  private int viewCount;



  public Spend() {}

  public Spend(int no) {
    this.no = no;
  }

  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }

    if (this.getClass() != obj.getClass()) {
      return false;
    }

    Spend s = (Spend) obj;

    if (this.getNo() != s.getNo()) {
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

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public char getDaily() {
    return daily;
  }

  public void setDaily(char daily) {
    this.daily = daily;
  }

  public Member getWho() {
    return who;
  }

  public void setWho(Member who) {
    this.who = who;
  }

  public Timestamp getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Timestamp createdDate) {
    this.createdDate = createdDate;
  }

  public int getViewCount() {
    return viewCount;
  }

  public void setViewCount(int viewCount) {
    this.viewCount = viewCount;
  }


}
