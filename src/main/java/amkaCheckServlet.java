import database.DB_Connection;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.sql.DriverManager.println;

@WebServlet(name = "amkaCheckServlet", value = "/amkaCheckServlet")
public class amkaCheckServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            PrintWriter out = response.getWriter();
            String amka = request.getParameter("amka");
            Connection con = DB_Connection.getConnection();
            PreparedStatement ps = con.prepareStatement("select * from users where amka=?");
            ps.setString(1,amka);
            ResultSet rs = ps.executeQuery();
            PreparedStatement ps2 = con.prepareStatement("select * from doctors where amka=?");
            ps2.setString(1,amka);
            ResultSet rs2 = ps2.executeQuery();
            if(rs.next()||rs2.next()){
                out.println("<font color=red> Amka already in use</font>");
            }else{
                out.println("<font color=green> Amka available</font>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
