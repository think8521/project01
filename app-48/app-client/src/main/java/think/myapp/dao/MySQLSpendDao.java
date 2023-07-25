package think.myapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import think.myapp.vo.Spend;

public class MySQLSpendDao implements SpendDao {

  Connection con;
  char category;

  public MySQLSpendDao(Connection con, char category) {
    this.con = con;
    this.category = category;
  }

  @Override
  public void insert(Spend spend) {
    try (PreparedStatement stmt =
        con.prepareStatement("insert into think_spend(spend,price,daily) values(?,?,?)")) {

      stmt.setString(1, spend.getSpend());
      stmt.setString(2, String.valueOf(spend.getPrice()));
      stmt.setString(3, String.valueOf(this.category));


      stmt.executeUpdate();

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public List<Spend> list() {
    try (PreparedStatement stmt =
        con.prepareStatement("select spend_no, spend, price, daily from think_spend"
            + " where daily=?" + " order by spend_no desc")) {

      stmt.setString(1, String.valueOf(this.category));

      try (ResultSet rs = stmt.executeQuery()) {
        List<Spend> list = new ArrayList<>();
        while (rs.next()) {
          Spend s = new Spend();
          s.setNo(rs.getInt("spend_no"));
          s.setSpend(rs.getString("spend"));
          s.setPrice(rs.getInt("price"));
          s.setDaily(rs.getString("daily").charAt(0));

          list.add(s);
        }

        return list;
      }

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Spend findBy(int no) {
    try (PreparedStatement stmt =
        con.prepareStatement("select spend_no, spend, price, daily from think_spend"
            + " where daily=? and spend_no=?" + " order by spend_no desc")) {
      ;

      stmt.setString(1, String.valueOf(this.category));
      stmt.setInt(2, no);

      try (ResultSet rs = stmt.executeQuery()) {
        if (rs.next()) {
          Spend s = new Spend();
          s.setNo(rs.getInt("spend_no"));
          s.setSpend(rs.getString("spend"));
          s.setPrice(rs.getInt("price"));
          s.setDaily(rs.getString("daily").charAt(0));

          return s;
        }

        return null;
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public int update(Spend spend) {
    try (PreparedStatement stmt = con.prepareStatement(
        "update think_spend set" + " spend=?," + " price=?," + " daily=?" + " where spend_no=?")) {

      stmt.setString(1, spend.getSpend());
      stmt.setInt(2, spend.getPrice());
      stmt.setString(3, String.valueOf(spend.getDaily()));
      stmt.setInt(4, spend.getNo());


      return stmt.executeUpdate();

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public int delete(Spend spend) {
    try (
        PreparedStatement stmt = con.prepareStatement("delete from think_spend where spend_no=?")) {

      stmt.setInt(1, spend.getNo());

      return stmt.executeUpdate();

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
