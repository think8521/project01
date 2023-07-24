package think.myapp.vo;

import java.io.Serializable;

public class Spend implements Serializable, CsvObject, AutoIncrement {
  private static final long serialVersionUID = 1L;

  public static int userId = 1;

  public static final char Yes = 'Y';
  public static final char No = 'N';

  private int no;
  private String spend;
  private String price;
  private char dailyNecessity;


  public Spend() {}

  public Spend(int no) {
    this.no = no;
  }

  public static Spend fromCsv(String csv) {
    String[] values = csv.split(",");

    Spend spend = new Spend(Integer.parseInt(values[0]));
    spend.setSpend(values[1]);
    spend.setPrice(values[2]);
    spend.setDaily(values[3].charAt(0));

    if (Spend.userId <= spend.getNo()) {
      Spend.userId = spend.getNo() + 1;
    }

    return spend;
  }

  @Override
  public String toCsvString() {
    return String.format("%d,%s,%s,%c", this.getNo(), this.getSpend(), this.getPrice(),
        this.getDaily());
  }

  @Override
  public void updatekey() {
    if (Spend.userId <= this.no) {
      Spend.userId = this.no + 1;
    }
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
    return dailyNecessity;
  }

  public void setDaily(char dailyNecessity) {
    this.dailyNecessity = dailyNecessity;
  }

}
