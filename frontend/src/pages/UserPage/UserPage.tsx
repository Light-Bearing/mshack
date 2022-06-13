import {Assessment, Header, Monitoring, PageBox} from "../../components";
import {Box, Container, Tab, Tabs} from "@mui/material";
import { SyntheticEvent, useState} from "react";
import {TabPanel} from "../../components/TabPanel/TabPanel";


export const UserPage = () => {
    const [tabValue, setTabValue] = useState<number>(0);

    const handleChange = (event: SyntheticEvent, newValue: number) => {
        setTabValue(newValue);
    };


    return (
        <PageBox>
            <Container>
                <Header/>
                <Box sx={{ borderBottom: 1, borderColor: 'divider' }}>
                    <Tabs value={tabValue} onChange={handleChange} aria-label="basic tabs example" centered>
                        <Tab label="Оценка" />
                        <Tab label="Мониторинг" />
                    </Tabs>
                </Box>
                <TabPanel value={tabValue} index={0}>
                    <Assessment />
                </TabPanel>
                <TabPanel value={tabValue} index={1}>
                    <Monitoring />
                </TabPanel>

            </Container>
        </PageBox>
    );
};