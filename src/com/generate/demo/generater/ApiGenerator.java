package com.generate.demo.generater;

import java.io.IOException;
import java.util.Date;

import com.generate.demo.common.ApiTask;
import com.generate.demo.common.DateUtil;

import freemarker.template.TemplateException;


/**
 * 
 * @author yexh
 * @since 2019-04-24
 *
 */
public class ApiGenerator {
	public static void main(String[] args) {
		String path = "D:\\git\\generate-freeamarker-demo-java\\src\\com\\generate\\demo\\api\\";
		try {
			String[] tablelist = new String[]{
//					"t_port",
//					"t_goods",
					"t_supply_goods_detail",
					"t_supply_goods_forecast",
			};
			String[] tableNamelist = new String[]{
//					"港口",
//					"货物",
					"供货明细",
					"供货预报",
			};
			
			for (int i = 0; i < tablelist.length; i++) {
				ApiTask apiTask = new ApiTask();
				apiTask.setPath(path);
				apiTask.setTableName(tablelist[i]);
				apiTask.setPreFix("t_");
				apiTask.setAuthor("yexh");
				apiTask.setCreatedTime(DateUtil.format(new Date(), "yyyy年MM月dd日  HH时mm分"));
				apiTask.setModuleName(tableNamelist[i]);
				apiTask.run();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
	}
}
