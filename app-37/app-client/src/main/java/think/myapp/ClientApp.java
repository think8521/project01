package think.myapp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
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
import think.myapp.vo.AutoIncrement;
import think.myapp.vo.Board;
import think.myapp.vo.Spend;
import think.util.BreadcrumbPrompt;
import think.util.Menu;
import think.util.MenuGroup;

public class ClientApp {

  ArrayList<Spend> spendList = new ArrayList<>();
  LinkedList<Board> boardList = new LinkedList<>();
  LinkedList<Board> readingList = new LinkedList<>();

  BreadcrumbPrompt prompt = new BreadcrumbPrompt();

  MenuGroup mainMenu = new MenuGroup("메인");

  public ClientApp() {
    prepareMenu();
  }

  public static void main(String[] args) {
    new ClientApp().execute();
  }

  static void printTitle() {
    System.out.println("지출 내역 관리 시스템");
    System.out.println("🪙 🪙 🪙 🪙 🪙 🪙 🪙 🪙 🪙\n");
  }

  public void execute() {
    printTitle();

    loadData();
    mainMenu.execute(prompt);
    saveData();

    prompt.close();
  }

  private void loadData() {
    loadJson("spend.Json", spendList, Spend.class);
    loadJson("board.Json", boardList, Board.class);
    loadJson("reading.Json", readingList, Board.class);
  }

  private void saveData() {
    saveJson("spend.Json", spendList);
    saveJson("board.Json", boardList);
    saveJson("reading.Json", readingList);
  }

  private void prepareMenu() {
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
  }

  private <T> void loadJson(String filename, List<T> list, Class<T> clazz) {
    try {
      FileReader in0 = new FileReader(filename);
      BufferedReader in = new BufferedReader(in0); // <== Decorator 역할을 수행!


      StringBuilder strBuilder = new StringBuilder();
      String line = null;

      while ((line = in.readLine()) != null) {
        strBuilder.append(line);
      }


      in.close();

      Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

      Collection<T> objects = gson.fromJson(strBuilder.toString(),
          TypeToken.getParameterized(Collection.class, clazz).getType());

      list.addAll(objects);

      Class<?>[] interfaces = clazz.getInterfaces();
      for (Class<?> info : interfaces) {
        if (info == AutoIncrement.class) {
          AutoIncrement autoIncrement = (AutoIncrement) list.get(list.size() - 1);
          autoIncrement.updatekey();
          break;
        }
      }

    } catch (Exception e) {
      System.out.println(filename + " 파일을 읽는 중 오류 발생!");
    }
  }

  private void saveJson(String filename, List<?> list) {
    try {
      FileWriter out0 = new FileWriter(filename);
      BufferedWriter out = new BufferedWriter(out0);

      Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").setPrettyPrinting().create();
      out.write(gson.toJson(list));

      out.close();
    } catch (Exception e) {
      System.out.println(filename + " 파일을 저장하는 중 오류 발생!");
    }
  }
}
