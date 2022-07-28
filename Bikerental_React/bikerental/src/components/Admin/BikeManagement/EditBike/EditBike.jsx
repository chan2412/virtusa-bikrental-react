import React, { useState, useEffect } from 'react'
import './EditBike.css'
import Header from '../../../Header/Header'
import { useLocation, useNavigate } from 'react-router-dom'
import Avatar from '@mui/material/Avatar';
import Breadcrumbs from '@mui/material/Breadcrumbs';
import Typography from '@mui/material/Typography';
import Link from '@mui/material/Link';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import { editBike } from '../../../../functions/Admin/BikeManagement/EditBike';

function EditBike() {

  const {state} = useLocation()
  const {bike} = state
  const [values, setValues] = useState({
    errorMsg: '',
    showError: false,
  });
  let navigate = useNavigate();

  const breadcrumbs = [
    <Typography key="1" color="inherit">
      Admin
    </Typography>,
    <Link
      key="2" 
      color="inherit"
      underline="hover"
      href="/admin/displayBikes"
    >
      Bikes
    </Link>,
    <Typography key="3" color="text.primary">
      Edit Bike
    </Typography>
  ];

  const handleChange = (prop) => (event) => {
    setValues({ ...values, [prop]: event.target.value });
  }

  const handleEditBike = () => {
    editBike(values).then((res) => {
      navigate('/admin/displayBikes')
    })
  }

  useEffect(() => {
    setValues({...bike, ...values})
  }, [])

  return (
    <div>
        <Header highlight="Bikes"/>
        <div className="EditBike-Nav">
          <Avatar sx={{width: 30, height: 30, marginRight: '8px'}}>
            <span className="material-icons">person</span>
          </Avatar>
          <Breadcrumbs separator="›" aria-label="breadcrumb">
            {breadcrumbs}
          </Breadcrumbs>
        </div>
        <div className="EditBike-Form">
          <div className="EditBike-Title">
            <h1>Bike Details</h1>
          </div>
         
          <TextField
            type={"text"}
            className="EditBike-Input"
            label="Model *"
            value={values.model}
            onChange={handleChange('model')}
            variant="standard"
          />
          <TextField
            type={"text"}
            className="EditBike-Input"
            label="Price *"
            value={values.price}
            onChange={handleChange('price')}
            variant="standard"
          />
          <TextField
            type={"text"}
            className="EditBike-Input"
            label="Type *"
            value={values.type}
            onChange={handleChange('type')}
            variant="standard"
          />
           <TextField
            type={"text"}
            className="EditBike-Input"
            label="Type *"
            value={values.type}
            onChange={handleChange('bikeimageurl')}
            variant="standard"
          />
          <div className="EditBike-ButtonWrapper">
            <Button variant="contained" className="EditBike-Button" onClick={() => handleEditBike()}>Update</Button>
          </div>
        </div>
    </div>
  )
}

export default EditBike