public class Task {

    private int id;
    private final String name;
    private final String text;
    private Status status;

   public Task (String name, String text) {
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", text='" + text + '\'' +
                ", status=" + status +
                '}';
    }
}