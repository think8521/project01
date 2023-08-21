package project01.handler;

import util.Prompt;
import project01.vo.Spend;

public class SpendsHandler {

  static final int MAX_SIZE = 100;
  static Spend[] spends = new Spend[MAX_SIZE];
  static int userId = 1;
  static int length = 0;

  static final char Yes = 'Y';
  static final char No = 'N';

  public static void inputSpend() {
    if (!available()) {
      System.out.println("더이상 입력할 수 없습니다!");
      return;
    }

    Spend s = new Spend();
    s.spend = Prompt.inputString("지출명? ");
    s.price = Prompt.inputString("금액? ");
    s.dailyNecessity = inputDaily((char)0);
    s.no = userId++;
    
    spends[length++] = s;
  }

  public static void printSpends() {
    System.out.println("--------------------------------");
    System.out.println("번호, 지출명, 금액, 생필품");
    System.out.println("--------------------------------");

    for (int i = 0; i < length; i++) {
      Spend s = spends[i];
      System.out.printf("%d, %s, %s원, %s\n", 
        s.no, s.spend, s.price, 
        toDailyString(s.dailyNecessity));
    }
  }
    
  public static void viewMember() {
    String spendNo = Prompt.inputString("번호? ");
    for (int i = 0; i < length; i++) {
      Spend s = spends[i];
      if (s.no == Integer.parseInt(spendNo)) {
        System.out.printf("지출명: %s\n", s.spend);
        System.out.printf("금액: %s\n", s.email);
        System.out.printf("생필품 여부: %s\n", toDailyString(s.dailyNecessity));
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
      if (s.no == Integer.parseInt(spendNo)) {
        // i 번째 항목에 저장된 회원 정보 출력
        System.out.printf("지출명(%s)? ", s.spend);
        s.spend = Prompt.inputString("");
        System.out.printf("금액(%s)? ", s.price);
        s.price = Prompt.inputString("");
        s.dailyNecessity = inputDaily(s.dailyNecessity);
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

  public static void deleteSpend() {
    int spendNo = Prompt.inputInt("번호? ");

    int deletedIndex = indexOf(spendNo);
    if (deletedIndex == -1) {
      System.out.println("해당 번호의 지출내역이 없습니다!");
      return;
    }

    for (int i = deletedIndex; i < length - 1; i++) {
      spends[i] = spends[i + 1];
    }

    spends[--length] = null;
  }

  private static int indexOf(int spendNo) {
    for (int i = 0; i < length; i++) {
      Spend s = spends[i];
      if (s.no == spendNo) {
        return i;
      }
    }
    return -1;
  }

  public static boolean available() {
    return length < MAX_SIZE;
  }
}