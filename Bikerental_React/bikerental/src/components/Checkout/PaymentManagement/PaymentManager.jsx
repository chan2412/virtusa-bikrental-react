import { useCreditCardValidator, images } from 'react-creditcard-validator';
import React, { useEffect, useState } from 'react'
import Breadcrumbs from '@mui/material/Breadcrumbs';
import { Alert, Avatar,Button,Input,InputAdornment,ListItemText,TextField,Typography } from '@mui/material';
import "./PaymentManager.css"
import { fetchProfile } from '../../../functions/Admin/ProfileManagement/DisplayProfile';
import { Navigate, useLocation, useNavigate } from 'react-router-dom';
import AdminHeader from '../../Header/Header';
import { Box } from '@mui/system';
import { bookBike } from '../../../functions/User/BookingManagement/BookBike';
const breadcrumbs = [
    <Typography key="1" color="inherit">
        Admin
    </Typography>,
    <Typography key="2" color="text.primary">
        Bikes
    </Typography>
];
export default function PaymentManager() {
    const [user, setUser] = useState({});
    const {state} = useLocation();
  const {bookings} = state;
    let navigate = useNavigate();

    useEffect(() => {
        fetchProfile().then( (fetchedUser) => {
            setUser(fetchedUser)
        })
      }, [user])
      const handlePayment = () => {
       
          bookBike(bookings.detail,bookings.companyname).then((res) => {
            console.log(res)
            if(res){
              console.log(res);
              navigate('/user/myBookings');
          }
          })
      }
    const {
        getCardNumberProps,
        getExpiryDateProps,
        getCVCProps,
        getCardImageProps,
        meta: { erroredInputs }
      } = useCreditCardValidator();
      
      return (
        <div>
            <AdminHeader highlight={"Profile"} />
            <div className="DisplayProfile-Nav">
                <Avatar sx={{ width: 30, height: 30, marginRight: '8px' }}>
                    <span className="material-icons">person</span>
                </Avatar>
                <Breadcrumbs separator="›" aria-label="breadcrumb">
                    {breadcrumbs}
                </Breadcrumbs>
            </div>
          {/* <input {...getCardNumberProps()} />
          <small>{erroredInputs.cardNumber && erroredInputs.cardNumber}</small>
    
          <input {...getExpiryDateProps()} />
          <small>{erroredInputs.expiryDate && erroredInputs.expiryDate}</small>
    
          <input {...getCVCProps()} />
          <small>{erroredInputs.cvc && erroredInputs.cvc}</small>
    
          <svg {...getCardImageProps({ images })} /> */}
          <div className="Payment-Form">
          <div className="Payment-Title">
            <h1>Payment Form</h1>
            {
            erroredInputs.cardNumber ? (  
              <Alert severity="error" className="Signin-Error">
                Error — <strong>{erroredInputs.cardNumber && erroredInputs.cardNumber}</strong>
              </Alert>
            ) : (
                erroredInputs.expiryDate ? (  
                    <Alert severity="error" className="Signin-Error">
                      Error — <strong>{erroredInputs.expiryDate}</strong>
                    </Alert>):(
                         erroredInputs.cvc ? (  
                            <Alert severity="error" className="Signin-Error">
                              Error — <strong>{erroredInputs.cvc}</strong>
                            </Alert>
                    ):(
              <div style={{marginBottom:"45px"}}></div>
            )
                    )
            )
          }
          </div>
         
  
           <TextField className="Payment-Input"
          InputProps={{
          startAdornment: (
            <InputAdornment position="start">
              <svg {...getCardImageProps({ images })} />
            </InputAdornment>
          ),
        }}
        inputProps={getCardNumberProps()}
        />
          <TextField className="Payment-Input"
            inputProps={getExpiryDateProps()}
          />
          <TextField className="Payment-Input"
            inputProps={getCVCProps()}
          />
           
          <br/>
          <div className="Payment-ButtonWrapper">
            <Button variant="contained" className="Payment-Button" onClick={() => handlePayment()}>Submit</Button>
          </div>
        </div>
        </div>
      );
}
