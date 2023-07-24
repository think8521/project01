package project01;

import util.Prompt;
import project01.handler.SpendsHandler;

public class App {

  public static void main(String[] args) {

    printTitle();

    while (SpendsHandler.available()) {
      SpendsHandler.inputSpend();
      if (!Prompt.promptContinue()) {
        break;
      }
    }

    SpendsHandler.printSpends();

    Prompt.close();
  }

  static void printTitle() {
    System.out.println("지출 내역 관리 시스템");
    System.out.println("----------------------------------");
  }

}
