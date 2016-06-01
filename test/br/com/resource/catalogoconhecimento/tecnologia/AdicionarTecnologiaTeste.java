package br.com.resource.catalogoconhecimento.tecnologia;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AdicionarTecnologiaTeste {

	private WebDriver driver;

	@Test
	public void adicionarTecnologiaComSucesso() {
		String tecnologia = "Selenium";
		driver.get(
				"http://localhost:8080/catalogoconhecimento/mvc?logica=tecnologia.FormularioAdicionarTecnologiaLogica");
		driver.findElement(By.name("nome")).sendKeys(tecnologia);
		driver.findElement(By.className("btn btn-success")).click();
		Assert.assertTrue("Deveria conter: Selenium", driver.getPageSource().contains(tecnologia));
	}

	@Before
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	@After
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

}
