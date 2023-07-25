package think.myapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import think.myapp.vo.Member;

public class MySQLMemberDao implements MemberDao {
  Connection con;

  public MySQLMemberDao(Connection con) {
    this.con = con;
  }

  @Override
  public void insert(Member member) {
    try (PreparedStatement stmt =
        con.prepareStatement("insert into think_member(name,id,pwd)" + " values(?,?,sha1(?))")) {

      stmt.setString(1, member.getName());
      stmt.setString(2, member.getId());
      stmt.setString(3, member.getPwd());

      stmt.executeUpdate();

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public List<Member> list() {
    try (
        PreparedStatement stmt = con.prepareStatement(
            "select member_no, name, id" + " from think_member" + " order by name asc");
        ResultSet rs = stmt.executeQuery()) {

      List<Member> list = new ArrayList<>();

      while (rs.next()) {
        Member m = new Member();
        m.setNo(rs.getInt("member_no"));
        m.setName(rs.getString("name"));
        m.setId(rs.getString("id"));

        list.add(m);
      }

      return list;

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Member findBy(int no) {
    try (PreparedStatement stmt = con.prepareStatement(
        "select member_no, name, id" + " from think_member" + " where member_no=?")) {

      stmt.setInt(1, no);

      try (ResultSet rs = stmt.executeQuery()) {
        if (rs.next()) {
          Member m = new Member();
          m.setNo(rs.getInt("member_no"));
          m.setName(rs.getString("name"));
          m.setId(rs.getString("id"));
          return m;
        }
        return null;
      }

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public int update(Member member) {
    try (PreparedStatement stmt = con.prepareStatement("update think_member set" + " name=?,"
        + " id=?," + " pwd=sha1(?)," + " where member_no=?")) {

      stmt.setString(1, member.getName());
      stmt.setString(2, member.getId());
      stmt.setString(3, member.getPwd());
      stmt.setInt(4, member.getNo());

      return stmt.executeUpdate();

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public int delete(int no) {
    try (PreparedStatement stmt =
        con.prepareStatement("delete from think_member where member_no=?")) {

      stmt.setInt(1, no);

      return stmt.executeUpdate();

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
