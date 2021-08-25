package yme.hackathon.model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

public class ListCollectionWriter
{
    public void writeFile(ListCollection listCollection)
    {
        try {
            FileWriter csvWriter = new FileWriter("./data/lists.csv");
            Set<String> setOfLists = listCollection.getListNames();
            for (String listName : setOfLists) {
                csvWriter.append(listName);
                ItemCollection items = listCollection.getList(listName);
                if (items != null) {
                    writeItems(csvWriter, items);
                }
                csvWriter.append("\n");
            }
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeItems(FileWriter csvWriter, ItemCollection items) throws IOException
    {
        for (String itemName : items.getItemNames()) {
            csvWriter.append(",");
            csvWriter.append(itemName);
            csvWriter.append("::");
            Item item = items.getItem(itemName);
            csvWriter.append(item.getItemType1());
            csvWriter.append("::");
            csvWriter.append(item.getItemBody1());
            if (item.isCombo()) {
                csvWriter.append("::");
                csvWriter.append(item.getItemType2());
                csvWriter.append("::");
                csvWriter.append(item.getItemBody2());
            }
        }
    }
}
