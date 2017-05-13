package com.critc.dic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.critc.common.vo.ComboboxVO;
import com.critc.dic.dao.DicProvinceDao;
import com.critc.dic.model.DicCity;
import com.critc.dic.model.DicDistrict;
import com.critc.dic.model.DicProvince;

@Service
public class DicProvinceService {
	@Autowired
	private DicProvinceDao dicProvinceDao;

	public List<ComboboxVO> listProvince() {
		return dicProvinceDao.listProvince();
	}

	public List<DicCity> listCity() {
		return dicProvinceDao.listCity();
	}

	/**
	 * 根据省份代码获取下属所有市
	 * @param province_code
	 * @return
	 */
	public List<ComboboxVO> listCityByProvince_code(String province_code) {
		return dicProvinceDao.listCityByProvince_code(province_code);
	}

	public List<DicDistrict> listDistrict() {
		return dicProvinceDao.listDistrict();
	}

	/**
	 * 根据城市代码获取所有区县
	 * @param city_code
	 * @return
	 */
	public List<ComboboxVO> listDistrictByCity_code(String city_code) {
		return dicProvinceDao.listDistrictByCity_code(city_code);
	}

	public DicCity getCityByCode(String city_code) {
		return dicProvinceDao.getCityByCode(city_code);
	}

	public DicDistrict getDistrictByCode(String district_code) {
		return dicProvinceDao.getDistrictByCode(district_code);
	}

	public DicProvince getProvinceByCode(String province_code) {
		return dicProvinceDao.getProvinceByCode(province_code);
	}
	
	/**
	 * 根据省份名称获取省份编码
	 * @param province_name
	 * @return
	 */
	public String getProvince_codByname(String province_name) {
		DicProvince dicProvince = dicProvinceDao.getProvinceByName(province_name);
		if (dicProvince == null)
			return "";
		else
			return dicProvince.getProvince_code();
	}


	public List<DicProvince> listAllProvince(){
	return 	dicProvinceDao.listAllProvince();
	}
}
