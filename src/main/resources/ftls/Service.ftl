	/**
	 *  ${memo!""}
	 * @author ${author}
	 * @since ${createdTime}
	 */
	@Override
	public ${returnType} ${methodName}(<#list paramList as param>${param.paramType} ${param.paramKey}<#sep>,</#list>){
		return baseMapper.${methodName}(<#list paramList as param>${param.paramKey}<#sep>,</#list>);
	}
	
}