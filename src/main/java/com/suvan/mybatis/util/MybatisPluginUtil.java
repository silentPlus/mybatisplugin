package com.suvan.mybatis.util;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.Element;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.config.Context;

import java.util.List;

/**
 * Mybatis自动生成插件的工具类
 */
public class MybatisPluginUtil {
	
	
	/**
	 * 根据sqlid获取对应的元素
	 * @param document
	 * @param referenceId
	 * @return
	 */
	public static XmlElement getReferenceElement(Document document, String referenceId){
		XmlElement parentElement = document.getRootElement();
		List<Element> sonElements = parentElement.getElements();
		for(int i = 0; i < sonElements.size(); i++){
			XmlElement element = (XmlElement) sonElements.get(i);
			List<Attribute> attributes = element.getAttributes();
			
			if(attributes.get(0).getValue().equals(referenceId)){
				return element;
			}
		}
		return null;
	}
	
	/**
	 * 根据传递的参数生成接口里面的方法
	 * @param introspectedTable
	 * @param baseMethod
	 * @param methodName
	 * @param methodParameters
	 * @param methodParameterNames
	 * @param annotations
	 * @return
	 */
	public static Method generateInterfaceMethod(Context context, IntrospectedTable introspectedTable, Method baseMethod, 
			String methodName, String[] methodParameters, String[] methodParameterNames, String[] annotations){
		Method method = new Method(methodName);
        method.setVisibility(baseMethod.getVisibility());
        method.setReturnType(FullyQualifiedJavaType.getIntInstance());
        
        if(!EmptyUtil.isEmpty(methodParameters) && !EmptyUtil.isEmpty(methodParameterNames) && 
        		methodParameterNames.length == methodParameters.length){
        	for(int i = 0; i < methodParameters.length; i++){
        		if(EmptyUtil.isEmpty(annotations) || annotations.length <= i || annotations[i] == null){
        			method.addParameter(new Parameter(new FullyQualifiedJavaType(methodParameters[i]), 
            				methodParameterNames[i]));
        			continue;
        		}
        		method.addParameter(new Parameter(new FullyQualifiedJavaType(methodParameters[i]), 
        				methodParameterNames[i], annotations[i]));
        	}
        }
        context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);
        return method;
	}
	
	/**
	 * 在Example下面添加属性和方法
	 * @param context
	 * @param topLevelClass
	 * @param introspectedTable
	 * @param name
	 */
	public static void addFieldAndMethod(Context context, TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable, String name, String type) throws Exception{
		CommentGenerator commentGenerator = context.getCommentGenerator();
		
		Field field = new Field();
		field.setVisibility(JavaVisibility.PROTECTED);
		field.setType(getType(type));
		field.setName(name);
		commentGenerator.addFieldComment(field, introspectedTable);
		topLevelClass.addField(field);
		
		char c = name.charAt(0);
		String camel = Character.toUpperCase(c) + name.substring(1);
		
		Method method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setName("set" + camel);
		method.addParameter(new Parameter(getType(type), name));
		method.addBodyLine("this." + name + "=" + name + ";");
		commentGenerator.addGeneralMethodComment(method, introspectedTable);
		topLevelClass.addMethod(method);
		
		method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setReturnType(getType(type));
		method.setName("get" + camel);
		method.addBodyLine("return " + name + ";");
		commentGenerator.addGeneralMethodComment(method, introspectedTable);
		topLevelClass.addMethod(method);
	}
	
	/**
	 * 根据指定type来获取Example中top class的属性类型
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public static FullyQualifiedJavaType getType(String type) throws Exception{
		if(EmptyUtil.isHaveEmpty(type)){
			return PrimitiveTypeWrapper.getStringInstance();
		}
		java.lang.reflect.Method method = PrimitiveTypeWrapper.class.getMethod("get" + type + "Instance");
		return (FullyQualifiedJavaType) method.invoke(null);
	}
}
