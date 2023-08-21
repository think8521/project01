package think.myapp;

import think.myapp.handler.BoardHandler;
import think.myapp.handler.SpendsHandler;
import think.util.ArrayList;
import think.util.LinkedList;
import think.util.Prompt;

public class App {

  public static void main(String[] args) {


    Prompt prompt = new Prompt();

    SpendsHandler spendsHandler = new SpendsHandler(prompt, "지출내역", new ArrayList());
    BoardHandler boardHandler = new BoardHandler(prompt, "게시글", new LinkedList());
    BoardHandler readingHandler = new BoardHandler(prompt, "독서록", new LinkedList());


    printTitle();

    printMenu();

    while (true) {
      String menuNo = prompt.inputString("메인> ");
      if (menuNo.equals("0")) {
        break;
      } else if (menuNo.equals("menu")) {
        printMenu();
      } else if (menuNo.equals("1")) {
        spendsHandler.execute();
      } else if (menuNo.equals("2")) {
        boardHandler.execute();
      } else if (menuNo.equals("3")) {
        readingHandler.execute();
      } else {
        System.out.println("메뉴 번호가 옳지 않습니다!");
      }
    }

    prompt.close();
  }

  static void printMenu() {
    System.out.println("1. 지출");
    System.out.println("2. 게시글");
    System.out.println("3. 독서록");
    System.out.println("0. 종료");
  }

  static void printTitle() {
    System.out.println("지출 내역 관리 시스템");
    System.out.println("----------------------------------");
  }

}
