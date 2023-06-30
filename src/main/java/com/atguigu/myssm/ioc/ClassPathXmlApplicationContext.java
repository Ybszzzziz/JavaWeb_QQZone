package com.atguigu.myssm.ioc;

import com.atguigu.myssm.util.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Yan
 * @create 2023-04-13 11:30
 **/
public class ClassPathXmlApplicationContext implements BeanFactory{

    private Map<String,Object> beanMap = new HashMap<>();
    private String path = "applicationContext.xml";
    public ClassPathXmlApplicationContext(){
        this("applicationContext.xml");
    }
    public ClassPathXmlApplicationContext(String path){
        if (StringUtils.isEmpty(path)){
            throw new RuntimeException("ioc错误");
        }
        try {

            InputStream stream = getClass().getClassLoader().getResourceAsStream(path);
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(stream);
            NodeList beanNodeList = document.getElementsByTagName("bean");
            for (int i = 0; i < beanNodeList.getLength(); i++) {

                Node beanNode = beanNodeList.item(i);
                if (beanNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element beanElement = (Element) beanNode;
                    String beanId = beanElement.getAttribute("id");
                    String beanClassName = beanElement.getAttribute("class");
                    Class beanClass = Class.forName(beanClassName);
                    Object beanObj = beanClass.newInstance();
//                    Method setServletContextMethod = controllerBeanClass.getDeclaredMethod("setServletContext", ServletContext.class);
//                    setServletContextMethod.invoke(beanObj,this.getServletContext());
//                    Field servletContext = controllerBeanClass.getDeclaredField("servletContext");
//                    servletContext.setAccessible(true);
//                    servletContext.set(beanObj,this.getServletContext());
                    beanMap.put(beanId, beanObj);
                    //到目前为止，此处需要注意的是，bean和bean之间的依赖关系还没有设置
                }
            }
            for (int i = 0; i < beanNodeList.getLength(); i++) {
                Node beanNode = beanNodeList.item(i);
                if (beanNode.getNodeType() == Node.ELEMENT_NODE){
                    Element beanElement = (Element) beanNode;
                    String beanId = beanElement.getAttribute("id");
                    NodeList beanChildNodeList = beanElement.getChildNodes();
                    for (int j = 0; j < beanChildNodeList.getLength(); j++) {
                        Node beanChildNode = beanChildNodeList.item(j);
                        if (beanChildNode.getNodeType() == Node.ELEMENT_NODE &&
                                "property".equals(beanChildNode.getNodeName())){
                            Element propertyElement = (Element) beanChildNode;
                            String propertyName = propertyElement.getAttribute("name");
                            String propertyRef = propertyElement.getAttribute("ref");
                            //1) 找到propertyRef对应的实例
                            Object refObj = beanMap.get(propertyRef);
                            //2) 将refObj设置到当前bean对应的实例的property属性上去
                            Object beanObj = beanMap.get(beanId);
                            Class beanClazz = beanObj.getClass();
                            Field propertyField = beanClazz.getDeclaredField(propertyName);
                            propertyField.setAccessible(true);
                            propertyField.set(beanObj,refObj);
                        }
                    }
                }
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object getBean(String id) {
        return beanMap.get(id);
    }
}
