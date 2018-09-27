package home.acc.JSF;

import home.acc.XML.Categories;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

@ManagedBean
@RequestScoped
public class ImportController {

    public String doImport(){

        Categories categories = null;
        try{
            File file = new File("test/test_category.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Categories.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            categories = (Categories) jaxbUnmarshaller.unmarshal(file);

        } catch (JAXBException e) {
            e.printStackTrace();
        }

        if (categories == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error during import xml", ""));
            return "";
        }

        List catList = categories.getListcategories();
        catList.forEach(cat -> {System.out.println(cat);
        });

        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucessfull", ((Integer)catList.size()).toString() + " elements imported"));

        return "index.xhtml";
    }
}
