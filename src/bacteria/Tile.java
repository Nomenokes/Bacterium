package bacteria;

import main.Calculator;

public class Tile {
	public float 
	n1,n2,n3,
	na,nb,nc;
	
	public float naturalA,naturalB,naturalC;
	
	public int color;
	public int colorP;
	public int colorF;
	public boolean calculating=false;
	
	public void tick(){
		if(calculating || Math.round(na)!=Math.round(naturalA) || Math.round(nb)!=Math.round(naturalB) || Math.round(nc)!=Math.round(naturalC))calcColor();
		
		na-=(na-naturalA)/10;
		nb-=(nb-naturalB)/10;
		nc-=(nc-naturalC)/10;
		
		if(n1<0)n1=0;
		if(n2<0)n2=0;
		if(n3<0)n3=0;
		if(na<0)na=0;
		if(nb<0)nb=0;
		if(nc<0)nc=0;
		
		if(n1>12.75)n1=12.75f;
		if(n2>12.75)n2=12.75f;
		if(n3>12.75)n3=12.75f;
		if(na>12.75)na=12.75f;
		if(nb>12.75)nb=12.75f;
		if(nc>12.75)nc=12.75f;
		if(naturalA>12.75)naturalB=12.75f;
		if(naturalB>12.75)naturalB=12.75f;
		if(naturalC>12.75)naturalB=12.75f;
		
	//	System.out.println(naturalA);
	}
	
	public void calcColor(){
		color=Calculator.getIntFromColor((int)(6.66*(na+nb+nc)),0,(int)(6.66*(n1+n2+n3)));
		colorP=Calculator.getIntFromColor((int)(20*na),(int)(20*nb),(int)(20*nc));
		colorF=Calculator.getIntFromColor((int)(20*n1),(int)(20*n2),(int)(20*n3));
		calculating=false;
	}
}
