package juego;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Cuadro {
	private int xEnTablero, yEnTablero;
	private int esquinaSuperiorIzquierdaX;
	private int esquinaSuperiorIzquierdaY;
	private int ancho, alto;
	private int jugadorPropietario = 0;
	private int matrizJugadas[][]= new int[][] {{0,0,0},
			                                   {0,0,0},
			                                   {0,0,0}};
	
	
	public Cuadro(int xEnTablero, int yEnTablero)  {
		super();
		this.xEnTablero = xEnTablero;
		this.yEnTablero = yEnTablero;
		
	}
	
	public void paint(Graphics g) {
		ancho = TresEnRaya.getInstance().getWidth() / 3;
		alto = TresEnRaya.getInstance().getHeight() / 3;
		esquinaSuperiorIzquierdaX = this.xEnTablero * ancho;
		esquinaSuperiorIzquierdaY = this.yEnTablero * alto;
		
		//Pinto el borde
		g.setColor(Color.BLACK);
		g.drawRect(esquinaSuperiorIzquierdaX, esquinaSuperiorIzquierdaY, ancho, alto );
		
		
			//pintaImagenesVectoriales(g);
			pintaImagenesJuego(g);
		
	}
	
	
	private void pintaImagenesVectoriales(Graphics g) {
		if (this.jugadorPropietario == TresEnRaya.JUGADOR_1) {
			g.drawLine(this.esquinaSuperiorIzquierdaX,this.esquinaSuperiorIzquierdaY, this.esquinaSuperiorIzquierdaX + ancho, this.esquinaSuperiorIzquierdaY + alto);
			g.drawLine(this.esquinaSuperiorIzquierdaX,this.esquinaSuperiorIzquierdaY + alto, this.esquinaSuperiorIzquierdaX + ancho, this.esquinaSuperiorIzquierdaY);
		}
		if (this.jugadorPropietario == TresEnRaya.JUGADOR_2) {
			g.drawOval(this.esquinaSuperiorIzquierdaX,this.esquinaSuperiorIzquierdaY,ancho,alto);
		}
	}
	
	private void pintaImagenesJuego(Graphics g) {
        BufferedImage imagenAPintar = null;
        if (this.jugadorPropietario == TresEnRaya.JUGADOR_1) {
            imagenAPintar = Cache.getInstance().getImagen("Putin.png");
        }
        if (this.jugadorPropietario == TresEnRaya.JUGADOR_2) {
            imagenAPintar = Cache.getInstance().getImagen("DonaldTrump.png");
        }
        if(imagenAPintar != null) {
            int x = this.esquinaSuperiorIzquierdaX + this.ancho / 2 - imagenAPintar.getWidth() / 2;
            int y = this.esquinaSuperiorIzquierdaY + this.alto / 2 - imagenAPintar.getHeight() / 2;
            g.drawImage(imagenAPintar, x, y, null);
        }
    }
	
	public boolean SehahechoclicSobreCuadro (int xClic, int yClic) {
		if (xClic > this.esquinaSuperiorIzquierdaX && xClic < (esquinaSuperiorIzquierdaX + ancho) &&
		   yClic > this.esquinaSuperiorIzquierdaY && yClic < (esquinaSuperiorIzquierdaY + alto)) {
	return true;
		   }
	return false;
}
	public void clic (int jugador) {
		if (this.jugadorPropietario ==0) {
			this.jugadorPropietario = jugador;
			
			TresEnRaya.getInstance().getMatrizJugadas()[this.yEnTablero][this.xEnTablero] = jugador;
		}
		TresEnRaya.getInstance().repaint();
		TresEnRaya.getInstance().revalidate();
		
		System.out.println("Jugada");
        for ( int i = 0; i < TresEnRaya.getInstance().getMatrizJugadas().length;i++) {
            for (int j = 0; j < TresEnRaya.getInstance().getMatrizJugadas()[i].length;j++) {
                System.out.print(TresEnRaya.getInstance().getMatrizJugadas()[i][j] + "\t");
            }
            System.out.println();
        }
	}


	public String toString() {
		return "Cuadro [XEnElTablero=" + xEnTablero + ", YEnElTablero=" + yEnTablero + "]";
	}

	public int getxEnTablero() {
		return xEnTablero;
	}

	public void setxEnTablero(int xEnTablero) {
		this.xEnTablero = xEnTablero;
	}

	public int getyEnTablero() {
		return yEnTablero;
	}

	public void setyEnTablero(int yEnTablero) {
		this.yEnTablero = yEnTablero;
	}
}
