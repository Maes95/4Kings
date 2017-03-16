/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FourKings;
import Mensajes.Jugada;
import Mensajes.Jugada.Promocion;
import Mensajes.Movimiento;
import Mensajes.Posicion;
import akka.actor.ActorRef;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.JTableHeader;

    public class GraphicBoard{
        
    private ActorRef myPlayer;
    private final int myId;
    
    private static Posicion casillaSeleccionada;
    private static Posicion casillaPiezaSeleccionada;
    
    private static final ImageIcon kingNYT = new ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\img\\kingNYT.gif");
    private static final ImageIcon kingYT = new ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\img\\kingYT.gif");
    private final JPanel gui = new JPanel(new BorderLayout(3, 3));
    private JButtonId[][] chessBoardSquares = new JButtonId[8][8];
    private JPanel chessBoard;
    private static final String COLS = "ABCDEFGH";
    
    private javax.swing.JScrollPane scroll;
    private javax.swing.JTextArea cajaTexto;
    
    
    // MICHEL ADD - IDEAS FELICES
    private javax.swing.JTable scoreTable;
    private javax.swing.JLabel label;
    // FIN
    
    public GraphicBoard(ActorRef a, int i) {
        this.myPlayer = a;
        this.myId = i + 1;
        GraphicBoard.casillaSeleccionada = null;
        GraphicBoard.casillaPiezaSeleccionada = null;
        
        initializeGui();
        
        JFrame f = new JFrame("ChessChamp");
        f.setTitle("Player "+ myId);
        f.add(this.getGui());
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setLocationByPlatform(true);
        
        this.cajaTexto = new JTextArea();
        this.scroll = new JScrollPane();
        cajaTexto.setColumns(60);
        cajaTexto.setRows(10);
        scroll.setViewportView(cajaTexto);
        cajaTexto.setVisible(true);
        scroll.setVisible(true);
        JPanel p = new JPanel();

        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        p.add(scroll);
        
        // MICHEL ADD - IDEAS FELICES
        
        label = new JLabel();
        label.setIcon(kingNYT);
        String[] columNames = {"Jugador","Puntos"};
        String[][] data = {
        {" Amarillas", " "+0+" pts"},
        {" Azules", " "+0+" pts"},
        {" Verdes", " "+0+" pts"},
        {" Rojas", " "+0+" pts"},
        };
        scoreTable = new JTable(data, columNames);
        scoreTable.setCellSelectionEnabled(false);
        scoreTable.setFillsViewportHeight(true);
        scoreTable.setShowVerticalLines(true);
        scoreTable.setShowHorizontalLines(true);
        scoreTable.setShowGrid(true);
        scoreTable.getColumnModel().getColumn(0).setPreferredWidth(70);
        scoreTable.getColumnModel().getColumn(1).setPreferredWidth(50);
        JPanel container = new JPanel();
        container.setLayout(new BorderLayout());
        container.add(scoreTable.getTableHeader(), BorderLayout.PAGE_START);
        container.add(scoreTable, BorderLayout.CENTER);
        p.add(container);
        scoreTable.setVisible(true);
        label.setVisible(true);
        container.setVisible(true);
        JSplitPane splitH2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitH2.setTopComponent(container);
        splitH2.setBottomComponent(label); 
        
        JSplitPane splitH = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitH.setLeftComponent(p);
        splitH.setRightComponent(splitH2);
    
        // FIN
        
        JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        split.setTopComponent(this.getGui());
        //split.setBottomComponent(p); PABLO
        split.setBottomComponent(splitH);
        
        split.setDividerLocation(0.8);
        f.add(split);
        
        // ensures the frame is the minimum size it needs to be
        // in order display the components within it
        f.pack();
        // ensures the minimum size is enforced.
        f.setMinimumSize(f.getSize());
        f.setResizable(false);
        
        
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        inicializarTablero();
        myPlayer.tell(this,myPlayer);
        f.setVisible(true);
    }

    public final void initializeGui() {
        // set up the main GUI
        //gui.setBorder(new EmptyBorder(40, 40, 60, 60));//PABLO
        gui.setBorder(new EmptyBorder(10, 10, 10, 10));//MICHEL
        chessBoard = new JPanel(new GridLayout(0, 9)); 
        chessBoard.setBorder(new LineBorder(Color.BLACK));
        gui.add(chessBoard);

        // create the chess board squares
        Insets buttonMargin = new Insets(0,0,0,0);
        for (int ii = 0; ii < 8; ii++) {
            for (int jj = 0; jj < 8; jj++) {
                JButtonId b = new JButtonId(ii, jj);
                if (ii <= 1){
                    boolean ocupado = (jj != 2 && jj != 3);
                    b.setOcupado(ocupado);
                }else if (ii <= 3){
                    boolean ocupado = !(jj >= 2);
                    b.setOcupado(ocupado);
                }else if (ii <= 5){
                    boolean ocupado = !(jj <= 5);
                    b.setOcupado(ocupado);
                }else{
                    boolean ocupado = (jj != 4 && jj != 5);
                    b.setOcupado(ocupado);
                }
                b.setMargin(buttonMargin);
                // our chess pieces are 64x64 px in size, so we'll
                // 'fill this in' using a transparent icon..
                ImageIcon icon = new ImageIcon(
                        new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
                b.setIcon(icon);
                if ((jj % 2 == 1 && ii % 2 == 1)
                        //) {
                        || (jj % 2 == 0 && ii % 2 == 0)) {
                    b.setBackground(Color.WHITE);
                } else {
                    b.setBackground(Color.BLACK);
                }
                chessBoardSquares[ii][jj] = b;
                
                
                b.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                        if (GraphicBoard.casillaSeleccionada == null){ //No había ninguna casilla seleccionada
                            GraphicBoard.casillaSeleccionada = new Posicion(b.getPosicion().fila(), b.getPosicion().columna());
                            System.out.println("NO HABÍA SELECCIÓN -> SE SELECCIONA LA CASILLA");
                            if (b.getOcupado()){
                                GraphicBoard.casillaPiezaSeleccionada = new Posicion(b.getPosicion().fila(), b.getPosicion().columna());
                                System.out.println("LA CASILLA ESTABA OCUPADA -> SE SELECCIONA LA CASILLA OCUPADA");
                            } else{
                                GraphicBoard.casillaPiezaSeleccionada = null;
                            }
                        }
                        else { //Ya había una casilla seleccionada
                            System.out.println("HABÍA SELECCIÓN");
                            if(GraphicBoard.casillaPiezaSeleccionada != null){ //Se había seleccionado una pieza
                                System.out.println("HABÍA SELECCIONADA UNA PIEZA");
                                if(!b.getPosicion().sonIguales(GraphicBoard.casillaPiezaSeleccionada)){
                                    Movimiento m = new Movimiento(GraphicBoard.casillaPiezaSeleccionada, b.getPosicion(), myId);
                                    myPlayer.tell(m);
                                    GraphicBoard.casillaPiezaSeleccionada = null;
                                    GraphicBoard.casillaSeleccionada = null;
                                    System.out.println("SE HA EFECTUADO MOVIMIENTO");
                                }
                            } else{ //La casilla seleccionada era una casilla desocupada
                                System.out.println("HABÍA SELECCIONADA UNA CASILLA VACÍA");
                                GraphicBoard.casillaSeleccionada = new Posicion(b.getPosicion().fila(), b.getPosicion().columna());
                                if (b.getOcupado()){
                                    System.out.println("SE SELCCIONA UNA CASILLA OCUPADA");
                                    GraphicBoard.casillaPiezaSeleccionada = new Posicion(b.getPosicion().fila(), b.getPosicion().columna());
                                }else{
                                    System.out.println("SE SELECCIONA UNA CASILLA DESOCUPADA");
                                    GraphicBoard.casillaPiezaSeleccionada = null;
                                }
                            }
                        }
                    }

                    
                });
                
            }
        }

        //fill the chess board
        chessBoard.add(new JLabel(""));
        // fill the top row
        for (int ii = 0; ii < 8; ii++) {
            chessBoard.add(
                    new JLabel(COLS.substring(ii, ii + 1),
                    SwingConstants.CENTER));
        }
        // fill the black non-pawn piece row
        for (int ii = 0; ii < 8; ii++) {
            for (int jj = 0; jj < 8; jj++) {
                switch (jj) {
                    case 0:
                        chessBoard.add(new JLabel("" + (ii + 1),
                                SwingConstants.CENTER));
                    default:
                        chessBoard.add(chessBoardSquares[ii][jj]);
                }
            }
        }
        
    }

    public final JComponent getChessBoard() {
        return chessBoard;
    }

    public final JComponent getGui() {
        return gui;
    }
    
    private void inicializarTablero(){
        for (int ii = 0; ii < chessBoardSquares.length; ii++) {
            for (int jj = 0; jj < chessBoardSquares[ii].length; jj++) {
                String path = "";
                switch(ii){
                    case 0:
                        switch(jj){
                            case 0:
                                path = "barco_azul.gif";
                                break;
                            case 1:
                                path = "peon_azul.gif";
                                break;
                            case 4:
                                path = "rey_verde.gif";
                                break;
                            case 5:
                                path = "elefante_verde.gif";
                                break;
                            case 6:
                                path = "caballo_verde.gif";
                                break;
                            case 7:
                                path = "barco_verde.gif";
                                break;
                            default:
                                break;
                        }
                        break;
                    case 1:
                        switch(jj){
                            case 0:
                                path = "caballo_azul.gif";
                                break;
                            case 1:
                                path = "peon_azul.gif";
                                break;
                            case 4:
                                path = "peon_verde.gif";
                                break;
                            case 5:
                                path = "peon_verde.gif";
                                break;
                            case 6:
                                path = "peon_verde.gif";
                                break;
                            case 7:
                                path = "peon_verde.gif";
                                break;
                            default:
                        }
                        break;
                    case 2:
                        switch(jj){
                            case 0:
                                path = "elefante_azul.gif";
                                break;
                            case 1:
                                path = "peon_azul.gif";
                                break;
                            default:
                        }
                        break;
                    case 3:
                        switch(jj){
                            case 0:
                                path = "rey_azul.gif";
                                break;
                            case 1:
                                path = "peon_azul.gif";
                                break;
                            default:
                        }
                        break;
                    case 4:
                        switch(jj){
                            case 6:
                                path = "peon_rojo.gif";
                                break;
                            case 7:
                                path = "rey_rojo.gif";
                                break;
                            default:
                        }
                        break;
                    case 5:
                        switch(jj){
                            case 6:
                                path = "peon_rojo.gif";
                                break;
                            case 7:
                                path = "elefante_rojo.gif";
                                break;
                            default:
                        }
                        break;
                    case 6:
                        switch(jj){
                            case 0:
                                path = "peon_amarillo.gif";
                                break;
                            case 1:
                                path = "peon_amarillo.gif";
                                break;
                            case 2:
                                path = "peon_amarillo.gif";
                                break;
                            case 3:
                                path = "peon_amarillo.gif";
                                break;
                            case 6:
                                path = "peon_rojo.gif";
                                break;
                            case 7:
                                path = "caballo_rojo.gif";
                                break;
                            default:
                        }
                        break;
                    case 7:
                        switch(jj){
                            case 0:
                                path = "barco_amarillo.gif";
                                break;
                            case 1:
                                path = "caballo_amarillo.gif";
                                break;
                            case 2:
                                path = "elefante_amarillo.gif";
                                break;
                            case 3:
                                path = "rey_amarillo.gif";
                                break;
                            case 6:
                                path = "peon_rojo.gif";
                                break;
                            case 7:
                                path = "barco_rojo.gif";
                                break;
                            default:
                        }
                        break;
                }
                //System.out.println(ii + ", " + jj + " ->  " + path);
                if (!path.equals("")) chessBoardSquares[ii][jj].setIcon(new ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\img\\" + path));
            }
        }
    }
    
    public void actualizarTablero(Jugada j){
        
        Movimiento m = j.getMov(); //Movimiento ya validado
        
        chessBoardSquares[m.DEST.fila()][m.DEST.columna()].setOcupado(true);
            Promocion c = j.getTipoPromocion();
            switch(c){
                case CABALLO:
                    chessBoardSquares[m.DEST.fila()][m.DEST.columna()].setIcon(new ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\img\\caballo_"+j.getColor()+".gif"));
                    break;
                case ELEFANTE:
                    chessBoardSquares[m.DEST.fila()][m.DEST.columna()].setIcon(new ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\img\\elefante_"+j.getColor()+".gif"));
                    break;
                case NO_PROMO:
                    chessBoardSquares[m.DEST.fila()][m.DEST.columna()].setIcon(chessBoardSquares[m.ORIG.fila()][m.ORIG.columna()].getIcon());
                    break;
            }
        
        chessBoardSquares[m.ORIG.fila()][m.ORIG.columna()].setOcupado(false);
        chessBoardSquares[m.ORIG.fila()][m.ORIG.columna()].setIcon(null);
        
        for(int i = 0; i < scoreTable.getRowCount(); i++){
            scoreTable.setValueAt(" "+j.getScore()[i]+" pts", i, 1);
        }
        
    }
    
    private boolean notNull(Integer[] i) {
        return (i[0] != null);
    }

    public void appendText(String s) {
        this.cajaTexto.append(s);
    }
    
    public void myTurn(){
        appendText("Te toca campeón \n");
        label.setIcon(kingYT);
    }
    
    public void notMyTurn(){
        label.setIcon(kingNYT);
    }
}