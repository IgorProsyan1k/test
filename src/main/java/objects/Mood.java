package objects;

public class Mood {

    public Integer index;

    public String custom = "";

    public Mood(Integer index) {
        this.index = index;
    }

    public Mood(Integer index, String custom) {
        this.index = index;
        this.custom = custom;
    }

}
