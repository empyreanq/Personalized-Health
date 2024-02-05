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

@WebServlet(name = "seePatientServlet", value = "/seePatientServlet")
public class seePatientServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session=request.getSession();
            PrintWriter out=response.getWriter();
            Connection con = DB_Connection.getConnection();
            Statement stmt = con.createStatement();
            Statement stmt2 = con.createStatement();
            Statement stmt3 = con.createStatement();
            Statement stmt4=con.createStatement();
            int i=0;
            ResultSet rs,rs2,rs3,rs4;
            out.println("<br><br><table cellspacing='3' bgcolor='#000000'>");
            out.println("<tr bgcolor='ffffff'><th>User_ID</td><td>Firstname</th><th>Lastname</th><th>Amka</th><th>Birthdate</th></tr>");
            rs=stmt.executeQuery("SELECT doctor_id from doctors where username='"+session.getAttribute("loggedIn").toString()+"'");
            rs.next();
            rs2 = stmt2.executeQuery("select * FROM randevouz where doctor_id='"+rs.getInt("doctor_id")+"'");
            while(rs2.next()){
                rs3=stmt3.executeQuery("select * from users where user_id='"+rs2.getInt("user_id")+"'");
                rs3.next();
                out.println("<tr bgcolor='ffffff'><td>"+rs3.getString("user_id")+"<td>" + rs3.getString("firstname") + "</td><td>" + rs3.getString("lastname") + "</td><td>" + rs3.getString("amka") + "</td><td>" + rs3.getString("birthdate") + "</td></tr>");
                i++;
            }
            if(i==0)out.println("<tr bgcolor='ffffff'><td>Doctor doesn't have any patients</td></tr>");
            /*out.println("<br><table cellspacing='3' bgcolor='#000000'>");
            out.println("<tr bgcolor='ffffff'><th>Username</th><th>Firstname</th><th>Lastname</th><th>Birthdate</th><th>Certification</th></tr>");
            while(rs.next()){
                out.println("<tr bgcolor='ffffff'><td>"+rs.getString("username")+"</td><td>"+rs.getString("firstname")+"</td><td>"+rs.getString("lastname")+"</td><td>"+rs.getString("birthdate")+"</td><td>This is a user!</td></tr>");
            }
            rs = stmt.executeQuery("select username,firstname,lastname,birthdate,certified FROM doctors");
            while(rs.next()){
                out.println("<tr bgcolor='ffffff'><td>"+rs.getString("username")+"</td><td>"+rs.getString("firstname")+"</td><td>"+rs.getString("lastname")+"</td><td>"+rs.getString("birthdate")+"</td><td>"+rs.getBoolean("certified")+"</td></tr>");
            }*/
            out.println("</table><br><br>");
            i=0;
            out.println("<br><table cellspacing='3' bgcolor='#000000'>");
            out.println("<tr bgcolor='ffffff'><th>Bloodtest ID</th><th>amka</th><th>test_date</th><th>medical_center</th><th>blood_sugar</th><th>blood_sugar_level</th><th>cholesterol</th><th>cholesterol_level</th><th>iron</th><th>iron_level</th><th>d3</th><th>d3_level</th><th>b12</th><th>b12_level</th></tr>");
            rs2 = stmt2.executeQuery("select * FROM randevouz where doctor_id='"+rs.getInt("doctor_id")+"'");
            while(rs2.next()){
                rs3=stmt3.executeQuery("select * from users where user_id='"+rs2.getInt("user_id")+"'");
                rs3.next();
                rs4= stmt4.executeQuery("SELECT * from bloodtest where amka='"+rs3.getString("amka")+"'");
                while(rs4.next()){
                    out.println("<tr bgcolor='ffffff'><td>"+rs4.getInt("bloodtest_id")+"</td><td>"+rs4.getString("amka")+"</td><td>"+rs4.getString("test_date")+"</td><td>"+rs4.getString("medical_center")+"</td><td>"+rs4.getInt("blood_sugar")+"</td><td>"+rs4.getString("blood_sugar_level")+"</td><td>"+rs4.getInt("cholesterol")+"</td><td>"+rs4.getString("cholesterol_level")+"</td><td>"+rs4.getInt("iron")+"</td><td>"+rs4.getString("iron_level")+"</td><td>"+rs4.getInt("vitamin_d3")+"</td><td>"+rs4.getString("vitamin_d3_level")+"</td><td>"+rs4.getInt("vitamin_b12")+"</td><td>"+rs4.getString("vitamin_b12_level")+"</td></tr>");
                    i++;
                }
            }
            if(i==0)out.println("<tr bgcolor='ffffff'><td>Doctor doesn't have any patients</td></tr>");
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
