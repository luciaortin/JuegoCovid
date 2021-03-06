import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.awt.Rectangle;

import javax.lang.model.type.IntersectionType;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PanelJuego extends JPanel{
	
	private ImageIcon fondo, esca1, esca2, muneco, covid1, covid2, covid3, vacuna;
	private Timer timer1, timer2, timer3;
	private Rectangle Rc1, Rc2, Rc3, Rv, Rm;
	
	private final int ancho=600, alto=700;
	private int x,y, x1,x2,x3;
	public int move1 = 1;
	public int move2 = 5;
	public int move3 = 6;
	private final int JUMP = 7, MOVE = 10;
	private AudioClip musica;
	
	
	public PanelJuego() {

		move1 = getVel1();
		move2 = getVel2();
		move3 = getVel3();
		
		//musica
		URL url = null;
		try{url = new URL ("file", "localhost", "Night Shade.wav");}
	    catch (Exception exception) {}
		musica = JApplet.newAudioClip(url);
		musica.play();
		//FIN musica
		
		addKeyListener(new DirectionListener());
		
		//covid abajo
		timer1 = new Timer(20, new ReboundListener1());
		covid1 = new ImageIcon("covid.png");
		x1=410;
		
		//covid centro
		timer2 = new Timer(20, new ReboundListener2());
		covid2 = new ImageIcon("covid.png");
		x2=150;
		
		//covid arriba
		timer3 = new Timer(20, new ReboundListener3());
		covid3 = new ImageIcon("covid.png");
		x3=50;
		
		//mu?eco
		muneco = new ImageIcon("prota.png");
		x=50; y=532;
		
		fondo = new ImageIcon("background.png");
		esca1 = new ImageIcon("escalera1.png");
		esca2 = new ImageIcon("escalera2.png");
		vacuna = new ImageIcon("vacuna.png");
		
		setPreferredSize(new Dimension(ancho, alto));
		setBackground(Color.black);
		timer1.start(); timer2.start(); timer3.start();
		setFocusable(true);
	 }
	
	public void paintComponent(Graphics page){
		super.paintComponent(page);
		
		fondo.paintIcon(this,  page,  0,  0);
		esca1.paintIcon(this,  page,  50,  176);
		esca2.paintIcon(this,  page,  320,  389);
		
		muneco.paintIcon(this, page, x, y);
		vacuna.paintIcon(this, page, 200, 125);
		
		covid1.paintIcon(this, page, x1, 560);
		covid2.paintIcon(this, page, x2, 389-80);
		covid3.paintIcon(this, page, x3, 176-80);
		
		Rc1 = new Rectangle(x1, 560, covid1.getIconWidth(), covid1.getIconHeight());
		Rc2 = new Rectangle(x2, 309, covid1.getIconWidth(), covid1.getIconHeight());
		Rc3 = new Rectangle(x3, 176-80, covid1.getIconWidth(), covid1.getIconHeight());
		Rv = new Rectangle(200, 125, vacuna.getIconWidth(), vacuna.getIconHeight());
		Rm = new Rectangle(x, y, muneco.getIconWidth(), muneco.getIconHeight());
		
		requestFocus();
	}
	
	public void setVel1(int velocidad1) {this.move1 = velocidad1;}
	public int getVel1() {return move1;}
	
	public void setVel2(int velocidad2) {this.move2 = velocidad2;}
	public int getVel2() {return move2;}
	
	public void setVel3(int velocidad3) {this.move3 = velocidad3;}
	public int getVel3() {return move3;}
	
	
	private class ReboundListener1 implements ActionListener{
		public void actionPerformed(ActionEvent event){
			x1 += move1;
			if(x1>=ancho-covid1.getIconWidth() || x1<150) {
				move1 = move1 * -1;
			}
			repaint();
		}
	}
	
	private class ReboundListener2 implements ActionListener{
		public void actionPerformed(ActionEvent event){
			x2+=move2;
			if(x2>=600-covid2.getIconWidth() || x2<0) {
				move2 = move2 * -1;
			}
			repaint();
		}
	}
	
	private class ReboundListener3 implements ActionListener{
		public void actionPerformed(ActionEvent event){
			x3+=move3;
			if(x3>=600-covid3.getIconWidth() || x3<0) {
				move3 = move3 * -1;
			}
			repaint();
		}
	}
	
	private class DirectionListener implements KeyListener{
		public void keyPressed(KeyEvent event){
			//PARA QUE SOLO USE LAS ESCALERAS PARA SUBIR O BAJAR
			//si est? en la escalera de arriba
			if (x>=50 && x<=50+esca1.getIconWidth()-muneco.getIconWidth() && y>=75 && y<=287) {
				switch(event.getKeyCode()){
				case KeyEvent.VK_UP:
					y -= JUMP; break;
				case KeyEvent.VK_DOWN:
					y += JUMP; break;
				case KeyEvent.VK_LEFT:
					x-=MOVE; break;
				case KeyEvent.VK_RIGHT:
					x+=MOVE; break;
				}
				
				//para que no se salga de la escalera ---------------------------- NO FUNCIONA
				if (x<50 && y<176+esca1.getIconHeight()-muneco.getIconHeight()-6) {x += MOVE;}
				if(x>50+esca2.getIconWidth()-muneco.getIconWidth() && y<176+esca1.getIconHeight()-muneco.getIconHeight()-6) {x -= MOVE;}
			}
			
			//si est? en la escalera de abajo
			else if (x>=320 && x<=320+esca2.getIconWidth()-muneco.getIconWidth() && y>=287) {
				switch(event.getKeyCode()){
				case KeyEvent.VK_UP:
					y -= JUMP; break;
				case KeyEvent.VK_DOWN:
					y += JUMP; break;
				case KeyEvent.VK_LEFT:
					x-=MOVE; break;
				case KeyEvent.VK_RIGHT:
					x+=MOVE; break;
				}
				
				//para que no se salga de la escalera
				if (x<320 && y<389+esca1.getIconHeight()-muneco.getIconHeight()-6) {x += MOVE;}
				if(x>320+esca2.getIconWidth()-muneco.getIconWidth() && y<389+esca1.getIconHeight()-muneco.getIconHeight()-6) {x -= MOVE;}
			}
			
			//RESTO DE CASOS
			else {
				switch(event.getKeyCode()){
				case KeyEvent.VK_LEFT:
					x-=MOVE; break;
				case KeyEvent.VK_RIGHT:
					x+=MOVE; break;
				}
			}
			
			//PARA QUE NO SE SALGA EL PROTA DEL PANEL
			if(x>=600-muneco.getIconWidth()) {x -= MOVE;}
			if(x<=0) {x += MOVE;}
			if(y>532){y -= JUMP;}
			
			if (Rc1.intersects(Rm) || Rc2.intersects(Rm) || Rc3.intersects(Rm)) { //abajo
				Toolkit.getDefaultToolkit().beep();
				gameOver();
			}
			if (Rv.intersects(Rm)) { //abajo
				Toolkit.getDefaultToolkit().beep();
				winwin();
			}
			
			repaint();
		}
	
	public void keyTyped(KeyEvent event){}
	public void keyReleased(KeyEvent event){}
	}	
	
	public void gameOver() {
		int dialogButton = JOptionPane.showConfirmDialog (null, "?Quieres volver a jugar?", "Game Over", JOptionPane.YES_NO_OPTION);
		if(dialogButton == JOptionPane.YES_OPTION) {
			x=50; y=532;
			remove(dialogButton);
		}
		else {
			System.exit(0);
		}
	}
	
	public void winwin() {
		int dialogButton = JOptionPane.showConfirmDialog (null, "TE HAS VACUNADO\n?Quieres volver a jugar?", "You win!", JOptionPane.YES_NO_OPTION);
		if(dialogButton == JOptionPane.YES_OPTION) {
			x=50; y=532;
			remove(dialogButton);
		}
		else {
			System.exit(0);
		}
	}

	

}
