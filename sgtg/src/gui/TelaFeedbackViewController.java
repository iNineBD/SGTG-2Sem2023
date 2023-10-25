package gui;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import conexao.DB;
import dto.FeedbackDTO;
import dto.GerenciarAlunoDTO;

import gui.util.Telas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TelaFeedbackViewController implements Initializable {
	
    
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
        tableColumnTituloEntrega.setCellValueFactory(new PropertyValueFactory<>("titulo_entrega"));
        tableColumnDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        tableColumnNota.setCellValueFactory(new PropertyValueFactory<>("nota"));
        tableColumnComentario.setCellValueFactory(new PropertyValueFactory<>("comentario"));
    }
    
    
    public void updateTableViewFeedback() {
    	
    	List<FeedbackDTO> feedbacks = new ArrayList<FeedbackDTO>();
    	int id_aluno = this.aluno.getId_aluno();
    	
    	
		try {
			
			DB db = new DB();
	    	Connection conn = db.getConnection();
	    	PreparedStatement st = conn.prepareStatement("SELECT feedback.comentario, feedback.nota, entrega.titulo_entrega, entrega.descricao FROM feedback, aluno, entrega where aluno.id = feedback.id_aluno and feedback.id_entrega = entrega.id and  id_aluno = ?");
	    	st.setInt(1, id_aluno);
	    	
	    	ResultSet result = st.executeQuery();
	    	
	    	while(result.next()) {
	    		String comentario = result.getString("comentario");
	    		String titulo_entrega = result.getString("titulo_entrega");
	    		String descricao = result.getString("descricao");
	    		Double nota = result.getDouble("nota");
	    		
	    		feedbacks.add(new FeedbackDTO(titulo_entrega, descricao, nota, comentario));
	    	}
	    	
	    	ObservableList<FeedbackDTO> obsList = FXCollections.observableArrayList(feedbacks);
	    	
	    	this.tableViewFeedback.setItems(obsList);
	    	
	    	
	    	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}