package think.myapp.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import think.myapp.vo.Spend;

public class MySQLSpendDao implements SpendDao {

  Connection con;

  public MySQLSpendDao(Connection con) {
    this.con = con;
  }

  @Override
  public void insert(Spend spend) {
    try (Statement stmt = con.createStatement()) {

      stmt.executeUpdate(
          String.format("insert into think_spend(spend,price,daily) values('%s','%s','%c')",
              spend.getSpend(), spend.getPrice(), spend.getDaily()));

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public List<Spend> list() {
    try (Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(
            "select spend_no, spend, price, daily from think_spend order by spend_no asc")) {

      List<Spend> list = new ArrayList<>();

      while (rs.next()) {
        Spend s = new Spend();
        s.setNo(rs.getInt("spend_no"));
        s.setSpend(rs.getString("spend"));
        s.setPrice(rs.getString("price"));
        s.setDaily(rs.getString("daily").charAt(0));

        list.add(s);
      }

      return list;

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Spend findBy(int no) {
    try (Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(
            "select spend_no, spend, price, daily from think_spend where spend_no=" + no)) {

      if (rs.next()) {
        Spend s = new Spend();
        s.setNo(rs.getInt("spend_no"));
        s.setSpend(rs.getString("spend"));
        s.setPrice(rs.getString("price"));
        s.setDaily(rs.getString("daily").charAt(0));
        return s;
      }

      return null;

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public int update(Spend spend) {
    try (Statement stmt = con.createStatement()) {

      return stmt.executeUpdate(String.format(
          "update think_spend set" + " spend='%s'," + " price='%s'," + " daily='%c'"
              + " where spend_no=%d",
          spend.getSpend(), spend.getPrice(), spend.getDaily(), spend.getNo()));

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public int delete(int no) {
    try (Statement stmt = con.createStatement()) {

      return stmt.executeUpdate(String.format("delete from think_spend where spend_no=%d", no));

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
