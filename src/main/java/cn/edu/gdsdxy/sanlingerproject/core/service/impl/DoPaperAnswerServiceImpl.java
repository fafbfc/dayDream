package cn.edu.gdsdxy.sanlingerproject.core.service.impl;

import cn.edu.gdsdxy.sanlingerproject.core.domain.ClientUser;
import cn.edu.gdsdxy.sanlingerproject.core.domain.DoPaperAnswer;
import cn.edu.gdsdxy.sanlingerproject.core.mapper.DoPaperAnswerMapper;
import cn.edu.gdsdxy.sanlingerproject.core.service.DoPaperAnswerService;
import cn.edu.gdsdxy.sanlingerproject.util.DayDreamRequestHolder;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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
public class DoPaperAnswerServiceImpl extends ServiceImpl<DoPaperAnswerMapper, DoPaperAnswer> implements DoPaperAnswerService {

    @Autowired
    private DoPaperAnswerMapper doPaperAnswerMapper;

    /**
     * 根据用户id和试卷id，查询做题情况
     * @param paperId 试卷id
     * @return
     */
    @Override
    public List<DoPaperAnswer> selectListByUserIdAndPaperId(Long paperId) {

        ClientUser clientUser = DayDreamRequestHolder.getClientUserLoginInSession();
        Long clientUserId = clientUser.getId();

//        构造where条件
        QueryWrapper queryWrapper = new QueryWrapper();
        Map whereMap = new HashMap();
        whereMap.put("client_user_id", clientUserId);
        whereMap.put("paper_id", paperId);
        queryWrapper.allEq(whereMap);

//        mysql:根据用户id和试卷id，查询做题情况
        List<DoPaperAnswer> list = doPaperAnswerMapper.selectList(queryWrapper);

        return list;
    }
}
