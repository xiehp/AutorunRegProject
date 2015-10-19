package xie.web.base.db.entity.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

/**
 * Created by xie on 2015/10/20.
 */
public class EntityUserAndDateListener {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @PrePersist
    public void prePersist(XBaseCommonEntity entity) {
        logger.info("do prePersist EntityUserAndDateListener");
        Date date = new Date();
        String user = "system";
        entity.setCreatUser(user);
        entity.setCreateDate(date);
        entity.setUpdateUser(user);
        entity.setUpdateDate(date);
    }

    @PreUpdate
    public void preUpdate(XBaseCommonEntity entity) {
        logger.info("do preUpdate EntityUserAndDateListener");
        Date date = new Date();
        String user = "system";
        entity.setUpdateUser(user);
        entity.setUpdateDate(date);
    }
}
