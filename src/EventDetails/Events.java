package EventDetails;

public class Events {
    String id, name, eventDate;
    int estBudget,actualBudget,eventType;

    public Events(String id, String name, String eventDate) {
        this.id = id;
        this.name = name;
        this.eventDate = eventDate;
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

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public int getEstBudget() {
        return estBudget;
    }

    public void setEstBudget(int estBudget) {
        this.estBudget = estBudget;
    }

    public int getActualBudget() {
        return actualBudget;
    }

    public void setActualBudget(int actualBudget) {
        this.actualBudget = actualBudget;
    }

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }
}
