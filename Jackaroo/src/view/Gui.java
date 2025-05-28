package view;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.print.DocFlavor.URL;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Colour;
import model.card.Card;
import model.card.standard.Standard;
import model.card.standard.Suit;
import model.card.wild.Wild;
import model.player.Marble;
import model.player.Player;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.PauseTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import engine.Game;
import engine.board.Cell;
import engine.board.SafeZone;
import exception.CannotDiscardException;
import exception.GameException;
import exception.InvalidCardException;
import exception.InvalidMarbleException;



public class Gui extends Application implements EventHandler<ActionEvent> {
	private Stage primaryStage;
	private Scene nameSc;
	private AnchorPane nameAp;
	private Label nameLb;
	private TextField nameFld;
	private Button play;
	
	private Game game;
	private Scene scene;
	private AnchorPane root;
	private Button[] buttons;
	private Button[] safeZoneP;
	private Button[] safeZone1;
	private Button[] safeZone2;
	private Button[] safeZone3;
	private Button test;
	private HBox Myhand;
	private HBox Cpuhand2;
	private VBox Cpuhand1;
	private VBox Cpuhand3;
	private Button[] HomeP;
	private Button[] Cpu1H;
	private Button[] Cpu2H;
	private Button[] Cpu3H;
	private Label FirePit;
	
	private Label Player0;
	private Label Player1;
	private Label Player2;
	private Label Player3;
	
	private Image PlayerImage;
	private ArrayList<Button> MyCards;
	
	private Label CurPlr;
	private Label NxtPlr;
	private Button Deselect;
	private Button CantPlay;
	private Slider slider;
	private Button backButton;
	private Button NextPlay;
	private int homeCount =0; 
	private Button PlayBt;
	private Label Deck;
	private ArrayList<Integer> trapBefore;
	private ArrayList<Integer> trapAfter;
	private Label CardDesc;
	
	
	private ArrayList<Integer> getTrapIndices() {
	    ArrayList<Integer> traps = new ArrayList<>();
	    for (int i = 0; i < 100; i++) {
	        if (game.getBoard().getTrack().get(i).isTrap()) { // assuming isTrap() exists
	            traps.add(i);
	        }
	    }
	    return traps;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		nameAp = new AnchorPane();
		nameSc = new Scene(nameAp, 400, 400);
		primaryStage.setScene(nameSc);
		primaryStage.show();
		
		Image backgroundImage = new Image("Jackar.png"); // Place image in /resources or same package
		BackgroundImage bgImage = new BackgroundImage(
		    backgroundImage,
		    BackgroundRepeat.NO_REPEAT,
		    BackgroundRepeat.NO_REPEAT,
		    BackgroundPosition.DEFAULT,
		    new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true)
		);

		nameAp.setBackground(new Background(bgImage));
		
		nameLb = new Label("Name: ");
		nameLb.setStyle("-fx-text-fill: white;");
		nameLb.setFont(new Font("Arial",26));
		nameLb.setLayoutX(50);
		nameLb.setLayoutY(20);
		nameLb.setPrefSize(150,40);
		nameAp.getChildren().add(nameLb);
		
		nameFld = new TextField();
		nameFld.setLayoutX(180);
		nameFld.setLayoutY(20);
		nameFld.setPrefSize(150,40);
		nameAp.getChildren().add(nameFld);
		
		play = new Button("Play!");
		play.setLayoutX(150);
		play.setLayoutY(200);
		play.setPrefSize(100,60);
		nameAp.getChildren().add(play);
		play.setOnAction(this);
		
		
		
		
	}
	
	public void gameScene(){
		
		try {
			game = new Game(nameFld.getText());
			trapBefore = getTrapIndices();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			displayAlert(e.getMessage());
		}
		
		root = new AnchorPane();
		scene = new Scene(root,1800,950);
		primaryStage.setScene(scene);
		primaryStage.setX(0);
		primaryStage.setY(0);
		
		
		root.setStyle(
			    "-fx-background-color: linear-gradient(from 0% 0% to 0% 100%, #654321, #D2B48C);"
			);
		buttons = new Button[100];

		buttons[0] = new Button(); buttons[0].setLayoutX(600); buttons[0].setLayoutY(700);
		buttons[1] = new Button(); buttons[1].setLayoutX(600); buttons[1].setLayoutY(680);
		buttons[2] = new Button(); buttons[2].setLayoutX(600); buttons[2].setLayoutY(660);
		buttons[3] = new Button(); buttons[3].setLayoutX(600); buttons[3].setLayoutY(640);
		buttons[4] = new Button(); buttons[4].setLayoutX(600); buttons[4].setLayoutY(620);
		buttons[5] = new Button(); buttons[5].setLayoutX(600); buttons[5].setLayoutY(600);
		
		buttons[6] = new Button(); buttons[6].setLayoutX(580); buttons[6].setLayoutY(610);
		buttons[7] = new Button(); buttons[7].setLayoutX(560); buttons[7].setLayoutY(620);
		
		buttons[8] = new Button(); buttons[8].setLayoutX(555); buttons[8].setLayoutY(600);
		buttons[9] = new Button(); buttons[9].setLayoutX(550); buttons[9].setLayoutY(580);
		buttons[10] = new Button(); buttons[10].setLayoutX(545); buttons[10].setLayoutY(560);
		
		buttons[11] = new Button(); buttons[11].setLayoutX(525); buttons[11].setLayoutY(565);
		buttons[12] = new Button(); buttons[12].setLayoutX(505); buttons[12].setLayoutY(570);
		buttons[13] = new Button(); buttons[13].setLayoutX(485); buttons[13].setLayoutY(565);
		
		buttons[14] = new Button(); buttons[14].setLayoutX(495); buttons[14].setLayoutY(545);
		buttons[15] = new Button(); buttons[15].setLayoutX(505); buttons[15].setLayoutY(525);
		buttons[16] = new Button(); buttons[16].setLayoutX(510); buttons[16].setLayoutY(505);
		
		buttons[17] = new Button(); buttons[17].setLayoutX(490); buttons[17].setLayoutY(505);
		buttons[18] = new Button(); buttons[18].setLayoutX(470); buttons[18].setLayoutY(505);
		buttons[19] = new Button(); buttons[19].setLayoutX(450); buttons[19].setLayoutY(505);
		buttons[20] = new Button(); buttons[20].setLayoutX(430); buttons[20].setLayoutY(505);
		buttons[21] = new Button(); buttons[21].setLayoutX(410); buttons[21].setLayoutY(505);
		
		buttons[22] = new Button(); buttons[22].setLayoutX(410); buttons[22].setLayoutY(485);
		buttons[23] = new Button(); buttons[23].setLayoutX(410); buttons[23].setLayoutY(465);
		buttons[24] = new Button(); buttons[24].setLayoutX(410); buttons[24].setLayoutY(445);
		buttons[25] = new Button(); buttons[25].setLayoutX(410); buttons[25].setLayoutY(425);
		
		buttons[26] = new Button(); buttons[26].setLayoutX(430); buttons[26].setLayoutY(425);
		buttons[27] = new Button(); buttons[27].setLayoutX(450); buttons[27].setLayoutY(425);
		buttons[28] = new Button(); buttons[28].setLayoutX(470); buttons[28].setLayoutY(425);
		buttons[29] = new Button(); buttons[29].setLayoutX(490); buttons[29].setLayoutY(425);
		buttons[30] = new Button(); buttons[30].setLayoutX(510); buttons[30].setLayoutY(425);
		
		buttons[31] = new Button(); buttons[31].setLayoutX(505); buttons[31].setLayoutY(402);
		buttons[32] = new Button(); buttons[32].setLayoutX(495); buttons[32].setLayoutY(380);
		buttons[33] = new Button(); buttons[33].setLayoutX(480); buttons[33].setLayoutY(358);
		
		buttons[34] = new Button(); buttons[34].setLayoutX(500); buttons[34].setLayoutY(358);
		buttons[35] = new Button(); buttons[35].setLayoutX(520); buttons[35].setLayoutY(358);
		buttons[36] = new Button(); buttons[36].setLayoutX(540); buttons[36].setLayoutY(358);
		
		buttons[37] = new Button(); buttons[37].setLayoutX(540); buttons[37].setLayoutY(338);
		buttons[38] = new Button(); buttons[38].setLayoutX(540); buttons[38].setLayoutY(318);
		buttons[39] = new Button(); buttons[39].setLayoutX(540); buttons[39].setLayoutY(298);
		
		buttons[40] = new Button(); buttons[40].setLayoutX(562); buttons[40].setLayoutY(305);
		buttons[41] = new Button(); buttons[41].setLayoutX(584); buttons[41].setLayoutY(312);
		
		buttons[42] = new Button(); buttons[42].setLayoutX(584); buttons[42].setLayoutY(292);
		buttons[43] = new Button(); buttons[43].setLayoutX(584); buttons[43].setLayoutY(272);
		buttons[44] = new Button(); buttons[44].setLayoutX(584); buttons[44].setLayoutY(252);
		buttons[45] = new Button(); buttons[45].setLayoutX(584); buttons[45].setLayoutY(232);
		buttons[46] = new Button(); buttons[46].setLayoutX(584); buttons[46].setLayoutY(213);
		
		buttons[47] = new Button(); buttons[47].setLayoutX(607); buttons[47].setLayoutY(213);
		buttons[48] = new Button(); buttons[48].setLayoutX(630); buttons[48].setLayoutY(213);
		buttons[49] = new Button(); buttons[49].setLayoutX(653); buttons[49].setLayoutY(213);
		
		buttons[99] = new Button(); buttons[99].setLayoutX(620); buttons[99].setLayoutY(700);
		buttons[98] = new Button(); buttons[98].setLayoutX(640); buttons[98].setLayoutY(700);
		buttons[97] = new Button(); buttons[97].setLayoutX(660); buttons[97].setLayoutY(700);
		buttons[96] = new Button(); buttons[96].setLayoutX(680); buttons[96].setLayoutY(700);
		
		buttons[95] = new Button(); buttons[95].setLayoutX(680); buttons[95].setLayoutY(680);
		buttons[94] = new Button(); buttons[94].setLayoutX(680); buttons[94].setLayoutY(660);
		buttons[93] = new Button(); buttons[93].setLayoutX(680); buttons[93].setLayoutY(640);
		buttons[92] = new Button(); buttons[92].setLayoutX(680); buttons[92].setLayoutY(620);
		buttons[91] = new Button(); buttons[91].setLayoutX(680); buttons[91].setLayoutY(600);
		
		buttons[90] = new Button(); buttons[90].setLayoutX(700); buttons[90].setLayoutY(610);
		buttons[89] = new Button(); buttons[89].setLayoutX(720); buttons[89].setLayoutY(620);
		
		buttons[88] = new Button(); buttons[88].setLayoutX(725); buttons[88].setLayoutY(600);
		buttons[87] = new Button(); buttons[87].setLayoutX(730); buttons[87].setLayoutY(580);
		buttons[86] = new Button(); buttons[86].setLayoutX(735); buttons[86].setLayoutY(560);
		
		buttons[85] = new Button(); buttons[85].setLayoutX(755); buttons[85].setLayoutY(565);
		buttons[84] = new Button(); buttons[84].setLayoutX(775); buttons[84].setLayoutY(570);
		buttons[83] = new Button(); buttons[83].setLayoutX(795); buttons[83].setLayoutY(565);
		
		buttons[82] = new Button(); buttons[82].setLayoutX(785); buttons[82].setLayoutY(545);
		buttons[81] = new Button(); buttons[81].setLayoutX(775); buttons[81].setLayoutY(525);
		buttons[80] = new Button(); buttons[80].setLayoutX(770); buttons[80].setLayoutY(505);
		
		buttons[79] = new Button(); buttons[79].setLayoutX(790); buttons[79].setLayoutY(505);
		buttons[78] = new Button(); buttons[78].setLayoutX(810); buttons[78].setLayoutY(505);
		buttons[77] = new Button(); buttons[77].setLayoutX(830); buttons[77].setLayoutY(505);
		buttons[76] = new Button(); buttons[76].setLayoutX(850); buttons[76].setLayoutY(505);
		buttons[75] = new Button(); buttons[75].setLayoutX(870); buttons[75].setLayoutY(505);
		
		buttons[74] = new Button(); buttons[74].setLayoutX(870); buttons[74].setLayoutY(485);
		buttons[73] = new Button(); buttons[73].setLayoutX(870); buttons[73].setLayoutY(465);
		buttons[72] = new Button(); buttons[72].setLayoutX(870); buttons[72].setLayoutY(445);
		buttons[71] = new Button(); buttons[71].setLayoutX(870); buttons[71].setLayoutY(425);
		
		buttons[70] = new Button(); buttons[70].setLayoutX(850); buttons[70].setLayoutY(425);
		buttons[69] = new Button(); buttons[69].setLayoutX(830); buttons[69].setLayoutY(425);
		buttons[68] = new Button(); buttons[68].setLayoutX(810); buttons[68].setLayoutY(425);
		buttons[67] = new Button(); buttons[67].setLayoutX(790); buttons[67].setLayoutY(425);
		buttons[66] = new Button(); buttons[66].setLayoutX(770); buttons[66].setLayoutY(425);
		
		buttons[65] = new Button(); buttons[65].setLayoutX(775); buttons[65].setLayoutY(402);
		buttons[64] = new Button(); buttons[64].setLayoutX(785); buttons[64].setLayoutY(380);
		buttons[63] = new Button(); buttons[63].setLayoutX(800); buttons[63].setLayoutY(358);
		
		buttons[62] = new Button(); buttons[62].setLayoutX(780); buttons[62].setLayoutY(358);
		buttons[61] = new Button(); buttons[61].setLayoutX(760); buttons[61].setLayoutY(358);
		buttons[60] = new Button(); buttons[60].setLayoutX(740); buttons[60].setLayoutY(358);
		
		buttons[59] = new Button(); buttons[59].setLayoutX(740); buttons[59].setLayoutY(338);
		buttons[58] = new Button(); buttons[58].setLayoutX(740); buttons[58].setLayoutY(318);
		buttons[57] = new Button(); buttons[57].setLayoutX(740); buttons[57].setLayoutY(298);
		
		buttons[56] = new Button(); buttons[56].setLayoutX(718); buttons[56].setLayoutY(305);
		buttons[55] = new Button(); buttons[55].setLayoutX(696); buttons[55].setLayoutY(312);
		
		buttons[54] = new Button(); buttons[54].setLayoutX(696); buttons[54].setLayoutY(292);
		buttons[53] = new Button(); buttons[53].setLayoutX(696); buttons[53].setLayoutY(272);
		buttons[52] = new Button(); buttons[52].setLayoutX(696); buttons[52].setLayoutY(252);
		buttons[51] = new Button(); buttons[51].setLayoutX(690); buttons[51].setLayoutY(232);
		buttons[50] = new Button(); buttons[50].setLayoutX(675); buttons[50].setLayoutY(212);
		
		int[] indices = {0, 1, 2, 3, 4, 5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,
				50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88, 89,90,91,92,93,94,95,96, 97, 98, 99};
		
		for (int i : indices) {
		    buttons[i].setPrefSize(20,20);
		    buttons[i].setMaxSize(20, 20);
		    buttons[i].setMinSize(20, 20);
		    buttons[i].setOnAction(this);
		    root.getChildren().add(buttons[i]);
		}
		
		test = new Button();
		
		safeZoneP = new Button[4];
		safeZoneP[0] = new Button(); safeZoneP[0].setLayoutX(640); safeZoneP[0].setLayoutY(680);
		safeZoneP[1] = new Button(); safeZoneP[1].setLayoutX(640); safeZoneP[1].setLayoutY(660);
		safeZoneP[2] = new Button(); safeZoneP[2].setLayoutX(640); safeZoneP[2].setLayoutY(640);
		safeZoneP[3] = new Button(); safeZoneP[3].setLayoutX(640); safeZoneP[3].setLayoutY(620);
		
		safeZone1 = new Button[4];
		safeZone1[0] = new Button(); safeZone1[0].setLayoutX(430); safeZone1[0].setLayoutY(465);
		safeZone1[1] = new Button(); safeZone1[1].setLayoutX(450); safeZone1[1].setLayoutY(465);
		safeZone1[2] = new Button(); safeZone1[2].setLayoutX(470); safeZone1[2].setLayoutY(465);
		safeZone1[3] = new Button(); safeZone1[3].setLayoutX(490); safeZone1[3].setLayoutY(465);
		
		safeZone2 = new Button[4];
		safeZone2[0] = new Button(); safeZone2[0].setLayoutX(630); safeZone2[0].setLayoutY(233);
		safeZone2[1] = new Button(); safeZone2[1].setLayoutX(630); safeZone2[1].setLayoutY(253);
		safeZone2[2] = new Button(); safeZone2[2].setLayoutX(630); safeZone2[2].setLayoutY(273);
		safeZone2[3] = new Button(); safeZone2[3].setLayoutX(630); safeZone2[3].setLayoutY(293);
		
		safeZone3 = new Button[4];
		safeZone3[0] = new Button(); safeZone3[0].setLayoutX(850); safeZone3[0].setLayoutY(465);
		safeZone3[1] = new Button(); safeZone3[1].setLayoutX(830); safeZone3[1].setLayoutY(465);
		safeZone3[2] = new Button(); safeZone3[2].setLayoutX(810); safeZone3[2].setLayoutY(465);
		safeZone3[3] = new Button(); safeZone3[3].setLayoutX(790); safeZone3[3].setLayoutY(465);
		
		HomeP = new Button[4];
		HomeP[0] = new Button(); HomeP[0].setLayoutX(720); HomeP[0].setLayoutY(670);
		HomeP[1] = new Button(); HomeP[1].setLayoutX(750); HomeP[1].setLayoutY(670);
		HomeP[2] = new Button(); HomeP[2].setLayoutX(735); HomeP[2].setLayoutY(650);
		HomeP[3] = new Button(); HomeP[3].setLayoutX(735); HomeP[3].setLayoutY(690);
		
		Cpu1H = new Button[4];
		Cpu1H[0] = new Button(); Cpu1H[0].setLayoutX(410); Cpu1H[0].setLayoutY(570);
		Cpu1H[1] = new Button(); Cpu1H[1].setLayoutX(440); Cpu1H[1].setLayoutY(570);
		Cpu1H[2] = new Button(); Cpu1H[2].setLayoutX(425); Cpu1H[2].setLayoutY(550);
		Cpu1H[3] = new Button(); Cpu1H[3].setLayoutX(425); Cpu1H[3].setLayoutY(590);
		
		Cpu2H = new Button[4];
		Cpu2H[0] = new Button(); Cpu2H[0].setLayoutX(505); Cpu2H[0].setLayoutY(240);
		Cpu2H[1] = new Button(); Cpu2H[1].setLayoutX(535); Cpu2H[1].setLayoutY(240);
		Cpu2H[2] = new Button(); Cpu2H[2].setLayoutX(520); Cpu2H[2].setLayoutY(220);
		Cpu2H[3] = new Button(); Cpu2H[3].setLayoutX(520); Cpu2H[3].setLayoutY(260);
		
		Cpu3H = new Button[4];
		Cpu3H[0] = new Button(); Cpu3H[0].setLayoutX(830); Cpu3H[0].setLayoutY(250);
		Cpu3H[1] = new Button(); Cpu3H[1].setLayoutX(860); Cpu3H[1].setLayoutY(250);
		Cpu3H[2] = new Button(); Cpu3H[2].setLayoutX(845); Cpu3H[2].setLayoutY(230);
		Cpu3H[3] = new Button(); Cpu3H[3].setLayoutX(845); Cpu3H[3].setLayoutY(270);
		
		for (int i=0 ;i<4;i++) {
			
			safeZoneP[i].setPrefSize(20,20);
			safeZoneP[i].setMaxSize(20, 20);
			safeZoneP[i].setMinSize(20, 20);
			safeZoneP[i].setOnAction(this);
		    root.getChildren().add(safeZoneP[i]);
		    
		    safeZone1[i].setPrefSize(20,20);
			safeZone1[i].setMaxSize(20, 20);
			safeZone1[i].setMinSize(20, 20);
		    root.getChildren().add(safeZone1[i]);
		    
		    safeZone2[i].setPrefSize(20,20);
			safeZone2[i].setMaxSize(20, 20);
			safeZone2[i].setMinSize(20, 20);
		    root.getChildren().add(safeZone2[i]);
		    
		    safeZone3[i].setPrefSize(20,20);
			safeZone3[i].setMaxSize(20, 20);
			safeZone3[i].setMinSize(20, 20);
		    root.getChildren().add(safeZone3[i]);
			
			HomeP[i].setPrefSize(20,20);
			HomeP[i].setMaxSize(20, 20);
			HomeP[i].setMinSize(20, 20);
		    root.getChildren().add(HomeP[i]);
		    
			Cpu1H[i].setPrefSize(20,20);
			Cpu1H[i].setMaxSize(20, 20);
			Cpu1H[i].setMinSize(20, 20);
		    root.getChildren().add(Cpu1H[i]);
		    
		    Cpu2H[i].setPrefSize(20,20);
			Cpu2H[i].setMaxSize(20, 20);
			Cpu2H[i].setMinSize(20, 20);
		    root.getChildren().add(Cpu2H[i]);
		    

		    Cpu3H[i].setPrefSize(20,20);
			Cpu3H[i].setMaxSize(20, 20);
			Cpu3H[i].setMinSize(20, 20);
		    root.getChildren().add(Cpu3H[i]);
		}
		
		Myhand = new HBox();
		Myhand.setLayoutX(500);
		Myhand.setLayoutY(750);
		Myhand.setPrefSize(400, 100);
		root.getChildren().add(Myhand);
		
		
		
		
		Cpuhand1 = new VBox();
		Cpuhand1.setLayoutX(300);
		Cpuhand1.setLayoutY(350);
		Cpuhand1.setPrefSize(100, 200);
		root.getChildren().add(Cpuhand1);
		
		Cpuhand2 = new HBox();
		Cpuhand2.setLayoutX(500);
		Cpuhand2.setLayoutY(50);
		Cpuhand2.setPrefSize(200, 100);
		root.getChildren().add(Cpuhand2);
		
		
		Cpuhand3 = new VBox();
		Cpuhand3.setLayoutX(1000);
		Cpuhand3.setLayoutY(350);
		Cpuhand3.setPrefSize(100, 200);
		root.getChildren().add(Cpuhand3);
		
		FirePit = new Label();
		FirePit.setLayoutX(640);
		FirePit.setLayoutY(425);
		FirePit.setPrefSize(100, 100);
		root.getChildren().add(FirePit);
		
		Deck = new Label();
		Deck.setLayoutX(540);
		Deck.setLayoutY(425);
		Deck.setPrefSize(100, 100);
		root.getChildren().add(Deck);
		
		String name = "card_back.png";
		Image im = new Image(name, 100, 100, true, true);
		ImageView imageView7 = new ImageView(im);
		Deck.setGraphic(imageView7);
		
		
		PlayerImage = new Image("Player.png", 100, 100, true, true); // Resize to 40x40
		ImageView imageView = new ImageView(PlayerImage);
		Player0 = new Label(nameFld.getText(), imageView);
		Player0.setContentDisplay(ContentDisplay.TOP);
		Player0.setStyle("-fx-text-fill: white;");
		Player0.setFont(new Font("Arial",26));
		Player0.setLayoutX(1000);
		Player0.setLayoutY(750);
		Player0.setPrefSize(100, 100);
		root.getChildren().add(Player0);
		
		ImageView imageView1 = new ImageView(PlayerImage);
		Player1 = new Label(game.getPlayers().get(1).getName(), imageView1);
		Player1.setContentDisplay(ContentDisplay.TOP);
		Player1.setStyle("-fx-text-fill: white;");
		Player1.setFont(new Font("Arial",26));
		Player1.setLayoutX(200);
		Player1.setLayoutY(560);
		Player1.setPrefSize(100, 100);
		root.getChildren().add(Player1);
		 
		ImageView imageView2 = new ImageView(PlayerImage);
		Player2 = new Label(game.getPlayers().get(2).getName(), imageView2);
		Player2.setContentDisplay(ContentDisplay.TOP);
		Player2.setStyle("-fx-text-fill: white;");
		Player2.setFont(new Font("Arial",26));
		Player2.setLayoutX(300);
		Player2.setLayoutY(50);
		Player2.setPrefSize(100, 100);
		root.getChildren().add(Player2);
		
		ImageView imageView3 = new ImageView(PlayerImage);
		Player3 = new Label(game.getPlayers().get(3).getName(), imageView3);
		Player3.setContentDisplay(ContentDisplay.TOP);
		Player3.setStyle("-fx-text-fill: white;");
		Player3.setFont(new Font("Arial",26));
		Player3.setLayoutX(1000);
		Player3.setLayoutY(220);
		Player3.setPrefSize(100, 100);
		root.getChildren().add(Player3);
		
		CurPlr = new Label();
		CurPlr.setFont(new Font("Arial",26));
		CurPlr.setLayoutX(1500);
		CurPlr.setLayoutY(100);
		CurPlr.setPrefSize(300, 50);
		CurPlr.setStyle("-fx-text-fill: white;");
		root.getChildren().add(CurPlr);
		
		NxtPlr = new Label();
		NxtPlr.setFont(new Font("Arial",26));
		NxtPlr.setLayoutX(1500);
		NxtPlr.setLayoutY(200);
		NxtPlr.setPrefSize(300, 50);
		NxtPlr.setStyle("-fx-text-fill: white;");
		root.getChildren().add(NxtPlr);
		
		Deselect = new Button("Deselect All");
		Deselect.setLayoutX(1500);
		Deselect.setLayoutY(350);
		Deselect.setPrefSize(200, 100);
		Deselect.setOnAction(this);
		root.getChildren().add(Deselect);
		
		CantPlay = new Button("Cant Play");
		CantPlay.setLayoutX(1200);
		CantPlay.setLayoutY(450);
		CantPlay.setPrefSize(200, 50);
		CantPlay.setOnAction(this);
		root.getChildren().add(CantPlay);
		
		NextPlay = new Button("NextPlay");
		NextPlay.setLayoutX(1200);
		NextPlay.setLayoutY(550);
		NextPlay.setPrefSize(200, 50);
		NextPlay.setOnAction(this);
		root.getChildren().add(NextPlay);
		
		PlayBt = new Button("Play");
		PlayBt.setLayoutX(1500);
		PlayBt.setLayoutY(500);
		PlayBt.setPrefSize(200, 100);
		PlayBt.setOnAction(this);
		root.getChildren().add(PlayBt);
		
		CardDesc = new Label();
		CardDesc.setFont(new Font("Arial",20));
		CardDesc.setLayoutX(1300);
		CardDesc.setLayoutY(700);
		CardDesc.setPrefSize(500, 300);
		CardDesc.setWrapText(true);
		CardDesc.setAlignment(Pos.TOP_LEFT);
		CardDesc.setStyle("-fx-background-color: white;");
		root.getChildren().add(CardDesc);
		
		/*
		root.setOnKeyPressed(e -> {
	        if (e.getCode() == KeyCode.F) {
	        	try {
	        		if(game.getCurrentPlayerIndex()==0){
						game.fieldMarble();
						Player player = game.getPlayers().get(0);
						if(player.getSelectedCard()==null)
							player.selectCard(player.getHand().get((int)(Math.random()*(player.getHand().size()))));
						game.endPlayerTurn();
						UpdateEachPlay();
						PauseTransition initialDelay = new PauseTransition(Duration.seconds(2));
					    initialDelay.setOnFinished(ev -> startNextPlaySequence(3)); // Start the sequence
					    initialDelay.play();
	        		}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					displayAlert(e1.getMessage());
				}
	            
	        }
	    });
		
		root.requestFocus();
		
		root.setOnKeyPressed(e -> {
	        if (e.getCode() == KeyCode.S) {
	        	try {
	        		if(game.getCurrentPlayerIndex()==0){
						game.getBoard().sendToSafe(game.getPlayers().get(0).getSelectedMarbles().get(0));
						Player player = game.getPlayers().get(0);
						if(player.getSelectedCard()==null)
							player.selectCard(player.getHand().get((int)(Math.random()*(player.getHand().size()))));
						game.endPlayerTurn();
						UpdateEachPlay();
						PauseTransition initialDelay = new PauseTransition(Duration.seconds(2));
					    initialDelay.setOnFinished(ev -> startNextPlaySequence(3)); // Start the sequence
					    initialDelay.play();
	        		}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					displayAlert(e1.getMessage());
				}
	            
	        }
	    });

	    root.requestFocus();
	    */
		
		root.setOnKeyPressed(event -> {
		    switch (event.getCode()) {
		        case F:
		        	try {
		        		if(game.getCurrentPlayerIndex()==0){
							game.fieldMarble();
							Player player = game.getPlayers().get(0);
							if(player.getSelectedCard()==null)
								player.selectCard(player.getHand().get((int)(Math.random()*(player.getHand().size()))));
							game.endPlayerTurn();
							UpdateEachPlay();
							PauseTransition initialDelay = new PauseTransition(Duration.seconds(2));
						    initialDelay.setOnFinished(ev -> startNextPlaySequence(3)); // Start the sequence
						    initialDelay.play();
		        		}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						displayAlert(e1.getMessage());
					}
		            break;
		        case S:
		        	try {
		        		if(game.getCurrentPlayerIndex()==0){
							game.getBoard().sendToSafe(game.getPlayers().get(0).getSelectedMarbles().get(0));
							Player player = game.getPlayers().get(0);
							if(player.getSelectedCard()==null)
								player.selectCard(player.getHand().get((int)(Math.random()*(player.getHand().size()))));
							game.endPlayerTurn();
							UpdateEachPlay();
							if(game.checkWin()==null){
								PauseTransition initialDelay = new PauseTransition(Duration.seconds(2));
							    initialDelay.setOnFinished(ev -> startNextPlaySequence(3)); // Start the sequence
							    initialDelay.play();
							}
		        		}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						displayAlert(e1.getMessage());
					}
		            break;
		        case L:
		        	try {
		        		if(game.getCurrentPlayerIndex()==0){
							game.getBoard().sendToSafe(game.getPlayers().get(0).getSelectedMarbles().get(0));
							Player player = game.getPlayers().get(game.getCurrentPlayerIndex());
							if(player.getSelectedCard()==null)
								player.selectCard(player.getHand().get((int)(Math.random()*(player.getHand().size()))));
							game.endPlayerTurn();
							UpdateEachPlay();
							if(game.checkWin()==null){
								PauseTransition initialDelay = new PauseTransition(Duration.seconds(2));
							    initialDelay.setOnFinished(ev -> startNextPlaySequence(3)); // Start the sequence
							    initialDelay.play();
							}
		        		}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						displayAlert(e1.getMessage());
					}
		            break;
		        default:
		            break;
		    }
		});
		root.requestFocus();
		UpdateEachPlay();
		
	}
	
	private int playerHasAceOrKing() {
		for(int i=0;i<game.getPlayers().get(0).getHand().size();i++)
			if(game.getPlayers().get(0).getHand().get(i).getName().equals("Ace") || game.getPlayers().get(0).getHand().get(i).getName().equals("King"))
				return i;
		return -1;
	}

	public void UpdateEachPlay(){
		for(int i=0;i<100;i++){
			String imageName = getpng(game.getBoard().getTrack().get(i).getMarble());
			Image im = new Image(imageName, 20, 20, true, true);
			ImageView imageView1 = new ImageView(im);
			buttons[i].setGraphic(imageView1);
		}
		
		for(int i=0;i<4;i++){
			String imageName = getpng(null);
			Image im = new Image(imageName, 20, 20, true, true);
			ImageView imageView1 = new ImageView(im);
			HomeP[i].setGraphic(imageView1);
		}
			
		for(int i=0;i<game.getPlayers().get(0).getMarbles().size();i++){
			String imageName = getpng(game.getPlayers().get(0).getMarbles().get(i));
			Image im = new Image(imageName, 20, 20, true, true);
			ImageView imageView1 = new ImageView(im);
			HomeP[i].setGraphic(imageView1);
		}
		
		for(int i=0;i<4;i++){
			String imageName = getpng(null);
			Image im = new Image(imageName, 20, 20, true, true);
			ImageView imageView1 = new ImageView(im);
			Cpu1H[i].setGraphic(imageView1);
		}
		
		
		for(int i=0;i<game.getPlayers().get(1).getMarbles().size();i++){
			String imageName = getpng(game.getPlayers().get(1).getMarbles().get(i));
			Image im = new Image(imageName, 20, 20, true, true);
			ImageView imageView1 = new ImageView(im);
			Cpu1H[i].setGraphic(imageView1);
		}
		
		for(int i=0;i<4;i++){
			String imageName = getpng(null);
			Image im = new Image(imageName, 20, 20, true, true);
			ImageView imageView1 = new ImageView(im);
			Cpu2H[i].setGraphic(imageView1);
		}
		for(int i=0;i<game.getPlayers().get(2).getMarbles().size();i++){
			String imageName = getpng(game.getPlayers().get(2).getMarbles().get(i));
			Image im = new Image(imageName, 20, 20, true, true);
			ImageView imageView1 = new ImageView(im);
			Cpu2H[i].setGraphic(imageView1);
		}
		
		for(int i=0;i<4;i++){
			String imageName = getpng(null);
			Image im = new Image(imageName, 20, 20, true, true);
			ImageView imageView1 = new ImageView(im);
			Cpu3H[i].setGraphic(imageView1);
		}
		for(int i=0;i<game.getPlayers().get(3).getMarbles().size();i++){
			String imageName = getpng(game.getPlayers().get(3).getMarbles().get(i));
			Image im = new Image(imageName, 20, 20, true, true);
			ImageView imageView1 = new ImageView(im);
			Cpu3H[i].setGraphic(imageView1);
		}
		
		for(int i=0;i<4;i++){
			String imageName = getpng(game.getBoard().getSafeZones().get(0).getCells().get(i).getMarble());
			Image im = new Image(imageName, 20, 20, true, true);
			ImageView imageView1 = new ImageView(im);
			safeZoneP[i].setGraphic(imageView1);
		}
		for(int i=0;i<4;i++){
			String imageName = getpng(game.getBoard().getSafeZones().get(1).getCells().get(i).getMarble());
			Image im = new Image(imageName, 20, 20, true, true);
			ImageView imageView1 = new ImageView(im);
			safeZone1[i].setGraphic(imageView1);
		}
		for(int i=0;i<4;i++){
			String imageName = getpng(game.getBoard().getSafeZones().get(2).getCells().get(i).getMarble());
			Image im = new Image(imageName, 20, 20, true, true);
			ImageView imageView1 = new ImageView(im);
			safeZone2[i].setGraphic(imageView1);
		}
		for(int i=0;i<4;i++){
			String imageName = getpng(game.getBoard().getSafeZones().get(3).getCells().get(i).getMarble());
			Image im = new Image(imageName, 20, 20, true, true);
			ImageView imageView1 = new ImageView(im);
			safeZone3[i].setGraphic(imageView1);
		}
		int count;
		Image image = new Image("card_back.png", 100, 80, true, true);
		Cpuhand1.getChildren().clear();
		Cpuhand1.setSpacing(10); // Optional spacing between images
		count = game.getPlayers().get(1).getHand().size(); // 0 to 4
		for (int i = 0; i < count; i++) {
		    ImageView imageView = new ImageView(image);
		    Cpuhand1.getChildren().add(imageView);
		}
		
		Cpuhand2.getChildren().clear();
		Cpuhand2.setSpacing(10); // Optional spacing between images
		count = game.getPlayers().get(2).getHand().size(); // 0 to 4
		for (int i = 0; i < count; i++) {
		    ImageView imageView = new ImageView(image);
		    Cpuhand2.getChildren().add(imageView);
		}
		
		Cpuhand3.getChildren().clear();
		Cpuhand3.setSpacing(10); // Optional spacing between images
		count = game.getPlayers().get(3).getHand().size(); // 0 to 4
		for (int i = 0; i < count; i++) {
		    ImageView imageView = new ImageView(image);
		    Cpuhand3.getChildren().add(imageView);
		}
		
		Myhand.getChildren().clear();
		Myhand.setSpacing(10);
		 
		
		count = game.getPlayers().get(0).getHand().size(); // 0 to 4
		MyCards = new ArrayList<Button>();
		Myhand.getChildren().clear();
		for (int i = 0; i < count; i++) {
			String cardimagename = getCardName(game.getPlayers().get(0).getHand().get(i));
			Image cardimage = new Image(cardimagename, 100, 120, true, true);
		    ImageView imageView = new ImageView(cardimage);
		    Button b = new Button();
		    b.setGraphic(imageView);
		    b.setOnAction(this);
		    MyCards.add(b);
		    Myhand.getChildren().add(b);
		}
		
		if(game.getFirePit().size()>0){
			if(game.getFirePit().get(game.getFirePit().size()-1)!= null){
			String name = getCardName(game.getFirePit().get(game.getFirePit().size()-1));
			Image im = new Image(name, 100, 100, true, true);
			ImageView imageView1 = new ImageView(im);
			FirePit.setGraphic(imageView1);
			}
		}
		if(!game.canPlayTurn()){
			
			game.endPlayerTurn();
			if(game.getCurrentPlayerIndex()==1){
				if(game.checkWin()==null){
					PauseTransition initialDelay = new PauseTransition(Duration.seconds(2));
					initialDelay.setOnFinished(ev -> startNextPlaySequence(3)); // Start the sequence
					initialDelay.play();
				}
			}
		}
		
		CurPlr.setText("Current player: " + game.getPlayers().get(game.getCurrentPlayerIndex()).getName());
		NxtPlr.setText("Next player: " + game.getPlayers().get((game.getCurrentPlayerIndex()+1)%4).getName());
		
		
		
		if(game.checkWin()!= null){
			if(game.checkWin() == game.getPlayers().get(0).getColour())
				WinSc();
			else
				LoseSc();
		}
		
		trapAfter = getTrapIndices();
		for (int i = 0; i < trapBefore.size(); i++) {
		    int value = trapBefore.get(i);
		    if (!trapAfter.contains(value)) {
		        TrapSc(value);

		        break; // only one change max
		    }
		}
		trapBefore.clear();
		trapBefore.addAll(trapAfter);
		
		String text;
		if(game.getPlayers().get(0).getSelectedCard() == null){
			text = "No selected card";
		}
		else{
			Card c = game.getPlayers().get(0).getSelectedCard();
			if(c instanceof Standard){
				Standard s = (Standard) c;
				text = "Name: "+ s.getName()+ "\n"+
					   "Description: " + s.getDescription()+"\n"+
					   "Rank: "+ s.getRank()+ "\n"+
					   "Suit: " + s.getSuit();
			}
			else{
				Wild w = (Wild) c;
				text = "Name: "+ w.getName()+ "\n"+
					   "Description: " + w.getDescription();
			}
				
		}
		CardDesc.setText(text);
		
			
	}
	
	
	private void TrapSc(int i) {
		// 1. Load the trap image
	    Image trapImage = new Image("trap.png", 20, 20, true, true);
	    ImageView trapView = new ImageView(trapImage);

	    // 2. Set it on the button
	    buttons[i].setGraphic(trapView);

	    // 3. Create a simple scale animation
	    ScaleTransition st = new ScaleTransition(Duration.seconds(0.5), trapView);
	    st.setFromX(0.1);
	    st.setFromY(0.1);
	    st.setToX(1.2);
	    st.setToY(1.2);
	    st.setAutoReverse(true);
	    st.setCycleCount(2); // Scale up and back down

	    // Optional: fade animation
	    FadeTransition ft = new FadeTransition(Duration.seconds(1), trapView);
	    ft.setFromValue(0.0);
	    ft.setToValue(1.0);

	    // Play both together
	    ParallelTransition pt = new ParallelTransition(st, ft);
	    pt.play();
		
	}

	private void LoseSc() {
		// Path to your video file (replace with actual path)
		try {
			java.net.URL videoUrl = getClass().getResource("/Rick.mp4");
	        if (videoUrl == null) {
	            System.out.println("Video file not found in resources.");
	            return;
	        }
			
				
				
			Media media = new Media(videoUrl.toExternalForm());
			MediaPlayer mediaPlayer = new MediaPlayer(media);
			MediaView mediaView = new MediaView(mediaPlayer);
			mediaView.setFitWidth(1000);
			mediaView.setFitHeight(900);

			StackPane root = new StackPane(mediaView);
			Scene scene = new Scene(root, 1000, 900);

			Stage popupStage = new Stage();
			popupStage.initModality(Modality.APPLICATION_MODAL); // blocks other windows
			popupStage.setTitle("You lost but the winner is " + game.checkWin());
			popupStage.setScene(scene);
			popupStage.show();

			mediaPlayer.play();

			    // Optionally close the popup when video ends
			mediaPlayer.setOnEndOfMedia(() -> popupStage.close());
			
		}
		catch (Exception e) {
	        e.printStackTrace();
	    }
		
	}

	public void WinSc() {
	    // Path to your video file (replace with actual path)
		java.net.URL videoUrl = getClass().getResource("/RonaldoSUI.mp4");
		
		if (videoUrl == null) {
		    
		    return;
		}
		Media media = new Media(videoUrl.toExternalForm());
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		MediaView mediaView = new MediaView(mediaPlayer);
	    mediaView.setFitWidth(1000);
	    mediaView.setFitHeight(900);

	    StackPane root = new StackPane(mediaView);
	    Scene scene = new Scene(root, 1000, 900);

	    Stage popupStage = new Stage();
	    popupStage.initModality(Modality.APPLICATION_MODAL); // blocks other windows
	    popupStage.setTitle("The Winner is " + game.getPlayers().get(0).getColour());
	    popupStage.setScene(scene);
	    popupStage.show();

	    mediaPlayer.play();

	    // Optionally close the popup when video ends
	    mediaPlayer.setOnEndOfMedia(() -> popupStage.close());
	    
	}
	
	public ArrayList<Cell> getMySafeZone() {
	    Colour myColour = game.getPlayers().get(0).getColour();  // Assuming index 0 is the human player
	    for (SafeZone zone : game.getBoard().getSafeZones()) {
	        if (zone.getColour() == myColour) {
	            return zone.getCells();  // This is your safe zone
	        }
	    }
	    return null;  // Not found (shouldn't happen unless data is broken)
	}

	@Override
	public void handle(ActionEvent e) {
		if(e.getSource() == play){
			if (!nameFld.getText().equals(""))
				gameScene();
			else{
				displayAlert("You have to write your name");
			}	
		}
		if(e.getSource()==Deselect)
			game.deselectAll();
		
		for(int i=0;i<buttons.length;i++){
			if(e.getSource()==buttons[i]){
				Player Human = game.getPlayers().get(0);
				
				try {
					Human.selectMarble(game.getBoard().getTrack().get(i).getMarble());
				} catch (InvalidMarbleException e1) {
					// TODO Auto-generated catch block
					displayAlert(e1.getMessage());
				}
				
			}
		}
		
		for(int i=0;i<safeZoneP.length;i++){
			if(e.getSource()==safeZoneP[i]){
				Player Human = game.getPlayers().get(0);
				
				try {
					Human.selectMarble(getMySafeZone().get(i).getMarble());
				} catch (InvalidMarbleException e1) {
					// TODO Auto-generated catch block
					displayAlert(e1.getMessage());
				}
				
			}
		}
		
		for(int i=0;i<MyCards.size();i++){
			if (game.canPlayTurn()){
			if(e.getSource() == MyCards.get(i) && game.getCurrentPlayerIndex() ==0){
				try {
					game.getPlayers().get(0).selectCard(game.getPlayers().get(0).getHand().get(i));
					if (game.getPlayers().get(0).getSelectedCard().getName().equals("Seven") &&game.getPlayers().get(0).getSelectedMarbles().size()>1){
						selectSplit();
						
						System.out.println(game.getBoard().getSplitDistance());
						
					}
							
						
				}catch (GameException e1) {
					// TODO Auto-generated catch block
					displayAlert(e1.getMessage());
				}
			}
			}
		}
		if(e.getSource() == CantPlay && game.getCurrentPlayerIndex() ==0){
			if(game.getPlayers().get(0).getSelectedCard()==null && game.getPlayers().get(0).getHand().size()>0)
				try {
					Player player = game.getPlayers().get(0);
					if(player.getSelectedCard()==null)
						player.selectCard(player.getHand().get((int)(Math.random()*(player.getHand().size()))));
				} 
				catch (InvalidCardException e1) {
					// TODO Auto-generated catch block
					displayAlert(e1.getMessage());
				};
			game.endPlayerTurn();
			if(game.checkWin()==null){
				PauseTransition initialDelay = new PauseTransition(Duration.seconds(2));
				initialDelay.setOnFinished(ev -> startNextPlaySequence(3)); // Start the sequence
				initialDelay.play();
			}
			
		}
		
		if(e.getSource() == NextPlay ){
			if(game.getCurrentPlayerIndex()!=0){
				boolean played = false;
				if (game.canPlayTurn()){
					try {
					
						game.playPlayerTurn();
						trapAfter = getTrapIndices();
					} catch (GameException e1) {
						// TODO Auto-generated catch block
						displayAlert(e1.getMessage());
					}
					
				}
				
			
			game.endPlayerTurn();
			}
			
			
		}
		
		if(e.getSource() == PlayBt && game.getCurrentPlayerIndex() ==0 ){
			boolean played = false;
			if (game.canPlayTurn())
				try {
					game.playPlayerTurn();
					trapAfter = getTrapIndices();
					played = true;
				} catch (GameException e1) {
					// TODO Auto-generated catch block
					displayAlert(e1.getMessage());
				}
			if(played){
				game.endPlayerTurn();
				if(game.checkWin()==null){
					PauseTransition initialDelay = new PauseTransition(Duration.seconds(2));
					initialDelay.setOnFinished(ev -> startNextPlaySequence(3)); // Start the sequence
					initialDelay.play();
				}
			}
			
		
		}
		
		
		UpdateEachPlay();
		
	}
	
	private void startNextPlaySequence(int times) {
	    if (times <= 0) return; // Base case: stop after 3 times

	    // Press the nextPlayButton
	    NextPlay.fire();
	    
	    if(game.checkWin()!= null){
			if(game.checkWin() == game.getPlayers().get(0).getColour())
				WinSc();
			else
				LoseSc();
			return;
		}

	    // Wait 2 seconds before the next press
	    PauseTransition delay = new PauseTransition(Duration.seconds(2));
	    delay.setOnFinished(e -> startNextPlaySequence(times - 1));
	    delay.play();
	}

	private void selectSplit(){
		AnchorPane root = new AnchorPane();
	    Scene scene = new Scene(root, 400, 500);

	    // Create ToggleGroup for RadioButtons
	    ToggleGroup group = new ToggleGroup();

	    // Create and position radio buttons for values 1 to 6
	    VBox radioBox = new VBox(10); // Vertical layout for buttons
	    radioBox.setLayoutX(100);
	    radioBox.setLayoutY(100);

	    for (int i = 1; i <= 6; i++) {
	        RadioButton rb = new RadioButton("Split distance " + i);
	        rb.setUserData(i); // Store numeric value
	        rb.setToggleGroup(group);
	        radioBox.getChildren().add(rb);
	    }

	    root.getChildren().add(radioBox);

	    // Confirm button
	    backButton = new Button("Confirm");
	    backButton.setLayoutX(100);
	    backButton.setLayoutY(400);
	    backButton.setPrefSize(200, 40);
	    root.getChildren().add(backButton);

	    // Stage setup
	    Stage splitStage = new Stage();
	    splitStage.setTitle("Select the split distance!");
	    splitStage.setScene(scene);
	    splitStage.show();

	    // Confirm button action
	    backButton.setOnAction(e -> {
	        if (group.getSelectedToggle() != null) {
	            int selectedValue = (int) group.getSelectedToggle().getUserData();
	            try {
					game.editSplitDistance(selectedValue);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					displayAlert(e1.getMessage());
				}
	            splitStage.close();
	            // You can store it in a field or use it as needed here
	        } else {
	        	displayAlert("You have to choose an option");
	        }

	        
	    });
	}
	
	private void displayAlert(String message) {
        Stage alertStage = new Stage();
        alertStage.setTitle("Error");

        Label label = new Label(message);
        Button closeButton = new Button("Continue");
        closeButton.setOnAction(event -> alertStage.close());

        BorderPane pane = new BorderPane();
        pane.setTop(label);
        pane.setCenter(closeButton);

        Scene scene = new Scene(pane, 500, 100);
        
        alertStage.setScene(scene);
        alertStage.show();
    }
	
	public String getpng(Marble m){
		if(m==null)
			return "White.png";
		else{
			if(m.getColour() == Colour.BLUE)
				return "Blue.png";
			else{
				if(m.getColour() == Colour.RED)
					return "Red.png";
				else{
					if(m.getColour() == Colour.GREEN)
						return "Green.png";
					else
						return "Yellow.png";
				}
			}
		}
		
	}
	
	public String getCardName(Card card){
		if(card != null){
			if(card instanceof Standard){
				Standard c = (Standard)card; 
			    int rank = c.getRank();
			    Suit suit = c.getSuit();
	
			    if (rank == 1 && suit == Suit.SPADE) return "1Spade.png";
			    if (rank == 2 && suit == Suit.SPADE) return "2Spade.png";
			    if (rank == 3 && suit == Suit.SPADE) return "3Spade.png";
			    if (rank == 4 && suit == Suit.SPADE) return "4Spade.png";
			    if (rank == 5 && suit == Suit.SPADE) return "5Spade.png";
			    if (rank == 6 && suit == Suit.SPADE) return "6Spade.png";
			    if (rank == 7 && suit == Suit.SPADE) return "7Spade.png";
			    if (rank == 8 && suit == Suit.SPADE) return "8Spade.png";
			    if (rank == 9 && suit == Suit.SPADE) return "9Spade.png";
			    if (rank == 10 && suit == Suit.SPADE) return "10Spade.png";
			    if (rank == 11 && suit == Suit.SPADE) return "11Spade.png";
			    if (rank == 12 && suit == Suit.SPADE) return "12Spade.png";
			    if (rank == 13 && suit == Suit.SPADE) return "13Spade.png";
	
			    if (rank == 1 && suit == Suit.HEART) return "1Heart.png";
			    if (rank == 2 && suit == Suit.HEART) return "2Heart.png";
			    if (rank == 3 && suit == Suit.HEART) return "3Heart.png";
			    if (rank == 4 && suit == Suit.HEART) return "4Heart.png";
			    if (rank == 5 && suit == Suit.HEART) return "5Heart.png";
			    if (rank == 6 && suit == Suit.HEART) return "6Heart.png";
			    if (rank == 7 && suit == Suit.HEART) return "7Heart.png";
			    if (rank == 8 && suit == Suit.HEART) return "8Heart.png";
			    if (rank == 9 && suit == Suit.HEART) return "9Heart.png";
			    if (rank == 10 && suit == Suit.HEART) return "10Heart.png";
			    if (rank == 11 && suit == Suit.HEART) return "11Heart.png";
			    if (rank == 12 && suit == Suit.HEART) return "12Heart.png";
			    if (rank == 13 && suit == Suit.HEART) return "13Heart.png";
	
			    if (rank == 1 && suit == Suit.DIAMOND) return "1Dia.png";
			    if (rank == 2 && suit == Suit.DIAMOND) return "2Dia.png";
			    if (rank == 3 && suit == Suit.DIAMOND) return "3Dia.png";
			    if (rank == 4 && suit == Suit.DIAMOND) return "4Dia.png";
			    if (rank == 5 && suit == Suit.DIAMOND) return "5Dia.png";
			    if (rank == 6 && suit == Suit.DIAMOND) return "6Dia.png";
			    if (rank == 7 && suit == Suit.DIAMOND) return "7Dia.png";
			    if (rank == 8 && suit == Suit.DIAMOND) return "8Dia.png";
			    if (rank == 9 && suit == Suit.DIAMOND) return "9Dia.png";
			    if (rank == 10 && suit == Suit.DIAMOND) return "10Dia.png";
			    if (rank == 11 && suit == Suit.DIAMOND) return "11Dia.png";
			    if (rank == 12 && suit == Suit.DIAMOND) return "12Dia.png";
			    if (rank == 13 && suit == Suit.DIAMOND) return "13Dia.png";
	
			    if (rank == 1 && suit == Suit.CLUB) return "1Club.png";
			    if (rank == 2 && suit == Suit.CLUB) return "2Club.png";
			    if (rank == 3 && suit == Suit.CLUB) return "3Club.png";
			    if (rank == 4 && suit == Suit.CLUB) return "4Club.png";
			    if (rank == 5 && suit == Suit.CLUB) return "5Club.png";
			    if (rank == 6 && suit == Suit.CLUB) return "6Club.png";
			    if (rank == 7 && suit == Suit.CLUB) return "7Club.png";
			    if (rank == 8 && suit == Suit.CLUB) return "8Club.png";
			    if (rank == 9 && suit == Suit.CLUB) return "9Club.png";
			    if (rank == 10 && suit == Suit.CLUB) return "10Club.png";
			    if (rank == 11 && suit == Suit.CLUB) return "11Club.png";
			    if (rank == 12 && suit == Suit.CLUB) return "12Club.png";
			    if (rank == 13 && suit == Suit.CLUB) return "13Club.png";
			
			    
			    }
			else
				if (card.getName().equals("MarbleBurner"))
					return "Burner.png";
				
			return"Saver.png";
			
		}
		return "card_back.png";
	}
	
	
	
	
	public static void main(String[] args){
		launch(args);
	}
	
	

}
