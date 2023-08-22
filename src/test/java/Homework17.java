import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Homework17 extends BaseTest {
    @Test
    public void addSongToPlaylist() throws InterruptedException {
        loginToKoel();

        findSongToAdd("Scott");

        clickViewAllButton();

        findFirstSongInResults();
        addFistSongToPlaylist();

        createNewPlaylist();


        String expectedMessageForListCreated = "Created playlist \"Rock.\"";
        Assert.assertEquals(verifyListIsCreated(), expectedMessageForListCreated);


        findSongToAdd("Ketsa");
        clickViewAllButton();
        findFirstSongInResults();
        addFistSongToPlaylist();

        selectCreatedPlaylist();

        //verifySongIsAddedToList();
        String expectedMsgForSongAdded = "Added 1 song into \"Rock.\"";
        Assert.assertEquals(verifySongIsAddedToList(), expectedMsgForSongAdded);


        driver.navigate().refresh();
        Thread.sleep(5000);

        verifyPlaylistIsCreated();

    }




    public void findSongToAdd(String name) throws InterruptedException {
        WebElement searchField = driver.findElement(By.cssSelector("[type='search']"));
        searchField.clear();
        searchField.sendKeys(name);
        Thread.sleep(2000);
    }

    public void clickViewAllButton() throws InterruptedException {
        WebElement viewAllButton = driver.findElement(By.cssSelector("button[data-test='view-all-songs-btn']"));
        viewAllButton.click();
        Thread.sleep(2000);
    }
    public void findFirstSongInResults() throws InterruptedException {

        //WebElement firstSongInList = driver.findElement(By.xpath("//section[@id='songResultWrapper']//tr[@class='song-item'][1]"));
        WebElement firstSongInList = driver.findElement(By.xpath("//div[@class='song-list-wrap main-scroll-wrap search-results']//table[@class='items']/tr[@class='song-item'][1]"));
        //WebElement firstSongInList = driver.findElement(By.cssSelector("div.virtual-scroller.scroller div.item-container table.items > tr:nth-child(1)"));
        firstSongInList.click();
        //WebElement firstSongInList = driver.findElement(By.cssSelector("#songResultsWrapper > div > div > div.item-container > table > tr.song-item.selected > td.title"));
        //#songResultsWrapper > div > div > div.item-container > table > tr.song-item.selected > td.title
        Thread.sleep(2000);
    }

    public void addFistSongToPlaylist() throws InterruptedException {
        //WebElement addToButton = driver.findElement(By.cssSelector("button[title='Add selected songs to…']"));
        WebElement addToButton = driver.findElement(By.xpath("//*[@id='songResultsWrapper']/header/div[3]/span/button[2]"));
        addToButton.click();
        Thread.sleep(2000);

    }

    public void createNewPlaylist() throws InterruptedException {
        WebElement playlistName = driver.findElement(By.xpath("//*[@id='songResultsWrapper']/header/div[3]/div/section[2]/form/input"));
        playlistName.clear();
        //playlistName.sendKeys(myPlayList);
        playlistName.sendKeys("Rock");
        WebElement createPlaylistButton = driver.findElement(By.xpath("//*[@id='songResultsWrapper']/header/div[3]/div/section[2]/form/button"));
        createPlaylistButton.click();
        Thread.sleep(2000);
    }

    public String verifyListIsCreated() throws InterruptedException {
        WebElement notificationMsg = driver.findElement(By.xpath("//div[@class='alertify-logs top right']/div[@class='success show']"));
        //Assert.assertEquals(notificationMessage.getText(), "Created playlist \"Rock.\"");
        return notificationMsg.getText();
        //Thread.sleep(2000);
    }


    public String verifySongIsAddedToList() throws InterruptedException {
        WebElement notificationMessage = driver.findElement(By.cssSelector("div.success.show"));
        return notificationMessage.getText();
        //Thread.sleep(2000);
    }

    /*
    public String verifySongIsAddedToList(){
        WebElement notificationMessage = driver.findElement(By.cssSelector("div.success.show"));
        return notificationMessage.getText();
    }*/

    public void selectCreatedPlaylist() throws InterruptedException {
        WebElement listWhereToAdd = driver.findElement(By.xpath("//*[@id='songResultsWrapper']/header/div[3]/div/section[1]/ul/li[5]"));
        Actions action = new Actions(driver);
        action.moveToElement(listWhereToAdd);
        listWhereToAdd.click();
        Thread.sleep(2000);
    }

    private void verifyPlaylistIsCreated() throws InterruptedException {
        //WebElement createdPlaylist = driver.findElement(By.cssSelector("#playlists .playlist.playlist .active"));
        WebElement createdPlaylist = driver.findElement(By.xpath("//*[@id='playlists']/ul/li[3]/a"));
        createdPlaylist.isDisplayed();
        createdPlaylist.click();
        Thread.sleep(10000);
    }






}
