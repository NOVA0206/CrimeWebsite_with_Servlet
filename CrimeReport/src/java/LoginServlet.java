import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.*; 
import javax.servlet.http.*;
import javax.sql.*;
public class LoginServlet extends HttpServlet {
@Override
protected void doPost(HttpServletRequest request, HttpServletResponse
response) throws ServletException, IOException {
response.setContentType("text/html"); PrintWriter out =
response.getWriter(); try{
String firstname, lastname, contactno, emailid1, password;
firstname = request.getParameter("firstname");
lastname= request.getParameter("lastname"); 
contactno =request.getParameter("contactno"); 
emailid1 =request.getParameter("emailid1"); 
password =request.getParameter("password");
Class.forName("com.mysql.jdbc.Driver");
Connection con =
DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=convertToNull","root","Jeevan@020604");
String query = "insert into access1 values(?,?,?,?,?)";
PreparedStatement ps =con.prepareStatement(query);
ps.setString(1, firstname);
ps.setString(2, lastname); 
ps.setString(3,contactno); 
ps.setString(4, emailid1);
ps.setString(5,password);
ps.executeUpdate();
con.close();
out.print("record saved successfully!");
RequestDispatcher rd=request.getRequestDispatcher("Loged.html");
rd.forward(request, response);
}
catch(Exception e){
out.println(e);
}
}
}
