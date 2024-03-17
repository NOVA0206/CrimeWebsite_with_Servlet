import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Data")
public class DataServlet extends HttpServlet {
    private static final String query = "SELECT name, age, crime, description FROM crime";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // get PrintWriter
        PrintWriter pw = res.getWriter();
        // set content type
        res.setContentType("text/html");
        // LOAD jdbc driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException cnf) {
            cnf.printStackTrace();
        }
        // generate the connection
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=convertToNull", "root", "Jeevan@020604");
             PreparedStatement ps = con.prepareStatement(query);) {
            ResultSet rs = ps.executeQuery();
            pw.println("<!DOCTYPE html>");
            pw.println("<html>");
            pw.println("<head>");
            pw.println("<title>Crime Records</title>");
            pw.println("<style>");
            pw.println("body {");
            pw.println("  font-family: Arial, sans-serif;");
            pw.println("  background-image: url('https://i.pinimg.com/564x/00/bb/cf/00bbcf199caef1e4f6c316e17dac97e1.jpg');");
            pw.println("  background-size: 75%;");
            pw.println("}");
            pw.println("table {");
            pw.println("    border-collapse: collapse;");
            pw.println("    width: 100%;");
            pw.println("}");
            pw.println("th, td {");
            pw.println("    padding: 12px;");
            pw.println("    text-align: left;");
            pw.println("    border-bottom: 1px solid #ddd;");
            pw.println("}");
            pw.println("th {");
            pw.println("    background-color: #f2f2f2;");
            pw.println("color: #333;");
            pw.println("}");
            pw.println("td {");
            pw.println("  background-color: #fff;");
            pw.println("  color: #333;");
            pw.println("}");
            pw.println("tr:hover {");
            pw.println("  background-color: #f5f5f5;");
            pw.println("}");
            pw.println(".caption {");
            pw.println("  font-size: 24px;");
            pw.println("  font-weight: bold;");
            pw.println("  margin-bottom: 20px;");
            pw.println("}");
            pw.println("</style>");
            pw.println("</head>");
            pw.println("<body>");
            pw.println("<center>");            
            pw.println("<h2 style='color:white; font-weight:bold; font-family:time new roman;'>Crime Records</h2>");
            pw.println("<table>");
            pw.println("<tr>");
            pw.println("<th>Name</th>");
            pw.println("<th>Age</th>");
            pw.println("<th>Crime</th>");
            pw.println("<th>Description</th>");
            pw.println("</tr>");

            while (rs.next()) {
                pw.println("<tr>");
                pw.println("<td>" + rs.getString(1) + "</td>");
                pw.println("<td>" + rs.getString(2) + "</td>");
                pw.println("<td>" + rs.getString(3) + "</td>");
                pw.println("<td>" + rs.getString(4) + "</td>");
                pw.println("</tr>");
            }
            pw.println("</table>");
            pw.println("</center>"); 
            pw.println("</body>");   
            pw.println("</html>");   
        } catch (SQLException ex) {
            Logger.getLogger(DataServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req, res);
    }
}
