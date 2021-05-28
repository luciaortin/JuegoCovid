import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class A_main {

	public static void main(String[] args) {

		JFrame frame = new JFrame("Juego Covid19 - Lucía Ortín");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane tp = new JTabbedPane(); 
		PanelJuego pj = new PanelJuego();
		tp.addTab("Juego Covid19",  pj);
		tp.addTab("Velocidad",  new Sliders(pj));
		frame.getContentPane().add(tp);
		
		frame.pack();
		frame.setVisible(true);
		
	}

}
