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

@WebServlet(name = "loginServlet", value = "/loginServlet")
public class loginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session=request.getSession(true);
            PrintWriter out = response.getWriter();
            String username = request.getParameter("usrname");
            String password = request.getParameter("passwrd");
            if((username.equals("admin")&&password.equals("admin12*"))){
                session.setAttribute("loggedIn",username);
                response.setStatus(200);
                response.sendRedirect(request.getContextPath()+"/adminPage.jsp");
                return;
            }
            Connection con= DB_Connection.getConnection();
            PreparedStatement ps= con.prepareStatement("select * from users where username=?");
            ps.setString(1,username);
            ResultSet rs=ps.executeQuery();
            PreparedStatement ps2= con.prepareStatement("select * from users where password=?");
            ps2.setString(1,password);
            ResultSet rs2=ps2.executeQuery();

            if(rs.next()&&rs2.next()){
                session.setAttribute("loggedIn",username);
                response.setStatus(200);
                response.sendRedirect(request.getContextPath()+"/userPage.jsp");
                return ;
            }
            PreparedStatement ps3=con.prepareStatement("select * from doctors where username=?");
            ps3.setString(1,username);
            ResultSet rs3=ps3.executeQuery();
            PreparedStatement ps4=con.prepareStatement("select * from doctors where password=?");
            ps4.setString(1,password);
            ResultSet rs4=ps4.executeQuery();
            if(rs3.next()&&rs4.next()){
                if(rs3.getInt("certified")==1){
                    session.setAttribute("loggedIn",username);
                    response.setStatus(200);
                    response.sendRedirect(request.getContextPath()+"/doctorPage.jsp");
                }else{
                    response.setStatus(403);
                    out.println("Doctor not certified");
                }
            }else {
                response.setStatus(403);
                out.println("Wrong username or password");
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
