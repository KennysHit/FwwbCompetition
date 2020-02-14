import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ReadVertexs {

	private  Map<Integer, Vertex> mapPoint = new HashMap<Integer, Vertex>();
	
	private ReadVertexs() {
		// TODO Auto-generated constructor stub
	}
	
	public static Map<Integer, Vertex> getRead(String fileName){
		ReadVertexs readVertexs = new ReadVertexs();
		try {
			File file = new File(fileName);
			Scanner scanner = new Scanner(file);
			int count = Integer.valueOf(scanner.nextLine());
			for(int i=0;i<=count;i++) {
				Vertex vertex = new Vertex();
				vertex.setName(scanner.nextInt());
				vertex.setLocation(new Location(scanner.nextInt(), scanner.nextInt()));
				readVertexs.mapPoint.put(vertex.getName(), vertex);
			}
			scanner.close();
    	}catch (Exception e) {
			// TODO: handle exception
    		e.printStackTrace();
		}
		return readVertexs.mapPoint;
	}
}
