package com.critc.util.test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * 单元测试工具,默认每个测试都是一个事务，且触发的增删改都进行回滚。
 * 
 * @author 孔垂云
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/applicationContext.xml" })
@Transactional
public class BaseTest {

}
