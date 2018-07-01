package com.suvan.mybatis.plugin;

import com.suvan.mybatis.util.MybatisPluginUtil;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.util.List;

/**
 * Mysql的分组插件
 */
public class MysqlGroupByPlugin extends PluginAdapter {
	
	@Override
	public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(
			XmlElement element, IntrospectedTable introspectedTable) {
		XmlElement isNotNullElement = new XmlElement("if");
		isNotNullElement.addAttribute(new Attribute(
				"test", "groupBy != null"));
		isNotNullElement.addElement(new TextElement(
				"group by #{groupBy} "));
		element.addElement(isNotNullElement);
		return super.sqlMapUpdateByExampleWithoutBLOBsElementGenerated(element, introspectedTable);
	}
	
	@Override
	public boolean modelExampleClassGenerated(TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable) {
		try {
			MybatisPluginUtil.addFieldAndMethod(context, topLevelClass, introspectedTable, "groupBy", "String");
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		return super.modelExampleClassGenerated(topLevelClass, introspectedTable);
	}

	public boolean validate(List<String> warnings) {
		return true;
	}

}
