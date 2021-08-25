package yme.hackathon.model;

import java.util.HashMap;
import java.util.Set;

public class ItemCollection
        /* Represents a collection of String Item pairs where the string is the Item Name
         */
{
    private final HashMap<String, Item> itemCollection = new HashMap<>();

    public Item getItem(String itemName)
    {
        return itemCollection.get(itemName);
    }

    public void addItem(String itemName, Item item)
    {
        itemCollection.put(itemName, item);
    }

    public Item removeItem(String itemName)
    {
        Item item = itemCollection.get(itemName);
        itemCollection.remove(itemName);
        return item;
    }

    public boolean containsItem(String itemName)
    {
        return getItem(itemName) != null;
    }



    public Set<String> getItemNames()
    {
        return itemCollection.keySet();
    }

    public int getSize()
    {
        return itemCollection.size();
    }
}
