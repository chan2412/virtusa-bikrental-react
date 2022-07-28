import { Avatar, Button, ListItemText, Typography } from '@mui/material';
import Breadcrumbs from '@mui/material/Breadcrumbs';
import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { fetchProfile } from '../../../../functions/Admin/ProfileManagement/DisplayProfile';
import AdminHeader from '../../../Header/Header';
import "./DisplayProfile.css";
const breadcrumbs = [
    <Typography key="1" color="inherit">
        Admin
    </Typography>,
    <Typography key="2" color="text.primary">
        Bikes
    </Typography>
];

export default function DisplayProfile() {

    const [user, setUser] = useState({})
    let navigate = useNavigate();

    useEffect(() => {
        fetchProfile().then( (fetchedUser) => {
            setUser(fetchedUser)
        })
      }, [])
      const handleEditProfile = () => {
        navigate('/admin/editProfile', { state: { profile: user } })
      }
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
            <img src={user.companyimageurl} alt="CompanyImage" width={300} height={300} className="ProfilePic-center" />
            <br/>
            <div className="details">
                <table className='displayprofiletable'>
                    <tr>
                        <th>Email</th>
                        <td>:</td>
                        <td><ListItemText> {user?user.email:''}</ListItemText></td>
                        </tr>
                        <tr>
                        <th>Company Name</th>
                        <td>:</td>
                        <td><ListItemText>{user?user.companyname:''}</ListItemText></td>
                    </tr>
                    <tr>
                    <th>Company Address</th>
                        <td>:</td>
                    <td><ListItemText> {user?user.companyaddress:''}</ListItemText></td>
                    </tr>
                    <tr>
                    <th>Mobile Number</th>
                        <td>:</td>
                    <td><ListItemText> {user?user.mobilenumber:''}</ListItemText></td>
                    </tr>
                        <tr>
                        <th>Seller Name</th>
                        <td>:</td>
                    <td><ListItemText>{user?user.sellername:''}</ListItemText></td>
                    </tr>
                </table>
                <Button onClick={()=> handleEditProfile()} variant="contained" size={"large"} id="editProfile" style={{ position: "fixed", right: "3%", bottom: "5%", width: "75px", height: "75px", borderRadius: "50%" }}><span className="material-icons">edit</span></Button>
            </div>
           
        </div>
    )
}
