package com.stylefeng.guns.rest.api.${packageName};

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
import com.stylefeng.guns.rest.workmodules.model.${className};
import com.stylefeng.guns.rest.workmodules.service.I${className}Service;

/**
 * 添加${moduleName!""}
 * @author ${author}
 * @since ${createdTime}
 */
@Component
public class AddOrModify${className} extends MobileInterface{
	private static Log log = LogFactory.getLog(AddOrModify${className}.class);
	@Autowired
	private I${className}Service ${entityName}Service;
	
	@Override
	public void doWith(HttpServletResponse response,HttpServletRequest request, Map<String, String> dataMap,String userId) {
		JsonMsg msg = new JsonMsg();
		try{
			${className} ${entityName} = JsonUtil.parseObject(JsonUtil.toJSONString(dataMap), ${className}.class);
			boolean isPass = validateParam(dataMap,msg);
			if(!isPass){
				return;
			}
			
			boolean flag  = ${entityName}Service.insertOrUpdate(${entityName});
			
			msg.setCode(HomeConstants.CODE_SUCC_REQ);
			msg.setMessage("操作成功！");
		} catch (RuntimeException e) {
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
	
	/**
	 *  验证请求参数
	 * @param dataMap
	 * @param msg
	 * @return
	 */
	private boolean validateParam(Map<String, String> dataMap, JsonMsg msg) {
//		if(StringUtils.isEmpty(dataMap.get("param"))){
//			msg.setCode(HomeConstants.CODE_ERR_PARA);
//			msg.setMessage("不能为空！");
//			return false;
//		}
		return true;
	}
	
	
}
