package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HomePage {
    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "shopping_cart_container")
    public WebElement CartButton;

    @FindBy(className = "product_sort_container")
    public WebElement SortButton;

    @FindBy(className = "bm-menu-wrap")
    public WebElement HiddenMenu;


    @FindBy(className = "shopping_cart_badge")
    public WebElement CartBadge;

    @FindBy(className = "inventory_item_name")
    public List<WebElement> ItemName;

    @FindBy(className = "active_option")
    public WebElement ActiveSort;

    @FindBy(className = "product_sort_container")
    public WebElement Sort;

    @FindBy(className = "inventory_item_price")
    public List<WebElement> ItemPrice;

    //----------------------

    public void clickOnCartButton() {
        CartButton.click();
    }

    public String getBadgeNumber() {
        return CartBadge.getText();
    }

    public String getActiveSort() {
        return ActiveSort.getText();
    }

    public ArrayList<String> getAllItemNames() {
        ArrayList<String> elements = new ArrayList<>();
        for (WebElement webElement : ItemName) {
            elements.add(webElement.getText());
        }
        return elements;
    }

    public ArrayList<String> getReversedList(ArrayList list) {
        list.sort(Collections.reverseOrder());
        return list;
    }

    public ArrayList<String> getSortedList(ArrayList list) {
        Collections.sort(list);
        return list;
    }

    public ArrayList<Double> getPrices() {
        ArrayList<Double> doubleList = new ArrayList<Double>();
        for (int i = 0; i < ItemPrice.size(); i++) {
            String name = ItemPrice.get(i).getText().replaceAll("\\$", "");
            double d = Double.parseDouble(name);
            doubleList.add(d);
        }
        return doubleList;
    }

    public boolean numbersAreSortedHighToLow(String sort, ArrayList<Double> list) throws Exception {
        switch (sort) {
            case "lowhigh":
                for (int i = 0; i < list.size()-1; i++) {
                if (list.get(i) > list.get(i+1)) {
                    return false;
                }
            }
                return true;

            case "highlow":
                for (int i = 0; i < list.size()-1; i++) {
                    if (list.get(i) < list.get(i+1)) {
                        return false;
                    }
                }
                return true;

            default: throw new Exception("Wrong sort input. Please provide 'highlow' or 'lowhigh'");
        }

    }

}
