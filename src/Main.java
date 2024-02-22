import java.sql.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws Exception{
        try{
            Class.forName("org.h2.Driver").newInstance();
        }catch(ClassNotFoundException e){
            e.printStackTrace();
            System.exit(0);
        }
        try{
            Connection con = DriverManager.getConnection("jdbc:h2:"+
                    "./Database/my", "sa", "sa");
            Statement stmt = con.createStatement();
            String createSql = """
                DROP TABLE IF EXISTS TEST;
                CREATE TABLE TEST(ID INT PRIMARY KEY, NAME VARCHAR(255));
                INSERT INTO TEST VALUES(1, 'Hello');
                INSERT INTO TEST VALUES(2, 'World');
                """;
            stmt.execute(createSql);
            String sql = "select * from TEST";
            ResultSet rd = stmt.executeQuery(sql);
            while(rd.next()) {
                System.out.println(rd.getInt(1) + rd.getString(2));
            }
            try{
                con.close();
            }catch(SQLException e){
                e.printStackTrace();
                System.exit(0);
            }
        }catch(Exception e){
            e.printStackTrace();
            System.exit(0);
        }
    }
}