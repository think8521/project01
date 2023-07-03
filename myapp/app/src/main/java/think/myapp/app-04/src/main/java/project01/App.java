package project01;

import java.util.Scanner;

public class App {
  public static void main(String[] args) {

    System.out.println("지출 내역 관리 시스템");
    System.out.println("----------------------------------");

    Scanner scanner = new Scanner(System.in);

    System.out.printf("번호? ");
    int no = scanner.nextInt();

    System.out.printf("지출명?");
    String spend = scanner.next();

    System.out.printf("금액? ");
    int price = scanner.nextInt();

    System.out.printf("생필품(Yes(Y), No(n))? ");
    String str = scanner.next();
    char dailyNecessity = str.charAt(0);

    System.out.println("-------------------------------");

    System.out.printf("번호: %d\n", no);
    System.out.printf("지출명: %s\n", spend);
    System.out.printf("금액: %d 원\n", price);
    System.out.printf("생필품: (Yes(Y), No(n)): %c\n", dailyNecessity);

    scanner.close();
  }
}
