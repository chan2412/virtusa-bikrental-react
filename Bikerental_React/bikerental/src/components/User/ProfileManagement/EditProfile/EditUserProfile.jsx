import { Alert } from '@mui/material';
import Avatar from '@mui/material/Avatar';
import Breadcrumbs from '@mui/material/Breadcrumbs';
import Button from '@mui/material/Button';
import Link from '@mui/material/Link';
import TextField from '@mui/material/TextField';
import Typography from '@mui/material/Typography';
import React, { useEffect, useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import { editUserProfile } from '../../../../functions/User/ProfileManagement/EditUserProfile';
import { useStateValue } from '../../../../functions/Utils/StateProvider';
import Header from '../../../Header/Header';
import './EditUserProfile.css';

function EditUserProfile() {

  const { state } = useLocation()
  const { profile } = state
  const [{ jwt }, dispatch] = useStateValue();
  const [values, setValues] = useState({
    errorMsg: '',
    showError: false,
  });
  let navigate = useNavigate();
  const navigateto = () => {
    navigate('/user/displayProfile');
  }
  const breadcrumbs = [
    <Typography key="1" color="inherit">
      User
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
    editUserProfile(values).then((res) => {
      if (res === true) {
        navigate('/user/displayProfile')
      }
      else {
        console.log(res)
        setValues({ ...values, showError: true, errorMsg: res.age?res.age:res.password?res.password:res.mobilenumber })
        setTimeout(() => {
          setValues({ ...values, showError: false, errorMsg: '' })
        }, 3000);
      }
    })
  }

  useEffect(() => {

    setValues({ ...profile, ...values })
  }, [])

  return (
    <div>
      <Header highlight="Profile" />
      <div className="EditProfile-Nav">
        <Avatar sx={{ width: 30, height: 30, marginRight: '8px' }}>
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
          label="User Name *"
          value={values.username}
          disabled
          onChange={handleChange('username')}
          variant="standard"
        />
        <TextField
          type={"password"}
          className="EditProfile-Input"
          label="Password *"
          value={values.password}
          onChange={handleChange('password')}
          variant="standard"
        />
        <TextField
          type={"text"}
          className="EditProfile-Input"
          label="Age *"
          value={values.age}
          onChange={handleChange('age')}
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

export default EditUserProfile