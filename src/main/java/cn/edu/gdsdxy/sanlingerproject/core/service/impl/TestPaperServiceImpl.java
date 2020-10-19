package cn.edu.gdsdxy.sanlingerproject.core.service.impl;

import cn.edu.gdsdxy.sanlingerproject.core.domain.AdminUser;
import cn.edu.gdsdxy.sanlingerproject.core.domain.TestPaper;
import cn.edu.gdsdxy.sanlingerproject.core.dto.BaseDeleteDTO;
import cn.edu.gdsdxy.sanlingerproject.core.dto.IsRecommendChangeDTO;
import cn.edu.gdsdxy.sanlingerproject.core.dto.TestPaperAddDTO;
import cn.edu.gdsdxy.sanlingerproject.core.dto.TestPaperUpdataDTO;
import cn.edu.gdsdxy.sanlingerproject.core.mapper.TestPaperMapper;
import cn.edu.gdsdxy.sanlingerproject.core.service.TestPaperService;
import cn.edu.gdsdxy.sanlingerproject.util.ServiceUtil;
import cn.edu.gdsdxy.sanlingerproject.util.ToolUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sun.xml.internal.fastinfoset.stax.events.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2020-10-12
 */
@Service
public class TestPaperServiceImpl extends ServiceImpl<TestPaperMapper, TestPaper> implements TestPaperService {

    @Autowired
    private TestPaperMapper testPaperMapper;
    /**
     * 添加试卷
     * @param testPaperAddDTO 接收客户端的添加试卷数据
     * @return
     */
    @Override
    public void add(TestPaperAddDTO testPaperAddDTO) {

//        testPaperAddDTO赋值到TestPaper
        TestPaper testPaper = ToolUtil.ObjToObj(testPaperAddDTO, TestPaper.class);

//        初始化数据
        testPaper.setIsRecommend(0);
        testPaper.setVersion(0);
        testPaper.setDeleted(0);

//        添加试卷数据到数据库
        this.save(testPaper);
    }

    /**
     * 查询试卷
     * @param pageNo 页码
     * @param pageSize 页面大小
     * @param map 封装数据，包含试卷名
     *            , gradeName（年级）, keywork（试卷名）, subject（学科）
     * @return
     */
    @Override
    public Map selectList(Integer pageNo, Integer pageSize, Map<String, String> map) {

//        构造where条件
        Page<TestPaper> page = new Page(pageNo, pageSize);

//        获取map的数据
        String gradeName = map.get("gradeName");
        String keyword = map.get("keyword");
        String subject = map.get("subject");

        QueryWrapper<TestPaper> queryWrapper = new QueryWrapper();
        if ( !Util.isEmptyString(gradeName) )
            queryWrapper.eq("grade_name", gradeName);

        if ( !Util.isEmptyString(subject) )
            queryWrapper.eq("subject", subject);

        if ( !Util.isEmptyString(keyword) ) {
            queryWrapper.like("name", keyword);
        }

//        根据条件，查询试卷
        Page<TestPaper> testPaperPage = testPaperMapper.selectPage(page, queryWrapper);


//        分页查询数据封装给前端
        Map dataMap = ServiceUtil.selectPageToMap(testPaperPage);
        return dataMap;

    }

    /**
     * 试卷变为是否推荐
     * @param isRecommendChangeDTO 接收客户端的传递的推荐变更数据
     * @return
     */
    @Override
    public void isRecommendChange(IsRecommendChangeDTO isRecommendChangeDTO) {

//        封装到TestPaper对象
        Long id = isRecommendChangeDTO.getId();
        Integer isRecommend = isRecommendChangeDTO.getIsRecommend();

        TestPaper testPaper = new TestPaper();
        testPaper.setId(id);
        testPaper.setIsRecommend(isRecommend);

//        更新试卷的数据库
        this.updateById(testPaper);
    }

    /**
     * 修改试卷
     * @param testPaperIUpdataDTO 接收客户端的传递的修改试卷数据
     * @return
     */
    @Override
    public void updata(TestPaperUpdataDTO testPaperIUpdataDTO) {
//        testPaperIUpdataDTO 赋值到TestPaper
        TestPaper testPaper = ToolUtil.ObjToObj(testPaperIUpdataDTO, TestPaper.class);

//        添加试卷数据到数据库
        this.updateById(testPaper);
    }

    /**
     * 后台管理用户删除试卷接口
     * @param baseDeleteDTO 接收后台管理用户的删除试卷信息
     * @return
     */
    @Override
    public void delete(BaseDeleteDTO baseDeleteDTO) {
//        mysql: 删除后台管理的用户数据
        this.removeById(baseDeleteDTO.getId());
    }
}
