package cn.edu.gdsdxy.sanlingerproject.util;

import cn.edu.gdsdxy.sanlingerproject.core.domain.SystemDictionary;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2020/10/12.
 */
public abstract class ServiceUtil {

    public static <T> Map selectPageToMap(Page<T> dataPage)
    {
//        分页查询数据查询
        List<T> records = dataPage.getRecords();
        long total = dataPage.getTotal();

//        封装返回的数据
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("total", total);
        dataMap.put("list", records);

        return dataMap;
    }
}
