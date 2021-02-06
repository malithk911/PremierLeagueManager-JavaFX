/*
 * Name : Malith Kulathilake
 * IIT number : 2018412
 * UOW number : w1761910
 *
 * I confirm that I understand what plagiarism /
    collusion / contract cheating is and have read and
    understood the section on Assessment Offences in the
    Essential Information for Students. The work that I
    have submitted is entirely my own. Any work from
    other authors is duly referenced and acknowledged.
 */

package level5.cw.implementation;

// imports
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

// Main class containing main() method for the program
public class Main extends Application {
    protected static Stage window;
    protected static Scene leagueTableScene;
    protected static Scene matchesScene;
    protected static Scene menuScene;
    protected static ListView<Object> matchListView;
    protected static TableView<FootballClub> leagueTable;
    protected static Button consoleButton;
    protected static Button sortWinsButton;
    protected static Button sortGoalsButton;
    protected static Button randomMatchButton;
    protected static Button backButton;
    protected static Button sortMatchesButton;
    protected static TextField userInput;
    protected static Button searchButton;
    protected static Label tableViewHeading;
    protected static Label matchListHeading;
    protected static Label menuHeading;
    protected static Button tableButton;
    protected static Button matchesButton;
    protected static Button exitMenuButton;
    protected static int plYear;

    /**
     * main method to run the console application
     * @param args main method
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        obtainLeagueYear();
        launch(args);
    }

    /**
     * @param primaryStage primary stage of application
     * @throws Exception handle exceptions
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        // primary stage
        window = primaryStage;
        window.setTitle("Football Premiere League Table");
        Menu.runMenu(); // run menu system in console

/* ----------------------------------------------- table columns ---------------------------------------------------- */

        // Club name column
        TableColumn<FootballClub, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(150);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("clubName"));
        // Club location column
        TableColumn<FootballClub, String> locationColumn = new TableColumn<>("Country");
        locationColumn.setMinWidth(150);
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("clubCountry"));
        // Club matches column
        TableColumn<FootballClub, Integer> matchesColumn = new TableColumn<>("Matches");
        matchesColumn.setMinWidth(120);
        matchesColumn.setCellValueFactory(new PropertyValueFactory<>("numOfMatches"));
        // Goals scored column
        TableColumn<FootballClub, Integer> goalScoreColumn = new TableColumn<>("GS");
        goalScoreColumn.setMinWidth(120);
        goalScoreColumn.setCellValueFactory(new PropertyValueFactory<>("numOfGoalsScored"));
        // Goals conceded column
        TableColumn<FootballClub, Integer> goalConcededColumn = new TableColumn<>("GC");
        goalConcededColumn.setMinWidth(120);
        goalConcededColumn.setCellValueFactory(new PropertyValueFactory<>("numOfGoalsConceded"));
        // Club matches column
        TableColumn<FootballClub, Integer> winsColumn = new TableColumn<>("Wins");
        winsColumn.setMinWidth(120);
        winsColumn.setCellValueFactory(new PropertyValueFactory<>("numOfWins"));
        // Club matches column
        TableColumn<FootballClub, Integer> drawsColumn = new TableColumn<>("Draws");
        drawsColumn.setMinWidth(120);
        drawsColumn.setCellValueFactory(new PropertyValueFactory<>("numOfDraws"));
        // Club matches column
        TableColumn<FootballClub, Integer> defeatsColumn = new TableColumn<>("Defeats");
        defeatsColumn.setMinWidth(120);
        defeatsColumn.setCellValueFactory(new PropertyValueFactory<>("numOfDefeats"));
        // Club points column
        TableColumn<FootballClub, Integer> pointsColumn = new TableColumn<>("Points");
        pointsColumn.setMinWidth(120);
        pointsColumn.setCellValueFactory(new PropertyValueFactory<>("numOfPoints"));

/* ----------------------------------------------- table view ------------------------------------------------------- */

        // table view for league table
        leagueTable = new TableView<>();
        leagueTable.setItems(getFootballClubs());

        // add column attributes
        leagueTable.getColumns().add(nameColumn);
        leagueTable.getColumns().add(locationColumn);
        leagueTable.getColumns().add(matchesColumn);
        leagueTable.getColumns().add(goalScoreColumn);
        leagueTable.getColumns().add(goalConcededColumn);
        leagueTable.getColumns().add(winsColumn);
        leagueTable.getColumns().add(drawsColumn);
        leagueTable.getColumns().add(defeatsColumn);
        leagueTable.getColumns().add(pointsColumn);

        leagueTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        leagueTable.setPadding(new Insets(0, 200, 0, 200)); // set padding

        tableViewHeading = new Label("⚽   Premiere League Table");
        matchListHeading = new Label("⚽   Premiere League Matches");

        tableViewHeading.setId("tableViewHeading");
        matchListHeading.setId("matchListHeading");

        tableViewHeading.setAlignment(Pos.TOP_CENTER);
        matchListHeading.setAlignment(Pos.TOP_CENTER);

        // VBox for scene 1
        VBox vBox = new VBox();
        vBox.getChildren().add(tableViewHeading);
        vBox.getChildren().addAll(leagueTable);
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.CENTER);

        // HBox for scene 1
        HBox hBox = new HBox();
        hBox.setSpacing(5);
        vBox.getChildren().add(hBox);

        // console button
        consoleButton = new Button("⟰ Menu"); // create button
        hBox.getChildren().addAll(consoleButton);
        consoleButton.setTranslateX(565);

        // lambda function for consoleButton
        consoleButton.setOnAction(event -> {
            leagueTable.getItems().clear();
            leagueTable.setItems(getFootballClubs()); // update league table when button clicked
            window.setScene(menuScene);
        });

        // sort by wins button
        sortWinsButton = new Button("▼ Sort by Wins"); // create button
        hBox.getChildren().addAll(sortWinsButton);
        sortWinsButton.setTranslateX(565);

        // lambda function for sort by wins button
        sortWinsButton.setOnAction(event -> leagueTable.setItems(getSortByWins()));

        // sort by goals button
        sortGoalsButton = new Button("▼ Sort by Goals"); // create button
        hBox.getChildren().addAll(sortGoalsButton);
        sortGoalsButton.setTranslateX(565);

        // lambda function for sort by goals button
        sortGoalsButton.setOnAction(event -> leagueTable.setItems(getSortByGoals()));

/* -------------------------------------------- back button --------------------------------------------------------- */

        // back button
        backButton = new Button(" ◄ League Table");
        backButton.setTranslateX(400);

        // lambda function for back button
        backButton.setOnAction(event -> {
            window.setScene(leagueTableScene);
            window.setTitle("Football Premiere League Table");
        });

/* ------------------------------------------- sort matches --------------------------------------------------------- */

        // sort matches button
        sortMatchesButton = new Button("▼ Sort Matches");
        sortMatchesButton.setTranslateX(395);

        sortMatchesButton.setOnAction(event -> {
            matchListView.getItems().clear();
            Match.sortMatches();

            for (Match item: LeagueManager.LEAGUE_MATCHES) {

                String date = ("════\t Date : " + item.getDate().getDay() + "." + item.getDate().getMonth()
                        + "." + item.getDate().getYear() + "\t════");
                matchListView.getItems().addAll(date + "\n\n\n⚽  " + item.getHomeClubName() + "\t\t" +
                item.getHomeClubScore() + "  :  " + item.getVisitingClubScore() + "\t\t" +
                item.getVisitingClubName() + "  ⚽");
            }
        });

/* ------------------------------------------- random match --------------------------------------------------------- */

        // fix random match button
        randomMatchButton = new Button("⇔ Fix Match"); // create button
        randomMatchButton.setTranslateX(390);

        randomMatchButton.setOnAction(event -> {
            PremiereLeagueManager plm = new PremiereLeagueManager();
            plm.setLeagueYear(Menu.premiereLeagueYear);
            Match match = plm.generateRandomMatch();
            plm.addPlayedMatch(match);
        });

/* ------------------------------------------- search match --------------------------------------------------------- */

        // user input text field to get date input
        userInput = new TextField();
        userInput.setPromptText("DD.MM.YYYY");

        // search button to search matches by date
        searchButton = new Button("Search \uD83D\uDD0D");

        // lambda function for search button
        searchButton.setOnAction(event -> {
            matchListView.getItems().clear();

            try{
                if(!userInput.getText().isEmpty()){

                    String tempDate = userInput.getText();
                    String [] arr = tempDate.split("\\.", 3);

                    int day = Integer.parseInt(arr[0]);
                    int month = Integer.parseInt(arr[1]);
                    int year = Integer.parseInt(arr[2]);

                    Date tempDateObject = new Date(day, month, year);

                    for(Match item : LeagueManager.LEAGUE_MATCHES){

                        String date = ("════\t Date : " + item.getDate().getDay() + "." + item.getDate().getMonth()
                                + "." + item.getDate().getYear() + "\t   ════");

                        if(item.getDate().equals(tempDateObject)){

                            matchListView.getItems().addAll( date + "\n\n\n⚽  " + item.getHomeClubName() +
                                    "\t\t" + item.getHomeClubScore() + "  :  " + item.getVisitingClubScore() + "\t\t" +
                                    item.getVisitingClubName() + "  ⚽");
                        }
                    }
                }
            } catch (Exception e){
                System.out.println("\nError : Enter valid input");
            }
        });

/* ---------------------------------------------- Menu scene -------------------------------------------------------- */

        VBox vBox3 = new VBox();
        vBox3.setSpacing(10);
        vBox3.setAlignment(Pos.TOP_CENTER);
        vBox3.setId("menu-Vbox");

        menuHeading = new Label("⚽   Premiere League Manager");
        menuHeading.setId("menuHeading");

        vBox3.getChildren().add(menuHeading);
        menuHeading.setAlignment(Pos.CENTER);

        // table button
        tableButton = new Button("⯁  League Table");
        vBox3.getChildren().add(tableButton);
        tableButton.setTranslateY(100);
        tableButton.setId("tableButton");

        // matches button
        matchesButton = new Button("⯁  Match List");
        vBox3.getChildren().add(matchesButton);
        matchesButton.setTranslateY(120);
        matchesButton.setId("matchesButton");

        // exit button
        exitMenuButton = new Button("⯁  Quit");
        vBox3.getChildren().add(exitMenuButton);
        exitMenuButton.setTranslateY(140);
        exitMenuButton.setId("exitMenuButton");

        // lambda function for consoleButton
        exitMenuButton.setOnAction(event -> {
            window.close(); // close window when button clicked
            try {
                Menu.runMenu(); // run console menu when button clicked
                leagueTable.getItems().clear();
                leagueTable.setItems(getFootballClubs()); // update league table when button clicked

            } catch (IOException | ClassNotFoundException ioException) {
                ioException.printStackTrace();
            }
        });

        tableButton.setOnAction(event -> {
            window.setScene(leagueTableScene);
            window.setTitle("Football Premiere League Table");
            leagueTable.getItems().clear();
            leagueTable.setItems(getFootballClubs());
        });

/* -------------------------------------------------------------------------------------------------------------------*/
        matchesButton.setOnAction(event -> {
            matchListView = new ListView<>();
            matchListView.setPadding(new Insets(0, 400, 0, 400)); // set padding
            matchListView.getItems().clear();

            for (Match item: LeagueManager.LEAGUE_MATCHES) {

                String date = ("════\t Date : " + item.getDate().getDay() + "." + item.getDate().getMonth()
                        + "." + item.getDate().getYear() + "\t════");
                matchListView.getItems().addAll(date + "\n\n\n⚽  " + item.getHomeClubName() + "\t\t" +
                        item.getHomeClubScore() + "  :  " + item.getVisitingClubScore() + "\t\t" +
                        item.getVisitingClubName() + "  ⚽");
            }

            VBox vBox2 = new VBox();
            vBox2.setAlignment(Pos.CENTER);
            matchListHeading.setText("⚽   Premier League Matches");
            vBox2.getChildren().add(matchListHeading);

            vBox2.getChildren().addAll(matchListView);
            vBox2.setSpacing(10);

            matchesScene = new Scene(vBox2, 1700, 800);
            matchesScene.getStylesheets().add("level5/cw/implementation/LeagueTable.css");

            HBox hBox2 = new HBox();
            hBox2.setSpacing(10);
            vBox2.getChildren().add(hBox2);

            // back button to go back to league table
            hBox2.getChildren().addAll(backButton);

            // sort matches button
            hBox2.getChildren().addAll(sortMatchesButton);

            // generate random match button
            hBox2.getChildren().addAll(randomMatchButton);

            // text field for searching date
            userInput.setId("searchDate");
            hBox2.getChildren().addAll(userInput);
            userInput.setTranslateX(545);

            // search button for searching matches by date
            hBox2.getChildren().addAll(searchButton);
            searchButton.setId("searchButton");
            searchButton.setTranslateX(540);

            // set match list scene
            window.setScene(matchesScene);
            window.setTitle("Football Premiere League Matches");
        });

        // menu scene
        menuScene = new Scene(vBox3, 1280, 800);
        vBox3.setPadding(new Insets(200, 0, 0, 0)); // set padding

/* ------------------------------------------------------------------------------------------------------------------ */

        // league table scene
        leagueTableScene = new Scene(vBox, 1550, 700);
        leagueTableScene.getStylesheets().add("level5/cw/implementation/LeagueTable.css");

        menuScene.getStylesheets().add("level5/cw/implementation/LeagueTable.css");
        window.setScene(menuScene);
        window.show();
    }

    /**
     * @return leagueClubs ObservableList
     */
    public static ObservableList<FootballClub> getFootballClubs(){
        ObservableList<FootballClub> leagueClubs = FXCollections.observableArrayList();

        LeagueManager.LEAGUE_CLUBS.sort(Comparator.comparingInt(FootballClub::getGoalDifference)); // sort clubs according to goal difference
        LeagueManager.LEAGUE_CLUBS.sort(Comparator.comparingInt(FootballClub::getNumOfPoints)); // sort clubs according to points
        Collections.reverse(LeagueManager.LEAGUE_CLUBS);

        leagueClubs.addAll(LeagueManager.LEAGUE_CLUBS);
        return leagueClubs;
    }

    /**
     * @return leagueClubs ObservableList sorted by wins
     */
    public static ObservableList<FootballClub> getSortByWins(){
        ObservableList<FootballClub> leagueClubs = FXCollections.observableArrayList();
        FootballClub.compareWins();
        leagueClubs.addAll(LeagueManager.LEAGUE_CLUBS);

        return leagueClubs;
    }

    /**
     * @return leagueClubs ObservableList sorted by goals scored
     */
    public static ObservableList<FootballClub> getSortByGoals() {
        ObservableList<FootballClub> leagueClubs = FXCollections.observableArrayList();
        FootballClub.compareGoals();
        leagueClubs.addAll(LeagueManager.LEAGUE_CLUBS);

        return leagueClubs;
    }

    /**
     * Obtain league year from user
     */
    public static void obtainLeagueYear(){
        try {
            Scanner keyboardInput = new Scanner(System.in);
            System.out.println("\n—————————————————————————————————— WELCOME! ——————————————————————————————————");
            System.out.println("\n⯁ Enter Premiere League Year :");
            plYear = keyboardInput.nextInt();
            Menu.premiereLeagueManager.setLeagueYear(plYear);
        } catch (Exception e){
            System.out.println("\n⚠ Error : Enter a valid year! ⚠\n");
            obtainLeagueYear();
        }
    }
}
