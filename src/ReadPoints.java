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
				String string = scanner.nextLine();
				String[] strings1 = string.split("&");
				String[] strings2 = strings1[0].substring(2,strings1[0].length()-1).split(",");
				Point point = new Point();
				point.setName(Integer.valueOf(string.substring(0,1)));
				point.setPosition(new Position(Integer.valueOf(strings2[0]),Integer.valueOf(strings2[1])));
				if(string.charAt(0)=='0') {
					point.setGoods(0);
				}else {
					point.setGoods(Float.valueOf(strings1[1]));
				}
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
