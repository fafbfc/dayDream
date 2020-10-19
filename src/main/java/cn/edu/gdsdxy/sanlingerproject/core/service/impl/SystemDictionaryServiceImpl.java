package cn.edu.gdsdxy.sanlingerproject.core.service.impl;

import cn.edu.gdsdxy.sanlingerproject.core.domain.SystemDictionary;
import cn.edu.gdsdxy.sanlingerproject.core.dto.SystemDictionaryAddDTO;
import cn.edu.gdsdxy.sanlingerproject.core.mapper.SystemDictionaryMapper;
import cn.edu.gdsdxy.sanlingerproject.core.service.SystemDictionaryService;
import cn.edu.gdsdxy.sanlingerproject.util.ServiceUtil;
import cn.edu.gdsdxy.sanlingerproject.util.ToolUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
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
public class SystemDictionaryServiceImpl extends ServiceImpl<SystemDictionaryMapper, SystemDictionary> implements SystemDictionaryService {

    @Autowired
    private SystemDictionaryMapper systemDictionaryMapper;

    /**
     * 数据字典查询操作
     * @param pageNo    页码
     * @param pageSize  页面数据条数
     * @param keyword   关键字
     * @return
     */
    @Override
    public Map<String, Object> selectList(Integer pageNo, Integer pageSize, String keyword) {

//        构造where条件
        Page<SystemDictionary> page = new Page(pageNo, pageSize);

        QueryWrapper<SystemDictionary> queryWrapper = null;
        if( keyword != null && !"".equals(keyword) )
        {
            String[] split = keyword.split(";");
            queryWrapper = new QueryWrapper();
            for (String s : split) {
                queryWrapper.like("title", s).like("onlyTitle", s);
            }
        }

//        分页查询数据查询
        Page<SystemDictionary> systemDictionaryPage = systemDictionaryMapper.selectPage(page, queryWrapper);

//        分页查询数据封装给前端
        Map dataMap = ServiceUtil.selectPageToMap(systemDictionaryPage);
        return dataMap;
    }
}
