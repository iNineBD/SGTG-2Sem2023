package conexao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import gui.util.Alerts;
import javafx.scene.control.Alert;

public class DB {

	private static Connection conn = null;
	
	public static Connection getConnection() {
		if (conn == null) {
			try {
				Properties props = loadProperties();
				String url = props.getProperty("dburl");
				conn = DriverManager.getConnection(url, props);
			}
			catch (SQLException e) {
				Alerts alerts = new Alerts();
				alerts.showAlert("SQL Exception", "getConnection", e.getMessage(), Alert.AlertType.ERROR);
			}
		}
		return conn;
	}
	
	public static void closeConnection() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				Alerts alerts = new Alerts();
				alerts.showAlert("SQL Exception", "closeConnection", e.getMessage(), Alert.AlertType.ERROR);
			}
		}
	}
	
	private static Properties loadProperties() {
		try (FileInputStream fs = new FileInputStream("db.properties")) {
			Properties props = new Properties();
			props.load(fs);
			return props;
		}
		catch (IOException e) {
			Alerts alerts = new Alerts();
			alerts.showAlert("IO Exception", "loadProperties", e.getMessage(), Alert.AlertType.ERROR);
		}
		return null;
	}
	
	public static void closeStatement(Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				Alerts alerts = new Alerts();
				alerts.showAlert("SQL Exception", "closeStatement", e.getMessage(), Alert.AlertType.ERROR);
			}
		}
	}

	public static void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				Alerts alerts = new Alerts();
				alerts.showAlert("SQL Exception", "closeResultSet", e.getMessage(), Alert.AlertType.ERROR);
			}
		}
	}
}
