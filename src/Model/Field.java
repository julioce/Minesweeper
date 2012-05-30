package Model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

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
		
		int nLines = mapper.length;
		int nColumns = mapper[0].length;
		
		for (int i=0; i< nLines; i++){
			for(int j=0; j < nColumns; j++){
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
		
		evaluationGrade = evaluationGrade/(nColumns*nColumns);
		
		System.out.println("Difficulty evaluation = " + evaluationGrade);
	}

	public static void setUpMap(int mapper[][]){
		for (int i=0; i<lines; i++){
			for(int j=0; j <columns; j++){
				mapper[i][j] = 0;
			}
		}
	}
	
	public static void generateBombs(int mapper[][]){
		int insertedBombs = 0;
		int conflicts = 0;
		
		Random randGenerator = new Random();
		
		for(int i=0; i < bombQuantity; i++){
			
			int randLine = randGenerator.nextInt() % lines;
			if(randLine < 0){
				randLine*=-1;
			}
			
			int randColumn = randGenerator.nextInt() % columns;
			if(randColumn < 0){
				randColumn*=-1;
			}
			
			if(mapper[randLine][randColumn] != -1){
				//adicionando bomba
				mapper[randLine][randColumn] = -1;
				
				bombPosition.add(new MatrixPosition(randLine,randColumn));
				
				insertedBombs ++;
				MatrixPosition pos = new MatrixPosition(randLine, randColumn);
				UpdateNearFields(mapper,pos,true);
			}else{
				i--;
				conflicts++;
			}
		}
		
		System.out.println("Bomb space conflicts = " + conflicts);
		System.out.println("Inserted bombs = " + insertedBombs);
	}
	
	public static boolean removeBomb(int mapper[][], MatrixPosition pos)
	{
		boolean isRemoved = false;
		
		if(mapper[pos.X][pos.Y] == -1)
		{
			mapper[pos.X][pos.Y] = 0;
			isRemoved = true;
			UpdateNearFields(mapper, pos, false);
		}
		
		return isRemoved;
	}
	
	public static void UpdateNearFields(int mapper[][], MatrixPosition pos, boolean isInserted){
		int newPosX = 0;
		int newPosY = 0;
		
		//1 - UP - subo em x
		if(isNearValid(mapper, pos, NearPositionEnum.N)){
			
			newPosX = pos.X + NearPositionEnum.N.x;
			newPosY = pos.Y  + NearPositionEnum.N.y;
			if(mapper[newPosX][newPosY] != -1){
				if(isInserted)
					mapper[newPosX][newPosY]++;
				else
				{
					if(mapper[newPosX][newPosY] > 0)
					{
						mapper[newPosX][newPosY]--;
					}
				}
			}
			else
			{
				if(!isInserted)
					mapper[pos.X][pos.Y]++;
			}
		}
		//2 - UP, RIGHT
		if(isNearValid(mapper, pos, NearPositionEnum.NE)){
			
			newPosX = pos.X + NearPositionEnum.NE.x;
			newPosY = pos.Y  + NearPositionEnum.NE.y;
			if(mapper[newPosX][newPosY] != -1){
				if(isInserted)
					mapper[newPosX][newPosY]++;
				else
				{
					if(mapper[newPosX][newPosY] > 0)
					{
						mapper[newPosX][newPosY]--;
					}
				}
			}
			else
			{
				if(!isInserted)
					mapper[pos.X][pos.Y]++;
			}
		}
		//3 - RIGHT
		if(isNearValid(mapper, pos, NearPositionEnum.E)){
			
			newPosX = pos.X + NearPositionEnum.E.x;
			newPosY = pos.Y  + NearPositionEnum.E.y;
			if(mapper[newPosX][newPosY] != -1){
				if(isInserted)
					mapper[newPosX][newPosY]++;
				else
				{
					if(mapper[newPosX][newPosY] > 0)
					{
						mapper[newPosX][newPosY]--;
					}
				}
			}
			else
			{
				if(!isInserted)
					mapper[pos.X][pos.Y]++;
			}
		}		
		//4 - RIGHT - DOWN	
		if(isNearValid(mapper, pos, NearPositionEnum.SE)){
			
			newPosX = pos.X + NearPositionEnum.SE.x;
			newPosY = pos.Y  + NearPositionEnum.SE.y;
			if(mapper[newPosX][newPosY] != -1){
				if(isInserted)
					mapper[newPosX][newPosY]++;
				else
				{
					if(mapper[newPosX][newPosY] > 0)
					{
						mapper[newPosX][newPosY]--;
					}
				}
			}
			else
			{
				if(!isInserted)
					mapper[pos.X][pos.Y]++;
			}
		}
		//5 - DOWN
		if(isNearValid(mapper, pos, NearPositionEnum.S)){
			
			newPosX = pos.X + NearPositionEnum.S.x;
			newPosY = pos.Y  + NearPositionEnum.S.y;
			if(mapper[newPosX][newPosY] != -1){
				if(isInserted)
					mapper[newPosX][newPosY]++;
				else
				{
					if(mapper[newPosX][newPosY] > 0)
					{
						mapper[newPosX][newPosY]--;
					}
				}
			}
			else
			{
				if(!isInserted)
					mapper[pos.X][pos.Y]++;
			}
		}
		
		//6 - DOWN - LEFT
		if(isNearValid(mapper, pos, NearPositionEnum.SW)){
			
			newPosX = pos.X + NearPositionEnum.SW.x;
			newPosY = pos.Y  + NearPositionEnum.SW.y;
			if(mapper[newPosX][newPosY] != -1){
				if(isInserted)
					mapper[newPosX][newPosY]++;
				else
				{
					if(mapper[newPosX][newPosY] > 0)
					{
						mapper[newPosX][newPosY]--;
					}
				}
			}
			else
			{
				if(!isInserted)
					mapper[pos.X][pos.Y]++;
			}
		}
		//7 - LEFT
		if(isNearValid(mapper, pos, NearPositionEnum.W)){
			
			newPosX = pos.X + NearPositionEnum.W.x;
			newPosY = pos.Y  + NearPositionEnum.W.y;
			if(mapper[newPosX][newPosY] != -1){
				if(isInserted)
					mapper[newPosX][newPosY]++;
				else
				{
					if(mapper[newPosX][newPosY] > 0)
					{
						mapper[newPosX][newPosY]--;
					}
				}
			}
			else
			{
				if(!isInserted)
					mapper[pos.X][pos.Y]++;
			}
		}
		//8 - LEFT - UP
		if(isNearValid(mapper, pos, NearPositionEnum.NW)){
			
			newPosX = pos.X + NearPositionEnum.NW.x;
			newPosY = pos.Y  + NearPositionEnum.NW.y;
			if(mapper[newPosX][newPosY] != -1){
				if(isInserted)
					mapper[newPosX][newPosY]++;
				else
				{
					if(mapper[newPosX][newPosY] > 0)
					{
						mapper[newPosX][newPosY]--;
					}
				}
			}
			else
			{
				if(!isInserted)
					mapper[pos.X][pos.Y]++;
			}
		}
	}

	public static void printGame(int mapper[][]){
		for (int i=0; i < lines; i++){
			for(int j=0; j < columns ; j++){
				// Gera os botões da janela
				new Button(i, j, mapper[i][j]);
			}
		}
		
		//Atualiza a janela com o campo minado
		Window.showField(lines, columns);
		
	}
	
	public static void printGameInConsole(int mapper[][]){
		for (int i=0; i < mapper.length; i++)
		{
			System.out.print("[");
			for(int j=0; j < mapper[0].length ; j++)
			{
				System.out.print(" "+ mapper[i][j] + " ");
			} 
			System.out.println("]");
		}	
	}
	
	
	public static void validateBombSpaces(int mapper[][]){
		//Pego os nearfields relativos a bomba
		for (MatrixPosition pos : bombPosition) {
			GetLocalMap(mapper, pos, 1);
		}
	}
	
	public static boolean validateUserInput(int lines, int columns, int bombs){
		if(lines > 0 && columns > 0 && bombs > 0 && bombs < lines*columns){
			return true;
		}else{
			return false;
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
	
	public static int[][] GetLocalMap(int[][] mapper, MatrixPosition pos)
	{
		int[][] localMapper = new int[3][3];
		
		//setando o centro
		localMapper[1][1] = mapper[pos.X][pos.Y];
		
		for (NearPositionEnum nearEnum : NearPositionEnum.values()) {			
			if(isNearValid(mapper, pos, nearEnum)){
				localMapper[1 + nearEnum.x][ 1 + nearEnum.y] = mapper[pos.X + nearEnum.x][pos.Y + nearEnum.y];
			}
			else
			{
				localMapper[1 + nearEnum.x][ 1 + nearEnum.y] = 0;
			}
			
		}	
		
		return localMapper;
				
	}
}