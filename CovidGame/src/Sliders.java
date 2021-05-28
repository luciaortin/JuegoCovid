import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

public class Sliders extends JPanel{

	private final int ancho=600, alto=700;
	private JPanel controls;
	private JSlider PrimerCovid, SegundoCovid, TercerCovid;
	private JLabel PrimerCovidLable, SegundoCovidLable, TercerCovidLable;
	private PanelJuego velocidad, covid1, covid2, covid3;
	
	public Sliders(PanelJuego pj){
	
		PrimerCovid = new JSlider(JSlider.HORIZONTAL, 0, 10, 2);
		PrimerCovid.setMajorTickSpacing(1);
		PrimerCovid.setMinorTickSpacing(1);
		PrimerCovid.setPaintTicks(true);
		PrimerCovid.setPaintLabels(true);
		PrimerCovid.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		SegundoCovid = new JSlider(JSlider.HORIZONTAL, 0, 10, 5);
		SegundoCovid.setMajorTickSpacing(1);
		SegundoCovid.setMinorTickSpacing(1);
		SegundoCovid.setPaintTicks(true);
		SegundoCovid.setPaintLabels(true);
		SegundoCovid.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		TercerCovid = new JSlider(JSlider.HORIZONTAL, 0, 10, 10);
		TercerCovid.setMajorTickSpacing(1);
		TercerCovid.setMinorTickSpacing(1);
		TercerCovid.setPaintTicks(true);
		TercerCovid.setPaintLabels(true);
		TercerCovid.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		SliderListener listener = new SliderListener();
		PrimerCovid.addChangeListener(listener);
		SegundoCovid.addChangeListener(listener);
		TercerCovid.addChangeListener(listener);
		
		PrimerCovidLable = new JLabel("Covid 1 (arriba)");
		PrimerCovidLable.setAlignmentX(Component.LEFT_ALIGNMENT);
		SegundoCovidLable = new JLabel("Covid 2 (centro)");
		SegundoCovidLable.setAlignmentX(Component.LEFT_ALIGNMENT);
		TercerCovidLable = new JLabel("Covid 3 (abajo)");
		TercerCovidLable.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		controls = new JPanel();
		BoxLayout layout = new BoxLayout(controls, BoxLayout.PAGE_AXIS);
		controls.setLayout(layout);
		
		controls.add(Box.createRigidArea(new Dimension(0, 180)));
		controls.add(PrimerCovidLable);
		controls.add(PrimerCovid);
		
		controls.add(Box.createRigidArea(new Dimension(0, 50)));
		controls.add(SegundoCovidLable);
		controls.add(SegundoCovid);
		
		controls.add(Box.createRigidArea(new Dimension(0, 50)));
		controls.add(TercerCovidLable);
		controls.add(TercerCovid);
		
		velocidad = pj; 
		velocidad.setVel1(2);
		velocidad.setVel2(5);
		velocidad.setVel3(10);
		
		add(controls);
	}
	
	
	private class SliderListener implements ChangeListener{
		public int vel1, vel2, vel3;
		public void stateChanged(ChangeEvent event){
			vel1 = PrimerCovid.getValue();
			vel2 = SegundoCovid.getValue();
			vel3 = TercerCovid.getValue();
			
			PrimerCovidLable.setText("Covid 1 (abajo): " + vel1);
			SegundoCovidLable.setText("Covid 2 (centro): "+ vel2);
			TercerCovidLable.setText("Covid 3 (arriba): " + vel3);
			
			velocidad.setVel1(vel1);
			velocidad.setVel2(vel2);
			velocidad.setVel3(vel3);
			
		}
	}
	
	
}
