public class Point {
	private int name ;
	private Position position = new Position() ;
	private float goods ;
	
	public Point() {
		
	}
	public Point(int name, Position p, float goods) {
		this.name = name;
		this.position = p;
		this.goods = goods;
	}
	public int getName() {

		return name;
	}
	
	public void setName(int name) {
		this.name = name;
	}
	
	public Position getPosition() {
		return position;
	}
	
	public void setPosition(Position position) {
		this.position = position;
	}
	
	public float getGoods() {
		return goods;
	}
	
	public void setGoods(float goods) {
		this.goods = goods;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.valueOf(this.name);
	}
	
}
