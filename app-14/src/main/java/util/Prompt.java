package util;

import java.util.Scanner;

public class Prompt {

  static Scanner scanner = new Scanner(System.in);

  public static String inputString(String title) {
    System.out.print(title);
    return scanner.nextLine();
  }

  public static int inputInt(String title) {
    retuen Integer.parseInt(inputString(title));
  }

  public static void close() {
    scanner.close();
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