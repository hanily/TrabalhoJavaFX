package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import dao.DaoVendedor;
import entidades.Vendedor;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class TelaInicialController implements Initializable{
	
	@FXML
	private BorderPane TelaInicial;
	
	@FXML
	private TableView<Vendedor> Vendedores;
	
	@FXML
	private TableColumn<Vendedor, Integer> id;
	
	@FXML
	private TableColumn<Vendedor, String> nome;
	
	@FXML
	private TableColumn<Vendedor, String> email;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		id.setCellValueFactory( new PropertyValueFactory<>("idVendedor") );
		nome.setCellValueFactory( new PropertyValueFactory<>("nome") );
		email.setCellValueFactory(new PropertyValueFactory<> ("email"));
		
		DaoVendedor daoVendedor = new DaoVendedor();
		try {
			List<Vendedor> vendedores = daoVendedor.buscarTodos();
			
			Vendedores.setItems( FXCollections.observableArrayList( vendedores ) ); 
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void clickCadastrar() throws IOException {
		BorderPane fxml = new FXMLLoader(
				getClass().getResource("/views/TelaCadastro.fxml")).load();
		
		Scene cena = new Scene(fxml);
		
		Stage stage = (Stage) TelaInicial.getScene().getWindow();
		
		stage.setScene(cena);
	}

}