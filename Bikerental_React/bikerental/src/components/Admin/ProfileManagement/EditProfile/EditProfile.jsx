import React, { useState, useEffect } from 'react'
import './EditProfile.css'
import Header from '../../../Header/Header'
import { useLocation, useNavigate } from 'react-router-dom'
import Avatar from '@mui/material/Avatar';
import Breadcrumbs from '@mui/material/Breadcrumbs';
import Typography from '@mui/material/Typography';
import Link from '@mui/material/Link';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import { editProfile } from '../../../../functions/Admin/ProfileManagement/EditProfile';
import { Alert } from '@mui/material';

function EditProfile() {

  const {state} = useLocation()
  const {profile} = state
  const [values, setValues] = useState({
    errorMsg: '',
    showError: false,
  });
  let navigate = useNavigate();
  const navigateto=()=>{
    navigate('/admin/displayProfile');
  }
  const breadcrumbs = [
    <Typography key="1" color="inherit">
      Admin
    </Typography>,
    <Link
      key="2" 
      color="inherit"
      underline="hover"
      onClick={navigateto}
    >
      Profile
    </Link>,
    <Typography key="3" color="text.primary">
      Edit Profile
    </Typography>
  ];

  const handleChange = (prop) => (event) => {
    setValues({ ...values, [prop]: event.target.value });
  }

  const handleEditProfile = () => {
    editProfile(values).then((res) => {
      if (res === true) {
      navigate('/admin/displayProfile')
    }
    else {
      console.log(res)
      setValues({ ...values, showError: true, errorMsg: res.companyaddress?res.companyaddress:res.companyimageurl?res.companyimageurl:res.mobilenumber })
      setTimeout(() => {
        setValues({ ...values, showError: false, errorMsg: '' })
      }, 3000);
    }
    })
  }

  useEffect(() => {
    setValues({...profile, ...values})
  }, [profile,values])

  return (
    <div>
        <Header highlight="Profile"/>
        <div className="EditProfile-Nav">
          <Avatar sx={{width: 30, height: 30, marginRight: '8px'}}>
            <span className="material-icons">person</span>
          </Avatar>
          <Breadcrumbs separator="›" aria-label="breadcrumb">
            {breadcrumbs}
          </Breadcrumbs>
        </div>
        <div className="EditProfile-Form">
          <div className="EditProfile-Title">
            <h1>Profile Details</h1>
          </div>
          {
            values.showError ? (
              <Alert severity="error" className="Signin-Error">
                {
                  console.log(values)
                }
                Error — <strong>{values.errorMsg}</strong>
              </Alert>
            ) : (
              <div style={{ marginBottom: "45px" }}></div>
            )
          }
          <TextField
            type={"text"}
            className="EditProfile-Input"
            label="Email *"
            value={values.email}
            disabled
            onChange={handleChange('email')}
            variant="standard"
          />
          <TextField
            type={"text"}
            className="EditProfile-Input"
            label="Company Name *"
            value={values.companyname}
            disabled
            onChange={handleChange('companyname')}
            variant="standard"
          />
          <TextField
            type={"text"}
            className="EditProfile-Input"
            label="Company Address *"
            value={values.companyaddress}
            onChange={handleChange('companyaddress')}
            variant="standard"
          />
           <TextField
            type={"text"}
            className="EditProfile-Input"
            label="Company Image Url *"
            value={values.companyimageurl}
            onChange={handleChange('companyimageurl')}
            variant="standard"
          />
          <TextField
            type={"text"}
            className="EditProfile-Input"
            label="Seller Name *"
            value={values.sellername}
            disabled
            onChange={handleChange('sellername')}
            variant="standard"
          />
          <TextField
          type={"text"}
          className="EditProfile-Input"
          label="Mobile Number *"
          value={values.mobilenumber}
          onChange={handleChange('mobilenumber')}
          variant="standard"
        />
          <div className="EditProfile-ButtonWrapper">
            <Button variant="contained" className="EditProfile-Button" onClick={() => handleEditProfile()}>Update</Button>
          </div>
        </div>
    </div>
  )
}

export default EditProfile