import database.DB_Connection;
import database.tables.EditTreatmentTable;
import mainClasses.Treatment;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet(name = "therapyServlet", value = "/therapyServlet")
public class therapyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Connection con= DB_Connection.getConnection();
            HttpSession session=request.getSession();
            Statement stmt=con.createStatement();
            ResultSet rs= stmt.executeQuery("Select doctor_id from doctors where username='"+session.getAttribute("loggedIn")+"'");
            rs.next();
            Treatment tr= new Treatment();
            tr.setDoctor_id(rs.getInt("doctor_id"));
            tr.setUser_id(Integer.parseInt(request.getParameter("usid")));
            tr.setStart_date(request.getParameter("startit"));
            tr.setEnd_date(request.getParameter("endit"));
            tr.setTreatment_text(request.getParameter("treatext"));
            tr.setBloodtest_id(Integer.parseInt(request.getParameter("bloodid")));
            EditTreatmentTable ett= new EditTreatmentTable();
            ett.createNewTreatment(tr);
            response.sendRedirect(request.getContextPath()+"/doctorPage.jsp");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
