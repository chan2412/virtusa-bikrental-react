import React, { useState } from 'react';
import './Signup.css'
import { useNavigate } from "react-router-dom";
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import Alert from '@mui/material/Alert';
import { validateFields, saveUser } from '../../../functions/Auth/Signup';


function UserSignup() {
  const [values, setValues] = useState({
    type: '',
    email: '',
    username: '',
    mobilenumber: '',
    password: '',
    cpassword: '',
    age:'',
    userrole:'user',
    errorMsg: '',
    showError: false,
  });
  let navigate = useNavigate();

  const performSignup = async () => {
    if(values.password !== values.cpassword){
      setValues({...values, showError: true, errorMsg:"Passwords Do Not Match"})
      setTimeout(() => {
        setValues({...values, showError: false, errorMsg: ''})
      }, 3000);
    }else{

      if(values.email === '' || values.username ==='' || values.mobilenumber === '' || values.password === '' || values.cpassword === '' ||values.age === '' ){
        setValues({...values, showError: true, errorMsg:"Required Fields Missing"})
        setTimeout(() => {
          setValues({...values, showError: false, errorMsg: ''})
        }, 3000);
      }else{
        const res = validateFields(values)
        if(res.status){
          setValues({...values, showError: true, errorMsg:res.error})
          setTimeout(() => {
            setValues({...values, showError: false, errorMsg: ''})
          }, 3000);
        }else{
         saveUser(values).then(res=> {
          if(res==="user already exist"){
            setValues({...values, showError: true, errorMsg:"email/username already exist"})
            setTimeout(() => {
              setValues({...values, showError: false, errorMsg: ''})
            }, 3000);
          }
          else{
            navigate('/')
          }
          })
        }
      }
    }
  }

  const handleChange = (prop) => (event) => {
    setValues({ ...values, [prop]: event.target.value });
  }

  

  return (
    <div className="UserSignin-Container">
      <div className="UserSignin-Wrapper">
        <div className="Signin-Left">
          <img src="../assets/Signup/landing.png" alt="signup" className="Signin-Image"/>
        </div>
        <div className="Signin-Right">
          <h2 className="Signin-Header">Register - User</h2>
          {
            values.showError ? (  
              <Alert severity="error" className="Signin-Error">
                Error — <strong>{values.errorMsg}</strong>
              </Alert>
            ) : (
              <div style={{marginBottom:"45px"}}></div>
            )
          }
          <TextField
            error = {values.showError}
            id="email"
            type={"email"}
            className="Signin-Input"
            label="Email *"
            value={values.email}
            onChange={handleChange('email')}
            variant="standard"
          />
          <TextField
            error = {values.showError}
            id="username"
            type={"text"}
            className="Signin-Input"
            label="Username *"
            value={values.username}
            onChange={handleChange('username')}
            variant="standard"
          />
          <TextField
            error = {values.showError}
            id="mobileNumber"
            type={"text"}
            className="Signin-Input"
            label="Mobile Number *"
            value={values.mobilenumber}
            onChange={handleChange('mobilenumber')}
            variant="standard"
          />
          <TextField
            error = {values.showError}
            id="password"
            type={"password"}
            className="Signin-Input"
            label="Password *"
            value={values.password}
            onChange={handleChange('password')}
            variant="standard"
          />
          <TextField
            error = {values.showError}
            id="confirmPassword"
            type={"password"}
            className="Signin-Input"
            label="Confirm Password *"
            value={values.cpassword}
            onChange={handleChange('cpassword')}
            variant="standard"
          />
           <TextField
            error = {values.showError}
            id="age"
            type={"number"}
            className="Signin-Input"
            label="Age *"
            value={values.age}
            onChange={handleChange('age')}
            variant="standard"
          />
         

          <Button id="submitButton" variant="contained" className="Signin-Button" onClick={() => performSignup()}>Submit</Button>
          <p className="Signin-Text">Already a user? <a id="signupLink" href="/user/login" className="Signin-Link">Login</a></p>
        </div>
      </div>
    </div>
  )
}

export default UserSignup