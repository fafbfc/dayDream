package cn.edu.gdsdxy.sanlingerproject.core.service;

import cn.edu.gdsdxy.sanlingerproject.core.domain.TestPaper;
import cn.edu.gdsdxy.sanlingerproject.core.dto.BaseDeleteDTO;
import cn.edu.gdsdxy.sanlingerproject.core.dto.IsRecommendChangeDTO;
import cn.edu.gdsdxy.sanlingerproject.core.dto.TestPaperAddDTO;
import cn.edu.gdsdxy.sanlingerproject.core.dto.TestPaperUpdataDTO;
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
public interface TestPaperService extends IService<TestPaper> {

    /**
     * 添加试卷
     * @param testPaperAddDTO 接收客户端的添加试卷数据
     * @return
     */
    void add(TestPaperAddDTO testPaperAddDTO);

    /**
     * 查询试卷
     * @param pageNo 页码
     * @param pageSize 页面大小
     * @param map 封装数据，包含试卷名
     *            , gradeName（年级）, keywork（试卷名）, subject（学科）
     * @return
     */
    Map selectList(Integer pageNo, Integer pageSize, Map<String, String> map);

    /**
     * 试卷变为是否推荐
     * @param isRecommendChangeDTO 接收客户端的传递的推荐变更数据
     * @return
     */
    void isRecommendChange(IsRecommendChangeDTO isRecommendChangeDTO);

    /**
     * 修改试卷
     * @param testPaperIUpdataDTO 接收客户端的传递的修改试卷数据
     * @return
     */
    void updata(TestPaperUpdataDTO testPaperIUpdataDTO);

    /**
     * 后台管理用户删除试卷接口
     * @param baseDeleteDTO 接收后台管理用户的删除试卷信息
     * @return
     */
    void delete(BaseDeleteDTO baseDeleteDTO);
}
