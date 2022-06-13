import {createTheme} from "@mui/material";

export const theme = createTheme({
    palette: {
        mode: "dark",
        primary: {
            main: '#311b92',
            light: '#ffffff',
            contrastText: '#fff',
        },
        secondary: {
            main: '#000000'
        }
    },
});