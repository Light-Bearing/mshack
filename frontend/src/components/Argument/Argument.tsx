import {Box, TextField, Typography} from "@mui/material";
import React, {ChangeEvent, useState} from "react";
import {ArgumentProps} from "./Argument.props";

export const Argument = ({ argument, modelID, title, index, onChange }: ArgumentProps) => {
    const [value, setValue] = useState<string>('');

    const handleChange = (e: ChangeEvent<HTMLInputElement>) => {
        setValue(e.target.value);
        onChange(e.target.value, modelID, argument, index);
    }

    return (
        <Box sx={{
            display: 'flex',
            flexDirection: 'column',
            alignItems: 'center'
        }}>
            <Typography variant='subtitle2' color='#fff'>{title}</Typography>
            <TextField value={value} variant='outlined' onChange={handleChange} />
        </Box>
    );
};