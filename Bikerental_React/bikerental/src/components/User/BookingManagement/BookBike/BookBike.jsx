import Avatar from '@mui/material/Avatar';
import Breadcrumbs from '@mui/material/Breadcrumbs';
import Button from '@mui/material/Button';
import Link from '@mui/material/Link';
import TextField from '@mui/material/TextField';
import Typography from '@mui/material/Typography';
import React, { useEffect, useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import { useStateValue } from '../../../../functions/Utils/StateProvider';
import Header from '../../../Header/Header';
import './BookBike.css';

function BookBike() {

  const {state} = useLocation()
  const {bike} = state
  const {company} = state
  const [{jwt}, dispatch] = useStateValue();
  const [values, setValues] = useState({
    errorMsg: '',
    showError: false,
  });
  let navigate = useNavigate();

  const breadcrumbs = [
    <Typography key="1" color="inherit">
      User
    </Typography>,
    <Link
    key="2" 
    color="inherit"
    underline="hover"
    href="/user/displayCompany"
  >
    Company
  </Link>,
    <Link
      key="3" 
      color="inherit"
      underline="hover"
      href="/user/"
    >
      Bike
    </Link>,
    <Typography key="4" color="text.primary">
      Book Bike
    </Typography>
  ];

  const handleChange = (prop) => (event) => {
    console.log(values,prop)
    // setValues({ ...values, [prop]: event.target.value });
    //   console.log(Number(values.price.replace(/[^0-9.-]+/g,"")) * Number(event.target.value))
    setValues({...values,[prop]: event.target.value,totalprice:""+Number(values.price.replace(/[^0-9.-]+/g,"")) * Number(event.target.value)+""})
    }

  const handleBookBike = () => {
    var x=
    {
      detail:values,
      companyname:company.companyname
    }
      navigate('/user/payment', { state: { bookings: x } })
  }

  useEffect(() => {
    setValues({...bike, ...values})
  }, [])

  return (
    <div>
        <Header highlight="Profile"/>
        <div className="BookBike-Nav">
          <Avatar sx={{width: 30, height: 30, marginRight: '8px'}}>
            <span className="material-icons">person</span>
          </Avatar>
          <Breadcrumbs separator="›" aria-label="breadcrumb">
            {breadcrumbs}
          </Breadcrumbs>
        </div>
        <div className="BookBike-Form">
          <div className="BookBike-Title">
            <h1>Booking Details</h1>
          </div>
         
          <TextField
            type={"text"}
            className="BookBike-Input"
            label="Company Name *"
            value={company.companyname}
            variant="standard"
            disabled
          />
          <TextField
            type={"text"}
            className="BookBike-Input"
            label="Bike Model *"
            value={values.model}
            variant="standard"
            disabled
          />
          <TextField
            type={"text"}
            className="BookBike-Input"
            label="Rent *"
            value={values.price}
            variant="standard"
            disabled
          />
           <TextField
            type={"text"}
            className="BookBike-Input"
            label="Days *"
            value={values.days}
            onChange={handleChange('days')}
            variant="standard"
          />
          <TextField
            type={"text"}
            className="BookBike-Input"
            label="Total Price *"
            value={values.totalprice?values.totalprice:"0"}
            variant="standard"
            disabled
          />
          <div className="BookBike-ButtonWrapper">
            <Button variant="contained" className="BookBike-Button" onClick={() => handleBookBike()}>Book</Button>
          </div>
        </div>
    </div>
  )
}

export default BookBike