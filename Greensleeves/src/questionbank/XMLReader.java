package questionbank;

import org.dom4j.*;

public class XMLReader {
	
	//Converting the org.w3c.dom.Document to dom4j document
	/**
	 * 
	 * @param doc w3c document
	 * @return dom4j document
	 * @throws Exception
	 */
	public static Document docConverter(org.w3c.dom.Document doc)throws Exception {     
        if(doc == null){     
            return  (null);     
        }     
        org.dom4j.io.DOMReader xmlReader = new org.dom4j.io.DOMReader();     
        return   (xmlReader.read(doc));     
    }
	
	
	public static String getRelation(String reln, Node node){
		String xpath = "./" + reln + "/text";
		String value = "";
		
		Element e = (Element)node.selectSingleNode(xpath);
		
		//There may be no object in a sentence
		if(reln.equals("object")){
			if(e != null) value = e.getText();
		}else{
			value = e.getText();
		}
		
		return value;
	}
	
	public static String getVerbFeature(String feature, Node node){
		String xpath = "./action/verb/" + feature;
		Element e = (Element)node.selectSingleNode(xpath);
		
		if(e != null){
			return e.getText();
		}else{
			return "";
		}
	}
}
