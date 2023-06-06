package project01;

import java.util.Scanner;

public class App {
  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);

    System.out.println("지출 내역 관리 시스템");
    System.out.println("----------------------------------");

    final int MAX_SIZE = 100;
    int userId = 1;
    int length = 0;

    int[] no = new int[MAX_SIZE];
    String[] spend = new String[MAX_SIZE];
    int[] price = new int[MAX_SIZE];
    char[] dailyNecessity = new char[MAX_SIZE];

    for (int i = 0; i < MAX_SIZE; i++) {

      System.out.printf("지출명? ");
      spend[i] = scanner.next();

      System.out.printf("금액? ");
      price[i] = scanner.nextInt();

      loop: while (true) {

        System.out.println("생필품 여부: ");
        System.out.println("  1. Yes");
        System.out.println("  2. No");
        System.out.println("> ");
        String menuNo = scanner.next();

        switch (menuNo) {
          case "1":
            dailyNecessity[i] = 'Y';
            break loop;
          case "2":
            dailyNecessity[i] = 'n';
            break loop;
          default:
            System.out.println("무효한 번호입니다.");
        }
      }

      no[i] = userId++;

      length++;

      System.out.print("계속 하시겠습니다?(Y/n) ");
      scanner.nextLine();
      String response = scanner.nextLine();
      if (!response.equals("") && !response.equalsIgnoreCase("Y")) {
        break;
      }

    }

    System.out.println("-------------------------------");

    System.out.println("번호, 지출명, 금액, 생필품 여부");
    System.out.println("-------------------------------");

    for (int i = 0; i < length; i++) {
      System.out.printf("%d, %s, %d원, %c\n", no[i], spend[i], price[i], dailyNecessity[i]);
    }

    scanner.close();
  }
}
