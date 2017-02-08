package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.*;
import java.util.Properties;



public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("User Position");

        Connection co = getConnection();
        viewTable(co, "cerberus");
        updateUserPosition(co, 1, 3, 5);

        // Grid to set views
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);

        // Title
        Text scenetitle = new Text("Update user's position :");
        grid.add(scenetitle, 0, 0, 2, 1);

        // X label
        Label userName = new Label("X :");
        grid.add(userName, 0, 1);
        TextField xBox = new TextField();
        grid.add(xBox, 1, 1);

        // Y label
        Label pw = new Label("Y :");
        grid.add(pw, 0, 2);
        TextField yBox = new TextField();
        grid.add(yBox, 1, 2);

        // OK button
        Button btn = new Button("Send");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 4);

        // Scene
        Scene scene = new Scene(grid, 300, 275);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public static void viewTable(Connection con, String dbName) throws SQLException {
        Statement stmt = null;
        String query = "SELECT * FROM user;";
        System.out.println(query);
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String username = rs.getString("name");
                float u_x = rs.getFloat("x");
                float u_y = rs.getFloat("y");
                System.out.println(username + "\t" + u_x + "\t" + u_y);
            }
        } catch (SQLException e ) {
            System.out.println(e.getMessage());
        } finally {
            if (stmt != null) { stmt.close(); }
        }
    }

    public Connection getConnection() throws SQLException {
        Connection con = null;
        try {
            String url = "jdbc:mysql://localhost:3306/cerberus";
            con = DriverManager.getConnection(url, "root", "root");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("Connected to database");
        return con;
    }

    public void updateUserPosition(Connection con, int user_ID, float user_X, float user_Y) {
        Statement stmt = null;
        String query = "UPDATE user SET x=" + user_X + ", y=" + user_Y + " WHERE id=" + user_ID + ";";
        System.out.println(query);
        try {
            stmt = con.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e ) {
            System.out.println(e.getMessage());
        }
    }
}
