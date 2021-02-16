package Accounts.Summary.EventSummary;

public class Event {
    String id,name,date;
    int budget;

    public Event(String id, String name, String date, int budget) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.budget = budget;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }
}
