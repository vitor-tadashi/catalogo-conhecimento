package br.com.resource.catalogoconhecimento.tecnologia;

import java.sql.Timestamp;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

public class AdicionarTecnologiaTeste {

	private WebDriver driver;
	private long t = new Timestamp(System.currentTimeMillis()).getTime();

	@Test
	public void adicionarTecnologiaComSucesso() {
		String tecnologia = "Selenium" + t;
		driver.get(
				"http://localhost:8080/catalogoconhecimento/mvc?logica=tecnologia.FormularioAdicionarTecnologiaLogica");
		driver.findElement(By.name("nome")).sendKeys(tecnologia);
		driver.findElement(By.id("submit")).click();

		Assert.assertTrue("Deveria conter: " + tecnologia, driver.getPageSource().contains(tecnologia));
	}

	@Test
	public void alterarTecnologiaComSucesso() {
		adicionarTecnologiaComSucesso();
		long t = new Timestamp(System.currentTimeMillis()).getTime();
		String tecnologia = "Selenium" + t;
		System.out.println(tecnologia);
		
		driver.findElement(By.name("nome")).sendKeys(tecnologia);
		driver.findElement(By.id("submit")).click();

		Assert.assertTrue("Deveria conter: " + tecnologia, driver.getPageSource().contains(tecnologia));
	}
	
	@Before
	public void setup() {
		driver = new PhantomJSDriver();
	}

	@After
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

}
