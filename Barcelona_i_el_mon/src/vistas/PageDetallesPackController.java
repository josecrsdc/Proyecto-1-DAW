package vistas;

import datos.bda.DetallePackDAO;
import datos.bda.PackDAO;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.util.Duration;
import modelo.DetallePack;
import modelo.Pack;
import org.controlsfx.control.Notifications;

public class PageDetallesPackController implements Initializable {

    private DetallePackDAO detallePackDAO;
    private PackDAO packDAO;
    private ObservableList<DetallePack> observableDetallePack;
    private Pack pack;
    private PageHomeController pageHomeController;
    private Alert eliminar;
    private DetallePack detallePack;

    @FXML
    private TableView<DetallePack> tableCarritoPack;
    @FXML
    private Label labelPrecioTotal;
    @FXML
    private Label labelDuracionPack;
    @FXML
    private TableColumn<DetallePack, String> inicio;
    @FXML
    private TableColumn<DetallePack, String> fin;
    @FXML
    private TableColumn<DetallePack, LocalTime> duracion;
    @FXML
    private TableColumn<DetallePack, Integer> numPlazas;
    @FXML
    private TableColumn<DetallePack, Double> precioUnitario;
    @FXML
    private TableColumn<DetallePack, Double> precioActividad;
    @FXML
    private TableColumn<DetallePack, String> NombreActividad;
    @FXML
    private Button confirmarPack;
    @FXML
    private TextField nombrePack;
    @FXML
    private DatePicker validezPack;
    @FXML
    private DatePicker creacionPack;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

///////////////////////////////////////////////////////////////////////////////
///////////////////////    SETTER Y GETTER     ////////////////////////////////
///////////////////////////////////////////////////////////////////////////////
    public void setDetallePackDAO(DetallePackDAO detallePackDAO) {
        this.detallePackDAO = detallePackDAO;
    }

    public void setPack(Pack pack) {
        this.pack = pack;
    }

    public void setPageHomeController(PageHomeController pageHomeController) {
        this.pageHomeController = pageHomeController;
    }

    public PackDAO getPackDao() {
        return packDAO;
    }

    public void setPackDAO(PackDAO packDao) {
        this.packDAO = packDao;
    }

    private Period getPeriod(LocalDateTime dob, LocalDateTime now) {
        return Period.between(dob.toLocalDate(), now.toLocalDate());
    }

    private void actualizarNombre() {
        pack.setNombre(nombrePack.getText());
    }

///////////////////////////////////////////////////////////////////////////////
//////////////////////////    METODOS FXML     ////////////////////////////////
///////////////////////////////////////////////////////////////////////////////
    @FXML
    private void quitarActividad(MouseEvent event) {
        ButtonType botonSI;
        ButtonType botonNO;
        Optional<ButtonType> resultado;
        String alertTitulo = "Actividad eliminada";
        String alertMensaje = "La actividad ha sido eliminada del pack actual.";

        detallePack = tableCarritoPack.getSelectionModel().getSelectedItem();
        if (event.getClickCount() == 2 && detallePack != null) {

            eliminar = new Alert(Alert.AlertType.CONFIRMATION);
            eliminar.setTitle("ELIMINAR ACTIVIDAD");
            eliminar.setHeaderText("¿Deseas eliminar esta actividad?");
            botonSI = new ButtonType("SI");
            botonNO = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);
            eliminar.getButtonTypes().setAll(botonSI, botonNO);
            resultado = eliminar.showAndWait();

            if (resultado.get() == botonSI) {
                pack.descontarPrecio(detallePack.getPrecioTotalActividad());
                pack.eliminarDetallePack(detallePack);
                DetallePack.setContador(DetallePack.getContador() - 1);
                alertaInformacion(alertTitulo, alertMensaje);
                if (pack.getListaDetallePack().isEmpty()) {
                    pageHomeController.setCarritoCantidadString(null);
                    pack = null;
                    DetallePack.setContador(1);
                    pageHomeController.setPack(pack);
                    pageHomeController.home(event);
                } else {
                    cargarDetallesPack();
                }

            }
        }
    }

    @FXML
    private void enviarPackBD(MouseEvent event) {
        Optional<ButtonType> eleccion;
        Path ticket = null;
        boolean valido;

        eleccion = crearAlarmaConfirmacion("Confirmar Pack", "¿Desea confirmar el pack?");
        if (eleccion.get() == ButtonType.OK) {

            actualizarNombre();
            eleccion = crearAlarmaConfirmacion("Imprimir ticket", "¿Desea imprimir un ticket con sus datos?");
            if (eleccion.get() == ButtonType.OK) {
                ticket = eligeDirectorio();
                if (ticket != null) {
                    crearAlarmaExito("Su ticket se ha generado con éxito", "Lo tiene en: " + ticket.toString());
                } else {
                    pageHomeController.crearAlarmaFallo("No se ha podido generar su ticket", "Algo ha debido de salir mal");
                }
            }
            valido = false;
            //        1. INSERTAR PACK
            try {
                valido = packDAO.insertarBD(pack);
            } catch (SQLException ex) {
                pageHomeController.crearAlarmaFallo("Error al insertar",
                        "El nuevo pack " + pack.getNombre() + " no se ha guardado"
                        + " en la base de datos.");
            }

            //        2. INSERTAR DETALLEPACK
            if (valido) {
                try {
                    valido = detallePackDAO.insertarBD(pack);
                    crearAlarmaExito("Pack " + pack.getNombre(), " Su pack "
                            + pack.getNombre() + " ha sido completado con éxito");
                } catch (SQLException ex) {
                    pageHomeController.crearAlarmaFallo("Error al insertar",
                            "No se han podido insertar las actividades seleccionadas "
                            + "en el pack " + pack.getNombre());
                }
            }
//        3. RESETEAR PACK Y NUMERO DE LINEA CONTROLAR CON BOOLEAN
            if (valido) {
                pack = null;
                DetallePack.setContador(1);
//        4. ENVIAR A HOME 
                pageHomeController.setPack(pack);
                pageHomeController.home(event);
            } else {
                pageHomeController.crearAlarmaFallo("Error", "El pack " + pack.getNombre() + " no se ha podido completar");
            }

        } else {
            pageHomeController.crearAlarmaFallo("Pack no confirmado", "Puede continuar con el pack");
        }

    }

    @FXML
    private void actualizarFechaValidez(ActionEvent event) {

        if (pack != null) {
            if (creacionPack.getValue().isBefore(ChronoLocalDate.from(LocalDate.now()))) {
                creacionPack.setValue(LocalDate.now());
            }
            if (validezPack == null) {
                validezPack.setValue(creacionPack.getValue().plusDays(1));
            } else if (validezPack.getValue().isBefore(creacionPack.getValue())) {
                validezPack.setValue(creacionPack.getValue().plusDays(1));
            }
            pack.setFechaCreacion(LocalDateTime.of(creacionPack.getValue(), LocalTime.now()));
            pack.setFechaValidez(LocalDateTime.of(validezPack.getValue(), LocalTime.now()));

            labelDuracionPack.setText(calcularDuracionActiviadad());
        } else {
            if (creacionPack.getValue().isAfter(validezPack.getValue()) || validezPack.getValue() == null) {
                validezPack.setValue(creacionPack.getValue().plusDays(1));
            }
        }

    }

    @FXML
    private void actualizarLabel(ActionEvent event) {
        if (pack != null) {
            if (creacionPack.getValue().isAfter(validezPack.getValue()) || validezPack.getValue() == null) {
                validezPack.setValue(creacionPack.getValue().plusDays(1));
            }
            pack.setFechaCreacion(LocalDateTime.of(creacionPack.getValue(), LocalTime.now()));
            pack.setFechaValidez(LocalDateTime.of(validezPack.getValue(), LocalTime.now()));

            labelDuracionPack.setText(calcularDuracionActiviadad());
        }
    }

///////////////////////////////////////////////////////////////////////////////
////////////////////////////    METODOS      //////////////////////////////////
///////////////////////////////////////////////////////////////////////////////
    
    public void alertaInformacion(String titulo, String nombre) {
        Notifications.create()
                .owner(confirmarPack)
                .title(titulo)
                .text(nombre)
                .position(Pos.CENTER)
                .hideAfter(Duration.seconds(3.0))
                .showInformation();

    }
    public void cargarDetallesPack() {

        observableDetallePack = FXCollections.observableArrayList(pack.getListaDetallePack());
        tableCarritoPack.setItems(observableDetallePack);

        if (pack.getFechaCreacion() != null && pack.getFechaValidez() != null) {
            creacionPack.setValue(pack.getFechaCreacion().toLocalDate());
            validezPack.setValue(pack.getFechaValidez().toLocalDate());
            labelDuracionPack.setText(calcularDuracionActiviadad());
            nombrePack.setText(pack.getNombre());
            labelPrecioTotal.setText(String.valueOf(pack.getPrecioTotal() + "€"));
        }
        for (DetallePack detallePack : pack.getListaDetallePack()) {
            NombreActividad.setCellValueFactory(new PropertyValueFactory<>("nombreActividad"));
            precioUnitario.setCellValueFactory(new PropertyValueFactory<>("precio"));
            inicio.setCellValueFactory(new PropertyValueFactory<>("fechaHoraInicioFormato"));
            fin.setCellValueFactory(new PropertyValueFactory<>("fechaHoraFinFormato"));
            duracion.setCellValueFactory(new PropertyValueFactory<>("duracion"));
            numPlazas.setCellValueFactory(new PropertyValueFactory<>("numeroPlazas"));
            precioActividad.setCellValueFactory(new PropertyValueFactory<>("precioTotalActividad"));
        }

        // Inicializamos las fechas de los datepicker
        pageHomeController.inicializarDatePickers(creacionPack, validezPack);

    }

    public Optional<ButtonType> crearAlarmaConfirmacion(String header, String contenido) {
        Alert dialogoAlerta = new Alert(Alert.AlertType.CONFIRMATION);

        dialogoAlerta.setTitle("Confirmación");
        dialogoAlerta.setHeaderText(header);
        dialogoAlerta.setContentText(contenido);

        return dialogoAlerta.showAndWait();

    }

    public void crearAlarmaExito(String titulo, String contenido) {
        Notifications.create()
                .owner(confirmarPack)
                .title(titulo)
                .text(contenido)
                .position(Pos.CENTER)
                .hideAfter(Duration.seconds(3.0))
                .showInformation();
    }

    private String calcularDuracionActiviadad() {
        String respuesta = "";
        int anyosActividad;
        int mesesActividad;
        int diasActividad;
        Period period;

        period = getPeriod(pack.getFechaCreacion(), pack.getFechaValidez());
        anyosActividad = period.getYears();
        mesesActividad = period.getMonths();
        diasActividad = period.getDays();
        if (anyosActividad != 0) {
            if (anyosActividad == 1) {
                respuesta = anyosActividad + " Año ";
            } else {
                respuesta = anyosActividad + " Años ";
            }
        }
        if (mesesActividad != 0) {
            if (mesesActividad == 1) {
                respuesta = respuesta + mesesActividad + " Mes ";
            } else {
                respuesta = respuesta + mesesActividad + " Meses ";
            }
        }

        if (diasActividad == 1) {
            respuesta = respuesta + diasActividad + " Día ";
        } else {
            respuesta = respuesta + diasActividad + " Días ";
        }

        return respuesta;
    }

    public Path eligeDirectorio() {
        Path rutaElegida;
        Path archivoCreado = null;
        //CREAMOS EL OBJETO FILECHOOSER
        DirectoryChooser directorioElegido = new DirectoryChooser();

        // PODEMOS INDICAR QUE SE SITUE EN UN DIRECTORIO CONCRETO
        directorioElegido.setInitialDirectory(new File(Paths.get("tickets").toString()));

        //ABRIMOS VENTANA DIRECTORYCHOOSER Y RECUPERAMOS EL DIRECTORIO SELECCIONADO
        File rutaDirectorio = directorioElegido.showDialog(null);

        //REVISAMOS SI EL USUARIO HA SELECCIONADO UN FICHERO O NO
        if (rutaDirectorio != null) {
            rutaElegida = rutaDirectorio.toPath();
            archivoCreado = generarTicket(rutaElegida);
        }

        return archivoCreado;
    }

    public Path generarTicket(Path ruta) {

        int cont = 1;
        DecimalFormat df = new DecimalFormat("0.00");
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm:SS");

        Path archivo = Paths.get(ruta + "\\Pack " + pack.getIdPack() + "_" + pack.getNombre() + ".txt");

        if (archivo != null) {

            try (BufferedWriter out = Files.newBufferedWriter(archivo,
                    Charset.defaultCharset(),
                    StandardOpenOption.CREATE)) {

                out.write("					    |            AGENCIA VIAJES            |\n"
                        + "					    |          BARCELONA I EL MÓN          |\n"
                        + "					    |                                      |\n"
                        + "					    |         C/Falsa Nº31 BAJO izq.       |\n"
                        + "					    |         BARCELONA - BARCELONA        |\n"
                        + "					    |                                      |");
                out.newLine();
                out.write("					    ****************************************\n"
                        + "					    ****************************************\n\n");
                out.newLine();
                out.write("                                  FECHA: "
                        + pack.getFechaCreacion().format(formatoFecha)
                        + "                         HORA: "
                        + pack.getFechaCreacion().format(formatoHora));
                out.newLine();
                out.write("---------------------------------------------------------"
                        + "---------------------------------------------------------"
                        + "----------------\n");
                out.write("---------------------------------------------------------"
                        + "---------------------------------------------------------"
                        + "----------------\n");
                out.newLine();
                for (DetallePack p : pack.getListaDetallePack()) {

                    out.newLine();
                    out.write("        Actividad nº" + cont + " Nombre: " + p.getNombreActividad()
                            + "\n                 Precio: " + df.format(p.getPrecio()) + " €"
                            + "  Plazas: " + p.getNumeroPlazas()
                            + "  Fecha inicio: " + p.getFechaHoraInicio().format(formatoFecha)
                            + "  Fecha fin: " + p.getFechaHoraFin().format(formatoFecha)
                            + "  Precio total: " + df.format(p.getPrecioTotalActividad()) + "€\n");
                    cont++;
                }
                out.newLine();
                out.newLine();
                out.newLine();
                out.write("-----------------------------------------------------"
                        + "-----------------------------------------------------"
                        + "------------------------\n");
                out.write("-----------------------------------------------------"
                        + "-----------------------------------------------------"
                        + "------------------------\n");
                out.newLine();

                out.write("     						"
                        + "			   El precio total del pack es "
                        + "de " + df.format(pack.getPrecioTotal()) + "€");

            } catch (IOException e) {
                pageHomeController.crearAlarmaFallo("Error ticket", "No se ha podido generar el ticket.");
            }

        } else {
            pageHomeController.crearAlarmaFallo("Error ticket", "No se ha podido generar el ticket.");
        }

        return archivo;

    }

}
