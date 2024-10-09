public class Subtask extends Task {
    private int id;
    private int epId;
    private Status status;
    private String text;

    public Subtask() {
        this.status = Status.NEW;
    }

    public void setEpId(int epId) {
        this.epId = epId;
    }

    public int getEpId() {
        return epId;
    }

    public Status getStatus() {
        return status;
    }

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
        return  id + " " + text + ", "
                + "статус " + status;
    }
}
