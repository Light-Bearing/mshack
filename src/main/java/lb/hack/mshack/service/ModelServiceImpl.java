package lb.hack.mshack.service;

import lb.hack.mshack.dto.Result;
import lb.hack.mshack.entity.EquationEntity;
import lb.hack.mshack.entity.ParamEquation;
import lb.hack.mshack.message.request.Equation;
import lb.hack.mshack.message.request.Parameter;
import lb.hack.mshack.repository.EquationRepository;
import lb.hack.mshack.utils.JavaScriptEngine;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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
                .map(el -> EquationEntity.builder()
                        .name(el.getName())
                        .equation(el.getEquation())
                        .paramEquationList(el.getParamList().stream()
                                .map(it -> ParamEquation.builder()
                                        .name(it.getName())
                                        .title(it.getTitle())
                                        .type(it.getType())
                                        .build())
                                .collect(Collectors.toList()))
                        .build()).collect(Collectors.toList());
        return equationRepository.saveAll(equationEntityList);
    }

    @Override
    public Result setParameter(List<Parameter> parameterList) {
        List<String> collect = parameterList.stream()
                .map(el -> javaScriptEngine.eval(equationRepository.findById(el.getEquationId())
                                .orElseThrow().getEquation(),
                        el.getParam())).collect(Collectors.toList());
        System.out.println(collect);

        List<Double> request = collect.stream()
                .map(str -> Arrays.stream(str.substring(str.indexOf('[') + 1, str.length() - 1).split(","))
                        .map(String::trim)
                        .map(Double::valueOf)
                        .reduce(Double::sum).orElse(0.))
                .collect(Collectors.toList());
        String geneticResponse = questionGA.getGeneticResponse(request);
        System.out.println(geneticResponse);

        return new Result(collect.stream().map(el->el.substring(el.indexOf(")")+1)).collect(Collectors.toList()), geneticResponse);
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
