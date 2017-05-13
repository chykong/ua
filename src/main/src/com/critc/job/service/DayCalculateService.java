package com.critc.job.service;

import com.critc.job.dao.DayCalculateDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DayCalculateService {
    @Autowired
    private DayCalculateDao dayCalculateDao;
}
