import {ParametrProps} from "./Parametr.props";
import {Box, TextField, Typography} from "@mui/material";
import {ChangeEvent, useState} from "react";
import {IParameter} from "../../types/model.types";

export const Parameter = ({ name,title,onChange}: ParametrProps) => {
    const [value, setValue] = useState<string>(title);

    const handleChange = (e: ChangeEvent<HTMLInputElement>) => {
        setValue(e.target.value);
        onChange(e);
    }

    return (
        <Box sx={{
            width: '100%',
            display: 'flex',
            flexDirection: 'column',
            gap: '5px',
        }}>
            <TextField sx={{width:'100%'}} value={value} variant='outlined' label={`Описание параметра ${name}`} name={name} onChange={handleChange} />
        </Box>
    );
};