package com.ttn.training;

// Class for Question 2

public class Phone implements Comparable<Phone> {

    String name;
    String description;
    String price;
    String createdDate;

    public Phone(String Name, String desc, String Price, String created){
        name = Name;
        description = desc;
        price = Price;
        createdDate = created;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public String getPrice() {
        return price;
    }
    public String getCreatedDate() {
        return createdDate;
    }

    @Override
    public String toString() {
        return "<br> <b>Name</b>: " + name + "<br>"
                + "<b>Description</b>: " + description + "<br>"
                + "<b>Price</b>: " + price + "<br>"
                + "<b>Created On</b>: <u>" + createdDate + "</u><br>";

    }

    @Override
    public int compareTo(Phone phone) {
        return createdDate.compareTo(phone.getCreatedDate());
    }
}
