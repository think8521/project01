package project01.handler;

import util.Prompt;

public class SpendsHandler {

  static final int MAX_SIZE = 100;
  static int[] no = new int[MAX_SIZE];
  static String[] spend = new String[MAX_SIZE];
  static String[] price = new String[MAX_SIZE];
  static char[] dailyNecessity = new char[MAX_SIZE];
  static String[] YesOrNo = new String[MAX_SIZE];
  static int userId = 1;
  static int length = 0;
  
  static final char Yes = 'Y';
  static final char No = 'N';

  public static void inputSpend() {

    spend[length] = Prompt.inputString("지출명? ");

    price[length] = Prompt.inputString("금액? ");

    loop: while (true) {

      String menuNo = Prompt.inputString("생필품 여부:\n" + 
      "  1. Yes\n" + 
      "  2. No\n" + 
      "> ");

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

  public static void printSpends() {
    System.out.println("--------------------------------");
    System.out.println("번호, 지출명, 금액, 생필품");
    System.out.println("--------------------------------");

    for (int i = 0; i < length; i++) {
      System.out.printf("%d, %s, %s원, %s\n", no[i], spend[i], price[i], YesOrNo[i]);
    }
  }

  public static boolean available() {
    return length < MAX_SIZE;
  }
}