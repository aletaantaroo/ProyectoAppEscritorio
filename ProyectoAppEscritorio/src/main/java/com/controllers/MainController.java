package com.controllers;

import com.modelos.Accion;
import com.modelos.Contexto;
import com.modelos.Equipo;
import com.modelos.Fichaje;
import com.modelos.Jugador;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import static java.lang.System.exit;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationMessage;
import org.controlsfx.validation.ValidationResult;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import org.controlsfx.validation.decoration.GraphicValidationDecoration;


public class MainController implements Initializable{
    
    private Connection conexion;
    private Statement st;
    private ResultSet rs;
    private final ObservableList<Jugador> listaJugadores = FXCollections.observableArrayList();
    private final ObservableList<Equipo> listaEquipos = FXCollections.observableArrayList();
    private final ObservableList<Fichaje> listaFichajes = FXCollections.observableArrayList();
    private final String placeholderImg = "./images/placeholder-img.png";
    private final String salirImg = "./images/salir.png";
    private final String masImg = "./images/mas.png";
    private final String editarImg = "./images/editar.png";
    private final String eliminarImg = "./images/eliminar.png";
    private final String filtrarImg = "./images/filtrar.png";
    private final String ayudaImg = "./images/ayuda.png";
    private final String lupaImg = "./images/lupa.png";
    private final String borrarImg = "./images/borrar.png";
    private Contexto contexto;
    private Accion accion;
    private String imagenBase64;
    private Jugador jugadorBuscado;
    private Equipo equipoBuscado;
    private Fichaje fichajeBuscado;
    private final boolean edicion = false;
    private final String css = getClass().getResource("/style.css").toString();
    List<ValidationSupport> validadores= new ArrayList<>();
    @FXML
    private BorderPane rootPane;
    @FXML
    private Button btnAceptar;
    @FXML
    private Button btnAyuda;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnFiltrar;
    @FXML
    private Button btnInsertar;
    @FXML
    private Button btnSalir;
    @FXML
    private Button btnBorrarEquipo;
    @FXML
    private Button btnBorrarJugador;
    @FXML
    private Button btnBuscarEquipo;
    @FXML
    private Button btnBuscarJugador;
    @FXML
    private ImageView imageBtnAyuda;
    @FXML
    private ImageView imageBtnEditar;
    @FXML
    private ImageView imageBtnEliminar;
    @FXML
    private ImageView imageBtnFiltrar;
    @FXML
    private ImageView imageBtnInsertar;
    @FXML
    private ImageView imageBtnSalir;
    @FXML
    private ImageView imageViewEscudo;
    @FXML
    private ImageView imageBtnBorrarEquipo;
    @FXML
    private ImageView imageBtnBorrarJugador;
    @FXML
    private ImageView imageBtnBuscarEquipo;
    @FXML
    private ImageView imageBtnBuscarJugadores;
    @FXML
    private TabPane MainTab;
    @FXML
    private Tab tabEquipos;
    @FXML
    private Tab tabFichaje;
    @FXML
    private Tab tabHistorial;
    @FXML
    private Tab tabJugadores;
    @FXML
    private TableView<Equipo> tableViewEquipos;
    @FXML
    private TableColumn<Equipo, Integer> idTeamColumn;
    @FXML
    private TableColumn<Equipo, String> nameTeamColumn;
    @FXML
    private TableColumn<Equipo, String> regionTeamColumn;
    @FXML
    private TableColumn<Equipo, VBox> escudoTeamColumn;
    @FXML
    private TableColumn<Equipo, String> directorTeamColumn;
    @FXML
    private TableColumn<Equipo, String> categoryTeamColumn;
    @FXML
    private TableColumn<Equipo, Integer> signBySeasonColumn;
    @FXML
    private TableView<Fichaje> tableViewHistorial;
    @FXML
    private TableColumn<Fichaje, Date> fechaFinColumn;
    @FXML
    private TableColumn<Fichaje, Date> fechaInicioColumn;
    @FXML
    private TableColumn<Fichaje, String> jugadorHistorialColumn;
    @FXML
    private TableColumn<Fichaje, String> equipoHistorialColumn;
    @FXML
    private TableView<Jugador> tableViewJugadores;
    @FXML
    private TableColumn<Jugador, String> nifColumn;
    @FXML
    private TableColumn<Jugador, String> nameColumn;    
    @FXML
    private TableColumn<Jugador, String> apellidosColumn;
    @FXML
    private TableColumn<Jugador, Integer> ageColumn;
    @FXML
    private TableColumn<Jugador, String> categoryColumn;
    @FXML
    private TableColumn<Jugador, VBox> alertFichajeColumn;
    @FXML
    private TextField txtCategoriaJugador;
    @FXML
    private TextField txtCategoriaEquipo;
    @FXML
    private TextField txtEdadJugador;
    @FXML
    private TextField txtLocalidadEquipo;
    @FXML
    private TextField txtNameDirector;
    @FXML
    private TextField txtNameEquipo;
    @FXML
    private TextField txtNombreJugador;
    @FXML
    private TextField txtNifJugador;
    @FXML
    private TextField txtApellidoJugador;
    @FXML
    private DatePicker dateFecha;
    @FXML
    private DatePicker dateFechaFin;
    
    @FXML
    void selectionFichaje(Event event) {
        contexto = Contexto.FICHAJE;
        habilitarBotones(contexto);
    }
    
    @FXML
    void selectionJugador(Event event) {
        contexto = Contexto.JUGADOR;
        habilitarBotones(contexto);
    }
    
    @FXML
    void selectionEquipo(Event event) {
        contexto = Contexto.EQUIPO;
        habilitarBotones(contexto);
    }

    @FXML
    void selectionHistorial(Event event) {
        contexto = Contexto.HISTORIAL;
        habilitarBotones(contexto);
    }
    
    @FXML
    void cerrarApp(ActionEvent event) {
        contexto = Contexto.SALIR;
        stagePregunta("SALIR", "¿Desea salir del programa?", event);
    }
    
    @FXML
    void insertarDatos(ActionEvent event) {
        accion = Accion.INSERTAR;
        stageInsertarEditar(event);
        
    }
    
    @FXML
    void editarDatos(ActionEvent event) {
        accion = Accion.EDITAR;
        if(selectedItem(contexto) != null){
            stageInsertarEditar(event);
        } else {
            resultAlert(AlertType.ERROR, 
                    "EDITAR "+contexto, 
                    "No has seleccionado ningún "+contexto.toString().toLowerCase(),
                    "Por favor selecciona que "+contexto.toString().toLowerCase()+" quieres editar e intentalo de nuevo"
            );
        }
    }

    @FXML
    void eliminarDatos(ActionEvent event) {
        accion = Accion.ELIMINAR;
        
        if(selectedItem(contexto) != null){
            stagePregunta("ELIMINAR "+ contexto.toString() , "¿Desea eliminar los datos de "+selectedItem(contexto).toString()+"?", event);
        } else {
            resultAlert(AlertType.ERROR, 
                    "ELIMINAR "+contexto, 
                    "No has seleccionado ningún "+contexto.toString().toLowerCase(),
                    "Por favor selecciona que "+contexto.toString().toLowerCase()+" quieres eliminar e intentalo de nuevo"
            );
        }
    }
    
    @FXML
    void aceptarFichaje(ActionEvent event) {
        if(edicion){
            accion = Accion.INSERTAR;
            stagePregunta("REALIZAR FICHAJE", "¿Desea realizar el fichaje?", event);
        } else{
            accion = Accion.EDITAR;
            stagePregunta("RENOVAR FICHAJE", "¿Desea renovar el fichaje?", event);
                
        }
    }
    
    @FXML
    void filtrarDatos(ActionEvent event) {
       
    }
     
    @FXML
    void mostrarAyuda(ActionEvent event) {

    }
    
    @FXML
    void renovarFichaje(MouseEvent event) {
        jugadorBuscado = tableViewJugadores.getSelectionModel().getSelectedItem();
        if(jugadorBuscado.isFichado()){
            for (Fichaje f : getListaHistorial()) {
                if (f.getNifJugador().equals(jugadorBuscado.getNif())) {
                    fichajeBuscado = f;
                    break;
                }
            }
            for (Equipo e : getListaEquipos()) {
                if (e.getIdEquipo() == fichajeBuscado.getIdEquipo()) {
                    equipoBuscado = e;
                    break;
                }
            }
            Stage stage = new Stage();
            Label lbl1 = new Label("¿Desea renovar el fichaje de "+jugadorBuscado.toString()+" ?");

            Button btnAceptar = new Button("Aceptar");
            Button btnCancelar = new Button("Cancelar");

            btnAceptar.setOnAction(e -> {
                btnBuscarEquipo.setDisable(true);
                btnBuscarJugador.setDisable(true);
                btnBorrarEquipo.setDisable(true);
                btnBorrarJugador.setDisable(true);

                txtNombreJugador.setText(jugadorBuscado.getNombre());
                txtApellidoJugador.setText(jugadorBuscado.getApellidos());
                txtNifJugador.setText(jugadorBuscado.getNif());
                txtEdadJugador.setText(String.valueOf(jugadorBuscado.getEdad()));
                txtCategoriaJugador.setText(jugadorBuscado.getCategoria());

                txtNameEquipo.setText(equipoBuscado.getNombre());
                txtNameDirector.setText(equipoBuscado.getDirector());
                txtLocalidadEquipo.setText(equipoBuscado.getLocalidad());
                txtCategoriaEquipo.setText(equipoBuscado.getCategoria());
                imageViewEscudo.setImage(base64ToImage(equipoBuscado.getUrlEscudo()));

                if(fichajeBuscado.getFechaInicio() != null){
                    dateFecha.setValue(fichajeBuscado.getFechaInicio());
                }

                MainTab.getSelectionModel().select(tabFichaje);
                stage.close(); 
            });

            btnCancelar.setOnAction(e -> { stage.close(); });

            HBox hboxButtons = new HBox(10, btnCancelar, btnAceptar);
            hboxButtons.setAlignment(Pos.BOTTOM_RIGHT);
            hboxButtons.setPadding(new Insets(10));

            VBox vbox = new VBox(15, lbl1, hboxButtons);
            vbox.setAlignment(Pos.CENTER);
            vbox.setPadding(new Insets(15));

            Scene scene = new Scene(vbox, 350, 100 );
            scene.getStylesheets().add(css);
            stage.setScene(scene);
            stage.setTitle("Renovar fichaje");
            stage.initModality(Modality.APPLICATION_MODAL); 
            stage.show();
        }
    }
    
    @FXML
    void buscarEquipos(ActionEvent event) {
        stageAutocompletado(event, Contexto.EQUIPO);
    }

    @FXML
    void buscarJugadores(ActionEvent event) {
        stageAutocompletado(event, Contexto.JUGADOR);
    }
    
    @FXML
    void borrarEquipo(ActionEvent event) {
        TextField[] txtsEquipo = {
            txtNameEquipo, 
            txtNameDirector, 
            txtLocalidadEquipo, 
            txtCategoriaEquipo
        };
        
        for (TextField txt : txtsEquipo) {
            txt.clear();
        }
        imageViewEscudo.setImage(new Image(getClass().getClassLoader().getResourceAsStream(placeholderImg)));
    }

    @FXML
    void borrarJugador(ActionEvent event) {
        TextField[] txtsJugador = {
            txtNombreJugador, 
            txtApellidoJugador, 
            txtNifJugador, 
            txtEdadJugador, 
            txtCategoriaJugador
        };
        
        for (TextField txt : txtsJugador) {
            txt.clear();
        }          
    }
    
    private void stagePregunta(String titulo, String mensaje, Event event) {
        Stage stage = new Stage();
        Label lbl1 = new Label(mensaje);

        Button btnAceptar = new Button("Aceptar");
        Button btnCancelar = new Button("Cancelar");

        btnAceptar.setOnAction(e -> {

            switch (contexto){

                case JUGADOR -> {
                        
                    if(accion == Accion.ELIMINAR){
                        Jugador jugador = tableViewJugadores.getSelectionModel().getSelectedItem();
                        String query = "DELETE FROM JUGADOR WHERE nif=?";
                        try {
                            PreparedStatement preparedStatement = this.conexion.prepareStatement(query);
                            preparedStatement.setString(1, jugador.getNif());
                            preparedStatement.executeUpdate();

                            resultAlert(AlertType.INFORMATION, "", "JUGADOR ELIMINADO CORRECTAMENTE", "");
                            stage.close();
                        } catch (SQLException ex) {
                            System.out.println("Excepción: "+ex.getMessage());
                            resultAlert(AlertType.ERROR, "", "ERROR AL ELIMINAR EL JUGADOR", "Ha ocurrido un error al eliminar al jugador, inténtelo de nuevo");
                        }
                        mostrarJugadores();
                    } 
                }
                case EQUIPO -> {
                    
                    if(accion == Accion.ELIMINAR){
                        Equipo equipo = tableViewEquipos.getSelectionModel().getSelectedItem();
                        String query = "DELETE FROM EQUIPO WHERE id_equipo=?";
                        try {
                            PreparedStatement preparedStatement = this.conexion.prepareStatement(query);
                            preparedStatement.setInt(1, equipo.getIdEquipo());
                            preparedStatement.executeUpdate();
                            mostrarJugadores();

                            resultAlert(AlertType.INFORMATION, "", "EQUIPO ELIMINADO CORRECTAMENTE", "");
                            stage.close();
                        } catch (SQLException ex) {
                            System.out.println("Excepción: "+ex.getMessage());
                            resultAlert(AlertType.ERROR, "", "ERROR AL ELIMINAR EL EQUIPO", "");
                        }
                        mostrarEquipos();
                    }
                }
                case FICHAJE -> {
                    switch (accion) {
                        case INSERTAR -> {
                            if(jugadorBuscado.getCategoria().equals(equipoBuscado.getCategoria()) && !jugadorBuscado.isFichado()){
                                String queryFichaje = "INSERT INTO FICHA (nif_jugador, id_equipo, fecha_fichaje, fecha_fin_fichaje) VALUES (?, ?, ?, ?)";
                                String queryActualizarJugador = "UPDATE JUGADOR SET fichado = TRUE WHERE nif = ?";

                                try {
                                    PreparedStatement preparedStatementFichaje = this.conexion.prepareStatement(queryFichaje);
                                    preparedStatementFichaje.setString(1, txtNifJugador.getText());
                                    preparedStatementFichaje.setInt(2, equipoBuscado.getIdEquipo());
                                    preparedStatementFichaje.setDate(3, java.sql.Date.valueOf(dateFecha.getValue()));
                                    preparedStatementFichaje.setDate(4, java.sql.Date.valueOf(dateFechaFin.getValue()));
                                    int rowsFichaje = preparedStatementFichaje.executeUpdate();
                                    System.out.println(rowsFichaje);

                                    if (rowsFichaje > 0) {
                                        PreparedStatement preparedStatementActualizarJugador = this.conexion.prepareStatement(queryActualizarJugador);
                                        preparedStatementActualizarJugador.setString(1, txtNifJugador.getText());
                                        int rowsJugador = preparedStatementActualizarJugador.executeUpdate();

                                        if (rowsJugador > 0) {
                                            resultAlert(AlertType.INFORMATION, "", "FICHAJE REALIZADO CORRECTAMENTE", "");
                                            stage.close();
                                        } else {
                                            resultAlert(AlertType.WARNING, "", "El fichaje se realizó, pero no se pudo actualizar el jugador.", "");
                                        }
                                    }
                                } catch (SQLException ex) {
                                    System.out.println("Excepción: " + ex.getMessage());
                                    resultAlert(AlertType.ERROR, "ERROR", "ERROR AL REALIZAR EL FICHAJE", "Ha habido un error en la base de datos, por favor intentelo de nuevo");
                                }
                            } else {
                                resultAlert(
                                        AlertType.ERROR,
                                        "ERROR", 
                                        "DATOS INCORRECTOS",
                                        "No se puede fichar a un jugador \n cuando su categoría no coincide con la de su equipo o \n si el jugador ya esta fichado actualmente por otro equipo"
                                );
                            }
                            mostrarFichajesHistorial();
                        }
                        case EDITAR -> {
                            String query = "UPDATE FICHA SET fecha_fin_fichaje=? WHERE nif_jugador = ? AND id_equipo = ?;";
                            try {
                                PreparedStatement preparedStatement = this.conexion.prepareStatement(query);
                                preparedStatement.setDate(1, java.sql.Date.valueOf(dateFechaFin.getValue()));
                                preparedStatement.setString(2, jugadorBuscado.getNif());
                                preparedStatement.setInt(3, equipoBuscado.getIdEquipo());
                                preparedStatement.executeUpdate();

                                resultAlert(AlertType.INFORMATION, "", "FICHAJE RENOVADO CORRECTAMENTE", "");
                                borrarEquipo(e);
                                borrarJugador(e);
                                dateFecha.setValue(null);
                                dateFechaFin.setValue(null);
                                btnBuscarEquipo.setDisable(false);
                                btnBuscarJugador.setDisable(false);
                                btnBorrarEquipo.setDisable(false);
                                btnBorrarJugador.setDisable(false);
                                stage.close();
                            } catch (SQLException ex) {
                                System.out.println("Excepción: "+ex.getMessage());
                                resultAlert(AlertType.ERROR, "", "ERROR AL RENOVAR EL FICHAJE", "Ha ocurrido un error al renovar el fichaje, inténtelo de nuevo");
                            }
                        }
                    }
                    mostrarFichajesHistorial();
                } case HISTORIAL -> {
                    if(accion == Accion.ELIMINAR){
                        Fichaje fichaje = tableViewHistorial.getSelectionModel().getSelectedItem();
                        String query = "DELETE FROM FICHA WHERE nif_jugador = ? AND id_equipo = ?;";
                        try {
                            PreparedStatement preparedStatement = this.conexion.prepareStatement(query);
                            preparedStatement.setString(1, fichaje.getNifJugador());
                            preparedStatement.setInt(2, fichaje.getIdEquipo());
                            preparedStatement.executeUpdate();

                            resultAlert(AlertType.INFORMATION, "", "FICHAJE ELIMINADO CORRECTAMENTE", "");
                            stage.close();
                        } catch (SQLException ex) {
                            System.out.println("Excepción: "+ex.getMessage());
                            resultAlert(AlertType.ERROR, "", "ERROR AL ELIMINAR EL FICHAJE", "Ha ocurrido un error al eliminar el fichaje, inténtelo de nuevo");
                        }
                    }
                }

                case SALIR -> {
                    Stage stageApp = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stageApp.close();  
                }  
            }
            stage.close(); 
        });

        btnCancelar.setOnAction(e -> {            
            stage.close();
        });

        HBox hboxButtons = new HBox(10, btnCancelar, btnAceptar);
        hboxButtons.setAlignment(Pos.BOTTOM_RIGHT);
        hboxButtons.setPadding(new Insets(10));

        VBox vbox = new VBox(15, lbl1, hboxButtons);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(15));

        Scene scene = new Scene(vbox, 350, 100 );
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.setTitle(titulo);
        stage.initModality(Modality.APPLICATION_MODAL); 
        stage.show();
    }
    
    private void stageRenovacion(){
        Stage stage = new Stage();
        Label[] labels = new Label[2];
        TextField[] txtArray = new TextField[2];
        HBox[] hboxArray = new HBox[2];

        for (int i = 0; i < 2; i++) {
            labels[i] = new Label();
            labels[i].setPrefWidth(60);

            txtArray[i] = new TextField();
            txtArray[i].setPrefWidth(30);
            hboxArray[i] = new HBox(10, labels[i], txtArray[i]);
        }
        labels[0].setText("Fecha: ");
        labels[1].setText("Fecha fin: ");
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));
        vbox.getChildren().addAll(hboxArray);
        Scene scene = new Scene(vbox, 400, 300);
        scene.getStylesheets().add(css);
        stage.setTitle("Renovar fichaje");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream(editarImg)));
        stage.show();
    }
    
    
    private void stageAutocompletado(ActionEvent event, Contexto contexto) {
        Stage stage = new Stage();

        ObservableList<?> lista = (contexto == Contexto.JUGADOR) ? getListaJugadores() : getListaEquipos();
        FilteredList<?> filteredItems = new FilteredList<>(lista, p -> true);

        TextField filterField = new TextField();
        filterField.setPromptText("Introduce texto para filtrar...");
        ListView<?> listView = new ListView<>(filteredItems);

        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredItems.setPredicate(item -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String nuevoValor = newValue.toLowerCase();
                if (contexto == Contexto.JUGADOR) {
                    Jugador jugador = (Jugador) item;
                    return jugador.getNombre().toLowerCase().contains(nuevoValor)
                            || jugador.getApellidos().toLowerCase().contains(nuevoValor)
                            || jugador.getNif().toLowerCase().contains(nuevoValor);
                } else if (contexto == Contexto.EQUIPO) {
                    Equipo equipo = (Equipo) item;
                    return equipo.getNombre().toLowerCase().contains(nuevoValor)
                            || equipo.getCategoria().toLowerCase().contains(nuevoValor);
                }
                return false;
            });
        });

        TextField seleccionado = new TextField();
        seleccionado.setDisable(true);
        seleccionado.setPrefWidth(230);

        Button btnAceptar = new Button("Aceptar");
        Button btnCancelar = new Button("Cancelar");
        HBox hboxButtons = new HBox(10, btnCancelar, btnAceptar);
        hboxButtons.setAlignment(Pos.BOTTOM_RIGHT);

        HBox hbox = new HBox(10, seleccionado, hboxButtons);

        listView.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.PRIMARY && e.getClickCount() == 2) {
                Object seleccionadoItem = listView.getSelectionModel().getSelectedItem();
                if (seleccionadoItem != null) {
                    seleccionado.setText(seleccionadoItem.toString());
                }
            }
        });

        btnAceptar.setOnAction(e -> {
            Object seleccionadoItem = listView.getSelectionModel().getSelectedItem();
            if (seleccionadoItem != null) {
                if (contexto == Contexto.JUGADOR) {
                    Jugador jugador = (Jugador) seleccionadoItem;
                    jugadorBuscado = jugador;
                    txtNombreJugador.setText(jugador.getNombre());
                    txtApellidoJugador.setText(jugador.getApellidos());
                    txtNifJugador.setText(jugador.getNif());
                    txtEdadJugador.setText(String.valueOf(jugador.getEdad()));
                    txtCategoriaJugador.setText(jugador.getCategoria());
                } else if (contexto == Contexto.EQUIPO) {
                    
                    Equipo equipo = (Equipo) seleccionadoItem;
                    equipoBuscado = equipo;
                    txtNameEquipo.setText(equipo.getNombre());
                    txtNameDirector.setText(equipo.getDirector());
                    txtLocalidadEquipo.setText(equipo.getLocalidad());
                    txtCategoriaEquipo.setText(equipo.getCategoria());
                    imageViewEscudo.setImage(base64ToImage(equipo.getUrlEscudo()));
                }
                stage.close();
            } else {
                resultAlert(
                        AlertType.ERROR,
                        (contexto == Contexto.JUGADOR ? "Buscador de Jugadores" : "Buscador de Equipos"),
                        "No has seleccionado ningún " + (contexto == Contexto.JUGADOR ? "jugador" : "equipo"),
                        "Inténtalo de nuevo. Si el problema persiste, contacte con el administrador"
                );
            }
        });

        btnCancelar.setOnAction(e -> stage.close());

        VBox vbox = new VBox(10, filterField, listView, hbox);
        vbox.setPadding(new Insets(10));
        Scene scene = new Scene(vbox, 400, 300);
        scene.getStylesheets().add(css);       
        stage.setTitle((contexto == Contexto.JUGADOR ? "Buscador de Jugadores" : "Buscador de Equipos"));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.show();
    }
    
    
    private void stageInsertarEditar(ActionEvent event) {
        Stage stageInsertar = new Stage();

        VBox vbox = new VBox(10);

        Label[] labels = new Label[5];
        TextField[] txtArray = new TextField[5];
        HBox[] hboxArray = new HBox[5];

        for (int i = 0; i < 5; i++) {
            labels[i] = new Label();
            labels[i].setPrefWidth(60);

            txtArray[i] = new TextField();
            if (i == 3) {
                txtArray[i].setPrefWidth(30);
            }

            hboxArray[i] = new HBox(10, labels[i], txtArray[i]);
        }

        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("src"));
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Imagen jpg", "*.jpg"),
            new FileChooser.ExtensionFilter("Imagen png", "*.png")
        );
        Button btnFileChooser = new Button("Selecciona fichero");
        btnFileChooser.setTooltip(new Tooltip("Cargar imagen"));
        btnFileChooser.setOnAction(e -> {
            imagenBase64 = imagenToBase64(fileChooser);
        });

        Button btnAceptar = new Button("Aceptar");
        Button btnCancelar = new Button("Cancelar");

        vbox.getChildren().addAll(hboxArray);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(15));

        HBox hboxButtons = new HBox(10, btnCancelar, btnAceptar);
        hboxButtons.setAlignment(Pos.BOTTOM_RIGHT);
        hboxButtons.setPadding(new Insets(10));

        VBox vboxScene = new VBox(15, vbox, hboxButtons);

        Jugador jugador = tableViewJugadores.getSelectionModel().getSelectedItem();
        Equipo equipo = tableViewEquipos.getSelectionModel().getSelectedItem();
        
        ValidationSupport vSnombre = new ValidationSupport();
        vSnombre.registerValidator(txtArray[0], false, (Control c, String texto) -> { 
            if (texto.length() == 0) { 
                return ValidationResult.fromWarning(c, "El nombre no debe estar vacío"); 
            } else if (texto.length() < 5 || texto.length() > 35) { 
                return ValidationResult.fromError(c, "El nombre debe tener entre 5 y 35 caracteres"); 
            } else { 
                return ValidationResult.fromInfo(c, "OK"); 
            } 
        });
                
        ValidationSupport vScategoria = new ValidationSupport();
        Validator<String> categoriaValidator = Validator.createPredicateValidator(valor -> {
            return valor != null && (valor.equalsIgnoreCase("Senior") || valor.equalsIgnoreCase("Juvenil"));
        }, "La categoría debe ser 'Senior' o 'Juvenil'");

        vScategoria.registerValidator(txtArray[4], false, categoriaValidator);

        ValidationSupport vSapellidos = new ValidationSupport();
        ValidationSupport vSedad = new ValidationSupport();
        ValidationSupport vSnif = new ValidationSupport();
        ValidationSupport vSlocalidad = new ValidationSupport();
        ValidationSupport vSdirector = new ValidationSupport();
        
        switch (contexto) {
            case JUGADOR -> {

                stageInsertar.setTitle(accion.toString().toLowerCase() + " " + contexto.toString().toLowerCase());

                labels[0].setText("Nombre:");
                labels[1].setText("Apellidos:");
                labels[2].setText("Nif:");
                labels[3].setText("Edad:");
                labels[4].setText("Categoría:");
                if (accion == Accion.EDITAR) {
                    txtArray[0].setText(jugador.getNombre());
                    txtArray[1].setText(jugador.getApellidos());
                    txtArray[2].setText(jugador.getNif());
                    txtArray[3].setText(String.valueOf(jugador.getEdad()));
                    txtArray[4].setText(jugador.getCategoria());
                }
                                
                vSapellidos.registerValidator(txtArray[1], false, (Control c, String texto) -> { 
                    if (texto.length() == 0) { 
                        return ValidationResult.fromWarning(c, "El campo de apellidos no puede estar vacío"); 
                    } else if (texto.length() < 5 || texto.length() > 30) { 
                        return ValidationResult.fromError(c, "El nombre debe tener entre 5 y 30 caracteres"); 
                    } else { 
                        return ValidationResult.fromInfo(c, "OK"); 
                    } 
                });


                vSedad.registerValidator(txtArray[3], false, (Control c, String texto) -> {
                    if (texto == null || texto.isEmpty()) {
                        return ValidationResult.fromWarning(c, "El campo de edad no puede estar vacío");
                    } else {
                        try {
                            int number = Integer.parseInt(texto);
                            if (number >= 16 && number <= 50) {
                                return ValidationResult.fromInfo(c, "OK");
                            } else {
                                return ValidationResult.fromError(c, "La edad debe estar entre 16 y 50");
                            }
                        } catch (NumberFormatException e) {
                            return ValidationResult.fromError(c, "Debe ingresar un número válido");
                        }
                    }
                });


                vSnif.registerValidator(txtArray[2], false, (Control c, String texto) -> {
                    if (texto == null || texto.isEmpty()) {
                        return ValidationResult.fromWarning(c, "El campo de NIF no puede estar vacío");
                    } else if (texto.matches("\\d{8}[A-Za-z]")) {
                        return ValidationResult.fromInfo(c, "OK");
                    } else {
                        return ValidationResult.fromError(c, "El NIF debe tener 8 números seguidos de una letra (Ej: 12345678A)");
                    }
                });


                validadores.addAll(Arrays.asList(vSnombre, vSapellidos, vSnif, vSedad, vScategoria));

            }
            case EQUIPO -> {
                stageInsertar.setTitle("Registrar " + contexto.toString().toLowerCase());

                labels[0].setText("Nombre:");
                labels[1].setText("Director:");
                labels[2].setText("Escudo:");
                hboxArray[2].getChildren().remove(txtArray[2]); // Quitar el TextField
                hboxArray[2].getChildren().add(btnFileChooser); // Añadir el botón para seleccionar archivo
                labels[3].setText("Localidad:");
                txtArray[3].setPrefWidth(80);
                labels[4].setText("Categoría:");
                if (accion == Accion.EDITAR) {
                    txtArray[0].setText(equipo.getNombre());
                    txtArray[1].setText(equipo.getDirector());
                    txtArray[3].setText(equipo.getLocalidad());
                    txtArray[4].setText(equipo.getCategoria());
                }
                
                vSlocalidad.registerValidator(txtArray[3], false, (Control c, String texto) -> {
                    if (texto == null || texto.isEmpty()) {
                        return ValidationResult.fromWarning(c, "La localidad no debe estar vacía");
                    } else if (!leerLocalidades().contains(texto)) {
                        return ValidationResult.fromError(c, "La localidad debe ser una localidad válida de España");
                    } else {
                        return ValidationResult.fromInfo(c, "OK");
                    }
                });
                
                vSdirector.registerValidator(txtArray[1], false, (Control c, String texto) -> { 
                    if (texto.length() == 0) { 
                        return ValidationResult.fromWarning(c, "El nombre del director no debe estar vacío"); 
                    } else if (texto.length() < 5 || texto.length() > 10) { 
                        return ValidationResult.fromError(c, "El nombre debe tener entre 5 y 40 caracteres"); 
                    } else { 
                        return ValidationResult.fromInfo(c, "OK"); 
                    } 
                });
                validadores.addAll(Arrays.asList(vSnombre, vSdirector, vSlocalidad, vScategoria));
            }
        } 
                 
         Platform.runLater(() -> {
             int longitud = (contexto == Contexto.EQUIPO) ? 4 : 5;
             for (int i = 0; i < longitud; i++) {
                 final int index = i;

                GraphicValidationDecoration decorador = new GraphicValidationDecoration() {
                    @Override
                    public void applyValidationDecoration(ValidationMessage message) {
                        super.applyValidationDecoration(message);
                        if (message.getSeverity() == Severity.ERROR || message.getSeverity() == Severity.WARNING) {
                            labels[index].setGraphic(iconoPersonalizadoEtiqueta());
                        } else if (message.getSeverity() == Severity.INFO) {
                            labels[index].setGraphic(null);
                        }
                    }
                };

                if (validadores.get(i) != null) {
                    validadores.get(i).setValidationDecorator(decorador);
                }
            }
        });

        btnAceptar.setOnAction(e -> {
            boolean todoOK = true;
            for (ValidationSupport validationSupport : validadores) {
                if (validationSupport.getValidationResult() != null) {
                    todoOK = todoOK && validationSupport.getValidationResult().getErrors().isEmpty();
                } else {
                    todoOK = false;
                }
            }

            if (todoOK) {
                switch (contexto) {
                    case JUGADOR -> {
                        if (accion == Accion.INSERTAR) {
                            String query = "INSERT INTO JUGADOR (nif, nombre, apellidos, edad, categoria, fichado) VALUES (?, ?, ?, ?, ?, ?)";
                            try {
                                PreparedStatement preparedStatement = this.conexion.prepareStatement(query);
                                preparedStatement.setString(1, txtArray[2].getText());
                                preparedStatement.setString(2, txtArray[0].getText());
                                preparedStatement.setString(3, txtArray[1].getText());
                                preparedStatement.setInt(4, Integer.parseInt(txtArray[3].getText()));
                                preparedStatement.setString(5, txtArray[4].getText());
                                preparedStatement.setBoolean(6, false);
                                preparedStatement.executeUpdate();

                                resultAlert(AlertType.INFORMATION, "", "JUGADOR REGISTRADO CORRECTAMENTE", "");
                                stageInsertar.close();
                            } catch (SQLException ex) {
                                System.out.println("Excepción: " + ex.getMessage());
                                resultAlert(AlertType.ERROR, "", "ERROR AL REGISTRAR EL JUGADOR", "");
                            }
                        } else if (accion == Accion.EDITAR) {
                            String query = "UPDATE JUGADOR SET nombre=?, apellidos=?, edad=?, categoria=?, fichado=? WHERE nif=?";
                            try {
                                PreparedStatement preparedStatement = this.conexion.prepareStatement(query);
                                preparedStatement.setString(1, txtArray[0].getText());
                                preparedStatement.setString(2, txtArray[1].getText());
                                preparedStatement.setInt(3, Integer.parseInt(txtArray[3].getText()));
                                preparedStatement.setString(4, txtArray[4].getText());
                                preparedStatement.setBoolean(5, jugador.isFichado());
                                preparedStatement.setString(6, txtArray[2].getText());
                                preparedStatement.executeUpdate();

                                resultAlert(AlertType.INFORMATION, "", "JUGADOR EDITADO CORRECTAMENTE", "");
                                stageInsertar.close();

                            } catch (SQLException ex) {
                                System.out.println("Excepción: " + ex.getMessage());
                                resultAlert(AlertType.ERROR, "", "ERROR AL EDITAR EL JUGADOR", "Ha ocurrido un error al editar al jugador, inténtelo de nuevo");
                            }
                        }
                        mostrarJugadores();
                    }
                        case EQUIPO -> {
                            if (accion == Accion.INSERTAR) {
                                String query = "INSERT INTO EQUIPO (nombre, director_equipo, escudo, localidad, categoria) VALUES (?, ?, ?, ?, ?)";
                                try {
                                    for (int i = 0; i < hboxArray.length; i++) {
                                        System.out.println(i + " " + txtArray[i].getText());
                                    }
                                    PreparedStatement preparedStatement = this.conexion.prepareStatement(query);
                                    preparedStatement.setString(1, txtArray[0].getText());
                                    preparedStatement.setString(2, txtArray[1].getText());
                                    preparedStatement.setString(3, imagenBase64 == null ? "" : imagenBase64);
                                    preparedStatement.setString(4, txtArray[3].getText());
                                    preparedStatement.setString(5, txtArray[4].getText());
                                    preparedStatement.executeUpdate();

                                    resultAlert(AlertType.INFORMATION, "", "EQUIPO REGISTRADO CORRECTAMENTE", "");
                                    stageInsertar.close();
                                } catch (SQLException ex) {
                                    System.out.println("Excepción: " + ex.getMessage());
                                    resultAlert(AlertType.ERROR, "", "ERROR AL REGISTRAR EL EQUIPO", "");
                                }
                            } else if (accion == Accion.EDITAR) {
                                String query = "UPDATE EQUIPO SET nombre=?, director_equipo=?, escudo=?, localidad=?, categoria=? WHERE id_equipo=?";
                                try {
                                    PreparedStatement preparedStatement = this.conexion.prepareStatement(query);
                                    preparedStatement.setString(1, txtArray[0].getText());
                                    preparedStatement.setString(2, txtArray[1].getText());
                                    preparedStatement.setString(3, imagenBase64);
                                    preparedStatement.setString(4, txtArray[3].getText());
                                    preparedStatement.setString(5, txtArray[4].getText());
                                    preparedStatement.setInt(6, equipo.getIdEquipo());
                                    preparedStatement.executeUpdate();

                                    resultAlert(AlertType.INFORMATION, "", "EQUIPO EDITADO CORRECTAMENTE", "");
                                    stageInsertar.close();

                                } catch (SQLException ex) {
                                    System.out.println("Excepción: " + ex.getMessage());
                                    resultAlert(AlertType.ERROR, "", "ERROR AL EDITAR EL EQUIPO", "Ha ocurrido un error al editar el equipo, inténtelo de nuevo");
                                }
                            }
                            mostrarEquipos();
                        }
                    }
            } else {
                resultAlert(AlertType.ERROR, "", "El formulario contiene errores, por favor revisa los campos!", "");
            }
        });

        btnCancelar.setOnAction(e -> {
            stageInsertar.close();
        });

        Scene scene = new Scene(vboxScene, 350, 270);
        scene.getStylesheets().add(css);       
        stageInsertar.setScene(scene);
        stageInsertar.initModality(Modality.APPLICATION_MODAL);
        stageInsertar.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream(accion == Accion.INSERTAR ? masImg : editarImg)));
        stageInsertar.show();
    }

    private List<String> leerLocalidades(){
        List<String> localidadesEspana = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/txt/provincias_españa.txt")))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                localidadesEspana.add(linea.trim());
            }
        } catch (IOException e) {
            System.out.println("Error al cargar localidades: " + e.getMessage());
        }
        return localidadesEspana;
    }
    
    
    
    private Label iconoPersonalizadoEtiqueta() {
        Label errorLabel = new Label("X");
        errorLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
        return errorLabel;
    }
    
    private void habilitarBotones(Contexto contexto) {
        Button[] botones= {btnInsertar, btnEditar, btnEliminar};

        switch (contexto) {
            case JUGADOR, EQUIPO -> {
                for (Button boton : botones) {
                    boton.setDisable(false);
                }
            }
            case HISTORIAL -> {
                botones[0].setDisable(true);
                botones[1].setDisable(true);
                botones[2].setDisable(false);
                //botones[3].setDisable(false);

            }
            case FICHAJE -> {
                for (Button boton : botones) {
                    boton.setDisable(true);
                }
            }
        }
    }

    private String imagenToBase64(FileChooser fileChooser) {
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            try {
                Image originalImage = new Image(selectedFile.toURI().toString());
                Image resizedImage = new Image(originalImage.getUrl(), 150, 150, true, true);

                BufferedImage bufferedImage = SwingFXUtils.fromFXImage(resizedImage, null);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(bufferedImage, "png", baos);
                byte[] imageBytes = baos.toByteArray();
                return Base64.getEncoder().encodeToString(imageBytes);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return null;
    }
    
    private Image base64ToImage(String base64Image) {
        if (base64Image == null || base64Image.isEmpty()) {
            return null; 
        }

        try {
            byte[] imageBytes = Base64.getDecoder().decode(base64Image);
            ByteArrayInputStream byteArray = new ByteArrayInputStream(imageBytes);
            BufferedImage bufferedImage = ImageIO.read(byteArray);
            return SwingFXUtils.toFXImage(bufferedImage, null);
        } catch (IOException | IllegalArgumentException e) {
            System.out.println("Error al convertir Base64 a imagen: " + e.getMessage());
            return null; 
        }
    }
   
    private void resultAlert(AlertType at, String mensajeTitulo, String mensajeHeader, String mensaje){
        
        Alert alert = new Alert(at);
        alert.setTitle(mensajeTitulo);
        alert.setHeaderText(mensajeHeader);
        alert.setContentText(mensaje);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.showAndWait();
    }
    
    private Object selectedItem(Contexto contexto) {
        return switch (contexto) {
            case JUGADOR ->  tableViewJugadores.getSelectionModel().getSelectedItem();
            case EQUIPO -> tableViewEquipos.getSelectionModel().getSelectedItem();
            case HISTORIAL -> tableViewHistorial.getSelectionModel().getSelectedItem();
            default -> null;   
        };
    }
    
    public Connection getConnection() throws IOException {
        Properties properties = new Properties();
        String IP, PORT, BBDD, USER, PWD;
        try {
            InputStream input_ip = new FileInputStream("ip.properties");//archivo debe estar junto al jar
            properties.load(input_ip);
            IP = (String) properties.get("IP");
        } catch (FileNotFoundException e) {
            System.out.println("No se pudo encontrar el archivo de propiedades para IP, se establece localhost por defecto");
            IP = "localhost";
        }

        InputStream input = getClass().getClassLoader().getResourceAsStream("bbdd.properties");
        if (input == null) {
            System.out.println("No se pudo encontrar el archivo de propiedades");
            return null;
        } else {
            properties.load(input);
            PORT = (String) properties.get("PORT");
            BBDD = (String) properties.get("BBDD");
            USER = (String) properties.get("USER"); 
            PWD = (String) properties.get("PWD");

            Connection conn;
            try {
                conn = DriverManager.getConnection("jdbc:mysql://" + IP + ":" + PORT + "/" + BBDD, USER, PWD);
                return conn;
            } catch (SQLException e) {
                System.out.println("Error SQL: " + e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Ha ocurrido un error de conexión");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
                exit(0);
                return null;
            }
        }
    }
    
    public void mostrarJugadores() {
        tableViewJugadores.setItems(getListaJugadores());
    }

    public ObservableList<Jugador> getListaJugadores() {
        if (conexion != null) {
            listaJugadores.clear(); 
            String query = "SELECT * FROM JUGADOR";
            try {
                rs = st.executeQuery(query);
                Jugador jugador;
                while (rs.next()) {
                    jugador = new Jugador(rs.getString("nif"), rs.getString("nombre"), rs.getString("apellidos"), rs.getInt("edad"), rs.getString("categoria"), rs.getBoolean("fichado"));
                    listaJugadores.add(jugador);
                }
            } catch (SQLException e) {
                System.out.println("Excepción SQL: "+e.getMessage());
            }
            return listaJugadores;
        }
        return null;
    }
    
    public void mostrarEquipos(){
        tableViewEquipos.setItems(getListaEquipos());
    }
    
    public ObservableList<Equipo> getListaEquipos(){
        if(conexion != null) {
            listaEquipos.clear();
            String query = "SELECT * FROM EQUIPO";
            try {
                rs = st.executeQuery(query);
                Equipo equipo;
                while(rs.next()) {
                    equipo = new Equipo(rs.getInt("id_equipo"), rs.getString("nombre"), rs.getString("categoria"), rs.getString("escudo"), rs.getString("localidad"), rs.getString("director_equipo"));
                    listaEquipos.add(equipo);
                }
            } catch (SQLException e){
                System.out.println("Excepción SQL: "+e.getMessage());
            }
            return listaEquipos;
        }
        return null;
    }
    
    public void mostrarFichajesHistorial(){
        tableViewHistorial.setItems(getListaHistorial());
    }
    
    public ObservableList<Fichaje> getListaHistorial(){
        if(conexion != null) {
            listaFichajes.clear();
            String query = "SELECT f.fecha_fichaje, f.nif_jugador, CONCAT(j.nombre, ' ', j.apellidos) as nombre_jugador, f.id_equipo, e.nombre as nombre_equipo, f.fecha_fin_fichaje "
                    + "FROM FICHA f JOIN JUGADOR j ON f.nif_jugador = j.nif JOIN EQUIPO e ON f.id_equipo = e.id_equipo;";
            try {
                rs = st.executeQuery(query);
                Fichaje fichaje;
                while(rs.next()) {
                    LocalDate fechaFichaje = rs.getDate("fecha_fichaje").toLocalDate();
                    LocalDate fechaFinFichaje = rs.getDate("fecha_fin_fichaje") != null ? rs.getDate("fecha_fin_fichaje").toLocalDate() : null;

                    fichaje = new Fichaje(rs.getString("nif_jugador"), rs.getString("nombre_jugador"), rs.getInt("id_equipo"),  rs.getString("nombre_equipo"), fechaFichaje, fechaFinFichaje);
                    listaFichajes.add(fichaje);
                }
            } catch (SQLException e){
                System.out.println("Excepción SQL: "+e.getMessage());
            }
            return listaFichajes;
        }
        return null;
    }
    
    private void inicializarTablaJugadores() {
        
        nifColumn.setCellValueFactory(new PropertyValueFactory<>("nif"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        apellidosColumn.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("edad"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        alertFichajeColumn.setCellValueFactory(cellData -> {
            ObservableList<Fichaje> fichajes = getListaHistorial(); 
            LocalDate hoy = LocalDate.now();
            Jugador jugador = cellData.getValue();
            Fichaje fichaje = null;
            for (Fichaje f : fichajes) {
                if (f.getNifJugador().equals(jugador.getNif())) {
                    fichaje = f;
                    break;
                }
            }

            String aviso = null;

            if (fichaje != null ) {
                LocalDate fechaFin = fichaje.getFechaFin();
                if(fichaje.getFechaFin() == null){
                    aviso = "./images/verde.png";
                } else if(jugador.isFichado()){
                    Period diferencia = Period.between(hoy, fechaFin);
                    int meses = diferencia.getYears() * 12 + diferencia.getMonths(); 

                    if (meses <= 1) {
                        aviso = "./images/rojo.png"; 
                    } else if (meses >= 1 && meses <= 5) {
                        aviso = "./images/amarillo.png"; 
                    } else {
                        aviso = "./images/verde.png";
                    } 
                }
            }

            ImageView imageView = new ImageView();
            imageView.setFitHeight(30);
            imageView.setFitWidth(30);

            if(aviso != null){
                Image image = new Image(getClass().getClassLoader().getResourceAsStream(aviso));
                imageView.setImage(image);
            }
            VBox vbox = new VBox(imageView);
            vbox.setAlignment(Pos.CENTER);

            return new SimpleObjectProperty<>(vbox);
        });
        
        tableViewJugadores.setItems(getListaJugadores());
    }

    private void inicializarTablaEquipos() {
        idTeamColumn.setCellValueFactory(new PropertyValueFactory<>("idEquipo"));
        nameTeamColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        categoryTeamColumn.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        escudoTeamColumn.setCellValueFactory(cellData -> {
            String escudoBase64 = cellData.getValue().getUrlEscudo();
            VBox vbox = new VBox();
            vbox.setAlignment(Pos.CENTER);

            if (escudoBase64 != null && !escudoBase64.isEmpty()) {
                Image image = base64ToImage(escudoBase64);
                if (image != null) {
                    ImageView imageView = new ImageView(image);
                    imageView.setFitWidth(50); 
                    imageView.setFitHeight(50); 
                    imageView.setPreserveRatio(true); 
                    vbox.getChildren().add(imageView);
                }
            }

            return new SimpleObjectProperty<>(vbox);
        });

        directorTeamColumn.setCellValueFactory(new PropertyValueFactory<>("director"));
        regionTeamColumn.setCellValueFactory(new PropertyValueFactory<>("localidad"));
        signBySeasonColumn.setCellValueFactory(cellData -> {
            Equipo equipo = cellData.getValue();
            long numFichajes = getListaHistorial().stream()
                .filter(fichaje -> fichaje.getIdEquipo() == equipo.getIdEquipo()) 
                .count(); 
            int num = (int) numFichajes;
            return new SimpleObjectProperty<>(num);
        });

        tableViewEquipos.setItems(getListaEquipos());
    }

    private void inicializarTablaFichajes() {
        jugadorHistorialColumn.setCellValueFactory(new PropertyValueFactory<>("jugador"));
        equipoHistorialColumn.setCellValueFactory(new PropertyValueFactory<>("equipo"));
        fechaInicioColumn.setCellValueFactory(new PropertyValueFactory<>("fechaInicio"));
        fechaFinColumn.setCellValueFactory(new PropertyValueFactory<>("fechaFin"));
        tableViewHistorial.setItems(getListaHistorial());
    }
    
    private void setIcons(){
        imageBtnSalir.setImage(new Image(getClass().getClassLoader().getResourceAsStream(salirImg)));
        imageBtnInsertar.setImage(new Image(getClass().getClassLoader().getResourceAsStream(masImg)));
        imageBtnEditar.setImage(new Image(getClass().getClassLoader().getResourceAsStream(editarImg)));
        imageBtnEliminar.setImage(new Image(getClass().getClassLoader().getResourceAsStream(eliminarImg)));
        //imageBtnFiltrar.setImage(new Image(getClass().getClassLoader().getResourceAsStream("./images/filtrar.png")));
        //imageBtnAyuda.setImage(new Image(getClass().getClassLoader().getResourceAsStream("./images/ayuda.png")));
        imageViewEscudo.setImage(new Image(getClass().getClassLoader().getResourceAsStream(placeholderImg)));
        imageBtnBuscarEquipo.setImage(new Image(getClass().getClassLoader().getResourceAsStream(lupaImg)));
        imageBtnBuscarJugadores.setImage(new Image(getClass().getClassLoader().getResourceAsStream(lupaImg)));
        imageBtnBorrarEquipo.setImage(new Image(getClass().getClassLoader().getResourceAsStream(borrarImg)));
        imageBtnBorrarJugador.setImage(new Image(getClass().getClassLoader().getResourceAsStream(borrarImg)));

    }
    
    KeyCodeCombination ctrlSupr = new KeyCodeCombination(KeyCode.DELETE, KeyCombination.CONTROL_DOWN);
    KeyCodeCombination ctrlEnter = new KeyCodeCombination(KeyCode.ENTER, KeyCodeCombination.CONTROL_DOWN);
    KeyCodeCombination ctrlE = new KeyCodeCombination(KeyCode.E, KeyCodeCombination.CONTROL_DOWN);
    KeyCodeCombination ctrlI = new KeyCodeCombination(KeyCode.I, KeyCodeCombination.CONTROL_DOWN);
    KeyCodeCombination altJ = new KeyCodeCombination(KeyCode.J, KeyCodeCombination.ALT_DOWN);
    KeyCodeCombination altH = new KeyCodeCombination(KeyCode.H, KeyCodeCombination.ALT_DOWN);
    KeyCodeCombination altG = new KeyCodeCombination(KeyCode.G, KeyCodeCombination.ALT_DOWN);
    KeyCodeCombination altQ = new KeyCodeCombination(KeyCode.Q, KeyCodeCombination.ALT_DOWN);

        
   

    @FXML
    //Comprobación de todas las combinaciones de teclas        
    void combKeys(KeyEvent event) {
        if (ctrlSupr.match(event)) {
            eliminarDatos(null); 
        } else if (ctrlEnter.match(event)) {
            insertarDatos(null);
        } else if(ctrlE.match(event)) {
            editarDatos(null);
        } else if (ctrlI.match(event)) {
            mostrarAyuda(null);
        } else if (altJ.match(event)) {
            MainTab.getSelectionModel().select(tabJugadores);
        } else if(altH.match(event)) {
            MainTab.getSelectionModel().select(tabHistorial);
        } else if(altG.match(event)) {
            MainTab.getSelectionModel().select(tabFichaje);
        } else if(altQ.match(event)) {
            MainTab.getSelectionModel().select(tabEquipos);
        }
    }
    
    @FXML
    private void onMouseEnteredHandler(MouseEvent event) {
        if (event.getSource() instanceof Button button) {
            ScaleTransition scaleUp = new ScaleTransition(Duration.millis(200), button);
            scaleUp.setToX(1.1); 
            scaleUp.setToY(1.1);
            scaleUp.play();
        }
    }

    @FXML
    private void onMouseExitedHandler(MouseEvent event) {
        if (event.getSource() instanceof Button button) {
            ScaleTransition scaleDown = new ScaleTransition(Duration.millis(200), button);
            scaleDown.setToX(1.0); 
            scaleDown.setToY(1.0);
            scaleDown.play();
        }
    }

       
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            conexion = this.getConnection();
            if (conexion != null) {
                this.st = conexion.createStatement();
            }
        } catch (IOException | SQLException e) {
        }
        if (conexion != null) {
            inicializarTablaJugadores();
            inicializarTablaEquipos();
            inicializarTablaFichajes();
            setIcons();
        }
        
        tableViewJugadores.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && !event.isConsumed()) { 
                event.consume(); 
                renovarFichaje(event); 
            }
        });          
    }
}
