package Pentomino;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;

import Pentomino.Interfaces.Control;

public class Board {
	Square[][] board;
	Square[][] shadowBoard;
	Pentomino livingPentomino;
	Pentomino shadowPentomino;
	private boolean rotatePressed;
	private boolean endgame;
	protected int currentScore;
	protected Highscore score= new Highscore();
	
	
	protected boolean isEndgame() {
		return endgame;
	}
	public int getcurrentScore(){
		return currentScore;
	}
	
	/**
	 * constructor that creates the board full of empty squares
	 * @param gameWidth
	 * @param gameHeight
	 */
	public Board(int gameWidth, int gameHeight){
		board = new Square[gameHeight][gameWidth];
		score = new Highscore();
		shadowBoard = new Square[gameHeight][gameWidth];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				board[i][j]=new Square(i, j);
			}
		}
		
	}
	
	/**
	 * checks for full lines and returns an array list containing their indexes
	 * @return
	 */
	public ArrayList<Integer> checkForFullLines(){
		ArrayList<Integer> fullLines = new ArrayList<Integer>();
		for (int i = 0;i<board.length;i++){
			int count = 0;
			for (int j = 0;j<board[1].length;j++){
				if (board[i][j].getC().getRGB()!=Color.GRAY.getRGB())count++;
			}
			if (count == board[1].length){
								
				fullLines.add(i);
				currentScore= score.score(currentScore, fullLines.size());
				PentominoMain.scoreLabel2.setText("                  " +Integer.toString(currentScore));

			}
		}
		
		return fullLines;
	}
	
	/**
	 * removes the full lines and moves the rest of the pieces down and returns the number of lines
	 * @param fullLines
	 * @return
	 */
	public int removeFullLines(ArrayList<Integer> fullLines){
		int lines = fullLines.size()*fullLines.size();
		for (Integer line : fullLines) {
			for (int x=0;x<board[line].length;x++){
				if (line==0)board[line][x] = new Square(board[line][x].getX(),board[line][x].getY());
				else{
					for (int y=line;y>0;y--){
						board[y][x] = board[y-1][x];
						board[y][x].setY(board[y][x].getY()+1);
					}
					board[0][x] = new Square(board[0][x].getX(),board[0][x].getY());
				}				
			}
		}
		return lines;
		
	}
	
	/**
	 * getter for a full board
	 * @return the full board
	 */
	public Square[][] getFullBoard(){
		return board;
	}
	
	/**
	 * getter for the current pentomino
	 * @return the current pentomino
	 */
	public Pentomino getLivingPentomino(){
		return livingPentomino;
	}
	
	/**
	 * moves the current pentomino all the way down
	 */
	public void setLivingPentominoDown(){
		System.out.println("puff");
		if (livingPentomino==null)return;
		if (livingPentomino.above(0)){
			System.out.println("endgame");
			this.endgame = true;
			return;
		}
		Square[] s = livingPentomino.getSquares();
		for (int i= 0;i<s.length;i++){
			Square ss = s[i];//singlesquare
			board[ss.getY()][ss.getX()]=ss;
		}
		livingPentomino=null;
	}
	/**
	 * moves current pentomino down by 1
	 */
	public void moveLivingPentominoOneTick(){
		if (livingPentomino==null) {
			livingPentomino = new Pentomino();
			livingPentomino.moveX(board[1].length/2);
			while(livingPentomino.right(board[1].length-1))livingPentomino.moveX(-1);
			while(livingPentomino.left(0))livingPentomino.moveX(1);
			livingPentomino.moveY(-1);
			return;
		}
		while(livingPentomino.right(board[1].length-1))livingPentomino.moveX(-1);
		while(livingPentomino.left(0))livingPentomino.moveX(1);
		livingPentomino.moveY(1);
		
		if (livingPentomino.completlyBelow(board.length)) {
			livingPentomino = new Pentomino();
			livingPentomino.moveX(board[1].length/2);
			while(livingPentomino.right(board[1].length))livingPentomino.moveX(-1);
			while(livingPentomino.left(0))livingPentomino.moveX(1);
			livingPentomino.moveY(-1);
			return;
		}
	}
	
	/**
	 * method for moving pentomino on the board
	 * @param c
	 * @param reverseXY
	 */
	public void moveLivingPentomino(Control c, boolean reverseXY) {
		if (livingPentomino==null)return;
		
			if (c.isButtonPressed(Control.Buttons.RotateRight) && !rotatePressed){
				
				livingPentomino.rotate(true^reverseXY);
				
				rotatePressed=true;
				if(isCollision()){
					livingPentomino.rotate(false^reverseXY);
					rotatePressed=false;
				}
			}
			if (c.isButtonPressed(Control.Buttons.RotateLeft)  && !rotatePressed){
				livingPentomino.rotate(false^reverseXY);
				rotatePressed=true;
				if(isCollision()){
					livingPentomino.rotate(true^reverseXY);
					rotatePressed=false;
				}
			}
			if (!c.isButtonPressed(Control.Buttons.RotateLeft) && !c.isButtonPressed(Control.Buttons.RotateRight)) rotatePressed=false;
		if (!reverseXY){
		
		if (c.isButtonPressed(Control.Buttons.Left) ){
			livingPentomino.moveX(-1);
			if(isCollision()){livingPentomino.moveX(1);}
			
		}
		if (c.isButtonPressed(Control.Buttons.Down) ){
			livingPentomino.moveY(1);
			if(isCollision()){livingPentomino.moveY(-1);}
		}
		if (c.isButtonPressed(Control.Buttons.Right) ){
			livingPentomino.moveX(1);
			if(isCollision()){livingPentomino.moveX(-1);}
		}
		if (c.isButtonPressed(Control.Buttons.AllTheWayDown) ){
			do{
			livingPentomino.moveY(1);
			
			}while(!isCollision());
			
			livingPentomino.moveY(-1);
			
		}
		}
		
		
		
	}

	
	/**
	 * moves pentomino to given X and Y position
	 * @param y
	 * @param x
	 */
	public void moveLivingPentomino(int y, int x) {
		if (livingPentomino==null)return;
		livingPentomino.moveX(x);
		livingPentomino.moveY(y);
		
	}
/*
	public ArrayList<Square[][]> getFullBoardShadow() {
		//remember this ArrayList<Square>[][];
		 for (int i = 0; i < board.length; i++) {
			 shadowBoard[i] = Arrays.copyOf(board[i], board[i].length);
		 }
		 //maybeInverse here, for the 4d part....
		 
		 
		 
		Square[][] shadowBoard2 = new Square[board.length][board[0].length];
		 for (int j = 0; j < board.length; j++) {
			 shadowBoard2[j] = Arrays.copyOf(board[j], board[j].length);
			}
		 ArrayList<Square[][]> asd = new ArrayList<Square[][]>();
		 asd.add(shadowBoard);
		 asd.add(shadowBoard2);
		return asd;
	}
*/
	
	/**
	 * setter for the board
	 * @param extracted
	 */
	public void setFullBoard(Square[][] extracted) {
		board = extracted;
		
	}

	/**
	 * checks for collision
	 * @return true if there is a collision
	 */
	public boolean isCollision() {
		Pentomino p = getLivingPentomino();
		if (p==null)return false;
		for (Square s : p.getSquares()) {
			Square[][] ss =board;
			int y =s.getY();
			int x =s.getX();
			if (y<0)continue;
			if (y>=ss.length)return true;
			if (x<0) return true;
			if (x>=ss[0].length)return true;
			if(!ss[s.getY()][s.getX()].getC().equals(Color.GRAY)){
				return true;
			}
			
		}
		return false;
	}
	
	
	
}