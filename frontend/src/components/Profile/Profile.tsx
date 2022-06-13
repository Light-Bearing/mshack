import React from 'react';
import {Typography} from "@mui/material";
import {IUser, roleType} from "../../types/auth.types";

export const Profile = () => {

    const getRoleByRussian = (role: roleType): string => {
        switch (role) {
            case "ROLE_ADMIN": {
                return 'Администратор';
            }
            case "ROLE_USER": {
                return 'Пользователь';
            }
        }
    }

    const user :IUser =JSON.parse(localStorage.getItem("user") as string);

    return (
        <>
            {user && (
                <div>
                    <Typography variant='h6' color='#e3f2fd' align='right'>{`${user.surname} ${user.name}`}</Typography>
                    <Typography variant='subtitle1' color='#e3f2fd' align='right'>{getRoleByRussian(user.authorities[0].authority)}</Typography>
                </div>
            )}
        </>
    );
};