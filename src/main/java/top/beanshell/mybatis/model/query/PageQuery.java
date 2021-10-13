package top.beanshell.mybatis.model.query;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import javax.validation.Valid;

/**
 * 通用分页查询对象
 * @author: binchao
 */
@Data
public class PageQuery<T, P> extends Page<T> {

    /**
     * 参数列表
     */
    @Valid
    private P params;
}
