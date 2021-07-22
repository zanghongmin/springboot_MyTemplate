package top.zang.mbg;


import cn.hutool.core.util.StrUtil;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.ListUtilities;
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;
import org.mybatis.generator.config.GeneratedKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * 自定义修改mybatis生成的diamante
 * http://mybatis.org/generator/reference/pluggingIn.html
 */
public class MyPlugin extends PluginAdapter {
    private static final Logger logger = LoggerFactory.getLogger(MyPlugin.class);
    private static final String ApiModelProperty = "io.swagger.annotations.ApiModelProperty";
    private static final String ApiModel = "io.swagger.annotations.ApiModel";
    private static final String Data = "lombok.Data";
    private String targetProject;//dao文件输出的目录位置
    private String targetPackage;//dao文件输出的包位置
    public static final String METHOD_BATCH_INSERT = "batchInsert";  // 方法名
    public static final String METHOD_BATCH_UPDATE = "batchUpdate";  // 方法名



    //入口类判断是否可以运行
    public boolean validate(List<String> list) {
        targetProject = properties.getProperty("targetProject");
        targetPackage = properties.getProperty("targetPackage");
        if(targetPackage==null || targetProject==null || targetPackage.trim().isEmpty() || targetProject.trim().isEmpty()) {
            return false;
        }
        logger.info("targetProject:"+targetProject);
        logger.info("targetPackage:"+targetPackage);
        return true;
    }

    //额外生成dao文件，dao继承mapper文件，dao文件中可写自定义的查询sql
    public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {
        try{
            String javaName = introspectedTable.getFullyQualifiedTable().getDomainObjectName() + "Dao.java";
            String targetPackagePath = targetPackage.replaceAll("\\.", "/");
            StringBuilder filePath = new StringBuilder();
            filePath.append(targetProject)
                    .append(File.separator)
                    .append(targetPackagePath)
                    .append(File.separator)
                    .append(javaName);
            if(new File(filePath.toString()).exists()) {
                logger.info(filePath+"文件存在，不生成");
                return new ArrayList<>();
            }
            CompilationUnit unit = generateUnit(introspectedTable);
            GeneratedJavaFile gf = new GeneratedJavaFile(unit, targetProject, "utf-8", this.context.getJavaFormatter());
            return Arrays.asList(gf);
        }catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    //额外生成dao java文件方法
    private CompilationUnit generateUnit(IntrospectedTable introspectedTable) {
        String javaName = introspectedTable.getFullyQualifiedTable().getDomainObjectName() + "Dao";
        //实体类类型
        String mapperInterfaceType = introspectedTable.getMyBatis3JavaMapperType();
        String domainObjectName = introspectedTable.getFullyQualifiedTable().getDomainObjectName();
        StringBuilder builder = new StringBuilder();
        //继承接口 xxxMapper.java
        FullyQualifiedJavaType superClassType = new FullyQualifiedJavaType(
                builder.append(domainObjectName)
                        .append("Mapper").toString()
        );
        Interface dto = new Interface(targetPackage +"."+javaName);
        dto.addSuperInterface(superClassType);
        dto.setVisibility(JavaVisibility.PUBLIC);
        //导入xxxMapper类所在的包
        dto.addImportedType(new FullyQualifiedJavaType(mapperInterfaceType));
        dto.addJavaDocLine("//非官方-自定义自动生成，dao文件中可写自定义的查询sql");
        return dto;
    }


    //给model的类增加@Data、@ApiModel、导入类
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        topLevelClass.addImportedType(this.Data);
        topLevelClass.addImportedType(this.ApiModel);
        topLevelClass.addImportedType(this.ApiModelProperty);
        topLevelClass.addAnnotation("@ApiModel(value = \""+topLevelClass.getType().getShortName()+"\",description=\""+introspectedTable.getRemarks()+"\")");
        topLevelClass.addAnnotation("@Data");
        return true;
    }

    //给model的字段增加ApiModelProperty
    public boolean modelFieldGenerated(Field field, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        String remarks = introspectedColumn.getRemarks();
        if(remarks.contains("\"")){
            remarks = remarks.replace("\"","'");
        }
        if(StrUtil.isEmpty(remarks)){
            remarks = field.getName();
        }
        //字段名称保持和数据库中字段一样的名称
        field.setName(introspectedColumn.getActualColumnName());

        //String example = field.getInitializationString().isPresent()?field.getInitializationString().get():"";
        //field.addJavaDocLine("@ApiModelProperty(name = \""+field.getName()+"\",value = \""+remarks+"\", example=\"" + example +"\",required = false)");
        //给model的字段添加swagger注解
        field.addJavaDocLine("@ApiModelProperty(value = \""+remarks+"\")");
        return true;
    }

    //给model中Example类型增加注释
    public boolean modelExampleClassGenerated(TopLevelClass topLevelClass,
                                              IntrospectedTable introspectedTable) {



        List<Method> methods = topLevelClass.getInnerClasses().get(0).getMethods();
        List<IntrospectedColumn>  introspectedColumns = introspectedTable.getAllColumns();
        for(Method method:methods){
            String methodname = method.getName();
            for(IntrospectedColumn introspectedColumn:introspectedColumns){
                introspectedColumn.setJavaProperty(introspectedColumn.getActualColumnName());
                if(methodname.toLowerCase().startsWith("and"+introspectedColumn.getJavaProperty().toLowerCase())){
                    method.addJavaDocLine("//"+introspectedColumn.getRemarks());
                }
            }
        }
        return true;
    }


    //不要model的get方法
    public boolean modelGetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        return false;
    }
    //不要model的set方法
    public boolean modelSetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        return false;
    }




    //mapper文件中生成batchInsert
    //参考 https://github.com/itfsw/mybatis-generator-plugin
    public boolean clientGenerated(Interface interfaze, IntrospectedTable introspectedTable) {
        // 1. batchInsert
        Method mBatchInsert = new Method(METHOD_BATCH_INSERT);
        mBatchInsert.setReturnType(FullyQualifiedJavaType.getIntInstance());
        // 添加参数
        FullyQualifiedJavaType tList = FullyQualifiedJavaType.getNewListInstance();
        tList.addTypeArgument(introspectedTable.getRules().calculateAllFieldsClass());
        mBatchInsert.addParameter(new Parameter(tList, "list", "@Param(\"list\")"));
        // interface 增加方法
        mBatchInsert.setAbstract(true);
        mBatchInsert.addJavaDocLine("//非官方-自定义自动生成");
        interfaze.addMethod(mBatchInsert);

        // 2. batchUpsert
        Method mBatchUpsert = new Method(METHOD_BATCH_UPDATE);
        mBatchUpsert.setReturnType(FullyQualifiedJavaType.getIntInstance());
        FullyQualifiedJavaType returnType = FullyQualifiedJavaType.getNewListInstance();
        returnType.addTypeArgument(introspectedTable.getRules().calculateAllFieldsClass());
        mBatchUpsert.addParameter(new Parameter(returnType, "list", "@Param(\"list\")"));
        // interface 增加方法
        mBatchUpsert.setAbstract(true);
        mBatchUpsert.addJavaDocLine("//非官方-自定义自动生成");
        interfaze.addMethod(mBatchUpsert);
        return true;
    }

    //xml文件中增加批量插入语句
    //参考 https://github.com/itfsw/mybatis-generator-plugin
    public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
        List<IntrospectedColumn>  introspectedColumns = introspectedTable.getAllColumns();
        for(IntrospectedColumn introspectedColumn:introspectedColumns){
            introspectedColumn.setJavaProperty(introspectedColumn.getActualColumnName());
        }


        // 1. batchInsert
        XmlElement batchInsertEle = new XmlElement("insert");
        batchInsertEle.addAttribute(new Attribute("id", METHOD_BATCH_INSERT));
        // 参数类型
        batchInsertEle.addAttribute(new Attribute("parameterType", "map"));
        GeneratedKey gk = introspectedTable.getGeneratedKey();
        if (gk != null) {
            Optional<IntrospectedColumn> introspectedColumn_Optional = introspectedTable.getColumn(gk.getColumn());
            IntrospectedColumn introspectedColumn = introspectedColumn_Optional.get();
            if (introspectedColumn != null) {
                // 使用JDBC的getGenereatedKeys方法获取主键并赋值到keyProperty设置的领域模型属性中。所以只支持MYSQL和SQLServer
                batchInsertEle.addAttribute(new Attribute("useGeneratedKeys", "true")); //$NON-NLS-1$ //$NON-NLS-2$
                batchInsertEle.addAttribute(new Attribute("keyProperty", introspectedColumn.getJavaProperty())); //$NON-NLS-1$
                batchInsertEle.addAttribute(new Attribute("keyColumn", introspectedColumn.getActualColumnName())); //$NON-NLS-1$
            }
        }
        StringBuilder insertClause = new StringBuilder();
        StringBuilder valuesClause = new StringBuilder();
        insertClause.append("insert into "); //$NON-NLS-1$
        insertClause.append(introspectedTable.getFullyQualifiedTableNameAtRuntime());
        insertClause.append(" ("); //$NON-NLS-1$
        valuesClause.append(" ("); //$NON-NLS-1$
        List<String> valuesClauses = new ArrayList<String>();
        List<IntrospectedColumn> columns = ListUtilities.removeIdentityAndGeneratedAlwaysColumns(introspectedTable.getAllColumns());
        for (int i = 0; i < columns.size(); i++) {
            IntrospectedColumn introspectedColumn = columns.get(i);
            insertClause.append("`"+MyBatis3FormattingUtilities.getEscapedColumnName(introspectedColumn)+"`");
            // 生成foreach下插入values
            valuesClause.append(MyBatis3FormattingUtilities.getParameterClause(introspectedColumn, "item."));
            if (i + 1 < columns.size()) {
                insertClause.append(", "); //$NON-NLS-1$
                valuesClause.append(", "); //$NON-NLS-1$
            }
        }
        insertClause.append(')');
        batchInsertEle.addElement(new TextElement(insertClause.toString()));
        valuesClause.append(')');
        valuesClauses.add(valuesClause.toString());
        // 添加foreach节点
        XmlElement foreachElement = new XmlElement("foreach");
        foreachElement.addAttribute(new Attribute("collection", "list"));
        foreachElement.addAttribute(new Attribute("item", "item"));
        foreachElement.addAttribute(new Attribute("separator", ","));
        for (String clause : valuesClauses) {
            foreachElement.addElement(new TextElement(clause));
        }
        // values 构建
        batchInsertEle.addElement(new TextElement("values"));
        batchInsertEle.addElement(foreachElement);
        if (context.getPlugins().sqlMapInsertElementGenerated(batchInsertEle, introspectedTable)) {
            document.getRootElement().addElement(batchInsertEle);
        }

        // 2. batchUpsert
        XmlElement batchUpsertEle = new XmlElement("update");
        batchUpsertEle.addAttribute(new Attribute("id", METHOD_BATCH_UPDATE));
        // 参数类型
        batchUpsertEle.addAttribute(new Attribute("parameterType", "java.util.List"));
        // 添加set节点
        XmlElement setElement = new XmlElement("set");
        columns = ListUtilities.removeIdentityAndGeneratedAlwaysColumns(introspectedTable.getAllColumns());
        for (int i = 0; i < columns.size(); i++) {
            String columnname_Parameter = columns.get(i).getJavaProperty();
            String columnname = MyBatis3FormattingUtilities.getEscapedColumnName(columns.get(i));
            XmlElement ifElement = new XmlElement("if");
            ifElement.addAttribute(new Attribute("test", "item."+columnname_Parameter+" != null"));
            ifElement.addElement(new TextElement("`"+columnname + "` = #{item."+ columnname_Parameter + "},"));
            setElement.addElement(ifElement);
        }
        // 添加foreach节点
        foreachElement = new XmlElement("foreach");
        foreachElement.addAttribute(new Attribute("collection", "list"));
        foreachElement.addAttribute(new Attribute("item", "item"));
        foreachElement.addAttribute(new Attribute("separator", ";"));
        foreachElement.addAttribute(new Attribute("index", "index"));
        foreachElement.addAttribute(new Attribute("open", ""));
        foreachElement.addAttribute(new Attribute("close", ""));
        foreachElement.addElement(new TextElement("UPDATE " + introspectedTable.getFullyQualifiedTableNameAtRuntime()));
        foreachElement.addElement(setElement);
        foreachElement.addElement(new TextElement("where `id` = #{item.id}"));

        batchUpsertEle.addElement(foreachElement);
        if (context.getPlugins().sqlMapInsertElementGenerated(batchUpsertEle, introspectedTable)) {
            document.getRootElement().addElement(batchUpsertEle);
        }
        return true;
    }


}