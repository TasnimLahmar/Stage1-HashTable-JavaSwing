package tn.usousse.eniso.ia1.stage.presentation.controller;

import tn.usousse.eniso.ia1.stage.model.Table;
import tn.usousse.eniso.ia1.stage.service.Service;

public class HashtableController {

    private Service service=new Service(new Table(3));

    public void setService(Service service) {
        this.service = service;
    }

    public Service getService() {
        return service;
    }
    public Table getModel() {
        return getService().getTable();
    }


}
