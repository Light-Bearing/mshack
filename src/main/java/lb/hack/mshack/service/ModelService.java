package lb.hack.mshack.service;

import lb.hack.mshack.message.request.Equation;
import lb.hack.mshack.message.request.Parameter;

import java.util.List;

public interface ModelService {

    public List<Equation> addModel(List<Equation> equationList);

    public List<Parameter> setParameter(List<Parameter> parameterList);

    }
