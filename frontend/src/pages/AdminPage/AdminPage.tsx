import React, {useEffect, useState} from 'react';
import {Header, Model, PageBox} from "../../components";
import {Box, Button, Container, List, ListItem, Modal, Typography} from "@mui/material";
import service from "../../sevices/Auth"
import {IModel} from "../../types/model.types";

export const AdminPage = () => {

    const [modelList, setModelList] = useState<IModel[]>([]);
    const [model,setModel]=useState<IModel>({id: -1, name: "", equation: "", paramList: []} as IModel);
    const [open, setOpen] = useState(false);
    useEffect(() => {
        service.getModel().then(res => setModelList(res.data), e => console.log(e))
    }, [])


    const handleAddFunc = () => {
        setModel({id: -1, name: "", equation: "", paramList: []});
        setOpen(true);
    }

    const handleChangeModel = (model: IModel) => {
        console.log(model)
        if (model.id === -1)
            setModelList((prev) => [...prev, {...model,id:Math.random()}]);
        else
            setModelList((prev) => {
                const a = [...prev];
                a[prev.findIndex(el=>el.id===model.id)] = {...model,id:a.length+1};
                return a
            })
        setOpen(false);
    }

    const handleClose = () => {
        setOpen(false);
    }
    const handleEdit = (index:number)=>{
        setModel(modelList[index]);
        setOpen(true);
    }

    const handleDelete = (index: number) => {
        setModelList(prev => prev.filter((el, idx) => idx !== index))
    }

    const handleSubmit = () => {
        service.sendModel(modelList).then(r => console.log(r),e=>console.log(e));
    }

    return (
        <PageBox>
            <Container sx={{
                paddingTop: '2rem',
            }}>
                <Header/>
                <main>
                    <Button variant='contained' onClick={handleAddFunc} sx={{
                        marginBottom: '20px',
                    }}>Добавить модель</Button>
                    <Modal
                        open={open}
                        onClose={handleClose}
                        sx={{
                            display: 'flex',
                            alignItems: 'center',
                            justifyContent: 'center'
                        }}
                    >
                        <Box sx={{
                            width: '50%',
                            border: '1px solid #f0f0f0',
                            borderRadius: '20px',
                            padding: '10px',
                            backgroundColor: '#000',
                        }}>
                            <Model model={model}  setModel={handleChangeModel}/>
                        </Box>
                    </Modal>
                    <Box noValidate autoComplete="off" component='form' sx={{
                        display: 'flex',
                        flexDirection: 'column',
                    }}>
                        <List>
                            {modelList.map((model, index) => (
                                 <ListItem sx={{
                                     display: 'flex',
                                     alignItems: 'center',
                                 }}>
                                     <Box sx={{
                                         display: 'flex',
                                         flexWrap: 'wrap',
                                         width: '100%',
                                         gap: '5px',
                                     }}>
                                         <Typography variant='h6' color='#fff' sx={{
                                             width: '10%',
                                             textAlign: 'center',
                                         }}>{index + 1}</Typography>
                                         <Typography variant='h6' color='#fff' sx={{
                                             width: '80%',
                                         }}>{model.name}</Typography>
                                         <Button variant={"contained"} onClick={()=>handleEdit(index)} sx={{
                                             width: '10%',
                                         }}>Изменить</Button>
                                         <Typography variant='subtitle1' color='#fff'>Модель: {model.equation}</Typography>
                                     </Box>
                                     <Button variant={"contained"} color='error' onClick={()=>handleDelete(index)}>Delete</Button>
                                 </ListItem>
                            ))}
                        </List>
                        <Button variant='contained' onClick={handleSubmit}>Отправить в систему</Button>
                    </Box>
                </main>
            </Container>

        </PageBox>
    );
};
