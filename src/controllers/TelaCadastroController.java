package controllers;

import java.io.IOException;
import java.sql.SQLException;

import dao.DaoVendedor;
import entidades.Vendedor;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class TelaCadastroController {
	
	@FXML
	private BorderPane TelaCadastro;
	
	@FXML
	private TextField textFieldNome;
	
	@FXML
	private TextField textFieldEmail;
	
	
	public void clickCadastrar() throws IOException{
		String nome = textFieldNome.getText();
		String email = textFieldEmail.getText().trim();
		
		Vendedor vendedor = new Vendedor(nome, email);
		
		DaoVendedor daoVendedor = new DaoVendedor();
		
		try {
			daoVendedor.inserir(vendedor);
			
			clickVoltar();
		} catch (SQLException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Atenção");
			alert.setContentText("Erro: " + e.getMessage());
			alert.show();
		}
	}
	
	public void clickVoltar() throws IOException {
		BorderPane fxml = new FXMLLoader(
				getClass().getResource("/views/TelaInicial.fxml")).load();
		
		Scene cena = new Scene(fxml);
		
		Stage stage = (Stage) TelaCadastro.getScene().getWindow();
		
		stage.setScene(cena);
	}
	
}
