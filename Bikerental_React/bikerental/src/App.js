import React, { useEffect } from 'react';
import './App.css';
import { BrowserRouter as Router, Routes, Route, Navigate } from "react-router-dom";
import { useStateValue } from './functions/Utils/StateProvider';
import { actionTypes } from "./functions/Utils/Reducer";
import { AsyncStorage } from 'AsyncStorage';
import Signup from './components/Auth/Signup/Signup';

import Login from './components/Auth/Login/Login';
import UserSignup from './components/Auth/Signup/UserSignup';
import DisplayBike from './components/Admin/BikeManagement/DisplayBike/DisplayBike';
import EditBike from './components/Admin/BikeManagement/EditBike/EditBike';
import AddBike from './components/Admin/BikeManagement/AddBike/AddBike';
import DisplayProfile from './components/Admin/ProfileManagement/DisplayProfile/DisplayProfile';
import EditProfile from './components/Admin/ProfileManagement/EditProfile/EditProfile';
import DisplayBookings from './components/Admin/BookingManagement/DisplayBookings';
import DisplayCompany from './components/User/BookingManagement/DisplayCompany/DisplayCompany';
import ViewCompany from './components/User/BookingManagement/ViewCompany/ViewCompany';
import ViewBike from './components/User/BookingManagement/ViewBike/ViewBike';
import BookBike from './components/User/BookingManagement/BookBike/BookBike';
import MyBookings from './components/User/BookingManagement/DisplayBookings/MyBookings';
import DisplayUserProfile from './components/User/ProfileManagement/DisplayProfile/DisplayUserProfile';
import EditUserProfile from './components/User/ProfileManagement/EditProfile/EditUserProfile';
import Dashboard from './components/SuperAdmin/Dashboard/Dashboard';
import Bookings from './components/SuperAdmin/Bookings/Bookings';
import PaymentManager from './components/Checkout/PaymentManagement/PaymentManager';
import { setAuthorizationHeader } from './functions/Utils/ApiClient';

function App() {
  const [{ user,jwt, userType }, dispatch] = useStateValue();
  
  useEffect(() => {
    console.log(user,jwt,userType);
    if (user) {
      console.log(user,userType);
      AsyncStorage.setItem('USER', JSON.stringify({
        user: `${user}`,
        jwt:`${jwt}`,
        userType: `${userType}`
      }));
      // var jwti;
      // AsyncStorage.getItem('jwt').then(res=>{
      //   jwti=res;
      //   console.log(jwti)
      // if(jwti!==undefined){
      //   console.log("setting")
      // setAuthorizationHeader(jwti);
      // }
      // })
      
    } else {
      AsyncStorage.getItem('USER').then((value) => {
       console.log(value);
        if (value) {
          value = JSON.parse(value)
          dispatch({
            type: actionTypes.SET_USER,
            user: value.user,
            jwt:value.jwt,
            userType: value.userType
          })
          setAuthorizationHeader(value.jwt);
        }
      //   var jwti;
      // AsyncStorage.getItem('jwt').then(res=>{
      //   jwti=res;
      //   console.log(jwti)
      // if(jwti!==undefined){
      //   console.log("setting")
      // setAuthorizationHeader(jwti);
      // }
      // })
      });
    }
  }, [dispatch, user,jwt, userType]);
  return (
    <div className="App">
      <Router>
        {!user ? (
          <>{console.log(user,userType)}
          <Routes>
            <Route exact path="/user/signup" element={<UserSignup />} />
            <Route exact path="/admin/signup" element={<Signup />} />
            <Route exact path="/user/login" element={<Login />} />
            <Route exact path="/" element={<Navigate replace to="/user/login" />} />
            <Route exact path="**" element={<Navigate replace to="/user/login" />} />
            <Route exact path="/login" element={<Navigate replace to="/user/login" />} />
            <Route exact path="/admin/login" element={<Navigate replace to="/user/login" />} />
          </Routes>
          </>
        ) : (
          <>
            {
              userType !== 'admin' ? (
                userType === 'user'?
                <>
                <Routes>
                  <Route exact path = "/user/displayCompany" element={<DisplayCompany />}/>
                  <Route exact path = "/user/viewCompany" element={<ViewCompany />}/>
                  <Route exact path = "/user/viewBike" element={<ViewBike />}/>
                  <Route exact path = "/user/bookBike" element={<BookBike />}/>
                  <Route exact path = "/user/myBookings" element={<MyBookings/>}/>
                  <Route exact path = "/user/payment" element={<PaymentManager/>}/>
                  <Route exact path="/user/displayProfile" element={<DisplayUserProfile />} />
                  <Route exact path="/user/editProfile" element={<EditUserProfile />} />
                  <Route exact path = "/user" element={<Navigate replace to="/user/displayCompany"/>}/>
                  <Route exact path = "/" element={<Navigate replace to="/user/displayCompany"/>}/>
                  <Route exact path = "/*" element={<Navigate replace to="/user/displayCompany"/>}/>
                  <Route exact path = "/user/login" element={<Navigate replace to="/user/displayCompany"/>}/>
                </Routes>
              </>
                :(
                <> 
                <Routes>
                  <Route exact path = "/superadmin/dashboard" element={<Dashboard/>}/>
                  <Route exact path="/superadmin/bookings" element={<Bookings />} />
                  <Route exact path = "/" element={<Navigate replace to="/superadmin/dashboard"/>}/>
                  <Route exact path = "**" element={<Navigate replace to="/superadmin/dashboard"/>}/>
                  <Route exact path = "/user/login" element={<Navigate replace to="/superadmin/dashboard"/>}/>
                  <Route exact path = "/superadmin" element={<Navigate replace to="/superadmin/dashboard"/>}/>
                </Routes>
              </>
                )
              ) : (
                <>
                  <Routes>
                    <Route exact path="/admin/displayBikes" element={<DisplayBike />} />
                    <Route exact path="/admin/displayProfile" element={<DisplayProfile />} />
                    <Route exact path="/admin/displayBookings" element={<DisplayBookings />} />
                    <Route exact path="/admin/editProfile" element={<EditProfile />} />
                    <Route exact path="/admin/addBike" element={<AddBike />} />
                    <Route exact path="/admin/editBike" element={<EditBike />} />
                    <Route exact path="/admin" element={<Navigate replace to="/admin/displayBikes" />} />
                    <Route exact path="/" element={<Navigate replace to="/admin/displayBikes" />} />
                    <Route path="**" element={<Navigate replace to="/admin/displayBikes" />} />
                    <Route exact path="/user/login" element={<Navigate replace to="/admin/displayBikes" />} />
                  </Routes>
                </>
              )
            }
          </>
        )}
      </Router>
    </div>
  );
}

export default App;
