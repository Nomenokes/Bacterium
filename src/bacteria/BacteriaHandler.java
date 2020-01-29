package bacteria;

import main.Run;

public class BacteriaHandler {
	public static final float g=0.11f;
	public Run run;
	private Bacterium[] bacteria;
	public Tile[] tiles;
	
	public int displaying;//0=bacteria,1=env,2=poison,3=food
	public boolean resetting=false;
	
	public BacteriaHandler(Run run){
		this.run=run;
		reset();
	}
	
	public void tick(){
		if(resetting){reset();resetting=false;}
		for(int i=0;i<bacteria.length;i++){
			tiles[i].tick();
		}
		for(int i=0;i<bacteria.length;i++){
			if(bacteria[i].alive)bacteria[i].preTick();
		}
		for(int i=0;i<bacteria.length;i++){
			if(bacteria[i].alive)bacteria[i].tick();
		}
	}
	public int[] render(){
		int[] returning=new int[bacteria.length];
		if(displaying==0){
			for(int i=0;i<returning.length;i++){
				if(bacteria[i].alive)returning[i]=bacteria[i].color;
			}
		}
		if(displaying==1){
			for(int i=0;i<returning.length;i++){
				returning[i]=tiles[i].color;
			}
		}
		if(displaying==2){
			for(int i=0;i<returning.length;i++){
				returning[i]=tiles[i].colorP;
			}
		}
		if(displaying==3){
			for(int i=0;i<returning.length;i++){
				returning[i]=tiles[i].colorF;
			}
		}
		return returning;
	}
	
	public void add(int spot, float m1, float m2, float m3, float ra,float rb,float rc, float pa,float pb,float pc){
		if(bacteria[spot].alive){
			if(Math.random()<0.5)return;
		}
		bacteria[spot].change(m1, m2, m3, ra, rb, rc, pa, pb, pc);
	}
	public void tile(int pos,int type, double quantity){
		//if(pos<0||pos>=run.renderer.backgroundHeight*run.renderer.backgroundWidth)return;
		if(type==1)tiles[pos].n1+=quantity;
		if(type==2)tiles[pos].n2+=quantity;
		if(type==3)tiles[pos].n3+=quantity;
		if(type==4)tiles[pos].naturalA+=quantity;
		if(type==5)tiles[pos].naturalB+=quantity;
		if(type==6)tiles[pos].naturalC+=quantity;
	}
	public void reset(){
		bacteria=new Bacterium[run.renderer.backgroundWidth*run.renderer.backgroundHeight];
		tiles=new Tile[bacteria.length];
		for(int i=0;i<bacteria.length;i++){
			Tile t=new Tile();
			bacteria[i]=new Bacterium(this,t,i);
			tiles[i]=t;
			
			tiles[i].n1=12;
			if(i<run.renderer.backgroundHeight*run.renderer.backgroundWidth/2){
			//	tiles[i].n1=10;
			}
			if(i%run.renderer.backgroundWidth<run.renderer.backgroundWidth/2){
			//	tiles[i].naturalA=10;
			//	tiles[i].naturalB=10;
			//	tiles[i].naturalC=10;
			}
			tiles[i].calcColor();
		}
		
		int middle=bacteria.length/2+run.renderer.backgroundWidth/2;
		add(middle, 1, 0, 0, 0, 0, 0, 0, 0, 0);
	}
	
}
