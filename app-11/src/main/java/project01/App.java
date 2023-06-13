package project01;

import util.Prompt;
import project01.handler.SpendsHandler;

public class App {

  public static void main(String[] args) {

    printTitle();

    printMenu();

    while (true) {
      String menuNo = Prompt.inputString("메인> ");
      if (menuNo.equals("6")) {
        break;
      } else if (menuNo.equals("menu")) {
        printMenu();
      } else if (menuNo.equals("1")) {
        SpendsHandler.inputSpend();
      } else if (menuNo.equals("2")) {
        SpendsHandler.printSpends();
      } else if (menuNo.equals("3")) {
        SpendsHandler.viewSpend();
      } else if (menuNo.equals("4")) {
        SpendsHandler.updateSpend();
      } else if (menuNo.equals("5")) {
        SpendsHandler.deleteSpend();
      } else {
        System.out.println(menuNo);
      }
    }

    // while (SpendsHandler.available()) {
    //   SpendsHandler.inputSpend();
    //   if (!Prompt.promptContinue()) {
    //     break;
    //   }
    // }

  //   SpendsHandler.printSpends();

    Prompt.close();
  }

  static void printMenu() {
    System.out.println("1. 지출등록");
    System.out.println("2. 지출목록");
    System.out.println("3. 지출조회");
    System.out.println("4. 지출변경");
    System.out.println("5. 지출삭제");
    System.out.println("6. 종료");
  }

  static void printTitle() {
    System.out.println("지출 내역 관리 시스템");
    System.out.println("----------------------------------");
  }

}
