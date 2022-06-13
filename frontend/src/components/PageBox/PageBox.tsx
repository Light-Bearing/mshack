import {PageBoxProps} from "./PageBox.props";
import {Box} from "@mui/material";

export const PageBox = ({ sx, children }: PageBoxProps) => {
    const initialSx = {
        width: '100%',
        height: '100%',
        minHeight: '100vh',
        padding: '20px 0',
        backgroundColor: 'secondary.main',
    }

    return (
        <Box sx={{ ...initialSx, ...sx }}>
            {children}
        </Box>
    );
};