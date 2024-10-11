public class Task {
    private int id;
    private String name;
    private Status status;
    private String text;

    public Task(String name, String text) {
        this.name = name;
        this.text = text;
        this.status = Status.NEW;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public String toString(){
        return id + " " + text + ", "
                + "статус: " + status;
    }

}
