package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import entity.ContactPerson;
import entity.Passenger;
import entity.Ticket;
import factory.Factory;

public class TicketOrder extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		//need set tomcat conf/server.xml <Connector> useBodyEncodingForURI=true
		
		PrintWriter out = response.getWriter();
		long orderId = Long.parseLong(request.getParameter("orderId"));
		
		String pName = request.getParameter("pName");
		String pCardNumber = request.getParameter("pCardNumber");
		Passenger p = new Passenger(pName, pCardNumber);
		
		String cName = request.getParameter("cName");
		String cPhone = request.getParameter("cPhone");
		String cEmail = request.getParameter("cEmail");
		ContactPerson c = new ContactPerson(cName, cPhone, cEmail);
		
		int uId = Integer.parseInt(request.getParameter("uId"));
		
		String flightId = request.getParameter("flightId");
		
		Boolean flag = Factory.getITicketService().insertTicket(orderId, p, c, uId, flightId);
		
		JSONArray jsonArray = new JSONArray();
		if(flag==true){
			List<Ticket> ticket = Factory.getITicketService().queryTicketContent(uId);
			jsonArray = new JSONArray();
			
			for(Ticket t:ticket){
				jsonArray.put(t.getJsonObject());
			}
		}else{
			//TODO: complete
		}
		out.write(jsonArray.toString());
		out.flush();
		out.close();
		//测试网址
		//http://127.0.0.1:8080/ServerOfPlane/servlet/TicketOrder?orderId=12312313&pName=李亮&pCardNumber=14040219940512002x&cName=张良&cPhone=13922223333&cEmail=123123@QQ.com&uId=106&flightId=hz4521
	}

}