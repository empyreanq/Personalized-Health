import database.DB_Connection;
import mainClasses.*;
import database.tables.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.io.PrintWriter;

@WebServlet(name = "UserNameCheckServlet", value = "/UserNameCheckServlet")
public class UserNameCheckServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            PrintWriter out = response.getWriter();
            String username = request.getParameter("username");
            Connection con = DB_Connection.getConnection();
            PreparedStatement ps = con.prepareStatement("select * from users where username=?");
            ps.setString(1,username);
            ResultSet rs = ps.executeQuery();
            PreparedStatement ps2 = con.prepareStatement("select * from doctors where username=?");
            ps2.setString(1,username);
            ResultSet rs2 = ps2.executeQuery();
            if(rs.next()||rs2.next()){
                out.println("<font color=red> Username already in use</font>");
            }else{
                out.println("<font color=green> Username available</font>");
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
