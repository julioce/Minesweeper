package Controller;

import java.util.ArrayList;

import Model.Field;
import Model.MatrixPosition;

import View.Window;

public class Main {

	static Window view;
	
	static int mapper[][];

	public static void main(String[] args) {
		/* Inicializa a GUI */
		view = new Window();
	}
	
	public static void generateField() {

		mapper = new int[Field.lines][Field.columns];
		Field.bombPosition = new ArrayList<MatrixPosition>();
		
		Field.setUpMap(mapper);
		Field.generateBombs(mapper);
		Field.validateBombSpaces(mapper);
		Field.evaluateMap(mapper);
		
		
		//fazendo a avaliação das densidades das bombas
		for (MatrixPosition bombPos :Field.bombPosition) {
			int[][] localMapper = Field.GetLocalMap(mapper, bombPos);
			Field.printGameInConsole(localMapper);
			Field.evaluateMap(localMapper);
		}
		
		Field.printGame(mapper);
	}
	
	public static void setSize(int linesValue, int columnsValue) {
		Field.lines = linesValue;
		Field.columns = columnsValue;
	}

	public static void setDifficulty(int i) {
		Field.difficulty = i;
	}

	public static void setBombQuantity(int bombs) {
		Field.bombQuantity = bombs;
	}

}
