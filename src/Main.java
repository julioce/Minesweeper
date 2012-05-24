import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Main extends MatrixUtil
{
	static ArrayList<MatrixPosition> bombPosition;
	static int lines;
	static int columns;
	static int difficulty = 1;
	
	public static void main(String[] args) 
	{
		/* Inicializa a GUI */
		@SuppressWarnings("unused")
		View view = new View();
	}
	
	public static void generateField(){

		int mapper[][] = new int[lines][columns];
		bombPosition = new ArrayList<MatrixPosition>();
		setUpMap(mapper);
		generateBombs(mapper);
		validateBombSpaces(mapper);
		printMatrix(mapper);
	}
	
	public static void setUpMap(int mapper[][])
	{
		for (int i=0; i< mapper.length; i++)
		{
			for(int j= 0; j < mapper[0].length; j++)
			{
				mapper[i][j] = 0;
			}
		}
	}
	
	public static void generateBombs(int mapper[][])
	{
		int bombQuantity = lines+columns;
		int insertedBombs = 0;
		Random randGenerator = new Random(912397);
		
		for(int i =0; i < bombQuantity; i++)
		{
			int randLine = randGenerator.nextInt() % mapper.length;
			if(randLine < 0){
				randLine*=-1;
			}
			
			int randColumn = randGenerator.nextInt() % mapper[0].length;
			if(randColumn < 0){
				randColumn*=-1;
			}
			
			if(mapper[randLine][randColumn] != -1)
			{
				//adicionando bomba
				mapper[randLine][randColumn] = -1;
				
				bombPosition.add(new MatrixPosition(randLine,randColumn));
				
				insertedBombs ++;
				MatrixPosition pos = new MatrixPosition(randLine, randColumn);
				UpdateNearFields(mapper,pos);
			}
			else
			{
				i--;
				System.out.println("Bomb space conflict");
			}
		}
		
		System.out.println("insertedbombs = " + insertedBombs);
	}
	
	public static void UpdateNearFields(int mapper[][],MatrixPosition pos)
	{
		int newPosX = 0;
		int newPosY = 0;
		

		//1 - UP - subo em x
		if(isNearValid(mapper, pos, NearPositionEnum.N))
		{
			newPosX = pos.X + NearPositionEnum.N.x;
			newPosY = pos.Y  + NearPositionEnum.N.y;
			if(mapper[newPosX][newPosY] != -1)
				mapper[newPosX][newPosY]++;
		}
		//2 - UP, RIGHT
		if(isNearValid(mapper, pos, NearPositionEnum.NE))
		{
			newPosX = pos.X + NearPositionEnum.NE.x;
			newPosY = pos.Y  + NearPositionEnum.NE.y;
			if(mapper[newPosX][newPosY] != -1)
				mapper[newPosX][newPosY]++;
		}
		//3 - RIGHT
		if(isNearValid(mapper, pos, NearPositionEnum.E))
		{
			newPosX = pos.X + NearPositionEnum.E.x;
			newPosY = pos.Y  + NearPositionEnum.E.y;
			if(mapper[newPosX][newPosY] != -1)
				mapper[newPosX][newPosY]++;
		}		
		//4 - RIGHT - DOWN	
		if(isNearValid(mapper, pos, NearPositionEnum.SE))
		{
			newPosX = pos.X + NearPositionEnum.SE.x;
			newPosY = pos.Y  + NearPositionEnum.SE.y;
			if(mapper[newPosX][newPosY] != -1)
				mapper[newPosX][newPosY]++;
		}
		//5 - DOWN
		if(isNearValid(mapper, pos, NearPositionEnum.S))
		{
			newPosX = pos.X + NearPositionEnum.S.x;
			newPosY = pos.Y  + NearPositionEnum.S.y;
			if(mapper[newPosX][newPosY] != -1)
				mapper[newPosX][newPosY]++;
		}	
		//6 - DOWN - LEFT
		if(isNearValid(mapper, pos, NearPositionEnum.SW))
		{
			newPosX = pos.X + NearPositionEnum.SW.x;
			newPosY = pos.Y  + NearPositionEnum.SW.y;
			if(mapper[newPosX][newPosY] != -1)
				mapper[newPosX][newPosY]++;
		}
		//7 - LEFT
		if(isNearValid(mapper, pos, NearPositionEnum.W))
		{
			newPosX = pos.X + NearPositionEnum.W.x;
			newPosY = pos.Y  + NearPositionEnum.W.y;
			if(mapper[newPosX][newPosY] != -1)
				mapper[newPosX][newPosY]++;
		}
		//8 - LEFT - UP
		if(isNearValid(mapper, pos, NearPositionEnum.NW))
		{
			newPosX = pos.X + NearPositionEnum.NW.x;
			newPosY = pos.Y  + NearPositionEnum.NW.y;
			if(mapper[newPosX][newPosY] != -1)
				mapper[newPosX][newPosY]++;
		}
	}
	
	
	public static void printMatrix(int mapper[][])
	{
		int width = 0;
		int height = 0;
		
		for (int i=0; i< mapper.length; i++)
		{
			System.out.print("[");
			
			for(int j= 0; j < mapper[0].length ; j++)
			{
				//Gera os botões da view
				Button.generateField(i, j, mapper[i][j]);
				System.out.print(" "+ mapper[i][j] + " ");
				height = j;
			}
			
			System.out.println("]");
			
			width = i;
		}
		
		View.window.setSize(new Dimension(width*Button.buttonWidth+30, height*Button.buttonHeight+50));
	}
	
	public static void validateBombSpaces(int mapper[][])
	{
		for (MatrixPosition pos : bombPosition) 
		{
			GetLocalMap(mapper,pos,1);
		}
		//Pego os nearfields relativos a bomba
	}
	
	public static HashMap<MatrixPosition, Integer> GetLocalMap(int[][] mapper, MatrixPosition pos, int range)
	{
		HashMap<MatrixPosition, Integer> mapPositionAndFieldValue = new HashMap<MatrixPosition, Integer>();
		
		MatrixPosition nextPos = new MatrixPosition(0, 0);
		
		for (NearPositionEnum nearEnum : NearPositionEnum.values()) {
			if(isNearValid(mapper, pos, nearEnum))
			{
				nextPos = new MatrixPosition(pos.X + nearEnum.x, pos.Y + nearEnum.y);
				mapPositionAndFieldValue.put(nextPos, mapper[nextPos.X][nextPos.Y]);
			}
			
		}
		
		return mapPositionAndFieldValue;
	}

	public static void setSize(String linesValue, String columnsValue) {
		lines = Integer.parseInt(linesValue);
		columns = Integer.parseInt(columnsValue);
	}

	public static void setDifficulty(int i) {
		difficulty = i;
	}

}