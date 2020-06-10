
package vistas;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Prototipo_v1 extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("pageHome.fxml"));
        
        Scene scene = new Scene(root);
        
        String css = Prototipo_v1.class.getResource("/css/estilos.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setTitle("Barcelona y punto");
        stage.getIcons().add(new Image("/recursos/imagenes/logo2.png"));
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
        
        
    }


    public static void main(String[] args) {
        launch(args);
        
    }
    
    
}
