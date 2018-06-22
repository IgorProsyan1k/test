package dataProviders.com.google.docs;

import objects.Human;
import objects.Mood;
import org.testng.annotations.DataProvider;

public class SetDataProvider {

    public static final Integer NONE = 0;
    public static final Integer MAN = 1;
    public static final Integer WOMAN = 2;

    public static final String[] errors = new String[]{
            "Это обязательный вопрос.",
            "Укажите действительный адрес эл. почты",
            "Недопустимая дата.",
            "Максимальное количество символов 20 превышено"
    };

    @DataProvider(name = "special errors")
    public Object[][] createErrors() {
        return new Object[][][]
                {
                        {
                                {
                                        new Human(
                                                "1234234",
                                                new String[]{"01", "12", "4564565"},
                                                "ddsdfs$@#$@#╨-╘XM○",
                                                NONE,
                                                new Mood(-1))
                                },
                                {
                                        errors[1],
                                        errors[2],
                                        errors[3],
                                        errors[0],
                                        errors[0]
                                }
                        },
                        {
                                {
                                        new Human(
                                                "1234234",
                                                new String[]{"01", "12", "2000"},
                                                "",
                                                MAN,
                                                new Mood(-1))
                                },
                                {
                                        errors[1],
                                        "",
                                        errors[0],
                                        "",
                                        errors[0]
                                }
                        },
                        {
                                {
                                        new Human(
                                                "normal@email.com",
                                                new String[]{"17", "05", "1995"},
                                                "Lucy",
                                                WOMAN,
                                                new Mood(-1))
                                },
                                {
                                        "",
                                        "",
                                        "",
                                        "",
                                        errors[0]
                                }
                        },
                        {
                                {
                                        new Human(
                                                "none@l.1",
                                                new String[]{"31", "02", "44464565"},
                                                "Sarah",
                                                WOMAN,
                                                new Mood(4, "Lovely"))
                                },
                                {
                                        errors[1],
                                        errors[2],
                                        "",
                                        "",
                                        ""
                                }
                        },

                };
    }

    @DataProvider(name = "success")
    public Object[] createSuccess() {
        return new Object[]{
                new Human(
                        "kim@email.com",
                        new String[]{"27", "09", "2000"},
                        "Kim",
                        MAN,
                        new Mood(2))
        };
    }

}
