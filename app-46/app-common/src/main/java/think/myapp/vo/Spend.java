package think.myapp.vo;

import java.io.Serializable;

public class Spend implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final char Yes = 'Y';
  public static final char No = 'N';

  private int no;
  private String spend;
  private String price;
  private char daily;
  private String user;
  private String id;
  private String password;


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

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public char getDaily() {
    return daily;
  }

  public void setDaily(char daily) {
    this.daily = daily;
  }

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


}
