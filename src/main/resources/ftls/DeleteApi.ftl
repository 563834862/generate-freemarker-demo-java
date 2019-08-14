package com.stylefeng.guns.rest.api.${packageName};

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stylefeng.guns.rest.api.MobileInterface;
import com.stylefeng.guns.rest.common.HomeConstants;
import com.stylefeng.guns.rest.common.JsonMsg;
import com.stylefeng.guns.rest.common.JsonUtil;
import com.stylefeng.guns.rest.common.StringUtils;
import com.stylefeng.guns.rest.workmodules.model.${className};
import com.stylefeng.guns.rest.workmodules.service.I${className}Service;

/**
 * 删除${moduleName!""}
 * @author ${author}
 * @since ${createdTime}
 */
@Component
public class Delete${className} extends MobileInterface{
	private static Log log = LogFactory.getLog(Delete${className}.class);
	@Autowired
	private I${className}Service ${entityName}Service;
	
	@Override
	public void doWith(HttpServletResponse response,HttpServletRequest request, Map<String, String> dataMap,String userId) {
		JsonMsg msg = new JsonMsg();
		try{
			
			String ${entityName}IdsStr  = dataMap.get("${entityName}Ids");
			
			if(StringUtils.areNotEmpty(${entityName}IdsStr)){
				
				List<Long> ${entityName}IdList = JsonUtil.parseArray(${entityName}IdsStr, Long.class);
				for (int i = 0; i < ${entityName}IdList.size(); i++) {
					${className} ${entityName} = new ${className}();
					${entityName}.set${className}Id(${entityName}IdList.get(i));
					${entityName}.setIsDelete(1);
					boolean flag   = ${entityName}Service.updateById(${entityName});
				}
				
			}
			
			msg.setCode(HomeConstants.CODE_SUCC_REQ);
			msg.setMessage("操作成功！");
		}catch (RuntimeException e) {
            e.printStackTrace();
            msg.setCode(HomeConstants.CODE_ERR_PARA);
            msg.setMessage(e.getMessage());
       }catch (Exception e) {
			e.printStackTrace();
			msg.setCode(HomeConstants.CODE_ERR_SYS);
			msg.setMessage(e.getMessage());
		} finally {
			outJsonStringforapp(response, msg.toJson(), request);
		}
		
	}
	
}
