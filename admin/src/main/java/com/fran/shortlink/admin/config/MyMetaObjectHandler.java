package com.fran.shortlink.admin.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import java.util.Date;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 * MyBatis-Plus Meta Object Handler
 * <p>
 * Automatically handles common field values during insert and update operations, such as creation
 * time, update time, and deletion flag.
 * <p>
 * - On insert: sets createTime, updateTime, and delFlag. - On update: updates updateTime.
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    /**
     * Automatically called during an insert operation.
     * <p>
     * Fields filled: - createTime: set to current date and time - updateTime: set to current date
     * and time - delFlag: set to 0 (meaning 'not deleted')
     *
     * @param metaObject the metaobject parameter
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        strictInsertFill(metaObject, "createTime", Date::new, Date.class);
        strictInsertFill(metaObject, "updateTime", Date::new, Date.class);
        strictInsertFill(metaObject, "delFlag", () -> 0, Integer.class);
    }

    /**
     * Automatically called during an update operation.
     * <p>
     * Fields filled: - updateTime: refreshed to current date and time
     *
     * @param metaObject the metaobject parameter
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        strictInsertFill(metaObject, "updateTime", Date::new, Date.class);
    }
}
