package cn.edu.gdsdxy.sanlingerproject.core.service;

import cn.edu.gdsdxy.sanlingerproject.core.domain.DoPaperAnswer;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2020-10-12
 */
public interface DoPaperAnswerService extends IService<DoPaperAnswer> {

    /**
     * 根据用户id和试卷id，查询做题情况
     * @param paperId 试卷id
     * @return
     */
    List<DoPaperAnswer> selectListByUserIdAndPaperId(Long paperId);
}
