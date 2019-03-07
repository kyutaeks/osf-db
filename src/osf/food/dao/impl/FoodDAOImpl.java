package osf.food.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import osf.food.dao.FoodDAO;
import osf.food.db.DBCon;
import osf.food.vo.FoodVO;

public class FoodDAOImpl implements FoodDAO {

	@Override
	public List<FoodVO> selectFoodList() {
		String sql = "select food_num, food_name, food_price from food";
		try {
			PreparedStatement ps = DBCon.getCon().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<FoodVO> foodList = new ArrayList<>();
			while(rs.next()) {
				FoodVO food = new FoodVO();
				food.setFoodNum(rs.getInt("food_num"));
				food.setFoodName(rs.getString("food_name"));
				food.setFoodPrice(rs.getInt("food_price"));
				foodList.add(food);

//				System.out.println(rs.getInt("food_num"));
//				System.out.println(rs.getString("food_name"));
//				System.out.println(rs.getInt("food_price"));
			}
			return foodList;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBCon.close();
		}
		return null;
	}
	public static void main(String[] args) {
		FoodDAO fdao = new FoodDAOImpl();
//		fdao.selectFoodList();
		List<FoodVO> foodList = fdao.selectFoodList();
		System.out.println(foodList);
	}

}
