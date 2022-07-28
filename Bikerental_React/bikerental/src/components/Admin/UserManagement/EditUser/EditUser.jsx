import React, { useState, useEffect } from 'react'
import './EditUser.css'
import Header from '../../../Header/Header'
import { useLocation, useNavigate } from 'react-router-dom'
import Avatar from '@mui/material/Avatar';
import Breadcrumbs from '@mui/material/Breadcrumbs';
import Typography from '@mui/material/Typography';
import Link from '@mui/material/Link';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import { editUser } from '../../../../functions/Admin/UserManagement/EditUser';
import { useStateValue } from '../../../../functions/Utils/StateProvider';

function EditUser() {

  const {state} = useLocation()
  const {user} = state
  const [{jwt}, dispatch] = useStateValue();
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
      href="/admin/displayUsers"
    >
      Users
    </Link>,
    <Typography key="3" color="text.primary">
      Edit User
    </Typography>
  ];

  const handleChange = (prop) => (event) => {
    setValues({ ...values, [prop]: event.target.value });
  }

  const handleEditUser = () => {
    editUser(values).then((res) => {
      navigate('/admin/displayUsers')
    })
  }

  useEffect(() => {
    setValues({...user, ...values})
  }, [])

  return (
    <div>
        <Header highlight="Users"/>
        <div className="EditUser-Nav">
          <Avatar sx={{width: 30, height: 30, marginRight: '8px'}}>
            <span className="material-icons">person</span>
          </Avatar>
          <Breadcrumbs separator="›" aria-label="breadcrumb">
            {breadcrumbs}
          </Breadcrumbs>
        </div>
        <div className="EditUser-Form">
          <div className="EditUser-Title">
            <h1>User Details</h1>
          </div>
          <TextField
            type={"email"}
            className="EditUser-Input"
            label="Email *"
            value={values.email}
            onChange={handleChange('email')}
            variant="standard"
          />
          <TextField
            type={"text"}
            className="EditUser-Input"
            label="Username *"
            value={values.username}
            onChange={handleChange('username')}
            variant="standard"
          />
          <TextField
            type={"text"}
            className="EditUser-Input"
            label="Mobile Number *"
            value={values.mobileNumber}
            onChange={handleChange('mobileNumber')}
            variant="standard"
          />
          <div className="EditUser-ButtonWrapper">
            <Button variant="contained" className="EditUser-Button" onClick={() => handleEditUser()}>Update</Button>
          </div>
        </div>
    </div>
  )
}

export default EditUser