package think.myapp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import think.myapp.dao.MemberDao;
import think.myapp.dao.MySQLMemberDao;
import think.myapp.dao.MySQLSpendDao;
import think.myapp.dao.SpendDao;
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
import think.net.NetProtocol;
import think.util.BreadcrumbPrompt;
import think.util.Menu;
import think.util.MenuGroup;
import think.util.SqlSessionFactoryProxy;

public class ServerApp {

  // 자바 스레드풀 준비
  ExecutorService threadPool = Executors.newFixedThreadPool(2);

  SqlSessionFactory sqlSessionFactory;

  MemberDao memberDao;
  SpendDao spendDao;

  final char NECESSITY = 'Y';
  final char LUXURY = 'N';

  MenuGroup mainMenu = new MenuGroup("메인");

  int port;

  public ServerApp(int port) throws Exception {

    this.port = port;

    // 1) mybatis 설정 파일을 읽어들일 도구를 준비한다.
    InputStream mybatisConfigIn =
        Resources.getResourceAsStream("think/myapp/config/mybatis-config.xml");

    // 2) SqlSessionFactory를 만들어줄 빌더 객체 준비
    SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();

    // 3) 빌더 객체를 통해 SqlSessionFactory를 생성
    sqlSessionFactory = new SqlSessionFactoryProxy(builder.build(mybatisConfigIn));

    this.memberDao = new MySQLMemberDao(sqlSessionFactory);
    this.spendDao = new MySQLSpendDao(sqlSessionFactory);

    prepareMenu();
  }

  public void close() throws Exception {

  }

  public static void main(String[] args) throws Exception {
    ServerApp app = new ServerApp(8888);
    app.execute();
    app.close();
  }

  public void execute() {
    try (ServerSocket serverSocket = new ServerSocket(this.port)) {
      System.out.println("서버 실행 중...");

      while (true) {
        Socket socket = serverSocket.accept();
        threadPool.execute(() -> processRequest(socket));
      }
    } catch (Exception e) {
      System.out.println("서버 실행 오류!");
      e.printStackTrace();
    }
  }

  private void processRequest(Socket socket) {
    try (Socket s = socket;
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {

      BreadcrumbPrompt prompt = new BreadcrumbPrompt(in, out);

      InetSocketAddress clientAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
      System.out.printf("%s 클라이언트 접속함!\n", clientAddress.getHostString());

      out.writeUTF("[지출 내역 관리 시스템]\n" + "🪙 🪙 🪙 🪙 🪙 🪙 🪙 🪙 🪙\n");

      new LoginListener(memberDao).service(prompt);

      mainMenu.execute(prompt);
      out.writeUTF(NetProtocol.NET_END);

    } catch (Exception e) {
      System.out.println("클라이언트 통신 오류!");
      e.printStackTrace();

    } finally {
      ((SqlSessionFactoryProxy) sqlSessionFactory).clean();
    }
  }

  private void prepareMenu() {
    MenuGroup memberMenu = new MenuGroup("사용자");
    memberMenu.add(new Menu("등록", new MemberAddListener(memberDao, sqlSessionFactory)));
    memberMenu.add(new Menu("목록", new MemberListListener(memberDao)));
    memberMenu.add(new Menu("조회", new MemberDetailListener(memberDao)));
    memberMenu.add(new Menu("변경", new MemberUpdateListener(memberDao, sqlSessionFactory)));
    memberMenu.add(new Menu("삭제", new MemberDeleteListener(memberDao, sqlSessionFactory)));
    mainMenu.add(memberMenu);

    MenuGroup necessityMenu = new MenuGroup("생필품");
    necessityMenu.add(new Menu("등록", new SpendAddListener(NECESSITY, spendDao, sqlSessionFactory)));
    necessityMenu.add(new Menu("목록", new SpendListListener(NECESSITY, spendDao)));
    necessityMenu
        .add(new Menu("조회", new SpendDetailListener(NECESSITY, spendDao, sqlSessionFactory)));
    necessityMenu
        .add(new Menu("변경", new SpendUpdateListener(NECESSITY, spendDao, sqlSessionFactory)));
    necessityMenu
        .add(new Menu("삭제", new SpendDeleteListener(NECESSITY, spendDao, sqlSessionFactory)));
    mainMenu.add(necessityMenu);

    MenuGroup luxuryMenu = new MenuGroup("사치품");
    luxuryMenu.add(new Menu("등록", new SpendAddListener(LUXURY, spendDao, sqlSessionFactory)));
    luxuryMenu.add(new Menu("목록", new SpendListListener(LUXURY, spendDao)));
    luxuryMenu.add(new Menu("조회", new SpendDetailListener(LUXURY, spendDao, sqlSessionFactory)));
    luxuryMenu.add(new Menu("변경", new SpendUpdateListener(LUXURY, spendDao, sqlSessionFactory)));
    luxuryMenu.add(new Menu("삭제", new SpendDeleteListener(LUXURY, spendDao, sqlSessionFactory)));
    mainMenu.add(luxuryMenu);

    // Menu helloMenu = new Menu("안녕!");
    // helloMenu.addActionListener(new HeaderListener());
    // helloMenu.addActionListener(new HelloListener());
    // helloMenu.addActionListener(new FooterListener());
    // mainMenu.add(helloMenu);
  }
}
