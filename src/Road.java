public class Road {
	
	private String name = null;
	private Point pointA;
	private Point pointB;
	private float distance;
	private float driveTime;
	
	
	public Road() {
		// TODO Auto-generated constructor stub
	}
	
	public Road(Point A, Point B) {
		this.pointA = A;
		this.pointB = B;
		this.setName();
	}
	public String getName() {
		this.setName();
		return this.name;
	}
	
	private void setName() {
		this.name = String.valueOf(this.pointA.getName()) + "-" + String.valueOf(this.pointB.getName());
	}

	public Point getPointA() {
		return pointA;
	}

	public void setPointA(Point pointA) {
		this.pointA = pointA;
	}

	public Point getPointB() {
		return pointB;
	}

	public void setPointB(Point pointB) {
		this.pointB = pointB;
	}

	
	private void setDistance() {
		this.distance = this.calculateDistance();
	}
	
	public float getDistance() {
		this.setDistance();
		return distance;
	}
	

	private void setDriveTime() {
		this.driveTime = calculateTime();
	}
	
	public float getDriveTime() {
		this.setDriveTime();
		return driveTime;
	}
	
	//计算这段路行驶时间
	private float calculateTime() {
		return this.getDistance() / 10;
	}
	
	//计算两点间距离
	private float calculateDistance() {
    	return (float) Math.sqrt(Math.pow(this.pointA.getPosition().getX()-pointB.getPosition().getX(), 2) + 
    			Math.pow(this.pointA.getPosition().getY()-pointB.getPosition().getY(), 2));
    }
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getName();
	}
	
}
