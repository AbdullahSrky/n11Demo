package com.n11.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.n11.commons.N11Commons;
import com.n11.data.GetData.Data;
import com.n11.data.GetData.Url;

public class PageN11AnaSayfa {

	N11Commons lib;

	public PageN11AnaSayfa(WebDriver driver) {

		lib = new N11Commons(driver);
		PageFactory.initElements(driver, this);
	}

	public PageN11AnaSayfa(N11Commons lib) {

		this.lib = lib;
		PageFactory.initElements(this.lib.driver, this);
	}

	@FindBy(how = How.XPATH, using = "//a[@class='btnSignIn']")
	WebElement btnGirisYap;
	@FindBy(how = How.ID, using = "searchData")
	WebElement txtArama;
	@FindBy(how = How.XPATH, using = "//a[@class='searchBtn']")
	WebElement btnAra;

	public PageN11AnaSayfa anaSayfayaGit() {

		lib.navigateTo(Url.N11_URL);

		lib.Control(lib.isElementExist(By.xpath("//a[@class='logo  home ']"))
		        && lib.isElementExist(By.xpath("//a[@class='btnSignIn']")), "'Ana Sayfa' sayfası açıldı.", "'Ana Sayfa' sayfası açılamadı!");
		lib.click(By.xpath("//span[@class='closeBtn']"));
		lib.click(By.xpath("//span[@class='closeBtn']"));

		return this;
	}

	public PageN11Login girisEkraninaGit() {

		lib.click(btnGirisYap);
		return new PageN11Login(lib);
	}

	public PageN11AnaSayfa girisYap() {

		girisEkraninaGit().login(Data.EMAIL, Data.PASSWORD);

		return this;
	}

	public PageN11AramaListesi aramaYap(String arama) {

		lib.sendKeys(txtArama, arama);
		lib.click(btnAra);
		lib.Control(lib.isElementExist(By.xpath("//a/span[text()='"
		                                        + arama.toLowerCase()
		                                        + "']")), "Arama Listesi '"
		                                                  + arama
		                                                  + "' için sayfa açıldı.", "Arama Listesi '"
		                                                                            + arama
		                                                                            + "' için sayfa açılamadı!");
		lib.Control(lib.isElementExist(By.xpath("//div[@class='resultText ']"), 1), "'"
		                                                                            + arama
		                                                                            + "' için sonuç bulundu.", "'"
		                                                                                                       + arama
		                                                                                                       + "' için sonuç bulunamadı!");

		return new PageN11AramaListesi(lib);
	}
}
