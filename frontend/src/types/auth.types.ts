export interface IRegisterObj {
    surname: string,
    name: string,
    patronymic: string,
    username: string,
    email: string,
    rolesId: string,
    password: string
}

export interface IUser {
    token: string,
    tokenType: string,
    username: string,
    surname: string,
    name: string,
    authorities: [
        {
            authority: roleType,
        }
    ]
}

export type roleType = 'ROLE_USER' | 'ROLE_ADMIN'