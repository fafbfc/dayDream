package cn.edu.gdsdxy.sanlingerproject.core.service;

import cn.edu.gdsdxy.sanlingerproject.core.domain.SystemDictionary;
import cn.edu.gdsdxy.sanlingerproject.core.dto.SystemDictionaryAddDTO;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.validation.Valid;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2020-10-12
 */
public interface SystemDictionaryService extends IService<SystemDictionary> {

    /**
     * 数据字典查询操作
     * @param pageNo    页码
     * @param pageSize  页面数据条数
     * @param keyword   关键字
     * @return
     */
    Map<String, Object> selectList(Integer pageNo, Integer pageSize, String keyword);
}
