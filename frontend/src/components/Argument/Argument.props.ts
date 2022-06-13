export interface ArgumentProps {
    argument: string;
    index: number
    modelID: number;
    title: string;
    onChange: (value: string, modelID: number, key: string, index: number) => void;
}