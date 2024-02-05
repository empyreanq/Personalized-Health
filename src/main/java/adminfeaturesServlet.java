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

@WebServlet(name = "adminfeaturesServlet", value = "/adminfeaturesServlet")
public class adminfeaturesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            PrintWriter out = response.getWriter();
            Connection con = null;
            con = DB_Connection.getConnection();
            Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs;
            rs=stmt.executeQuery("SELECT * from doctors where username='"+request.getParameter("certifydoc")+"'");
            if(!rs.next()){out.println("<font color=red> No doctor with that username exists!</font>");return;}
            if(rs.getBoolean("certified")==true){
                out.println("<font color=red> Doctor already certified!</font>");
                return;
            }
            rs.updateInt("certified",1);
            rs.updateRow();
            out.println("<font color=green> Doctor certified successfully!</font>");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection con= null;
        try {
            PrintWriter out = response.getWriter();
            con = DB_Connection.getConnection();
            Statement stmt=con.createStatement();
            ResultSet rs;
            String delete;

            rs=stmt.executeQuery("Select * from users where username='"+ request.getParameter("deleteuser")+"'");
            if(rs.next()){
                delete="Delete from users where username='"+request.getParameter("deleteuser")+"'";
                stmt.executeUpdate(delete);
                out.println("<font color=green> Successfully deleted user!</font>");
                return;
            }
            rs=stmt.executeQuery("Select * from doctors where username='"+ request.getParameter("deleteuser")+"'");
            if(rs.next()){
                delete="Delete from doctors where username='"+request.getParameter("deleteuser")+"'";
                stmt.executeUpdate(delete);
                out.println("<font color=green> Successfully deleted doctor!</font>");
                return;
            }
            out.println("<font color=red> No user or doctor exists with that username!</font>");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }
}
