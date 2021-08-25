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


@WebServlet("/newList.html")
public class AddListServlet extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Model model = ModelFactory.getModel();
        ListCollection listCollection = model.getListCollection();
        String listName = request.getParameter("newListName");
        if (listCollection.containsList(listName)) {
            ServletContext context = getServletContext();
            RequestDispatcher dispatch = context.getRequestDispatcher("/nameTaken.html");
            dispatch.forward(request, response);
        }
        else {
            model.addList(listName);
            request.setAttribute("listCollection", listCollection);
            ServletContext context = getServletContext();
            RequestDispatcher dispatch = context.getRequestDispatcher("/viewLists.jsp");
            dispatch.forward(request, response);
        }
    }
}

