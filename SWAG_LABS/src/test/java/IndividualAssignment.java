import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.awt.SystemColor.menuText;

public class IndividualAssignment {
    //Global Variable Section
    String BaseURL = "https://www.saucedemo.com/v1/";
    WebDriver driver;
    String ActualOutput;
    String ExpectedOutput;


    //Before test section
    @BeforeTest
    public void BeforeTestMethod(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    //Test case section
    //Test case for login
    @Test(priority = 1)
    public void UserLogin() throws InterruptedException {
        driver.get(BaseURL);
        Thread.sleep(3000);
        System.out.println("---------------TC 001---------------");
        //Send the username and password to the username and password fields
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");

        //Click LogIn button
        driver.findElement(By.id("login-button")).click();
        Thread.sleep(3000);
        //Verify the Expected Output and Actual Output
        ExpectedOutput = "Products";
        ActualOutput = driver.findElement(By.className("product_label")).getText();

        //Print Actual Result
        System.out.println("Test Case 001 Expected Output : "+ExpectedOutput);
        System.out.println("Test Case 001 Actual Output : "+ActualOutput);
        if (ExpectedOutput.equals(ActualOutput)){
            System.out.println("Test Case 001 : PASS");
            System.out.println("The User Successfully login to the system and redirected to the product page.");
        }else {
            System.out.println("Test Case 001 : FAIL");
            System.out.println("The User login is failed and not redirected to the product page.");
        }
    }

    //Test Case for Hamburger icon
    @Test(priority = 2)
    public void Hamburger_Icon() throws InterruptedException {
        UserLogIn();
        System.out.println("---------------TC 002---------------");
        //Check the hamburger icon works correctly

        driver.findElement(By.xpath("/html/body/div/div[1]/div/div[3]/div/button")).click();
        Thread.sleep(3000);
        //Verify the Expected Output and Actual Output
        WebElement Hamberger_icon = driver.findElement(By.className("bm-menu-wrap"));
        Thread.sleep(3000);
        if (Hamberger_icon.isDisplayed()){
            System.out.println("Hamberger Icon Displayed Successfully.");
        }else {
            System.out.println("Hamberger Icon not Displayed.");
        }
        Thread.sleep(3000);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='menu_button_container']/div/div[2]/div[2]/div/button")));
        Thread.sleep(3000);
        //Close the button
        closeButton.click();
        if (closeButton.isEnabled()){
            System.out.println("The Hamburger Icon Closed Successfully.");
        }
    }

    //Test Case for product catalogue
    @Test(priority = 3)
    public void ProductCatalogue() throws InterruptedException {
        UserLogIn();
        System.out.println("---------------TC 003---------------");
        Thread.sleep(3000);
        //Click the image and open product catalogue
        driver.findElement(By.xpath("(//img[@class='inventory_item_img'])[1]")).click();
        Thread.sleep(3000);
        //Verify if the image clicked and redirected to the product catalogue page
        ExpectedOutput = "Swag Labs";
        ActualOutput = driver.findElement(By.className("header_label")).getText();
        //print actual result
        System.out.println("Test Case 003 Expected Output : "+ExpectedOutput);
        System.out.println("Test Case 003 Actual Output : "+ActualOutput);
        if (ExpectedOutput.equals(ActualOutput)){
            System.out.println("Test Case 003 : PASS");
            System.out.println("User clicked the product image and redirected to the catalogue page successfully.");
        }else {
            System.out.println("Test Case 003 : FAIL");
            System.out.println("User clicked the product image but not redirected to the catalogue page.");
        }
        Thread.sleep(3000);
        //Check all the elements in the swag labs page
        WebElement Product_img = driver.findElement(By.xpath("//*[@id=\"inventory_item_container\"]/div/div/img"));
        WebElement Product_name = driver.findElement(By.xpath("//*[@id=\"inventory_item_container\"]/div/div/div/div[1]"));
        WebElement Product_details = driver.findElement(By.xpath("//*[@id=\"inventory_item_container\"]/div/div/div/div[2]"));
        WebElement Product_price = driver.findElement(By.className("inventory_details_price"));
        WebElement Add_to_cart_button = driver.findElement(By.xpath("//*[@id=\"inventory_item_container\"]/div/div/div/button"));
        Thread.sleep(3000);
        //Verify if the details are available in the page
        System.out.println("--Available Details in Product Catalogue page--");
        if (Product_img.isDisplayed()){
            System.out.println("The Product image is Available.");
        }if (Product_name.isDisplayed()){
            System.out.println("The Product name is Available.");
        }if (Product_details.isDisplayed()){
            System.out.println("The Product details are Available.");
        }if (Product_price.isDisplayed()){
            System.out.println("The Product price is Available.");
        }if (Add_to_cart_button.isDisplayed()){
            System.out.println("The Add To Cart button is Available.");
        }else {
            System.out.println("Elements not Available.");
        }

        System.out.println("----- Currency Type -----");
        WebElement Inventory_price = driver.findElement(By.className("inventory_details_price"));
        String Price_Value = Inventory_price.getText();
        if (Price_Value.contains("$")){
            System.out.println("Price value contains '$' sign.");
        }else {
            System.out.println("Price value not contains '$' sign.");
        }

        Assert.assertTrue(Product_img.isDisplayed(), "Product image is not Available.");
        Assert.assertTrue(Product_name.isDisplayed(), "Product name is not Available.");
        Assert.assertTrue(Product_details.isDisplayed(), "Product details are not Available.");
        Assert.assertTrue(Product_price.isDisplayed(), "Product price is not Available.");
        Assert.assertTrue(Add_to_cart_button.isDisplayed(), "Add to cart button is not Available.");

        Thread.sleep(3000);
        driver.findElement(By.className("inventory_details_back_button")).click();
    }

    //Test Case for add to cart
    @Test(priority = 4)
    public void Add_To_Cart() throws InterruptedException {
        UserLogIn();
        System.out.println("---------------TC 004---------------");

        Thread.sleep(3000);
        //Check the name before click and after click
        WebElement addToCartButton = driver.findElement(By.xpath("//div[@class='inventory_list']//div[1]//div[3]//button[1]"));
        Thread.sleep(3000);
        //Check the name before click
        String Before_name = addToCartButton.getText();
        System.out.println("Name before click : "+Before_name);

        Thread.sleep(3000);
        //click the button
        addToCartButton.click();

        Thread.sleep(3000);
        //Check the name after click
        String After_name = addToCartButton.getText();
        System.out.println("Name after click : "+After_name);

        WebElement Cart_Icon = driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a"));
        Thread.sleep(3000);
        Cart_Icon.click();

        //Verify the expected output and actual output
        Thread.sleep(3000);
        ExpectedOutput = "Your Cart";
        ActualOutput = driver.findElement(By.xpath("//div[@class='subheader']")).getText();

        //Print Actual Result
        Thread.sleep(3000);
        System.out.println("Test Case 005 Expected Output : "+ExpectedOutput);
        System.out.println("Test Case 005 Actual Output : "+ActualOutput);
        if (ExpectedOutput.equals(ActualOutput)){
            System.out.println("Test Case 005 : PASS");
            System.out.println("The User Successfully redirected to the Your Cart page.");
        }else {
            System.out.println("Test Case 005 : FAIL");
            System.out.println("The User can not redirected to the Your Cart page.");
        }

        Thread.sleep(3000);

        //Check the quantity
        WebElement Quantity_Element = driver.findElement(By.xpath("//div[@class='cart_quantity_label']"));
        String Quantity_Text = Quantity_Element.getText();
        System.out.println("The Quantity in cart is : "+Quantity_Text);

        //Check all the elements in the Your Cart page
        Thread.sleep(3000);
        WebElement Product_name = driver.findElement(By.xpath("//div[@class='inventory_item_name']"));
        WebElement Product_details = driver.findElement(By.xpath("//div[@class='inventory_item_desc']"));
        WebElement Product_price = driver.findElement(By.xpath("//div[@class='inventory_item_price']"));
        WebElement Remove_Button = driver.findElement(By.xpath("//button[normalize-space()='REMOVE']"));


        //Verify if the details are available in the page
        System.out.println("--Available Details in Your Cart page--");
        if (Product_name.isDisplayed()){
            System.out.println("The Product name is Available.");
        }if (Product_details.isDisplayed()){
            System.out.println("The Product details are Available.");
        }if (Product_price.isDisplayed()){
            System.out.println("The Product price is Available.");
        }if (Remove_Button.isDisplayed()){
            System.out.println("The Remove Button is Available.");
        }else {
            System.out.println("Elements not Available.");
        }
        System.out.println("----- Currency Type -----");
        WebElement Inventory_Item_Price = driver.findElement(By.className("inventory_item_price"));
        String Item_Price_Value = Inventory_Item_Price.getText();
        if (Item_Price_Value.contains("$")){
            System.out.println("Price value contains '$' sign.");
        }else {
            System.out.println("Price value not contains '$' sign.");
        }
        Thread.sleep(3000);
        Assert.assertTrue(Product_name.isDisplayed(), "Product name is not Available.");
        Assert.assertTrue(Product_details.isDisplayed(), "Product details are not Available.");
        Assert.assertTrue(Product_price.isDisplayed(), "Product price is not Available.");
        Assert.assertTrue(Remove_Button.isDisplayed(),"Remove Button is not Available.");
        Thread.sleep(3000);

        //Click the ContinueSopping button
        WebElement Continue_Shopping = driver.findElement(By.xpath("//a[normalize-space()='Continue Shopping']"));
        Thread.sleep(3000);
        Continue_Shopping.click();
        Thread.sleep(3000);
        ExpectedOutput = "Products";
        ActualOutput = driver.findElement(By.className("product_label")).getText();
        Thread.sleep(3000);

        //Print Actual result after click the Continue Shopping button
        System.out.println("-- Actual result after clicking the Continue Shopping button --");
        System.out.println("Test Case 005 Expected Output : "+ExpectedOutput);
        System.out.println("Test Case 005 Actual Output : "+ActualOutput);
        Thread.sleep(3000);

        //After click the ContinueSopping, Click the ADD TO CART button
        WebElement AddToCart = driver.findElement(By.xpath("//body//div[@id='page_wrapper']//div[@id='inventory_container']//div[@id='inventory_container']//div[2]//div[3]//button[1]"));
        Thread.sleep(3000);
        //Check the name before click
        String Beforemethod = AddToCart.getText();
        System.out.println("Name before click : "+Beforemethod);

        Thread.sleep(3000);
        //click the button
        AddToCart.click();

        Thread.sleep(3000);
        //Check the name after click
        String Aftermethod = AddToCart.getText();
        System.out.println("Name after click : "+Aftermethod);

        //Click the Cart Icon
        Thread.sleep(3000);
        WebElement Cart_icon = driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a"));
        Thread.sleep(3000);
        Cart_icon.click();

    }

    //Test Case for Checkout
    @Test(priority = 5)
    public void Checkout() throws InterruptedException {
        YourCart();
        System.out.println("---------------TC 005---------------");
        Thread.sleep(3000);

        //Identify the checkout button
        WebElement Check_Out_Button = driver.findElement(By.xpath("//*[@id=\"cart_contents_container\"]/div/div[2]/a[2]"));
        Thread.sleep(3000);
        //click the button
        Check_Out_Button.click();
        Thread.sleep(3000);

        //Verify the Expected output and Actual output
        ExpectedOutput = "Checkout: Your Information";
        ActualOutput = driver.findElement(By.className("subheader")).getText();

        //Print Expected output and Actual output
        System.out.println("Test Case 001 Expected Output : "+ExpectedOutput);
        System.out.println("Test Case 001 Actual Output : "+ActualOutput);

        if (ExpectedOutput.equals(ActualOutput)){
            System.out.println("Test Case 005 : PASS");
            System.out.println("The user successfully clicked the checkout button and redirected to the 'Checkout: Your Information' page.");
        }else {
            System.out.println("Test Case 005 : FAIL");
            System.out.println("The user can't click the checkout button and can't redirected to the 'Checkout: Your Information' page.");
        }
        Thread.sleep(3000);

        //Verify the Information filling section

        //Click Continue with no inputs
        WebElement Continue_Button = driver.findElement(By.xpath("//input[@value='CONTINUE']"));
        Continue_Button.click();
        Thread.sleep(2000);

        //Verify it
        try {
            WebElement Error_msg = driver.findElement(By.xpath("//*[@id=\"checkout_info_container\"]/div/form/h3"));
            if (Error_msg.isDisplayed()){
                System.out.println("----- Validation form with no inputs -----");
                System.out.println("Error message displayed with no inputs : "+Error_msg.getText());
            }
        }catch (Exception e){
            System.out.println("No error found, validation failed.");
        }

        //Clear the error
        driver.navigate().refresh();
        Thread.sleep(3000);

        //Check only filling first name
        //Fill First name
        WebElement First_name = driver.findElement(By.xpath("//input[@id='first-name']"));
        Thread.sleep(2000);
        First_name.sendKeys("Sankar");
        Thread.sleep(2000);
        WebElement ContinueButton = driver.findElement(By.xpath("//input[@value='CONTINUE']"));
        ContinueButton.click();
        Thread.sleep(3000);

        try {
            WebElement ErrorMessage = driver.findElement(By.xpath("//*[@id=\"checkout_info_container\"]/div/form/h3"));
            if (ErrorMessage.isDisplayed()){
                System.out.println("----- Validation form with only first name -----");
                System.out.println("Error message displayed with only first name : "+ErrorMessage.getText());
            }
        }catch (Exception e){
            System.out.println("No error message found, validation failed.");
        }

        //Clear the error
        driver.navigate().refresh();
        Thread.sleep(3000);

        //Check filling without zip code
        //Fill name first name
        WebElement f_name = driver.findElement(By.xpath("//input[@id='first-name']"));
        f_name.sendKeys("Sankar");
        Thread.sleep(2000);
        //Fill last name
        WebElement l_name = driver.findElement(By.xpath("//input[@id='last-name']"));
        l_name.sendKeys("Madhushankha");
        Thread.sleep(2000);
        //click continue button
        WebElement C_Button = driver.findElement(By.xpath("//input[@value='CONTINUE']"));
        C_Button.click();
        Thread.sleep(3000);

        try {
            WebElement Error = driver.findElement(By.xpath("//*[@id=\"checkout_info_container\"]/div/form/h3"));
            if (Error.isDisplayed()){
                System.out.println("----- Validation form without zip code/postal code -----");
                System.out.println("Error message displayed without zip code/postal code "+Error.getText());
            }
        }catch (Exception e){
            System.out.println("No error message found, validation failed.");
        }
        //Clear the error
        driver.navigate().refresh();
        Thread.sleep(3000);

        //Check using valid inputs
        //Fill name first name
        WebElement F_name = driver.findElement(By.xpath("//input[@id='first-name']"));
        F_name.sendKeys("Sankar");
        Thread.sleep(2000);
        //Fill last name
        WebElement L_name = driver.findElement(By.xpath("//input[@id='last-name']"));
        L_name.sendKeys("Madhushankha");
        Thread.sleep(2000);
        //Fill postal code
        WebElement postalCode = driver.findElement(By.xpath("//input[@id='postal-code']"));
        postalCode.sendKeys("60036");

        //click continue button
        WebElement CButton = driver.findElement(By.xpath("//input[@value='CONTINUE']"));
        CButton.click();
        Thread.sleep(3000);

        //Verify the Expected output and Actual output
        ExpectedOutput = "Checkout: Overview";
        ActualOutput = driver.findElement(By.xpath("//div[@class='subheader']")).getText();

        //Print expected output and actual output
        System.out.println("Expected output : "+ExpectedOutput);
        System.out.println("Actual output : "+ActualOutput);
        Thread.sleep(2000);
        if (ExpectedOutput.equals(ActualOutput)){
            System.out.println("Test : PASS");
            System.out.println("The user successfully filled and clicked the continue button and redirected to the 'Checkout: Overview' page.");
        }else {
            System.out.println("Test : FAIL");
            System.out.println("The user can't redirected to the 'Checkout: Overview' page.");
        }
        Thread.sleep(3000);
    }

    //Test Case for Overview
    @Test(priority = 6)
    public void Checkout_Overview() throws InterruptedException {
        Overview();

        //Verify the Expected output and Actual output
        ExpectedOutput = "Checkout: Overview";
        ActualOutput = driver.findElement(By.xpath("//div[@class='subheader']")).getText();
        System.out.println("---------------TC 006---------------");
        //Print Actual output
        if (ExpectedOutput.equals(ActualOutput)){
            System.out.println("Test Case 006 : PASS");
            System.out.println("The user successfully redirected to the 'Checkout: Overview' page.");
        }else {
            System.out.println("Test Case 006 : FAIL");
            System.out.println("The user can't redirected to the 'Checkout: Overview' page.");
        }
        Thread.sleep(3000);

        System.out.println("---------------------------------------------------------");
        //Check all the elements in the Checkout Overview page.
        WebElement Product_Item_name = driver.findElement(By.className("inventory_item_name"));
        String ProductName = Product_Item_name.getText();
        if (Product_Item_name.isDisplayed()){
            System.out.println("The Product Name : "+ProductName);
        }else {
            System.out.println("No Product name.");
        }
        Thread.sleep(3000);

        WebElement Product_Item_details = driver.findElement(By.className("inventory_item_desc"));
        String ProductDetails = Product_Item_details.getText();
        if (Product_Item_details.isDisplayed()){
            System.out.println("The Product details : "+ProductDetails);
        }else {
            System.out.println("No Product details.");
        }
        Thread.sleep(3000);

        WebElement Product_price = driver.findElement(By.className("inventory_item_price"));
        String ProductPrice = Product_price.getText();
        if (Product_price.isDisplayed()){
            System.out.println("The Product price : "+ProductPrice);
        }else {
            System.out.println("No Product price.");
        }
        Assert.assertTrue(Product_Item_name.isDisplayed(), "No Product name.");
        Assert.assertTrue(Product_Item_details.isDisplayed(), "No Product details.");
        Assert.assertTrue(Product_price.isDisplayed(), "No Product price.");
        Thread.sleep(3000);

        System.out.println("---------------------------------------------------------");

        //Check the payment information
        WebElement Payment_info = driver.findElement(By.xpath("//div[normalize-space()='SauceCard #31337']"));
        String PaymentInfo = Payment_info.getText();
        if (Payment_info.isDisplayed()){
            System.out.println("The Payment Information : "+PaymentInfo);
        }else {
            System.out.println("The Payment Information not available.");
        }

        //Check the Shipping information
        WebElement Shipping_Info = driver.findElement(By.xpath("//div[normalize-space()='FREE PONY EXPRESS DELIVERY!']"));
        String ShippingInfo = Shipping_Info.getText();
        if (Shipping_Info.isDisplayed()){
            System.out.println("The Shipping Information : "+ShippingInfo);
        }else {
            System.out.println("The Shipping Information not available.");
        }

        System.out.println("---------------------------------------------------------");

        //Check the calculation
        String ItemTotal_Text = driver.findElement(By.xpath("//div[@class='summary_subtotal_label']")).getText();
        String Tax_Text = driver.findElement(By.xpath("//div[@class='summary_tax_label']")).getText();
        String Total_Text = driver.findElement(By.xpath("//div[@class='summary_total_label']")).getText();

        //Remove $ Signs and divide text and values
        String amount_String = ItemTotal_Text.split(":")[1].trim().replace("$","");
        String tax_String = Tax_Text.split(":")[1].trim().replace("$","");
        String total_String = Total_Text.split(":")[1].trim().replace("$","");

        double Item_Total = Double.parseDouble(amount_String);
        double Tax = Double.parseDouble(tax_String);
        double Total = Double.parseDouble(total_String);

        //Calculate the values
        double Calculated_Total = Item_Total + Tax;

        System.out.println("The Item Total : "+Item_Total);
        System.out.println("The Tax : "+Tax);
        System.out.println("The Total : "+Calculated_Total);

        if (Total == Calculated_Total){
            System.out.println("The Total Price is Correct : "+Calculated_Total);
        }else {
            System.out.println("The Total Price is Incorrect.");
        }

        System.out.println("---------------------------------------------------------");
        //Verify the Finish Button available
        WebElement Finish_Button = driver.findElement(By.xpath("//a[normalize-space()='FINISH']"));
        if (Finish_Button.isDisplayed()){
            System.out.println("The Finish Button Available.");
        }else {
            System.out.println("The Finish Button not available.");
        }
    }

    //Test Case for Finish
    @Test(priority = 7)
    public void Finish_() throws InterruptedException {
        Finish();
        Thread.sleep(3000);
        //Verify the Expected output and Actual output
        ExpectedOutput = "Finish";
        ActualOutput = driver.findElement(By.xpath("//div[@class='subheader']")).getText();
        Thread.sleep(2000);
        //Print the Actual output
        System.out.println("Test Case 007 Expected Output : "+ExpectedOutput);
        System.out.println("Test Case 007 Actual Output : "+ActualOutput);

        if (ExpectedOutput.equals(ActualOutput)){
            System.out.println("---------------TC 007---------------");
            System.out.println("The user successfully finished the process and redirected to the Finish page.");
        }else {
            System.out.println("The user can not finished the process and not redirected to the Finish page.");
        }

        System.out.println("------------------------------------------------------");

        //Get the Thank you message
        WebElement Thank_you = driver.findElement(By.xpath("//h2[normalize-space()='THANK YOU FOR YOUR ORDER']"));
        String ThankYou = Thank_you.getText();
        if (Thank_you.isDisplayed()){
            System.out.println("The Finish Success Notification : "+ThankYou);
        }else {
            System.out.println("No Success Notification.");
        }
        Thread.sleep(3000);

        WebElement Thank_Y = driver.findElement(By.xpath("//div[@class='complete-text']"));
        String ThankY = Thank_Y.getText();
        if (Thank_Y.isDisplayed()){
            System.out.println("The Message : "+ThankY);
        }else {
            System.out.println("No Message.");
        }
    }

    //Supportive Method Section
    //User Log In
    public void UserLogIn() throws InterruptedException {
        driver.get(BaseURL);
        Thread.sleep(3000);

        //Identify the username and password field and send the values
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        //Click LogIn button
        driver.findElement(By.id("login-button")).click();
        Thread.sleep(3000);
    }

    //Your_Cart
    public void YourCart() throws InterruptedException {
        driver.get(BaseURL);
        Thread.sleep(3000);

        //Identify the username and password field and send the values
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        //Click LogIn button
        driver.findElement(By.id("login-button")).click();
        Thread.sleep(3000);

        driver.findElement(By.xpath("//div[@class='inventory_list']//div[1]//div[3]//button[1]")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[2]/div[3]/button")).click();
        Thread.sleep(3000);

        WebElement Cart_Icon = driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a"));
        Thread.sleep(3000);
        Cart_Icon.click();
    }

    //Overview
    public void Overview() throws InterruptedException {
        driver.get(BaseURL);
        Thread.sleep(3000);

        //Identify the username and password field and send the values
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        //Click LogIn button
        driver.findElement(By.id("login-button")).click();
        Thread.sleep(3000);

        driver.findElement(By.xpath("//div[@class='inventory_list']//div[1]//div[3]//button[1]")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[2]/div[3]/button")).click();
        Thread.sleep(3000);

        WebElement Cart_Icon = driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a"));
        Thread.sleep(3000);
        Cart_Icon.click();
        Thread.sleep(3000);

        //Click Checkout Button
        driver.findElement(By.xpath("//a[normalize-space()='CHECKOUT']")).click();
        Thread.sleep(3000);
        //Enter inputs
        driver.findElement(By.xpath("//input[@id='first-name']")).sendKeys("Sankar");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@id='last-name']")).sendKeys("Madhushankha");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@id='postal-code']")).sendKeys("60036");
        Thread.sleep(1000);

        //Click continue button
        driver.findElement(By.xpath("//input[@value='CONTINUE']")).click();
        Thread.sleep(2000);

    }

    //Finish
    public void Finish() throws InterruptedException {
        driver.get(BaseURL);
        Thread.sleep(3000);

        //Identify the username and password field and send the values
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        //Click LogIn button
        driver.findElement(By.id("login-button")).click();
        Thread.sleep(3000);

        driver.findElement(By.xpath("//div[@class='inventory_list']//div[1]//div[3]//button[1]")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[2]/div[3]/button")).click();
        Thread.sleep(3000);

        WebElement Cart_Icon = driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a"));
        Thread.sleep(3000);
        Cart_Icon.click();
        Thread.sleep(3000);

        //Click Checkout Button
        driver.findElement(By.xpath("//a[normalize-space()='CHECKOUT']")).click();
        Thread.sleep(3000);
        //Enter inputs
        driver.findElement(By.xpath("//input[@id='first-name']")).sendKeys("Sankar");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@id='last-name']")).sendKeys("Madhushankha");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@id='postal-code']")).sendKeys("60036");
        Thread.sleep(1000);

        //Click continue button
        driver.findElement(By.xpath("//input[@value='CONTINUE']")).click();
        Thread.sleep(2000);

        //Click finish button
        driver.findElement(By.xpath("//a[normalize-space()='FINISH']")).click();
        Thread.sleep(2000);
    }
}
