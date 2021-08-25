package yme.hackathon.model;

import java.io.File;
import java.util.HashMap;
import java.util.Set;

public class ListCollection
    /* A collection of lists, where a list is a String ItemCollection pair where the String is the list's Name */
{
    private final HashMap<String, ItemCollection> listCollection = new HashMap<>();

    public ItemCollection getList(String listName)
    {
        return listCollection.get(listName);
    }

    public Set<String> getListNames()
    {
        return listCollection.keySet();
    }


    public void addList(String listName, ItemCollection itemCollection)
    {
        listCollection.put(listName, itemCollection);
    }

    public void addItem(String listName, String itemName, String itemType, String itemBody)
    {
        ItemCollection items = listCollection.get(listName);
        if (items == null) {
            ItemCollection temp = new ItemCollection();
            temp.addItem(itemName, new Item(itemType, itemBody));
            listCollection.put(listName, temp);
        }
        else {
            items.addItem(itemName, new Item(itemType, itemBody));
            listCollection.put(listName, items);
        }
    }

    public void addComboItem(String listName, String itemName, String itemType1, String itemType2, String itemBody1, String itemBody2)
    {
        ItemCollection items = listCollection.get(listName);
        if (items == null) {
            ItemCollection temp = new ItemCollection();
            temp.addItem(itemName, new Item(itemType1, itemBody1, itemType2, itemBody2));
            listCollection.put(listName, temp);
        }
        else {
            items.addItem(itemName, new Item(itemType1, itemBody1, itemType2, itemBody2));
            listCollection.put(listName, items);
        }
    }

    public void removeItem(String listName, String itemName)
    {
        ItemCollection items = listCollection.get(listName);
        if (items != null) {
            Item item = items.removeItem(itemName);
            if (item != null) {
                removalTypeCheck1(item);
                removalTypeCheck2(item);
                listCollection.put(listName, items);
            }
        }
    }

    private void removalTypeCheck1(Item item)
    {
        if (item.getItemType1().equals("List")) {
            removeList(item.getItemBody1());
        }
        if (item.getItemType1().equals("Image")) {
            String fileName = "src/main/webapp" + item.getItemBody1();
            File image = new File(fileName);
            image.delete();
        }
    }

    private void removalTypeCheck2(Item item)
    {
        if (item.isCombo()) {
            if (item.getItemType2().equals("List")) {
                removeList(item.getItemBody2());
            }
            if (item.getItemType2().equals("Image")) {
                File image = new File("src/main/webapp" + item.getItemBody2());
                image.delete();
            }
        }
    }

    public void removeList(String listName)
    {
        ItemCollection items = getList(listName);
        if (items != null) {
            for (String itemName : items.getItemNames()) {
                Item item = items.getItem(itemName);
                removalTypeCheck1(item);
                removalTypeCheck2(item);
            }
        }
        listCollection.remove(listName);
    }

    public boolean containsList(String listName)
    {
        return listCollection.containsKey(listName);
    }

    public void renameList(String listName, String newName)
    {
        ItemCollection temp = listCollection.get(listName);
        if (listCollection.containsKey(listName)) {
            removeList(listName);
            addList(newName, temp);
        }
    }

    public boolean containsItem(String listName, String itemName)
    {
        ItemCollection items = listCollection.get(listName);
        if (items == null) {
            return false;
        }
        return items.containsItem(itemName);

    }


}
