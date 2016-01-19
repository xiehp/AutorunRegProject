package xie.web.base.db.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.profiler.Profiler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xie.web.base.db.dao.IRegisterInfoDao;
import xie.web.base.db.dao.ISerialNumberInfoDao;
import xie.web.base.db.entity.impl.XRegisterInfoEntity;
import xie.web.base.db.entity.impl.XSerialNumberInfoEntity;
import xie.web.base.db.service.IRegisterInfoService;
import xie.web.base.db.service.XBaseService;
import xie.web.fuhao.utils.FFuhaoWebConst;

@Service
@Transactional
public class XRegisterInfoService extends XBaseService implements IRegisterInfoService {

	Logger slf4jLogger = LoggerFactory.getLogger(getClass());

	Logger slf4jLogger2 = LoggerFactory.getLogger("FuhaoConsoleLogger");

	Log commonLogger = LogFactory.getLog(getClass());

	// @Autowired
	// @Qualifier("IRegisterInfoDao")
	// @Resource
	// private IRegisterInfoDao<XRegisterInfoEntity> registerInfoDao;
	@Autowired
	private IRegisterInfoDao registerInfoDao;
	@Autowired
	private ISerialNumberInfoDao serialNumberInfoDao;

	@PostConstruct
	public void postConstruct1() {

		logger.info("XLoggerClass's log message.");

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

	/**
	 * 保存当前注册信息和对应的机器信息。<br>
	 * 同时更新注册码信息表。<br>
	 * 如果没有就新增，有的话注册数量加1。<br>
	 *
	 * @param serialNumber 注册码
	 * @param pcInfo 注册申请的电脑的信息
	 * @return 成功注册后的信息
	 */
	//@Override
	// @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
//	@Transactional
//	public XRegisterInfoEntity register(String serialNumber, String pcInfo) {
//		// 保存当前注册信息和对应的机器信息
//		XRegisterInfoEntity registerInfoEntity = new XRegisterInfoEntity();
//		registerInfoEntity.setSerialNumber(serialNumber);
//		registerInfoEntity.setPcInfo(pcInfo);
//		registerInfoEntity.setRegistDate(new Date());
//		registerInfoEntity.setRegistCount(1);
//		registerInfoEntity = registerInfoDao.save(registerInfoEntity);
//
//		// 同时更新注册码信息表
//		XSerialNumberInfoEntity serialNumberInfoEntity = serialNumberInfoDao.findBySerialNumber(serialNumber);
//		if (serialNumberInfoEntity == null) {
//			serialNumberInfoEntity = new XSerialNumberInfoEntity();
//			serialNumberInfoEntity.setSerialNumber(serialNumber);
//			serialNumberInfoEntity.setMaxRegistCount(FFuhaoWebConst.INIT_MAX_REGIST_COUNT);
//			serialNumberInfoEntity.setFirstRegistDate(registerInfoEntity.getRegistDate());
//		}
//		int count = registerInfoDao.countBySerialNumber(serialNumber);
//		serialNumberInfoEntity.setNowRegistCount(count);
//		serialNumberInfoEntity = serialNumberInfoDao.save(serialNumberInfoEntity);
//
//		// 将注册码ID信息更新回注册信息中
//		registerInfoEntity.setSerialNumberId(serialNumberInfoEntity.getId());
//		registerInfoEntity = registerInfoDao.save(registerInfoEntity);
//
//		return registerInfoEntity;
//	}

	@Transactional
	@Override
	public XRegisterInfoEntity register(String serialNumber, String encodeSerialNumber, String pcInfo) {
		Date nowDate = new Date();
		
		// 检查注册码表中数据是否存在，同时更新注册码信息表
		XSerialNumberInfoEntity serialNumberInfoEntity = serialNumberInfoDao.findBySerialNumber(serialNumber);
		if (serialNumberInfoEntity == null) {
			serialNumberInfoEntity = new XSerialNumberInfoEntity();
			serialNumberInfoEntity.setSerialNumber(serialNumber);
			serialNumberInfoEntity.setEncodeSerialNumber(encodeSerialNumber);
			serialNumberInfoEntity.setMaxRegistCount(FFuhaoWebConst.REGIST_INFO_TABLE_INIT_MAX_REGIST_COUNT);
			serialNumberInfoEntity.setNowRegistCount(0);
			serialNumberInfoEntity.setFirstRegistDate(nowDate);
			serialNumberInfoEntity = serialNumberInfoDao.save(serialNumberInfoEntity);
		}

		// 保存当前注册信息和对应的机器信息
		XRegisterInfoEntity registerInfoEntity = new XRegisterInfoEntity();
		registerInfoEntity.setSerialNumberId(serialNumberInfoEntity.getId());
		registerInfoEntity.setSerialNumber(serialNumber);
		registerInfoEntity.setPcInfo(pcInfo);
		registerInfoEntity.setRegistDate(nowDate);
		registerInfoEntity.setRegistCount(1);
		registerInfoEntity = registerInfoDao.save(registerInfoEntity);

		// 判断是否已经存在其他该注册码和对应机器信息，没有的话，则该注册码注册次数加1，否则认为注册次数不需要增加
		int dataCount = registerInfoDao.countBySerialNumberIdAndPcInfo(serialNumberInfoEntity.getId(), pcInfo);
		 // 注册记录为0次或者1次  或者超过了同一机器信息最大注册次数的时候，才会去增加该注册码实际注册次数
		if (dataCount <= 1 || dataCount > FFuhaoWebConst.SAME_PCINFO_MAX_REGIST_COUNT) {
			// 更新注册码被注册次数
//			int count = registerInfoDao.countBySerialNumber(serialNumber);
//			serialNumberInfoEntity.setNowRegistCount(count); // TODO 重新计算总次数
			serialNumberInfoEntity.setNowRegistCount(serialNumberInfoEntity.getNowRegistCount() + 1);
			serialNumberInfoEntity = serialNumberInfoDao.save(serialNumberInfoEntity);
		}

		return registerInfoEntity;
	}

	@Override
	public XRegisterInfoEntity findById(String Id) {
		return registerInfoDao.findOne(Id);
	}

	@Override
	public List<XRegisterInfoEntity> findBySerialNumberIdAndPcInfo(String serialNumberId, String pcInfo) {
		return registerInfoDao.findBySerialNumberIdAndPcInfo(serialNumberId, pcInfo);
	}

	@Override
	public int countBySerialNumberIdAndPcInfo(String serialNumberId, String pcInfo) {
		return registerInfoDao.countBySerialNumberIdAndPcInfo(serialNumberId, pcInfo);
	}
}
