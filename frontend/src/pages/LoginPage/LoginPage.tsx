import React, {ChangeEvent, useState} from 'react';
import {Box, Button, TextField, Typography} from "@mui/material";
import {PageBox} from "../../components";
import AuthenticationService from '../../sevices/Auth';
import {IUser} from "../../types/auth.types";
import {useNavigate} from "react-router-dom";

export const LoginPage = () => {
    const [login, setLogin] = useState<string>('');
    const [password, setPassword] = useState<string>('');
    const navigate = useNavigate();

    const handleLoginChange = (e: ChangeEvent<HTMLInputElement>) => setLogin(e.target.value);
    const handlePasswordChange = (e: ChangeEvent<HTMLInputElement>) => setPassword(e.target.value);

    const handleSubmit = async () => {
        AuthenticationService
            .signin(login, password)
            .then((user: IUser) => {
                    switch (user.authorities[0].authority) {
                        case "ROLE_USER":
                            navigate('/user');
                            break;
                        case "ROLE_ADMIN":
                            navigate('/admin');
                            break;
                        default:
                            navigate('/');
                            break;
                    }
                },
                error => {
                    console.log("Login fail: error = { " + error.toString() + " }");
                }
            )
    }

    return (
        <PageBox sx={{
            display: 'flex',
            alignItems: 'center',
            justifyContent: 'center',
        }}>
            <Box noValidate autoComplete="off" component='form' sx={{
                maxWidth: '500px',
                width: '25%',
                display: 'flex',
                flexDirection: 'column',
            }}>
                <Typography variant='h2' color='#e3f2fd'>Авторизация</Typography>
                <TextField value={login} variant='outlined' color='primary' required id='login' placeholder='Логин'
                           margin="normal" onChange={handleLoginChange}/>
                <TextField value={password} type='password' variant='outlined' required id='password'
                           placeholder='Пароль' margin="normal" onChange={handlePasswordChange}/>
                <Button variant='outlined' onClick={handleSubmit}>Войти</Button>
            </Box>
        </PageBox>
    );
};