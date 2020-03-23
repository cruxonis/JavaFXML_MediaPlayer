/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mediaplayer;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author zentrend
 */
public class FXMLDocumentController implements Initializable {
    
    private MediaPlayer mediaPlayer;
    
    @FXML
    private MediaView mediaView;
    
    @FXML
    private Slider slider;
    @FXML
    private Slider seekSlider;
    Status status= Status.STOPPED;
    
    
    
    private String filePath;
    @FXML
    private void handleButtonAction(ActionEvent event) {
        
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filter= new FileChooser.ExtensionFilter("select a File (*.mp4)", "*.mp4");
            fileChooser.getExtensionFilters().add(filter);
            File file = fileChooser.showOpenDialog(null);
            filePath = file.toURI().toString();
            
           switch(status) {
               case STOPPED:
            if (filePath != null) {
            
            Media media = new Media(filePath);
            
            mediaPlayer = new MediaPlayer(media);
            
            mediaView.setMediaPlayer(mediaPlayer);
            
            DoubleProperty width = mediaView.fitWidthProperty();
            DoubleProperty height = mediaView.fitHeightProperty();
            
            
            width.bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
            height.bind(Bindings.selectDouble(mediaView.sceneProperty(), "height"));
            
            
            slider.setValue(mediaPlayer.getVolume()* 100);
            slider.valueProperty().addListener(new InvalidationListener() {
                @Override
                public void invalidated(Observable observable) {
                    mediaPlayer.setVolume(slider.getValue()/100);
                    slider.setMin(0.0);
                }
            });
            
          /*  seekSlider.setMax(mediaPlayer.getTotalDuration().toSeconds());*/
            mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>(){
                @Override
                public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue){
                    
                    seekSlider.setMin(0.0);
                    seekSlider.setValue(0.0);
                    seekSlider.setMax(mediaPlayer.getTotalDuration().toSeconds());
                    
                    
                    seekSlider.setValue(newValue.toSeconds());
                    
                
                }
            });
                
                seekSlider.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    mediaPlayer.seek(Duration.seconds(seekSlider.getValue()));
                    
                }
            });
                mediaPlayer.play();
                status=Status.PLAYING;
                break;        
        }
               case PLAYING:
                   mediaPlayer.stop();
                   if (filePath != null) {
            
            Media media = new Media(filePath);
            
            mediaPlayer = new MediaPlayer(media);
            
            mediaView.setMediaPlayer(mediaPlayer);
            
            DoubleProperty width = mediaView.fitWidthProperty();
            DoubleProperty height = mediaView.fitHeightProperty();
            
            
            width.bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
            height.bind(Bindings.selectDouble(mediaView.sceneProperty(), "height"));
            
            
            slider.setValue(mediaPlayer.getVolume()* 100);
            slider.valueProperty().addListener(new InvalidationListener() {
                @Override
                public void invalidated(Observable observable) {
                    mediaPlayer.setVolume(slider.getValue()/100);
                    slider.setMin(0.0);
                }
            });
            
          /*  seekSlider.setMax(mediaPlayer.getTotalDuration().toSeconds());*/
            mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>(){
                @Override
                public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue){
                    
                    seekSlider.setMin(0.0);
                    seekSlider.setValue(0.0);
                    seekSlider.setMax(mediaPlayer.getTotalDuration().toSeconds());
                    
                    
                    seekSlider.setValue(newValue.toSeconds());
                    
                
                }
            });
                
                seekSlider.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    mediaPlayer.seek(Duration.seconds(seekSlider.getValue()));
                    
                }
            });
                mediaPlayer.play();
                status=Status.PLAYING;
                break; 
           
}
    }}
    @FXML
    private void pauseVideo(ActionEvent event){
        mediaPlayer.pause();
    }
    @FXML
    private void playVideo(ActionEvent event){
        mediaPlayer.play();
        mediaPlayer.setRate(1);
    }
    @FXML
    private void stopVideo(ActionEvent event){
        mediaPlayer.stop();
    }
    @FXML
    private void fastVideo(ActionEvent event){
        mediaPlayer.setRate(1.5);
    }
    @FXML
    private void fasterVideo(ActionEvent event){
        mediaPlayer.setRate(2);
    }
    @FXML
    private void slowVideo(ActionEvent event){
        mediaPlayer.setRate(0.75);
    }
    @FXML
    private void slowerVideo(ActionEvent event){
        mediaPlayer.setRate(0.5);
    }
    Stage stage;
    @FXML
    void exitVideo(ActionEvent event) {
        stage=(Stage)((Button)event.getSource()).getScene().getWindow();
        stage.close();
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
