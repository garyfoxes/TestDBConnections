import java.sql.*;

/**
 * Created by gfox on 12/05/2016.
 */
public class TestDriver {

    public static void main(String[] args) {
        Connection conn = null;
        Statement st;
        ResultSet rset;
        PreparedStatement ptst;
        try {
            conn = setUpJDBCConnection(conn);
            st = conn.createStatement();
            createTableAndInsertData(conn, st);
            System.out.println("Success");

            //simpleSelectStatement(st);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                    System.out.println("Closed Connection");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void createTableAndInsertData(Connection conn, Statement st) throws SQLException {
        PreparedStatement ptst;
        st.executeUpdate("drop table if exists books");
        st.executeUpdate("create table books(isbn int(10), title char(50), author char(50), publisher char(50),price float,PRIMARY KEY (isbn))");
        st.executeUpdate("insert into books(isbn,title,author,publisher,price) values(1,'Java beginners','Gary','Addison',40.5)");
        String sql = "Update books set title = ? where isbn = ?";
        ptst = conn.prepareStatement(sql);
        ptst.setString(1, "Java Advanced");
        ptst.setInt(2, 1);
        ptst.executeUpdate();
    }

    private static void simpleSelectStatement(Statement st) throws SQLException {
        ResultSet rset;
        rset = st.executeQuery("select * from film where film_id = 1;");

        if (rset.next()) {
            System.out.println(rset.getString("description"));
        }
    }

    private static Connection setUpJDBCConnection(Connection conn) throws Exception {
        String username = "root";
        String password = "password";
        String url = "jdbc:mysql://localhost/sakila";
        final Driver driver = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
        System.out.println("success");
        conn = DriverManager.getConnection(url, username, password);
        System.out.println("Successful");
        return conn;
    }
}
