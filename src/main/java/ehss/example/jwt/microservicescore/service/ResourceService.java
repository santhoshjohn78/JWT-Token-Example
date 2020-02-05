package ehss.example.jwt.microservicescore.service;

import ehss.example.jwt.microservicescore.data.GenericKPData;
import ehss.example.jwt.microservicescore.data.InvalidRequestException;
import ehss.example.jwt.microservicescore.data.ResourceNotFoundException;
import ehss.example.jwt.microservicescore.data.ServiceException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class ResourceService {

    public List<GenericKPData> getResources(){
        List<GenericKPData> glist = new ArrayList<>();
        GenericKPData gp= new GenericKPData();
        gp.setId("EHSS"+Calendar.getInstance().getTimeInMillis());
        gp.setSystemTimeStamp(Calendar.getInstance().getTimeInMillis());
        gp.setText("Sam payload");
        glist.add(gp);
        return glist;
    }

    public GenericKPData getResources(String id) throws InvalidRequestException, ResourceNotFoundException, ServiceException {
        if ("0".equals(id))
            throw new ResourceNotFoundException("Not found..");
        if ("-1".equals(id))
            throw new InvalidRequestException("Check the id...");

        GenericKPData gp= new GenericKPData();
        gp.setId("EHSS"+Calendar.getInstance().getTimeInMillis());
        gp.setSystemTimeStamp(Calendar.getInstance().getTimeInMillis());
        gp.setText("Sam payload");
        return gp;
    }

    public GenericKPData createResource(GenericKPData genericKPData) throws InvalidRequestException, ServiceException {
        genericKPData = Optional.ofNullable(genericKPData).orElseThrow(()->new InvalidRequestException("Need GenericKPData payload..."));

        genericKPData.setId(Optional.ofNullable(genericKPData.getId()).orElse("EHSS"+Calendar.getInstance().getTimeInMillis()));
        genericKPData.setText(Optional.ofNullable(genericKPData.getText()).orElseThrow(()->new InvalidRequestException("Need text field...")));
        genericKPData.setSystemTimeStamp(Calendar.getInstance().getTimeInMillis());
        return  genericKPData;
    }
}
