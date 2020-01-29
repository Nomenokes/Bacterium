package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import main.Calculator;
import main.Run;

public class InputHandler implements KeyListener, MouseListener{
	private Run run;
	
	
	
	public InputHandler(Run run){
		this.run=run;
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		//System.out.println(arg0.getKeyCode());
	//	if(arg0.getKeyCode()==KeyEvent.VK_UP)Run.ticksPerSecond+=2;
	//	else if(arg0.getKeyCode()==KeyEvent.VK_DOWN)Run.ticksPerSecond-=2;
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		if(arg0.getKeyCode()==KeyEvent.VK_ESCAPE)run.paused=!run.paused;
		
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		
		
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		click(arg0);
		
	}
	private void click(MouseEvent mouse){
		int backgroundWidth=run.renderer.backgroundWidth;
		int backgroundHeight=run.renderer.backgroundHeight;
		int x=(int)screenToGamePosX(mouse.getX(),backgroundWidth);
		int y=(int)screenToGamePosY(mouse.getY(),backgroundHeight);
		int type=run.menu.game.cursorType;
		int size=run.menu.game.vs;
		double quantity=run.menu.game.vq;
		
		if(run.menu.game.brushType){
			for(int w=x-size;w<=x+size;w++){
				for(int h=y-size;h<=y+size;h++){
					int p=Calculator.posToAbs(w, h, backgroundWidth, backgroundHeight);
					if(p>=0&&p<backgroundWidth*backgroundHeight){
						run.bacteria.tile(
								p,
								type,
								quantity
								);
						run.bacteria.tiles[p].calculating=true;
					}
				}
			}
		}
		else{
			quantity=quantity/size;
			for(int i=0;i<=size;i++){
				for(int w=x-i;w<=x+i;w++){
					for(int h=y-i;h<=y+i;h++){
						int p=Calculator.posToAbs(w, h, backgroundWidth, backgroundHeight);
						if(p>=0&&p<backgroundWidth*backgroundHeight){
							run.bacteria.tile(
									p,
									type,
									quantity
									);
							run.bacteria.tiles[p].calculating=true;
							
						}
					}
				}
			}
		}
		
	}
	
	public double screenToGamePosX(int screenPos, int backgroundWidth){
		double ratio=(double)screenPos/run.canvasWidth;
		return backgroundWidth*ratio;
	}
	public double screenToGamePosY(int screenPos, int backgroundHeight){
		double ratio=(double)screenPos/run.canvasHeight;
		return backgroundHeight*ratio;
	}
	
}
