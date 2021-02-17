package juego;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class TresEnRaya extends Canvas {
	
	 static JFrame ventana = new JFrame("3 en raya"); 
	 
	
	 private static final int JFrame_WIDTH=700;
	 private static final int JFrame_HEIGTH=700;
	
	 private static TresEnRaya instance = null;
	 
	 public static int JUGADOR_1 = 1;
	 public static int JUGADOR_2 = 2;
	 private int turnoActual = JUGADOR_1;
	 private int matrizJugadas[][]= new int[][] {{0,0,0},
                                                 {0,0,0},
                                                 {0,0,0}};

	List<Cuadro> cuadros = new ArrayList<Cuadro>();
	 
	 	public TresEnRaya() {
		 
		 JPanel panel = (JPanel) ventana.getContentPane();
		 
				panel.setLayout(new BorderLayout());
		
		 panel.add(this, BorderLayout.CENTER);
		 InicializaCuadrosDelTablero();
		 
		 this.addMouseListener(new MouseAdapter() {
			 
			 public void mouseClicked (MouseEvent e) {
			 super.mouseClicked(e);
			 if(e.getButton() == MouseEvent.BUTTON1) {
				 for (Cuadro cuadro : cuadros ) {
					 if (cuadro.SehahechoclicSobreCuadro(e.getX(), e.getY())) {
						 cuadro.clic(turnoActual);
						 compruebaResultado();
						 if(turnoActual==JUGADOR_1) {
							 turnoActual = JUGADOR_2;
						 }
						 else {
							 turnoActual = JUGADOR_1;
							 
						 }
					 }
				 }
			 }
		 }});
				
		 ventana.setBounds(0,0, JFrame_WIDTH, JFrame_HEIGTH);
		
		 ventana.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		 ventana.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
				cerrarAplicacion();
				}

				});
		
		 ventana.setVisible(true);
		 this.requestFocus();
		 }
	 
	 	public static TresEnRaya getInstance() {
			if (instance == null) {
				instance = new TresEnRaya();
			}
			return instance;
		}
		
		private void InicializaCuadrosDelTablero() {
			for (int i = 0; i<3; i++) {
				for (int j = 0; j<3; j++) {
					this.cuadros.add(new Cuadro(i,j));
					
				}
			}
		}
		private static void cerrarAplicacion() {
			String[] opciones = { "Aceptar", "Cancelar" };
			int eleccion = JOptionPane.showOptionDialog(ventana, "¿Desea cerrar la aplicación?", "Salir de la aplicación",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, "Aceptar");
			if (eleccion == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		}

	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.WHITE);
		g.fillRect(0, 0,this.getWidth(), this.getHeight());
	
		for (Cuadro cuadro : cuadros) {
			cuadro.paint(g);
		}
	}

	public int[][] getMatrizJugadas() {
		return matrizJugadas;
	}
	
	 public static void main(String[] args) {
		 TresEnRaya.getInstance();
	}
	 
	 private static int getGanadorDelTablero (int tablero[][]) {
		
			for (int i = 0; i < tablero.length; i++) {
				if (tablero[i][0] == tablero[i][1] && tablero[i][1] == tablero[i][2]) { 
					return tablero[i][0]; 
				}
			}
			
			for (int i = 0; i < tablero[0].length; i++) {
				if (tablero[0][i] == tablero[1][i] && tablero[1][i] == tablero[2][i]) { 
					return tablero[0][i]; 
				}
			}
			
			if (tablero[0][0] == tablero[1][1] && tablero[1][1] == tablero[2][2]) { 
				return tablero[0][0]; 
			}
			
			if (tablero[0][2] == tablero[1][1] && tablero[1][1] == tablero[2][0]) {
				return tablero[0][2]; 
			}
			
			
			boolean hayCasillasVacias = false;
			for (int i = 0; i < tablero.length; i++) {
				for (int j = 0; j < tablero[0].length; j++) {
					if (tablero[i][j] == 0) {
						hayCasillasVacias = true;
					}
				}
			}

			
			if (hayCasillasVacias == true) {
				return 0; 
			}
			else {
				return -1; 
			}
		}
	 public void compruebaResultado() {
			int resultado = getGanadorDelTablero(matrizJugadas);

			if (resultado == -1) {
				JOptionPane.showMessageDialog(instance, "La partida queda en empate");
			}
			if (resultado == 1) {
				JOptionPane.showMessageDialog(instance, "El jugador 1 es el ganador");
			}
			if (resultado == 2) {
				JOptionPane.showMessageDialog(instance, "El jugador 2 es el ganador");
			}
	}
}

