package eathealthyweb.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.Document;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import eathealthyweb.logic.ConfigurationHelper;
import eathealthyweb.logic.FoodItem;
import eathealthyweb.logic.GoogleVisionProcessor;
import eathealthyweb.logic.LoggerHelper;
import eathealthyweb.logic.NutrientDatabaseProcessor;

@WebServlet("/UploadDownloadFileServlet")
public class UploadDownloadFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ServletFileUpload uploader = null;
	@Override
	public void init() throws ServletException{
		DiskFileItemFactory fileFactory = new DiskFileItemFactory();
//		File filesDir = (File) getServletContext().getAttribute("FILES_DIR_FILE");
//		fileFactory.setRepository(filesDir);
		this.uploader = new ServletFileUpload(fileFactory);
		//new LoggerHelper();		
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("In doGet servlet method");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!ServletFileUpload.isMultipartContent(request)){
			throw new ServletException("Content type is not multipart/form-data");
		}
		System.out.println(request.getParameter("image"));
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String msg = "";
//		out.write("<html><head></head><body>");
		try {
			List<FileItem> fileItemsList = uploader.parseRequest(request);
			Iterator<FileItem> fileItemsIterator = fileItemsList.iterator();
			while(fileItemsIterator.hasNext()){
				FileItem fileItem = fileItemsIterator.next();
				System.out.println("FileName="+fileItem.getName());
				System.out.println("ContentType="+fileItem.getContentType());
				
				if(!fileItem.getContentType().startsWith("image"))
					throw new ServletException("File uploaded is not an image. Kindly upload a valid image file.");
				if(fileItem.getName().isEmpty())
					throw new ServletException("File uploaded is not an image. Kindly upload a valid image file.");
				GoogleVisionProcessor proc = new GoogleVisionProcessor(fileItem.get());
				FoodItem fItem = proc.getFoodItem();
				NutrientDatabaseProcessor ndb = new NutrientDatabaseProcessor();
				msg = ndb.getHealthierFood(fItem);;
				//out.write(healthierOption);
//				out.write("<br>");
			}
		} catch (Exception e) {
			msg = "Exception in uploading file. "  + e.getMessage();
			//out.write("Exception in uploading file. "  + e.getMessage());
			System.out.print("Exception in uploading file. "  + e.getMessage());
		}
		finally
		{
			out.println("<script type=\"text/javascript\">");
			out.println("alert('" + msg + "');");
			out.println("location='UploadImage.html';");
			out.println("</script>");
		}
//		out.write("</body></html>");
	}

}
