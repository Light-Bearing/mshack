import {HeaderProps} from "./Header.props";
import {Typography} from "@mui/material";
import {Profile} from "../Profile/Profile";
import './Header.css';

export const Header = ({ ...props }: HeaderProps) => {
    return (
        <header className='header' {...props}>
            <Typography variant='h4' color='#e3f2fd'>TimeMachine</Typography>
            <Profile />
        </header>
    );
};