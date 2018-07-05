package com.suvan.mybatis.plugin;

import com.suvan.mybatis.util.MybatisPluginUtil;
import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.util.List;

/**
 * @function : Mysql的分页插件
 */
public class MysqlPaginationPlugin extends PluginAdapter {
	@Override
	public boolean modelExampleClassGenerated(TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable) {
		try {
			MybatisPluginUtil.addFieldAndMethod(context, topLevelClass, introspectedTable, "limitStart", "Long");
			MybatisPluginUtil.addFieldAndMethod(context, topLevelClass, introspectedTable, "count", "Long");
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
		return super.modelExampleClassGenerated(topLevelClass,
				introspectedTable);
	}

	@Override
	public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(
			XmlElement element, IntrospectedTable introspectedTable) {
		XmlElement isNotNullElement = new XmlElement("if");
		isNotNullElement.addAttribute(new Attribute(
				"test", "limitStart != null and limitStart >= 0 and count != null and count >= 0"));
		isNotNullElement.addElement(new TextElement(
				"limit #{limitStart} , #{count}"));
		element.addElement(isNotNullElement);
		return super.sqlMapUpdateByExampleWithoutBLOBsElementGenerated(element,
				introspectedTable);
	}

	public boolean validate(List<String> warnings) {
		return true;
	}

}