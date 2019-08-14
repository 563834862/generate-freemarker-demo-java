	/**
	 *  ${memo!""}
	 * @author ${author}
	 * @since ${createdTime}
	 */
	public ${returnType} ${methodName}(<#list paramList as param>@Param("${param.paramKey}")${param.paramType} ${param.paramKey}<#sep>,</#list>);
	
}