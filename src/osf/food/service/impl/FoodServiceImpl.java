package osf.food.service.impl;

import java.util.List;

import osf.food.dao.FoodDAO;
import osf.food.dao.impl.FoodDAOImpl;
import osf.food.service.FoodService;
import osf.food.vo.FoodVO;

public class FoodServiceImpl implements FoodService {
	private FoodDAO fdao = new FoodDAOImpl();

	@Override
	public List<FoodVO> selectFoodList(FoodVO food) {
		return fdao.selectFoodList(food);
	}

	@Override
	public FoodVO selectFood(Integer foodNum) {
		return fdao.selectFood(foodNum);
	}

	@Override
	public int insertFood(FoodVO food) throws Exception { // 있는지 검색하고 중복값이 있는지 확인하는것 유효성 검사
		List<FoodVO> tmpFoodList = fdao.selectFoodList(food);
		if (tmpFoodList.size() != 0) {
			throw new Exception("중복!!!");
		}
		return fdao.insertFood(food);
	}

	@Override
	public int updateFood(FoodVO food) throws Exception {
		List<FoodVO> tmpFoodList = fdao.selectFoodList(food);
		if (tmpFoodList.size() != 0) {
			throw new Exception("이미 업데이트 되었습니다.");
		}
		return fdao.updateFood(food);
	}

	@Override
	public int deleteFood(FoodVO food) throws Exception {
		FoodVO tmpFood = fdao.selectFood(food.getFoodNum());
		if (tmpFood == null) {
			throw new Exception("이미 삭제 되었습니다.");
		}
		return fdao.deleteFood(food);
	}
//아래부분에서는 단위테스트 각 단위마다 제대로 실행되는지 확인하기위한 테스트
	public static void main(String[] args) throws Exception {
		FoodService fs = new FoodServiceImpl();
		List<FoodVO> foodList = fs.selectFoodList(null);
		System.out.println(foodList);
		FoodVO food = new FoodVO();
//		food.setFoodName("참치");
//		foodList = fs.selectFoodList(food);
//		System.out.println(foodList);

//		food.setFoodName("과자");
//		food.setFoodPrice(10000);
//		int cnt = 0;
//		try {
//			cnt = fs.insertFood(food);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		System.out.println("저장 건수 : " + cnt);
		
		System.out.println(foodList);
		

//		food.setFoodNum(5);
//		food.setFoodName("계란");
//		food.setFoodPrice(2000);
//		try {
//			cnt = fs.updateFood(food);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		System.out.println("업데이트 건수 : " + cnt);
//
//		food.setFoodNum(6);
//		try {
//			cnt = fs.deleteFood(food);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		System.out.println("삭제된 건수 : " + cnt);
//		
	}

	// 하나하나 테스트하고 실행하고 하는게 단위테스트
}
