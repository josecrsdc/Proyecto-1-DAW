package vistas;

import datos.bda.ActividadDAO;
import datos.bda.SubTipoDAO;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.SpinnerValueFactory.ListSpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import modelo.Actividad;
import org.controlsfx.control.Notifications;

public class PageGestionActividadesController implements Initializable {

    private Set<Actividad> listaActividades;
    private SubTipoDAO subTipoDAO;
    private Set<String> listaSubTipoDAO;
    private ObservableList<String> listaSubTipo;
    private ObservableList<Actividad> observableActividades;
    private ActividadDAO actividadDAO;
    private Actividad actividadSeleccionada;
    private SpinnerValueFactory puntuacion;
    private ListSpinnerValueFactory<String> valoresSubtipo;
    private PageHomeController homeController;
    private Alert eliminarEditar, confEliminar, informacion;
    private Actividad actividad;
    private ButtonType botonEliminar, botonEditar, botonCancelar;
    private ButtonType botonSi;
    private ButtonType botonNo;
    private Optional<ButtonType> resultado;

    private String nombreActividad;
    private String descripcionActividad;
    private String urlActividad;
    private String imagenActividad;
    private String codigoSubtipoActividad;
    private Double distanciaCentroActividad;
    private int id;
    private int puntuacionActividad;
    private boolean cambioNombre = false;
    private boolean cambioURL = false;
    private boolean cambioDescripcion = false;
    private boolean cambioDistancia = false;
    private boolean cambioImagen = false;
    private boolean cambioSubtipo = false;
    private boolean cambioPuntuacion = false;

    @FXML
    private TableColumn<Actividad, String> nombre;
    @FXML
    private TableColumn<Actividad, String> subTipo;
    @FXML
    private TableColumn<Actividad, String> tipo;
    @FXML
    private TableColumn<Actividad, ImageView> editar;
    @FXML
    private TableView<Actividad> tableGestion;
    @FXML
    private TextField fieldNombre;
    @FXML
    private ImageView imagen;
    @FXML
    private TextField fieldURL;
    @FXML
    private TextArea areaDescripcion;
    @FXML
    private Button buttonAgregarImagen;
    @FXML
    private Spinner<Integer> spinnerPuntuacion;
    @FXML
    private Spinner<String> spinnerSubtipo;
    @FXML
    private Pane infoActividad;
    @FXML
    private Button cancelarCambios;
    @FXML
    private Button b_AgregarActividad;
    @FXML
    private Slider sliderDistanciaCentro;
    @FXML
    private Button crearActividad;
    @FXML
    private Button btn_confirmarCambios;
    @FXML
    private Label tituloEditar;
    @FXML
    private Label tituloAgregarActividad;
    @FXML
    private Label valorDistanciaCentro;
    @FXML
    private Label labelNumActividades;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

///////////////////////////////////////////////////////////////////////////////
///////////////////////    SETTER Y GETTER     ////////////////////////////////
///////////////////////////////////////////////////////////////////////////////    
    public ActividadDAO getActividadDAO() {
        return actividadDAO;
    }

    public PageHomeController getHomeController() {
        return homeController;
    }

    public void setHomeController(PageHomeController homeController) {
        this.homeController = homeController;
    }

    public void setActividadDAO(ActividadDAO actividadDAO) {
        this.actividadDAO = actividadDAO;
    }

    public void setSubTipoDAO(SubTipoDAO subTipoDAO) {
        this.subTipoDAO = subTipoDAO;
    }

///////////////////////////////////////////////////////////////////////////////
////////////////////////////    METODOS      //////////////////////////////////
///////////////////////////////////////////////////////////////////////////////
    public void datosIniciales() {
        try {
            nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            subTipo.setCellValueFactory(new PropertyValueFactory<>("subTipo"));
            tipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
            editar.setCellValueFactory(new PropertyValueFactory<>("imagenEditar"));
            listaSubTipoDAO = subTipoDAO.hacerConsulta();
            listaSubTipo = FXCollections.observableArrayList(listaSubTipoDAO);
            valoresSubtipo = new ListSpinnerValueFactory(listaSubTipo);
            spinnerSubtipo.setValueFactory(valoresSubtipo);

            puntuacion = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5, 1, 1);

            editar.getStyleClass().add("cursorMano");
            refrescarDatos();

        } catch (SQLException ex) {
            alarmaFalloSQL();
        }
    }

    public void alertaInformacion(String titulo, String nombre) {
        Notifications.create()
                .owner(btn_confirmarCambios)
                .title(titulo)
                .text(nombre)
                .position(Pos.CENTER)
                .hideAfter(Duration.seconds(3.0))
                .showInformation();

    }

    private void punterosAcambios() {
        cambioNombre = false;
        cambioURL = false;
        cambioDescripcion = false;
        cambioDistancia = false;
        cambioImagen = false;
        cambioSubtipo = false;
        cambioPuntuacion = false;
    }

    private void recogerDatos() {
        nombreActividad = fieldNombre.getText();
        descripcionActividad = areaDescripcion.getText();
        urlActividad = fieldURL.getText();
        imagenActividad = imagen.getImage().getUrl();
        imagenActividad = imagenActividad.substring(imagenActividad.lastIndexOf("/") + 1);
        codigoSubtipoActividad = spinnerSubtipo.getValue();
        distanciaCentroActividad = sliderDistanciaCentro.getValue();
        distanciaCentroActividad = (double) Math.round(distanciaCentroActividad);
        puntuacionActividad = spinnerPuntuacion.getValue();
    }

    private void comprobarCampoDistancia() {
        if (actividadSeleccionada != null) {
            if (!valorDistanciaCentro.getText().equalsIgnoreCase(String.valueOf(actividadSeleccionada.getDistanciaCentro()).replace(".", ","))) {
                cambioDistancia = true;
                valorDistanciaCentro.setStyle("-fx-text-fill: red");
            } else {
                cambioDistancia = false;
                valorDistanciaCentro.setStyle("-fx-text-fill: black");
            }
        }
    }

    private void alertaEliminarEditar() {
        eliminarEditar = new Alert(Alert.AlertType.CONFIRMATION);
        eliminarEditar.setTitle("ACTIVIDAD " + actividadSeleccionada.getNombre());
        eliminarEditar.setHeaderText("¿Qué deseas hacer con la actividad?");
        eliminarEditar.setContentText("Selecciona una opcion");
        botonEliminar = new ButtonType("Eliminar");
        botonEditar = new ButtonType("Editar");
        botonCancelar = new ButtonType("Cancelar", ButtonData.CANCEL_CLOSE);
        eliminarEditar.getButtonTypes().setAll(botonEliminar, botonEditar, botonCancelar);
        resultado = eliminarEditar.showAndWait();
    }

    private void alertaConfirmacionEliminar() {
        confEliminar = new Alert(Alert.AlertType.CONFIRMATION);
        confEliminar.setTitle("ACTIVIDAD " + actividadSeleccionada.getNombre());
        confEliminar.setHeaderText("¿Estas seguro que deseas eliminar la actividad?");
        confEliminar.setContentText("Selecciona una opcion");
        botonSi = new ButtonType("SI");
        botonNo = new ButtonType("NO", ButtonData.CANCEL_CLOSE);
        confEliminar.getButtonTypes().setAll(botonSi, botonNo);
        resultado = confEliminar.showAndWait();
    }

    private void cargarVentanaEdicion() {
        id = actividadSeleccionada.getIdActividad();
        fieldNombre.setText(actividadSeleccionada.getNombre());
        areaDescripcion.setText(actividadSeleccionada.getDescripcion());

        if (actividadSeleccionada.getImagen() == null) {
            imagen.setImage(new Image("/recursos/imagenes/icons8_new_york_80px.png"));
        } else {
            imagen.setImage(new Image("/recursos/imagenes/" + actividadSeleccionada.getImagen()));
        }

        fieldURL.setText(actividadSeleccionada.getUrl());
        sliderDistanciaCentro.setValue(actividadSeleccionada.getDistanciaCentro());
        valorDistanciaCentro.setText(String.valueOf(actividadSeleccionada.getDistanciaCentro()));
        spinnerPuntuacion.setValueFactory(puntuacion);
        spinnerPuntuacion.getValueFactory().setValue(actividadSeleccionada.getPuntuacion());
        spinnerSubtipo.getValueFactory().setValue("MUSE");
        spinnerSubtipo.getValueFactory().setValue(actividadSeleccionada.getSubTipo());
        infoActividad.setVisible(true);
        btn_confirmarCambios.setVisible(true);
        crearActividad.setVisible(false);
        tituloAgregarActividad.setVisible(false);
        tituloEditar.setVisible(true);
    }

    private void resetPanelEditar() {
        actividadSeleccionada = null;
        punterosAcambios();

        fieldNombre.clear();
        fieldNombre.setStyle("-fx-border-width:0;");

        fieldURL.clear();
        fieldURL.setStyle("-fx-border-width:0;");

        spinnerPuntuacion.setValueFactory(puntuacion);
        spinnerPuntuacion.setStyle("-fx-border-width:0;");

        spinnerSubtipo.setStyle("-fx-border-width:0;");
        areaDescripcion.clear();
        areaDescripcion.setStyle("-fx-border-width:0;");

        sliderDistanciaCentro.setValue(10);
        valorDistanciaCentro.setText(String.valueOf(Math.round(sliderDistanciaCentro.getValue())));

        valorDistanciaCentro.setStyle("-fx-text-fill: black");
        imagen.getStyleClass().clear();
        imagen.setImage(new Image("/recursos/imagenes/icons8_new_york_80px.png"));

    }

    private boolean urlValidator(String url) {
        boolean respuesta;
        if (url.equalsIgnoreCase("")) {
            respuesta = true;
        } else {
            try {
                new URL(url).toURI();
                respuesta = true;
            } catch (URISyntaxException | MalformedURLException exception) {
                respuesta = false;
            }
        }

        return respuesta;
    }

    private void refrescarDatos() {
        try {
            listaActividades = actividadDAO.hacerConsulta();
            labelNumActividades.setText(String.valueOf(listaActividades.size()));
        } catch (SQLException ex) {
            alarmaFalloSQL();
        }
        observableActividades = FXCollections.observableArrayList(listaActividades);
        tableGestion.setItems(observableActividades);
        nombre.setSortType(TableColumn.SortType.ASCENDING);
        tableGestion.getSortOrder().add(nombre);
    }

    public void alarmaFalloSQL() {
        String alertTituloFallo = "Error con la base de datos";
        String alertMensajeFallo = "No ha sido posible cargar las actividades.";
        homeController.crearAlarmaFallo(alertTituloFallo, alertMensajeFallo);
    }
    
    
///////////////////////////////////////////////////////////////////////////////
//////////////////////////    METODOS FXML     ////////////////////////////////
///////////////////////////////////////////////////////////////////////////////
    @FXML
    public void selectActividad(MouseEvent event) {
        actividadSeleccionada = tableGestion.getSelectionModel().getSelectedItem();
        String alertTituloExito = "Eliminar";
        String alertMensajeExito = "La activiadad "
                + actividadSeleccionada.getNombre() + " ha sido eliminada con éxito.";
        String alertTituloFallo = "Fallo al eliminar";
        String alertMensajeFallo = "No ha sido posible eliminar la actividad "
                + actividadSeleccionada.getNombre();
        String alertFalloSQLPack = "No ha sido posible eliminar la actividad, por "
                + "estar incluida dentro de un pack.";
        String alertFalloSQL = "Fallo al conectar con la base de datos, la "
                + "actividad no ha sido eliminada.";

        if (event.getClickCount() == 2 && actividadSeleccionada != null) {
            id = actividadSeleccionada.getIdActividad();
            alertaEliminarEditar();
            if (resultado.get() == botonEliminar) {
                alertaConfirmacionEliminar();
                if (resultado.get() == botonSi) {
                    try {
                        if (actividadDAO.eliminarActividad(id)) {
                            listaActividades.remove(actividadSeleccionada);
                            alertaInformacion(alertTituloExito, alertMensajeExito);
                        } else {
                            alertaInformacion(alertTituloFallo, alertMensajeFallo);
                        }
                    } catch (SQLException err) {
                        if (err.getErrorCode() == 1451) {
                            homeController.crearAlarmaFallo(alertTituloFallo, alertFalloSQLPack);
                        } else {
                            homeController.crearAlarmaFallo(alertTituloFallo, alertFalloSQL);
                        }
                    }
                }
            } else if (resultado.get() == botonEditar) {
                cargarVentanaEdicion();
            }
            refrescarDatos();
        }
    }

    @FXML
    private void btnAgregarFoto(ActionEvent event) {
        File f1;
        FileChooser elegirImagen = new FileChooser();
        Image foto2;
        String alertTitulo = "Error con su imagen";
        String alertMensaje = "No ha elegido ninguna imagen.";

        elegirImagen.setTitle("Elegir imagen");
        elegirImagen.setInitialDirectory(new File(Paths.get("src/recursos/imagenes").toString()));

        f1 = elegirImagen.showOpenDialog(null);
        if (f1 != null) {
            foto2 = new Image("/recursos/imagenes/" + f1.getName());
            imagen.setImage(foto2);
            if (actividadSeleccionada != null) {
                imagen.getStyleClass().clear();
                imagen.getStyleClass().add("bordeImagenRojo");
                cambioImagen = true;
            }
        } else {
            homeController.crearAlarmaFallo(alertTitulo, alertMensaje);
        }
    }

    @FXML
    private void cancelar(ActionEvent event) {
        infoActividad.setVisible(false);
        resetPanelEditar();
    }

    @FXML
    private void btnAgregarActividad(ActionEvent event) {
        resetPanelEditar();

        infoActividad.setVisible(true);
        tituloAgregarActividad.setVisible(true);
        crearActividad.setVisible(true);
        btn_confirmarCambios.setVisible(false);
        tituloEditar.setVisible(false);
    }

    @FXML
    private void confirmarCambios(ActionEvent event) {
        recogerDatos();

        boolean respuesta = false;
        String alertTituloFallo = "Fallo con la base de datos";
        String alertMensajeFallo = "No ha sido posible editar la actividad.";
        String alertTituloExito = "Actividad modificada";
        String alertMensajeExito = "La actividad " + nombreActividad
                + " ha sido modificada con éxito.";
        String alertaTituloNoEdit = "Actividad sin cambios";
        String alertaMensajeNoEdit = "No se ha detectado ningún cambio en los datos de la actividad.";
        if (cambioDescripcion || cambioDistancia || cambioImagen || cambioNombre
                || cambioPuntuacion || cambioSubtipo || cambioURL) {
            try {
                respuesta = actividadDAO.modificarActividad(id, nombreActividad,
                        descripcionActividad, urlActividad, imagenActividad,
                        codigoSubtipoActividad, distanciaCentroActividad, puntuacionActividad);
            } catch (SQLException ex) {
                homeController.crearAlarmaFallo(alertTituloFallo, alertMensajeFallo);
            }

            if (respuesta) {
                alertaInformacion(alertTituloExito, alertMensajeExito);
                resetPanelEditar();
                infoActividad.setVisible(false);
                refrescarDatos();
            }
            punterosAcambios();
        } else {
            alertaInformacion(alertaTituloNoEdit, alertaMensajeNoEdit);
        }
    }

    @FXML
    private void crearActividad(ActionEvent event) {
        recogerDatos();
        String respuesta = "";
        boolean urlValido;
        String alertTituloExito = "Actividad creada";
        String alertMensajeExito = "La actividad " + nombreActividad
                + " ha sido creada con éxito.";
        String alertTituloFalloSQL = "Fallo con la Base de datos";
        String alertMensajeFalloSQL = "No ha sido posible crear la nueva actividad.";
        String alertTituloFalloCampos = "Error al crear actividad";
        String mensajeNombreVacio = "El campo nombre no puede estar vacio.\n";
        String mensajeDescripcionVacia = "El campo descripción no puede estar vacio.\n";
        String mensajeURLInvalida = "El campo URL no es válido.";

        urlValido = urlValidator(fieldURL.getText());
        if (!nombreActividad.equalsIgnoreCase("")
                && !descripcionActividad.equalsIgnoreCase("") && urlValido) {

            actividad = new Actividad(nombreActividad, descripcionActividad,
                    urlActividad, imagenActividad, codigoSubtipoActividad,
                    distanciaCentroActividad, puntuacionActividad);

            actividad.setUrl(urlActividad);

            try {
                if (actividadDAO.insertarActividad(actividad)) {
                    alertaInformacion(alertTituloExito, alertMensajeExito);
                    datosIniciales();
                    infoActividad.setVisible(false);
                }
            } catch (SQLException ex) {
                homeController.crearAlarmaFallo(alertTituloFalloSQL, alertMensajeFalloSQL);
            }

        } else {
            if (nombreActividad.equalsIgnoreCase("")) {
                respuesta = mensajeNombreVacio;
            }
            if (descripcionActividad.equalsIgnoreCase("")) {
                respuesta += mensajeDescripcionVacia;
            }
            if (!urlValido) {
                respuesta += mensajeURLInvalida;
            }
            homeController.crearAlarmaFallo(alertTituloFalloCampos, respuesta);
        }
    }

    @FXML
    private void actualizarDistancia(MouseEvent event) {
        distanciaCentroActividad = sliderDistanciaCentro.getValue();
        DecimalFormat df = new DecimalFormat("0.0#");
        valorDistanciaCentro.setText(df.format(distanciaCentroActividad));

        comprobarCampoDistancia();

    }

    @FXML
    private void comprobarCampoNombre(KeyEvent event) {

        if (actividadSeleccionada != null) {
            if (!fieldNombre.getText().equals(actividadSeleccionada.getNombre())) {
                cambioNombre = true;
                fieldNombre.setStyle("-fx-border-width:1;-fx-border-color: red");

            } else {
                cambioNombre = false;
                fieldNombre.setStyle("-fx-border-width:0");

            }
        }
    }

    @FXML
    private void comprobarCampoUrl(KeyEvent event) {
        if (actividadSeleccionada != null) {

            if (!fieldURL.getText().equals(actividadSeleccionada.getUrl())) {
                cambioURL = true;
                fieldURL.setStyle("-fx-border-width:1;-fx-border-color: red");

            } else {
                cambioURL = false;
                fieldURL.setStyle("-fx-border-width:0");

            }
        }
    }

    @FXML
    private void comprobarCampoDescripcion(KeyEvent event) {
        if (actividadSeleccionada != null) {

            if (!areaDescripcion.getText().equals(actividadSeleccionada.getDescripcion())) {

                cambioDescripcion = true;
                areaDescripcion.setStyle("-fx-border-width:1;-fx-border-color: red");

            } else {
                cambioDescripcion = false;
                areaDescripcion.setStyle("-fx-border-width:0");
            }
        }
    }

    @FXML
    private void comprobarCampoPuntuacion(MouseEvent event) {
        if (actividadSeleccionada != null) {

            if (spinnerPuntuacion.getValueFactory().getValue() != actividadSeleccionada.getPuntuacion()) {
                cambioPuntuacion = true;
                spinnerPuntuacion.setStyle("-fx-border-width:1;-fx-border-color: red");

            } else {
                cambioPuntuacion = false;
                spinnerPuntuacion.setStyle("-fx-border-width:0");
            }
        }
    }

    @FXML
    private void comprobarCampoSubtipo(MouseEvent event) {
        if (actividadSeleccionada != null) {

            if (!spinnerSubtipo.getValueFactory().getValue().equalsIgnoreCase(actividadSeleccionada.getSubTipo())) {
                cambioSubtipo = true;
                spinnerSubtipo.setStyle("-fx-border-width:1; -fx-border-color: red");

            } else {
                cambioSubtipo = false;
                spinnerSubtipo.setStyle("-fx-border-width:0;");

            }
        }
    }

}
