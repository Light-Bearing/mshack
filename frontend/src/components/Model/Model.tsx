import {ModelProps} from "./Model.props";
import {Box, Button, List, ListItem, TextField, Typography} from "@mui/material";
import React, {ChangeEvent, useEffect, useState} from "react";
import {Parameter} from "../Parametr/Parameter";
import {IModel} from "../../types/model.types";

export const Model = ({model, setModel}: ModelProps) => {
    const [name, setName] = useState(model.name);
    const [equation, setEquation] = useState(model.equation);
    const [paramList, setParamList] = useState(model.paramList);

    console.log(paramList);

    useEffect(() => {
        if (model.equation) {
            parse(model.equation)
        }
    }, [])

    const parse = (str: string) => {
        if (str.match("[(*)]")) {
            const param = str.substring(str.indexOf('(') + 1, str.indexOf(')'))
                .split(',')
                .map(el => el.trim())
                .map(el => ({
                    id: Math.random(),
                    name: el,
                    title: '',
                    type: ''
                }));
            setParamList(param);
        }
    }

    const handleChange = (e: ChangeEvent<HTMLInputElement>) => {
        const key = e.target.name as 'name' | 'equation';
        switch (key) {
            case "equation": {
                setEquation(e.target.value);
                parse((e.target.value));
            }
                break;
            case "name":
                setName(e.target.value);
                break;
            default:
                const i=paramList.findIndex(el=>el.name===e.target.name);
                setParamList((prev) => {
                   const a = [...prev]
                   a[i]={ ...a[i], title: e.target.value }
                   return a;
                });
                break;
        }

    }

    return (
        <Box sx={{
            marginBottom: '20px',
            display: 'flex',
            flexDirection: 'column',
            gap: '10px'
        }}>
            <Typography variant='h5' color='#fff'>Добавление/изменение уравнения</Typography>
            <TextField value={name} variant='outlined' fullWidth label='Название канала' name='name'
                       onChange={handleChange}/>
            <TextField value={equation} variant='outlined' fullWidth label='Функция' name='equation'
                       placeholder='Введите функцию' onChange={handleChange}/>
            <Typography variant='h6' color='#fff'>Параметры:</Typography>
            <Box sx={{
                display: 'flex',
                flexWrap: 'wrap',
                alignItems: 'center',
            }}>
                <List sx={{
                    width: '100%'
                }}>
                    {paramList && paramList.length > 0 && paramList.map((parameter, parameterIndex) => (
                        <ListItem>
                            <Parameter key={parameter.name} title={parameter.title} name={parameter.name}
                                       onChange={handleChange}/>
                        </ListItem>
                    ))}
                </List>
            </Box>
            <Button variant={"contained"}
                    onClick={() => setModel({id: model.id, name, equation, paramList} as IModel)}>Закрыть</Button>
        </Box>
    );
};