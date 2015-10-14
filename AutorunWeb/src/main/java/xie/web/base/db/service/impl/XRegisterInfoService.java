package xie.web.base.db.service.impl;

import java.util.Random;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.profiler.Profiler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import xie.web.base.db.dao.IRegisterInfoDao;
import xie.web.base.db.entity.impl.XRegisterInfoEntity;
import xie.web.base.db.service.IRegisterInfoService;

@Service
@Transactional
public class XRegisterInfoService implements IRegisterInfoService {

	Logger slf4jLogger = LoggerFactory.getLogger(getClass());

	Logger slf4jLogger2 = LoggerFactory.getLogger("FuhaoConsoleLogger");

	Log commonLogger = LogFactory.getLog(getClass());

	// @Autowired
	// @Qualifier("IRegisterInfoDao")
	// @Resource
	// private IRegisterInfoDao<XRegisterInfoEntity> registerInfoDao;
	@Autowired
	private IRegisterInfoDao registerInfoDao;

	@PostConstruct
	public void postConstruct1() {

		Profiler profiler = new Profiler("BASIC");
		profiler.start("A1");
		System.out.println("postConstruct1 XRegisterInfoService XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		System.out.println(this);
		System.out.println(registerInfoDao);

		profiler.start("A2");
		slf4jLogger.error("slf4jLogger postConstruct1 XRegisterInfoService XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		slf4jLogger.error(this.toString());
		slf4jLogger.error(registerInfoDao.toString());
		slf4jLogger2.error("slf4jLogger2 postConstruct1 XRegisterInfoService XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");

		profiler.start("A3");
		profiler.stop().print();
		commonLogger.error("commonLogger postConstruct1 XRegisterInfoService XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		commonLogger.error(this.toString());
		commonLogger.error(registerInfoDao.toString());

		profiler.start("B1");
		profiler.stop().print();
	}

	@PreDestroy
	public void preDestroy1() {
		System.out.println("preDestroy1 XRegisterInfoService XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		slf4jLogger.error("slf4jLogger preDestroy1 XRegisterInfoService XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		slf4jLogger2.error("slf4jLogger2 preDestroy1 XRegisterInfoService XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		commonLogger.error("commonLogger preDestroy1 XRegisterInfoService XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public XRegisterInfoEntity register(String serialNumber, String pcInfo) {
		XRegisterInfoEntity aaa = new XRegisterInfoEntity();
		aaa.setRegisterInfoId((new Random()).nextInt(Integer.MAX_VALUE));
		aaa.setSerialNumber(serialNumber);
		aaa.setPcInfo(pcInfo);

		return registerInfoDao.save(aaa);
	}

	public XRegisterInfoEntity findBySerialNumber(String serialNumber) {
		return registerInfoDao.findBySerialNumber(serialNumber);
	}
}
