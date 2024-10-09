public class Task {
    private int id;
    private Status status;
    private String text;

    public Task() {
        this.status = Status.NEW;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setText(String text) {
        this.text = text;
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
