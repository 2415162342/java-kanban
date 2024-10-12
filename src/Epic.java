import java.util.ArrayList;

public class Epic extends Task {
    private final ArrayList<Integer> subIds;


    public Epic(int id, String name, Status status, String text) {
        super(id, name, status, text);
        subIds = new ArrayList<>();
    }

    public ArrayList<Integer> getSubIds() {
        return subIds;
    }

}
