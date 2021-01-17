package com.n11.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.n11.commons.N11Commons;

public class PageN11AramaListesi extends PageN11AnaSayfa {

	N11Commons lib;

	public PageN11AramaListesi(N11Commons lib) {

		super(lib);
		this.lib = lib;
		PageFactory.initElements(this.lib.driver, this);
	}

	@FindBy(how = How.XPATH, using = "//div[@class='pagination']/a[@class='active ']")
	WebElement lblAktifSayfa;

	public PageN11AramaListesi sayfayaGit(Integer sayfaNo) {

		lib.click(By.xpath("//div[@class='pagination']/a[text()='" + sayfaNo + "']"));
		lib.Control(sayfaNo.toString()
		                   .equals(aktifSayfaNoGetir()), "Sayfa no '"
		                                                 + sayfaNo
		                                                 + "' olduğu doğrulandı.", "Sayfa no '"
		                                                                           + sayfaNo
		                                                                           + "' olduğu doğrulanamadı!");

		return this;
	}

	public PageN11Urun uruneGit(Integer siraNo) {

		lib.click(By.xpath("//ul[@class='clearfix']/li[" + siraNo + "]//h3"));

		return new PageN11Urun(lib);
	}

	private String aktifSayfaNoGetir() {

		return lib.getTextOfElement(lblAktifSayfa);
	}
}
