package Controller;

import java.util.ArrayList;

import Model.Field;
import Model.MatrixPosition;

import View.Window;

public class Main {

	static Window view;
	
	public static int mapper[][];

	public static int blankMapper[][];
	
	public static void main(String[] args) {
		/* Inicializa a GUI */
		view = new Window();
	}
	
	public static void generateField() {

		mapper = new int[Field.lines][Field.columns];
		blankMapper = new int[Field.lines][Field.columns];
		
		Field.bombPosition = new ArrayList<MatrixPosition>();
		
		Field.addText("General Field Information\n----------------------\n");
		Field.setUpMap(mapper);
		Field.setUpBlankMap(blankMapper);
		Field.generateBombs(mapper);
		Field.evaluateGame();
		Field.evaluateMap(mapper);
		Field.addText("----------------------\n\n");
		
		Field.populateBlankMap(mapper, blankMapper);
		//Field.printGameInConsole(blankMapper);
		
		Field.localSearch(mapper, blankMapper);
		
		Field.printGame(mapper);
	}

}
