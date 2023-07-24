package think.myapp;

import think.myapp.handler.BoardAddListener;
import think.myapp.handler.BoardDeleteListener;
import think.myapp.handler.BoardDetailListener;
import think.myapp.handler.BoardListListener;
import think.myapp.handler.BoardUpdateListener;
import think.myapp.handler.FooterListener;
import think.myapp.handler.HeaderListener;
import think.myapp.handler.HelloListener;
import think.myapp.handler.SpendAddListener;
import think.myapp.handler.SpendDeleteListener;
import think.myapp.handler.SpendDetailListener;
import think.myapp.handler.SpendListListener;
import think.myapp.handler.SpendUpdateListener;
import think.util.ArrayList;
import think.util.BreadcrumbPrompt;
import think.util.LinkedList;
import think.util.Menu;
import think.util.MenuGroup;

public class App {

  public static void main(String[] args) {

    ArrayList spendList = new ArrayList();
    LinkedList boardList = new LinkedList();
    LinkedList readingList = new LinkedList();

    BreadcrumbPrompt prompt = new BreadcrumbPrompt();

    MenuGroup mainMenu = new MenuGroup("메인");

    MenuGroup spendMenu = new MenuGroup("지출내역");
    spendMenu.add(new Menu("등록", new SpendAddListener(spendList)));
    spendMenu.add(new Menu("목록", new SpendListListener(spendList)));
    spendMenu.add(new Menu("조회", new SpendDetailListener(spendList)));
    spendMenu.add(new Menu("변경", new SpendUpdateListener(spendList)));
    spendMenu.add(new Menu("삭제", new SpendDeleteListener(spendList)));
    mainMenu.add(spendMenu);

    MenuGroup boardMenu = new MenuGroup("게시글");
    boardMenu.add(new Menu("등록", new BoardAddListener(boardList)));
    boardMenu.add(new Menu("목록", new BoardListListener(boardList)));
    boardMenu.add(new Menu("조회", new BoardDetailListener(boardList)));
    boardMenu.add(new Menu("변경", new BoardUpdateListener(boardList)));
    boardMenu.add(new Menu("삭제", new BoardDeleteListener(boardList)));
    mainMenu.add(boardMenu);

    MenuGroup readingMenu = new MenuGroup("독서록");
    readingMenu.add(new Menu("등록", new BoardAddListener(readingList)));
    readingMenu.add(new Menu("목록", new BoardListListener(readingList)));
    readingMenu.add(new Menu("조회", new BoardDetailListener(readingList)));
    readingMenu.add(new Menu("변경", new BoardUpdateListener(readingList)));
    readingMenu.add(new Menu("삭제", new BoardDeleteListener(readingList)));
    mainMenu.add(readingMenu);

    Menu helloMenu = new Menu("안녕!");
    helloMenu.addActionListener(new HeaderListener());
    helloMenu.addActionListener(new HelloListener());
    helloMenu.addActionListener(new FooterListener());
    mainMenu.add(helloMenu);

    printTitle();

    mainMenu.execute(prompt);

    prompt.close();
  }

  static void printTitle() {
    System.out.println("지출 내역 관리 시스템");
    System.out.println("----------------------------------");
  }
}
