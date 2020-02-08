import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ReadPoints {
	private  Map<Integer, Point> mapPoint = new HashMap<Integer, Point>();
	
	private ReadPoints() {
		// TODO Auto-generated constructor stub
	}
	
	public static Map<Integer, Point> getRead(String fileName){
		ReadPoints readPoints = new ReadPoints();
		try {
			File file = new File(fileName);
			Scanner scanner = new Scanner(file);
			int count = Integer.valueOf(scanner.nextLine());
			for(int i=0;i<=count;i++) {
				Point point = new Point();
				point.setName(scanner.nextInt());
				point.setPosition(new Position(scanner.nextInt(), scanner.nextInt()));
				readPoints.mapPoint.put(point.getName(), point);
			}
			scanner.close();
    	}catch (Exception e) {
			// TODO: handle exception
    		e.printStackTrace();
		}
		return readPoints.mapPoint;
	}
}
