package com.n11.commons;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.n11.basepages.SeleniumAbstractPage;

public class N11Commons extends SeleniumAbstractPage {

	public N11Commons(WebDriver driver) {

		super(driver);
		PageFactory.initElements(this.driver, this);
	}

	public void control(WebElement elem, String onTrue, String onFalse) {

		try {
			if (getTextOfElement(elem).contains(onTrue)) {
				LogPASS(onTrue);
			} else {
				LogFAIL(onFalse);
				Assert.assertTrue(false);
			}
		} catch (Exception e) {
			LogFAIL(onFalse);
			Assert.assertTrue(false);
		}
	}
}
