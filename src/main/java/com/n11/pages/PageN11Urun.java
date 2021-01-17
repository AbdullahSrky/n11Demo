package com.n11.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.n11.commons.N11Commons;

public class PageN11Urun extends PageN11AnaSayfa {

	N11Commons lib;

	public PageN11Urun(N11Commons lib) {

		super(lib);
		this.lib = lib;
		PageFactory.initElements(this.lib.driver, this);
	}

	String urunAdi = "";
	@FindBy(how = How.ID, using = "getWishList")
	WebElement btnFavori;
	@FindBy(how = How.ID, using = "addToFavouriteWishListBtn")
	WebElement btnFavoriEkle;

	public PageN11Urun favoriyeEkle() {

		lib.waitForElement(btnFavori);
		urunAdi = lib.getTextOfElement(By.xpath("//h1[@class='proName']"));
		for (WebElement ozellikler : lib.driver.findElements(By.xpath("//div[@id='skuArea']//select"))) {
			lib.selectCombobox(ozellikler, ozellikler.findElement(By.xpath("./*[2]"))
			                                         .getAttribute("value"));
		}

		lib.click(btnFavori);

		lib.click(btnFavoriEkle);

		lib.click(By.xpath("//div[@style='display: block;']//span[text()='Tamam']"));
		lib.ControlWarning(lib.getProperty(btnFavori, "class")
		                      .contains("added"), "Ürün favoriye eklendi.", "Ürün favoriye eklenmedi.");

		return this;
	}

	public PageN11Urun favoridenCikar() {

		lib.moveToElement(By.xpath("//div[@class='myAccount']"));
		lib.click(By.xpath("//a[text()='Favorilerim / Listelerim']"));
		lib.click(By.xpath("//h4[starts-with(text(),'Favorilerim')]"));

		String favUrunAdi = lib.getTextOfElement(By.xpath("//h3[@class='productName ']"));
		lib.Control(urunAdi.contains(favUrunAdi), "Favoriye atılan ürün eşleşti.", "Favoriye atılan ürün eşleşmedi!");

		lib.click(By.xpath("//span[@class='deleteProFromFavorites']"));
		lib.click(By.xpath("//div[@style='display: block;']//span[text()='Tamam']"));

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}
		lib.Control(!lib.isElementExist(By.xpath("//h3[@class='productName 'and contains(text(),'HELİXSUN 3.0 Micro USB Hızlı Şarj Kablosu ve Data Samsung Android')]"), 3), "Ürün kaldırıldı.", "Ürün kaldırılamadı!");

		return this;
	}
}
