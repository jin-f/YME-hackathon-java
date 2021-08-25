package yme.hackathon.servlets;

import yme.hackathon.model.ItemCollection;
import yme.hackathon.model.Model;
import yme.hackathon.model.ModelFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;


@WebServlet("/newItem.html")
@MultipartConfig
public class AddItemServlet extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Model model = ModelFactory.getModel();
        String listName = request.getParameter("listName");
        String itemType1 = request.getParameter("itemType1");
        String itemType2 = request.getParameter("itemType2");
        String itemName = request.getParameter("newItemName");
        String itemBody1 = request.getParameter("itemBody1");
        String itemBody2 = request.getParameter("itemBody2");
        if (model.getListCollection().containsItem(listName, itemName)) {
            nameTaken(request, response);
        }
        else {
            addItemCheck(request, model, listName, itemType1, itemType2, itemName, itemBody1, itemBody2);

            viewItems(request, response, model, listName);
        }

    }

    private void viewItems(HttpServletRequest request, HttpServletResponse response, Model model, String listName) throws ServletException, IOException
    {
        ItemCollection itemCollection = model.getListCollection().getList(request.getParameter("listName"));
        request.setAttribute("listName", listName);
        request.setAttribute("itemCollection", itemCollection);

        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/viewItems.jsp");
        dispatch.forward(request, response);
    }

    private void addItemCheck(HttpServletRequest request, Model model, String listName,
                              String itemType1, String itemType2, String itemName, String itemBody1,
                              String itemBody2) throws IOException, ServletException
    {
        if (itemType1.equals("Image")) {
            Part part = request.getPart("itemBody1");
            itemBody1 = saveImage(model, listName, itemName, part);
        }
        if (itemType2 != null) {
            if (itemType2.equals("Image")) {
                Part part = request.getPart("itemBody2");
                itemBody2 = saveImage(model, listName, itemName, part);
            }
            model.addComboItem(listName, itemName, itemType1, itemType2, itemBody1, itemBody2);
        }
        else {
            model.addItem(listName, itemName, itemType1, itemBody1);
        }
    }

    private void nameTaken(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/nameTaken.html");
        dispatch.forward(request, response);
    }

    private String saveImage(Model model, String listName, String itemName, Part part) throws IOException
    {
        String itemBody1;
        InputStream inputStream = part.getInputStream();
        String fileName = part.getSubmittedFileName();
        int index = fileName.lastIndexOf('.');
        String extension = fileName.substring(index);

        String filePath = "src/main/webapp/Images/" + listName + itemName + extension;
        model.saveImage(inputStream, filePath);
        itemBody1 = "/Images/" + listName + itemName + extension;
        return itemBody1;
    }
}

