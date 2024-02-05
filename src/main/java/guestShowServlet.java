import com.mysql.cj.jdbc.interceptors.ResultSetScannerInterceptor;
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

@WebServlet(name = "guestShowServlet", value = "/guestShowServlet")
public class guestShowServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            PrintWriter out=response.getWriter();
            Connection con= DB_Connection.getConnection();
            Statement stmt=con.createStatement();
            ResultSet rs= stmt.executeQuery("Select * from doctors");
            System.out.println("hey");
            out.println("<br><table cellspacing='3' bgcolor='#000000'>");
            out.println("<tr bgcolor='ffffff'><th>Firstname</th><th>Lastname</th><th>City</th><th>Address</th><th>Specialty</th><th>Telephone</th></tr>");
            while(rs.next()){
               out.println("<tr bgcolor='ffffff'><td>"+rs.getString("firstname")+"</td><td>"+rs.getString("lastname")+"</td><td>"+rs.getString("city")+"</td><td>"+rs.getString("address")+"</td><td>"+rs.getString("specialty")+"</td><td>"+rs.getString("telephone")+"</td></tr>");
            }
            out.println("</table>");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
