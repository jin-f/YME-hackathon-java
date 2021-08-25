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


@WebServlet("/newName.html")
public class RenameListServlet extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Model model = ModelFactory.getModel();
        ListCollection listCollection = model.getListCollection();
        String newName = request.getParameter("newName");
        if (listCollection.containsList(newName))
        {
            ServletContext context = getServletContext();
            RequestDispatcher dispatch = context.getRequestDispatcher("/nameTaken.html");
            dispatch.forward(request, response);
        }
        else
        {
            model.renameList(request.getParameter("renameListName"), newName);
            request.setAttribute("listCollection", listCollection);

            ServletContext context = getServletContext();
            RequestDispatcher dispatch = context.getRequestDispatcher("/viewLists.jsp");
            dispatch.forward(request, response);
        }

    }
}

