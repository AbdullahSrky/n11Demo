package com.n11.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.n11.commons.N11Commons;
import com.n11.data.GetData.Data;
import com.n11.utility.log;

public class PageN11Login {

	N11Commons lib;
	Robot robot;
	int delay;

	public PageN11Login(N11Commons lib) {

		this.lib = lib;
		PageFactory.initElements(this.lib.driver, this);
		//		lib.Control(lib.isElementExist(By.xpath("//div[@class='blockWrap']/h2[text()='Giriş Yap']")), "'Giriş Yap' sayfası açıldı.", "'Giriş Yap' sayfası açılamadı!");

		try {
			robot = new Robot();
		} catch (AWTException e) {
		}
		delay = 50;
	}

	@FindBy(how = How.ID, using = "email")
	WebElement txtEmail;
	@FindBy(how = How.ID, using = "password")
	WebElement txtSifre;
	@FindBy(how = How.ID, using = "loginButton")
	WebElement btnGirisYap;

	public PageN11AnaSayfa login(Data email, Data password) {

		emailGir(email.getValue()).sifreGir(password.getValue())
		                          .girisYapaTikla();

		return new PageN11AnaSayfa(lib);
	}

	private PageN11Login emailGir(String email) {

		robot.delay(1500);
		sendKeys(email);

		//		lib.sendKeys(txtEmail, email);
		return this;
	}

	private PageN11Login sifreGir(String sifre) {

		robot.keyPress(KeyEvent.VK_TAB);
		robot.delay(delay);
		robot.keyRelease(KeyEvent.VK_TAB);
		robot.delay(delay);

		sendKeys(sifre);

		//		lib.sendKeys(txtSifre, sifre);
		return this;
	}

	private PageN11AnaSayfa girisYapaTikla() {

		robot.keyPress(KeyEvent.VK_ENTER);
		robot.delay(delay);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.delay(delay);

		//				lib.click(btnGirisYap);
		return new PageN11AnaSayfa(lib);
	}

	private void sendKeys(String keys) {

		for (char c : keys.toCharArray()) {
			try {
				if (c == '@') {
					try {
						robot.keyPress(KeyEvent.VK_ALT);
						robot.keyPress(KeyEvent.VK_NUMPAD0);
						robot.keyRelease(KeyEvent.VK_NUMPAD0);
						robot.keyPress(KeyEvent.VK_NUMPAD6);
						robot.keyRelease(KeyEvent.VK_NUMPAD6);
						robot.keyPress(KeyEvent.VK_NUMPAD4);
						robot.keyRelease(KeyEvent.VK_NUMPAD4);
						robot.keyRelease(KeyEvent.VK_ALT);
						robot.delay(delay);
					} catch (Exception e2) {
						log.info("'@' " + e2.getMessage());
					}
					continue;
				}

				if (c == 'i') {
					try {
						robot.keyPress(KeyEvent.VK_ALT);
						robot.keyPress(KeyEvent.VK_NUMPAD0);
						robot.keyRelease(KeyEvent.VK_NUMPAD0);
						robot.keyPress(KeyEvent.VK_NUMPAD1);
						robot.keyRelease(KeyEvent.VK_NUMPAD1);
						robot.keyPress(KeyEvent.VK_NUMPAD0);
						robot.keyRelease(KeyEvent.VK_NUMPAD0);
						robot.keyPress(KeyEvent.VK_NUMPAD5);
						robot.keyRelease(KeyEvent.VK_NUMPAD5);
						robot.keyRelease(KeyEvent.VK_ALT);
						robot.delay(delay);
					} catch (Exception e2) {
						log.info("'i' " + e2.getMessage());
					}
					continue;
				}

				int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
				if (KeyEvent.CHAR_UNDEFINED == keyCode) {
					throw new RuntimeException("Key code not found for character '" + c + "'");
				}
				robot.keyPress(keyCode);
				robot.delay(delay);
				robot.keyRelease(keyCode);
				robot.delay(delay);
			} catch (Exception e) {
				log.error(e.getMessage());
				log.info(e.getMessage());
				try {
					robot.keyPress(KeyEvent.VK_AT);
					robot.delay(delay);
					robot.keyRelease(KeyEvent.VK_AT);
					robot.delay(delay);
				} catch (Exception e2) {
					log.info(e2.getMessage());
				}
			}
		}
	}
}
