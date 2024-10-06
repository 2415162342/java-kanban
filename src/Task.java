public class Task {
    public int id;
    public Status status;
    public String text;

    @Override
    public String toString(){
        return " " + text + ", "
                + "статус: " + status;
    }

}
