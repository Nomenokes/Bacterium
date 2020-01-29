package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import main.Run;

public class GameUI extends JPanel{
	public static final String TAB="        ";
	
	private Run run;
	
	private BoxLayout layout;
	
	private JSlider speed,ss,sq,sm,sm2;
	private JButton reset,brush,viewB,viewE,viewP,viewF,ba,bb,bc,b1,b2,b3;
	private JLabel lspeed,ls,lq,lm;
	
	private JButton settings,simulation,cursor;
	private boolean set,sim,cur;
	private JPanel settingsP,simulationP,cursorP;
	
	public static int vs,vq,vm=1;
	public int cursorType;
	public boolean brushType=false;//false=graduated,true=solid
	
	//private 
	public GameUI(Run run){
		this.run=run;
		
	}
	public void update(){
		//System.out.println(vs);
		//revalidate();
	}
	public void init(){
		
		//this.setLayout(new GridLayout(0,1,0,10));
		layout=new BoxLayout(this,BoxLayout.PAGE_AXIS);
		setLayout(layout);
		
		settingsP=new JPanel();
		simulationP=new JPanel();
		cursorP=new JPanel();
		
		lspeed=new JLabel("Simulation speed");
		ls=new JLabel("Brush size");
		lq=new JLabel("Brush quantity");
		lm=new JLabel("Mutation Rate");
		
		speed=new JSlider(0,100);
		ss=new JSlider(0,200);
		sq=new JSlider(-10,10);
		sm=new JSlider(0,1000);
		sm2=new JSlider(0,10);
		
		settings=new JButton("Settings");
		simulation=new JButton("Simulation");
		cursor=new JButton("Brush");
		reset=new JButton("Reset simulation");
		brush=new JButton("Brush type | Graduated");
		viewB=new JButton("View Bacteria");
		viewE=new JButton("View Environment");
		viewP=new JButton("View Poison");
		viewF=new JButton("View Food");
		ba=new JButton("Poison A");
		bb=new JButton("Poison B");
		bc=new JButton("Poison C");
		b1=new JButton("Food 1");
		b2=new JButton("Food 2");
		b3=new JButton("Food 3");
		
		
	//	settingsP.setPreferredSize(new Dimension(this.getPreferredSize().height/4,this.getPreferredSize().width));
	//	simulationP.setPreferredSize(new Dimension(this.getPreferredSize().height/4,this.getPreferredSize().width));
	//	cursorP.setPreferredSize(new Dimension(this.getPreferredSize().height/4,this.getPreferredSize().width));
	//	settingsP.setSize(this.getPreferredSize());
		
/*		settingsP.setLayout(new GridLayout(0,1,0,10));
		simulationP.setLayout(new GridLayout(0,1,0,10));
		cursorP.setLayout(new GridLayout(0,1,0,10));*/
		settingsP.setLayout(new BoxLayout(settingsP,BoxLayout.PAGE_AXIS));
		simulationP.setLayout(new BoxLayout(simulationP,BoxLayout.PAGE_AXIS));
		cursorP.setLayout(new BoxLayout(cursorP,BoxLayout.PAGE_AXIS));
		
		speed.setName("Game speed");
		speed.setMajorTickSpacing(10);
		speed.setPaintTicks(true);
		speed.setPaintLabels(true);
		
		ss.setMajorTickSpacing(25);
		ss.setPaintTicks(true);
		ss.setPaintLabels(true);
		ss.setValue(0);
		
		sq.setMajorTickSpacing(5);
		sq.setPaintTicks(true);
		sq.setPaintLabels(true);
		
		sm.setMajorTickSpacing(100);
		sm.setPaintTicks(true);
		sm.setPaintLabels(true);
		sm.setValue(1);
		
		sm2.setMajorTickSpacing(1);
		sm2.setPaintTicks(true);
		sm2.setPaintLabels(true);
		sm2.setValue(1);
		
		speed.addChangeListener(new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent arg0) {
				Run.ticksPerSecond=speed.getValue();
			}
		});
		
		ss.addChangeListener(new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent arg0) {
				vs=ss.getValue();
			}
		});
		
		sq.addChangeListener(new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent arg0) {
				vq=sq.getValue();
			}
		});
		
		sm.addChangeListener(new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent arg0) {
				vm=sm.getValue();
			}
		});
		
		sm2.addChangeListener(new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent arg0) {
				vm=sm2.getValue();
			}
		});
		
		reset.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				run.bacteria.resetting=true;
			}
		});
		settings.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				resetMenu();
				add(settingsP);
				revalidate();
				repaint();
				set=true;
			}
		});
		simulation.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				resetMenu();
				add(simulationP);
				revalidate();
				repaint();
				sim=true;
			}
		});
		cursor.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				resetMenu();
				add(cursorP);
				revalidate();
				repaint();
				cur=true;
			}
		});
		brush.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(brushType)brush.setText("Brush type | Graduated");
				else brush.setText("Brush type | Solid");
				brushType=!brushType;
			}
		});
		b1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cursorType=1;
				resetButtons();
				b1.setEnabled(false);
			}
		});
		b2.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cursorType=2;
				resetButtons();
				b2.setEnabled(false);
			}
		});
		b3.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cursorType=3;
				resetButtons();
				b3.setEnabled(false);
			}
		});
		ba.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cursorType=4;
				resetButtons();
				ba.setEnabled(false);
			}
		});
		bb.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cursorType=5;
				resetButtons();
				bb.setEnabled(false);
			}
		});
		bc.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cursorType=6;
				resetButtons();
				bc.setEnabled(false);
			}
		});
		viewB.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				run.bacteria.displaying=0;
			}
		});
		viewE.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				run.bacteria.displaying=1;
			}
		});
		viewP.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				run.bacteria.displaying=2;
			}
		});
		viewF.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				run.bacteria.displaying=3;
			}
		});
		
		
		add(new JLabel("Menu"));
	//	add(new JLabel());
		
		settingsP.add(reset);
		settingsP.add(new JLabel("Made by Bryce Costawong"));
		settingsP.add(new JLabel());
		
		simulationP.add(lspeed);
		simulationP.add(speed);
		simulationP.add(lm);
		simulationP.add(sm2);
		simulationP.add(sm);
		simulationP.add(viewB);
		simulationP.add(viewE);
		simulationP.add(viewF);
		simulationP.add(viewP);
		simulationP.add(new JLabel());
		
		cursorP.add(ls);
		cursorP.add(ss);
		cursorP.add(lq);
		cursorP.add(sq);
		cursorP.add(brush);
		cursorP.add(new JLabel());
		cursorP.add(new JLabel("Brush type:"));
		cursorP.add(b1);
		cursorP.add(b2);
		cursorP.add(b3);
		cursorP.add(ba);
		cursorP.add(bb);
		cursorP.add(bc);
		cursorP.add(new JLabel());
		
		add(settings);
		add(simulation);
		add(cursor);
		add(new JLabel("------------------------------------------------------------------------------------------------------------------------------------------------"));
		
		
		revalidate();
	}
	private void resetMenu(){
		if(sim){
			remove(simulationP);
			sim=false;
		}
		if(set){
			remove(settingsP);
			set=false;
		}
		if(cur){
			remove(cursorP);
			cur=false;
		}
	}
	private void resetButtons(){
		b1.setEnabled(true);
		b2.setEnabled(true);
		b3.setEnabled(true);
		ba.setEnabled(true);
		bb.setEnabled(true);
		bc.setEnabled(true);
	}
}
