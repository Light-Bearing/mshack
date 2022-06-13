import React, {useEffect, useState} from 'react';
import {Box, Button, List, ListItem, Typography} from "@mui/material";
import {IParameterValueObj, IResModel, IResult} from "../../types/model.types";
import service from "../../sevices/Auth";
import {Argument} from "../Argument/Argument";
import {VictoryChart, VictoryLine, VictoryTheme} from "victory";

export const Assessment = () => {
    const [modelList, setModelList] = useState<IResModel[]>([]);
    const [paramsValue, setParamsValue] = useState<IParameterValueObj[]>([]);
    const [result, setResult] = useState<IResult | null>(null);
    const [profit,setProfit] = useState(0);

    useEffect(() => {
        service.getModel().then(res => {
            setModelList(res.data);
            const paramsValue = res.data.map((model: IResModel) => ({
                equationId: model.id,
                param: model.paramEquationList.map(param => ({
                    key: param.name,
                    value: '',
                }))
            }));
            setParamsValue(paramsValue);
        }, e => console.log(e))
    }, [])

    // const getArguments = (equation: string): string[] => {
    //     const endOfArgs: number = equation.indexOf(')', 1);
    //     const argsStr: string = equation.substring(1, endOfArgs);
    //     const argsArr: string[] = argsStr.trim().split(',');
    //
    //     return argsArr;
    // }

    const handleChangeParamValue = (value: string, modelID: number, key: string, index: number) => {

        const currentParamIndex: number = paramsValue.findIndex(param => param.equationId === modelID);
        if (currentParamIndex !== -1) {
            const param = paramsValue[currentParamIndex].param;
            param[index] = {...param[index], value};
        }

    }

    const handleStartMachine = () => {
        service.setParametres(paramsValue).then((res) => {
            const data = res.data;
            const result={
                calculateResult: data.calculateResult.map((item: string) => JSON.parse(item)),
                    recommendation: JSON.parse(data.recommendation)
            }
            setResult(result);
            const p=result.calculateResult.reduce((acc:number,el:number[])=>acc+el.reduce((a:number,it:number)=>a+it,0),0);
            setProfit(p);
        }, e => console.log(e));
    }

    return (
        <Box sx={{
            display: 'flex',
            flexWrap: 'wrap',
        }}>
            <Box sx={{
                width: '50%',
            }}>
                <List>
                    {modelList.length > 0 && modelList.map((model, index) => (
                        <ListItem sx={{
                            display: 'flex',
                            alignItems: 'center',
                        }}>
                            <Typography variant='h4' color='#fff'>{index + 1}</Typography>
                            <Box sx={{
                                width: '100%',
                                display: 'flex',
                                flexDirection: 'column',
                            }}>
                                <Typography variant='h6' color='#fff' sx={{
                                    paddingBottom: '5px',
                                }}>{model.name}</Typography>
                                <List sx={{
                                    display: 'flex',
                                    alignItems: 'flexStart'
                                }}>
                                    {model.paramEquationList.map((param, index) => (
                                        <ListItem>
                                            <Argument key={param.id} argument={param.name} title={param.title}
                                                      modelID={model.id} onChange={handleChangeParamValue}
                                                      index={index}/>
                                        </ListItem>
                                    ))}
                                </List>

                            </Box>
                        </ListItem>
                    ))}
                </List>
                <Button variant='contained' fullWidth onClick={handleStartMachine}>Запустить машину времени</Button>
            </Box>
            <Box sx={{
                width: '50%',
            }}>
                {result && result.calculateResult.length > 0 && (
                    <>
                        <Typography variant='h5' color='#fff'>{profit} р.</Typography>
                        <VictoryChart
                            theme={VictoryTheme.material}
                            padding={{left: 50, bottom: 20}}
                        >
                            {result.calculateResult.map((item, index) => (
                                <VictoryLine
                                    style={{
                                        data: { stroke: `#c43a3${index}` },
                                        parent: { border: "1px solid #ccc"}
                                    }}
                                    data={
                                        item.map((value, index) => ({ x: index + 1, y: value }))
                                    }
                                    domain={{x: [0, 12], y: [0, 1000000]}}
                                    height={600}
                                />
                            ))}
                        </VictoryChart>
                    </>
                )}

            </Box>
            <Box sx={{
                width: "80%",
            }}>
                {result && result.recommendation.length > 0 && (
                    <>
                        <Typography variant='h5' color='#fff'>Рекомендации</Typography>
                        {result.recommendation.map((recomend, index) => (
                            <Typography variant='subtitle1' color='#fff'>{`В ${index + 1} канал рекомендуется вложить ${(recomend*100).toFixed(2)} процентов бюджета`}</Typography>
                        ))}
                    </>
                )}
            </Box>

        </Box>
    );
};