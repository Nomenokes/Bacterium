package bacteria;

import ui.GameUI;
import main.Calculator;

public class Bacterium {
	public int color;
	public final int pos;
	private Tile tile;
	private BacteriaHandler parent;
	public static final float FOOD_TO_DIVIDE=10;
	
	public boolean alive;
	
	public float 
	
	m1,
	m2,
	m3,
	
	ra,
	rb,
	rc,
	
	pa,
	pb,
	pc;
	
	public float meta,food;
	
	public Bacterium(BacteriaHandler parent, Tile t, int pos){
		this.pos=pos;
		this.parent=parent;
		tile=t;
	}
	
	public void tick(){
		food+=calcIncome()-meta;
		if(food<0)alive=false;
		if(food>FOOD_TO_DIVIDE)divide();
		
		
		
	}
	public void preTick(){
		createPoison();
	}
	public void calcInitial(){
		float val=0.5f;
		meta=0;
		if(m1>0)meta+=val;
		if(m2>0)meta+=val;
		if(m3>0)meta+=val;
		if(ra>0)meta+=val;
		if(rb>0)meta+=val;
		if(rc>0)meta+=val;
		if(pa>0)meta+=val;
		if(pb>0)meta+=val;
		if(pc>0)meta+=val;
		meta+=m1*m1+m2*m2+m3*m3+ra+rb+rc+pa+pb+pc;
		//System.out.println(ra);
		color=Calculator.getIntFromColor(50*(int)(m1+m2+m3), 10*(int)(ra+rb+rc), 80*(int)(pa+pb+pc));
		//color=new Color(250,250,0).getRGB();
	}
	public void change(float m1, float m2, float m3, float ra,float rb,float rc, float pa,float pb,float pc){
		this.m1=m1;this.m2=m2;this.m3=m3;this.ra=ra;this.rb=rb;this.rc=rc;this.pa=pa;this.pb=pb;this.pc=pc;
		food=0;
		alive=true;
		calcInitial();
	}
	private float calcIncome(){
		float returning=0;
		returning=
				m1*tile.n1
				+m2*tile.n2
				+m3*tile.n3;
		if(tile.na>0.0001)try{returning-=tile.na/(ra*2+pa)*4;}catch(ArithmeticException e){returning=0;}
		if(tile.nb>0.0001)try{returning-=tile.nb/(rb*2+pb)*4;}catch(ArithmeticException e){returning=0;}
		if(tile.nc>0.0001)try{returning-=tile.nc/(rc*2+pc)*4;}catch(ArithmeticException e){returning=0;}
		//System.out.println(returning);
		return returning;
	}
	private void divide(){
		double rand=Math.random();
		int width=parent.run.renderer.backgroundWidth;/*TODO clean up messy casting*/
		if(rand<1f/5){if((float)pos%((float)width)!=0 && pos+1<parent.tiles.length){parent.add(pos+1, mutate(m1), mutate(m2), mutate(m3), mutate(ra), mutate(rb), mutate(rc), mutate(pa), mutate(pb), mutate(pc));}}
		else if(rand<2f/5){if((float)pos%((float)width)!=1 && pos-1>=0){parent.add(pos-1, mutate(m1), mutate(m2), mutate(m3), mutate(ra), mutate(rb), mutate(rc), mutate(pa), mutate(pb), mutate(pc));}}
		else if(rand<3f/5){if(pos+width<parent.tiles.length){parent.add(pos+width, mutate(m1), mutate(m2), mutate(m3), mutate(ra), mutate(rb), mutate(rc), mutate(pa), mutate(pb), mutate(pc));}}
		else if(rand<4f/5){if(pos-width>=0){parent.add(pos-width, mutate(m1), mutate(m2), mutate(m3), mutate(ra), mutate(rb), mutate(rc), mutate(pa), mutate(pb), mutate(pc));}}
		else {parent.add(pos, mutate(m1), mutate(m2), mutate(m3), mutate(ra), mutate(rb), mutate(rc), mutate(pa), mutate(pb), mutate(pc));}
		
		food=1;
	}
	private static float mutate(float orig){
		if(orig<0.0001){
			orig=0;
			if(Math.random()<(.5/10000f)*GameUI.vm)//1/64 chance of gaining attribute
				orig=(float) Math.random();
		}else{
			if(Math.random()<(4/10000f)*GameUI.vm){//1/8 chance of random gain/loss
				orig+=Math.random()-Math.random();
				if(orig<0)orig=0;
			}
			else if(Math.random()<(.5/10000f)*GameUI.vm)//1/64 (7/512 actual) chance of losing attribute
				orig=0;
		}
		return orig;
	}
	private void createPoison(){
		if(pa>0.0001||pb>0.0001||pc>0.0001){
			tile.na+=pa;
			tile.nb+=pb;
			tile.nc+=pc;
			
			int width=parent.run.renderer.backgroundWidth;/*TODO clean up messy casting*/
			if((float)pos%((float)width)!=0 && pos+1<parent.tiles.length){
				parent.tiles[pos+1].na+=pa;
				parent.tiles[pos+1].nb+=pb;
				parent.tiles[pos+1].nc+=pc;
				parent.tiles[pos+1].calculating=true;
			}
			if((float)pos%((float)width)!=1 && pos-1>=0){
				parent.tiles[pos-1].na+=pa;
				parent.tiles[pos-1].nb+=pb;
				parent.tiles[pos-1].nc+=pc;
				parent.tiles[pos-1].calculating=true;
			}
			if(pos-width>=0){
				parent.tiles[pos-width].na+=pa;
				parent.tiles[pos-width].nb+=pb;
				parent.tiles[pos-width].nc+=pc;
				parent.tiles[pos-width].calculating=true;
			}
			if(pos+width<parent.tiles.length){
				parent.tiles[pos+width].na+=pa;
				parent.tiles[pos+width].nb+=pb;
				parent.tiles[pos+width].nc+=pc;
				parent.tiles[pos+width].calculating=true;
			}
		}
		
	}
}
