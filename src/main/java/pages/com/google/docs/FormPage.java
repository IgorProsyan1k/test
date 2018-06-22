package pages.com.google.docs;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import objects.Mood;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class FormPage {

    public SelenideElement emailInput = $(By.xpath(".//*[@type='email']"));

    public SelenideElement dateInput = $(By.xpath(".//*[@type='date']"));

    public SelenideElement nameInput = $(By.xpath(".//*[@type='text'][contains(@class, 'quantumWizTextinputPaperinputInput')]"));

    public SelenideElement sexSelectTitle = $(".quantumWizMenuPaperselectOptionList");

    public ElementsCollection moodOptions = $$(".freebirdFormviewerViewItemsCheckboxChoice");

    public By sexSelectOptions = By.xpath(".//*[contains(@class, 'exportSelectPopup')]/*[@role='option']");

    public ElementsCollection errors = $$(".freebirdFormviewerViewItemsItemErrorMessage");

    public SelenideElement submitButton = $(".quantumWizButtonPaperbuttonEl");

    public void sendForm() {
        submitButton.click();
    }

    public FormPage setEmail(String email) {
        emailInput.setValue(email);

        return this;
    }

    public FormPage setName(String name) {
        nameInput.setValue(name);

        return this;
    }

    public FormPage setDate(String dd, String mm, String yyyy) {
        dateInput.sendKeys(String.format("%s-%s-%s", dd, mm, yyyy));

        return this;
    }

    public FormPage setDate(String[] date) {
        if(date.length == 3) {
            setDate(date[0], date[1], date[2]);
        }

        return this;
    }

    public FormPage selectSex(Integer sexIndex) {
        sexSelectTitle.click();
        $(sexSelectOptions).waitUntil(Condition.visible,2000);

        ElementsCollection options = $$(sexSelectOptions);
        Integer size = options.size();

        if (size > sexIndex) {
            options.get(sexIndex).click();
        }

        sexSelectTitle.waitUntil(Condition.not(Condition.attribute("aria-hidden", "true")), 1500);

        return this;
    }

    public FormPage selectSex(String sexIndex) {
        selectSex(Integer.parseInt(sexIndex));

        return this;
    }

    public FormPage selectMood(Mood choise) {

        switch (choise.index) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
                moodOptions.get(choise.index).find(".quantumWizTogglePapercheckboxEl").click();
                break;

            case 5:
                moodOptions.get(choise.index).find(".quantumWizTogglePapercheckboxEl").click();
                moodOptions.get(choise.index).find(".quantumWizTextinputSimpleinputInput ").setValue(choise.custom);
                break;
        }

        return this;
    }

    public String getErrorText(Integer index) {

        String result = "";

        if (errors.size() > index) {
            result = errors.get(index).getText();
        }

        return result;
    }

}
