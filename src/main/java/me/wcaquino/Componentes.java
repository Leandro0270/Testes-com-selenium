package me.wcaquino;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class Componentes {

    public WebDriver driver;

    @Before
    public void inicio(){
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1200, 765));
        driver.get("https://wcaquino.me/selenium/componentes.html");
    }

    @After
    public void finaliza(){
        driver.quit();
    }

    @Test
    public void TextField() {
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Teste de escrita");
        Assert.assertEquals("Teste de escrita", driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));
    }

    @Test
    public void Textarea() {
        driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("Teste de escrita");
        Assert.assertEquals("Teste de escrita", driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value"));

    }

    @Test
    public void RadioButton() {
        driver.findElement(By.id("elementosForm:sexo:0")).click();
        Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());
        driver.quit();
    }

    @Test
    public void Checkbox() {
        driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();
        Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:2")).isSelected());
    }

    @Test
    public void Combobox() {
        WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
        Select combo = new Select(element);
        combo.selectByVisibleText("2o grau completo");
        Assert.assertEquals("2o grau completo", combo.getFirstSelectedOption().getText());
    }
    @Test
    public void VerificarComboBox() {
        WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
        Select combo = new Select(element);
        List<WebElement> options = combo.getOptions();
        Assert.assertEquals(8, options.size());

        boolean encontrou = false;
        for(WebElement option: options){
            if(option.getText().equals("Mestrado")){
                encontrou = true;
                break;
            }
        }
        Assert.assertTrue(encontrou);
    }
    @Test
    public void VerificarComboMulti() {
        WebElement element = driver.findElement(By.id("elementosForm:esportes"));
        Select combo = new Select(element);
        combo.selectByVisibleText("Natacao");
        combo.selectByVisibleText("Corrida");
        combo.selectByVisibleText("O que eh esporte?");
        combo.deselectByVisibleText("Corrida");
        List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
        Assert.assertEquals(2, allSelectedOptions.size());

    }
    @Test
    public void Botão() {
        driver.findElement(By.id("buttonSimple")).click();
        Assert.assertEquals("Obrigado!", driver.findElement(By.id("buttonSimple")).getAttribute("value") );

    }
    @Test
    public void Links() {
        driver.findElement(By.linkText("Voltar")).click();
        Assert.assertEquals("Voltou!", driver.findElement(By.id("resultado")).getText());
    }
}
