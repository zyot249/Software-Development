package com.group4.itss.controller;

import com.group4.itss.common.AppState;
import com.group4.itss.entity.model.Merchandise;
import com.group4.itss.repository.MerchandiseRepository;
import com.group4.itss.ui.Navigator;
import com.group4.itss.ui.component.PopUp;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("merchandise-controller")
public class MerchandiseController {
    @Autowired
    private MerchandiseRepository merchandiseRepository;
    private AppState appState;
    private Navigator navigator;

    public MerchandiseController(Navigator navigator,AppState appState,MerchandiseRepository merchandiseRepository){
        this.navigator = navigator;
        this.appState = appState;
        this.merchandiseRepository = merchandiseRepository;
    }

    public Merchandise findMerchandiseByOrderCode(String code) {
        try {
            return merchandiseRepository.findMerchandiseByCode(code);
        } catch (Exception e){
            System.out.println("Error while querying data");
        }
        return null;
    }

    public List<Merchandise> getAllMerchandise() {
        try {
            return merchandiseRepository.findAll();
        }catch (Exception e){
            System.out.println("Error while querying data");
        }
        return new ArrayList<>();
    }

    public void addNewMerchandise(Merchandise merchandise){
        try {
            merchandiseRepository.save(merchandise);
            PopUp.render(
                    PopUp.PopupTypes.SUCCESS,
                    "Create Merchandise Success",
                    "Create Merchandise"
            );
        } catch (RuntimeException exception) {
            exception.printStackTrace();
        }
    }

    public void updateDataMerchandise(List<Merchandise> merchandiseList){
        try {
            merchandiseRepository.saveAll(merchandiseList);
            PopUp.render(PopUp.PopupTypes.SUCCESS, "Update Data Merchandise successfully!","Notify");
        } catch (Exception e1) {
            System.out.println("Error while saving data");
            return;
        }
    }

    public boolean checkValidation(String name, String unit, String code){
        if(StringUtils.isBlank(name)){
            PopUp.render(PopUp.PopupTypes.ERROR, "You can't leave name field blank","Error!!");
            return false;
        }
        if(StringUtils.isBlank(unit)){
            PopUp.render(PopUp.PopupTypes.ERROR, "You can't leave unit field blank","Error!!");
            return false;
        }
        if(StringUtils.isBlank(code)){
            PopUp.render(PopUp.PopupTypes.ERROR,"You can't leave code field blank","Error");
            return false;
        }
        return true;
    }
}
