package cn.edu.gdsdxy.sanlingerproject.core.service;

import cn.edu.gdsdxy.sanlingerproject.core.domain.DoStemRecord;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2020-10-12
 */
public interface DoStemRecordService extends IService<DoStemRecord> {

    /**
     * 根据id，查询用户做题记录
     * @param pageNo 页码
     * @param pageSize 页面大小
     * @return
     */
    Map selectList(Integer pageNo, Integer pageSize);
}
