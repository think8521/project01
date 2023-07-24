package util;

import java.io.InputStream;
import java.util.Scanner;

public class Prompt {

  private Scanner scanner = new Scanner(System.in);

  public Prompt() {
    this.scanner = new Scanner(System.in);
  }

  public Prompt(InputStream in) {
    this.scanner = new Scanner(in);
  }

  public String inputString(String title, Object... args) {
    System.out.printf(title, args);
    return this.scanner.nextLine();
  }

  public int inputInt(String title, Object... args) {
    retuen Integer.parseInt(this.inputString(title, args));
  }

  public void close() {
    this.scanner.close();
  }
}


// public static boolean promptContinue() {
//     System.out.print("계속 하시겠습니까?(Y/n) ");
//     String response = scanner.nextLine();
//     if (!response.equals("") && !response.equalsIgnoreCase("Y")) {
//       return false;
//     }
//     return true;
//   }