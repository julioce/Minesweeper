package Model;

public enum NearPositionEnum {
	N (-1,0),
	NE (-1,1),
	E (0,1),
	SE (1,1),
	S (1,0),
	SW (1,-1),
	W (0,-1),
	NW (-1,-1);
	
	public final int x;
	public final int y;
	
	private NearPositionEnum(int x, int y) {
		this.x = x;
		this.y = y;
	}

}
