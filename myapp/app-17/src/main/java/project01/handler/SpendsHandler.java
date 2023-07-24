package project01.handler;

import util.Prompt;
import project01.vo.Spend;

public class SpendsHandler implements Handler{

  private static final int MAX_SIZE = 100;

  private Prompt prompt;
  private Spend[] spends = new Spend[MAX_SIZE];
  private int length;
  private String title;

  public SpendsHandler(Prompt prompt, String title) {
    this.prompt = prompt;
    this.title = title;
  }

  public void execute() {
    printMenu();

    while (true) {
      String menuNo = prompt.inputString("%s> ", this.title);
      if (menuNo.equals("0")) {
        return;
      } else if (menuNo.equals("menu")) {
        printMenu();
      } else if (menuNo.equals("1")) {
        this.inputSpend();
      } else if (menuNo.equals("2")) {
        this.printSpends();
      } else if (menuNo.equals("3")) {
        this.viewSpend();
      } else if (menuNo.equals("4")) {
        this.updateSpend();
      } else if (menuNo.equals("5")) {
        this.deleteSpend();
      } else {
        System.out.println("메뉴 번호가 옳지 않습니다!");
      }
    }
  }

  private static void printMenu() {
    System.out.println("1. 등록");
    System.out.println("2. 목록");
    System.out.println("3. 조회");
    System.out.println("4. 변경");
    System.out.println("5. 삭제");
    System.out.println("0. 메인");
  }

  private void inputMember() {
    if (!this.available()) {
      System.out.println("더이상 입력할 수 없습니다!");
      return;
    }
  }

  public void inputSpend() {
    if (!this.available()) {
      System.out.println("더이상 입력할 수 없습니다!");
      return;
    }

    Spend s = new Spend();
    s.setSpend(this.prompt.inputString("지출명? "));
    s.setPrice(this.prompt.inputString("금액? "));
    s.setDaily(inputDaily((char)0));
    s.setNo(userId++);
    
    this.spends[this.length++] = s;
  }

  public static void printSpends() {
    System.out.println("--------------------------------");
    System.out.println("번호, 지출명, 금액, 생필품");
    System.out.println("--------------------------------");

    for (int i = 0; i < this.length; i++) {
      Spend s = this.spends[i];
      System.out.printf("%d, %s, %s원, %s\n", 
        s.getNo(), s.getSpend(), s.getPrice(), 
        toDailyString(s.getDaily()));
    }
  }
    
  public static void viewMember() {
    String spendNo = this.prompt.inputString("번호? ");
    for (int i = 0; i < length; i++) {
      Spend s = this.spends[i];
      if (s.getNo() == Integer.parseInt(spendNo)) {
        System.out.printf("지출명: %s\n", s.getSpend());
        System.out.printf("금액: %s\n", s.getPrice());
        System.out.printf("생필품 여부: %s\n", toDailyString(s.getDaily()));
        return;
      }
    }
    System.out.println("해당 번호의 지출내역이 없습니다!");
  }

  public static String toDailyString(char Daily) {
    return Daily == 'Y' ? "Yes" : "No";
  }

  public static void updateSpend() {
    String spendNo = this.prompt.inputString("번호? ");
    // 입력 받은 번호를 가지고 배열에서 해당 회원을 찾아야 한다.
    for (int i = 0; i < this.length; i++) {
      Spend s = this.spends[i];
      if (s.getNo() == Integer.parseInt(spendNo)) {
        // i 번째 항목에 저장된 회원 정보 출력
        s.setSpend(this.prompt.inputString("지출명(%s)? ", s.getSpend()));
        s.setPrice(this.prompt.inputString("금액(%s)? ", s.getPrice()));
        s.setDaily(inputDaily(s.getDailyNecessity()));
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
      String menuNo = this.prompt.inputString(label + 
      "  1. Yes\n" + 
      "  2. No\n" + 
      "> ");

      switch (menuNo) {
        case "1":
          return Spend.Yes;
        case "2":
          return Spend.No;
        default:
          System.out.println("무효한 번호입니다.");
      }
    }
  }

  public static void deleteSpend() {
    int spendNo = this.prompt.inputInt("번호? ");

    int deletedIndex = indexOf(spendNo);
    if (deletedIndex == -1) {
      System.out.println("해당 번호의 지출내역이 없습니다!");
      return;
    }

    for (int i = deletedIndex; i < this.length - 1; i++) {
      this.spends[i] = this.spends[i + 1];
    }

    this.spends[--this.length] = null;
  }

  private static int indexOf(int spendNo) {
    for (int i = 0; i < this.length; i++) {
      Spend s = spends[i];
      if (s.getNo() == spendNo) {
        return i;
      }
    }
    return -1;
  }

  public boolean available() {
    return this.length < MAX_SIZE;
  }
}
