package yme.hackathon.servlets;

import yme.hackathon.model.ItemCollection;
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



@WebServlet("/searchList.html")
public class SearchListServlet extends HttpServlet
{
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {

    Model model = ModelFactory.getModel();
    String listName = request.getParameter("searchListName");
    ListCollection listCollection = model.getListCollection();
    if (listCollection.containsList(listName))
    {
      ItemCollection searchResult = model.getListCollection().getList(listName);
      request.setAttribute("itemCollection", searchResult);
      request.setAttribute("listName", listName);


      ServletContext context = getServletContext();
      RequestDispatcher dispatch = context.getRequestDispatcher("/viewItems.jsp");
      dispatch.forward(request, response);
    }
    else {
      ServletContext context = getServletContext();
      RequestDispatcher dispatch = context.getRequestDispatcher("/listUnavailable.html");
      dispatch.forward(request, response);
    }
  }
}
