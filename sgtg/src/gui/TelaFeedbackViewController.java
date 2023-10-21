package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dto.FeedbackDTO;
import dto.GerenciarAlunoDTO;
import gui.util.LoadFeedback;
import gui.util.Telas;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class TelaFeedbackViewController implements Initializable {
	
    private LoadFeedback loadFeedback;
    
    //AS INFORMACOES DO ALUNO ESTÃO AQUIIIIIII!!!
    private GerenciarAlunoDTO aluno;

	public void setAluno(GerenciarAlunoDTO aluno) {
		this.aluno = aluno;
	}

	@FXML
    private Button btnAvaliarEntrega;
    
    @FXML
    private Label labelNomeAluno;

    
	public void setLabelNomeAluno(String nome) {
		this.labelNomeAluno.setText(nome);
	}

	@FXML
    private TableView<FeedbackDTO> tableViewFeedback;

    @FXML
    private TableColumn<FeedbackDTO, String> tableColumnTituloEntrega;

    @FXML
    private TableColumn<FeedbackDTO, String> tableColumnDescricao;

    @FXML
    private TableColumn<FeedbackDTO, Double> tableColumnNota;

    @FXML
    private TableColumn<FeedbackDTO, String> tableColumnComentario;
    
    public void onBtnAvaliarEntrega() {
        Telas tela = new Telas();

        tela.loadView4("/gui/TelaFeedbackAluno.fxml", aluno);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        initializeNodes();
    }

    private void initializeNodes() {
        // TODO Auto-generated method stub
//        tableColumnTituloEntrega.setCellValueFactory(new PropertyValueFactory<>("titulo_entrega"));
//        tableColumnDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
//        tableColumnNota.setCellValueFactory(new PropertyValueFactory<>("nota"));
//        tableColumnComentario.setCellValueFactory(new PropertyValueFactory<>("comentario"));
    }

}