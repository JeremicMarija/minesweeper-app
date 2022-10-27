/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import communication.SocketCommunication;
import domain.Game;
import domain.GeneralDomainObject;
import domain.Level;
import domain.User;
import game.GUIListener.GUIListenerExitGame;
import game.GUIListener.GUIListenerSetDifficulty;
import game.GUIListener.GUIListenerStartGame;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import threads.ScoreListener;
import transfer.Request;
import transfer.Response;
import transfer.util.Operation;
import transfer.util.ResponseStatus;
import utils.ConverterGUIDK;
import utils.Messages;

/**
 *
 * @author Marija Jeremic
 */
public class Controller {
    
    public boolean[][] mines;
    public int numOfMines;
    public int numOfFieldsLeft;
    public int rows;
    public int cols;
    public double difficulty;
    
    public FXMLDocumentController fxdc;
    ScoreListener scoreListener;
    
    List<ScoreListener> counterOfThreads;
    
    private User user;
    private Game game;
    private Level level;
    
    public Controller(FXMLDocumentController fxdc){
        
        super();
        this.fxdc = fxdc;
        rows = this.fxdc.getFieldRows();
        cols = this.fxdc.getFieldColumns();
        
        this.user = MainMenuFx.user;
        numOfMines = 0;
        numOfFieldsLeft = 0;
        
        this.fxdc.start.setOnAction(new GUIListenerStartGame(this));
        this.fxdc.exit.setOnAction(new GUIListenerExitGame(this));
        MainMenuFx.stage.setOnCloseRequest(new GUIListenerExitGame(this));
        this.fxdc.startGame.setOnAction(new GUIListenerStartGame(this));
        this.fxdc.setDifficulty.setOnAction(new GUIListenerSetDifficulty(this));
        
        mines = new boolean[rows][cols];
        
        this.fxdc.score.setText(String.valueOf(0));
        fillFormFromObject(user);
        counterOfThreads = new ArrayList<>();
        
        setStartGameImage(this.fxdc.startGame);
        setNormalDifficulty();
        
    }
    
    public void gameInitialization(){
        
        try{
            numOfMines = 0;
            numOfFieldsLeft = rows * cols;
            
            game = new Game();
            game.setDate(game.getDate(new Date()));
            game.setUserId(user.getPrimaryKey());
            
            setStartGameImage(this.fxdc.startGame);
            
            if (!counterOfThreads.isEmpty()) {
                stopAllRunningThreads();
            } else {
                scoreListener = new ScoreListener(this);
                scoreListener.start();
                counterOfThreads.add(scoreListener);
            }
            
            System.out.println("Currently running: " + scoreListener.getName());
            
            for(int x = 0; x < rows; x++){
                for(int y = 0; y < cols; y++){
                    Button btn = null;
                    boolean hasBomb = Math.random() < level.percentageOfBomb;
                    mines[x][y] = hasBomb;
                    
                    if(hasBomb){
                        numOfMines++;
                        numOfFieldsLeft--;
                    }
                    
                    try{
                        btn = this.fxdc.getButton(x, y);
                        resetButton(btn);
                        resetButton(btn);
                    }catch(NoSuchFieldException | IllegalAccessException | IllegalArgumentException ex){
                        Logger.getLogger(Controller.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                }  
            }
            Messages.startGameMsg();
        }catch(Exception ex){
            Messages.failedStartGameMsg();
            System.err.println(ex.getMessage());
            System.err.println(ex.getStackTrace());
        }
    }
    
    void resetButton(Button btn){
        btn.setText("");
        btn.setGraphic(null);
        btn.getStyleClass().remove("bg-white");
        btn.getStyleClass().remove("bg-bomb");
        btn.getStyleClass().remove("bg-white");
        btn.getStyleClass().remove("bg-bomb");
        btn.getStyleClass().add("bg-dark");
        btn.setDisable(false);
    }
    
    void setEasyDifficulty() {
//        difficulty for Easy game is set to 0.1
        level = getLevel("Easy");
    }
    
       void setNormalDifficulty() {
//        difficulty for Normal game is set to 0.2;        
        level = getLevel("Normal");
    }

    void setHardDifficulty() {
//        difficulty for Hard game is set 0.35;
        level = getLevel("Hard");
    }
    
    Level getLevel(String difficulty) {
        Request request = new Request();
        level = new Level();
        level.difficulty = difficulty;

        request.setLevel(level);
        request.setOperation(Operation.OPERATION_GET_LEVEL_DIFFICULTY);

        SocketCommunication.getInstance().sendRequest(request);
        Response response = SocketCommunication.getInstance().readResponse();

        return response.getLevel();
    }
    
    public void setDifficulty() {
        Alert difficultyAlert = new Alert(Alert.AlertType.CONFIRMATION);
        difficultyAlert.setTitle("Select difficulty");
        difficultyAlert.setHeaderText(null);
        difficultyAlert.setContentText("Select the difficulty of the game");
        
        ButtonType easy = new ButtonType("Easy");
        ButtonType normal = new ButtonType("Normal");
        ButtonType hard = new ButtonType("Hard");
        ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        
        difficultyAlert.getButtonTypes().setAll(easy, normal, hard, cancel);
        Optional<ButtonType> result = difficultyAlert.showAndWait();
        
        if (result.get() == easy) {
            setEasyDifficulty();
        } else if (result.get() == normal) {
            setNormalDifficulty();
        } else if (result.get() == hard) {
            setHardDifficulty();
        }
        gameInitialization();
    }
    
    public void openCellStyle(Button btn) {
        btn.getStyleClass().remove("bg-bomb");
        btn.getStyleClass().remove("bg-dark");
        btn.getStyleClass().add("bg-white");
    }
    public void darkCellStyle(Button btn) {
        btn.getStyleClass().remove("bg-bomb");
        btn.getStyleClass().remove("bg-white");
        btn.getStyleClass().add("bg-dark");
    }
    public void bombCellStyle(Button btn) {
        if (btn.getGraphic() != null) {
            btn.setGraphic(null);
        }
        btn.setText("X");
        btn.getStyleClass().remove("bg-white");
        btn.getStyleClass().remove("bg-dark");
        btn.getStyleClass().add("bg-bomb");
    }
    
    public void setStartGameImage(Button btn) {
        String location = "assets/images/start.png";
        ImageView imgView = setImageToButtonProportions(location, btn);
        btn.setGraphic(null);
        btn.setGraphic(imgView);
    }
    
    public void setOnClickSmiley(Button btn) {
        String location = "assets/images/onClick.png";
        ImageView imgView = setImageToButtonProportions(location, btn);
        btn.setGraphic(null);
        btn.setGraphic(imgView);
    }
    
    public void setGameOverImage(Button btn) {
        String location = "assets/images/gameOver.png";
        ImageView imgView = setImageToButtonProportions(location, btn);
        btn.setGraphic(null);
        btn.setGraphic(imgView);
    }
        
    public void setWinImage(Button btn) {
        String location = "assets/images/win.png";
        ImageView imgView = setImageToButtonProportions(location, btn);
        btn.setGraphic(null);
        btn.setGraphic(imgView);
    }
    
    public void open(Button btn){
        
        try {

            Integer row = GridPane.getRowIndex(btn);
            Integer column = GridPane.getColumnIndex(btn);

            int r = row == null ? 0 : row;
            int c = column == null ? 0 : column;

            if (btn.isDisabled()) {
                return;
            }

            btn.setGraphic(null);

            boolean mine = mines[r][c];

            if (mine) {
                bombCellStyle(btn);
                System.out.println("Game Over");
                gameOver();
                setGameOverImage(this.fxdc.startGame);
            } else {

                setFieldNumber(btn, r, c);
                openCellStyle(btn);

                if (btn.getText().isEmpty()) {
                    try {
                        findEmptyCells(r, c);
                    } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException ex) {
                        Logger.getLogger(FXMLDocumentController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                } else {
                    numOfFieldsLeft--;
                }

                checkIfGameIsFinished();
            }

            btn.setDisable(true);
            btn.setOpacity(1);

        } catch (Exception ex) {
            Messages.failedOpenCellMsg();
            System.err.println(ex.getMessage());
            System.err.println(ex.getStackTrace());
        }
    }
    
    public void setFlag(Button btn) {
        
        if (btn.getGraphic() != null) {
            btn.setGraphic(null);
            return;
        }

        String location = "assets/images/flag.png";
        ImageView imgView = setImageToButtonProportions(location, btn);

        if (imgView != null) {
            btn.setGraphic(imgView);
        } else {
            btn.setGraphic(new ImageView());
        }
    }
    
    public void setFieldNumber(Button btn, int row, int column) {
        int mineCount = getNumberOfSurroundingMines(row, column);

        if (mineCount > 0) {
            btn.setText(Integer.toString(mineCount));
        }
    }
    
    public int getNumberOfSurroundingMines(int row, int column) {
        int mineCount = 0;

        // count surrounding mines
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (checkMine(mines, row + i, column + j)) {
                    mineCount++;
                }
            }
        }

        return mineCount;
    }
    
    public boolean checkMine(boolean[][] mines, int row, int column) {
        return row >= 0 && column >= 0 && row < mines.length && column < mines[row].length && mines[row][column];
    }
    
    
    public void findEmptyCells(int i, int j) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {

        if (i >= 0 && j >= 0 && i < rows && j < cols) {
            Button btn = this.fxdc.getButton(i, j);

            int mineAroundCount = getNumberOfSurroundingMines(i, j);

            if (!btn.isDisabled() && mines[i][j] == false && mineAroundCount == 0) {

                openCellStyle(btn);
                btn.setDisable(true);
                btn.setOpacity(1);

                numOfFieldsLeft--;

                List<Point> neighbours = getNeighbours(i, j);

                if (!neighbours.isEmpty()) {
                    neighbours.forEach(p -> {
                        try {
                            findEmptyCells(p.x, p.y);
                        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException ex) {
                            Logger.getLogger(FXMLDocumentController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                        }
                    });
                }
            } else if (!btn.isDisabled() && mines[i][j] == false) {
                setFieldNumber(btn, i, j);
                openCellStyle(btn);
                btn.setDisable(true);
                btn.setOpacity(1);

                numOfFieldsLeft--;
            }
        }
    }
    
    private List<Point> getNeighbours(int row, int column) {
        List<Point> neighbours = new ArrayList<>();

        int[] points = new int[]{
            -1, -1,
            -1, 0,
            -1, 1,
            0, -1,
            0, 1,
            1, -1,
            1, 0,
            1, 1
        };

        for (int i = 0; i < points.length; i++) {
            int dx = points[i];
            int dy = points[++i];

            int newX = row + dx;
            int newY = column + dy;

            if (newX >= 0 && newX < rows
                    && newY >= 0 && newY < cols) {
                neighbours.add(new Point(newX, newY));
            }
        }

        return neighbours;
    }
    
    public void checkIfGameIsFinished() {
        int fieldsLeft = numOfFieldsLeft;

        if (fieldsLeft == 0) {
            gameOver();
            win();
        }
    }
    
    public void gameOver() {

        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                Button btn = null;
                try {
                    btn = this.fxdc.getButton(x, y);
                } catch (NoSuchFieldException | IllegalAccessException | IllegalArgumentException ex) {
                    Logger.getLogger(Controller.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }

                if (mines[x][y]) {
                    bombCellStyle(btn);
                } else if (!btn.isDisabled()) {
                    darkCellStyle(btn);
                }

                btn.setDisable(true);
            }
        }

        stopAllRunningThreads();
    }
    
    private void win() {
        try {
            setWinImage(this.fxdc.startGame);

            Alert playAgainAlert = new Alert(Alert.AlertType.CONFIRMATION);
            playAgainAlert.setTitle("Winner!");
            playAgainAlert.setHeaderText(null);
            playAgainAlert.setContentText("You've won! Congratulations!\nYour score: " + this.fxdc.score.getText() + "\n\nWant to try again?");

            ButtonType playAgain = new ButtonType("Play again");
            ButtonType cancel = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            playAgainAlert.getButtonTypes().setAll(playAgain, cancel);
            Optional<ButtonType> result = playAgainAlert.showAndWait();

            saveGame();

            if (user.getHighscore() > Integer.parseInt(this.fxdc.score.getText())) {
                saveHighscore();
            }

            if (result.get() == playAgain) {
                gameInitialization();
            }

        } catch (Exception ex) {
            Messages.winErrorMsg();
        }
    }
    
    public void exitGame() {

        stopAllRunningThreads();
        Platform.exit();
        System.exit(0);
    }
    
    private ImageView setImageToButtonProportions(String location, Button button) {
        Image img = new Image(location);
        ImageView imgView = new ImageView(img);

        imgView.setPreserveRatio(true);
        imgView.fitWidthProperty().add(button.widthProperty().getValue() / 2);

        return imgView;
    }
    public void saveGame() {
        int score = Integer.parseInt(this.fxdc.score.getText());

        game.setScore(score);
        game.setLevel(level.getPrimaryKey());

        Request request = new Request();
        request.setGame(game);
        request.setLevel(level);
        request.setUser(user);
        request.setOperation(Operation.OPERATION_SAVE_GAME);

        SocketCommunication.getInstance().sendRequest(request);
        Response response = SocketCommunication.getInstance().readResponse();

        if (response.getStatus() == ResponseStatus.OK) {
            Messages.saveGameMsg();
        } else {
            Messages.saveGameErrorMsg();
        }
    }
    
    public void saveHighscore() {
        int newHighscore = Integer.parseInt(this.fxdc.score.getText());

        game.setScore(newHighscore);
        game.setLevel(level.getPrimaryKey());
        user.setHighscore(newHighscore);

        Request request = new Request();
        request.setGame(game);
        request.setLevel(level);
        request.setUser(user);
        request.setOperation(Operation.OPERATION_NEW_HIGHSCORE);

        SocketCommunication.getInstance().sendRequest(request);
        Response response = SocketCommunication.getInstance().readResponse();

        user.highscore = response.getUser().highscore;
        this.fxdc.highscore.setText(String.valueOf(user.getHighscore()));

        fillFormFromObject(user);
        
        if (response.getStatus() == ResponseStatus.OK) {
            Messages.saveHighscoreMsg();
        } else {
            Messages.saveHighscoreErrorMsg();
        }
    }
    
    private void stopAllRunningThreads() {
        for (ScoreListener thread : counterOfThreads) {
            if (!thread.isInterrupted()) {
                thread.interrupt();
                System.out.println("Removing thread: " + thread.getName());
            }
        }
        counterOfThreads.clear();
    }
    
    public boolean fillFormFromObject(GeneralDomainObject gdo) {
        if (!ConverterGUIDK.convertDKUGUI(gdo, fxdc)) {
            return false;
        }
        return true;
    }
    
    public boolean fillOjectFromForm(GeneralDomainObject gdo) {
        if (!ConverterGUIDK.convertGUIDK(fxdc, gdo)) {
            return false;
        }
        return true;
    }
    
    
}
