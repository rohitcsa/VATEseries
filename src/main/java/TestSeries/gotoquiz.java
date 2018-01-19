package TestSeries;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.xmp.impl.Base64;

import java.io.PrintWriter;
import java.util.Enumeration;

public class gotoquiz extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
res.setContentType("text/html");
        PrintWriter pw = res.getWriter();

        // Let's obtains parameters name here!
        Enumeration<String> enumeration = req.getParameterNames();
    
    for(int i=1;i<=1;i++) {
            String parameterName = (String) enumeration.nextElement();
            
        
            String decode=Base64.decode(parameterName);
           
            	 pw.println("<center><h1>" + decode+"</h1></center>");
            RequestDispatcher rd=req.getRequestDispatcher("/index2.html");  
            rd.include(req, res);  
          
        }
      
        
    }
}