package project01.handler;

import util.Prompt;

public class SpendsHandler {

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

  public static void inputSpend() {
    if (!available()) {
      System.out.println("더이상 입력할 수 없습니다!");
      return;
    }

    spend[length] = Prompt.inputString("지출명? ");
    price[length] = Prompt.inputString("금액? ");
    dailyNecessity[length] = Prompt.inputDaily((char)0);
    
    no[length] = userId++;
    length++;
  }

  public static void printSpends() {
    System.out.println("--------------------------------");
    System.out.println("번호, 지출명, 금액, 생필품");
    System.out.println("--------------------------------");

    for (int i = 0; i < length; i++) {
      System.out.printf("%d, %s, %s원, %s\n", 
        no[i], spend[i], price[i], 
        toDailyString(dailyNecessity[i]));
    }
  }
    
  public static void viewMember() {
    String memberNo = Prompt.inputString("번호? ");
    for (int i = 0; i < length; i++) {
      if (no[i] == Integer.parseInt(memberNo)) {
        System.out.printf("지출명: %s\n", spend[i]);
        System.out.printf("금액: %s\n", email[i]);
        System.out.printf("생필품 여부: %s\n", toDailyString(dailyNecessity[i]));
        return;
      }
    }
    System.out.println("해당 번호의 지출내역이 없습니다!");
  }

  public static String toDailyString(char Daily) {
    return Daily == 'Y' ? "Yes" : "No";
  }

  public static void updateSpend() {
    String spendNo = Prompt.inputString("번호? ");
    // 입력 받은 번호를 가지고 배열에서 해당 회원을 찾아야 한다.
    for (int i = 0; i < length; i++) {
      Spend s = spends[i];
      if (no[i] == Integer.parseInt(spendNo)) {
        // i 번째 항목에 저장된 회원 정보 출력
        System.out.printf("지출명(%s)? ", spend[i]);
        spend[i] = Prompt.inputString("");
        System.out.printf("금액(%s)? ", price[i]);
        price[i] = Prompt.inputString("");
        dailyNecessity[i] = inputDaily(dailyNecessity[i]);
        return;
      }
    }
    System.out.println("해당 번호의 지출내역이 없습니다!");
  }


  private static char inputDaily(char dailyNecessity) {
    String label;
    if (dailyNecessity == 0) {
      label = "생필품 여부?\n";
    } else {
      label = String.format("생필품 여부(%s)?\n", toDailyString(dailyNecessity));
    }
    loop: while (true) {
      String menuNo = Prompt.inputString(label + 
      "  1. Yes\n" + 
      "  2. No\n" + 
      "> ");

      switch (menuNo) {
        case "1":
          return Yes;
        case "2":
          return No;
        default:
          System.out.println("무효한 번호입니다.");
      }
    }
  }

  public static void deleteMember() {
    int spendNo = Prompt.inputInt("번호? ");

    int deletedIndex = indexOf(spendNo);
    if (deletedIndex == -1) {
      System.out.println("해당 번호의 지출내역이 없습니다!");
      return;
    }

    for (int i = deletedIndex; i < length - 1; i++) {
      no[i] = no[i + 1];
      spend[i] = spend[i + 1];
      price[i] = price[i + 1];
      dailyNecessity[i] = dailyNecessity[i + 1];
    }

    no[length - 1] = 0;
    spend[length - 1] = null;
    price[length - 1] = null;
    dailyNecessity[length - 1] = (char) 0;

    length--;
  }

  private static int indexOf(int spendNo) {
    for (int i = 0; i < length; i++) {
      if (no[i] == spendNo) {
        return i;
      }
    }
    return -1;
  }

  public static boolean available() {
    return length < MAX_SIZE;
  }
}