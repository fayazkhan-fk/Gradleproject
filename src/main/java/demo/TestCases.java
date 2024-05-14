package demo;

import java.time.Duration;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;



import java.util.logging.Level;
import io.github.bonigarcia.wdm.WebDriverManager;


public class TestCases {
    ChromeDriver driver;
    //@SuppressWarnings("deprecation")
    public TestCases()
    {
        System.out.println("Constructor: TestCases");

        WebDriverManager.chromedriver().timeout(30).setup();
        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        // Set log level and type
        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("start-maximized");
        options.addArguments("--disable-blink-features=AutomationControlled");


        // Set path for log file
        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "chromedriver.log");

        driver = new ChromeDriver(options);

        // Set browser to maximize and wait
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    public void endTest()
    {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    
    public  void testCase01(){
        System.out.println("Start Test case: testCase01");

        driver.get("https://www.makemytrip.com/");
        
        String currURL=driver.getCurrentUrl();
        
        if(currURL.contains("makemytrip")){
            System.out.println("home page of makemytrip");
        }else{
            System.out.println("Not a home page of makemytrip");
        }

        System.out.println("end Test case: testCase01");
    }
    public  void testCase02() throws InterruptedException{
        System.out.println("Start Test case: testCase02");
         // Navigate to MakeMyTrip website
        driver.get("https://www.makemytrip.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement clickoncross = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@data-cy='closeModal']")));
    clickoncross.click();

        // Select BLR(Bangalore) as the departure location
        WebElement fromCityInput = driver.findElement(By.xpath("//*[@id=\"fromCity\"]"));
        fromCityInput.click();

        // Wait for the dropdown to appear
      
        WebElement fromCitySearchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='From']")));

        fromCitySearchInput.sendKeys("blr");
       
    //     // Wait for the options to appear in the dropdown
    //    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(),'Bengaluru, India')]")));
    //    WebElement tobeclicked=driver.findElement(By.xpath("//p[contains(text(),'Bengaluru, India')]"));
    //     //click on visible option
    //     tobeclicked.click();
    List<WebElement> suggestionElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//p[@class='font14 appendBottom5 blackText']")));

    for (int i=0; i<suggestionElements.size(); i++) {
        String Text= suggestionElements.get(i).getText();
        if(Text.contains("Bengaluru")){
            suggestionElements.get(i).click();
            break;
        }
    }
        System.out.println("departure location Added succesfully");

        // Select DEL(New Delhi) as the arrival location
        WebElement toCityInput = driver.findElement(By.xpath("//*[@id=\"toCity\"]"));
        toCityInput.click();

        WebElement toCitySearchInput = driver.findElement(By.xpath("//input[@placeholder='To']"));
        toCitySearchInput.sendKeys("del");
        // wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(),'New Delhi, India')]")));
        // WebElement toCityOption = driver.findElement(By.xpath("//p[contains(text(),'New Delhi, India')]"));
        // toCityOption.click();
        List<WebElement> suggestionElements2 = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//p[@class='font14 appendBottom5 blackText']")));
    //List<String> suggestions = new ArrayList<>();
    for (int i=0; i<suggestionElements2.size(); i++) {
        String Text= suggestionElements2.get(i).getText();
        if(Text.contains("New Delhi")){
            suggestionElements2.get(i).click();
            break;
        }
    }
        System.out.println("Arrival location Added succesfully");

            // Select the correct date (29th of next Month)
        
      
        // Get the current date and calculate the date for next month
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='top-banner']/div[2]/div/div/div/div/div[2]/div[1]/div[3]/div[1]/div/div/div/div[2]/div/div[2]")));
        System.out.println("calender is visible");
        
        WebElement nextbutton = driver.findElement(By.xpath("//span[@aria-label='Next Month']"));
        nextbutton.click();
        System.out.println("clicked on next button");
        // Locate the date element corresponding to the 29th of next month
           
        WebElement nextMonthDateOption = driver.findElement(By.xpath("//div[@class='dateInnerCell']/p[text()='29']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nextMonthDateOption);

        // Click on the date using JavaScript
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", nextMonthDateOption);
    
            
        

        // Click on the search button
        WebElement searchButton = driver.findElement(By.xpath("//a[@class='primaryBtn font24 latoBold widgetSearchBtn ']"));
        searchButton.click();
        System.out.println("clicked on search button");
        // Wait for flight search results to load
        
        // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"listing-id\"]/div/div[2]/div/div[1]/div[1]/div[2]/div[2]/div/div/div")));

        // Store the flight price (per adult)
        WebElement flightPriceElement = driver.findElement(By.xpath("//*[@id=\"listing-id\"]/div/div[2]/div/div[1]/div[1]/div[2]/div[2]/div/div/div"));
        String flightPrice = flightPriceElement.getText();
        System.out.println("Flight price (per adult): " + flightPrice);

}
    public  void testCase03() throws InterruptedException{

    System.out.println("Start Test case: testCase03");
     // Navigate to MakeMyTrip website
    driver.get("https://www.makemytrip.com/");
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    // WebElement clickoncross = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@data-cy='closeModal']")));
    // clickoncross.click();
    WebElement switchtotrainpage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='https://www.makemytrip.com/railways/']")));
    switchtotrainpage.click();

    // Select BLR(Bangalore) as the departure location
    WebElement fromCityInput = driver.findElement(By.xpath("//*[@id=\"fromCity\"]"));
    fromCityInput.click();

    // Wait for the dropdown to appear
   
    WebElement fromCitySearchInput1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='From']")));
    fromCitySearchInput1.sendKeys("ypr");
    Thread.sleep(3000);
        
    //click on visible option
    List<WebElement> suggestionElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("p[class='searchedResult font14 darkText']")));
    //List<String> suggestions = new ArrayList<>();
    for (int i=0; i<suggestionElements.size(); i++) {
        String Text= suggestionElements.get(i).getText();
        if(Text.contains("Bangalore")){
            suggestionElements.get(i).click();
            break;
        }
    }
    
    System.out.println("departure location Added succesfully");

    // Select DEL(New Delhi) as the arrival location
   
    WebElement toCitySearchInput = driver.findElement(By.xpath("//input[@placeholder='To']"));
    toCitySearchInput.sendKeys("ndls");
    Thread.sleep(3000);
    //click on visible option
    List<WebElement> suggestionElements2 = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("p[class='searchedResult font14 darkText']")));
    //List<String> suggestions = new ArrayList<>();
    for (int i=0; i<suggestionElements2.size(); i++) {
        String Text= suggestionElements2.get(i).getText();
        if(Text.contains("Delhi")){
            suggestionElements2.get(i).click();
            break;
        }
    }
    System.out.println("Arrival location Added succesfully");

    // Select the correct date (29th of next Month)
    WebElement nextbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@aria-label='Next Month']")));
    nextbutton.click();
    System.out.println("clicked on next button");
    // Locate the date element corresponding to the 29th of next month
       
    WebElement nextMonthDateOption = driver.findElement(By.xpath("//*[@id=\"top-banner\"]/div[2]/div/div/div/div[2]/div/div[3]/div[1]/div/div/div/div[2]/div/div[2]/div[1]/div[3]/div[5]/div[7]"));
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nextMonthDateOption);
    // Click on the date using JavaScript
    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", nextMonthDateOption);

    //appendBottom5 downArrow  
     driver.findElement(By.cssSelector(".appendBottom5.downArrow")).click();
    Thread.sleep(2000);
    WebElement selectTrainclass = driver.findElement(By.xpath("//li[@data-cy='3A']"));
   selectTrainclass.click();


    // Click on the search button
    WebElement searchButton = driver.findElement(By.xpath("//a[@class='primaryBtn font24 latoBold widgetSearchBtn']"));
    searchButton.click();
    System.out.println("clicked on search button");
    // Wait for flight search results to load
    
    // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    // wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"train_options_11-05-2024_0\"]/div[1]/div[2]")));

    // Store the flight price (per adult)
    WebElement TrainPriceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='ticket-price justify-flex-end']")));
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", TrainPriceElement);
    String TrainPrice = TrainPriceElement.getText();
    System.out.println("Train price (per adult): " + TrainPrice);

}
public  void testCase04() throws InterruptedException{

    System.out.println("Start Test case: testCase04");
     // Navigate to MakeMyTrip website
    driver.get("https://www.makemytrip.com/");
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    // WebElement clickoncross = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@data-cy='closeModal']")));
    // clickoncross.click();
    WebElement switchtobuspage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='https://www.makemytrip.com/bus-tickets/']")));
    switchtobuspage.click();

    // Select BLR(Bangalore) as the departure location
    WebElement fromCityInput = driver.findElement(By.xpath("//*[@id=\"fromCity\"]"));
    fromCityInput.click();

    // Wait for the dropdown to appear
   
    WebElement fromCitySearchInput1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='From']")));
    fromCitySearchInput1.sendKeys("bangl");
    Thread.sleep(3000);
        
    //click on visible option
    List<WebElement> suggestionElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("p[class='searchedResult font14 darkText']")));
    
    for (int i=0; i<suggestionElements.size(); i++) {
        String Text= suggestionElements.get(i).getText();
        if(Text.contains("Bangalore")){
            suggestionElements.get(i).click();
            break;
        }
    }
    
    System.out.println("departure location Added succesfully");

    // Select DEL(New Delhi) as the arrival location
   
    WebElement toCitySearchInput = driver.findElement(By.xpath("//input[@placeholder='To']"));
    toCitySearchInput.sendKeys("kathma");
    Thread.sleep(3000);
    //click on visible option
    List<WebElement> suggestionElements2 = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("p[class='searchedResult font14 darkText']")));
    //List<String> suggestions = new ArrayList<>();
    for (int i=0; i<suggestionElements2.size(); i++) {
        String Text= suggestionElements2.get(i).getText();
        if(Text.contains("Kathmandu")){
            suggestionElements2.get(i).click();
            break;
        }
    }
    System.out.println("Arrival location Added succesfully");

    // Select the correct date (29th of next Month)
    WebElement nextbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@aria-label='Next Month']")));
    nextbutton.click();
    System.out.println("clicked on next button");
    // Locate the date element corresponding to the 29th of next month
       
    WebElement nextMonthDateOption = driver.findElement(By.xpath("//div[@class='bus_sw datePickerContainer']//div[@aria-label='Sat Jun 29 2024']"));
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nextMonthDateOption);
    // Click on the date using JavaScript
    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", nextMonthDateOption);

    


    // Click on the search button
    WebElement searchButton = driver.findElement(By.xpath("//button[@class='primaryBtn font24 latoBold widgetSearchBtn']"));
    searchButton.click();
    System.out.println("clicked on search button");
    
    //busses not found
    WebElement busisnotava = driver.findElement(By.className("error-title"));
   if(busisnotava.getText().equals("No buses found for 29 Jun")){
    System.out.println("Testcase 4 is passed");
   }
    
    

}
}
