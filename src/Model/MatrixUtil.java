package Model;

public abstract class MatrixUtil {	
	
	public static boolean isNearValid(int mapper[][], MatrixPosition pos, NearPositionEnum direction)
	{
		boolean isValid = false;
		int newPosX = pos.X  + direction.x;
		int newPosY = pos.Y + direction.y;
		
		switch (direction) {
		case N:
			newPosX = pos.X  + direction.x;
			if(newPosX >= 0)
			{
				isValid = true;
			}			
			break;
		case NE:
			if(newPosX >= 0 && newPosY < mapper[0].length)
			{
				isValid = true;
			}			
			break;
		case E:
			if(newPosY < mapper[0].length)
			{
				isValid = true;
			}		
			break;
		case NW:
			if(newPosY >= 0 && newPosX >= 0)
			{
				isValid = true;
			}
			
			break;
		case S:
			if(newPosX < mapper.length)
			{
				isValid = true;
			}				
			break;
		case SE:
			if(newPosY < mapper[0].length && newPosX < mapper.length)
			{
				isValid = true;
			}
			
			break;
		case SW:
			if(newPosY >= 0 && newPosX < mapper.length)
			{
				isValid = true;
			}
			break;
		case W:
			if(newPosY >= 0)
			{
				isValid = true;
			}
			
			break;
		default:
			break;
		}
		
		return isValid;
	}
}
