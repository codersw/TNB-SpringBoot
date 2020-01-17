package com.simple.generator;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.*;

/**
 * 逆向工程
 * @author swen
 */
public class Generator {

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    private static String scanner() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入表名,多个英文逗号分割：");
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的表名,多个英文逗号分割！");
    }

    /**
     * 执行代码生成器
     * @param args
     */
    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        //全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setAuthor("swen");
        gc.setSwagger2(true);
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(true);
        gc.setFileOverride(true);
        gc.setOpen(false);
        gc.setDateType(DateType.ONLY_DATE);
        mpg.setGlobalConfig(gc);


        //数据源配置
        DataSourceConfig dc = new DataSourceConfig();
        dc.setUrl("jdbc:mysql://localhost:3306/require?characterEncoding=utf-8&serverTimezone=GMT%2B8&useSSL=false");
        dc.setDriverName("com.mysql.cj.jdbc.Driver");
        dc.setUsername("root");
        dc.setPassword("123456");
        mpg.setDataSource(dc);

        //包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.simple");
        pc.setEntity("model");
        mpg.setPackageInfo(pc);


        //自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        //自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        //自定义mapper.xml
        String templateMapperXmlPath = "/templates/mapper.xml.ftl";
        focList.add(new FileOutConfig(templateMapperXmlPath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath + "/mapper/src/main/resources/mapper/"+ tableInfo.getMapperName() + StringPool.DOT_XML;
            }
        });
        //自定义mapper
        String templateMapperPath = "/templates/mapper.java.ftl";
        focList.add(new FileOutConfig(templateMapperPath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath + "/mapper/src/main/java/"+ pc.getParent().replace(".","/") + "/mapper/" + tableInfo.getMapperName() + StringPool.DOT_JAVA;
            }
        });
        //自定义entity
        String templateEntityPath = "/templates/entity.java.ftl";
        focList.add(new FileOutConfig(templateEntityPath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath + "/model/src/main/java/"+ pc.getParent().replace(".","/") + "/model/" + tableInfo.getEntityName() + StringPool.DOT_JAVA;
            }
        });
        //自定义service
        String templateServicePath = "/templates/service.java.ftl";
        focList.add(new FileOutConfig(templateServicePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath + "/service/src/main/java/"+ pc.getParent().replace(".","/") + "/service/" + tableInfo.getServiceName() + StringPool.DOT_JAVA;
            }
        });
        //自定义serviceImpl
        String templateServiceImplPath = "/templates/serviceImpl.java.ftl";
        focList.add(new FileOutConfig(templateServiceImplPath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath + "/service/src/main/java/"+ pc.getParent().replace(".","/") + "/service/impl/" + tableInfo.getServiceImplName() + StringPool.DOT_JAVA;
            }
        });
        //自定义controller
        String templateControllerPath = "/templates/controller.java.ftl";
        focList.add(new FileOutConfig(templateControllerPath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath + "/web/src/main/java/"+ pc.getParent().replace(".","/") + "/web/controller/" + tableInfo.getControllerName() + StringPool.DOT_JAVA;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        //配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        templateConfig.setEntity("templates/entity.java");
        templateConfig.setController("templates/controller.java");
        templateConfig.setService("templates/service.java");
        templateConfig.setServiceImpl("templates/serviceImpl.java");
        templateConfig.setMapper("templates/mapper.java");
        templateConfig.setEntityKt("templates/mapper.xml");
        mpg.setTemplate(templateConfig);

        //策略配置
        StrategyConfig strategy = new StrategyConfig();
        //设置命名格式
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setInclude(scanner().split(","));
        //实体是否为lombok模型（默认 false）
        strategy.setEntityLombokModel(true);
        //生成 @RestController 控制器
        strategy.setRestControllerStyle(true);
        //驼峰转连字符
        strategy.setControllerMappingHyphenStyle(true);
        //表名前缀
        List<String> tables = Arrays.asList(strategy.getInclude());
        strategy.setTablePrefix(tables.stream().map(table -> table.split("_")[0]).toArray(String[]::new));
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }
}