import {IModel} from "../../types/model.types";

export interface ModelProps {
    model: IModel,
    setModel: ( model:IModel)=>void;
}