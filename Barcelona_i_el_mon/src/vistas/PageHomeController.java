package vistas;

import datos.bda.ActividadDAO;
import datos.bda.DetallePackDAO;
import datos.bda.GestionBD;
import datos.bda.PackDAO;
import datos.bda.SubTipoDAO;
import datos.bda.TipoDAO;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import modelo.Pack;
import org.controlsfx.control.Notifications;

public class PageHomeController implements Initializable, DraggedScene {

    private final GestionBD CONEXION = new GestionBD();
    private final ActividadDAO ACTIVIDADDAO = new ActividadDAO();
    private final PackDAO PACKDAO = new PackDAO();
    private final DetallePackDAO DETALLEPACKDAO = new DetallePackDAO();
    private final TipoDAO TIPODAO = new TipoDAO();
    private final SubTipoDAO SUBTIPODAO = new SubTipoDAO();

    private PageQueHacerController queHacerController;
    private PageDondeDormirController dondeDormirController;
    private PageDetallesPackController detallesPackController;
    private PageDondeComerController comerController;
    private PageHomeController homeController;
    private PageGestionActividadesController gestionActividadesController;
    private Pack pack;

    private String ventana = "";
    private final String ALARMAIO = "No ha sido posible localizar " + ventana;
    private final String ALARMASQLTITULO = "Error conexión BD";
    private final String ALARMASQLMENSAJE = "Fallo con la base de datos";

    @FXML
    private Label tituloVentana;
    @FXML
    private ImageView bDetallesPack;
    @FXML
    private AnchorPane panelRoot;
    @FXML
    private Pane panelBase;
    @FXML
    private VBox panelMenuPrincipal;
    @FXML
    private Button bMenuInicio;
    @FXML
    private Button bMenuQueHacer;
    @FXML
    private Pane panelTopBar;
    @FXML
    private Button bMenuDondeDormir;
    @FXML
    private Button bMenuDondeComer;
    @FXML
    private ImageView imgConexion;
    @FXML
    private Label cantidadCarrito;
    @FXML
    private ImageView closeApp;
    @FXML
    private Button bDropDownMenu;
    @FXML
    private Button bMenuGestion;
    @FXML
    private ImageView recargarBD;
    @FXML
    private ImageView trianguloAlarma;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        this.onDraggedScene(panelRoot);
        try {
            CONEXION.conectarBD();
            imgConexion.setImage((new Image("/recursos/imagenes/conexionOn.png")));
            pasarConexion();
            recargarBD.setVisible(false);
            trianguloAlarma.setVisible(false);
        } catch (SQLException ex) {
            Alert dialogoAlerta = new Alert(Alert.AlertType.ERROR);
            dialogoAlerta.setTitle("Error");
            dialogoAlerta.setHeaderText(ALARMASQLTITULO);
            dialogoAlerta.setContentText(ALARMASQLMENSAJE);
            dialogoAlerta.showAndWait();
            recargarBD.setVisible(true);
            trianguloAlarma.setVisible(true);
            bDropDownMenu.setDisable(true);
        }
    }

///////////////////////////////////////////////////////////////////////////////
///////////////////////    SETTER Y GETTER     ////////////////////////////////
///////////////////////////////////////////////////////////////////////////////
    public void setCarritoCantidadString(String carritoCantidadString) {
        if (pack != null && !pack.getListaDetallePack().isEmpty()) {
            cantidadCarrito.setVisible(true);
            this.cantidadCarrito.setText(carritoCantidadString);
        } else {
            cantidadCarrito.setVisible(false);
        }
    }

    public void setPack(Pack pack) {
        this.pack = pack;
    }

    public String getCantidadCarrito() {
        return cantidadCarrito.getText();
    }

///////////////////////////////////////////////////////////////////////////////
////////////////////////////    METODOS      //////////////////////////////////
///////////////////////////////////////////////////////////////////////////////
    public void crearAlarmaFallo(String header, String contenido) {
        Notifications.create()
                .owner(bMenuInicio)
                .title(header)
                .text(contenido)
                .position(Pos.CENTER)
                .hideAfter(Duration.seconds(3.0))
                .showError();

    }

    private void pasarConexion() {
        ACTIVIDADDAO.setConn(CONEXION.getConn());
        PACKDAO.setConn(CONEXION.getConn());
        DETALLEPACKDAO.setConn(CONEXION.getConn());
        TIPODAO.setConn(CONEXION.getConn());
        SUBTIPODAO.setConn(CONEXION.getConn());
    }

    public Pack crearNuevoPack() {
        int id;
        String nombre, dni;
        try {
            id = PACKDAO.ultimoValorId();
            nombre = "Pack - " + id;
            dni = "53274644L";
            pack = new Pack(id, nombre, dni);
        } catch (SQLException ex) {
            crearAlarmaFallo(ALARMASQLTITULO, ALARMASQLMENSAJE);
        }
        return pack;
    }

    public void inicializarDatePickers(DatePicker fechaInicio, DatePicker fechaFin) {
        Callback<DatePicker, DateCell> dayCellFactory
                = (final DatePicker datePicker) -> new DateCell() {
            // Sobreescribiendo el método updateItem que nos permite configurar las celdas
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);

                // Fechas anteriores a hoy
                if (item.isBefore(LocalDate.now()) || empty) {
                    setTextFill(Color.LIGHTGREY);
                } else {
                    setTextFill(Color.BLACK);
                }

                // fines de semana en color verde
                DayOfWeek dayweek = item.getDayOfWeek();
                if (item.isAfter(LocalDate.now()) && (dayweek == DayOfWeek.SATURDAY || dayweek == DayOfWeek.SUNDAY)) {
                    setTextFill(Color.GREEN);
                }
            }

        };
        // Deshabilitando fechas seleccionables
        fechaInicio.setDayCellFactory(dayCellFactory);
        fechaFin.setDayCellFactory(dayCellFactory);
        // No mostrar el número de semana
        fechaInicio.setShowWeekNumbers(false);
        fechaFin.setShowWeekNumbers(false);

    }

///////////////////////////////////////////////////////////////////////////////
//////////////////////////    METODOS FXML     ////////////////////////////////
///////////////////////////////////////////////////////////////////////////////
    @FXML
    public void home(MouseEvent event) {
        ventana = "pageHome.fxml";
        tituloVentana.setText("Home");
        pasarConexion();
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(ventana));
            AnchorPane panelHome = loader.load();
            homeController = loader.getController();
            panelRoot.getChildren().setAll(panelHome);
            homeController.setPack(pack);
            if (pack != null) {
                homeController.setCarritoCantidadString(String.valueOf(pack.getNumDetallesPack()));
            }
        } catch (IOException ex) {
            crearAlarmaFallo("Fallo IO", ALARMAIO);
        }
    }

    @FXML
    private void dondeDormir(MouseEvent event) {
        ventana = "pageDondeDormir.fxml";
        tituloVentana.setText("Dónde Dormir");
        pasarConexion();
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(ventana));
            AnchorPane panelDormir = loader.load();
            dondeDormirController = loader.getController();
            dondeDormirController.setHomeController(this);
            dondeDormirController.setIdPack(PACKDAO.ultimoValorId());
            dondeDormirController.setActividadDAO(ACTIVIDADDAO);
            dondeDormirController.generarLista();
            panelBase.getChildren().setAll(panelDormir);
            dondeDormirController.setPack(pack);
        } catch (IOException ex) {
            crearAlarmaFallo("Fallo IO", ALARMAIO);
        } catch (SQLException e) {
            crearAlarmaFallo(ALARMASQLTITULO, ALARMASQLMENSAJE);
            home(event);
        } catch (Exception err) {
            crearAlarmaFallo(ALARMASQLTITULO, ALARMASQLMENSAJE);
        }
    }

    @FXML
    private void dondeComer(MouseEvent event) {
        ventana = "pageDondeComer.fxml";
        tituloVentana.setText("Dónde Comer");
        pasarConexion();
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(ventana));
            AnchorPane panelComer = loader.load();
            comerController = loader.getController();
            comerController.setHomeController(this);
            comerController.setIdPack(PACKDAO.ultimoValorId());
            comerController.setActividadDAO(ACTIVIDADDAO);
            comerController.generarLista();
            panelBase.getChildren().setAll(panelComer);
            comerController.setPack(pack);
        } catch (IOException ex) {
            crearAlarmaFallo("Fallo IO", ALARMAIO);
        } catch (SQLException e) {
            crearAlarmaFallo(ALARMASQLTITULO, ALARMASQLMENSAJE);
            home(event);
        } catch (Exception err) {
            crearAlarmaFallo(ALARMASQLTITULO, ALARMASQLMENSAJE);
        }
    }

    @FXML
    private void queHacer(MouseEvent event) {
        ventana = "pageQueHacer.fxml";
        tituloVentana.setText("Qué Hacer");
        pasarConexion();
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(ventana));
            AnchorPane panelHacer = loader.load();
            queHacerController = loader.getController();
            queHacerController.setHomeController(this);
            queHacerController.setIdPack(PACKDAO.ultimoValorId());
            queHacerController.setActividadDAO(ACTIVIDADDAO);
            queHacerController.generarLista();
            panelBase.getChildren().setAll(panelHacer);
            queHacerController.setPack(pack);
        } catch (IOException ex) {
            crearAlarmaFallo("Fallo IO", ALARMAIO);
        } catch (SQLException e) {
            crearAlarmaFallo(ALARMASQLTITULO, ALARMASQLMENSAJE);
            home(event);
        } catch (Exception err) {
            crearAlarmaFallo(ALARMASQLTITULO, ALARMASQLMENSAJE);
        }
    }

    @FXML
    private void panelDetallesPack(MouseEvent event) {
        ventana = "pageDetallesPack.fxml";
        if (pack != null) {
            tituloVentana.setText("Detalles Pack");
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(ventana));
                AnchorPane panelDetallesPack = loader.load();
                detallesPackController = loader.getController();
                detallesPackController.setDetallePackDAO(DETALLEPACKDAO);
                detallesPackController.setPackDAO(PACKDAO);
                panelBase.getChildren().setAll(panelDetallesPack);
                detallesPackController.setPack(pack);
                detallesPackController.setPageHomeController(this);
                detallesPackController.cargarDetallesPack();
            } catch (IOException ex) {
                crearAlarmaFallo("Fallo IO", ALARMAIO);
            } catch (Exception err) {
                crearAlarmaFallo(ALARMASQLTITULO, ALARMASQLMENSAJE);
            }
        }
    }

    @FXML
    private void gestionActividades(MouseEvent event) {
        ventana = "pageGestionActividades.fxml";
        tituloVentana.setText("Gestión Actividades");
        pasarConexion();
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(ventana));
            AnchorPane panelGestionActividades = loader.load();
            gestionActividadesController = loader.getController();
            gestionActividadesController.setActividadDAO(ACTIVIDADDAO);
            panelBase.getChildren().setAll(panelGestionActividades);
            gestionActividadesController.setSubTipoDAO(SUBTIPODAO);
            gestionActividadesController.datosIniciales();
            gestionActividadesController.setHomeController(this);
        } catch (IOException ex) {
            crearAlarmaFallo("Fallo IO", ALARMAIO);
        } catch (Exception err) {
            crearAlarmaFallo(ALARMASQLTITULO, ALARMASQLMENSAJE);
            home(event);
        }
    }

    @FXML
    private void cerrarAplicacion(MouseEvent event) {
        Stage stage = (Stage) this.closeApp.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void desplegarActividades(ActionEvent event) {
        if (!bMenuDondeComer.isVisible() && !bMenuDondeDormir.isVisible() && !bMenuQueHacer.isVisible() && !bMenuGestion.isVisible()) {
            bMenuDondeComer.setVisible(true);
            bMenuDondeDormir.setVisible(true);
            bMenuQueHacer.setVisible(true);
            bMenuGestion.setVisible(true);
        } else {
            bMenuDondeComer.setVisible(false);
            bMenuDondeDormir.setVisible(false);
            bMenuQueHacer.setVisible(false);
            bMenuGestion.setVisible(false);
        }
    }

}
