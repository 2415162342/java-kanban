public class Subtask extends Task {
    private final int epId;


    public Subtask(int id, String name, Status status, String text, int epId) {
        super(id, name, status, text);
        this.epId = epId;
    }


    public int getEpId() {
        return epId;
    }

}
