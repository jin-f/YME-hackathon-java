package yme.hackathon.servlets;

import yme.hackathon.model.ListCollection;
import yme.hackathon.model.Model;
import yme.hackathon.model.ModelFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



@WebServlet("/viewList.html")
public class ViewListServlet extends HttpServlet
{

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
  {
    Model model = ModelFactory.getModel();
    ListCollection listCollection = model.getListCollection();
    request.setAttribute("listCollection", listCollection);

    ServletContext context = getServletContext();
    RequestDispatcher dispatch = context.getRequestDispatcher("/viewLists.jsp");
    dispatch.forward(request, response);
  }
}
