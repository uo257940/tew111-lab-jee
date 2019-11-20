package com.tew.Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CarritoCompraServlet
 */
@WebServlet("/CarritoCompraServlet")
public class CarritoCompraServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String n;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CarritoCompraServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<HTML>");
		out.println("<HEAD><TITLE>Hola Mundo!</TITLE></HEAD>");
		out.println("<BODY>");
		out.println("<FORM ACTION=\"CarritoCompraServlet\" method=\"post\">");
		out.println("<SELECT NAME=\"productos\" SIZE=\"1\"><OPTION VALUE=\"010\">La trampa</OPTION><OPTION VALUE=\"345\">Los pájaros</OPTION><OPTION VALUE=\"554\">Matrix reloaded</OPTION></SELECT>");
		out.println("<td align=\"center\"><input type=\"submit\"\r\n" + 
				"value=\"añadir al carrito...\"></td>");
		out.println("</FORM>");
		HashMap<Integer,Integer> productos =
				(HashMap<Integer,Integer>)request.getSession().getAttribute("carrito");
				if (productos == null){
					productos = new HashMap<Integer,Integer>();
				}
				else {
					out.println(productos.toString());
				}


		out.println("</BODY>");
		out.println("</HTML>");
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		@SuppressWarnings("unchecked")
		HashMap<Integer, Integer> carrito = (HashMap<Integer, Integer>) request.getSession().getAttribute("carrito");
		if (carrito == null) {
			carrito = new HashMap<Integer, Integer>();
		}
		Integer producto = Integer.parseInt(request.getParameter("productos"));
		if (producto != null) {
			Integer cantidad = (Integer) carrito.get(producto);
			if (cantidad == null)
				cantidad = new Integer(1);
			else
				cantidad = new Integer(cantidad.intValue() + 1);
			//Y añadimos el producto al carrito
			carrito.put(producto, cantidad);
			request.getSession().setAttribute("carrito", carrito);
		}
		out.print(producto);
		doGet(request, response);
	}

}
