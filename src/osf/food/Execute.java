package osf.food;

import java.util.HashMap;
import java.util.Map;

import osf.food.controller.FoodController;

public class Execute {
	private FoodController fc = new FoodController();

	public static void main(String[] args) {
		Map<String, String> req = new HashMap<>();
		Map<String, Object> res = new HashMap<>();
		Execute e = new Execute();
		try {
			req.put("cmd", "list");
			e.fc.doGet(req, res);
			System.out.println(res);

//			req = new HashMap<>();
//			res = new HashMap<>();
//			
//			req.put("cmd", "food");
//			req.put("foodNum", "1");
//			e.fc.doGet(req, res);
//			System.out.println(res);
			
			req = new HashMap<>();
			res = new HashMap<>();
			req.put("cmd", "insert");
			req.put("foodNum", "5");
			req.put("foodName", "과자");
			req.put("foodPrice", "30000");
			e.fc.doPost(req, res);
			System.out.println(res);
			
			req = new HashMap<>();
			res = new HashMap<>();
			req.put("cmd", "update");
			req.put("foodNum", "1");
			req.put("foodName", "참치");
			req.put("foodPrice", "2000");
			e.fc.doPost(req, res);
			System.out.println(res);
			
//			req = new HashMap<>();
//			res = new HashMap<>();
//			req.put("cmd", "delete");
//			req.put("foodNum", "5");
//			e.fc.doPost(req, res);
//			System.out.println(res);
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}
}
