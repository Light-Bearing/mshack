import {ChangeEvent} from "react";

export interface ParametrProps {
    name: string;
    title: string;
    onChange: (e: ChangeEvent<HTMLInputElement>)=>void;
}