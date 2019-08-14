package com.generate.demo.common;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * Author GreedyStar
 * Date   2018/4/19
 */
public class FileUtil {

    /**
     * @param type     使用模板类型
     * @param data     填充数据
     * @param filePath 输出文件
     * @throws IOException
     * @throws TemplateException
     */
    public static void generateToJava(int type, Object data, String filePath) throws IOException, TemplateException {
        File file = new File(filePath);
        if (file.exists()) {
            System.err.println("ERROR: " + file.getPath().substring(file.getPath().lastIndexOf("\\") + 1, file.getPath().length()) + " 已存在，请手动修改");
            return;
        }
        Template tpl = getTemplate(type); // 获取模板文件
        // 填充数据
        StringWriter writer = new StringWriter();
        tpl.process(data, writer);
        writer.flush();
        // 写入文件
        FileOutputStream fos = new FileOutputStream(filePath);
        OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
        BufferedWriter bw = new BufferedWriter(osw, 1024);
        tpl.process(data, bw);
        fos.close();
    }
    /**
     * @param type     使用模板类型
     * @param data     填充数据
     * @param filePath 输出文件
     * @throws IOException
     * @throws TemplateException
     */
    public static String getGenerateString(int type, Object data) throws IOException, TemplateException {
    	Template tpl = getTemplate(type); // 获取模板文件
    	 StringWriter out = new StringWriter();
         tpl.process(data, out);
        return out.toString();
    }


    /**
     * 获取模板
     *
     * @param type 模板类型
     * @return
     * @throws IOException
     */
    private static Template getTemplate(int type) throws IOException {
        switch (type) {
            case FreemarketConfigUtils.TYPE_ENTITY:
                return FreemarketConfigUtils.getInstance().getTemplate("Entity.ftl");
            case FreemarketConfigUtils.TYPE_DAO:
                return FreemarketConfigUtils.getInstance().getTemplate("Dao.ftl");
            case FreemarketConfigUtils.TYPE_SERVICE:
                return FreemarketConfigUtils.getInstance().getTemplate("Service.ftl");
            case FreemarketConfigUtils.TYPE_CONTROLLER:
                return FreemarketConfigUtils.getInstance().getTemplate("Controller.ftl");
            case FreemarketConfigUtils.TYPE_MAPPER:
                return FreemarketConfigUtils.getInstance().getTemplate("Mapper.ftl");
            case FreemarketConfigUtils.TYPE_INTERFACE:
                return FreemarketConfigUtils.getInstance().getTemplate("Interface.ftl");
            case FreemarketConfigUtils.TYPE_API_ADD_MODIFY:
            	return FreemarketConfigUtils.getInstance().getTemplate("AddOrModifyApi.ftl");
            case FreemarketConfigUtils.TYPE_API_DELETE:
            	return FreemarketConfigUtils.getInstance().getTemplate("DeleteApi.ftl");
            case FreemarketConfigUtils.TYPE_API_LIST:
            	return FreemarketConfigUtils.getInstance().getTemplate("GetListApi.ftl");
            case FreemarketConfigUtils.TYPE_API_ONE:
            	return FreemarketConfigUtils.getInstance().getTemplate("GetOneApi.ftl");
            case FreemarketConfigUtils.TYPE_API_PAGE:
            	return FreemarketConfigUtils.getInstance().getTemplate("GetPageApi.ftl");
            default:
                return null;
        }
    }

    private static String getBasicProjectPath() {
        String path = new File(FileUtil.class.getClassLoader().getResource("").getFile()).getPath() + File.separator;
        StringBuilder sb = new StringBuilder();
        sb.append(path.substring(0, path.indexOf("target"))).append("src").append(File.separator).append("main").append(File.separator);
        return sb.toString();
    }

    /**
     * 获取源码路径
     *
     * @return
     */
    public static String getSourcePath() {
        StringBuilder sb = new StringBuilder();
        sb.append(getBasicProjectPath()).append("java").append(File.separator);
        return sb.toString();
    }

    /**
     * 获取资源文件路径
     *
     * @return
     */
    public static String getResourcePath() {
        StringBuilder sb = new StringBuilder();
        sb.append(getBasicProjectPath()).append("resources").append(File.separator);
        return sb.toString();
    }

    public static String getApiClassName(String entityName,int type){
        switch (type) {
            case FreemarketConfigUtils.TYPE_API_ADD_MODIFY:
            	return "AddOrModify"+entityName + ".java";
            case FreemarketConfigUtils.TYPE_API_DELETE:
            	return "Delete"+entityName + ".java";
            case FreemarketConfigUtils.TYPE_API_LIST:
            	return "Get"+entityName + "List.java";
            case FreemarketConfigUtils.TYPE_API_ONE:
            	return "Get"+entityName + ".java";
            case FreemarketConfigUtils.TYPE_API_PAGE:
            	return "Get"+entityName + "Page.java";
            default:
                return "";
        }
    
    }
}
