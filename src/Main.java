import javax.swing.*;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
	
	public static MyFrame frame = null;
	public static final Main main = new Main();
	public static final float ONCE_MAXIMUM_KILOMETRE = 35; //单词运送路程长度，单位：公里（km）
	public static final float ONCE_MAXIMUM_WORKING_TIME = 8; //单个员工工作时常，单位：时（h）
	public static final float DWCC_DEADWEIGHT_CARGO_CAPACITY_L = 2; //小车载重，单位：吨（t）
	public static final float DWCC_DEADWEIGHT_CARGO_CAPACITY_XL = 5; //大车载重，单位：吨（t）
	
	public static Map<Integer, Point> mapPoint = new HashMap<Integer, Point>();
	public static Map<String, Road> mapRoad = new HashMap<String, Road>();
	
    public static void main(String[] args) {
        /*
         * 在 AWT 的事件队列线程中创建窗口和组件, 确保线程安全,
         * 即 组件创建、绘制、事件响应 需要处于同一线程。
         */
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                // 创建窗口对象
                frame = new MyFrame();
                // 显示窗口
                frame.setVisible(true);
            }
        });
    }

    //窗口
    public static class MyFrame extends JFrame {
		private static final long serialVersionUID = 1L;
		
		public static final String TITLE = "模拟配送路径选择";
        public static final int WIDTH = 800;
        public static final int HEIGHT = 700;

        public MyFrame() {
            super();
            initFrame();
        }

        private void initFrame() {
            // 设置 窗口标题 和 窗口大小
            setTitle(TITLE);
            setSize(WIDTH, HEIGHT);

            // 设置窗口关闭按钮的默认操作(点击关闭时退出进程)
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            // 把窗口位置设置到屏幕的中心
            setLocationRelativeTo(null);

            // 设置窗口的内容面板
            MyPanel panel = new MyPanel(this);
            setContentPane(panel);
            this.creatMenu(panel,this);
        }
        private void creatMenu(MyPanel panel, JFrame jFrame) {
   		 /*
            * 创建一个菜单栏
            */
   		JMenuBar jMenuBar = new JMenuBar();
   		/*
   		 * 创建一级菜单
   		 */
   		JMenu operateMenu = new JMenu("操作");
   		
   		jMenuBar.add(operateMenu);//一级菜单添加到菜单栏
   		/*
   		 * 创建操作菜单的一级子菜单
   		 */

   		JMenuItem useTest = new JMenuItem("使用测试集");
   		/*
   		 * 添加子菜单
   		 */

   		operateMenu.add(useTest);
   		
   		useTest.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				main.initPoints();
				main.initRoads();
				panel.updateUI();
			}
		});
   		
   		this.setJMenuBar(jMenuBar);
   		this.setVisible(true);
   		
    }

}

    
    //绘图版面
    public static class MyPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		
		public MyPanel(MyFrame frame) {
            super();
        }

        
        //绘制面板的内容: 创建 JPanel 后会调用一次该方法绘制内容
        //之后如果数据改变需要重新绘制, 可调用 updateUI() 方法触发
        //系统再次调用该方法绘制更新 JPanel 的内容。
         
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // 重新调用 Graphics 的绘制方法绘制时将自动擦除旧的内容

            drawMap(g);

        }
        private void drawMap(Graphics g) {

            // 创建 Graphics 的副本, 需要改变 Graphics 的参数,
            // 这里必须使用副本, 避免影响到 Graphics 原有的设置
            Graphics2D g2d = (Graphics2D) g.create();

            // 抗锯齿
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            //画横纵坐标轴
            BasicStroke bs1 = new BasicStroke(3);       // 笔画的轮廓（画笔宽度/线宽为3px）
            g2d.setStroke(bs1);
            g2d.setColor(Color.blue);	// 设置画笔颜色
            g2d.drawLine(50, 600, 720, 600); //绘制直线点(50, 600) 至 点(720, 600)
            g2d.drawLine(50, 600, 50, 30);
            for(int x=50;x<=700;x+=50) {
            	g2d.drawLine(x, 600, x, 595);
            	g2d.drawString(String.valueOf(x-50), x, 615);
            }
            for(int y=600;y>=30;y-=50) {
            	g2d.drawLine(50, y, 55, y);
            	g2d.drawString(String.valueOf(600-y), 20, y);
            }
            
          //画路径
            BasicStroke bs3 = new BasicStroke(2);
            g2d.setStroke(bs3);
            g2d.setColor(Color.lightGray);
            
            Iterator<String> iteratorRoad = mapRoad.keySet().iterator();
            while(iteratorRoad.hasNext()) {
            	String key = iteratorRoad.next().toString();
            	Road road = mapRoad.get(key);
            	int x1 = road.getPointA().getPosition().getX();
            	int y1 = road.getPointA().getPosition().getY();
            	int x2 = road.getPointB().getPosition().getX();
            	int y2 = road.getPointB().getPosition().getY();
            	g2d.drawLine(x1+50,600-y1,x2+50,600-y2);
            	
            }
            
            //画坐标点
            g2d.setColor(Color.black);	// 设置画笔颜色
            
            Iterator<Integer> iteratorPoint = mapPoint.keySet().iterator();
            while(iteratorPoint.hasNext()) {
            	int key =  iteratorPoint.next();
            	g2d.drawString("▲"+String.valueOf(mapPoint.get(key).getName()),
            			mapPoint.get(key).getPosition().getX()+50,
            			600-mapPoint.get(key).getPosition().getY());
            	
            }
//            0(260,270)
//            1(520,410)&1.5
//            2(130,460)&2.5
//            3(600,537)&3.5
//            4(539,278)&0.5
//            5(111,130)&2

            // 销毁副本
            g2d.dispose();
            
            
        }

    }
    
    //遍历找出所有的分区方案
	public void findAllModule(Point point, float goods, HashSet<Point> hashSet) {
		
    }
    
    //将相邻配送点以最大装配率，和最短配送时间权重分区
    public void splitPoint() {
    	
    }
    
    //根据配送区设定配送路线
    public void setRoute() {}
    
    //计算时间
    public float calculateTime(float km) {
    	return km / 10;
    }
    //计算某区域的配送总货物重量
    public float caculateWeight(ArrayList<Point> al) {
    	float sum = 0;
    	Iterator<Point> iterator = al.iterator();
    	while (iterator.hasNext()) {
			Point point = (Point) iterator.next();
			sum += point.getGoods();
		}
    	return sum;
	}
   
    //初始化画板坐标点
    public void initPoints() {
    	mapPoint = ReadPoints.getRead("points.txt");
	}
    //初始化画板路径
    public void initRoads() {
    	Graph graph = new Graph("Graph.txt");
    	for(int v=0;v<graph.getV();v++) {
    		for(int w : graph.consecutivePoint(v)) {
    			Point pointA = mapPoint.get(v);
    			Point pointB = mapPoint.get(w);
    			Road road = new Road(pointA, pointB);
    			if(!mapRoad.containsKey(String.valueOf(pointB)+"-"+String.valueOf(pointA))) {
    				mapRoad.put(road.getName(), road);
    			}
    		}
    	}
	}
    
}

