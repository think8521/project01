package project01.handler;

import project01.vo.Spend;

public class SpendList {
  private static final int DEFAULT_SIZE = 3;

  private Spend[] spends = new Spend[DEFAULT_SIZE];
  private int length = 0;

  public void add(Spend s) {
    if (this.length == spends.length) {
      increase();
    }

    this.spends[this.length++] = s;
    return true;
  }

  private void increase() {
    // 기존 배열 보다 50% 큰 배열을 새로 만든다.
    Spend[] arr = new Spend[spends.length + (spends.length >> 1)];

    // 기존 배열의 값을 새 배열로 복사한다.
    for (int i = 0; i < spends.length; i++) {
      arr[i] = spends[i];
    }

    // spends 레퍼런스가 새 배열을 가리키도록 한다.
    spends = arr;
    System.out.println("배열 확장: " + spends.length);
  }

  public Spend[] list() {
    Spend[] arr = new Spend[this.length];
    for (int i = 0; i < this.length; i++) {
      arr[i] = this.spends[i];
    }
    return arr;
  }

  public Spend get(int no) {
    for (int i = 0; i < this.length; i++) {
      Spend s = this.spends[i];
      if (s.getNo() == no) {
        return s;
      }
    }
    return null;
  }

  public boolean delete(int no) {
    int deletedIndex = indexOf(no);
    if (deletedIndex == -1) {
      return false;
    }

    for (int i = deletedIndex; i < this.length - 1; i++) {
      this.spends[i] = this.spends[i + 1];
    }
    this.spends[--this.length] = null;
    return true;
  }

  private int indexOf(int SpendNo) {
    for (int i = 0; i < this.length; i++) {
      Spend s = this.spends[i];
      if (s.getNo() == SpendNo) {
        return i;
      }
    }
    return -1;
  }
}







