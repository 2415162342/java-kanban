public class Task {
    private int id;
    private String name;
    private Status status;
    private String text;

    public Task(int id, String name, Status status, String text) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.text = text;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString(){
        return id + " " + text + ", "
                + "статус: " + status;
    }

}
