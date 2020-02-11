public class Vertex {
	private int name ;
	private Location location = new Location() ;
	private float goods ;
	
	public Vertex() {
		
	}
	public Vertex(int name, Location p) {
		this.name = name;
		this.location = p;
	}
	public int getName() {
		return name;
	}
	
	public void setName(int name) {
		this.name = name;
	}
	
	public Location getLocation() {
		return location;
	}
	
	public void setLocation(Location location) {
		this.location = location;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.valueOf(this.name);
	}
	
}
