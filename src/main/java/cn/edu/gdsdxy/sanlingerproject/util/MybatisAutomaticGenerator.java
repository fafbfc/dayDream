package cn.edu.gdsdxy.sanlingerproject.util;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;

public class MybatisAutomaticGenerator
{
    public static void main(String[] args)
    {
//          需要构建一个 代码自动生成器 对象
        AutoGenerator mpg = new AutoGenerator();
//         配置策略

//         1、全局配置
        GlobalConfig gc = new GlobalConfig();
//        获取当前系统目录工作目录，就是mybatis_plus路径
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath+"/src/main/java");
//        是否打开window文件夹
        gc.setOpen(false);

//         重新生成是否覆盖
        gc.setFileOverride(false);
//         去Service的I前缀，%s == 类名
        gc.setServiceName("%sService");

//        设置id生成策略
//        日期类型,仅仅是日期
//        是否Swagger2支持
        gc.setIdType(IdType.ASSIGN_ID);
        gc.setDateType(DateType.ONLY_DATE);
        gc.setSwagger2(true);

//        自动构建器设置总体配置
        mpg.setGlobalConfig(gc);

//        2、todo 设置数据源
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql:///daydream?useSSL=false&useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

//        3、todo 包的配置
        PackageConfig pc = new PackageConfig();
//        blog目录包含：controller，domain，mapper，service
        pc.setParent("cn.edu.gdsdxy.sanlingerproject");
        pc.setModuleName("");
        pc.setEntity("core.domain");
        pc.setService("core.service");
        pc.setServiceImpl("core.service.impl");
        pc.setMapper("core.mapper");
        pc.setXml("core.mapper.xml");
        pc.setController("api.controller");

//        自动构建器设置包配置
        mpg.setPackageInfo(pc);

//        4、策略配置
        StrategyConfig strategy = new StrategyConfig();
//        todo 表名，需要表生成对应代码，只需要改这里
        strategy.setInclude("test_paper");
//        设置要映射的表名
//        类名驼峰，字段名驼峰
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//         自动lombok；
        strategy.setEntityLombokModel(true);

//        逻辑删除
        strategy.setLogicDeleteFieldName("deleted");

//         自动填充配置
        TableFill gmtCreate = new TableFill("create_time", FieldFill.INSERT);
        TableFill gmtModified = new TableFill("update_time", FieldFill.INSERT_UPDATE);

        ArrayList<TableFill> tableFills = new ArrayList<>();
        tableFills.add(gmtCreate);
        tableFills.add(gmtModified);
        strategy.setTableFillList(tableFills);

//         乐观锁
        strategy.setVersionFieldName("version");

//        RESTFUL风格的URL：localhost:8080/hello/2
        strategy.setRestControllerStyle(true);

//         localhost:8080/hello_id_2
//        strategy.setControllerMappingHyphenStyle(true);

        mpg.setStrategy(strategy);

        mpg.execute(); //执行
    }
}