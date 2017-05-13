package com.critc.dic.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.critc.common.vo.ComboboxVO;
import com.critc.dic.model.DicCity;
import com.critc.dic.model.DicDistrict;
import com.critc.dic.model.DicProvince;

/**
 * 修理厂Dao
 * 
 * @author lmy
 * 
 */
@Repository
public class DicProvinceDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * 得到所有的省数据
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<ComboboxVO> listProvince() {
		String sql = "select province_code value,province_name content from t_dic_province order by province_code ";
		List<ComboboxVO> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper(ComboboxVO.class));
		return list;
	}

	/**
	 * 得到所有的省数据
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<DicProvince> listAllProvince() {
		String sql = "select * from t_dic_province order by province_code ";
		List<DicProvince> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper(DicProvince.class));
		return list;
	}

	/**
	 * 通过省的编码得到实体
	 * @param province_code
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public DicProvince getProvinceByCode(String province_code) {
		String sql = "select * from t_dic_province where province_code=? ";
		Object[] params = new Object[] { province_code };
		List<DicProvince> list = jdbcTemplate.query(sql, params, new BeanPropertyRowMapper(DicProvince.class));
		if (list.size() > 0)
			return list.get(0);
		else
			return null;
	}

	/**
	 * 通过市的编码得到实体
	 * @param city_code
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public DicCity getCityByCode(String city_code) {
		String sql = "select * from t_dic_city where city_code=? ";
		Object[] params = new Object[] { city_code };
		List<DicCity> list = jdbcTemplate.query(sql, params, new BeanPropertyRowMapper(DicCity.class));
		if (list.size() > 0)
			return list.get(0);
		else
			return null;
	}

	/**
	 * 通过县的编码得到实体
	 * @param district_code
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public DicDistrict getDistrictByCode(String district_code) {
		String sql = "select * from t_dic_district where district_code=? ";
		Object[] params = new Object[] { district_code };
		List<DicDistrict> list = jdbcTemplate.query(sql, params, new BeanPropertyRowMapper(DicDistrict.class));
		if (list.size() > 0)
			return list.get(0);
		else
			return null;
	}

	/**
	 * 得到所有的市数据
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<DicCity> listCity() {
		String sql = "select * from t_dic_city order by city_code ";
		List<DicCity> allList = jdbcTemplate.query(sql, new BeanPropertyRowMapper(DicCity.class));
		return allList;
	}

	/**
	 * 根据省份获取城市
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<ComboboxVO> listCityByProvince_code(String province_code) {
		String sql = "select city_code value,city_name content  from t_dic_city where upper_code=? order by city_code ";
		Object[] params = new Object[] { province_code };
		List<ComboboxVO> allList = jdbcTemplate.query(sql, params, new BeanPropertyRowMapper(ComboboxVO.class));
		return allList;
	}

	/**
	 * 得到所有的县数据
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<DicDistrict> listDistrict() {
		String sql = "select * from t_dic_district order by district_code ";
		List<DicDistrict> allList = jdbcTemplate.query(sql, new BeanPropertyRowMapper(DicDistrict.class));
		return allList;
	}

	/**
	 * 根据城市代码获取所有区县
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<ComboboxVO> listDistrictByCity_code(String city_code) {
		String sql = "select district_code value,district_name content from t_dic_district where upper_code=?  order by district_code ";
		Object[] params = new Object[] { city_code };
		List<ComboboxVO> allList = jdbcTemplate.query(sql, params, new BeanPropertyRowMapper(ComboboxVO.class));
		return allList;
	}

	/**
	 * 通过省的编码得到实体
	 * @param province_code
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public DicProvince getProvinceByName(String name) {
		String sql = "select * from t_dic_province where province_name like ? ";
		Object[] params = new Object[] { "%" + name + "%" };
		List<DicProvince> list = jdbcTemplate.query(sql, params, new BeanPropertyRowMapper(DicProvince.class));
		if (list.size() > 0)
			return list.get(0);
		else
			return null;
	}

}
