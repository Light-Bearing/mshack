import React, {useEffect, useState} from 'react';
import {Box, Table, TableBody, TableCell, TableHead, TableRow} from "@mui/material";
import {IMonitoring} from "../../types/model.types";
import service from "../../sevices/Auth";

export const Monitoring = () => {
    const [rows, setRows] = useState<IMonitoring[]>();

    const fetchTableData = () => {
        service.getChannel().then(res => {
            setRows(res.data);
        }, e => console.log(e))
    }

    useEffect(() => {
        service.getChannel().then(res => {
            setRows(res.data);
        }, e => console.log(e))
    }, [])

    return (
        <Box>
            <Table sx={{ minWidth: 650 }}>
                <TableHead>
                    <TableRow>
                        <TableCell>№</TableCell>
                        <TableCell>Название канала</TableCell>
                        <TableCell align="right">Тип канала</TableCell>
                        <TableCell align="right">Охват</TableCell>
                        <TableCell align="right">В тренде</TableCell>
                        <TableCell align="right">Ссылка</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {rows && rows.length && rows.map((row) => (
                        <TableRow
                            key={row.id}
                            sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                        >
                            <TableCell component="th" scope="row">{row.id}</TableCell>
                            <TableCell >{row.name}</TableCell>
                            <TableCell align="right">{row.type}</TableCell>
                            <TableCell align="right">{row.involvement}</TableCell>
                            <TableCell align="right">{row.up ? 'Да' : 'Нет'}</TableCell>
                            <TableCell align="right">
                                <a href={row.link}>Ссылка на профиль</a>
                            </TableCell>
                        </TableRow>
                    ))}
                </TableBody>
            </Table>
        </Box>
    );
};