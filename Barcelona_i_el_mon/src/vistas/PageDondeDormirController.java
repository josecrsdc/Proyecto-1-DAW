package vistas;

import datos.bda.ActividadDAO;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import modelo.Actividad;
import modelo.DetallePack;
import modelo.Pack;

public class PageDondeDormirController implements Initializable {

    private ActividadDAO actividadDAO;
    private PageHomeController homeController;
    private DetallePack detallePack;
    private Actividad actividadSeleccionada;
    private Pack pack;
    private Set<Actividad> listaActividades;
    private Set<Actividad> actividadesFiltradas = new HashSet<>();
    private SpinnerValueFactory plazas;
    private LocalDate fechaSeleccionada;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private LocalDateTime fechaHoraInicio;
    private LocalDateTime fechaHoraFin;
    private ObservableList<String> listaHorasInicio;
    private ObservableList<String> listaHorasFin;
    private Double precio;
    private int idPack;
    private int numDias;

    @FXML
    private Label distanciaCentroActividad;
    @FXML
    private Label puntuacionActividad;
    @FXML
    private DatePicker calendarioActividadFin;
    @FXML
    private Hyperlink urlActividad;
    @FXML
    private Slider sliderPrecio;
    @FXML
    private Label labelPrecio;
    @FXML
    private ComboBox<String> horaActividadInicio;
    @FXML
    private ComboBox<String> horaActividadFin;
    @FXML
    private ImageView fotoActividad;
    @FXML
    private Label nombreActividad;
    @FXML
    private DatePicker calendarioActividadInicio;
    @FXML
    private Spinner<Integer> numPlazasActividad;
    @FXML
    private ImageView bCancelarActividad;
    @FXML
    private Label descripcionActividad;
    @FXML
    private Pane infoActividad;
    @FXML
    private Button bAgregarActividad;
    @FXML
    private Label numDiasActividad;
    @FXML
    private ScrollPane scrollPaneDondeDormir;
    @FXML
    private AnchorPane anchorScrollDondeDormir;
    @FXML
    private Pane paneFiltros;
    @FXML
    private CheckBox puntuacionCinco;
    @FXML
    private CheckBox puntuacionCuatro;
    @FXML
    private CheckBox puntuacionTres;
    @FXML
    private CheckBox puntuacionDos;
    @FXML
    private CheckBox puntuacionUno;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        horaActividad();
        numPlazas();
        calendario();
    }

///////////////////////////////////////////////////////////////////////////////
///////////////////////    SETTER Y GETTER     ////////////////////////////////
///////////////////////////////////////////////////////////////////////////////
    public void setPack(Pack pack) {
        this.pack = pack;
    }

    public void setActividadDAO(ActividadDAO actividad) {
        this.actividadDAO = actividad;
    }

    public void setIdPack(int idPack) {
        this.idPack = idPack;
    }

    public void setHomeController(PageHomeController homeController) {
        this.homeController = homeController;
    }

///////////////////////////////////////////////////////////////////////////////
////////////////////////////    METODOS      //////////////////////////////////
///////////////////////////////////////////////////////////////////////////////
    public void limpiarPantalla() {
        anchorScrollDondeDormir.getChildren().clear();
    }

    public void generarLista() {
        try {
            listaActividades = actividadDAO.hacerConsulta("DORM");
        } catch (SQLException ex) {
            homeController.crearAlarmaFallo("Fallo con base de datos", "No se "
                    + "han podido recuperar las actividades desde la base de datos.");
        }

        bloqueDondeDormir(listaActividades);
        homeController.inicializarDatePickers(calendarioActividadInicio, calendarioActividadFin);
    }

    public void bloqueDondeDormir(Set<Actividad> listaActividades) {

        limpiarPantalla();
        int posCompY = 30;
        int posCompX = 40;
        int contador = 0;
        Iterator<Actividad> it;
        Pane paneContenedor;
        Label tituloActividad;
        ImageView imgPuntuacion;
        ImageView imagen;

        it = listaActividades.iterator();
        while (it.hasNext()) {
            if (contador == 2) {
                posCompY += 260;
                posCompX = 40;
                contador = 0;
            }
            Actividad actividad = it.next();

            //Creacion del Pane principal de la vista
            paneContenedor = new Pane();

            paneContenedor.setMinSize(200, 240);
            paneContenedor.setLayoutX(posCompX);
            paneContenedor.setLayoutY(posCompY);
            paneContenedor.getStyleClass().add("cursorMano");

            //Creacion del titulo
            tituloActividad = new Label();
            tituloActividad.setText(actividad.getNombre());
            tituloActividad.setLayoutX(10);
            tituloActividad.setLayoutY(200);
            tituloActividad.getStyleClass().add("tituloActividad");

            //Creacion de puntuación
            Label puntuacionGridPane = new Label();
            puntuacionGridPane.setText(String.valueOf(actividad.getPuntuacion()));
            puntuacionGridPane.setLayoutX(152);
            puntuacionGridPane.setLayoutY(10);
            puntuacionGridPane.getStyleClass().add("puntuacionActividad");

            imgPuntuacion = new ImageView("/recursos/imagenes/icons8_filled_boo"
                    + "kmark_ribbon_64px.png");
            imgPuntuacion.setLayoutX(125);
            imgPuntuacion.setLayoutY(0);

            //Creacion de la imagen
            imagen = new ImageView();
            if (actividad.getImagen() != null) {
                imagen.setImage(new Image("/recursos/imagenes/" + actividad.getImagen()));
            } else {
                imagen.setImage(new Image("/recursos/imagenes/icons8_new_york_80px.png"));
            }

            imagen.setLayoutX(10);
            imagen.setLayoutY(10);
            imagen.setFitHeight(180);
            imagen.setFitWidth(180);
            imagen.getStyleClass().add("sombraGrid");

            //Añadimos cada componente a su padre para visualizarlo        
            anchorScrollDondeDormir.getChildren().add(paneContenedor);
            paneContenedor.getChildren().add(imagen);
            paneContenedor.getChildren().add(tituloActividad);
            paneContenedor.getChildren().add(imgPuntuacion);
            paneContenedor.getChildren().add(puntuacionGridPane);

            posCompX += 220;
            contador++;

            //vamos sumando lo alto para que aparezca el scroll del lado
            anchorScrollDondeDormir.setMinHeight(posCompY + 260);

            paneContenedor.setOnMouseClicked((event) -> {
                actividadSeleccionada = actividad;
                nombreActividad.setText(actividad.getNombre());
                descripcionActividad.setText(actividad.getDescripcion());
                distanciaCentroActividad.setText(String.valueOf(actividad.getDistanciaCentro()) + " km");
                puntuacionActividad.setText(String.valueOf(actividad.getPuntuacion()));

                if (actividad.getImagen() != null) {
                    fotoActividad.setImage(new Image("/recursos/imagenes/"
                            + actividad.getImagen()));
                }
                infoActividad.setVisible(true);
            });
        }

    }

    private void horaActividad() {
        int inicioHora, inicioMinuto;
        String inicioMinText;
        String valorHora;

        for (inicioHora = 10; inicioHora <= 20; inicioHora++) {
            for (inicioMinuto = 00; inicioMinuto < 60; inicioMinuto += 15) {
                inicioMinText = String.valueOf(inicioMinuto);
                if (inicioMinuto == 0) {

                    inicioMinText = inicioMinuto + "0";
                }

                horaActividadInicio.getItems().add(inicioHora + ":" + inicioMinText);
                horaActividadFin.getItems().add(inicioHora + ":" + inicioMinText);
            }
        }
        horaActividadInicio.setVisibleRowCount(6);
        horaActividadFin.setVisibleRowCount(6);

        listaHorasInicio = horaActividadInicio.getItems();
        listaHorasFin = horaActividadFin.getItems();
        valorHora = DateTimeFormatter.ofPattern("HH:'00'").format(LocalTime.now().plusHours(1));

        if (!listaHorasInicio.contains(valorHora)) {
            horaActividadInicio.getItems().add(valorHora);
        }

        horaActividadInicio.setValue(valorHora);

        valorHora = DateTimeFormatter.ofPattern("HH:'00'").format(LocalTime.now().plusHours(2));

        if (!listaHorasFin.contains(valorHora)) {
            horaActividadFin.getItems().add(valorHora);
        }

        horaActividadFin.setValue(valorHora);

    }

    public void limpiarCamposActividad() {
        String valorHora;
        calendario();
        valorHora = DateTimeFormatter.ofPattern("HH:'00'").format(LocalTime.now().plusHours(1));
        if (!listaHorasInicio.contains(valorHora)) {
            horaActividadInicio.getItems().add(valorHora);
        }
        horaActividadInicio.setValue(valorHora);
        valorHora = DateTimeFormatter.ofPattern("HH:'00'").format(LocalTime.now().plusHours(2));
        if (!listaHorasFin.contains(valorHora)) {
            horaActividadFin.getItems().add(valorHora);
        }

        horaActividadFin.setValue(valorHora);
        sliderPrecio.setValue(30.0);
        numPlazas();
        mostrarPrecioTotal();
    }

    private void numPlazas() {
        plazas = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1, 1);
        numPlazasActividad.setValueFactory(plazas);
    }

    private void calendario() {
        calendarioActividadInicio.setValue(LocalDate.now());
        calendarioActividadFin.setValue(LocalDate.now().plusDays(1));
    }

    public void cargarValoresFecha() {
        String falloTitulo = "Error hora fin";
        String falloMensaje = "Si desea comenzar la atividad a partir de las 20:15, "
                + "deberá aumentar un día la fecha fin de la actividad";
        fechaSeleccionada = calendarioActividadInicio.getValue();
        horaInicio = LocalTime.parse(horaActividadInicio.getValue(), DateTimeFormatter.ofPattern("HH:mm"));
        fechaHoraInicio = LocalDateTime.of(fechaSeleccionada, horaInicio);
        fechaSeleccionada = calendarioActividadFin.getValue();
        horaFin = LocalTime.parse(horaActividadFin.getValue(), DateTimeFormatter.ofPattern("HH:mm"));
        fechaHoraFin = LocalDateTime.of(fechaSeleccionada, horaInicio);
    }

    public void calcularDias() {
        numDias = (int) ChronoUnit.DAYS.between(fechaHoraInicio, fechaHoraFin);
        numDiasActividad.setText(String.valueOf(numDias));
    }

    private void mostrarPrecioTotal() {
        DecimalFormat df = new DecimalFormat("0.##");
        if (numDias != 0) {
            precio = sliderPrecio.getValue() * numPlazasActividad.getValue() * numDias;

        } else {
            precio = sliderPrecio.getValue() * numPlazasActividad.getValue();
        }
        precio = (double) Math.round(precio * 100d) / 100d;
        labelPrecio.setText(df.format(precio) + " €");
    }

    private void filtroPuntuacion(String operacion, int puntuacion) {
        for (Actividad actividad : listaActividades) {
            if (actividad.getPuntuacion() == puntuacion) {
                if (operacion.equalsIgnoreCase("suma")) {
                    actividadesFiltradas.add(actividad);
                } else if (operacion.equalsIgnoreCase("resta")) {
                    actividadesFiltradas.remove(actividad);
                }
            }
        }
    }

///////////////////////////////////////////////////////////////////////////////
//////////////////////////    METODOS FXML     ////////////////////////////////
///////////////////////////////////////////////////////////////////////////////
    @FXML
    private void agregarActividad(ActionEvent event) {
        int idActividad = actividadSeleccionada.getIdActividad();
        int numeroPlazas;

        precio = sliderPrecio.getValue();
        precio = (double) Math.round(precio * 100d) / 100d;
        fechaSeleccionada = calendarioActividadInicio.getValue();
        horaInicio = LocalTime.parse(horaActividadInicio.getValue(), DateTimeFormatter.ofPattern("HH:mm"));
        fechaHoraInicio = LocalDateTime.of(fechaSeleccionada, horaInicio);

        fechaSeleccionada = calendarioActividadFin.getValue();
        horaInicio = LocalTime.parse(horaActividadFin.getValue(), DateTimeFormatter.ofPattern("HH:mm"));
        fechaHoraFin = LocalDateTime.of(fechaSeleccionada, horaInicio);
        numeroPlazas = numPlazasActividad.getValue();
        mostrarPrecioTotal();

        if (pack == null) {
            pack = homeController.crearNuevoPack();
            pack.setFechaCreacion(LocalDateTime.now());
            pack.setFechaValidez(LocalDateTime.now().plusDays(1));
        }

        detallePack = new DetallePack(idPack, idActividad, sliderPrecio.getValue(), fechaHoraInicio,
                fechaHoraFin, numeroPlazas, numDias);
        detallePack.setNombreActividad(actividadSeleccionada.getNombre());
        pack.setHomeController(homeController);
        pack.añadirDetallePack(detallePack);

    }

    @FXML
    private void actualizarPrecio(MouseEvent event) {
        precio = sliderPrecio.getValue();
        mostrarPrecioTotal();
    }

    @FXML
    private void controlFechaInicio(ActionEvent event) {
        String valorHora;
        ObservableList<String> listaHoras;

        cargarValoresFecha();

        listaHoras = horaActividadInicio.getItems();
        if (fechaHoraInicio.isBefore(LocalDateTime.now())) {
            calendarioActividadInicio.setValue(LocalDate.now());
            valorHora = DateTimeFormatter.ofPattern("HH:'00'").format(LocalTime.now().plusHours(1));

            if (!listaHoras.contains(valorHora)) {
                listaHoras.add(valorHora);
                horaActividadInicio.getItems().add(valorHora);
            }
            horaActividadInicio.setValue(valorHora);
        } else if (fechaHoraFin.isBefore(fechaHoraInicio)) {
            calendarioActividadFin.setValue(fechaHoraInicio.toLocalDate());
            valorHora = DateTimeFormatter.ofPattern("HH:'00'").format(horaInicio.plusHours(1));
            if (!listaHoras.contains(valorHora)) {
                listaHoras.add(valorHora);
                horaActividadFin.getItems().add(valorHora);
            }
            horaActividadFin.setValue(valorHora);
        } else if (calendarioActividadInicio.getValue().equals(calendarioActividadFin.getValue()) &&
                ( horaInicio.equals(horaFin) || horaFin.isBefore(horaInicio) )) {
            valorHora = DateTimeFormatter.ofPattern("HH:'00'").format(horaInicio.plusHours(1));
            if (!listaHoras.contains(valorHora)) {
                listaHoras.add(valorHora);
                horaActividadFin.getItems().add(valorHora);
            }
            horaActividadFin.setValue(valorHora);
        }

        calcularDias();
        mostrarPrecioTotal();
    }

    @FXML
    private void irAWeb(ActionEvent event) {
        String url = actividadSeleccionada.getUrl();
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (URISyntaxException ex) {
            homeController.crearAlarmaFallo("URL incorrecta", "El formato "
                    + "introducido para la URL no es válido. http://www.ejemplo.com");
        } catch (IOException ex) {
            homeController.crearAlarmaFallo("Error navegador", "No ha sido "
                    + "posible localizar el navegador web.");
        }
    }

    @FXML
    private void cancelarAñadirActividad(MouseEvent event) {
        infoActividad.setVisible(false);
        limpiarCamposActividad();
    }

    @FXML
    private void actualizarPlazas(MouseEvent event) {
        if (precio == null) {
            precio = sliderPrecio.getValue();
        }
        mostrarPrecioTotal();
    }

    @FXML
    private void aplicarFiltro(ActionEvent event) {
        if (puntuacionCinco.isSelected()) {
            filtroPuntuacion("suma", 5);
        } else {
            filtroPuntuacion("resta", 5);
        }
        if (puntuacionCuatro.isSelected()) {
            filtroPuntuacion("suma", 4);
        } else {
            filtroPuntuacion("resta", 4);
        }
        if (puntuacionTres.isSelected()) {
            filtroPuntuacion("suma", 3);
        } else {
            filtroPuntuacion("resta", 3);
        }
        if (puntuacionDos.isSelected()) {
            filtroPuntuacion("suma", 2);
        } else {
            filtroPuntuacion("resta", 2);
        }
        if (puntuacionUno.isSelected()) {
            filtroPuntuacion("suma", 1);
        } else {
            filtroPuntuacion("resta", 1);
        }

        if (!puntuacionCinco.isSelected() && !puntuacionCuatro.isSelected()
                && !puntuacionTres.isSelected() && !puntuacionDos.isSelected()
                && !puntuacionUno.isSelected()) {
            bloqueDondeDormir(listaActividades);
        } else {
            bloqueDondeDormir(actividadesFiltradas);
        }
    }

}
