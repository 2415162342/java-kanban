public class Subtask extends Task {
    public int epId;
    public Status status;
    public String text;

    @Override
    public String toString(){
        return  " " + text + ", "
                + "статус " + status;
    }
}
