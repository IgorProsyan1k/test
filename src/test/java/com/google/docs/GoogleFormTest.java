package com.google.docs;

import dataProviders.com.google.docs.SetDataProvider;
import objects.Human;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.com.google.docs.FormPage;
import pages.com.google.docs.SuccessPage;

import static com.codeborne.selenide.Selenide.confirm;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;

public class GoogleFormTest extends BaseTest {

    public static final String TEST_URL = "https://docs.google.com/forms/d/e/1FAIpQLSdqT5F9_qhPDmJ4lfIH7buVkUvjf4LS9ODdqD7PYfVbfFTnpA/viewform";

    String[] errors = SetDataProvider.errors;

    @Test
    public void requiredFields() {
        SoftAssert softAsserts = new SoftAssert();

        open(TEST_URL);
        FormPage page = new FormPage();
        page.sendForm();

        softAsserts.assertEquals(page.getErrorText(0), errors[0]);
        softAsserts.assertEquals(page.getErrorText(1), errors[0]);
        softAsserts.assertEquals(page.getErrorText(2), errors[0]);
        softAsserts.assertEquals(page.getErrorText(3), errors[0]);
        softAsserts.assertEquals(page.getErrorText(4), errors[0]);

        softAsserts.assertAll();
    }

    @Test(dataProvider = "special errors", dataProviderClass = SetDataProvider.class)
    public void specialErrors(Object[] humans , Object[] errors) {
        SoftAssert softAsserts = new SoftAssert();
        Human human = (Human) humans[0];

        open(TEST_URL);
        try {
            confirm();
        } catch (Exception e) {
            //if alert absent dont close test
        }
        FormPage page = new FormPage();
        page
                .setEmail(human.email)
                .setDate(human.date)
                .setName(human.name)
                .selectSex(human.sex)
                .selectMood(human.mood)
                .sendForm();

        softAsserts.assertEquals(page.getErrorText(0), errors[0]);
        softAsserts.assertEquals(page.getErrorText(1), errors[1]);
        softAsserts.assertEquals(page.getErrorText(2), errors[2]);
        softAsserts.assertEquals(page.getErrorText(3), errors[3]);
        softAsserts.assertEquals(page.getErrorText(4), errors[4]);

        softAsserts.assertAll();
    }

    @Test(dataProvider = "success", dataProviderClass = SetDataProvider.class)
    public void success(Human human) {
        SoftAssert softAsserts = new SoftAssert();

        open(TEST_URL);

        FormPage page = new FormPage();
        page
                .setEmail(human.email)
                .setDate(human.date)
                .setName(human.name)
                .selectSex(human.sex)
                .selectMood(human.mood)
                .sendForm();

        sleep(3000);


        SuccessPage successPage = new SuccessPage();

        softAsserts.assertTrue(successPage.isFormSend());

        softAsserts.assertAll();
    }
}
