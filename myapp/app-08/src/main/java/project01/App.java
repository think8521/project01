package project01;

import java.util.Scanner;

public class App {

  static Scanner scanner = new Scanner(System.in);

  static final int MAX_SIZE = 100;
  static int userId = 1;
  static int length = 0;
  static int[] no = new int[MAX_SIZE];
  static String[] spend = new String[MAX_SIZE];
  static String[] price = new String[MAX_SIZE];
  static char[] dailyNecessity = new char[MAX_SIZE];
  static String[] YesOrNo = new String[MAX_SIZE];

  static final char Yes = 'Y';
  static final char No = 'N';

  public static void main(String[] args) {

    printTitle();

    while (length < MAX_SIZE) {
      inputSpend();
      if (!promptContinue()) {
        break;
      }
    }

    printSpends();

    scanner.close();
  }

  static void printTitle() {
    System.out.println("지출 내역 관리 시스템");
    System.out.println("----------------------------------");
  }

  static void inputSpend() {

    spend[length] = prompt("지출명? ");

    price[length] = prompt("금액? ");

    loop: while (true) {

      String menuNo = prompt("생필품 여부:\n" + "  1. Yes\n" + "  2. No\n" + "> ");

      switch (menuNo) {
        case "1":
          dailyNecessity[length] = Yes;
          YesOrNo[length] = "Yes";
          break loop;
        case "2":
          dailyNecessity[length] = No;
          YesOrNo[length] = "No";
          break loop;
        default:
          System.out.println("무효한 번호입니다.");
      }
    }

    no[length] = userId++;
    length++;
  }

  static boolean promptContinue() {
    System.out.print("계속 하시겠습니까?(Y/n) ");
    String response = scanner.nextLine();
    if (!response.equals("") && !response.equalsIgnoreCase("Y")) {
      return false;
    }
    return true;
  }

  static void printSpends() {
    System.out.println("--------------------------------");
    System.out.println("번호, 지출명, 금액, 생필품");
    System.out.println("--------------------------------");

    for (int i = 0; i < length; i++) {
      System.out.printf("%d, %s, %s원, %s\n", no[i], spend[i], price[i], YesOrNo[i]);
    }
  }

  static String prompt(String title) {
    System.out.print(title);
    return scanner.nextLine();
  }
}
