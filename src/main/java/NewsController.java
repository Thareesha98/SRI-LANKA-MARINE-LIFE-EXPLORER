/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
//
//import java.net.URL;
//import java.util.ResourceBundle;
//import javafx.fxml.Initializable;
package com.students.sri_lanka_marine_life_explorer;

/**
 * FXML Controller class
 *
 * @author thareesha
 */


//package com.students.sri_lanka_marine_life_explorer.controllers;
//
//import java.net.URL;
//import java.util.ResourceBundle;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.web.WebView;
//import javafx.scene.web.WebEngine;
//
//public class NewsController implements Initializable {
//
//    @FXML
//    private WebView webView1;
//    
//    @FXML
//    private WebView webView2;
//
//    // Replace with the actual news site you want to display
//    
//     private static final String NEWS_URL1 = "https://rss.app/embed/v1/wall/2gC8Vn0o4MR8p4uY";
//    private static final String NEWS_URL2 = "https://rss.app/embed/v1/ticker/2gC8Vn0o4MR8p4uY";
//private static final String test="https://www.bbc.com/";
//
//   //String url0 = getClass().getResource("/web/news.html").toExternalForm();
//    
//    
//    
//    @Override
//    public void initialize(URL url, ResourceBundle rb)  {
//        System.out.println("Strart erbiiiiiiiiii");
//        WebEngine engine1 = webView1.getEngine();
//        WebEngine engine2 = webView2.getEngine();
//        engine1.load(NEWS_URL1);
//        engine2.load(NEWS_URL2);
//      // webView1.getEngine().load(url0);
//
//
//    }
//
//    



























import com.students.sri_lanka_marine_life_explorer.models.NewsItem;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.application.Platform;

import javax.xml.parsers.DocumentBuilderFactory;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import javafx.util.Callback;
import org.w3c.dom.*;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class NewsController {
     private static final String NEWS_URL2 = "https://rss.app/embed/v1/ticker/x43FIU2UXUP35VmO";

    @FXML
    private ListView<NewsItem> newsListView;
    
    @FXML
    private WebView webView2;

    @FXML
    private void initialize() {
        

        
        
    //      newsListView.getScene().getStylesheets().add(getClass().getResource("/styles/news.css").toExternalForm());

        loadRSSFeed("https://rss.app/feeds/x43FIU2UXUP35VmO.xml");

        newsListView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                NewsItem item = newsListView.getSelectionModel().getSelectedItem();
                if (item != null) {
                    try {
                        Desktop.getDesktop().browse(new URI(item.getLink()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        newsListView.setCellFactory(new Callback<>() {
            @Override
            public ListCell<NewsItem> call(ListView<NewsItem> listView) {
                return new ListCell<>() {
                    private final VBox vbox = new VBox();
                    private final Text title = new Text();
                    private final Text description = new Text();
                    private final Text date = new Text();
                    private final ImageView imageView = new ImageView();

                    {
                        title.setFont(Font.font("Arial", 16));
                        description.setWrappingWidth(550);
                        imageView.setFitWidth(100);
                        imageView.setFitHeight(100);
                        HBox hbox = new HBox(imageView, new VBox(title, date, description));
                        hbox.setSpacing(10);
                        vbox.getChildren().add(hbox);
                        vbox.setSpacing(5);
                        
                        
                        
                    }

                    @Override
                    protected void updateItem(NewsItem item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setGraphic(null);
                        } else {
                            title.setText(item.getTitle());
                            title.getStyleClass().add("title");
                            
                            date.getStyleClass().add("date");
                            description.getStyleClass().add("description");
                            
                            description.setText(item.getDescription());
                            date.setText("📅 " + item.getPubDate());
                            try {
                                imageView.setImage(new Image(item.getImageUrl(), true));
                            } catch (Exception e) {
                                imageView.setImage(null);
                            }
                            setGraphic(vbox);
                        }
                    }
                };
            }
        });
       
        
        WebEngine engine2 = webView2.getEngine();
        engine2.load(NEWS_URL2);
        
       
        
    }

    private void loadRSSFeed(String rssUrl) {
        new Thread(() -> {
            try {
                HttpURLConnection connection = (HttpURLConnection) new URL(rssUrl).openConnection();
                Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(connection.getInputStream());
                doc.getDocumentElement().normalize();

                NodeList items = doc.getElementsByTagName("item");
                List<NewsItem> newsItems = new ArrayList<>();

                for (int i = 0; i < items.getLength(); i++) {
                    Element item = (Element) items.item(i);
                    String title = getText(item, "title");
                    String link = getText(item, "link");
                 //   String description = getText(item, "description");
                    String rawDescription = getText(item, "description");
                    String description = rawDescription.replaceAll("<[^>]*>", "").trim();

                    String pubDate = getText(item, "pubDate");
                    String imageUrl = null;

                    NodeList mediaContent = item.getElementsByTagName("media:content");
                    if (mediaContent.getLength() > 0) {
                        Element media = (Element) mediaContent.item(0);
                        imageUrl = media.getAttribute("url");
                    }

                    newsItems.add(new NewsItem(title, description, link, pubDate, imageUrl));
                }

                Platform.runLater(() -> newsListView.getItems().setAll(newsItems));

            } catch (Exception e) {
                e.printStackTrace();
                Platform.runLater(() -> newsListView.getItems().clear());
            }
        }).start();
    }

    private String getText(Element parent, String tag) {
        NodeList list = parent.getElementsByTagName(tag);
        return list.getLength() > 0 ? list.item(0).getTextContent() : "";
    }
    
    @FXML
    private void goToMainMenu() throws IOException{
        App.setRoot("home");
    }
}
