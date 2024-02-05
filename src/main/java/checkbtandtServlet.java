import database.DB_Connection;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet(name = "checkbtandtServlet", value = "/checkbtandtServlet")
public class checkbtandtServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Connection con = DB_Connection.getConnection();
            HttpSession session=request.getSession();
            PrintWriter out=response.getWriter();
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("Select user_id,amka from users where username='"+session.getAttribute("loggedIn").toString()+"'");
            rs.next();
            SimpleDateFormat sdf1= new SimpleDateFormat("yyyy-MM-dd");
            Date d1;
            Date d2,d3;
            ResultSet rs3=stmt.executeQuery("Select * from treatment where user_id='"+rs.getInt("user_id")+"'");
            out.println("<br><br><table cellspacing='3' bgcolor='#000000'>");
            out.println("<tr bgcolor='ffffff'><th>start_date</th><th>end_date</th><th>treatment_text</th></tr>");
            while(rs3.next())
            {
                out.println("<tr bgcolor='ffffff'><td>"+rs3.getString("start_date")+"</td><td>"+rs3.getString("end_date")+"</td><td>"+rs3.getString("treatment_text")+"</td></tr>");

                /*System.out.println("1");
                d1= (Date) sdf1.parse(rs3.getString("end_date"));
                d2= (Date) sdf1.parse(rs3.getString("start_date"));
                d3= (Date) sdf1.parse("2022-09-12");
                System.out.println(d1);
                if (d1.compareTo(d3) >= 0 && d2.compareTo(d3) <= 0) {
                    out.println(rs3.getString("treatment_text") + "<br>");
                }*/
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
        System.out.println("hey");
        try {

            Connection con= DB_Connection.getConnection();
            HttpSession session=request.getSession();
            PrintWriter out=response.getWriter();
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("Select user_id,amka from users where username='"+session.getAttribute("loggedIn").toString()+"'");
            rs.next();
            ResultSet rs2=stmt.executeQuery("Select * from bloodtest where amka='"+rs.getString("amka")+"'");
            out.println("<br><table cellspacing='3' bgcolor='#000000'>");
            out.println("<tr bgcolor='ffffff'><th>amka</th><th>test_date</th><th>medical_center</th><th>blood_sugar</th><th>blood_sugar_level</th><th>cholesterol</th><th>cholesterol_level</th><th>iron</th><th>iron_level</th><th>d3</th><th>d3_level</th><th>b12</th><th>b12_level</th></tr>");
            if(rs2.next()){
                out.println("<tr bgcolor='ffffff'><td>"+rs2.getString("amka")+"</td><td>"+rs2.getString("test_date")+"</td><td>"+rs2.getString("medical_center")+"</td><td>"+rs2.getInt("blood_sugar")+"</td><td>"+rs2.getString("blood_sugar_level")+"</td><td>"+rs2.getInt("cholesterol")+"</td><td>"+rs2.getString("cholesterol_level")+"</td><td>"+rs2.getInt("iron")+"</td><td>"+rs2.getString("iron_level")+"</td><td>"+rs2.getInt("vitamin_d3")+"</td><td>"+rs2.getString("vitamin_d3_level")+"</td><td>"+rs2.getInt("vitamin_b12")+"</td><td>"+rs2.getString("vitamin_b12_level")+"</td></tr>");
            }
            int bsprev=rs2.getInt("blood_sugar");
            int cholprev=rs2.getInt("cholesterol");
            int ironprev=rs2.getInt("iron");
            int d3prev=rs2.getInt("vitamin_d3");
            int b12prev=rs2.getInt("vitamin_b12");
            int bs,chol,iron,d3,b12;
            while(rs2.next()){
                bs=rs2.getInt("blood_sugar")-bsprev;
                chol=rs2.getInt("cholesterol")-cholprev;
                iron=rs2.getInt("iron")-ironprev;
                d3=rs2.getInt("vitamin_d3")-d3prev;
                b12=rs2.getInt("vitamin_b12")-b12prev;
                out.println("<tr bgcolor='ffffff'><td>"+rs2.getString("amka")+"</td><td>"+rs2.getString("test_date")+"</td><td>"+rs2.getString("medical_center")+"</td><td>"+rs2.getInt("blood_sugar")+"("+bs+")</td><td>"+rs2.getString("blood_sugar_level")+"</td><td>"+rs2.getInt("cholesterol")+"("+chol+")</td><td>"+rs2.getString("cholesterol_level")+"</td><td>"+rs2.getInt("iron")+"("+iron+")</td><td>"+rs2.getString("iron_level")+"</td><td>"+rs2.getInt("vitamin_d3")+"("+d3+")</td><td>"+rs2.getString("vitamin_d3_level")+"</td><td>"+rs2.getInt("vitamin_b12")+"("+b12+")</td><td>"+rs2.getString("vitamin_b12_level")+"</td></tr>");
                bsprev=rs2.getInt("blood_sugar");
                cholprev=rs2.getInt("cholesterol");
                ironprev=rs2.getInt("iron");
                d3prev=rs2.getInt("vitamin_d3");
                b12prev=rs2.getInt("vitamin_b12");
            }
            out.println("</table>");


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
