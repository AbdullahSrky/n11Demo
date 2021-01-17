package com.n11.controller;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;

import com.n11.base.SeleniumAbstractTest;
import com.n11.pages.PageN11AnaSayfa;
import com.n11.utility.ExtentTestManager;

public class TestController extends SeleniumAbstractTest {
	protected ThreadLocal<PageN11AnaSayfa> tl = new ThreadLocal<PageN11AnaSayfa>();

	@BeforeMethod
	public void Before(Method method) {

		WebDriver driver;
		ExtentTestManager.startTest(method.getName());
		driver = super.setUpBrowser();
		tl.set(new PageN11AnaSayfa(driver));
	}

	protected PageN11AnaSayfa startTest() {

		return tl.get();
	}
}
