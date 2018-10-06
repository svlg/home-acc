package home.acc.JSF;

import home.acc.AccEJB;
import home.acc.Entity.Operation;
import home.acc.XML.Operations;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

@ManagedBean
@RequestScoped
public class ImportController {

    @Inject
    private AccEJB accEJB;

    public String doImport(){

        //Groups
//        CategoryGroups categoryGroups = null;
//        try{
//            File file = new File("test/categoryGroups.xml");
//            JAXBContext jaxbContext = JAXBContext.newInstance(CategoryGroups.class);
//            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
//            categoryGroups = (CategoryGroups) jaxbUnmarshaller.unmarshal(file);
//
//        } catch (JAXBException e) {
//            e.printStackTrace();
//        }
//
//        if (categoryGroups == null) {
//            FacesContext.getCurrentInstance().addMessage(null,
//                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error during import category groups", ""));
//            return "";
//        }
//
//        List groupList = categoryGroups.getCategoryGroup();
//        groupList.forEach(group -> {accEJB.saveObject((CategoryGroup) group);
//        });
//
//        FacesContext.getCurrentInstance().addMessage(null,
//                new FacesMessage(FacesMessage.SEVERITY_INFO, "Category groups import sucessfull", ((Integer)groupList.size()).toString() + " elements imported"));

        //Categories
//        Categories categories = null;
//        try{
//            File file = new File("test/categories.xml");
//            JAXBContext jaxbContext = JAXBContext.newInstance(Categories.class);
//            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
//            categories = (Categories) jaxbUnmarshaller.unmarshal(file);
//
//        } catch (JAXBException e) {
//            e.printStackTrace();
//        }
//
//        if (categories == null) {
//            FacesContext.getCurrentInstance().addMessage(null,
//                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error during import categories", ""));
//            return "";
//        }
//
//        List catList = categories.getListcategories();
//        catList.forEach(cat -> {accEJB.saveCategoryNative((Category) cat);
//        });
//
//        FacesContext.getCurrentInstance().addMessage(null,
//                new FacesMessage(FacesMessage.SEVERITY_INFO, "Categories import sucessfull", ((Integer)catList.size()).toString() + " elements imported"));

        Operations operations = null;
        try{
            File file = new File("test/operations.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Operations.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            operations = (Operations) jaxbUnmarshaller.unmarshal(file);

        } catch (JAXBException e) {
            e.printStackTrace();
        }

        if (operations == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error during import operations", ""));
            return "";
        }

        List opList = operations.getOperations();
        opList.forEach(op -> {accEJB.saveObject((Operation) op);
        });

        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Operations import sucessfull", ((Integer)opList.size()).toString() + " elements imported"));

        return "";
    }
}
