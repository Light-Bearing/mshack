export interface IParameter {
    id: number,
    name: string;
    title: string;
    type: string;
}

export interface IModel {
    id: number,
    name: string,
    equation: string,
    paramList: Array<IParameter>
}

export interface IResModel {
    id: number,
    name: string,
    equation: string,
    paramEquationList: Array<IParameter>
}

export interface IParameterValueObj {
    equationId: number;
    param: Array<{
        key: string,
        value: string
    }>;
}

export interface IResult {
    calculateResult: Array<number[]>,
    recommendation: number[],
}

export interface IMonitoring {
    id: number;
    name: string;
    type: string;
    involvement: number;
    innerId: string;
    up: boolean;
    down: boolean;
    link: string;
}