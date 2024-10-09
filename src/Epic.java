import java.util.ArrayList;

public class Epic extends Task {
    private int id;
    private Status status;
    private String text;
    private final ArrayList<Integer> subIds = new ArrayList<>();

    public Epic() {
        this.status = Status.NEW;
    }

    public ArrayList<Integer> getSubIds() {
        return subIds;
    }

    @Override
    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString(){
        return id + " " + text + ", "
                + " статус: " + status;
    }
}
