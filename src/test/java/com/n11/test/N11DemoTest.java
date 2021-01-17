package com.n11.test;

import org.testng.annotations.Test;

import com.n11.controller.TestController;

public class N11DemoTest extends TestController {

	@Test
	public void N11_FavoriEkleKaldir() {

		startTest().anaSayfayaGit()
		           .girisYap()
		           .aramaYap("samsung")
		           .sayfayaGit(2)
		           .uruneGit(3)
		           .favoriyeEkle()
		           .favoridenCikar();
	}
}
