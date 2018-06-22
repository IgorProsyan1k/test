package objects;

public class Human {

    public String email;
    public String[] date;
    public String name;
    public Integer sex;
    public Mood mood;

    public Human(String email, String[] date, String name, Integer sex, Mood mood) {
        this.email = email;
        this.date = date;
        this.name = name;
        this.sex = sex;
        this.mood = mood;
    }

}
