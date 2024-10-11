import java.util.ArrayList;

public class Epic extends Task {

    public Epic(String name, String text) {
        super(name, text);
    }

    private final ArrayList<Integer> subIds = new ArrayList<>();

    public ArrayList<Integer> getSubIds() {
        return subIds;
    }

}
