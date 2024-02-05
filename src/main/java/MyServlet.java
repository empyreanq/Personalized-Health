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


@WebServlet(name = "MyServlet", value = "/MyServlet")
public class MyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            PrintWriter out = response.getWriter();
            String email = request.getParameter("email");
            Connection con = DB_Connection.getConnection();
            PreparedStatement ps = con.prepareStatement("select * from users where email=?");
            ps.setString(1,email);
            ResultSet rs = ps.executeQuery();
            PreparedStatement ps2 = con.prepareStatement("select * from doctors where email=?");
            ps2.setString(1,email);
            ResultSet rs2 = ps2.executeQuery();
            if(rs.next()||rs2.next()){
                out.println("<font color=red> Email already in use</font>");
            }else{
                out.println("<font color=green> Email available</font>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        if(Objects.equals(request.getParameter("accountype"), "user")) {
            SimpleUser u = new SimpleUser();
            u.setUsername((request.getParameter("username")));
            u.setPassword((request.getParameter("password")));
            u.setEmail(request.getParameter("email"));
            u.setFirstname(request.getParameter("firstname"));
            u.setLastname(request.getParameter("lastname"));
            u.setBirthdate(request.getParameter("birthdate"));
            u.setGender(request.getParameter("gender"));
            u.setAmka(request.getParameter("amka"));
            u.setCountry(request.getParameter("country"));
            u.setCity(request.getParameter("city"));
            u.setAddress(request.getParameter("address"));
            u.setLat(123.123);
            u.setLon(123.123);
            u.setTelephone(request.getParameter("telephone"));
            u.setHeight(Integer.parseInt(request.getParameter("height")));
            u.setWeight(Double.parseDouble(request.getParameter("weight")));
            u.setBloodDonor(Integer.parseInt(request.getParameter("blooddonor")));
            u.setBloodtype(request.getParameter("bloodtype"));
            EditSimpleUserTable eut = new EditSimpleUserTable();
            out.println(eut.simpleUserToJSON(u));
            out.println("\nRegistration Completed Successfully");
            try {
                eut.addNewSimpleUser(u);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }else{
            Doctor d = new Doctor();
            d.setUsername((request.getParameter("username")));
            d.setPassword((request.getParameter("password")));
            d.setEmail(request.getParameter("email"));
            d.setFirstname(request.getParameter("firstname"));
            d.setLastname(request.getParameter("lastname"));
            d.setBirthdate(request.getParameter("birthdate"));
            d.setGender(request.getParameter("gender"));
            d.setAmka(request.getParameter("amka"));
            d.setCountry(request.getParameter("country"));
            d.setCity(request.getParameter("city"));
            d.setAddress(request.getParameter("address"));
            d.setLat(123.123);
            d.setLon(123.123);
            d.setTelephone(request.getParameter("telephone"));
            d.setHeight(Integer.parseInt(request.getParameter("height")));
            d.setWeight(Double.parseDouble(request.getParameter("weight")));
            d.setBloodDonor(Integer.parseInt(request.getParameter("blooddonor")));
            d.setBloodtype(request.getParameter("bloodtype"));
            d.setSpecialty(request.getParameter("specialty"));
            d.setDoctor_info(request.getParameter("doctor_info"));
            d.setCertified(0);
            EditDoctorTable edt = new EditDoctorTable();
            out.println(edt.doctorToJSON(d));
            out.println("\nRegistration completed Successfully but wait for Admin certification.");
            try {
                edt.addNewDoctor(d);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }
}
