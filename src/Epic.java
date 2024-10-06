import java.util.ArrayList;

public class Epic extends Task {
    public int id;
    public Status status;
    public String text;
    ArrayList<Integer> subIds = new ArrayList<>();

    @Override
    public String toString(){
        return " " + text + ", "
                + " статус: " + status;
    }
}
