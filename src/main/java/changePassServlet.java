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

@WebServlet(name = "changePassServlet", value = "/changePassServlet")
public class changePassServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            System.out.println("1");
            HttpSession session=request.getSession();
            String username=session.getAttribute("loggedIn").toString();
            PrintWriter out=response.getWriter();
            Connection con = DB_Connection.getConnection();
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs,rs2;
            rs=stmt.executeQuery("Select * from users where username='"+username+"'");
            System.out.println("2");

            if(rs.next()){
                rs.updateString("password",request.getParameter("changepass"));
                rs.updateRow();
                out.println("<font color=green>User password changed successfully!</font>");
            }else{
                rs2=stmt.executeQuery("Select * from doctors where username='"+username+"'");
                if(rs2.next()){
                    rs2.updateString("password",request.getParameter("changepass"));
                    rs2.updateRow();
                    out.println("<font color=green>Doctor password changed successfully!</font>");
                }
            }
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
