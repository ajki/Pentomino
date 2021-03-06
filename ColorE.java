package Pentomino;

import java.awt.*;
	
public class ColorE extends Color{
		
	private static Color white;
	
	public static  Color color(){		
		Color b=Color.white;
		//Color color=new color(int r,int g,int b);
		//color=r.setColor();		
		return b;
	}
	
	/**
	 * picks a random color
	 * @return the random color
	 */
	public static  Color colorM(){			
		int R = (int)(Math.random()*256);
		int G = (int)(Math.random()*256);
		int B = (int)(Math.random()*256);
		Color color= new Color(R,G,B);			
		return color;
	}
	
	/**
	 * getter for color in integer from
	 * @param R red
	 * @param G green
	 * @param B blue
	 * @return color in integer form
	 */
	public int getColorInt(float R ,float G,float  B){
		
		// r =Math.round(255*r);
		// r = (r << 16) & 0x00FF0000;
		//return r;
		
		//color d= r;
		//Color d=setColor(r);
		//Color d =Color.get(r);
			
		R=(int)(Math.random()*256);
		G=(int)(Math.random()*256);
		B=(int)(Math.random()*256);
		Color color= new Color(R,G,B);
			
		return color.getRGB();
	}
	
	/**
	 * contructor for color
	 * @param R red 
	 * @param G green
	 * @param B blue
	 */
	public ColorE(float R, float G, float B){
		super(R,G,B);	
	}
		
	public static void main(String[] args){
			
		int a=0;//((int)0-256);
		int b=0;//((int)0-256);
		int c=0;//((int)0-256);
			
		ColorE r = new ColorE(a,b,c);
		Color standartWhite = ColorE.color();
			
	}
				
}
				
			
			
		