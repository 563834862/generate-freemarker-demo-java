package com.generate.demo.generater;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.generate.demo.common.DateUtil;
import com.generate.demo.common.FileUtil;
import com.generate.demo.common.FreemarketConfigUtils;

import freemarker.template.TemplateException;

/**
 * 在dao,service,mapper上追加方法
 * 这个生成工具要结合项目已存在的dao,service,mapper层修改后才能使用
 * @author yexh
 *
 */
public class AppendMethod {
private static final String basePath = "D:\\git\\generate-freeamarker-demo-java\\src\\com\\generate\\demo";
	public static void main(String[] args) {
		
		/**
		 * 填写下面 5个参数 即可
		 */
		
		String moduleName = "SupplyGoodsForecast";//模块名
		String  returnType = "List<SupplyGoodsForecast>";//返回值类型
		String  paramKey = "query";
		String  paramType = "Map<String,Object>";
		String  methodName = "selectSupplyGoodsForecastPage";
		String  memo = "获取供货信息列表";//注释
		
		
		int[] apiTypeList = new int[]{
        		FreemarketConfigUtils.TYPE_DAO,	
        		FreemarketConfigUtils.TYPE_MAPPER,
        		FreemarketConfigUtils.TYPE_SERVICE,	
        		FreemarketConfigUtils.TYPE_INTERFACE,	
        };
		/**
		 * 多个入参数
		 */
		List<Map<String,String>> paramList = new ArrayList<>();
		Map<String,String> map2 = new HashMap<>();
		map2.put("paramKey", "page");
		map2.put("paramType", "Page<SupplyGoodsForecast>");
		paramList.add(map2);
		
		Map<String,String> map1 = new HashMap<>();
		map1.put("paramKey", paramKey);
		map1.put("paramType", paramType);
		paramList.add(map1);
		
		
		/**
		 * 如果 代码放在目录不是 modules ，需要修改
		 */
		String bashPath = basePath+"\\com\\stylefeng\\guns\\rest\\workmodules\\";
		String daoPath = "dao\\";
		String mappingPath = "dao\\mapping\\";
		String serviePath = "service\\";
		String servieImplPath = "service\\impl\\";
		
	    // 生成Service填充数据
        Map<String, Object> serviceData = new HashMap<>();
        serviceData.put("returnType", returnType);	        
        serviceData.put("paramList", paramList);	        
        serviceData.put("author", "yexh");
        serviceData.put("methodName", methodName);
        serviceData.put("memo", memo);
        serviceData.put("createdTime", DateUtil.format(new Date(), "yyyy年MM月dd日  HH时mm分"));
        
        
        
		try {
			for (int i = 0; i < apiTypeList.length; i++) {
				String path = "";
				// 生成Service文件
				String str = FileUtil.getGenerateString(apiTypeList[i],serviceData);
				switch (apiTypeList[i]) {
				case FreemarketConfigUtils.TYPE_DAO:
					path  = bashPath + daoPath + moduleName+"Mapper.java";
					writJavaFile(path, str);
					break;
				case FreemarketConfigUtils.TYPE_INTERFACE:
					path  = bashPath + serviePath + "I"+moduleName+"Service.java";
					writJavaFile(path, str);
					break;
				case FreemarketConfigUtils.TYPE_SERVICE:
					path  = bashPath + servieImplPath +moduleName+"ServiceImpl.java";
					writJavaFile(path, str);
					break;
				case FreemarketConfigUtils.TYPE_MAPPER:
					path  = bashPath + mappingPath +moduleName+"Mapper.xml";
					writXmlFile(path, str);
					break;

				default:
					break;
				}
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
		
		
	}

	/**
	 * 把代码 写入java文件
	 * @param fileName
	 * @param content
	 */
	public static void writJavaFile(String fileName, String content) {
		try {
			// 打开一个随机访问文件流，按读写方式
			RandomAccessFile randomFile = new RandomAccessFile(fileName, "rw");
			// 文件长度，字节数
			long fileLength = randomFile.length();
			// 将写文件指针移到文件尾。
			randomFile.seek(fileLength-3);
			randomFile.write((content+"\r\n").getBytes("utf-8"));
			randomFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 代码写入xml文件
	 * @param fileName
	 * @param content
	 */
	public static void writXmlFile(String fileName, String content) {
		try {
			// 打开一个随机访问文件流，按读写方式
			RandomAccessFile randomFile = new RandomAccessFile(fileName, "rw");
			// 文件长度，字节数
			long fileLength = randomFile.length();
			// 将写文件指针移到文件尾。
			randomFile.seek(fileLength-12);
			randomFile.write((content+"\r\n").getBytes("utf-8"));
			randomFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
