import database.DB_Connection;
import database.tables.EditBloodTestTable;
import mainClasses.BloodTest;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.swing.plaf.nimbus.State;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet(name = "bloodTestServlet", value = "/bloodTestServlet")
public class bloodTestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Connection con= DB_Connection.getConnection();
            HttpSession session=request.getSession();
            PrintWriter out=response.getWriter();
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("Select amka from users where username='"+session.getAttribute("loggedIn").toString()+"'");
            rs.next();
            BloodTest bt= new BloodTest();
            bt.setAmka(rs.getString("amka"));
            bt.setTest_date(request.getParameter("tdate"));
            bt.setMedical_center(request.getParameter("medcent"));
            bt.setBlood_sugar(Double.parseDouble(request.getParameter("bloodsug")));
            bt.setCholesterol(Double.parseDouble(request.getParameter("cholest")));
            bt.setIron(Double.parseDouble(request.getParameter("iron")));
            bt.setVitamin_d3(Double.parseDouble(request.getParameter("d3")));
            bt.setVitamin_b12(Double.parseDouble(request.getParameter("b12")));
            bt.setBlood_sugar_level();
            bt.setCholesterol_level();
            bt.setIron_level();
            bt.setVitamin_d3_level();
            bt.setVitamin_b12_level();
            EditBloodTestTable ebtt= new EditBloodTestTable();
            ebtt.createNewBloodTest(bt);
            response.sendRedirect(request.getContextPath()+"/userPage.jsp");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
