package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import entity.ContactPerson;
import entity.Passenger;
import entity.User;
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
		// need set tomcat conf/server.xml <Connector>
		// useBodyEncodingForURI=true

		PrintWriter out = response.getWriter();
		
		String pName = request.getParameter("pName");
		String pCardNumber = request.getParameter("pCardNumber");
		String cName = request.getParameter("cName");
		String cPhone = request.getParameter("cPhone");
		String cEmail = request.getParameter("cEmail");
		String username = request.getParameter("username");
		String flightId = request.getParameter("flightId");
		boolean delay = Boolean.parseBoolean(request.getParameter("delay"));
		boolean safe = Boolean.parseBoolean(request.getParameter("safe"));
		int type = Integer.parseInt(request.getParameter("level"));
		
		ContactPerson c = new ContactPerson(cName, cPhone, cEmail);
		Passenger p = new Passenger(pName, pCardNumber);
		
		int uId = Factory.getIUserService().queryUid(new User(username, null));

		Boolean flag = Factory.getITicketService().insertTicket(p, c, uId,
				flightId,delay,safe,type);

		JSONObject json = new JSONObject();
		json.put("result", flag);
		out.write(json.toString());
		out.flush();
		out.close();
		// 测试网址
		// http://127.0.0.1:8080/ServerOfPlane/servlet/TicketOrder?orderId=12312313&pName=李亮&pCardNumber=14040219940512002x&cName=张良&cPhone=13922223333&cEmail=123123@QQ.com&uId=106&flightId=hz4521&delay=true&safe=true&level=1
	}

}
