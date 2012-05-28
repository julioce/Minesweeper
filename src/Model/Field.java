package Model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import View.Button;
import View.Window;

public class Field extends MatrixUtil{
	public static ArrayList<MatrixPosition> bombPosition;
	public static int lines;
	public static int columns;
	public static int difficulty;
	public static int bombQuantity;

	public static void evaluateMap(int[][] mapper) {
		int values[] = {0, 0, 0, 0, 0, 0, 0, 0};
		double evaluationGrade = 0;
		
		for (int i=0; i< mapper.length; i++){
			for(int j=0; j < mapper[0].length; j++){
				switch (mapper[i][j]) {
				case 1:
					values[0]++; 
					break;
				case 2:
					values[1]++; 
					break;
				case 3:
					values[2]++; 
					break;
				case 4:
					values[3]++; 
					break;
				case 5:
					values[4]++; 
					break;
				case 6:
					values[5]++; 
					break;
				case 7:
					values[6]++; 
					break;
				case 8:
					values[7]++; 
					break;
				default:
					break;
				}
				
			}
		}
		
		for (int i = 1; i < values.length; i++) {
			values[i] = values[i]*i;
			evaluationGrade += values[i];
		}
		
		evaluationGrade = evaluationGrade/(lines*columns);
		
		System.out.println("Difficulty evaluation = " + evaluationGrade);
	}

	public static void setUpMap(int mapper[][]){
		for (int i=0; i< mapper.length; i++){
			for(int j=0; j < mapper[0].length; j++){
				mapper[i][j] = 0;
			}
		}
	}
	
	public static void generateBombs(int mapper[][]){
		int insertedBombs = 0;
		int conflicts = 0;
		
		Random randGenerator = new Random();
		
		for(int i=0; i < bombQuantity; i++){
			
			int randLine = randGenerator.nextInt() % mapper.length;
			if(randLine < 0){
				randLine*=-1;
			}
			
			int randColumn = randGenerator.nextInt() % mapper[0].length;
			if(randColumn < 0){
				randColumn*=-1;
			}
			
			if(mapper[randLine][randColumn] != -1){
				//adicionando bomba
				mapper[randLine][randColumn] = -1;
				
				bombPosition.add(new MatrixPosition(randLine,randColumn));
				
				insertedBombs ++;
				MatrixPosition pos = new MatrixPosition(randLine, randColumn);
				UpdateNearFields(mapper,pos);
			}else{
				i--;
				conflicts++;
			}
		}
		
		System.out.println("Bomb space conflicts = " + conflicts);
		System.out.println("Inserted bombs = " + insertedBombs);
	}
	
	public static void UpdateNearFields(int mapper[][],MatrixPosition pos){
		int newPosX = 0;
		int newPosY = 0;

		//1 - UP - subo em x
		if(isNearValid(mapper, pos, NearPositionEnum.N)){
			
			newPosX = pos.X + NearPositionEnum.N.x;
			newPosY = pos.Y  + NearPositionEnum.N.y;
			if(mapper[newPosX][newPosY] != -1){
				mapper[newPosX][newPosY]++;
			}
		}
		//2 - UP, RIGHT
		if(isNearValid(mapper, pos, NearPositionEnum.NE)){
			
			newPosX = pos.X + NearPositionEnum.NE.x;
			newPosY = pos.Y  + NearPositionEnum.NE.y;
			if(mapper[newPosX][newPosY] != -1){
				mapper[newPosX][newPosY]++;
			}
		}
		//3 - RIGHT
		if(isNearValid(mapper, pos, NearPositionEnum.E)){
			
			newPosX = pos.X + NearPositionEnum.E.x;
			newPosY = pos.Y  + NearPositionEnum.E.y;
			if(mapper[newPosX][newPosY] != -1){
				mapper[newPosX][newPosY]++;
			}
		}		
		//4 - RIGHT - DOWN	
		if(isNearValid(mapper, pos, NearPositionEnum.SE)){
			
			newPosX = pos.X + NearPositionEnum.SE.x;
			newPosY = pos.Y  + NearPositionEnum.SE.y;
			if(mapper[newPosX][newPosY] != -1){
				mapper[newPosX][newPosY]++;
			}
		}
		//5 - DOWN
		if(isNearValid(mapper, pos, NearPositionEnum.S)){
			
			newPosX = pos.X + NearPositionEnum.S.x;
			newPosY = pos.Y  + NearPositionEnum.S.y;
			if(mapper[newPosX][newPosY] != -1){
				mapper[newPosX][newPosY]++;
			}
		}
		
		//6 - DOWN - LEFT
		if(isNearValid(mapper, pos, NearPositionEnum.SW)){
			
			newPosX = pos.X + NearPositionEnum.SW.x;
			newPosY = pos.Y  + NearPositionEnum.SW.y;
			if(mapper[newPosX][newPosY] != -1){
				mapper[newPosX][newPosY]++;
			}
		}
		//7 - LEFT
		if(isNearValid(mapper, pos, NearPositionEnum.W)){
			
			newPosX = pos.X + NearPositionEnum.W.x;
			newPosY = pos.Y  + NearPositionEnum.W.y;
			if(mapper[newPosX][newPosY] != -1){
				mapper[newPosX][newPosY]++;
			}
		}
		//8 - LEFT - UP
		if(isNearValid(mapper, pos, NearPositionEnum.NW)){
			
			newPosX = pos.X + NearPositionEnum.NW.x;
			newPosY = pos.Y  + NearPositionEnum.NW.y;
			if(mapper[newPosX][newPosY] != -1){
				mapper[newPosX][newPosY]++;
			}
		}
	}

	public static void printGame(int mapper[][]){
		for (int i=0; i < mapper.length; i++){
			for(int j=0; j < mapper[0].length ; j++){
				// Gera os botões da janela
				new Button(i, j, mapper[i][j]);
			}
		}
		
		//Atualiza a janela com o campo minado
		Window.showField(mapper[0].length);
		
	}
	
	public static void validateBombSpaces(int mapper[][]){
		//Pego os nearfields relativos a bomba
		for (MatrixPosition pos : bombPosition) {
			GetLocalMap(mapper, pos, 1);
		}
	}
	
	public static HashMap<MatrixPosition, Integer> GetLocalMap(int[][] mapper, MatrixPosition pos, int range){
		
		HashMap<MatrixPosition, Integer> mapPositionAndFieldValue = new HashMap<MatrixPosition, Integer>();
		
		MatrixPosition nextPos = new MatrixPosition(0, 0);
		
		for (NearPositionEnum nearEnum : NearPositionEnum.values()) {
			
			if(isNearValid(mapper, pos, nearEnum)){
				nextPos = new MatrixPosition(pos.X + nearEnum.x, pos.Y + nearEnum.y);
				mapPositionAndFieldValue.put(nextPos, mapper[nextPos.X][nextPos.Y]);
			}
			
		}
		
		return mapPositionAndFieldValue;
	}

}