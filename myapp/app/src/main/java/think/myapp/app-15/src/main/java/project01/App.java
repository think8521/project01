package project01;

import util.Prompt;
import project01.handler.SpendsHandler;
import project01.handler.BoardHandler;
import project01.handler.BoardHandler2;


public class App {

  public static void main(String[] args) {


    Prompt prompt = new Prompt();

    SpendsHandler spendsHandler = new SpendsHandler(prompt);
    BoardHandler boardHandler = new BoardHandler(prompt);
    BoardHandler readingHandler = new BoardHandler(prompt);


    printTitle();

    printMenu();

    while (true) {
      String menuNo = Prompt.inputString("메인> ");
      if (menuNo.equals("99")) {
        break;
      } else if (menuNo.equals("menu")) {
        printMenu();
      } else if (menuNo.equals("1")) {
        spendsHandler.inputSpend();
      } else if (menuNo.equals("2")) {
        spendsHandler.printSpends();
      } else if (menuNo.equals("3")) {
        spendsHandler.viewSpend();
      } else if (menuNo.equals("4")) {
        spendsHandler.updateSpend();
      } else if (menuNo.equals("5")) {
        spendsHandler.deleteSpend();
      } else if (menuNo.equals("6")) {
        boardHandler.inputBoard();
      } else if (menuNo.equals("7")) {
        boardHandler.printBoard();
      } else if (menuNo.equals("8")) {
        boardHandler.viewBoard();
      } else if (menuNo.equals("9")) {
        boardHandler.updateBoard();
      } else if (menuNo.equals("10")) {
        boardHandler.deleteBoard();
      } else if (menuNo.equals("11")) {
        readingHandler2.inputBoard();
      } else if (menuNo.equals("12")) {
        readingHandler2.printBoards();
      } else if (menuNo.equals("13")) {
        readingHandler2.viewBoard();
      } else if (menuNo.equals("14")) {
        readingHandler2.updateBoard();
      } else if (menuNo.equals("15")) {
        readingHandler2.deleteBoard();
      } else {
        System.out.println("메뉴 번호가 옳지 않습니다!");
      }
    }

    // while (SpendsHandler.available()) {
    //   SpendsHandler.inputSpend();
    //   if (!Prompt.promptContinue()) {
    //     break;
    //   }
    // }

  //   SpendsHandler.printSpends();

    ㅔrompt.close();
  }

  static void printMenu() {
    System.out.println("1. 지출등록");
    System.out.println("2. 지출목록");
    System.out.println("3. 지출조회");
    System.out.println("4. 지출변경");
    System.out.println("5. 지출삭제");
    System.out.println("6. 게시글등록");
    System.out.println("7. 게시글목록");
    System.out.println("8. 게시글조회");
    System.out.println("9. 게시글변경");
    System.out.println("10. 게시글삭제");
    System.out.println("11. 독서록등록");
    System.out.println("12. 독서록목록");
    System.out.println("13. 독서록조회");
    System.out.println("14. 독서록변경");
    System.out.println("15. 독서록삭제");
    System.out.println("99. 종료");
  }

  static void printTitle() {
    System.out.println("지출 내역 관리 시스템");
    System.out.println("----------------------------------");
  }

}
