import database.DB_Connection;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet(name = "userlistServlet", value = "/userlistServlet")
public class userlistServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            PrintWriter out=response.getWriter();
            Connection con = DB_Connection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("select username,firstname,lastname,birthdate FROM users");
            out.println("<br><table cellspacing='3' bgcolor='#000000'>");
            out.println("<tr bgcolor='ffffff'><th>Username</th><th>Firstname</th><th>Lastname</th><th>Birthdate</th><th>Certification</th></tr>");
            while(rs.next()){
                out.println("<tr bgcolor='ffffff'><td>"+rs.getString("username")+"</td><td>"+rs.getString("firstname")+"</td><td>"+rs.getString("lastname")+"</td><td>"+rs.getString("birthdate")+"</td><td>This is a user!</td></tr>");
            }
            rs = stmt.executeQuery("select username,firstname,lastname,birthdate,certified FROM doctors");
            while(rs.next()){
                out.println("<tr bgcolor='ffffff'><td>"+rs.getString("username")+"</td><td>"+rs.getString("firstname")+"</td><td>"+rs.getString("lastname")+"</td><td>"+rs.getString("birthdate")+"</td><td>"+rs.getBoolean("certified")+"</td></tr>");
            }
            out.println("</table>");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
