package me.wcaquino;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class DSL {

    private WebDriver driver;

    public DSL(WebDriver driver) {
        this.driver = driver;
    }
    /********* Text field e area *********/
    public void escreve(String locate, String texto){
        driver.findElement(By.id(locate)).sendKeys(texto);
    }
    public String verificatexto(String locate) {
        return driver.findElement(By.id(locate)).getAttribute("value");
    }
    /********* Radio e check ************/
    public void clickarnoelemento(String locate){
        driver.findElement(By.id(locate)).click();
    }
    public boolean isRadioMarcado(String locate){
        return driver.findElement(By.id(locate)).isSelected();
    }
    /************** Combo **************/
    public void SelecionarCombo (String locate, String valor){
        WebElement element = driver.findElement(By.id(locate));
        Select combo = new Select(element);
        combo.selectByVisibleText(valor);
    }
    public String ObterValorAtualcombo(String id) {
        WebElement element = driver.findElement(By.id(id));
        Select combo = new Select(element);
        return combo.getFirstSelectedOption().getText();
    }
    public int QuantidadeDeOpcoes(String id){
        WebElement element = driver.findElement(By.id(id));
        Select combo = new Select(element);
        List<WebElement> options = combo.getOptions();
        return options.size();
    }
    public List<String> obterValoresCombo(String id) {
        WebElement element = driver.findElement(By.id("elementosForm:esportes"));
        Select combo = new Select(element);
        List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
        List<String> valores = new ArrayList<String>();
        for(WebElement opcao: allSelectedOptions) {
            valores.add(opcao.getText());
        }
        return valores;
    }
    public boolean verificarOpcaoCombo(String id, String opcao){
        WebElement element = driver.findElement(By.id(id));
        Select combo = new Select(element);
        List<WebElement> options = combo.getOptions();
        for(WebElement option: options) {
            if(option.getText().equals(opcao)){
                return true;
            }
        }
        return false;
    }
}

