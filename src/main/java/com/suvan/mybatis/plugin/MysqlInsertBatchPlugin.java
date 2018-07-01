package com.suvan.mybatis.plugin;

import org.mybatis.generator.api.*;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.*;

import java.util.List;

/**
 * @function : 数据库批量导入插件
 */
public class MysqlInsertBatchPlugin extends PluginAdapter {

	@Override
	public boolean clientInsertMethodGenerated(Method method, Interface interfaze,
            IntrospectedTable introspectedTable) {
		interfaze.addMethod(generateDeleteLogicByIds(method, introspectedTable));
        return super.clientInsertMethodGenerated(method, interfaze, introspectedTable);
    }
	
	@Override
	public boolean sqlMapDocumentGenerated(Document document,
			IntrospectedTable introspectedTable) {
		XmlElement parentElement = document.getRootElement();
		List<Element> sonElements = parentElement.getElements();
		
		for(int i = 0; i < sonElements.size(); i++){
			XmlElement element = (XmlElement) sonElements.get(i);
			List<Attribute> attributes = element.getAttributes();
			
			if(attributes.get(0).getValue().equals("insert")){//找到id是insert的
				XmlElement insertBatch = new XmlElement("insert");
				insertBatch.addAttribute(new Attribute("id", "insertBatch"));
				insertBatch.addAttribute(new Attribute("parameterType", "java.util.List"));
				
				String insertContent = element.getFormattedContent(0);
				insertContent = insertContent.replaceFirst("<insert .*>", "");
				insertContent = insertContent.replace("</insert>", "");
				
				String[] content = insertContent.split(" values ");
				
				insertBatch.addElement(new TextElement(content[0].trim() + " values"));
				
				XmlElement foreach = new XmlElement("foreach");
				foreach.addAttribute(new Attribute("collection", "list"));
				foreach.addAttribute(new Attribute("item", "item"));
				foreach.addAttribute(new Attribute("index", "index"));
				foreach.addAttribute(new Attribute("separator", ","));
				foreach.addElement(new TextElement(content[1].replaceAll("\\#\\{", "#{item\\.").trim()));
				insertBatch.addElement(foreach);
				
				parentElement.addElement(insertBatch);
			}
		}
		return super.sqlMapDocumentGenerated(document, introspectedTable);
	}

	private Method generateDeleteLogicByIds(Method method, IntrospectedTable introspectedTable) {
        Method m = new Method("insertBatch");
        
        m.setVisibility(method.getVisibility());
        
        m.setReturnType(FullyQualifiedJavaType.getIntInstance());
        m.addParameter(new Parameter(new FullyQualifiedJavaType("java.util.List<" + method.getParameters().get(0).getType().getShortName() + ">"), "list"));
        context.getCommentGenerator().addGeneralMethodComment(m, introspectedTable);
        return m;
    }

	public boolean validate(List<String> warnings) {
		return true;
	}
}
