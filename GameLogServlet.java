import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.net.*;

public class GameLogServlet extends HttpServlet
{
	private static String message = "Error during Servlet processing";
     
    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            int len = req.getContentLength();
            byte[] input = new byte[len];
         
            ServletInputStream sin = req.getInputStream();
            int c, count = 0 ;
            while ((c = sin.read(input, count, input.length-count)) != -1) {
                count +=c;
            }
            sin.close();
         
            String inString = new String(input);
            int index = inString.indexOf("=");
            if (index == -1) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().print(message + " did not have right index");
                resp.getWriter().close();
                return;
            }
            String value = inString.substring(index + 1);
             
            //decode application/x-www-form-urlencoded string
            String decodedString = URLDecoder.decode(value, "UTF-8");
             
			// Separate out file and what to write to the file
			String[] fileAndContents = decodedString.split(";");
			 
			// Now, let's try to write this to a file
			File file = new File(".." + File.separator + "cardplaying_logs" + File.separator + fileAndContents[0]);
            boolean isCreated = file.createNewFile() || file.exists();
			
			if (isCreated == false)
			{
				throw new IOException("file wasn't created.");
			}
            FileOutputStream fout = new FileOutputStream(file, true);
            fout.write(fileAndContents[1].getBytes());
            fout.close();
			
// set the response code and write the response data
            resp.setStatus(HttpServletResponse.SC_OK);
            OutputStreamWriter writer = new OutputStreamWriter(resp.getOutputStream());			
            writer.write("ok");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            try{
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().print(e.getMessage());
                resp.getWriter().close();
            } catch (IOException ioe) {
            }
        }
         
    } 
         
}