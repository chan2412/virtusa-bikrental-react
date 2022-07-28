import React, { useEffect, useState } from 'react'
import AdminHeader from '../../../Header/Header'
import Breadcrumbs from '@mui/material/Breadcrumbs';
import { Avatar, Button, ListItemText, Typography } from '@mui/material';
import "./DisplayUserProfile.css";
import { Navigate, useNavigate } from 'react-router-dom';
import { fetchUserProfile } from '../../../../functions/User/ProfileManagement/DisplayUserProfile';
const breadcrumbs = [
    <Typography key="1" color="inherit">
        User
    </Typography>,
    <Typography key="2" color="text.primary">
        Profile
    </Typography>
];

export default function DisplayUserProfile() {

    const [user, setUser] = useState({})
    let navigate = useNavigate();

    useEffect(() => {
        fetchUserProfile().then((fetchedUser) => {
            setUser(fetchedUser)
        })
    }, [])
    const handleEditProfile = () => {
        navigate('/user/editProfile', { state: { profile: user } })
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
            <img src={"https://picsum.photos/200/200"} alt="CompanyImage" width={300} height={300} className="ProfilePic-center" />
            <br />
            <div className="details">
                <table className='displayprofiletable'>
                    <tr>
                        <th>Email</th>
                        <td>:</td>
                        <td><ListItemText> {user ? user.email : ''}</ListItemText></td>
                    </tr>
                    <tr>
                        <th>Name</th>
                        <td>:</td>
                        <td><ListItemText>{user ? user.username : ''}</ListItemText></td>
                    </tr>
                    <tr>
                        <th>Age</th>
                        <td>:</td>
                        <td><ListItemText> {user ? user.age : ''}</ListItemText></td>
                    </tr>
                    <tr>
                        <th>Mobile Number</th>
                        <td>:</td>
                        <td><ListItemText> {user ? user.mobilenumber : ''}</ListItemText></td>
                    </tr>
                </table>
                <Button onClick={() => handleEditProfile()} variant="contained" size={"large"} id="editProfile" style={{ position: "fixed", right: "3%", bottom: "5%", width: "75px", height: "75px", borderRadius: "50%" }}><span className="material-icons">edit</span></Button>
            </div>

        </div>
    )
}
