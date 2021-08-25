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
import java.util.ArrayList;

@WebServlet("/findItem.html")
public class SearchItemServlet extends HttpServlet
{
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {

    Model model = ModelFactory.getModel();
    String itemName = request.getParameter("itemName");
    ListCollection lists = model.getListCollection();

    ArrayList<String> selectedLists = new ArrayList<>();
    if (lists != null) {
      for (String listNames : lists.getListNames())
      {
        char firstChar = listNames.charAt(0);
        if (firstChar != '*')
        {
          String listName = request.getParameter(listNames);
          if (listName != null)
          {
            selectedLists.add(listName);
          }
        }
      }
    }
    ArrayList<String> hasItem = model.searchItem(itemName, selectedLists);

    request.setAttribute("lists", lists);
    request.setAttribute("hasItem", hasItem);
    request.setAttribute("itemName", itemName);

    ServletContext context = getServletContext();
    RequestDispatcher dispatch = context.getRequestDispatcher("/itemSearchResult.jsp");
    dispatch.forward(request, response);
  }
}
