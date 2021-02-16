package EventDetails.EventHistory;


public class History {
    String name,id;
    String eventDate;
    int actBudget,estBudget;

    public History(String name, String id, String eventDate, int actBudget, int estBudget) {
        this.name = name;
        this.id = id;
        this.eventDate = eventDate;
        this.actBudget = actBudget;
        this.estBudget = estBudget;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public int getActBudget() {
        return actBudget;
    }

    public void setActBudget(int actBudget) {
        this.actBudget = actBudget;
    }

    public int getEstBudget() {
        return estBudget;
    }

    public void setEstBudget(int estBudget) {
        this.estBudget = estBudget;
    }
}
