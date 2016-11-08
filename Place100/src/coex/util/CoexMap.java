package coex.util;


import java.util.HashMap;
import java.util.Map;

public class CoexMap {
	Map<Integer,String> node = new HashMap();//노드를 가지고 있는  map
	
	
	
	
	
	
	public String nodeToXY(String node){		//노드번호를 받아서 해당 좌표 반환해주는 메소드
		String result = "";
		String[] nodelist = node.split("->");
		this.node.put(1, "806,336");
		this.node.put(2, "752,370");
		this.node.put(3, "801,294");
		this.node.put(4, "832,260");
		this.node.put(5, "729,286");
		this.node.put(6, "649,360");
		this.node.put(7, "840,208");
		this.node.put(8, "738,228");
		this.node.put(9, "649,292");
		this.node.put(10, "851,138");
		this.node.put(11, "599,298");
		this.node.put(12, "561,317");
		this.node.put(13, "603,205");
		this.node.put(14, "521,305");
		this.node.put(15, "503,365");
		this.node.put(16, "609,116");
		this.node.put(17, "522,206");
		this.node.put(18, "851,40");
		this.node.put(19, "605,45");
		this.node.put(20, "391,373");
		this.node.put(21, "458,268");
		this.node.put(22, "520,118");
		this.node.put(23, "547,49");
		this.node.put(24, "486,64");
		this.node.put(25, "482,114");
		this.node.put(26, "404,202");
		this.node.put(27, "406,244");
		this.node.put(28, "345,305");
		this.node.put(29, "432,60");
		this.node.put(30, "437,109");
		this.node.put(31, "395,123");
		this.node.put(32, "298,232");
		this.node.put(33, "443,29");
		this.node.put(34, "370,28");
		this.node.put(35, "371,59");
		this.node.put(36, "343,91");
		this.node.put(37, "270,89");
		this.node.put(38, "302,163");
		this.node.put(39, "260,49");
		this.node.put(40, "246,157");
		this.node.put(41, "256,182");
		this.node.put(42, "199,300");
		this.node.put(43, "177,189");
		this.node.put(44, "180,84");
		this.node.put(45, "85,126");
		this.node.put(46, "132,234");
		this.node.put(47, "163,337");
		this.node.put(48, "86,185");
		this.node.put(49, "90,270");
		this.node.put(50, "87,309");
		this.node.put(51, "422,531");
		this.node.put(52, "422,651");
		this.node.put(53, "426,685");
		this.node.put(54, "453,571");
		this.node.put(55, "484,711");
		this.node.put(56, "578,709");
		this.node.put(57, "633,662");
		this.node.put(58, "727,662");
		this.node.put(59, "436,791");
		this.node.put(60, "60,788");
		this.node.put(61, "257,820");
		this.node.put(62, "357,819");
		this.node.put(63, "574,821");
		this.node.put(64, "843,788");
		for (int i = 0; i < nodelist.length; i++) {
			result += this.node.get(Integer.parseInt(nodelist[i]))+",";
		}
		return result+"#";
	}
	
}
