import Alert from '@mui/material/Alert';
import Button from '@mui/material/Button';
import FormControl from '@mui/material/FormControl';
import IconButton from '@mui/material/IconButton';
import Input from '@mui/material/Input';
import InputAdornment from '@mui/material/InputAdornment';
import InputLabel from '@mui/material/InputLabel';
import TextField from '@mui/material/TextField';
import React, { useState } from 'react';
import { useNavigate } from "react-router-dom";
import { validateUser } from '../../../functions/Auth/Login';
import { actionTypes } from "../../../functions/Utils/Reducer";
import { useStateValue } from "../../../functions/Utils/StateProvider";
import './Login.css';

function UserLogin() {

  const [state, dispatch] = useStateValue();
  const [values, setValues] = useState({
    email: '',
    password: '',
    errorMsg: '',
    showPassword: false,
    showError: false
  });
  let navigate = useNavigate();

  const performLogin = async () => {
    if(values.username === '' || values.password === ''){
      setValues({...values, showError: true, errorMsg: 'Required Fields Missing'})
      setTimeout(() => {
        setValues({...values, showError: false, errorMsg: ''})
      }, 3000);
    }else{
      const res = await validateUser(values.email, values.password)
      if(res.status){
        dispatch({
          type: actionTypes.SET_USER,
          user: res.username,
          userType : res.userRole
        });
        setTimeout(() => {
          res.userRole === 'admin' ? navigate('/admin') : res.userRole === 'organizer' ? navigate('/organizer') : navigate('/user')
        }, 1000)
      }else{
        setValues({...values, showError: true, errorMsg: 'Invalid Username/Password'})
        setTimeout(() => {
          setValues({...values, showError: false, errorMsg: ''})
        }, 3000);
      }
    }
  }

  const handleChange = (prop) => (event) => {
    setValues({ ...values, [prop]: event.target.value });
  };

  const handleClickShowPassword = () => {
    setValues({
      ...values,
      showPassword: !values.showPassword,
    });
  };


  return (
    <div className="Login-Container">
      <div className="Login-Wrapper">
        <div className="Login-Left">
          <img src="../assets/Login/landing.jpg" alt="login" className="Login-Image"/>
        </div>
        <div className="Login-Right">
          <h2 className="Login-Header">Login</h2>
          {
            values.showError ? (
              <Alert severity="error" className="Login-Error">
                Error ??? <strong>{values.errorMsg}</strong>
              </Alert>
            ) : (
              <div style={{marginBottom:"45px"}}></div>
            )
          }
          <TextField
            error = {values.showError}
            id="email"
            className="Login-Input"
            label="Email *"
            value={values.email}
            onChange={handleChange('email')}
            variant="standard"
          />
          <FormControl className="Login-Input" variant="standard">
            <InputLabel id="password-label" htmlFor="password" error = {values.showError}>Password *</InputLabel>
              <Input
                error = {values.showError}
                required
                id="password"
                type={values.showPassword ? 'text' : 'password'}
                value={values.password}
                onChange={handleChange('password')}
                endAdornment={
                  <InputAdornment position="end">
                    <IconButton
                      aria-label="toggle password visibility"
                      onClick={handleClickShowPassword}
                      onMouseDown={(e) => {e.preventDefault()}}
                    >
                      {values.showPassword ? <span className="material-icons">visibility_off</span> : <span className="material-icons">visibility</span>}
                    </IconButton>
                  </InputAdornment>
                }
              />
          </FormControl>
          <Button id="loginButton" variant="contained" className="Login-Button" onClick={() => performLogin()}>Login</Button>
          <p className="Login-Text">Don't have an account? <a id="signupLink" href="/user/signup" className="Login-Link">Signup as user</a>&nbsp;OR &nbsp;
          <a id="signupLink" href="/admin/signup" className="Login-Link">Signup as admin</a></p>
        </div>
      </div>
    </div>
  )
}

export default UserLogin