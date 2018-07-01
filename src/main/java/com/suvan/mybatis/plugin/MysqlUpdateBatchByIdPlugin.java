package com.suvan.mybatis.plugin;

import com.suvan.mybatis.util.MybatisPluginUtil;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.util.List;

/**
 * Mysql 根据id批量更新
 * jdbc的url需要添加：allowMultiQueries=true参数
 */
public class MysqlUpdateBatchByIdPlugin extends PluginAdapter{
	
	public static final String updateBatchByPrimaryKey = "updateBatchByPrimaryKey";
	public static final String updateBatchByPrimaryKeySelective = "updateBatchByPrimaryKeySelective";
	
	@Override
	public boolean clientUpdateByPrimaryKeyWithoutBLOBsMethodGenerated(Method method,
            Interface interfaze, IntrospectedTable introspectedTable) {
		interfaze.addMethod(MybatisPluginUtil.generateInterfaceMethod(context, introspectedTable, method, updateBatchByPrimaryKey, 
				new String[]{"java.util.List<" + method.getParameters().get(0).getType().getShortName() + ">"}, 
				new String[]{"list"}, null));
		return super.clientUpdateByPrimaryKeyWithoutBLOBsMethodGenerated(method, interfaze, introspectedTable);
    }
	
	@Override
	public boolean clientUpdateByPrimaryKeySelectiveMethodGenerated(Method method,
            Interface interfaze, IntrospectedTable introspectedTable) {
		interfaze.addMethod(MybatisPluginUtil.generateInterfaceMethod(context, introspectedTable, method, 
				updateBatchByPrimaryKeySelective, 
				new String[]{"java.util.List<" + method.getParameters().get(0).getType().getShortName() + ">"}, 
				new String[]{"list"}, null));
		return super.clientUpdateByPrimaryKeyWithoutBLOBsMethodGenerated(method, interfaze, introspectedTable);
    }
	
	@Override
	public boolean sqlMapDocumentGenerated(Document document,
            IntrospectedTable introspectedTable) {
		String referenceId = "updateByPrimaryKey";
		XmlElement referenceElement = MybatisPluginUtil.getReferenceElement(document, referenceId);
		if(referenceElement == null){
			return super.sqlMapDocumentGenerated(document, introspectedTable);
		}
		String referenceContent = referenceElement.getFormattedContent(0);
		document.getRootElement().addElement(createUpdateBatchByPrimaryKey(referenceContent, updateBatchByPrimaryKey));
		
		referenceId = "updateByPrimaryKeySelective";
		referenceElement = MybatisPluginUtil.getReferenceElement(document, referenceId);
		referenceContent = referenceElement.getFormattedContent(0);
		document.getRootElement().addElement(createUpdateBatchByPrimaryKey(referenceContent, updateBatchByPrimaryKeySelective));
		
        return super.sqlMapDocumentGenerated(document, introspectedTable);
    }
	
	private XmlElement createUpdateBatchByPrimaryKey(String referenceContent, String newSqlId){
		XmlElement element = new XmlElement("update");
		element.addAttribute(new Attribute("id", newSqlId));
		element.addAttribute(new Attribute("parameterType", "java.util.List"));
		
		referenceContent = referenceContent.replaceAll("<update .*>", "");
		referenceContent = referenceContent.replace("</update>", "");
		referenceContent = referenceContent.replace("#{", "#{item.");
		referenceContent = referenceContent.replace("test=\"", "test=\"item.");
		
		String elementContent = "<foreach collection=\"list\" item=\"item\" index=\"index\" separator=\";\">" + 
				referenceContent + "\t</foreach>";
		element.addElement(new TextElement(elementContent));
		return element;
	}
	
	public boolean validate(List<String> warnings) {
		return true;
	}
}
