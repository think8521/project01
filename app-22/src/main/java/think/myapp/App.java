package think.myapp;

import think.myapp.handler.BoardHandler;
import think.myapp.handler.SpendsHandler;
import think.util.ArrayList;
import think.util.LinkedList;
import think.util.MenuPrompt;

public class App {

  public static void main(String[] args) {


    MenuPrompt prompt = new MenuPrompt();
    prompt.appendBreadcrumb("메인", getMenu());

    SpendsHandler spendsHandler = new SpendsHandler(prompt, "지출내역", new ArrayList());
    BoardHandler boardHandler = new BoardHandler(prompt, "게시글", new LinkedList());
    BoardHandler readingHandler = new BoardHandler(prompt, "독서록", new LinkedList());


    printTitle();

    prompt.printMenu();

    loop: while (true) {
      String menuNo = prompt.inputString("메인> ");
      switch (menuNo) {
        case "0":
          break loop;
        case "1":
          spendsHandler.execute();
          break;
        case "2":
          boardHandler.execute();
          break;
        case "3":
          readingHandler.execute();
          break;
      }
    }

    prompt.close();
  }

  static String getMenu() {
    StringBuilder menu = new StringBuilder();
    menu.append("1. 지출내역\n");
    menu.append("2. 게시글\n");
    menu.append("3. 독서록\n");
    menu.append("0. 종료\n");
    return menu.toString();
  }

  static void printTitle() {
    System.out.println("지출 내역 관리 시스템");
    System.out.println("----------------------------------");
  }

}
