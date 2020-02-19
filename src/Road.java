//import java.math.BigDecimal;
//
//public class Road {
//
//	private String name = null;
//	private Vertex vertexA;
//	private Vertex vertexB;
//	private float distance;
//	private float driveTime;
//
//
//	public Road() {
//		// TODO Auto-generated constructor stub
//	}
//
//	public Road(Vertex A, Vertex B) {
//		this.vertexA = A;
//		this.vertexB = B;
//		this.setName();
//	}
//
//	public String getName() {
//		this.setName();
//		return this.name;
//	}
//
//	private void setName() {
//		this.name = String.valueOf(this.vertexA.getName()) + "-" + String.valueOf(this.vertexB.getName());
//	}
//
//	public Vertex getVertexA() {
//		return vertexA;
//	}
//
//	public void setVertexA(Vertex vertexA) {
//		this.vertexA = vertexA;
//	}
//
//	public Vertex getVertexB() {
//		return vertexB;
//	}
//
//	public void setVertexB(Vertex vertexB) {
//		this.vertexB = vertexB;
//	}
//
//
//	private void setDistance() {
//		this.distance = this.calculateDistance();
//	}
//
//	public float getDistance() {
//		this.setDistance();
//		return distance;
//	}
//
//	private void setDriveTime() {
//		this.driveTime = calculateTime();
//	}
//
//	public float getDriveTime() {
//		this.setDriveTime();
//		return driveTime;
//	}
//
//	//计算这段路行驶时间
//	private float calculateTime() {
//		return this.getDistance() / 10;
//	}
//
//	//计算两点间距离
//	private float calculateDistance() {
//    	float distance = (float) Math.sqrt(Math.pow(this.vertexA.getLocation().getX()- vertexB.getLocation().getX(), 2) +
//    			Math.pow(this.vertexA.getLocation().getY()- vertexB.getLocation().getY(), 2));
//		BigDecimal bd = new BigDecimal(distance/30);
//		return bd.setScale(2, BigDecimal.ROUND_DOWN).floatValue();
//    }
//
//	@Override
//	public String toString() {
//		// TODO Auto-generated method stub
//		return this.getName();
//	}
//
//}
