package TestSeries;



import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;

import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Base64;
import java.util.Properties; 
/**
 * Servlet implementation class decode_encode
 */
public class decode_encode extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public decode_encode() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String str1=request.getParameter("name");
		String str2=request.getParameter("email");
		String str3=request.getParameter("quiz_level");
		String str4=request.getParameter("job_role");
		
		Base64.Encoder encoder = Base64.getEncoder();  
		String strencode1 = encoder.encodeToString(str1.getBytes());  
		String strencode2 = encoder.encodeToString(str2.getBytes());
		String strencode3 = encoder.encodeToString(str3.getBytes());
		String strencode4 = encoder.encodeToString(str4.getBytes());
		Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
	
try{	
	Class.forName("com.mysql.jdbc.Driver");
	Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/quizdb","root","2309");
	PreparedStatement pst=con.prepareStatement("insert into userquiz(username,email,date,job_role,quiz_level)values(?,?,?,?,?)");
	pst.setString(1, str1);
	pst.setString(2, str2);
	pst.setDate(3, sqlDate);
	pst.setString(4, str3);
	pst.setString(5, str4);
	pst.executeUpdate();
}catch(Exception e){
	e.printStackTrace();
}
		String smtpHost = "smtp.gmail.com"; // replace this with a valid host
		int smtpPort = 587; // replace this with a valid port

		final String sender = "rohitcsa.rg@gmail.com";
		final String password = "1200882309";
		String recipient1 = "crosstab.vate@gmail.com";
		Properties properties = new Properties();
		properties.put("mail.smtp.host", smtpHost);
		properties.put("mail.smtp.port", smtpPort);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		properties.put("mail.smtp.debug", "true");
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(sender, password);
			}
		};
		Session session = Session.getDefaultInstance(properties, auth);
		 try{ 
			 StringBuffer sb = new StringBuffer();

	         MimeMessage message = new MimeMessage(session);  
	      
	         message.addRecipient(Message.RecipientType.TO,new InternetAddress(str2));  
	         message.setSubject("Quiz link url");  
	         sb.append("Please continue your exam by clicking this Url");  
	         sb.append("\n");
	    sb.append("http://localhost:8000/Quiz/gotoquiz?"+strencode1+"&"+strencode2+"&"+strencode3+"&"+strencode4); 
	 
	     
	         message.setText(sb.toString());
	        
	         // Send message  
	         Transport.send(message);  
	         System.out.println("message sent successfully....");  
	  response.sendRedirect("admin.html");
	      }catch (MessagingException mex) {mex.printStackTrace();}  
	   }  
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	

