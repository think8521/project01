package think.myapp;

import java.sql.Connection;
import java.sql.DriverManager;
import think.myapp.dao.BoardDao;
import think.myapp.dao.MySQLBoardDao;
import think.myapp.dao.MySQLSpendDao;
import think.myapp.dao.SpendDao;
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
import think.util.BreadcrumbPrompt;
import think.util.Menu;
import think.util.MenuGroup;

public class ClientApp {

  SpendDao spendDao;
  BoardDao necessityDao;
  BoardDao luxuryDao;

  final int NECESSITY_CATEGORY = 1;
  final int LUXURY_CATEGORY = 2;

  BreadcrumbPrompt prompt = new BreadcrumbPrompt();

  MenuGroup mainMenu = new MenuGroup("메인");

  public ClientApp(String ip, int port) throws Exception {

    Connection con = DriverManager.getConnection("jdbc:mysql://study:1111@localhost:3306/thinkdb"); //


    this.spendDao = new MySQLSpendDao(con);
    this.necessityDao = new MySQLBoardDao(con, NECESSITY_CATEGORY);
    this.luxuryDao = new MySQLBoardDao(con, LUXURY_CATEGORY);

    prepareMenu();
  }

  public void close() throws Exception {
    prompt.close();
  }

  public static void main(String[] args) throws Exception {
    if (args.length < 2) {
      System.out.println("실행 예) java ... bitcamp.myapp.ClientApp 서버주소 포트번호");
      return;
    }

    ClientApp app = new ClientApp(args[0], Integer.parseInt(args[1]));
    app.execute();
    app.close();
  }

  static void printTitle() {
    System.out.println("지출 내역 관리 시스템");
    System.out.println("🪙 🪙 🪙 🪙 🪙 🪙 🪙 🪙 🪙\n");
  }

  public void execute() {

    printTitle();
    mainMenu.execute(prompt);

  }

  private void prepareMenu() {
    MenuGroup spendMenu = new MenuGroup("지출내역");
    spendMenu.add(new Menu("등록", new SpendAddListener(spendDao)));
    spendMenu.add(new Menu("목록", new SpendListListener(spendDao)));
    spendMenu.add(new Menu("조회", new SpendDetailListener(spendDao)));
    spendMenu.add(new Menu("변경", new SpendUpdateListener(spendDao)));
    spendMenu.add(new Menu("삭제", new SpendDeleteListener(spendDao)));
    mainMenu.add(spendMenu);

    MenuGroup boardMenu = new MenuGroup("생필품");
    boardMenu.add(new Menu("등록", new BoardAddListener(necessityDao)));
    boardMenu.add(new Menu("목록", new BoardListListener(necessityDao)));
    boardMenu.add(new Menu("조회", new BoardDetailListener(necessityDao)));
    boardMenu.add(new Menu("변경", new BoardUpdateListener(necessityDao)));
    boardMenu.add(new Menu("삭제", new BoardDeleteListener(necessityDao)));
    mainMenu.add(boardMenu);

    MenuGroup readingMenu = new MenuGroup("사치품");
    readingMenu.add(new Menu("등록", new BoardAddListener(luxuryDao)));
    readingMenu.add(new Menu("목록", new BoardListListener(luxuryDao)));
    readingMenu.add(new Menu("조회", new BoardDetailListener(luxuryDao)));
    readingMenu.add(new Menu("변경", new BoardUpdateListener(luxuryDao)));
    readingMenu.add(new Menu("삭제", new BoardDeleteListener(luxuryDao)));
    mainMenu.add(readingMenu);

    Menu helloMenu = new Menu("안녕!");
    helloMenu.addActionListener(new HeaderListener());
    helloMenu.addActionListener(new HelloListener());
    helloMenu.addActionListener(new FooterListener());
    mainMenu.add(helloMenu);
  }
}
