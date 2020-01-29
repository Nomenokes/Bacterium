package main;

import input.InputHandler;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import render.Renderer;
import ui.Menu;
import bacteria.BacteriaHandler;

public class Run {
	
	public String name="Bacterium";
	
	public static int ticksPerSecond=21;
	public boolean running;
	public boolean paused;
	
	private int frameHeight,frameWidth;
	public JFrame frame;
	public JPanel game;
	public Canvas canvas;
	public int canvasHeight,canvasWidth;
	
	public Renderer renderer;
	
	public BacteriaHandler bacteria;
	public InputHandler input;
	public Menu menu;
	
	public static void main(String[] args){
		new Run();
		try{
			throw new java.lang.UnknownError();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public Run(){
		System.out.println("Starting.");
		start();
		loop();
		System.out.println("Closing.");
	}
	public void start(){
		
		frameHeight=Toolkit.getDefaultToolkit().getScreenSize().height;
		frameWidth=Toolkit.getDefaultToolkit().getScreenSize().width;
		canvasHeight=(int) (frameHeight*(19d/20));
		canvasWidth=canvasHeight;
		
		frame=new JFrame(name);
		frame.setSize(new Dimension((int)frameWidth/2,(int)frameHeight/2));
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		game=new JPanel();
		
		game.setSize(new Dimension(canvasWidth,canvasHeight));
		
		canvas=new Canvas();
		canvas.setSize(game.getSize());
		game.add(canvas);
		
		frame.add(game,BorderLayout.CENTER);
		
		Thread.currentThread().setPriority(Thread.NORM_PRIORITY);
		
		
		renderer=new Renderer(this);
		bacteria=new BacteriaHandler(this);//must make renderer first
		input=new InputHandler(this);
		menu=new Menu(this);
		
		menu.setPreferredSize(new Dimension((int) ((frameWidth-canvasWidth)*(5d/6)), canvasHeight));
		canvas.addKeyListener(input);
		canvas.addMouseListener(input);
		frame.add(menu,BorderLayout.EAST);
		menu.init();
		
		
		
		running=true;
		paused=false;
		
		frame.revalidate();
	}
	@SuppressWarnings("unused")
	public void loop(){
		long current=0;
		long last=0;
		long tickCount=0;
		
		long before=0;
		long lag=0;
		
		long nanosecondsPerTick=0;
		
		while(running){
			current=System.nanoTime();
			
			if(ticksPerSecond>0){nanosecondsPerTick=(1000000000/ticksPerSecond);paused=false;}
			else paused=true;
			
			if(current>=last+nanosecondsPerTick){
				if(!paused){
					last=current;
					before=System.nanoTime();
					
					tick(tickCount);
					tickCount++;
					
					lag=System.nanoTime()-before;
				}
			}
			menu.update();
			renderer.render();
		}
	}
	public void tick(long count){
		bacteria.tick();
	}
}
