package top.beanshell.mybatis.util;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.beanshell.common.model.dto.PageQueryDTO;
import top.beanshell.common.model.dto.PageResultDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页工具类
 * @author mobinchao
 */
public final class PageUtil {

    private PageUtil() {
    }

    /**
     * 初始化分页对象
     * @param pageQuery pageQuery instance
     * @param <T>       DB POJO Class
     * @return          Page instance
     */
    public static <T> Page<T> getPage(PageQueryDTO pageQuery) {
        Page<T> page = new Page<>();
        page.setCurrent(pageQuery.getCurrent());
        page.setSize(pageQuery.getPageSize());

        return page;
    }


    /**
     * 数据库分页对象转换成DTO
     * @param page      page instance
     * @param cls       dto class
     * @param <D>       dto class
     * @return          pageResultDTO instance
     */
    public static <D extends Serializable> PageResultDTO<D> getPageResult(Page page, Class<D> cls) {

        PageResultDTO<D> pageResult = new PageResultDTO<>();
        pageResult.setCurrent((int) page.getCurrent());
        pageResult.setPageSize((int) page.getSize());
        pageResult.setTotal(page.getTotal());
        pageResult.setTotalPage(page.getPages());
        List<D> resultList = new ArrayList<>();
        for (Object data : page.getRecords()) {
            D result = BeanUtil.toBean(data, cls);
            resultList.add(result);
        }
        pageResult.setRecords(resultList);
        return pageResult;
    }
}
