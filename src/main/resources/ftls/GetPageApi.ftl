package com.stylefeng.guns.rest.api.${packageName};

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.rest.api.MobileInterface;
import com.stylefeng.guns.rest.common.HomeConstants;
import com.stylefeng.guns.rest.common.JsonMsg;
import com.stylefeng.guns.rest.common.JsonUtil;
import com.stylefeng.guns.rest.common.StringUtils;
import com.stylefeng.guns.rest.workmodules.model.${className};
import com.stylefeng.guns.rest.workmodules.service.I${className}Service;

/**
 * 获取${moduleName!""}列表Page
 * @author ${author}
 * @since ${createdTime}
 */
@Component
public class Get${className}Page extends MobileInterface{
	private static Log log = LogFactory.getLog(Get${className}Page.class);
	@Autowired
	private I${className}Service ${entityName}Service;
	
	@Override
	public void doWith(HttpServletResponse response,HttpServletRequest request, Map<String, String> dataMap,String userId) {
		JsonMsg msg = new JsonMsg();
		try{
			
			Integer current = Integer.valueOf(dataMap.get("current"));
			Integer size = Integer.valueOf(dataMap.get("size"));
			String ${entityName}Name = dataMap.get("${entityName}Name");
			EntityWrapper<${className}> wrapper = new EntityWrapper<>();
			
			wrapper.orderBy("created_time", false);
			wrapper.eq("is_delete", HomeConstants.IS_NOT_DELETE);
			Page<${className}> page = new Page<>(current,size);
			Page<${className}> resultPage  = ${entityName}Service.selectPage(page, wrapper);
			msg.setData(resultPage);
			
			
			msg.setCode(HomeConstants.CODE_SUCC_REQ);
			msg.setMessage("操作成功！");
		}catch (RuntimeException e) {
            e.printStackTrace();
            msg.setCode(HomeConstants.CODE_ERR_PARA);
            msg.setMessage(e.getMessage());
       } catch (Exception e) {
			e.printStackTrace();
			msg.setCode(HomeConstants.CODE_ERR_SYS);
			msg.setMessage(e.getMessage());
		} finally {
			outJsonStringforapp(response, msg.toJson(), request);
		}
		
	}
	
}
