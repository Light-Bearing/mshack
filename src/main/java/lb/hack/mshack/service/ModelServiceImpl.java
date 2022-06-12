package lb.hack.mshack.service;

import lb.hack.mshack.entity.EquationEntity;
import lb.hack.mshack.entity.ParamEquation;
import lb.hack.mshack.message.request.Equation;
import lb.hack.mshack.message.request.Parameter;
import lb.hack.mshack.repository.EquationRepository;
import lb.hack.mshack.utils.JavaScriptEngine;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ModelServiceImpl implements ModelService {

    private EquationRepository equationRepository;
    private QuestionGA questionGA;
    private JavaScriptEngine javaScriptEngine;
    @Override
    public List<EquationEntity> addModel(List<Equation> equationList) {
        equationRepository.deleteAll();
        List<EquationEntity> equationEntityList = equationList.stream()
                .map(el->EquationEntity.builder()
                        .name(el.getName())
                        .equation(el.getEquation())
                        .paramEquationList(el.getParamList().stream()
                                .map(it-> ParamEquation.builder()
                                        .name(it.getName())
                                        .title(it.getTitle())
                                        .type(it.getType())
                                        .build())
                                .collect(Collectors.toList()))
                        .build()).collect(Collectors.toList());
        return equationRepository.saveAll(equationEntityList);
    }

    @Override
    public List<String> setParameter(List<Parameter> parameterList){
        List<String> collect = parameterList.stream()
                .map(el -> javaScriptEngine.eval(equationRepository.findById(el.getEquationId())
                                .orElseThrow().getEquation(),
                        el.getParam()))
                .collect(Collectors.toList());
        System.out.println(collect);
        //List<Double> parameters = collect.stream().map(el->).collect(Collectors.toList());
        List<Double> parameters = new ArrayList<>();
        parameters.add(Math.random());
        parameters.add(Math.random());
        parameters.add(Math.random());
        parameters.add(Math.random());
        parameters.add(Math.random());
        List<Double> geneticResponse = questionGA.getGeneticResponse(parameters);
        System.out.println(geneticResponse);
        return null;
    }

    @Override
    public List<EquationEntity> getModel() {
        return equationRepository.findAll();
    }

    @Override
    public EquationEntity deleteModel(Long id) {
        EquationEntity equationEntity = equationRepository.getById(id);
        equationRepository.delete(equationEntity);
        return equationEntity;
    }

}
