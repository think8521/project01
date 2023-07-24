package think.myapp.handler;

import think.myapp.vo.Spend;
import think.util.List;
import think.util.Prompt;

public class SpendsHandler implements Handler {

  private List list;
  private Prompt prompt;
  private String title;

  public SpendsHandler(Prompt prompt, String title, List list) {
    this.prompt = prompt;
    this.title = title;
    this.list = list;
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

  private void inputSpend() {
    Spend s = new Spend();
    s.setSpend(this.prompt.inputString("지출명? "));
    s.setPrice(this.prompt.inputString("금액? "));
    s.setDaily(inputDaily((char) 0));

    this.list.add(s);
  }

  private void printSpends() {
    System.out.println("--------------------------------");
    System.out.println("번호, 지출명, 금액, 생필품");
    System.out.println("--------------------------------");

    for (int i = 0; i < this.list.size(); i++) {
      Spend s = (Spend) this.list.get(i);
      System.out.printf("%d, %s, %s원, %s\n", s.getNo(), s.getSpend(), s.getPrice(),
          toDailyString(s.getDaily()));
    }
  }

  private void viewSpend() {
    int spendNo = this.prompt.inputInt("번호? ");

    Spend s = this.findBy(spendNo);
    if (s == null) {
      System.out.println("해당 번호의 지출내역이 없습니다!");
      return;
    }

    System.out.printf("지출명: %s\n", s.getSpend());
    System.out.printf("금액: %s\n", s.getPrice());
    System.out.printf("생필품 여부: %s\n", toDailyString(s.getDaily()));
  }

  private static String toDailyString(char Daily) {
    return Daily == 'Y' ? "Yes" : "No";
  }

  private void updateSpend() {
    int spendNo = this.prompt.inputInt("번호? ");

    Spend s = this.findBy(spendNo);
    if (s == null) {
      System.out.println("해당 번호의 지출내역이 없습니다!");
      return;
    }

    s.setSpend(this.prompt.inputString("지출명(%s)? ", s.getSpend()));
    s.setPrice(this.prompt.inputString("금액(%s)? ", s.getPrice()));
    s.setDaily(inputDaily(s.getDaily()));

  }


  private char inputDaily(char dailyNecessity) {
    String label;
    if (dailyNecessity == 0) {
      label = "생필품 여부?\n";
    } else {
      label = String.format("생필품 여부(%s)?\n", toDailyString(dailyNecessity));
    }
    while (true) {
      String menuNo = this.prompt.inputString(label + "  1. Yes\n" + "  2. No\n" + "> ");

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

  private void deleteSpend() {
    if (!this.list.remove(new Spend(this.prompt.inputInt("번호? ")))) {
      System.out.println("해당 번호의 지출내역이 없습니다!");
    }
  }

  private Spend findBy(int no) {
    for (int i = 0; i < this.list.size(); i++) {
      Spend s = (Spend) this.list.get(i);
      if (s.getNo() == no) {
        return s;
      }
    }
    return null;
  }
}
