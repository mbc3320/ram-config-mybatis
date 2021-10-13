package top.beanshell.mybatis.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.beanshell.common.service.ServiceI;
import top.beanshell.mybatis.model.pojo.BaseEntity;

import java.util.Date;

/**
 * CRUD抽象服务实现
 * @param <D>   DTO
 * @param <T>   DB POJO
 * @param <M>   Mapper
 * @author mobinchao
 */
public class CRUDServiceImpl<D, T extends BaseEntity, M extends BaseMapper<T>>
        extends ServiceImpl<M, T>
        implements ServiceI<D> {


    @Override
    public boolean saveEntity(D dto) {
        T entity = BeanUtil.toBean(dto, currentModelClass());
        entity.init();
        return super.save(entity);
    }

    @Override
    public boolean updateEntityById(D dto) {
        T entity = BeanUtil.toBean(dto, currentModelClass());
        entity.setUpdateTime(new Date());
        return super.updateById(entity);
    }

    @Override
    public D getById(Long id) {
        T entity = super.getById(id);
        if (null == entity) {
            return null;
        }
        return BeanUtil.toBean(entity, currentDtoClass());
    }

    @Override
    public boolean removeById(Long id) {
        return super.removeById(id);
    }


    /**
     * 当前DTO类
     * @return  dto class
     */
    protected Class<D> currentDtoClass() {
        return (Class<D>) ReflectionKit.getSuperClassGenericType(getClass(), CRUDServiceImpl.class, 0);
    }

    /**
     * 当前DB POJO类
     * @return pojo class
     */
    @Override
    protected Class<T> currentModelClass() {
        return (Class<T>) ReflectionKit.getSuperClassGenericType(this.getClass(), ServiceImpl.class, 1);
    }

    /**
     * 当前DAO Mapper类
     * @return mapper class
     */
    @Override
    protected Class<M> currentMapperClass() {
        return (Class<M>) ReflectionKit.getSuperClassGenericType(this.getClass(), CRUDServiceImpl.class, 2);
    }
}
