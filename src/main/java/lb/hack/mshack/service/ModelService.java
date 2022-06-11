package lb.hack.mshack.service;

import lb.hack.mshack.entity.EquationEntity;
import lb.hack.mshack.message.request.Equation;
import lb.hack.mshack.message.request.Parameter;

import java.util.List;

public interface ModelService {

    List<EquationEntity> addModel(List<Equation> equationList);

    List<EquationEntity> getModel();

    EquationEntity deleteModel(Long id);

    List<String> setParameter(List<Parameter> parameterList);
}
