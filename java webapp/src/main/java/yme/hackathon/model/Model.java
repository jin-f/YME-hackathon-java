package yme.hackathon.model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Model
{
  private ListCollection listCollection;
  private final ListCollectionWriter writer;

  public Model()
  {
    listCollection = new ListCollection();
    writer = new ListCollectionWriter();
  }

  public ListCollection getListCollection()
  {
    return listCollection; // This is just dummy data
  }

  public void loadLists()
  {
    ListCollectionLoader listLoader = new ListCollectionLoader();
    listCollection = listLoader.loadLists();
  }

  public void removeList(String listName)
  {
    listCollection.removeList(listName);
    writer.writeFile(listCollection);
  }

  public void renameList(String listName, String newName)
  {
    listCollection.renameList(listName, newName);
    writer.writeFile(listCollection);
  }

  public void addList(String listName)
  {
    listCollection.addList(listName, new ItemCollection());
    writer.writeFile(listCollection);
  }

  public void addItem(String listName, String itemName, String itemType, String itemBody)
  {
    listCollection.addItem(listName, itemName, itemType, itemBody);
    writer.writeFile(listCollection);
  }

  public void addComboItem(String listName, String itemName, String itemType1, String itemType2, String itemBody1, String itemBody2)
  {
    listCollection.addComboItem(listName, itemName, itemType1, itemType2, itemBody1, itemBody2);
    writer.writeFile(listCollection);
  }

  public void removeItem(String listName, String itemName)
  {
    listCollection.removeItem(listName, itemName);
    writer.writeFile(listCollection);
  }

  public void saveImage(InputStream inputStream, String fileName)
  {
    File file = new File(fileName);
    try {
      FileOutputStream outputStream = new FileOutputStream(file);
      byte[] buffer = new byte[inputStream.available()];
      inputStream.read(buffer);

      outputStream.write(buffer);
      outputStream.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public ArrayList<String> searchItem(String itemName, ArrayList<String> selectedLists)
  {
    ArrayList<String> hasItem = new ArrayList<>();
    for (String listName : selectedLists)
    {
      if (listCollection.containsItem(listName, itemName))
      {
        hasItem.add(listName);
      }
    }
    return hasItem;
  }

}
