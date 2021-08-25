package yme.hackathon.model;

 import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ListCollectionLoader
{
    public ListCollection loadLists()
    {
        ListCollection lists = new ListCollection();
        try {
            lists = readFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lists;
    }

    private ListCollection readFile() throws IOException
    {
        ListCollection lists = new ListCollection();
        BufferedReader CSVReader = new BufferedReader(new FileReader("./data/lists.csv"));
        String row;
        while ((row = CSVReader.readLine()) != null) {
            if (!row.equals("")) {
                String[] s = row.split(",");
                String listName = s[0];
                lists.addList(listName, splitItems(s));
            }
        }
        CSVReader.close();
        return lists;
    }

    private ItemCollection splitItems(String[] s)
    {
        ItemCollection items = new ItemCollection();
        for (int i = 1; i < s.length; i++) {
            String[] ss = s[i].split("::");
            if (ss.length == 3) {
                items.addItem(ss[0], new Item(ss[1], ss[2]));
            }
            else {
                items.addItem(ss[0], new Item(ss[1], ss[2], ss[3], ss[4]));
            }
        }
        return items;
    }
}
