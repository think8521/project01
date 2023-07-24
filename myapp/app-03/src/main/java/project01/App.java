
package project01;

public class App {
  public static void main(String[] args) {

    System.out.println("지출 내역 관리 시스템");
    System.out.println("----------------------------------");

    int no = 100;
    String spend = "아이스 아메리카노";
    int price = 1400;
    char dailyNecessity = 'n';

    System.out.printf("번호: %d\n", no);

    System.out.printf("지출명: %s\n", spend);

    System.out.printf("금액: %d 원\n", price);

    System.out.printf("생필품: (Yes(Y), No(n)): %c\n", dailyNecessity);

  }
}
