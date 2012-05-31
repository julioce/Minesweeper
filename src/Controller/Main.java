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
		
		Field.setUpMap(mapper);
		Field.setUpBlankMap(blankMapper);
		Field.generateBombs(mapper);
		Field.populateBlankMap(mapper, blankMapper);
		Field.printGameInConsole(blankMapper);
		
		//Field.validateBombSpaces(mapper);
		Field.evaluateMap(mapper);
		Field.localSearch(mapper);
		Field.printGame(mapper);
	}

}
