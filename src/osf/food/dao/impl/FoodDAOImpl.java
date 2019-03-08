package osf.food.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import osf.food.dao.FoodDAO;
import osf.food.vo.FoodVO;
import test.DBCon;

public class FoodDAOImpl implements FoodDAO {

	public List<FoodVO> selectFoodList(FoodVO sFood) {
		String sql = "select food_num, food_name, food_price from food where 1=1";
		if(sFood!= null) {
			if(sFood.getFoodName()!= null) {
				sql += " and food_name=?";
			}
		}
		try {
			PreparedStatement ps = DBCon.getCon().prepareStatement(sql);
			if(sFood!= null) {
				if(sFood.getFoodName()!= null) {
					ps.setString(1, sFood.getFoodName());
				}
			}
			ResultSet rs = ps.executeQuery();
			List<FoodVO> foodList = new ArrayList<>();
			while (rs.next()) {
				FoodVO food = new FoodVO();
				food.setFoodName(rs.getString("food_name"));
				food.setFoodNum(rs.getInt("food_num"));
				food.setFoodPrice(rs.getInt("food_price"));
				foodList.add(food);
			}
			return foodList;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCon.close();
		}
		return null;
	}

	public FoodVO selectFood(Integer foodNum) {
		String sql = "select food_num, food_name, food_price from food" + " where food_num=?";
		try {
			PreparedStatement ps = DBCon.getCon().prepareStatement(sql);
			ps.setInt(1, foodNum);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				FoodVO food = new FoodVO();
				food.setFoodName(rs.getString("food_name"));
				food.setFoodNum(rs.getInt("food_num"));
				food.setFoodPrice(rs.getInt("food_price"));
				return food;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCon.close();
		}

		return null;
	}


	@Override
	public int insertFood(FoodVO food) {
		String sql = "insert into Food(food_num, food_name, food_price)"
				+ " values((select nvl(max(food_num),0)+1 from food)" + " ,?,?)";

		// 사용자에게 입력받으면 똑같은 화면을 보고있는사람이 동시에 입력할수도 있기 때문에 반드시 어긋난다.
		// 그러므로 이 오류를 해결하기위해서 제일 큰숫자에 +1을 해서 나오게한다.
		try {
			PreparedStatement ps = DBCon.getCon().prepareStatement(sql);
			ps.setString(1, food.getFoodName());
			ps.setInt(2, food.getFoodPrice());
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}

	@Override
	public int updateFood(FoodVO food) {
		String sql = "update food set food_name=?, food_price=? where food_num=?";
		try {
			PreparedStatement ps = DBCon.getCon().prepareStatement(sql);
			ps.setString(1, food.getFoodName());
			ps.setInt(2, food.getFoodPrice());
			ps.setInt(3, food.getFoodNum());
			return ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBCon.close();
		}
		return 0;
	}

	@Override
	public int deleteFood(FoodVO food) {
		String sql = "delete from food where food_num =?";
		try {
			PreparedStatement ps = DBCon.getCon().prepareStatement(sql);
			ps.setInt(1, food.getFoodNum());
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBCon.close();
		}
		return 0;
	}
	public static void main(String[] args) {
		FoodDAO fdao = new FoodDAOImpl();
		List<FoodVO> foodList = fdao.selectFoodList(null);
		FoodVO food = new FoodVO();
		food.setFoodName("초밥");
		foodList = fdao.selectFoodList(food);
		System.out.println(foodList);
	}

}
