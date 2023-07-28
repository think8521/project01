package think.myapp;

import java.sql.Connection;
import java.sql.DriverManager;
import think.myapp.dao.MemberDao;
import think.myapp.dao.MySQLMemberDao;
import think.myapp.dao.MySQLSpendDao;
import think.myapp.dao.SpendDao;
import think.myapp.handler.FooterListener;
import think.myapp.handler.HeaderListener;
import think.myapp.handler.HelloListener;
import think.myapp.handler.LoginListener;
import think.myapp.handler.MemberAddListener;
import think.myapp.handler.MemberDeleteListener;
import think.myapp.handler.MemberDetailListener;
import think.myapp.handler.MemberListListener;
import think.myapp.handler.MemberUpdateListener;
import think.myapp.handler.SpendAddListener;
import think.myapp.handler.SpendDeleteListener;
import think.myapp.handler.SpendDetailListener;
import think.myapp.handler.SpendListListener;
import think.myapp.handler.SpendUpdateListener;
import think.myapp.vo.Member;
import think.util.BreadcrumbPrompt;
import think.util.Menu;
import think.util.MenuGroup;

public class ClientApp {

  public static Member loginUser;

  MemberDao memberDao;
  SpendDao necessityDao;
  SpendDao luxuryDao;

  final char NECESSITY_CATEGORY = 'Y';
  final char LUXURY_CATEGORY = 'N';

  BreadcrumbPrompt prompt = new BreadcrumbPrompt();

  MenuGroup mainMenu = new MenuGroup("메인");

  public ClientApp(String ip, int port) throws Exception {

    Connection con = DriverManager.getConnection("jdbc:mysql://study:1111@localhost:3306/thinkdb"); //


    this.memberDao = new MySQLMemberDao(con);
    this.necessityDao = new MySQLSpendDao(con, NECESSITY_CATEGORY);
    this.luxuryDao = new MySQLSpendDao(con, LUXURY_CATEGORY);

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

    new LoginListener(memberDao).service(prompt);

    mainMenu.execute(prompt);

  }

  private void prepareMenu() {
    MenuGroup memberMenu = new MenuGroup("사용자");
    memberMenu.add(new Menu("등록", new MemberAddListener(memberDao)));
    memberMenu.add(new Menu("목록", new MemberListListener(memberDao)));
    memberMenu.add(new Menu("조회", new MemberDetailListener(memberDao)));
    memberMenu.add(new Menu("변경", new MemberUpdateListener(memberDao)));
    memberMenu.add(new Menu("삭제", new MemberDeleteListener(memberDao)));
    mainMenu.add(memberMenu);

    MenuGroup necessityMenu = new MenuGroup("생필품");
    necessityMenu.add(new Menu("등록", new SpendAddListener(necessityDao)));
    necessityMenu.add(new Menu("목록", new SpendListListener(necessityDao)));
    necessityMenu.add(new Menu("조회", new SpendDetailListener(necessityDao)));
    necessityMenu.add(new Menu("변경", new SpendUpdateListener(necessityDao)));
    necessityMenu.add(new Menu("삭제", new SpendDeleteListener(necessityDao)));
    mainMenu.add(necessityMenu);

    MenuGroup luxuryMenu = new MenuGroup("사치품");
    luxuryMenu.add(new Menu("등록", new SpendAddListener(luxuryDao)));
    luxuryMenu.add(new Menu("목록", new SpendListListener(luxuryDao)));
    luxuryMenu.add(new Menu("조회", new SpendDetailListener(luxuryDao)));
    luxuryMenu.add(new Menu("변경", new SpendUpdateListener(luxuryDao)));
    luxuryMenu.add(new Menu("삭제", new SpendDeleteListener(luxuryDao)));
    mainMenu.add(luxuryMenu);

    Menu helloMenu = new Menu("안녕!");
    helloMenu.addActionListener(new HeaderListener());
    helloMenu.addActionListener(new HelloListener());
    helloMenu.addActionListener(new FooterListener());
    mainMenu.add(helloMenu);
  }
}
