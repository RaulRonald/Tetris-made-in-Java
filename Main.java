package application;
	
import java.util.ArrayList;
import java.util.List;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
class tetrissquare{
	Rectangle rectangle;
	Scene scene;
	public double speed;
	public tetrissquare(Pane root,Scene scene) {
		this.scene = scene;
		speed = 1;
        rectangle = new Rectangle(10, 10); // Criando um retângulo com largura e altura de 100 pixels
        rectangle.setFill(Color.RED); // Definindo a cor de preenchimento do retângulo
        rectangle.setStroke(Color.BLACK); // Definindo a cor da borda do retângulo
        rectangle.setLayoutX(330);
        rectangle.setLayoutY(50);
        root.getChildren().add(rectangle);
    }
	public void update(boolean left,boolean right)
	{
            rectangle.setLayoutY(rectangle.getLayoutY() + speed);
            //INPUTS
            scene.setOnKeyPressed(event -> {
                if ((event.getText().equals("d") || event.getText().equals("D")) && right) {
                	rectangle.setLayoutX(rectangle.getLayoutX() + 10);
                }
                if ((event.getText().equals("a") || event.getText().equals("A")) && left) {
                	rectangle.setLayoutX(rectangle.getLayoutX() - 10);
                }
            });
	}
}
public class Main extends Application {
    static enum scene {MENU, NAME_SET, POINT_TABLES, TETRIS_PLAY}
    private scene nowscene;
    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane(); // Usando um Pane como layout raiz
        this.nowscene = scene.MENU;
        Scene scene = new Scene(root, 848, 480); // Criando a cena
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Tetris Game");
        Draw(root,scene); // Chamando o método Draw para adicionar os botões
        primaryStage.show();
    }

    public void Draw(Pane root,Scene scenet) {
    	root.getChildren().clear();
        switch (this.nowscene) {
            case MENU:
            	//botão do play
                Button button = new Button("PLAY GAME!");
                button.setLayoutX(380);
                button.setLayoutY(150);
                button.setPrefWidth(100);
                button.setPrefHeight(100);
                button.setStyle("-fx-font-size: 12px;");
                button.setOnAction(e -> {
                    this.nowscene = scene.NAME_SET;
                    Draw(root,scenet);
                });
                //botão de pontuações
                Button button1 = new Button("point panels");
                button1.setLayoutX(380);
                button1.setLayoutY(250);
                button1.setPrefWidth(100);
                button1.setPrefHeight(50);
                button1.setStyle("-fx-font-size: 10px;");
                button1.setOnAction(e -> {
                    this.nowscene = scene.POINT_TABLES;
                    Draw(root,scenet);
                });
                root.getChildren().add(button);
                root.getChildren().add(button1);
                break;
            case NAME_SET:
                // Adicione elementos para a cena NAME_SET
            	// Criando uma caixa de texto
            	//TEXTO DA CENA
            	Text text = new Text("INSIRA SEU NICKNAME");
            	text.setLayoutX(380);
            	text.setLayoutY(240);
            	//INSIRA O NOME NO BLOCO DE TEXTO
            	TextField textField = new TextField();
                textField.setLayoutX(350);
                textField.setLayoutY(250);
                textField.setPrefWidth(200);
                //BOTÕES
              //botão do play
                Button button2 = new Button("PLAY");
                button2.setLayoutX(350);
                button2.setLayoutY(275);
                button2.setPrefWidth(50);
                button2.setPrefHeight(25);
                button2.setOnAction(e -> {
                    this.nowscene = scene.TETRIS_PLAY;
                    Draw(root,scenet);
                });
                //botão de retorno
                Button button3 = new Button("return");
                button3.setLayoutX(400);
                button3.setLayoutY(275);
                button3.setPrefWidth(50);
                button3.setPrefHeight(25);
                button3.setOnAction(e -> {
                    this.nowscene = scene.MENU;
                    Draw(root,scenet);
                });
                //ADICIONANDO
                root.getChildren().add(button2);
                root.getChildren().add(button3);
                root.getChildren().add(textField);
                root.getChildren().add(text);
                
                break;
            case POINT_TABLES:
                // Adicione elementos para a cena POINT_TABLES
                break;
            case TETRIS_PLAY:
                // Adicione elementos para a cena TETRIS_PLAY
            	//LAYALT
            	Rectangle rectangle = new Rectangle(150, 400); // Criando um retângulo com largura e altura de 100 pixels
                rectangle.setFill(Color.GRAY); // Definindo a cor de preenchimento do retângulo
                rectangle.setStroke(Color.BLACK); // Definindo a cor da borda do retângulo
                rectangle.setLayoutX(250);
                rectangle.setLayoutY(50);
                root.getChildren().add(rectangle);
                //GAME INTERACTIONS
                List<tetrissquare> tetrislist = new ArrayList<tetrissquare>();

                //AÇÕES QUE OCORRERÃO A CADA X MILLISSEGUNDOS
            	Timeline timeline = new Timeline(new KeyFrame(Duration.millis(20), event -> {
            		boolean left = true,right = true;
                    if(tetrislist.isEmpty() || tetrislist.get(tetrislist.size() - 1).speed <= 0) {
                    	tetrissquare tetris = new tetrissquare(root, scenet);
                    	tetrislist.add(tetris);
                    	}
                    for(int i = 0;i < (tetrislist.size()-1);i++)
                    {
                    	if((tetrislist.get(tetrislist.size() - 1).rectangle.getLayoutY() + 10) >= tetrislist.get(i).rectangle.getLayoutY() && tetrislist.get(tetrislist.size() - 1).rectangle.getLayoutX() == tetrislist.get(i).rectangle.getLayoutX()) {
                    		tetrislist.get(tetrislist.size() - 1).speed = 0;
                    		
                    	}
                    	if((tetrislist.get(tetrislist.size() - 1).rectangle.getLayoutY()) <= tetrislist.get(i).rectangle.getLayoutY() && (tetrislist.get(tetrislist.size() - 1).rectangle.getLayoutY() + 10) >= tetrislist.get(i).rectangle.getLayoutY()) {
                    		if((tetrislist.get(tetrislist.size() - 1).rectangle.getLayoutX() + 10) == tetrislist.get(i).rectangle.getLayoutX()) {
                        		right = false;
                        	}
                        	else
                        		if((tetrislist.get(tetrislist.size() - 1).rectangle.getLayoutX() - 10) == tetrislist.get(i).rectangle.getLayoutX()) {
                            		left = false;
                            	}
                    	}
                    }
                    
                    if((tetrislist.get(tetrislist.size() - 1).rectangle.getLayoutY() + 10) >= (rectangle.getLayoutY() + 400)) {
                    	tetrislist.get(tetrislist.size() - 1).speed = 0;
                    }
                    if((tetrislist.get(tetrislist.size() - 1).rectangle.getLayoutX() + 10) == rectangle.getLayoutX() + 150) {
                		right = false;
                	}
                	else
                		if((tetrislist.get(tetrislist.size() - 1).rectangle.getLayoutX()) == rectangle.getLayoutX()) {
                    		left = false;
                    	}
                    tetrislist.get(tetrislist.size() - 1).update(left,right);
                    }));
                timeline.setCycleCount(Timeline.INDEFINITE);
                timeline.play();
                break;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}


