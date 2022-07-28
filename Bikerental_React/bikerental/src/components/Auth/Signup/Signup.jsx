import React, { useState } from 'react';
import './Signup.css'
import { useNavigate } from "react-router-dom";
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import Alert from '@mui/material/Alert';
import { validateFields, saveAdmin  } from '../../../functions/Auth/Signup';


function Signup() {
  const [values, setValues] = useState({
    type: '',
    email: '',
    username: '',
    mobilenumber: '',
    password: '',
    cpassword: '',
    sellername:'',
    companyname:'',
    companyimageurl:'',
    companyaddress:'',
    userrole:'admin',
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

      if(values.email === '' || values.username ==='' || values.mobilenumber === '' || values.password === '' || values.cpassword === ''|| values.companyaddress===''||values.companyimageurl===''||values.sellername===''||values.companyname===''){
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
          saveAdmin(values).then(res=> {
            if(res==="admin already exist"){
              setValues({...values, showError: true, errorMsg:"email already exist"})
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
    <div className="Signin-Container">
      <div className="Signin-Wrapper">
        <div className="Signin-Left">
          <img src="../assets/Signup/landing.png" alt="signup" className="Signin-Image"/>
        </div>
        <div className="Signin-Right">
          <h2 className="Signin-Header">Register - Admin</h2>
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
            id="companyName"
            type={"text"}
            className="Signin-Input"
            label="CompanyName *"
            value={values.companyname}
            onChange={handleChange('companyname')}
            variant="standard"
          />
          <TextField
            error = {values.showError}
            id="companyImageUrl"
            type={"text"}
            className="Signin-Input"
            label="CompanyImageUrl *"
            value={values.companyimageurl}
            onChange={handleChange('companyimageurl')}
            variant="standard"
          />
          <TextField
            error = {values.showError}
            id="companyAddress"
            type={"text"}
            className="Signin-Input"
            label="CompanyAddress *"
            value={values.companyaddress}
            onChange={handleChange('companyaddress')}
            variant="standard"
          />
          <TextField
            error = {values.showError}
            id="sellerName"
            type={"text"}
            className="Signin-Input"
            label="SellerName *"
            value={values.sellername}
            onChange={handleChange('sellername')}
            variant="standard"
          />

          <Button id="submitButton" variant="contained" className="Signin-Button" onClick={() => performSignup()}>Submit</Button>
          <p className="Signin-Text">Already a user? <a id="signupLink" href="/user/login" className="Signin-Link">Login</a></p>
        </div>
      </div>
    </div>
  )
}

export default Signup