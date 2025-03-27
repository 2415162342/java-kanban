
public class Subtask extends Task {

    private final int epicId;

    public Subtask(String name, String text,int epicId) {
        super(name, text);
        this.epicId = epicId;
    }

    public int getEpicId() {
        return epicId;
    }
}
