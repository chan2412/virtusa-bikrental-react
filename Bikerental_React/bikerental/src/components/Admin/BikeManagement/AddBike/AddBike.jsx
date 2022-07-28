import React, { useState, useEffect } from 'react'
import './AddBike.css'
import Header from '../../../Header/Header'
import { useNavigate } from 'react-router-dom'
import Avatar from '@mui/material/Avatar';
import Breadcrumbs from '@mui/material/Breadcrumbs';
import Typography from '@mui/material/Typography';
import Link from '@mui/material/Link';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import { addBike } from '../../../../functions/Admin/BikeManagement/AddBike';
import { FormControl, InputLabel, MenuItem, Select } from '@mui/material';

function AddBike() {

  // const {state} = useLocation()
  // const {bike} = state
  const [bike] = useState({});
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
      Add Bike
    </Typography>
  ];

  const handleChange = (prop) => (event) => {
    setValues({ ...values, [prop]: event.target.value });
  }

  const handleAddBike = () => {
    addBike(values).then((res) => {
      navigate('/admin/displayBikes')
    })
  }

  useEffect(() => {
    setValues({ ...bike, ...values })
  }, [])

  return (
    <div>
      <Header highlight="Bikes" />
      <div className="AddBike-Nav">
        <Avatar sx={{ width: 30, height: 30, marginRight: '8px' }}>
          <span className="material-icons">person</span>
        </Avatar>
        <Breadcrumbs separator="›" aria-label="breadcrumb">
          {breadcrumbs}
        </Breadcrumbs>
      </div>
      <div className="AddBike-Form">
        <div className="AddBike-Title">
          <h1>Bike Details</h1>
        </div>
        <TextField
          type={"text"}
          className="AddBike-Input"
          mask="00 00"
          label="Bike number *"
          value={values.bikeno}
          onChange={handleChange('bikeno')}
          variant="standard"
        />
        <TextField
          type={"text"}
          
          className="AddBike-Input"
          label="Model *"
          value={values.model}
          onChange={handleChange('model')}
          variant="standard"
        />
        <TextField
          type={"number"}
          onInput={(e)=>{e.target.value = e.target.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');}}
          className="AddBike-Input"
          label="Price *"
          value={values.price}
          onChange={handleChange('price')}
          variant="standard"
        />
        <TextField
            type={"text"}
            className="AddBike-Input"
            label="Bike Image Url *"
            value={values.bikeimageurl}
            onChange={handleChange('bikeimageurl')}
            variant="standard"
          />
        {/* <IconButton color="primary" aria-label='upload picture' component="label">
          <input hidden accept="image/*" type="file"/>
        </IconButton> */}
        {/* <TextField
          accept="image/*" type={"file"}
          className="AddBike-Input"
          label="Type *"
          value={values.type}
          onChange={handleChange('file')}
          variant="standard"
        /> */}
       < FormControl  className="AddBike-Input" variant="standard" >
  <InputLabel >Type *</InputLabel>
  <Select
    labelId="type-label"
    id="type-select"
    value={ values.type}
    onChange={handleChange('type')}
    variant="standard">
    <MenuItem value={"Bike"}>Bike</MenuItem>
    <MenuItem value={"Scooty"}>Scooty</MenuItem>
  </Select>
</FormControl>
        <div className="AddBike-ButtonWrapper">
          <Button variant="contained" className="AddBike-Button" onClick={() => handleAddBike()}>Add</Button>
        </div>
      </div>
    </div>
  )
}

export default AddBike