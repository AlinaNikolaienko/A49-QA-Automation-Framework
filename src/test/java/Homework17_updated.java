import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Homework17_updated extends BaseTest {
    @Test
    public void addSongToPlaylist() throws InterruptedException {
        loginToKoel();

        WebElement homePage = driver.findElement(By.cssSelector(".menu .home.active"));
        homePage.click();

        /*
        //Delete only "Rock" Playlists if it exists
        for (int i = 0; i <= 5; i++) {
            try {
                WebElement rockPlaylist = driver.findElement(By.cssSelector("//*[@id='playlists']//a[contains(text(), 'Rock')]"));

                if (rockPlaylist.isDisplayed()) {
                    Actions actions = new Actions(driver);
                    actions.contextClick(rockPlaylist).perform();
                    WebElement deleteOption = driver.findElement(By.cssSelector(".menu.playlist-item-menu > ul > li:nth-child(2)"));
                    deleteOption.click();
                }
            } catch (org.openqa.selenium.NoSuchElementException e) {
                // Element not found, do nothing and continue
            }
        }
        */



        //If ONE "Rock" playlist is displayed - it will be deleted. Otherwise - not working
        try {
            //WebElement rockPlaylist = driver.findElement(By.cssSelector("#playlists li:nth-child(3)"));
            WebElement rockPlaylist = driver.findElement(By.xpath("//*[@id='playlists']//a[contains(text(), 'Rock')]"));
            while (rockPlaylist.isDisplayed()) {
                Actions actions = new Actions(driver);
                actions.contextClick(rockPlaylist).perform();
                WebElement deleteOption = driver.findElement(By.cssSelector(".menu.playlist-item-menu > ul > li:nth-child(2)"));
                deleteOption.click();

            }
        } catch (org.openqa.selenium.NoSuchElementException e) {
            // Element not found, do nothing and continue
        }


        searchForSong("Scott");
        clickViewAllButton();
        findFirstSongInResults();
        clickAddToButton();

        String myPlaylist = "Rock";
        createNewPlaylist(myPlaylist);

        String expectedMessageForListCreated = "Created playlist \""+ myPlaylist +".\"";
        Assert.assertEquals(verifyListIsCreated(), expectedMessageForListCreated);


        searchForSong("Ketsa");
        clickViewAllButton();
        findFirstSongInResults();
        clickAddToButton();
        addSongToPlaylistRock();


        String expectedMsgForSongAdded = "Added 1 song into \""+ myPlaylist +".\"";
        Assert.assertEquals(verifySongIsAddedToList(), expectedMsgForSongAdded);


        driver.navigate().refresh();
        verifyPlaylistIsCreated();

    }




    public void searchForSong(String name) throws InterruptedException {
        WebElement searchField = driver.findElement(By.cssSelector("[type='search']"));
        searchField.clear();
        searchField.sendKeys(name);
        Thread.sleep(2000);
    }

    public void clickViewAllButton(){
        WebElement viewAllButton = driver.findElement(By.cssSelector("button[data-test='view-all-songs-btn']"));
        viewAllButton.click();
    }
    public void findFirstSongInResults() {
        WebElement firstSongInList = driver.findElement(By.xpath("//div[@class='song-list-wrap main-scroll-wrap search-results']//table[@class='items']/tr[@class='song-item'][1]"));
        firstSongInList.click();
    }

    public void clickAddToButton() throws InterruptedException {
        WebElement addToButton = driver.findElement(By.xpath("//*[@id='songResultsWrapper']/header/div[3]/span/button[2]"));
        //WebElement addToButton = driver.findElement(By.xpath("//*[@id='songResultsWrapper']//li[contains(text(), 'Rock')]"));
        addToButton.click();
        Thread.sleep(2000);
    }


    public void createNewPlaylist(String playlistTitle) throws InterruptedException {
        WebElement playlistName = driver.findElement(By.xpath("//*[@id='songResultsWrapper']/header/div[3]/div/section[2]/form/input"));
        playlistName.clear();
        playlistName.sendKeys(playlistTitle);
        WebElement createPlaylistButton = driver.findElement(By.xpath("//*[@id='songResultsWrapper']/header/div[3]/div/section[2]/form/button"));
        createPlaylistButton.click();
        Thread.sleep(2000); //!!!!
    }

    /*
    public void addFistSongToPlaylistRock() throws InterruptedException {
        //WebElement addToButton = driver.findElement(By.xpath("//*[@id='songResultsWrapper']/header/div[3]/span/button[2]"));
        WebElement addToButton = driver.findElement(By.xpath("//*[@id='songResultsWrapper']//li[contains(text(), 'Rock')]"));
        addToButton.click();
        Thread.sleep(2000);
    } */

    public void addSongToPlaylistRock() throws InterruptedException {
        //WebElement rockButton = driver.findElement(By.xpath("//*[@id='songResultsWrapper']/header/div[3]/span/button[2]"));
        WebElement rockButton = driver.findElement(By.xpath("//*[@id='songResultsWrapper']//li[contains(text(), 'Rock')]"));
        Actions action = new Actions(driver);
        action.moveToElement(rockButton);
        rockButton.click();
        Thread.sleep(2000);

    }

    public String verifyListIsCreated() {
        WebElement notificationMsg = driver.findElement(By.xpath("//div[@class='alertify-logs top right']/div[@class='success show']"));
        return notificationMsg.getText();

    }

    public String verifySongIsAddedToList() {
        WebElement notificationMessage = driver.findElement(By.cssSelector("div.success.show"));
        return notificationMessage.getText();

    }

    public void selectCreatedPlaylist() throws InterruptedException {
        WebElement listWhereToAdd = driver.findElement(By.xpath("//*[@id='songResultsWrapper']/header/div[3]/div/section[1]/ul/li[5]"));
        Actions action = new Actions(driver);
        action.moveToElement(listWhereToAdd);
        listWhereToAdd.click();
        Thread.sleep(2000);
    }

    private void verifyPlaylistIsCreated()  {
        //WebElement createdPlaylist = driver.findElement(By.cssSelector("#playlists .playlist.playlist .active"));
        //WebElement createdPlaylist = driver.findElement(By.xpath("//*[@id='playlists']/ul/li[3]/a"));
        WebElement createdPlaylist = driver.findElement(By.xpath("//*[@id='playlists']//a[contains(text(), 'Rock')]"));
        createdPlaylist.isDisplayed();
        createdPlaylist.click();
    }

}
