package com.generate.demo.common;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import freemarker.template.TemplateException;

public class ApiTask {
	private String path;
	private String tableName;
	private String preFix;
	private String author;
	private String moduleName;
	private String createdTime;
	public ApiTask(){
		
	}
	
	  public void run() throws IOException, TemplateException {
		  String noPreFixName = NamingStrategy.removePrefix(tableName,preFix);
		  String packageName = noPreFixName.replace("_", "");
		  String entityName = NamingStrategy.underlineToCamel(noPreFixName);
		  String className = NamingStrategy.capitalFirst(entityName);
	        // 生成Service填充数据
	        Map<String, String> serviceData = new HashMap<>();
	        serviceData.put("className", className);	        
	        serviceData.put("entityName", entityName);	        
	        serviceData.put("packageName", packageName);
	        serviceData.put("author", author);
	        serviceData.put("moduleName", moduleName);
	        serviceData.put("createdTime", createdTime);
	        
	        int[] apiTypeList = new int[]{
	        		FreemarketConfigUtils.TYPE_API_ADD_MODIFY,	
	        		FreemarketConfigUtils.TYPE_API_DELETE,
	        		FreemarketConfigUtils.TYPE_API_ONE,	
	        		FreemarketConfigUtils.TYPE_API_PAGE,	
	        		FreemarketConfigUtils.TYPE_API_LIST,	
	        };
	        
	        for (int i = 0; i < apiTypeList.length; i++) {
	        	String fileName  = FileUtil.getApiClassName(className, apiTypeList[i]);
	        	// 生成Service文件
	        	createFilePathIfNotExists(path+packageName);
	        	FileUtil.generateToJava(apiTypeList[i], serviceData, path+packageName+ "\\"+ fileName);
			}
	    }

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getPreFix() {
		return preFix;
	}

	public void setPreFix(String preFix) {
		this.preFix = preFix;
	}
	
	
    public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	
	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	protected void createFilePathIfNotExists(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) { // 检测文件路径是否存在，不存在则创建
            file.mkdir();
        }
    }
	  
	  
	  
	  
	  
}
