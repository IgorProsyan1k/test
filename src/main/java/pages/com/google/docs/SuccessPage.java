package pages.com.google.docs;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;


public class SuccessPage {

    public static final String successMessage = "Ответ записан.";

    public SelenideElement emailInput = $(".freebirdFormviewerViewResponseConfirmationMessage");

    public boolean isFormSend() {
        boolean result = emailInput.has(Condition.text(successMessage));

        return result;
    }
}
