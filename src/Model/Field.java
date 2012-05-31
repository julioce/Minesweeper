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

	public static double evaluateMap(int[][] mapper) {
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
			values[i] = (8-i)*values[i];
			evaluationGrade += values[i];
		}
		
		evaluationGrade = evaluationGrade/(nColumns*nColumns);
		
		return evaluationGrade;
	}

	public static void setUpMap(int mapper[][]){
		for (int i=0; i<lines; i++){
			for(int j=0; j <columns; j++){
				mapper[i][j] = 0;
			}
		}
	}
	
	public static void setUpBlankMap(int blankMapper[][]){
		for (int i=0; i<blankMapper.length; i++){
			for(int j=0; j <blankMapper[0].length; j++){
				int qtOfBlanks = 8;
				if((i == 0) || (i == blankMapper.length - 1))
				{
					if(j == 0 || (j == blankMapper[0].length - 1))
					{
						qtOfBlanks = 3;
					}
					else
					{
						qtOfBlanks = 5;
					}
				}
				else if((j == 0) || (j == blankMapper[0].length - 1))
				{
					if(i == 0 || (i == blankMapper.length - 1))
					{
						qtOfBlanks = 3;
					}
					else
					{
						qtOfBlanks = 5;
					}
				}

				blankMapper[i][j] = qtOfBlanks;
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
			}
			else{
				i--;
				conflicts++;
			}
		}
		
		System.out.println("Bomb space conflicts = " + conflicts);
		System.out.println("Inserted bombs = " + insertedBombs);
	}

	public static void populateBlankMap(int mapper[][],int blankMapper[][])
	{
		for (int i=0; i < lines; i++){
			for(int j=0; j < columns ; j++){
				// Gera os botões da janela
				if(mapper[i][j] != 0)
				{
					UpdateNearFieldsInBlankMap(blankMapper, i, j);
				}
			}
		}
	}
	
	public static int[][] removeBomb(int[][] mapper, MatrixPosition pos)
	{
		mapper[pos.X][pos.Y] = 0;
		UpdateNearFields(mapper, pos, false);
		if (bombPosition.contains(pos))
		{
			System.out.println("achei");
			bombPosition.remove(pos);
		}
		
		return mapper;
	}
	
	public static void UpdateNearFields(int mapper[][], MatrixPosition pos, boolean isInserted){
		int newPosX = 0;
		int newPosY = 0;
		
		//1 - UP - subo em x
		if(isNearValid(mapper, pos, NearPositionEnum.N)){
			newPosX = pos.X + NearPositionEnum.N.x;
			newPosY = pos.Y + NearPositionEnum.N.y;
			if(mapper[newPosX][newPosY] != -1){
				if(isInserted)
					mapper[newPosX][newPosY]++;
				else
				{
					if(mapper[newPosX][newPosY] > 0)
						mapper[newPosX][newPosY]--;	
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
						mapper[newPosX][newPosY]--;	
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
						mapper[newPosX][newPosY]--;
					
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
						mapper[newPosX][newPosY]--;	
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
						mapper[newPosX][newPosY]--;	
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
						mapper[newPosX][newPosY]--;	
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
						mapper[newPosX][newPosY]--;
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
						mapper[newPosX][newPosY]--;
				}
			}
			else
			{
				if(!isInserted)
					mapper[pos.X][pos.Y]++;
			}
		}
		
	}

	public static void UpdateNearFieldsInBlankMap(int blankMapper[][], MatrixPosition pos){
		int newPosX = 0;
		int newPosY = 0;
		
		//1 - UP - subo em x
		if(isNearValid(blankMapper, pos, NearPositionEnum.N)){
			newPosX = pos.X + NearPositionEnum.N.x;
			newPosY = pos.Y + NearPositionEnum.N.y;
			if(blankMapper[newPosX][newPosY] > 0)
				blankMapper[newPosX][newPosY]--;
			
		}
		//2 - UP, RIGHT
		if(isNearValid(blankMapper, pos, NearPositionEnum.NE)){
			newPosX = pos.X + NearPositionEnum.NE.x;
			newPosY = pos.Y  + NearPositionEnum.NE.y;
			if(blankMapper[newPosX][newPosY] > 0)
				blankMapper[newPosX][newPosY]--;
			
		}
		//3 - RIGHT
		if(isNearValid(blankMapper, pos, NearPositionEnum.E)){
			newPosX = pos.X + NearPositionEnum.E.x;
			newPosY = pos.Y  + NearPositionEnum.E.y;
			if(blankMapper[newPosX][newPosY] > 0)
				blankMapper[newPosX][newPosY]--;
			
		}		
		//4 - RIGHT - DOWN	
		if(isNearValid(blankMapper, pos, NearPositionEnum.SE)){
			newPosX = pos.X + NearPositionEnum.SE.x;
			newPosY = pos.Y  + NearPositionEnum.SE.y;
			if(blankMapper[newPosX][newPosY] > 0)
				blankMapper[newPosX][newPosY]--;
			
		}
		//5 - DOWN
		if(isNearValid(blankMapper, pos, NearPositionEnum.S)){
			newPosX = pos.X + NearPositionEnum.S.x;
			newPosY = pos.Y  + NearPositionEnum.S.y;
			if(blankMapper[newPosX][newPosY] > 0)
				blankMapper[newPosX][newPosY]--;
			
		}
		//6 - DOWN - LEFT
		if(isNearValid(blankMapper, pos, NearPositionEnum.SW)){
			newPosX = pos.X + NearPositionEnum.SW.x;
			newPosY = pos.Y  + NearPositionEnum.SW.y;
			if(blankMapper[newPosX][newPosY] > 0)
				blankMapper[newPosX][newPosY]--;
			
		}
		//7 - LEFT
		if(isNearValid(blankMapper, pos, NearPositionEnum.W)){
			newPosX = pos.X + NearPositionEnum.W.x;
			newPosY = pos.Y  + NearPositionEnum.W.y;
			if(blankMapper[newPosX][newPosY] > 0)
				blankMapper[newPosX][newPosY]--;
			
		}
		
		//8 - LEFT - UP
		if(isNearValid(blankMapper, pos, NearPositionEnum.NW)){
			newPosX = pos.X + NearPositionEnum.NW.x;
			newPosY = pos.Y  + NearPositionEnum.NW.y;
			if(blankMapper[newPosX][newPosY] > 0)
				blankMapper[newPosX][newPosY]--;
			
		}
	}
	
	public static void UpdateNearFieldsInBlankMap(int blankMapper[][],int x, int y){
		UpdateNearFieldsInBlankMap(blankMapper,new MatrixPosition(x, y));
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
		for (int i=0; i < mapper[0].length; i++)
		{
			System.out.print("[");
			for(int j=0; j < mapper.length ; j++)
			{
				System.out.print(" "+ mapper[j][i] + " ");
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

	public static int[][] localSearch(int[][] mapper) {
		double betterEvaluation = 0.0;
		MatrixPosition betterBomb = null;
		int counter = 0;
		
		switch (Field.difficulty) {
			case 1:
				while(Field.evaluateMap(mapper) >= 0.5 && counter != 200){
					System.out.println("Difficulty evaluation = " + Field.evaluateMap(mapper));
					
					//fazendo a avaliação das densidades locais das bombas
					for (MatrixPosition bombPos :Field.bombPosition) {
						//mapa local
						int[][] localMapper = Field.GetLocalMap(mapper, bombPos);
						
						//guarda a melhoar avalaliação
						if(Field.evaluateMap(localMapper) >= betterEvaluation){
							betterBomb = bombPos;
						}
					}
					
					//Remover a bomba
					mapper = removeBomb(mapper, betterBomb);
					
					//Inserir uma bomba
					mapper = insertBomb(mapper);
					
					counter++;
					
				}
				System.out.println("Final Difficulty evaluation = " + Field.evaluateMap(mapper));
				break;
			case 2:
				while(Field.evaluateMap(mapper) < 0.5 || Field.evaluateMap(mapper) >= 1.0 ){
					System.out.println("Difficulty evaluation = " + Field.evaluateMap(mapper));
					//fazendo a avaliação das densidades locais das bombas
					for (MatrixPosition bombPos :Field.bombPosition) {
						//mapa local
						int[][] localMapper = Field.GetLocalMap(mapper, bombPos);
						
						//guarda a melhoar avalaliação
						if(Field.evaluateMap(mapper) < 0.5){
							if(Field.evaluateMap(localMapper) >= betterEvaluation){
								betterBomb = bombPos;
							}
						}else{
							if(Field.evaluateMap(localMapper) <= betterEvaluation){
								betterBomb = bombPos;
							}
						}
						
					}
					
					//Remover a bomba
					mapper = removeBomb(mapper, betterBomb);
					
					//Inserir uma bomba
					mapper = insertBomb(mapper);
				}
				System.out.println("Final Difficulty evaluation = " + Field.evaluateMap(mapper));
				break;
			case 3:
				while(Field.evaluateMap(mapper) <= 1.0){
					System.out.println("Difficulty evaluation = " + Field.evaluateMap(mapper));
					//fazendo a avaliação das densidades locais das bombas
					for (MatrixPosition bombPos :Field.bombPosition) {
						//mapa local
						int[][] localMapper = Field.GetLocalMap(mapper, bombPos);
						
						//guarda a melhoar avalaliação
						if(Field.evaluateMap(localMapper) <= betterEvaluation){
							betterBomb = bombPos;
						}
					}
					
					//Remover a bomba
					mapper = removeBomb(mapper, betterBomb);
					
					//Inserir uma bomba
					mapper = insertBomb(mapper);
				}
				System.out.println("Final Difficulty evaluation = " + Field.evaluateMap(mapper));
				break;

			default:
				break;
		}
		
		return mapper;
	}

	private static int[][] insertBomb(int[][] mapper) {
		Random randGenerator = new Random();
		int randLine = randGenerator.nextInt() % lines;
		if(randLine < 0){
			randLine*=-1;
		}
		
		int randColumn = randGenerator.nextInt() % columns;
		if(randColumn < 0){
			randColumn*=-1;
		}
		
		while(mapper[randLine][randColumn] != -1){
			//adicionando bomba
			mapper[randLine][randColumn] = -1;
			
			bombPosition.add(new MatrixPosition(randLine,randColumn));
			
			MatrixPosition pos = new MatrixPosition(randLine, randColumn);
			UpdateNearFields(mapper,pos,true);
			
			//Inserir uma bomba
			randLine = randGenerator.nextInt() % lines;
			if(randLine < 0){
				randLine*=-1;
			}
			
			randColumn = randGenerator.nextInt() % columns;
			if(randColumn < 0){
				randColumn*=-1;
			}
		}
		
		return mapper;
	}
}