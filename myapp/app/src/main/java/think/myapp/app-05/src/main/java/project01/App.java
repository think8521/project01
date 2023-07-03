package project01;

import java.util.Scanner;

public class App {
  public static void main(String[] args) {

    System.out.println("지출 내역 관리 시스템");
    System.out.println("----------------------------------");

    Scanner scanner = new Scanner(System.in);

    final int SIZE = 100;

    int[] no = new int[SIZE];
    String[] spend = new String[SIZE];
    int[] price = new int[SIZE];
    char[] dailyNecessity = new char[SIZE];

    for (int i = 0; i < SIZE; i++) {
      System.out.printf("번호? ");
      no[i] = scanner.nextInt();

      System.out.printf("지출명?");
      spend[i] = scanner.next();

      System.out.printf("금액? ");
      price[i] = scanner.nextInt();

      System.out.printf("생필품(Yes(Y), No(n))? ");
      String str = scanner.next();
      dailyNecessity[i] = str.charAt(0);
    }

    System.out.println("-------------------------------");

    for (int i = 0; i < SIZE; i++) {
      System.out.printf("번호: %d\n", no[i]);
      System.out.printf("지출명: %s\n", spend[i]);
      System.out.printf("금액: %d 원\n", price[i]);
      System.out.printf("생필품: (Yes(Y), No(n)): %c\n", dailyNecessity[i]);
    }

    scanner.close();
  }
}
