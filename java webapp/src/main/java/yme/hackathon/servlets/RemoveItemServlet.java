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


@WebServlet("/removeItem.html")
public class RemoveItemServlet extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Model model = ModelFactory.getModel();
        String listName = request.getParameter("listName");
        model.removeItem(listName, request.getParameter("removeItemName"));
        ItemCollection itemCollection = model.getListCollection().getList(listName);
        request.setAttribute("listName", listName);
        request.setAttribute("itemCollection", itemCollection);

        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/viewItems.jsp");
        dispatch.forward(request, response);
    }
}

