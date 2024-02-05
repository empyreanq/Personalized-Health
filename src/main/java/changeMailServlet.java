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

@WebServlet(name = "changeMailServlet", value = "/changeMailServlet")
public class changeMailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session=request.getSession();
            String username=session.getAttribute("loggedIn").toString();
            PrintWriter out=response.getWriter();
            Connection con = DB_Connection.getConnection();
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs;
            rs=stmt.executeQuery("Select * from users where username='"+username+"'");
            if(rs.next()){
                rs.updateString("email",request.getParameter("changemail"));
                rs.updateRow();
                out.println("<font color=green>User email changed successfully!</font>");
            }else{
                rs=stmt.executeQuery("Select * from doctors where username='"+username+"'");
                if(rs.next()) {
                    rs.updateString("email", request.getParameter("changemail"));
                    rs.updateRow();
                    out.println("<font color=green>Doctor email changed successfully!</font>");
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
        HttpSession session=request.getSession();
        if(session.getAttribute("loggedIn")!=null){
            session.invalidate();
            response.setStatus(200);
        }
    }
}
