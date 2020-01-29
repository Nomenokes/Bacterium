package main;


public class Calculator {
	public static int posToAbs(int x,int y,int backgroundWidth,int backgroundHeight){
		if(x>=backgroundWidth||y>=backgroundHeight||x<0||y<0)return 0;
		return  y*backgroundWidth+x;
	}
	public static double absToPosX(int abs, int backgroundWidth){
		return abs%backgroundWidth;
	}
	public static double absToPosY(int abs, int backgroundWidth){
		return abs/backgroundWidth;
	}
	public static int getFreeSpot(Object[] objects){
		for(int i=0;i<objects.length;i++){
			if(objects[i]==null){
				return i;
			}
		}
		return -1;
	}
	public static int getFreeSpot(int[] objects){
		for(int i=0;i<objects.length;i++){
			if(objects[i]==0){
				return i;
			}
		}
		return -1;
	}
	public static float getDistanceSquared(double x1, double y1, double x2, double y2){
		float distancex=(float) (x1-x2);
		float distancey=(float) (y1-y2);
		return distancex*distancex+distancey*distancey;
	}
	public static double inaccurate(double init, double inaccuracy){
		return init+(Math.random()-0.5)*init*2*inaccuracy;
	}
	public static int getIntFromColor(int r, int g, int b){
	    return ((r&0x0ff)<<16)|((g&0x0ff)<<8)|(b&0x0ff);
	}
	
}
