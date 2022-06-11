package lb.hack.mshack.service;

import lb.hack.mshack.message.request.Equation;
import lb.hack.mshack.message.request.Parameter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelServiceImpl implements ModelService {

    @Override
    public List<Equation> addModel(List<Equation> equationList) {
        return equationList;
    }

    @Override
    public List<Parameter> setParameter(List<Parameter> parameterList){
        return parameterList;
    };

}
