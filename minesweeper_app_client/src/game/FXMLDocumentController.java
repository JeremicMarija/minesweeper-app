/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.lang.reflect.Field;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Marija Jeremic
 */
public class FXMLDocumentController {
    
    @FXML
    private Button p50;

    @FXML
    private Button p52;

    @FXML
    private Button p51;

    @FXML
    private Button p10;

    @FXML
    private Button p54;

    @FXML
    private Button p53;

    @FXML
    private Button p12;

    @FXML
    private Button p56;

    @FXML
    private Button p11;

    @FXML
    private Button p55;

    @FXML
    private Button p14;

    @FXML
    private Button p13;

    @FXML
    private Button p57;

    @FXML
    private Button p16;

    @FXML
    private Button p15;

    @FXML
    private Button p17;

    @FXML
    private Button p61;

    @FXML
    private Button p60;

    @FXML
    private Button p63;

    @FXML
    private Button p62;

    @FXML
    private Button p21;

    @FXML
    private Button p65;

    @FXML
    private Button p20;

    @FXML
    private Button p64;

    @FXML
    private Button p23;

    @FXML
    private Button p67;

    @FXML
    private Button p22;

    @FXML
    private Button p66;

    @FXML
    private Button p25;

    @FXML
    private Button p24;

    @FXML
    private Button p27;

    @FXML
    private Button p26;

    @FXML
    private Button p70;

    @FXML
    private Button p72;

    @FXML
    private Button p71;

    @FXML
    private Button p30;

    @FXML
    private Button p74;

    @FXML
    private Button p73;

    @FXML
    private Button p32;

    @FXML
    private Button p76;

    @FXML
    private Button p31;

    @FXML
    private Button p75;

    @FXML
    private Button p34;

    @FXML
    private Button p33;

    @FXML
    private Button p77;

    @FXML
    private Button p36;

    @FXML
    private Button p35;

    @FXML
    private Button p37;

    @FXML
    private Button p41;

    @FXML
    private Button p40;

    @FXML
    private Button p43;

    @FXML
    private Button p42;

    @FXML
    private Button p01;

    @FXML
    private Button p45;

    @FXML
    private Button p00;

    @FXML
    private Button p44;

    @FXML
    private Button p03;

    @FXML
    private Button p47;

    @FXML
    private Button p02;

    @FXML
    private Button p46;

    @FXML
    private Button p05;

    @FXML
    private Button p04;

    @FXML
    private Button p07;

    @FXML
    private Button p06;

    @FXML
    private Button p08;

    @FXML
    private Button p09;

    @FXML
    private Button p18;

    @FXML
    private Button p19;

    @FXML
    private Button p28;

    @FXML
    private Button p29;

    @FXML
    private Button p38;

    @FXML
    private Button p39;

    @FXML
    private Button p48;

    @FXML
    private Button p49;

    @FXML
    private Button p58;

    @FXML
    private Button p59;

    @FXML
    private Button p68;

    @FXML
    private Button p69;

    @FXML
    private Button p78;

    @FXML
    private Button p79;

    @FXML
    private Button p80;

    @FXML
    private Button p81;

    @FXML
    private Button p82;

    @FXML
    private Button p83;

    @FXML
    private Button p84;

    @FXML
    private Button p85;

    @FXML
    private Button p86;

    @FXML
    private Button p87;

    @FXML
    private Button p88;

    @FXML
    private Button p89;

    @FXML
    private Button p90;

    @FXML
    private Button p91;

    @FXML
    private Button p92;

    @FXML
    private Button p93;

    @FXML
    private Button p94;

    @FXML
    private Button p95;

    @FXML
    private Button p96;

    @FXML
    private Button p97;

    @FXML
    private Button p98;

    @FXML
    private Button p99;
    
    @FXML
    private AnchorPane AnchorPane;

    @FXML
    private GridPane mainField;

    @FXML
    public Button startGame;

    @FXML
    public TextField score;

    @FXML
    public TextField highscore;

    @FXML
    public AnchorPane anchorPane;
    
    @FXML
    public MenuBar MainMenu;
    
    @FXML
    public Menu game;
    
    @FXML
    public MenuItem start;
    
    @FXML
    public MenuItem setDifficulty;
    
    @FXML
    public Menu exitGame;
    
    @FXML
    public MenuItem exit;
    
    private int fieldRows = 10;
    private int fieldColumns = 10;
    
    public int getFieldRows() {
        return fieldRows;
    }

    public int getFieldColumns() {
        return fieldColumns;
    }
    
    Controller con;
    
    @FXML
    public void initialize() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        
        con = new Controller(this);
        
        for( int x = 0; x < fieldRows; x++){
            for(int y = 0; y < fieldColumns; y++){
                String nameBtn = "p" + x + y;
                Class cls = this.getClass();
                Field field = cls.getDeclaredField(nameBtn);
                Button btn = (Button) field.get(this);
                
                btn.setDisable(true);
                btn.setOnMouseClicked(e ->{
                    if(e.getButton() == MouseButton.PRIMARY){
                        con.open((Button) e.getSource());
                    }else if(e.getButton() == MouseButton.SECONDARY){
                        con.setFlag((Button) e.getSource());
                    }
                });
            }
        }
    }
    
    public Button getButton(int x, int y) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException{
        Button btn = null;
        String nameBtn = "p" + x + y;
        Class cls = this.getClass();
        Field field = cls.getDeclaredField(nameBtn);
        btn = (Button) field.get(this);
        
        return btn;
    }
}
