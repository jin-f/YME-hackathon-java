package yme.hackathon.servlets;

import yme.hackathon.model.ItemCollection;
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


@WebServlet("/listItems.html")
public class ViewItemsServlet extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Model model = ModelFactory.getModel();
        String listName = (String) request.getParameter("listName");
        ItemCollection itemCollection = model.getListCollection().getList(listName);
        request.setAttribute("itemCollection", itemCollection);
        request.setAttribute("listName", listName);

        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/viewItems.jsp");
        dispatch.forward(request, response);
    }
}

