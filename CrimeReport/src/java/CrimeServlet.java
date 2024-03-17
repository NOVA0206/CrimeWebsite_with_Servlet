import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.*; 
import javax.servlet.http.*;
import javax.sql.*;
public class CrimeServlet extends HttpServlet {
@Override
protected void doPost(HttpServletRequest request, HttpServletResponse
response) throws ServletException, IOException {
response.setContentType("text/html"); PrintWriter out =
response.getWriter(); try{
String name, age, crime, description;
name = request.getParameter("name");
age= request.getParameter("age"); 
crime =request.getParameter("crime"); 
description =request.getParameter("description"); 
Class.forName("com.mysql.jdbc.Driver");
Connection con =
DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=convertToNull","root","Jeevan@020604");
String query = "insert into crime values(?,?,?,?)";
PreparedStatement ps =con.prepareStatement(query);
ps.setString(1, name);
ps.setString(2, age); 
ps.setString(3,crime); 
ps.setString(4, description);

ps.executeUpdate();
con.close();
out.print("record saved successfully!");
RequestDispatcher rd=request.getRequestDispatcher("Thank.html");
rd.forward(request, response);
}
catch(Exception e){
out.println(e);
}
}
}
