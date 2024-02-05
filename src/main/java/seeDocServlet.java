import com.mysql.cj.protocol.Resultset;
import database.DB_Connection;
import database.tables.EditRandevouzTable;
import mainClasses.Randevouz;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet(name = "seeDocServlet", value = "/seeDocServlet")
public class seeDocServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            PrintWriter out=response.getWriter();
            Connection con= DB_Connection.getConnection();
            Statement stmt= con.createStatement();
            ResultSet rs;
            rs=stmt.executeQuery("select doctor_id,firstname,lastname from doctors");
            out.println("<br><table cellspacing='3' bgcolor='#000000'>");
            out.println("<tr bgcolor='ffffff'><th>Doctor_id</th><th>Firstname</th><th>Lastname</th></tr>");
            while(rs.next()){
                out.println("<tr bgcolor='ffffff'><td>"+rs.getString("doctor_id")+"</td><td>"+rs.getString("firstname")+"</td><td>"+rs.getString("lastname")+"</td></tr>");
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
        try {
            HttpSession session=request.getSession();
            PrintWriter out=response.getWriter();
            Connection con=DB_Connection.getConnection();
            Statement stmt=con.createStatement();
            String uusername=session.getAttribute("loggedIn").toString();
            ResultSet rs=stmt.executeQuery("Select user_id from users where username='"+uusername+"'");
            rs.next();
            Randevouz rand= new Randevouz();
            rand.setDoctor_id(Integer.parseInt(request.getParameter("doctor_id")));
            rand.setUser_id(rs.getInt("user_id"));
            rand.setDate_time("2022-09-11 15:00:00");
            rand.setPrice(50);
            rand.setDoctor_info("null");
            rand.setUser_info("null");
            rand.setStatus("done");
            EditRandevouzTable ert= new EditRandevouzTable();
            ert.createNewRandevouz(rand);
            out.println("<font color=green>Randevouz booked successfully!</font>");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
