package Controller;

import java.util.ArrayList;

import Model.Field;
import Model.MatrixPosition;

import View.Window;

public class Main {

	static Window view;

	public static void main(String[] args) {
		/* Inicializa a GUI */
		view = new Window();
	}
	
	public static void generateField(){

		int mapper[][] = new int[Field.lines][Field.columns];
		Field.bombPosition = new ArrayList<MatrixPosition>();
		
		Field.setUpMap(mapper);
		Field.generateBombs(mapper);
		Field.validateBombSpaces(mapper);
		Field.evaluateMap(mapper);
		Field.printGame(mapper);
	}
	
	public static void setSize(int linesValue, int columnsValue) {
		Field.lines = linesValue;
		Field.columns = columnsValue;
	}

	public static void setDifficulty(int i) {
		Field.difficulty = i;
	}

}
