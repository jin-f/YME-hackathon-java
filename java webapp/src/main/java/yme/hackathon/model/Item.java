package yme.hackathon.model;

public class Item

{
    private String itemType1;
    private String itemBody1;
    private String itemType2;
    private String itemBody2;

    public Item(String itemType1, String itemBody1)
    {
        this.itemType1 = itemType1;
        this.itemBody1 = itemBody1;
    }

    public Item(String itemType1, String itemBody1, String itemType2, String itemBody2)
    {
        this.itemType1 = itemType1;
        this.itemBody1 = itemBody1;
        this.itemType2 = itemType2;
        this.itemBody2 = itemBody2;
    }

    public boolean isCombo()
    {
        return itemType2 != null;
    }

    public String getItemType1()
    {
        return itemType1;
    }

    public String getItemBody1()
    {
        return itemBody1;
    }

    public String getItemType2()
    {
        return itemType2;
    }

    public String getItemBody2()
    {
        return itemBody2;
    }
}
