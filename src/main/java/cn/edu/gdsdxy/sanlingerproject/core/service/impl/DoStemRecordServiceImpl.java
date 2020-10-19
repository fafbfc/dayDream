package cn.edu.gdsdxy.sanlingerproject.core.service.impl;

import cn.edu.gdsdxy.sanlingerproject.core.domain.ClientUser;
import cn.edu.gdsdxy.sanlingerproject.core.domain.DoStemRecord;
import cn.edu.gdsdxy.sanlingerproject.core.mapper.DoStemRecordMapper;
import cn.edu.gdsdxy.sanlingerproject.core.service.DoStemRecordService;
import cn.edu.gdsdxy.sanlingerproject.util.DayDreamRequestHolder;
import cn.edu.gdsdxy.sanlingerproject.util.ServiceUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class DoStemRecordServiceImpl extends ServiceImpl<DoStemRecordMapper, DoStemRecord> implements DoStemRecordService {

    @Autowired
    private DoStemRecordMapper doStemRecordMapper;

    /**
     * 根据id，查询用户做题记录
     * @param pageNo 页码
     * @param pageSize 页面大小
     * @return
     */
    @Override
    public Map selectList(Integer pageNo, Integer pageSize) {

//        构建where条件
        Page<DoStemRecord> page = new Page(pageNo, pageSize);
        ClientUser clientUser = DayDreamRequestHolder.getClientUserLoginInSession();
        Long clientUserId = clientUser.getId();
        QueryWrapper<DoStemRecord> queryWrapper = new QueryWrapper();
        queryWrapper.eq("client_user_id", clientUserId);

//        mysql：根据id，查询用户做题记录
        Page<DoStemRecord> doStemRecordPage = doStemRecordMapper.selectPage(page, queryWrapper);

//        分页查询数据封装给前端
        Map dataMap = ServiceUtil.selectPageToMap(doStemRecordPage);
        return dataMap;
    }
}
